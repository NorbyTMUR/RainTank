package frc.robot.Subsystems.WaterAuto;

import frc.robot.Subsystems.WaterAuto.WaterCommands.WaterCommand;

public abstract class WaveRunner{
    private static int commandOn = 0;

    //Example Auto
    private static WaterCommand[] autoOne = new WaterCommand[]{
        
    };

    //Another Example Auto
    private static WaterCommand[] autoTwo = new WaterCommand[]{

    };

    //Another Example Auto
    private static WaterCommand[] autoThree = new WaterCommand[]{

    };

    /**
     * All of the autoRoutines wrapped in Sea objects 
     * so they can be easily selected over network tables
     */
    private static Sea[] ocean = new Sea[]{
        new Sea("AutoOne", autoOne),
        new Sea("AutoTwo", autoTwo),
        new Sea("AutoThree", autoThree),
    };

    /**
     * Gets the ocean
     * @return Sea[] of ocean
     */
    public static Sea[] getOcean(){
        return ocean;
    }

    /**
     * This is a wrapper class for a WaterCommand array, 
     * it is mean to simplify selecting autos in telemetry
     */
    public static class Sea {
        private String sendableName;
        private WaterCommand[] commands;

        /**
         * Constructor for a Sea Wrapper
         * @param sendableName The Name that represents the commands
         * @param commands The commands that should be run, when this is run
         */
        public Sea(String sendableName, WaterCommand[] commands){
            this.sendableName=sendableName;
            this.commands=commands;
        }
        
        /**
         * Gets the name of the Sea
         * @return A string of the name
         */
        public String getName(){
            return sendableName;
        }

        /**
         * Gets the commands of the Sea
         * @return WaterCommand[] of the commands
         */
        public WaterCommand[] getCommands(){
            return commands;
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
            waterCommands[commandOn].update();
            if(waterCommands[commandOn].isFinished()){
                commandOn++;
            }
        } else {

        }
    }
}