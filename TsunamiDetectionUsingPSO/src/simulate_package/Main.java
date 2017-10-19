/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulate_package;

import javax.swing.JFrame;

/**
 *
 * @author Pushkar
 */
public class Main {
    public static void main(String[] args) 
	{
		MainFrame screen = new MainFrame();
		screen.setSize(800, 600);
		screen.setVisible(true);

                screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
