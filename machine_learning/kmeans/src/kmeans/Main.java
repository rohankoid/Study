package kmeans;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.opencsv.CSVReader;

public class Main {

	public static void main(String[] args) {
		
		Main main = new Main();
		List<Point> instances = main.readFromFile("data/MarsRoboter-Weka.csv");
		List<Point> centroids = new ArrayList<Point>();
		List<Cluster> clusters;
		centroids.add(new Point(10, 10));
		centroids.add(new Point(50, 50));		
		Kmeans km = new Kmeans();
		km.init(centroids);
		km.cluster(instances, centroids);
		clusters = km.getClusters();
		
		JFrame frame2 = new JFrame("Exercise 1.2.2.1 - K = 2");
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GraphDraw graph2 = new GraphDraw();
        graph2.setInstances(instances);        
        graph2.setClusters(clusters);
        frame2.add(graph2);
        frame2.setSize(200, 200);
        frame2.setVisible(true);
		
		// for k=3
		List<Point> centroids2 = new ArrayList<Point>();
		centroids2.add(new Point(10, 10));
		centroids2.add(new Point(50, 50));	
		centroids2.add(new Point(80, 50));	
		Kmeans kmeans = new Kmeans();
		kmeans.init(centroids2);
		kmeans.cluster(instances, centroids2);
		clusters = kmeans.getClusters();
		
		JFrame frame1 = new JFrame("Exercise 1.2.2.2 _ K 03");
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GraphDraw graph1 = new GraphDraw();
        graph1.setInstances(instances);        
        graph1.setClusters(clusters);
        frame1.add(graph1);
        frame1.setSize(200, 200);
        frame1.setVisible(true);
		
		// 2.3 inputs
		List<Point> centroids3 = new ArrayList<Point>();
		centroids3.add(new Point(10, 10));
		centroids3.add(new Point(20, 20));	
		centroids3.add(new Point(30, 30));	
		kmeans = new Kmeans();
		kmeans.init(centroids3);
		kmeans.cluster(instances, centroids3);
		clusters = kmeans.getClusters();
		
		JFrame frame = new JFrame("Exercise 1.2.3 _ Bad centroid selection");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GraphDraw graph = new GraphDraw();
        graph.setInstances(instances);        
        graph.setClusters(clusters);
        frame.add(graph);
        frame.setSize(200, 200);
        frame.setVisible(true);
		
		
	}
	
	/**
	 * Reads CSV file and returns list of points
	 * @param fileName
	 * @return instances
	 */
	public List<Point> readFromFile(String fileName) {
		CSVReader reader;
		List<Point> instances = new ArrayList<Point>();
		try {
			reader = new CSVReader(new FileReader(fileName));
			String[] nextLine;

			boolean first = true;
			while ((nextLine = reader.readNext()) != null) {
				// nextLine[] is an array of values from the line
				if (!first) {
					Point point = new Point(Integer.parseInt(nextLine[0]), Integer.parseInt(nextLine[1]));
					instances.add(point);
				}
				first = false;
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return instances;
	}

}
