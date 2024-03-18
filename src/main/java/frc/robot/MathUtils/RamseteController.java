package frc.robot.MathUtils;

public class RamseteController {
    //Sees if the robot is moving on preset velocities or to a point
    private boolean errorBased;

    //Tuning values
    private double b;
    private double c;
    private double v;
    private double w;
    private double scalar;

    protected Position2d error2d;
    
    /**
     * @param b Roughly a proportional term for the controller. Large values of 
     * b will result in a more aggressive controller. Must be > 0.
     * @param c Roughly a damping term (like the D term of a PID controller). Must be between 0 and 1.
     * @param v desired linear velocity
     * @param w the desired angular velocity.
     */
    public RamseteController(double b, double c, double v, double w){
        this.b=b;
        this.c=c;
        this.v=v;
        this.w=w;
        this.errorBased=false;
    }

    /**
     * @param b Roughly a proportional term for the controller. Large values of 
     * b will result in a more aggressive controller. Must be > 0.
     * @param c Roughly a damping term (like the D term of a PID controller). Must be between 0 and 1.
     * @param scalar Scale of the error
     */
    public RamseteController(double b, double c, double scalar){
        this.b = b;
        this.c = c;
        this.scalar = scalar;
        this.errorBased = true;
    }

    /**
     * Calculates the output based on current robot pos, and reference pos
     * @param currentRobotPosition The current robot position
     * @param reference The position the robot could go to
     * @return A double[] where double[0] is the leftSpeed, and double[1] is the rightspeed
     */
    public double[] calculate(Position2d currentRobotPosition, Position2d reference){
        Position2d error = currentRobotPosition.sub(reference);

        if(errorBased){
            w = scalar * error.getRotation();
            v = scalar * error.getPosition().getX();
        }

        double k = 2*c*Math.sqrt(Math.pow(w, 2)+b*Math.pow(v, 2));

        double vOut = v*Math.cos(error.getRotation())+k*error.getPosition().getX();
        double wOut = w+k*error.getRotation()+((b*v*Math.sin(error.getRotation())*error.getPosition().getY())/error.getRotation());


    }
    
}

