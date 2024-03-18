package frc.robot.Subsystems.WaterAuto.WaterCommands;

import frc.robot.Subsystems.WaterAuto.SubType;

/**
 * Super class of all water commands
 */
public abstract class WaterCommand {
    protected boolean isFinished;
    protected SubType[] subsystemRequirements;
    
    /**
     * Constructor for the WaterCommand
     */
    public WaterCommand(SubType[] subsystemRequirements){}

    /**
     * Gets the subsystem requirments
     * @return SubType[] of the subsystem requirments
     */
    public SubType[] getRequirements(){
        return subsystemRequirements;
    }

    /**
     * Inits the command 
     */
    public void init(){}

    /**
     * Updates the command
     */
    public void update(){}

    /**
     * Sees if the command is finished
     * @return A boolean that is true if the command is finsihed
     */
    public boolean isFinished(){ 
        return isFinished; 
    }
}
