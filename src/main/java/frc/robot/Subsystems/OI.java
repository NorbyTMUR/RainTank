package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.OperatorConstants;
import frc.robot.Constants.Pneumatics;
import frc.robot.Subsystems.WaterAuto.WaveRunner;

public abstract class OI {
    private static SendableChooser<String> controlModeSelector = new SendableChooser<>(); 
    private static SendableChooser<String> autoSelector = new SendableChooser<>();
    private static Joystick joystick;
    public static AnalogPotentiometer pressureTransducer;
    /**
     * Inits the OI class
     */
    public static void init(){
        //Sets up the controlModeSelector
        pressureTransducer = new AnalogPotentiometer(Pneumatics.AnalogInPort, Pneumatics.kScale, Pneumatics.kOffset);
        controlModeSelector.setDefaultOption("ArcadeDrive", "ArcadeDrive");
        controlModeSelector.addOption("TankDrive", "TankDrive");
        SmartDashboard.putData("ControlMode", controlModeSelector);

        //Sets up the autoSelector
        autoSelector.setDefaultOption(WaveRunner.getOcean()[0].getName(), WaveRunner.getOcean()[0].getName());
        for(int index = 1; index<WaveRunner.getOcean().length; index++){
            autoSelector.addOption(WaveRunner.getOcean()[index].getName(), WaveRunner.getOcean()[index].getName());
        }
        SmartDashboard.putData("AutoSelector", autoSelector);

        //Joystick setup
        joystick = new Joystick(0);
    }

    /**
     * Gets the selected auto
     * @return A int of the index of the selected auto
     */
    public static int getSelectedAuto(){
        String selected = autoSelector.getSelected();
        for(int index = 0; index<WaveRunner.getOcean().length; index++){
            if(selected.equals(WaveRunner.getOcean()[index].getName())) return index;
        }
        return -1;
    }   

    /**
     * Gets the foward axis
     * @return A double of the foward axis
     */
    public static double getForward(){
        if(Math.abs(joystick.getRawAxis(OperatorConstants.FOWARD_AXIS))>OperatorConstants.DEAD_ZONE){
            return joystick.getRawAxis(OperatorConstants.FOWARD_AXIS);
        } else {
            return 0;
        }
    }

    /**
     * @return the joystick input for Arcade Drive
     */
    public static double getTurn(){
        if(Math.abs(joystick.getRawAxis(OperatorConstants.TURN_AXIS))>OperatorConstants.DEAD_ZONE){
            return joystick.getRawAxis(OperatorConstants.TURN_AXIS)*OperatorConstants.TURN_STRENGTH;
        } else {
            return 0;
        }
    }

    /**
     * Gets the left axis
     * @return A double of the left axis
     */
    public static double getLeft(){
        if(Math.abs(joystick.getRawAxis(OperatorConstants.LEFT_AXIS))>OperatorConstants.DEAD_ZONE){
            return joystick.getRawAxis(OperatorConstants.LEFT_AXIS);
        } else {
            return 0;
        }
    }

    /**
     * Gets the right axis
     * @return A double of the right axis
     */
    public static double getRight(){
        if(Math.abs(joystick.getRawAxis(OperatorConstants.RIGHT_AXIS))>OperatorConstants.DEAD_ZONE){
            return joystick.getRawAxis(OperatorConstants.RIGHT_AXIS);
        } else {
            return 0;
        }
    }

    /**
     * Sees if the control mode is arcade drive
     * @return A boolean that is true if the control mode is arcade drive
     */
    public static boolean isArcadeDrive(){
        return controlModeSelector.getSelected() == "ArcadeDrive";
    }

    public static boolean firedPressed(){
        if(pressureTransducer.get()>Pneumatics.pressureRequired){
            return true;
        }
        return false;
    }
}
