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

    public calculate(Position2d currentRobotPosition, Position2d desiredRobotPosition, double v, double d){
    
    }

    public calculate(Position2d currentRobotPosition, Position2d desiredRobotPosition, double scale){

    }
}

