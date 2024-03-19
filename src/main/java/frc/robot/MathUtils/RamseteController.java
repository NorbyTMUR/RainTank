package frc.robot.MathUtils;

import javax.xml.namespace.QName;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.AutoTune;
import frc.robot.Constants.DriveConstants;

public class RamseteController {
    private double b;
    private double zeta;
    private Position2d deadZone;

    public RamseteController(){
        this(AutoTune.RAM_POINT_B, AutoTune.RAM_POINT_ZETA);
    }

    public RamseteController(double b, double zeta){
        this(b, zeta, AutoTune.RAM_POINT_DEAD_ZONE);
    }

    public RamseteController(double b, double zeta, Position2d deadZone){
        this.b=b;
        this.zeta=zeta;
        this.deadZone=deadZone;
    }

    private boolean isInDeadZone(Position2d error){
        return Math.abs(error.getRotation())<deadZone.getRotation() &&
            Math.abs(error.getPosition().getX())<deadZone.getPosition().getX() &&
            Math.abs(error.getPosition().getY())<deadZone.getPosition().getY();
    }

    public double[] calculate(Position2d currentRobotPosition, Position2d desiredRobotPosition, double v, double w){
        Position2d error = desiredRobotPosition.sub(currentRobotPosition);
        if(isInDeadZone(error)) return new double[2];

        double k = 2*zeta*Math.sqrt(Math.pow(w,2)+(b*Math.pow(v, 2)));

        double outputV = (v*Math.cos(error.getRotation())+k*error.getPosition().getX())/(DriveConstants.WHEEL_DIAMATER*Math.PI);
        double outputW = w+k*error.getRotation()+((b+v*Math.sin(error.getRotation())*error.getPosition().getY())/error.getRotation());

        return new double[]{outputV+outputW, outputV-outputW};
    }

    public double[] calculate(Position2d currentRobotPosition, Position2d desiredRobotPosition, double scale){
        Position2d error = desiredRobotPosition.sub(currentRobotPosition);
        return calculate(currentRobotPosition, desiredRobotPosition, error.getPosition().getX()*scale, error.getRotation()*scale);
    }
}

