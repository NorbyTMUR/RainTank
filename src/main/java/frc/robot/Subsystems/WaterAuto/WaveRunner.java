package frc.robot.Subsystems.WaterAuto;

import frc.robot.Constants.AutoTune;
import frc.robot.MathUtils.Position2d;
import frc.robot.MathUtils.RamseteController;
import frc.robot.MathUtils.Vector2;
import frc.robot.Subsystems.Drivetrain;
import frc.robot.Subsystems.WaterAuto.WaterCommands.*;

public abstract class WaveRunner{
    /**
     * Index of the the current command
     */
    private static int currentCommandIndex;

    /**
     * Index of the AutoRoutine chosen
     */
    private static int currentRoutineIndex;

    /**
     * Ramsete controller used for WaterPoint commands
     */
    private static RamseteController movePointController = 
        new RamseteController(AutoTune.RAM_POINT_B, AutoTune.RAM_POINT_C, AutoTune.RAM_POINT_SCALE, AutoTune.RAM_POINT_DEAD_ZONE);

    /**
     * AutoExampleOne commands
     */
    private static WaterCommand[] exampleOneCommands = new WaterCommand[]{
        new WaterPoint(new Position2d(new Vector2(8, 8), 0), movePointController)
    };

    /**
     * AutoExampleTwo commands
     */
    private static WaterCommand[] exampleTwoCommands = new WaterCommand[]{
        new WaterPoint(new Position2d(new Vector2(8, 8), 0), movePointController)
    };

    /**
     * All of the auto commands stored in a Sea objects 
     * so they can be selected over network tables
     */
    private static Sea[] ocean = new Sea[]{
        new Sea("AutoExampleOne", exampleOneCommands),
        new Sea("AutoExampleTwo", exampleTwoCommands)
    };

    /**
     * Gets the ocean variable, which holds all of the auto routines stored in Sea objects
     * @return Sea[] of ocean
     */
    public static Sea[] getOcean(){
        return ocean;
    }

    /**
     * This is a wrapper class for a WaterCommand array, 
     * it is meant to simplify selecting autos in telemetry
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
    public static void init(int indexOfRoutine){
        currentCommandIndex=0;
        currentRoutineIndex=indexOfRoutine;
        ocean[currentRoutineIndex].getCommands()[0].init();
    }

     /**
     * Updates and runs through all the commands sequentially. It firsts update the command that the robot is on, 
     * then it checks if that command has been finshed, if it is finished it moves to the next command. 
     * If there is no more commands it sets the robot speed to zero, and prints that the auto is finished
     */
    public static void update(){
        WaterCommand[] sea = ocean[currentRoutineIndex].getCommands();
        if(currentCommandIndex<sea.length){
            sea[currentCommandIndex].update();
            if(sea[currentCommandIndex].isFinished()){
                currentCommandIndex++;
                if(currentCommandIndex == sea.length){
                    System.out.println(ocean[currentRoutineIndex].getName()+" is finished");
                    Drivetrain.tankDrive(0,0);
                } else {
                    sea[currentCommandIndex].init();
                }
            }
        }
    }
}