package frc.robot;

public final class Constants {
    public final class DriveConstants{
        //Motor Lead Ports
        public static final int LEFT_LEADER_PORT = 0;
        public static final int RIGHT_LEADER_PORT = 0;

        //Motor Follower Ports
        public static final int LEFT_FOLLOWER_PORT = 0;
        public static final int RIGHT_FOLLOWER_PORT = 0;
        
        //Conversion Factor
        private final static double motorTicks = 4096;
        private final static double gearRatio = 1;
        private final static double wheelDiamater = 1;
        public final static double tickToFeet = (1/motorTicks)*gearRatio*(Math.PI*wheelDiamater);
    
        //Sim Conversion Factor
        public static double simConversion = .1;
    }

    public final class OdometryConstants{
        //Odometry start position
        public static final double odometryStartX = 0;
        public static final double odometryStartY = 0;

        //Wheelbase inches
        public static final double wheelBase = 3;
    }
    
    public final class OperatorConstants{
        public static final double deadZone = .1;

        public static final int fowardAxis = 0;
        public static final int turnAxis = 1;
        public static final double turnStrength = 0.5;

        public static final int leftAxis = 2;
        public static final int rightAxis = 3;

        public static final int zeroButton = 0;
    }

    public final class AutoTune{
        //Ramsete Controller
    }
}
