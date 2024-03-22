package frc.robot.Subsystems.WaterAuto.WaterCommands;

import java.util.HashSet;
import java.util.function.BooleanSupplier;

import frc.robot.Subsystems.WaterAuto.SubType;

/**
 * An WaterCommand that loops all the commands that it gets
 */
public class LoopWave extends WaterCommand{
    private WaterCommand[] allCommands;
    private int loopPhaseOn;

    private boolean conditionStop;
    private BooleanSupplier condition;
    private int loopAmount;


    private int currentCommandIndex;

    /**
     * @param commands WaterCommand[] of the commands to loop
     * @param loopAmount The amount of times to repeate
     */
    public LoopWave(WaterCommand[] commands, int loopAmount){
        super(findRequirements(commands));
        allCommands=commands;
        this.loopAmount=loopAmount;
        this.conditionStop=false;
    }


    public LoopWave(WaterCommand[] commands, BooleanSupplier condition){
        super(findRequirements(commands));
        this.conditionStop=true;
    }

    /**
     * Inits the loopWave coomand
     */
    @Override
    public void init(){
        isFinished=false;
        loopPhaseOn=0;
    }

    /**
     * Updates the loopWave command
     */
    @Override
    public void update(){
        if((currentCommandIndex<allCommands.length && loopPhaseOn<loopAmount)
        ||(conditionStop==true&&!condition.getAsBoolean())){
            allCommands[currentCommandIndex].update();
            if(allCommands[currentCommandIndex].isFinished()){
                currentCommandIndex++;
                if(currentCommandIndex==allCommands.length||conditionStop==true){
                    currentCommandIndex=0;
                    loopPhaseOn++;
                    if(loopPhaseOn<loopAmount) isFinished=true;
                }
            }
        }
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
}

