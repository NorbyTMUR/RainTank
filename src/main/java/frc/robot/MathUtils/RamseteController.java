package frc.robot.MathUtils;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.DriveConstants;

public class RamseteController {
    /**
     * Boolean that states if it at the end point the velocity should be 0
     */
    private boolean noVelocity;

    // Tuning coefficients
    private double b;
    private double c;
    private double v;
    private double w;

    //Used when there should be no end velocity control
    private double scalar;

    //Deadzone for position and rotation errors
    private Position2d deadZone;
    
    /**
     * Constructor for standard control.
     * @param b Roughly a proportional term for the controller. Large values of 
     * b will result in a more aggressive controller. Must be > 0.
     * @param c Roughly a damping term (like the D term of a PID controller). Must be between 0 and 1.
     * @param v Desired linear velocity
     * @param w Desired angular velocity.
     * @param deadZone Deadzone  is the deadzone of the ramsete controller
     */
    public RamseteController(double b, double c, double v, double w,  Position2d deadZone){
        this.b = b;
        this.c = c;
        this.v = v;
        this.w = w;
        this.noVelocity = false;
        this.deadZone = deadZone;
    }

    /**
     * Constructor for stopped control
     * @param b Roughly a proportional term for the controller. Large values of 
     * b will result in a more aggressive controller. Must be > 0.
     * @param c Roughly a damping term (like the D term of a PID controller). Must be between 0 and 1.
     * @param scalar Scale of the error, proportional term for the controller
     * @param deadZone this is the deadzone of the ramsete controller
     */
    public RamseteController(double b, double c, double scalar, Position2d deadZone){
        this.b = b;
        this.c = c;
        this.scalar = scalar;
        this.noVelocity = true;
        this.deadZone=deadZone;
    }

    /**
     * Calculates the output based on current robot position, and reference position
     * @param currentRobotPosition The current robot position
     * @param reference The position the robot should go to
     * @return A double[] where double[0] is the leftSpeed and double[1] is the rightspeed
     */
    public double[] calculate(Position2d currentRobotPosition, Position2d reference){
        Position2d error = reference.sub(currentRobotPosition);

        if(Math.abs(error.getPosition().getX())<deadZone.getPosition().getX()&&
            Math.abs(error.getPosition().getY())<deadZone.getPosition().getY()&&
            Math.abs(error.getRotation())<deadZone.getRotation()){
                return(new double[2]);
        }

        if(noVelocity){
            w = scalar * error.getRotation();
            v = scalar * error.getPosition().getX();
        }

        double k = 2*c*Math.sqrt(Math.pow(w, 2)+b*Math.pow(v, 2));

        double vOut = v*Math.cos(error.getRotation())+k*error.getPosition().getX();
        double wOut = w+k*error.getRotation()+((b*v*Math.sin(error.getRotation())*error.getPosition().getY())/error.getRotation());

        vOut=v/(DriveConstants.WHEEL_DIAMATER*Math.PI);
        
        double[] movement = new double[2];
        movement[0] = vOut+wOut;
        movement[1] = vOut-wOut;
        return movement;
    }
    
}

