package kmeans;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Kmeans implements Clustering {

	private List<Cluster> clusters;

	public Kmeans() {

		this.clusters = new ArrayList();
	}
	
	public List<Cluster> getClusters(){
		return this.clusters;
	}

	@Override
	public double distance(Point instance, Point centroid) {
		double distance = instance.distance(centroid.getX(), centroid.getY());
		return distance;
	}

	@Override
	public Point centroid(Point oldCentroid, List<Point> instances) {

		double sumX = 0;
		double sumY = 0;

		int n_points = instances.size();

		for (Point point : instances) {
			sumX += point.getX();
			sumY += point.getY();
		}

		if (n_points > 0) {
			double newX = sumX / n_points;
			double newY = sumY / n_points;
			oldCentroid.setLocation(newX, newY);
		}

		return oldCentroid;
	}

	@Override
	public Point assign(Point instance, List<Point> centroids) {

		int num_clusters = centroids.size();
		double max = 300.0;
		double min = max;
		int cluster = 0;
		double distance = 0.0;
		min = max;

		for (int i = 0; i < num_clusters; i++) {
			Point centroid = centroids.get(i);
			distance = this.distance(instance, centroid);
			if (distance < min) {
				min = distance;
				cluster = i;
			}
		}		
		
		((Cluster) clusters.get(cluster)).addPoint(instance);
		
		return centroids.get(cluster); // @todo recheck returning point here doesnt make sense 
	}

	@Override
	public Map<Point, List<Point>> cluster(List<Point> instances, List<Point> centroids) {

		boolean hasChanged = true;
		int iteration = 0;
		do {
			
			clearClusters();
			
			// assign points to respective clusters
			for (Point instance : instances) {
				this.assign(instance, centroids);
			}
			
			// change centroid
			int index = 0;
			for (Cluster cluster : clusters){		
				System.out.println("[OLD Centroid: " + centroids.get(index) + "]");
				Point centroid_point = this.centroid(cluster.getCentroid(), cluster.getPoints());
				cluster.setCentroid(centroid_point);				
				System.out.println("[NEW Centroid: " + centroid_point + "]");        		
        		System.out.println("------------------------");
				index++;
			}
			
			iteration++;
			
			double distance = 0;
			index = 0;
			for (Cluster cluster : clusters){
				Point new_centroid = cluster.getCentroid();
        		distance += this.distance(centroids.get(index),new_centroid);        		
        		centroids.set(index, new_centroid);
        		index++;
        	}
			
			System.out.println("___________________________");
        	System.out.println("Iteration: " + iteration);
        	System.out.println("Centroid distances: " + distance);
        	plotClusters();
        	
        	if(distance == 0 )
        		hasChanged = false;
        	
		} while (hasChanged);
		

		return null;
	}

	private void plotClusters() {
    	for (int i = 0; i < clusters.size(); i++) {
    		Cluster c = clusters.get(i);
    		c.plotCluster();
    	}
    }
	
	public void init(List<Point> centroids) {
		int num_clusters = centroids.size();
		for (int i = 0; i < num_clusters; i++) {
			Cluster cluster = new Cluster(i);
			Point centroid = centroids.get(i);
			cluster.setCentroid(centroid);
			clusters.add(cluster);
		}
	}
	
	private void clearClusters() {
    	for(Cluster cluster : clusters) {
    		cluster.clear();
    	}
    }
}
