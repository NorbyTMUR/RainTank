package frc.robot.Subsystems.WaterAuto.WaterCommands;
import java.util.HashSet;

import org.ejml.simple.UnsupportedOperation;

import frc.robot.Subsystems.WaterAuto.SubType;

/**
 * An Water Command that allows two commands to run parrallel
 */
public class ParallelWave extends WaterCommand{
    private WaterCommand[] commands;

    /**
     * Constructor for a ParrelWave
     * @param commands The commands to run parrel to eachother
     */
    public ParallelWave(WaterCommand[] commands){
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
                int size = requirements.size();
                requirements.add(subType);
                if(size==requirements.size()) 
                    throw new UnsupportedOperation("Commands can not use the same subsytem");
            }
        }
        return requirements.toArray(new SubType[0]);
    }

    /**
     * Inits the ParallelWave 
     */
    @Override
    public void init(){
        isFinished=false;
        for(WaterCommand command : commands) command.init();
    }

    /**
     * Updates the ParallelWave
     */
    @Override
    public void update(){
        for(WaterCommand command : commands) command.update();
        isFinished = false;
        for(WaterCommand command : commands) 
            isFinished = isFinished && command.isFinished();
    }
}
