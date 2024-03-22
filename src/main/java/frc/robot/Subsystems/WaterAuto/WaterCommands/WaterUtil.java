package frc.robot.Subsystems.WaterAuto.WaterCommands;

public abstract class WaterUtil {
    /**
     * Put a list of commands into a LoopWave, it is useful for parrel logic
     * @param waterCommands The commands you want to run
     * @return A command that holds all the command
     */
    public static WaterCommand sequentialCommand(WaterCommand[] waterCommands){
        return new LoopWave(waterCommands, 1);
    }
}
