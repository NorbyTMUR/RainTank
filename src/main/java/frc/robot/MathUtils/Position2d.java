package frc.robot.MathUtils;

public class Position2d {
    private Vector2 position;
    private double rotation;
    
    public Position2d(Vector2 position, double rotation){
        this.position=position;
        this.rotation=rotation;
    }

    public Vector2 getPosition(){
        return position;
    }

    public double getRotation(){
        return rotation;
    }

    /**
     * Adds two position2d
     * @param other The position2d to add
     * @return A new position2d that has been added to other
     */
    public Position2d plus(Position2d other){
        return new Position2d(position.add(other.getPosition()), addAngle(rotation, other.getRotation()));
    }

    /**
     * Adds two position2d
     * @param other The position2d to add
     * @return A new position2d that has been added to other
     */
    public Position2d sub(Position2d other){
        return new Position2d(position.sub(other.getPosition()), subAngle(rotation, other.getRotation()));
    }

    /**
     * Adds a value to an angle
     * @param angle A double of the current angle in radians
     * @param add A double of the angle to add in radians
     */
    protected static double addAngle(double angle, double add){
        double newAngle = (angle*180/Math.PI) + (add*180/Math.PI);
        newAngle=newAngle%360;
        return newAngle*Math.PI/180;
    }

    /**
     * Subs a value to an angle
     * @param angle A double of the current angle in radians
     * @param add A double of the angle to sub in radians
     */
    protected static double subAngle(double angle, double sub){
        double newAngle = (angle*180/Math.PI) - (sub*180/Math.PI);
        newAngle=newAngle%360;
        return newAngle*Math.PI/180;
    }
}
