package frc.robot.Subsystems.WaterAuto;

import frc.robot.MathUtils.Vector2;

public class RamseteController {
    //Tuning Values
    private static double b;
    private static double c;

    //Tuning/ComputedPath Values
    private static double Vd;
    private static double Wd;

    //Refrence Point
    private static Vector2 desiredPosition;
    private static double rotation;

    /**
     * Constructor for a RamseteController
     * @param b Roughly a proportional term for the controller. Large values of 
     * b will result in a more aggressive controller. Must be > 0.
     * @param c Roughly a damping term (like the D term of a PID controller). 
     * Must be between 0 and 1.
     * @param Vd The desired linear velocity. 
     * This can come from a computed path or can be another tuning factor.
     * @param Wd he desired angular velocity. 
     * This can come from a computed path or can be another tuning factor.
     */
    public RamseteController(double b, double c, double Vd, double Wd){
        this.b=b;
        this.c=c;
        this.Vd=Vd;
        this.Wd=Wd;
    }

    /**
     * Constructor for a RamseteController
     * @param b Roughly a proportional term for the controller. Large values of 
     * b will result in a more aggressive controller. Must be > 0.
     * @param c Roughly a damping term (like the D term of a PID controller). 
     * Must be between 0 and 1.
     * @param scaler Controls Vd and Wd to be based on a value from error
     */
    public RamseteController(double b, double c, double Vd){
        this.b=b;
        this.c=c;
        this.Vd=Vd;
        this.Wd=Wd;
    }

    public double cal
}

