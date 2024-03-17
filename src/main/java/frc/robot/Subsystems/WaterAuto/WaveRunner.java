package frc.robot.Subsystems.WaterAuto;

import frc.robot.MathUtils.Vector2;
import frc.robot.Subsystems.WaterAuto.WaterCommands.WaterCommand;

public abstract class WaveRunner{
    private static int commandOn = 0;

    //Example Auto
    private static WaterCommand[] autoOne = new WaterCommand[]{

    };

    //Another Example Auto
    private static WaterCommand[] autoTwo = new WaterCommand[]{

    };

    /**
     * All of the autoRoutines wrapped in Sea objects 
     * so they can be easily selected over network tables
     */
    private static Sea[] ocean = new Sea[]{
        new Sea("AutoOne", autoOne)
    };

    /**
     * This is a wrapper class for a WaterCommand array, 
     * it is mean to simplify selecting autos in telemetry
     */
    private static class Sea {
        private String sendableName;
        private WaterCommand[] commands;

        public Sea(String sendableName, WaterCommand[] commands){
            this.sendableName=sendableName;
            this.commands=commands;
        }
        
    }

    /**
     * Starts the waveRunner class
     */
    public static void init(){
        commandOn=0;
    }

     /**
     * Updates and runs through all the commands
     */
    public static void update(WaterCommand[] waterCommands){
        if(commandOn<waterCommands.length){
      
        } else {

        }
    }
}