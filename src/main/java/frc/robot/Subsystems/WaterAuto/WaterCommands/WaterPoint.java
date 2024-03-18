package frc.robot.Subsystems.WaterAuto.WaterCommands;

import frc.robot.MathUtils.Vector2;
import frc.robot.Subsystems.WaterAuto.SubType;

/**
 * WaterCommand that moves the robot to a specific point and rotation
 */
public class WaterPoint extends WaterCommand{
    private Vector2 position;
    private double rotation;

    /**
     * Constructor for the WaterPoint command
     * @param position
     * @param rotation
     */
    public WaterPoint(Vector2 position, double rotation){
        super(new SubType[]{SubType.DriveTrain});
        this.position=position;
        this.rotation=rotation;
    }

    /**
     * Inits the WaterPoint 
     */
    @Override
    public void init(){
        
    }

    /**
     * Updates the WaterPoint
     */
    @Override
    public void update(){

    }
}
