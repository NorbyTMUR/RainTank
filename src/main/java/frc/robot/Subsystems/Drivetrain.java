package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.wpilibj.RobotBase;
import frc.robot.Constants.DriveConstants;

public abstract class Drivetrain {
    //Motor leader
    private static VictorSPX leftLeader;
    private static VictorSPX rightLeader;

    //Motor followers
    private static VictorSPX leftFollower;
    private static VictorSPX rightFollower;
    
    //Sim Encoder Values
    private static double simLeft;
    private static double simRight;

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
        resetEncoders();
    }

    /**
     * Zeros the encoders
     */
    public static void resetEncoders(){
        //Resets the encoders
        leftLeader.setSelectedSensorPosition(0, 0, 10);
        rightLeader.setSelectedSensorPosition(0, 0, 10);
        simLeft=0;
        simRight=0;
    }

    /**
     * Gets the encoder value of the left lead motor in inches
     */
    public static double getLeftEncoderValue(){
        if(RobotBase.isReal()) 
            return leftLeader.getSelectedSensorPosition()*DriveConstants.tickToFeet;
        return simLeft*DriveConstants.simConversion;
    }

    /**
     * Gets the encoder value of the right lead motor in inches
     */
    public static double getRightEncoderValue(){
        if(RobotBase.isReal()) 
            return rightLeader.getSelectedSensorPosition()*DriveConstants.tickToFeet;
        return simRight*DriveConstants.simConversion;
    }

    /**
     * Moves the tank drive train with arcade drive
     * @param foward The foward axis of the robot
     * @param rotation The rotation axis of the robot
     */
    public static void arcadeDrive(double foward, double rotation){
        //Movement Math
        double leftMovement = foward-rotation;
        double rightMovement = foward+rotation;

        //Normalizes the speed 
        if(leftMovement>rightMovement && leftMovement!=0){
            leftMovement/=Math.abs(leftMovement);
            rightMovement/=Math.abs(leftMovement);
        } else if(rightMovement != 0 && rightMovement!=leftMovement){
            rightMovement/=Math.abs(rightMovement);
            leftMovement/=Math.abs(rightMovement);
        }

        //Sets the motors to speed
        if(RobotBase.isReal()){
            leftLeader.set(VictorSPXControlMode.PercentOutput, leftMovement);
            rightLeader.set(VictorSPXControlMode.PercentOutput, rightMovement);
        } else {
            simLeft+=leftMovement;
            simRight+=rightMovement;
        }
    }

    /**
     * Moves the tank drive train with tank style control
     * @param leftMovement The robot's left side speed
     * @param rightMovement The robot's right side speed
     */
    public static void tankDrive(double leftMovement, double rightMovement){
        if(RobotBase.isReal()){
            leftLeader.set(VictorSPXControlMode.PercentOutput, leftMovement);
            rightLeader.set(VictorSPXControlMode.PercentOutput, rightMovement);
        } else {
            simLeft+=leftMovement;
            simRight+=rightMovement;
        }
    }
}
