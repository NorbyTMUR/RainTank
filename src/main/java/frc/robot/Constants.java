package frc.robot;

import frc.robot.MathUtils.Position2d;
import frc.robot.MathUtils.Vector2;

public final class Constants {
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

    public final class OdometryConstants{
        //Odometry start position
        public static final double ODOMETRY_START_X = 0;
        public static final double ODOMETRY_START_Y = 0;

        //Wheelbase inches
        public static final double WHEEL_BASE = 3;
    }
    
    public final class OperatorConstants{
        public static final double DEAD_ZONE = .1;

        public static final int FOWARD_AXIS = 0;
        public static final int TURN_AXIS = 1;
        public static final double TURN_STRENGTH = 0.5;

        public static final int LEFT_AXIS = 2;
        public static final int RIGHT_AXIS = 3;
    }

    /**
     * Tuning values 
     */
    public final class AutoTune{

        public static final double RAM_POINT_B = 1;
        public static final double RAM_POINT_C = 0.2;
        public static final double RAM_POINT_SCALE = 0.5;

        public static final Position2d RAM_POINT_DEAD_ZONE = 
            new Position2d(new Vector2(.4, .4), 1);
    }
}
