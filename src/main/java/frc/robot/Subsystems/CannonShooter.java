package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.Constants;

/**
 * Class that controls the pneumatics on the cannon shooter
 */
public abstract class CannonShooter{
    private static Solenoid releaseSolenoid;
    private static Compressor compressor;

    /**
     * Inits the Cannon Shooter Class
     */
    public static void init(){
        releaseSolenoid = new Solenoid(PneumaticsModuleType.CTREPCM, Constants.SolenoidPort);
        compressor = new Compressor(PneumaticsModuleType.CTREPCM);
    }

    /**
     * Updates the CannonShooter class;
     */
    public static void update(){
        //If the fire button is pressed and there is enough pressure
        if(OI.firedPressed() && getPressure()>Constants.pressureToFire){
            releaseSolenoid.set(true);
        } else {
            releaseSolenoid.set(false);
        }
            
        //Toggles the compressor
        if(OI.compressorPressed()) toggleCompressor();
    }

    /**
     * Toggels the Compressor
     */
    private static void toggleCompressor(){
        if(OI.compressorPressed()){
            if(isEnabled()){
                compressor.disable();
            } else {
                compressor.enableDigital();
            }
        }
    }

    /**
     * Gets the pressure of the Compresser
     * @return A double of the Pressure in PSI
     */
    public static double getPressure(){
        return compressor.getPressure();
    }

    /**
     * Gets the current draw of the Compresser
     * @return A double of the current draw of the Compresser
     */
    public static double getCurrent(){
        return compressor.getCurrent();
    }

    /**
     * Sees if the compresser is enabled
     * @return A boolean if the compressor is enabled
     */
    public static boolean isEnabled(){
        return compressor.isEnabled();
    }

}
