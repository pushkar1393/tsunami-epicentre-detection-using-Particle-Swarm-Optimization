/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulate_package;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;
import pso_Package.Bot;
import pso_Package.PSOAssumptions;
import pso_Package.SwarmRunner;

/**
 *
 * @author Pushkar
 */
public class PaintPanel  extends JPanel
{
	private SwarmRunner process;
	private double minX, maxX, minY, maxY;
	private Font font;
	private boolean autoIterate;
	private Timer IterateTimer;
	int counter = 0;

	public PaintPanel()
	{
		super();
		setBackground(Color.LIGHT_GRAY);
		process = new SwarmRunner();
		executeProcess(false);
		autoIterate = true;
		
		IterateTimer = new Timer(150, new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						counter++;
						if(autoIterate && counter <= PSOAssumptions.MAX_ITERATION)
							executeProcess(true);
					}
				}
		);
		IterateTimer.start();
		
		minX = PSOAssumptions.LOC_X_LOW;
		maxX = PSOAssumptions.LOC_X_HIGH;
		minY = PSOAssumptions.LOC_Y_LOW;
		maxY = PSOAssumptions.LOC_Y_HIGH;
		
		font = new Font("Serif", Font.PLAIN, 10);
	}
	
	public void setAutoIterate(boolean autoIterate) {
		this.autoIterate = autoIterate;
	}
	public void setIterateTimerDelay(int delay)
	{
		IterateTimer.setDelay(delay);
	}
	
	private Point formattedPoint(double x, double y)
	{
		
		double normX = (x - (minX + maxX)/2)/((maxX - minX)/2);
		double normY = (y - (minY + maxY)/2)/((maxY - minY)/2);
		
		return new Point(getWidth()/2 + (int)(getWidth()/2*normX), getHeight()/2 - ((int)(getHeight()/2*normY)));
	}
	
	public void executeProcess(boolean forward)
	{
		process.simulate(forward);
		repaint();
	}
	
	public int getT()
	{
		return process.getIterator();
	}
	
	private void drawGrid(Graphics g)
	{
		Point point1, point2;
		
		for(int i = (int) minX; i <= maxX; i++)
		{
			point1 = formattedPoint(i, minY);
			point2 = formattedPoint(i, maxY);
			g.drawLine(point1.x, point1.y, point2.x, point2.y);
			g.drawString(String.valueOf(i), point1.x, (point1.y + point2.y)/2);
		}
		
		for(int i = (int) minY; i <= maxY; i++)
		{
			point1 = formattedPoint(minX, i);
			point2 = formattedPoint(maxX, i);
			g.drawLine(point1.x, point1.y, point2.x, point2.y);
			g.drawString(String.valueOf(i), (point1.x + point2.x)/2, point1.y);
		}
	}
	
	public void drawCircle(Graphics g, int x, int y, int size)
	{
		g.drawOval(x - size/2, y - size/2, size, size);
	}
	
	public void fillCircle(Graphics g, int x, int y, int size)
	{
		g.fillOval(x - size/2, y - size/2, size, size);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Point point;
		
		g.setColor(Color.GRAY);
		g.setFont(font);
		
		drawGrid(g);
		
		g.setColor(Color.BLUE);
		

                for(Bot particle: process.getSwarm())
		{
			point = formattedPoint(particle.getPosition().getPos_array()[0], particle.getPosition().getPos_array()[1]);
			fillCircle(g, point.x, point.y, 4);
		}
		
		g.setColor(Color.RED);
		
		point = formattedPoint(process.getgBestPosition().getPos_array()[0], process.getgBestPosition().getPos_array()[1]);
		drawCircle(g, point.x, point.y, 8);

	}
}

