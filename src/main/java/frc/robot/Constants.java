package frc.robot;

import frc.robot.MathUtils.Position2d;
import frc.robot.MathUtils.Vector2;

public final class Constants {
    /**
     * Constants of the Pneumatics
     */
    public final class PneumaticsConstants{
        public static final int SOLENOID_PORT = 0;
        public static final double MIN_PRESSURE = 95;
        public static final double MAX_PRESSURE = 100;
        public static final double PRESSURE_TO_FIRE = 95; 
    }

    /**
     * Constants of the drivetrain
     */
    public final class DriveConstants{
        //Motor Lead Ports
        public static final int LEFT_LEADER_PORT = 0;
        public static final int RIGHT_LEADER_PORT = 0;

        //Motor Follower Ports
        public static final int LEFT_FOLLOWER_PORT = 0;
        public static final int RIGHT_FOLLOWER_PORT = 0;
        
        //Conversion Factor
        private final static double MOTOR_TICKS = 4096;
        private final static double GEAR_RATIO = 1;
        public final static double WHEEL_DIAMATER = 1;
        public final static double TICK_TO_FEET = (1/MOTOR_TICKS)*GEAR_RATIO*(Math.PI*WHEEL_DIAMATER);
    
        //Sim Conversion Factor
        public static double SIM_CONVERSION = .1;
    }

    /**
     * Constants of the odometry class
     */
    public final class OdometryConstants{
        //Odometry start position
        public static final double ODOMETRY_START_X = 0;
        public static final double ODOMETRY_START_Y = 0;

        //Wheelbase inches
        public static final double WHEEL_BASE = 1;
    }
    
    /**
     * Constants of the operator class
     */
    public final class OperatorConstants{
        public static final double DEAD_ZONE = .1;

        public static final int FOWARD_AXIS = 0;
        public static final int TURN_AXIS = 1;
        public static final double TURN_STRENGTH = 0.5;

        public static final int LEFT_AXIS = 2;
        public static final int RIGHT_AXIS = 3;

        public static final int COMPRESSOR_TOGGLE_BUTTON = 2;
        public static final int FIRE_BUTTON = 0;
    }
 
    /**
     * Tuning values for auto
     */
    public final class AutoTune{
        //Tune values for the point-point ramsete controller
        public static final double RAM_POINT_B = .1;
        public static final double RAM_POINT_ZETA = .5;
        public static final double RAM_POINT_SCALE = .1;

        //Ramsete point-point deadzone
        public static final Position2d RAM_POINT_DEAD_ZONE = 
            new Position2d(new Vector2(1,1), 1);
    }
    
}
