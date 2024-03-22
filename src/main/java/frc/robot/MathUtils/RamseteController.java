package frc.robot.MathUtils;

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
            Math.abs(error.getPosition().getY())<deadZone.getPosition().getX();
    }

    public double[] calculate(Position2d currentRobotPosition, Position2d desiredRobotPosition, double v, double w){
        // Calculate error
        Position2d error = desiredRobotPosition.sub(currentRobotPosition);
        if (isInDeadZone(error)) return new double[2];

        error = new Position2d(error.getPosition().rotate(error.getRotation()), error.getRotation());
        
        double k = 2*zeta*Math.sqrt(Math.pow(w,2)+(b*Math.pow(v, 2)));

        double outputV = v*Math.cos(error.getRotation())+k*error.getPosition().getX()/5;
        double outputW = w+k*error.getRotation()+(b*v*Math.sin(error.getRotation())*error.getPosition().getY())/error.getRotation();

        SmartDashboard.putNumber("k", k);
        SmartDashboard.putNumber("v", v);
        SmartDashboard.putNumber("w", w);
        SmartDashboard.putNumber("v1", outputV);
        SmartDashboard.putNumber("w1", outputW);
        SmartDashboard.putNumber("x", error.getPosition().getX());
        SmartDashboard.putNumber("y", error.getPosition().getX());
        SmartDashboard.putNumber("rot", error.getRotation());

        double[] movement = new double[]{outputV-outputW, outputV+outputW};
        return movement;
    }

    public double[] calculate(Position2d currentRobotPosition, Position2d desiredRobotPosition, double scale){
        Position2d error = makeLocal(desiredRobotPosition.sub(currentRobotPosition));

        return calculate(currentRobotPosition, desiredRobotPosition, error.getPosition().getX()*scale, error.getRotation()*scale);
    }

    private Position2d makeLocal(Position2d error){
        return new Position2d(error.getPosition().rotate(error.getRotation()), error.getRotation());
    }
}

