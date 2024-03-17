package frc.robot.Subsystems;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import frc.robot.Constants.OdometryConstants;
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
        position = new Vector2(OdometryConstants.odometryStartX, OdometryConstants.odometryStartY);
    
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
        lastRotation=currentRotation;

        double dL = Drivetrain.getLeftEncoderValue()-lastLeftEncoderValue;
        double dR = Drivetrain.getRightEncoderValue()-lastRightEncoderValue;
       
        double d = (dL+dR)/2;
        double theta = (dR-dL)/(2*OdometryConstants.wheelBase);

        double dX = d*Math.cos(lastRotation+theta/2);
        double dY = d*Math.sin(lastRotation+theta/2);

        position = position.add(new Vector2(dX, dY));
        currentRotation = addAngle(lastRotation, theta);

        lastLeftEncoderValue=Drivetrain.getLeftEncoderValue();
        lastRightEncoderValue=Drivetrain.getRightEncoderValue();
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
     * @return A Pose2d of the robot position
     */
    public static Pose2d getPosition(){
        return new Pose2d(position.translation2d(), new Rotation2d(currentRotation));
    }
}
