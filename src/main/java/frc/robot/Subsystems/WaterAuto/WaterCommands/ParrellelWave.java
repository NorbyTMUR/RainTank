package frc.robot.Subsystems.WaterAuto.WaterCommands;
import java.util.HashSet;

import frc.robot.Subsystems.WaterAuto.SubType;

public class ParrelWave extends WaterCommand{
    private WaterCommand[] commands;

    /**
     * Constructor for a ParrelWave
     * @param commands The commands to run parrel to eachother
     */
    public ParrelWave(WaterCommand[] commands){
        super(findRequirements(commands));
        this.commands = commands;
    }

    /**
     * Gets all the requiremnts from a list of commands,
     * the reason this is not in the constructor directly is 
     * becuase super needs to be called first
     */
    protected static SubType[] findRequirements(WaterCommand[] commands){
        HashSet<SubType> requirements = new HashSet<>();
        for (WaterCommand command : commands) {
            for (SubType subType : command.getRequirements()) {
                requirements.add(subType);
            }
        }
        return requirements.toArray(new SubType[0]);
    }

    /**
     * Inits the ParrelWave 
     */
    @Override
    public void init(){
        for(WaterCommand command : commands) command.init();
    }

    /**
     * Updates the ParrelWave
     */
    @Override
    public void update(){
        for(WaterCommand command : commands) command.update();
        isFinished = false;
        for(WaterCommand command : commands) 
            isFinished = isFinished && command.isFinished();
    }
}
