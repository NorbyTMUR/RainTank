package frc.robot.Subsytems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import frc.robot.Constants.DriveConstants;

public abstract class Drivetrain {
    //Motor leader
    private static VictorSPX leftLeader;
    private static VictorSPX rightLeader;

    //Motor followers
    private static VictorSPX leftFollower;
    private static VictorSPX rightFollower;

    /**
     * Inits the DriveTrain
     */
    public static void init(){
        //Instantiates the lead motors
        leftLeader = new VictorSPX(DriveConstants.LEFT_LEADER_PORT);
        rightLeader = new VictorSPX(DriveConstants.RIGHT_LEADER_PORT);

        //Instantiates the follow motors
        leftFollower = new VictorSPX(DriveConstants.LEFT_FOLLOWER_PORT);
        rightFollower = new VictorSPX(DriveConstants.RIGHT_FOLLOWER_PORT);

        //Has the follow motors follow the lead motors
        leftFollower.follow(leftLeader, FollowerType.PercentOutput);
        rightFollower.follow(rightLeader, FollowerType.PercentOutput);

        //Configs the motors
        leftLeader.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
        rightLeader.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
        
        //Resets the encoders
        leftLeader.setSelectedSensorPosition(0, 0, 10);
        rightLeader.setSelectedSensorPosition(0, 0, 10);
    }

    /**
     * Gets the encoder value of the left lead motor
     */
    public static double getLeftEncoderValue(){
        return leftLeader.getSelectedSensorPosition();
    }

    /**
     * Gets the encoder value of the right lead motor
     */
    public static double getRightEncoderValue(){
        return leftLeader.getSelectedSensorPosition();
    }

    /**
     * Moves the tank drive train with arcade drive
     * @param foward The foward axis of the robot
     * @param rotation The rotation axis of the robot
     */
    public static void arcadeDrive(double foward, double rotation){
        //Movement Math
        double leftMovement = foward+rotation;
        double rightMovement = foward+rotation;

        //Normalizes the speed
        if(leftMovement>rightMovement && leftMovement!=0){
            leftMovement/=leftMovement;
            rightMovement/=leftMovement;
        } else if(rightMovement != 0 ){
            rightMovement/=rightMovement;
            leftMovement/=rightMovement;
        }

        //Sets the motors to speed
        leftLeader.set(VictorSPXControlMode.PercentOutput, leftMovement);
        rightLeader.set(VictorSPXControlMode.PercentOutput, rightMovement);
    }

    /**
     * Moves the tank drive train with tank style control
     * @param leftMovement The robot's left side speed
     * @param rightMovement The robot's right side speed
     */
    public static void tankDrive(double leftMovement, double rightMovement){
        leftLeader.set(VictorSPXControlMode.PercentOutput, leftMovement);
        rightLeader.set(VictorSPXControlMode.PercentOutput, rightMovement);
    }
}
