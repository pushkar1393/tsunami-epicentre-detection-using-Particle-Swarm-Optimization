/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulate_package;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import pso_Package.PSOAssumptions;

/**
 *
 * @author Pushkar
 */
public class MainFrame extends JFrame implements PSOAssumptions
        
{
	private PaintPanel canvas;
	private JPanel display;
	private JPanel gridDisplay;

	
	public MainFrame()
	{
		super("Tsunami Epicenter Detection Using PSO");
		
		gridDisplay = new JPanel(new GridBagLayout());
		add(gridDisplay, BorderLayout.CENTER);
		
		display = new JPanel();
		add(display, BorderLayout.SOUTH);

		
		canvas = new PaintPanel();
		gridDisplay.add(canvas);
		
		gridDisplay.addComponentListener(new CustomListener());

	}

	class CustomListener implements ComponentListener
	{

		@Override
		public void componentResized(ComponentEvent e) {

			int w = gridDisplay.getWidth();
			int h = gridDisplay.getHeight();
			int size = Math.min(w, h);
			canvas.setPreferredSize(new Dimension(size, size));
			gridDisplay.revalidate();
		}

		@Override
		public void componentMoved(ComponentEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void componentShown(ComponentEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void componentHidden(ComponentEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	

}
