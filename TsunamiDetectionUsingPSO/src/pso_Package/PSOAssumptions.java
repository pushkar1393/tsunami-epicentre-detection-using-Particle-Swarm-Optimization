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
public interface PSOAssumptions {

    public int SWARM_SIZE = 30;
    public int MAX_ITERATION = 75;
    public int PROBLEM_DIMENSION = 2;

    public double c1 = 2.0;
    public double c2 = 2.0;

    public double W_UPPERBOUND = 1.0;
    public double W_LOWERBOUND = 0.0;

    //graph initializer
    public static final double LOC_X_LOW = -3;
    public static final double LOC_X_HIGH = 3;
    public static final double LOC_Y_LOW = -3;
    public static final double LOC_Y_HIGH = 3;
    public static final double VEL_LOW = -1;
    public static final double VEL_HIGH = 1;
    public static final double ERR_TOLERANCE = 1E-20; 
}
