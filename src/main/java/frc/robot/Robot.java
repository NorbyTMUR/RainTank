package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.Subsystems.Drivetrain;
import frc.robot.Subsystems.OI;
import frc.robot.Subsystems.Odometry;
import frc.robot.Subsystems.Telemetry;

public class Robot extends TimedRobot {
  @Override
  public void robotInit() {
    Drivetrain.init();
    OI.init();
    Odometry.init();
    Telemetry.init();
  }

  @Override
  public void robotPeriodic() {
    Odometry.update();
    Telemetry.update();
  }

  @Override
  public void autonomousInit() {
    
  }

  @Override
  public void autonomousPeriodic() {

  }

  @Override
  public void teleopInit() {
    Odometry.init();
  }

  @Override
  public void teleopPeriodic() {
    if(OI.isArcadeDrive()){
      Drivetrain.arcadeDrive(OI.getForward(), OI.getTurn());
    } else {
      Drivetrain.tankDrive(OI.getLeft(), OI.getRight());
    }
    
  }
}
