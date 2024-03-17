package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public abstract class Telemetry {
    //Field2d of the robot position
    private static Field2d field2d = new Field2d();

    public static void init(){
        //Inits the field2d
        field2d.setRobotPose(Odometry.getPosition());
        SmartDashboard.putData(field2d);
    }

    public static void update(){
        //Updates the field2D robot position
        field2d.setRobotPose(Odometry.getPosition());
        SmartDashboard.putData(field2d);
        SmartDashboard.putNumber("EncoderLeft", Drivetrain.getLeftEncoderValue());
        SmartDashboard.putNumber("EncoderRight", Drivetrain.getRightEncoderValue());
    }
}
 