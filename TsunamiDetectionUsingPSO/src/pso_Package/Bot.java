/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pso_Package;

/**
 *
 * @author Pushkar
 */
public class Bot {

    private Position position;
    private Speed speed;
    private double fitnessValue;

    public Bot() {

    }

    public Bot(double fitnessValue, Speed speed, Position position) {
        this.fitnessValue = fitnessValue;
        this.speed = speed;
        this.position = position;
    }

    /**
     * @return the position
     */
    public Position getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * @return the speed
     */
    public Speed getSpeed() {
        return speed;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(Speed speed) {
        this.speed = speed;
    }

    /**
     * @return the fitnessValue
     */
    public double getFitnessValue() {
       fitnessValue = fitnessEvaluator(position);
        return fitnessValue;
    }

    public static double fitnessEvaluator(Position position) {
       double result = 0;
		double x = position.getPos_array()[0]; // the "x" part of the location
		double y = position.getPos_array()[1]; // the "y" part of the location
		
                result= Math.pow((Math.pow((y-2),2) + Math.pow(
                        (x-1), 2)), .5);
		
		return result;
    }
    
    
    
    
}
