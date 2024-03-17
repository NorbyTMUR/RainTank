package frc.robot.Subsystems.WaterAuto.WaterCommands;

/**
 * Super class of all water commands
 */
public abstract class WaterCommand {
    protected boolean isFinished;
    
    /**
     * Constructor for the WaterCommand
     */
    public WaterCommand(){}

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
