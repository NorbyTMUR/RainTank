package frc.robot;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {
  VictorSPX motor;
  Joystick joystick;

  @Override
  public void robotInit() {
    motor = new VictorSPX(1);
    motor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 10);

    joystick = new Joystick(0);
  }

  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("MotorEncoder", motor.getSelectedSensorPosition());
  }

  @Override
  public void teleopPeriodic() {
    motor.set(VictorSPXControlMode.PercentOutput, Math.abs(joystick.getRawAxis(0))>.1 ? joystick.getRawAxis(0) : 0);
  }

  @Override
  public void teleopExit(){
    motor.set(VictorSPXControlMode.PercentOutput, 0);
  }
}
