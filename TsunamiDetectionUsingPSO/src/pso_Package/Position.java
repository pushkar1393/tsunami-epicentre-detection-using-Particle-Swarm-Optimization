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
public class Position {
    
    private double[] pos_array;
    
    public Position(double[] pos_array){
        super();
        this.pos_array= pos_array;
    }

    /**
     * @return the pos_array
     */
    public double[] getPos_array() {
        return pos_array;
    }

    /**
     * @param pos_array the pos_array to set
     */
    public void setPos_array(double[] pos_array) {
        this.pos_array = pos_array;
    }
    
    
}
