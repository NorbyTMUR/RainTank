package frc.robot.Subsystems.WaterAuto.WaterCommands;

import frc.robot.Constants.AutoTune;
import frc.robot.MathUtils.Position2d;
import frc.robot.MathUtils.RamseteController;
import frc.robot.Subsystems.Drivetrain;
import frc.robot.Subsystems.Odometry;
import frc.robot.Subsystems.WaterAuto.SubType;

/**
 * WaterCommand that moves the robot to a specific point and rotation
 */
public class WaterPoint extends WaterCommand{
    private Position2d desiredPosition;
    private RamseteController controller;

    /**
     * Constructor for the WaterPoint command
     * @param position
     * @param rotation
     * @param controller A RamseteController that must be errorBased
     */
    public WaterPoint(Position2d desiredPosition, RamseteController controller){
        super(new SubType[]{SubType.DriveTrain});
        this.desiredPosition = desiredPosition;
        this.controller = controller;
    }

    /**
     * Inits the WaterPoint 
     */
    @Override
    public void init(){
        isFinished=false;
    }

    /**
     * Updates the WaterPoint
     */
    @Override
    public void update(){
        //Calculates the movement from the controller based of the current position, and desired position
        double[] movement = controller.calculate(Odometry.getPosition(), desiredPosition, AutoTune.RAM_POINT_SCALE);

        //If there is no movement, then the command is finished
        if(movement[0] + movement[1] == 0){
            isFinished =true;
            return;
        }

        //Moves the drivetrain to speed given by movement
        Drivetrain.tankDrive(movement[0], movement[1]);
    }
}
