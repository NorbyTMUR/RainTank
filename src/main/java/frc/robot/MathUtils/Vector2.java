package frc.robot.MathUtils;

import edu.wpi.first.math.geometry.Translation2d;

public class Vector2{
    //Represents the x and y of the vector
    private double x;
    private double y; 
    
    /**
     * Creates a new vector 2 at 0, 0
     */
    public Vector2() {
        x = 0;
        y = 0;
    }

    /**
     * Creates a new Vector2 at the x and y given
     * @param x The x of the vector
     * @param y The y of the vector
     */
    public Vector2(double x, double y){
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the x of the vector
     * @return returns a double of the x of the vector
     */
    public double getX(){
        return x;
    }

    /**
     * Gets the y of the vector
     * @return returns a double of the y of the vector
     */
    public double getY(){
        return y;
    }

    /**
     * Sets the x of the vector
     * @param x The x to set the vectors x to
     */
    public void setX(double x){
        this.x=x;
    }

    /**
     * Sets the y of the vector
     * @param x The y to set the vectors y to
     */
    public void setY(double y){
        this.y = y;
    }

    /**
     * Returns a new Vector2 in Polar  cordiantes
     * @return A Vector2 in Polar cordiantes
     */
    public Vector2 polar(){
        return new Vector2(Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)), Math.atan2(y, x));
    }

    /**
     * Adds two vector2
     * @param other The vector you want to add on the righthand side
     * @return A vector2 added by the other vector2
     */
    public Vector2 add(Vector2 other) {
        return new Vector2(this.x + other.x, this.y + other.y);
    }


    /**
     * Subtracts two Vector2
     * @param othe The Vector2 you want to substract on the righthand side
     * @return A Vector2 subtracted by the other Vector2
     */
    public Vector2 sub(Vector2 other) {
        return new Vector2(this.x - other.x, this.y - other.y);
    }

    /**
     * Mutiplies two Vector2
     * @param other The Vector2 you want to mutiplies
     * @return A Vector2 mutiplied by the other vector2
     */
    public Vector2 mult(Vector2 other) {
        return new Vector2(this.x * other.x, this.y * other.y);
    }

    /**
     * Mutiplies a vector2
     * @param m The value you want to times the vector by
     * @return A vector2 mutiplied by m
     */
    public Vector2 mult(double m) {
        return new Vector2(this.x * m, this.y * m);
    }
    
    /**
     * Gets the distance of the Vector2 from zero
     * @return A double of the Vector2 distance from zero
     */
    public double distance(){
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    /**
     * Gets the distance of the Vector2 from the other Vector2
     * @return A double of the Vector2 distance from other Vector2
     * @param other A Vector2 of the other point
     */
    public double distance(Vector2 other){
        return Math.sqrt(Math.pow(x-other.x, 2) + Math.pow(y-other.y, 2));
    }

    /**
     * Rotates a Vector2
     * @param angle Angle to rotate by in radians
     * @return A Vector2 of the roatated Vector2
     */
    public Vector2 rotate(double angle){
        double x_new = x * Math.cos(angle) - y * Math.sin(angle);
        double y_new = x * Math.sin(angle) + y * Math.cos(angle);
        return new Vector2(x_new, y_new);
    }


    /**
     * Sets the Vector2 to a translation2D 
     * @return a Translation2D
     */
    public Translation2d translation2d(){
        return new Translation2d(this.x, this.y);
    }

    /**
     * Sets the Vector2 to a string
     */
    @Override
    public String toString(){
        return x + ", " + y;
    }
} 

