package frc.robot.Subsystems;

import frc.robot.Constants.OdometryConstants;
import frc.robot.MathUtils.Position2d;
import frc.robot.MathUtils.Vector2;

public abstract class Odometry {
    //A Vector2 of the position of the robot
    private static Vector2 position;

    //Encoder Values
    private static double lastLeftEncoderValue = 0;
    private static double lastRightEncoderValue = 0;

    //Rotation
    private static double lastRotation;
    private static double currentRotation;

    public static void init(){
        //Creates the position at starting position
        position = new Vector2(OdometryConstants.ODOMETRY_START_X, OdometryConstants.ODOMETRY_START_Y);
    
        zeroValues();
    }

    public static void zeroValues(){
        //Sets last encoder values to to 0
        lastLeftEncoderValue=0;
        lastRightEncoderValue=0;

        //Resets rotation
        lastRotation=0;
        currentRotation=0;
        
        //Zeros encoders
        Drivetrain.resetEncoders();
    }
    /**
     * Updates the odometry
     */
    public static void update(){
        //Sets the lastRotation to the true lastRotation
        lastRotation=currentRotation;

        //Get the delta change in encoder values
        double dL = Drivetrain.getLeftEncoderValue()-lastLeftEncoderValue;
        double dR = Drivetrain.getRightEncoderValue()-lastRightEncoderValue;
        
        //Gets the distance traveled
        double d = (dL+dR)/2;

        //Gets theta
        double theta = (dR-dL)/(2*OdometryConstants.WHEEL_BASE);

        //Gets the delta change in x and y
        double dX = d*Math.cos(lastRotation+theta/2);
        double dY = d*Math.sin(lastRotation+theta/2);

        //Adds the delta x and y to position
        position = position.add(new Vector2(dX, dY));

        //Adds theta to currentRotation
        currentRotation = addAngle(lastRotation, theta);

        //Sets the last encoder values to current encoder values
        lastLeftEncoderValue = Drivetrain.getLeftEncoderValue();
        lastRightEncoderValue = Drivetrain.getRightEncoderValue();
    }

     /**
     * Adds a value to an angle
     * @param angle A double of the current angle in radians
     * @param add A double of the angle to add in radians
     */
    private static double addAngle(double angle, double add){
        double newAngle = (angle*180/Math.PI) + (add*180/Math.PI);
        newAngle=newAngle%360;
        return newAngle*Math.PI/180;
    }
    
    /**
     * Gets the position and rotation
     * @return A Position2d of the robot position
     */
    public static Position2d getPosition(){
        return new Position2d(position, currentRotation);
    }
}
