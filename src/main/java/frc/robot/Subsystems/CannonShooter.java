package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.Constants.PneumaticsConstants;

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
        releaseSolenoid = new Solenoid(PneumaticsModuleType.REVPH, PneumaticsConstants.SOLENOID_PORT);
        compressor = new Compressor(PneumaticsModuleType.REVPH);
    }

    /**
     * Fires the CannonShooter;
     */
    public static void fire(){
        if(compressor.getPressure()>PneumaticsConstants.PRESSURE_TO_FIRE){
            releaseSolenoid.set(true);
        }
    }  

    /**
     * Disables the solenoid on the CannonShooter;
     */
    public static void fireDisabled(){
        releaseSolenoid.set(false);
    }  

    /**
     * Toggels the Compressor
     */
    public static void toggleCompressor(){
        if(isEnabled()){
            compressor.disable();
        } else {
            compressor.enableAnalog(PneumaticsConstants.MIN_PRESSURE, PneumaticsConstants.MAX_PRESSURE);
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
