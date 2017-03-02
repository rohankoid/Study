package kmeans;

/* Simple graph drawing class
Bert Huang
COMS 3137 Data Structures and Algorithms, Spring 2009

This class is really elementary, but lets you draw 
reasonably nice graphs/trees/diagrams. Feel free to 
improve upon it!
 */

import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.geom.Ellipse2D;

import javax.swing.*;

public class GraphDraw extends JPanel {

	private List<Point> instances;
	private List<Cluster> clusters;
	
	public void setInstances(List<Point> points){
		this.instances = points;
	}
	
	public void setClusters(List<Cluster> clusters)
	{
		this.clusters = clusters;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// draw points
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.blue);		
		for (Point instance : instances) {
			int x = (int) instance.getX();
			int y = (int) instance.getY();
			g2d.drawLine(x, y, x, y);
		}
		
		// draw clusters
		
		g2d.setColor(Color.red);		
		for (Cluster cluster : clusters) {
			double radius = cluster.getRadius();
			Point centre = cluster.getCentroid();
			Shape theCircle = new Ellipse2D.Double(centre.getX() - radius, centre.getY() - radius, 2.0 * radius, 2.0 * radius);
		    g2d.draw(theCircle);			
		}
		
	}
}
