/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pso_Package;

import java.util.Random;
import java.util.Vector;

/**
 *
 * @author Pushkar
 */
public class SwarmRunner implements PSOAssumptions {

    Random rand = new Random();

    private Vector<Bot> swarm = new Vector<>();
    private Vector<Position> pBestPosition = new Vector<>();
    private double[] pBest = new double[SWARM_SIZE];
    private double gBest;
    private Position gBestPosition;
    private double[] fitnessValueList = new double[SWARM_SIZE];
    private int iterator = 0;
    private double w;
    private double err = 9999;

    
    public void poolCreator() {
        Bot bot;
        for (int i = 0; i < SWARM_SIZE; i++) {
            bot = new Bot();

          
            double[] pos = new double[PROBLEM_DIMENSION];

            pos[0] = LOC_X_LOW + rand.nextDouble() * (LOC_X_HIGH - LOC_X_LOW);
            pos[1] = LOC_Y_LOW + rand.nextDouble() * (LOC_Y_HIGH - LOC_Y_LOW);
            Position position = new Position(pos);

           
            double[] speed = new double[PROBLEM_DIMENSION];
            speed[0] = VEL_LOW + rand.nextDouble() * (VEL_HIGH - VEL_LOW);
            speed[1] = VEL_LOW + rand.nextDouble() * (VEL_HIGH - VEL_LOW);
            Speed s = new Speed(speed);

            bot.setPosition(position);
            bot.setSpeed(s);
            swarm.add(bot);
        }
    }

    public void rewriteValues() {
        for (int i = 0; i < SWARM_SIZE; i++) {
            fitnessValueList[i] = swarm.get(i).getFitnessValue();
        }
    }

    public void simulate(boolean forward) {
        if (forward && iterator < MAX_ITERATION) {
            iterator++;
        } else if (!forward && iterator > 0) {
            iterator = 0;
        }

        if ( iterator == 0) {
            poolCreator();
            rewriteValues();

            for (int i = 0; i < SWARM_SIZE; i++) {
                pBest[i] = fitnessValueList[i];
                pBestPosition.add(swarm.get(i).getPosition());
            }
           
        }

        if ( iterator < MAX_ITERATION && err > ERR_TOLERANCE) {
            
            for (int i = 0; i < SWARM_SIZE; i++) {
                if (fitnessValueList[i] < pBest[i]) {
                    pBest[i] = fitnessValueList[i];
                    pBestPosition.set(i, swarm.get(i).getPosition());
                }
            }

           
            int gBestPositionIndex = getMinPos(fitnessValueList);
            if (iterator == 0 || fitnessValueList[gBestPositionIndex] < gBest) {
                gBest = fitnessValueList[gBestPositionIndex];
                gBestPosition = swarm.get(gBestPositionIndex).getPosition();
            }

            w = W_UPPERBOUND - (((double) iterator) / MAX_ITERATION) * (W_UPPERBOUND - W_LOWERBOUND);

            for (int i = 0; i < SWARM_SIZE; i++) {
                double r1 = rand.nextDouble();
                double r2 = rand.nextDouble();

                Bot bot = swarm.get(i);

              
                double[] newSpeed = new double[PROBLEM_DIMENSION];
                newSpeed[0] = (w * bot.getSpeed().getSpeed_array()[0])
                        + (r1 * c1) * (pBestPosition.get(i).getPos_array()[0] - bot.getPosition().getPos_array()[0])
                        + (r2 * c2) * (gBestPosition.getPos_array()[0] - bot.getPosition().getPos_array()[0]);
                newSpeed[1] = (w * bot.getSpeed().getSpeed_array()[1])
                        + (r1 * c1) * (pBestPosition.get(i).getPos_array()[1] - bot.getPosition().getPos_array()[1])
                        + (r2 * c2) * (gBestPosition.getPos_array()[1] - bot.getPosition().getPos_array()[1]);
                Speed speed = new Speed(newSpeed);
                bot.setSpeed(speed);

                
                double[] newLoc = new double[PROBLEM_DIMENSION];
                newLoc[0] = bot.getPosition().getPos_array()[0] + newSpeed[0];
                newLoc[1] = bot.getPosition().getPos_array()[1] + newSpeed[1];
                Position pos = new Position(newLoc);
                bot.setPosition(pos);
            }

            err = Bot.fitnessEvaluator(gBestPosition); 

            System.out.println( iterator+ " round for gBest detection");
            System.out.println("X coordinate: " + gBestPosition.getPos_array()[0]);
            System.out.println("Y coordinate: " + gBestPosition.getPos_array()[1]);
            System.out.println(" optimal Value: " + Bot.fitnessEvaluator(gBestPosition));

            rewriteValues();
        } else {

            System.out.println("\n" + ( iterator - 1) + "round:");
            System.out.println("    Epicenter x coordinate: " + gBestPosition.getPos_array()[0]);
            System.out.println("     Epicenter y coordinate: " + gBestPosition.getPos_array()[1]);
        }

    }

    private static int getMinPos(double[] list) {
        int pos = 0;
        double minValue = list[0];

        for (int i = 0; i < list.length; i++) {
            if (list[i] < minValue) {
                pos = i;
                minValue = list[i];
            }
        }

        return pos;
    }

    public Vector<Bot> getSwarm() {
        return swarm;
    }

    public Vector<Position> getpBestPosition() {
        return pBestPosition;
    }

    public Position getgBestPosition() {
        return gBestPosition;
    }

    public int getIterator() {
        return iterator;
    }

}
