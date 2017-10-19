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
public class Speed {
   
    private double[] speed_array;
    
    public Speed(double[] speed_array){
        super();
        this.speed_array= speed_array;
    }

    /**
     * @return the speed_array
     */
    public double[] getSpeed_array() {
        return speed_array;
    }

    /**
     * @param speed_array the speed_array to set
     */
    public void setSpeed_array(double[] speed_array) {
        this.speed_array = speed_array;
    }
}
