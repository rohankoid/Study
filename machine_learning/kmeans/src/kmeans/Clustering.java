package kmeans;

import java.awt.Point;
import java.util.List;
import java.util.Map;

public interface Clustering {

	/**
	 * Berechnet den Abstand zwischen einem beliebigen Punkt und einem Centroid.
	 * 
	 * @param instance
	 *            der Punkt
	 * @param centroid
	 *            der Centroid
	 * @return Abstand zwischen Punkt und Centroid
	 */
	public double distance(Point instance, Point centroid);

	/**
	 * Berechnet den Centroid eines Clusters aus Punkten. Falls dem Centroid
	 * keine Punkte zugeordnet sind bleibt der Centroid unver�ndert.
	 * 
	 * @param oldCentroid
	 *            der alte Centroid
	 * @param instances
	 *            die Punkte
	 * @return der Centroid
	 */
	public Point centroid(Point oldCentroid, List<Point> instances);

	/**
	 * Liefert den n�chsten Centroid zu einem Punkt
	 * 
	 * @param instance
	 *            der Punkt
	 * @param centroids
	 *            die m�glichen Centroide
	 * @return der n�chste Centroid am Punkt
	 */
	public Point assign(Point instance, List<Point> centroids);

	/**
	 * Implementierung des Clustering-Algorithmus (bspw. k-Means). Weisst allen
	 * Punkten einen Centroid zu. Das Ergebnis ist eine Liste der endg�ltigen
	 * Centroide, sowie deren zugeh�rige Punkte.
	 * 
	 * @param instances
	 *            die Punkte
	 * @param centroids
	 *            die Centroide
	 * @return Centroide und deren zugewiesene Punkte
	 */
	public Map<Point, List<Point>> cluster(List<Point> instances,
			List<Point> centroids);

}
