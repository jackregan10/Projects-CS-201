import java.security.InvalidAlgorithmParameterException;
import java.io.*;
import java.util.*;


/**
 * Models a weighted graph of latitude-longitude points
 * and supports various distance and routing operations.
 * To do: Add your name(s) as additional authors
 * @author Brandon Fain
 * @author Owen Astrachan modified in Fall 2023
 * @author Jack Regan
 *
 */
public class GraphProcessor {
    /**
     * Creates and initializes a graph from a source data
     * file in the .graph format. Should be called
     * before any other methods work.
     * @param file a FileInputStream of the .graph file
     * @throws IOException
     * @throws Exception if file not found or error reading
     */

    // include instance variables here
    private HashMap<Point, List <Point>> adj;
    private int num_verticies;
    private int num_edges;
    private List<Point> locations;
    private HashSet<Point> visited;
    

    public GraphProcessor() {
        adj = new HashMap<>();
        locations = new ArrayList<>();
        visited = new HashSet<Point>();
        num_verticies = 0;
        num_edges = 0;
    }

    /**
     * Creates and initializes a graph from a source data
     * file in the .graph format. Should be called
     * before any other methods work.
     * @param file a FileInputStream of the .graph file
     * @throws IOException if file not found or error reading
     */

    public void initialize(FileInputStream file) throws IOException {
        Scanner scan = new Scanner(file);
        num_verticies = scan.nextInt();
        num_edges = scan.nextInt();
        for (int i = 0; i < num_verticies; i++) {
            String title = scan.next();
            double lat = scan.nextDouble();
            double longi = scan.nextDouble();
            locations.add(new Point(lat, longi));
        }
        scan.nextLine();
        for (int i = 0; i < num_edges; i++) {
            String line = scan.nextLine();
            String[] data = line.split(" ");
            adj.putIfAbsent(locations.get(Integer.parseInt(data[0])), new ArrayList<Point>());
            adj.putIfAbsent(locations.get(Integer.parseInt(data[1])), new ArrayList<Point>());
            adj.get(locations.get(Integer.parseInt(data[1]))).add(locations.get(Integer.parseInt(data[0])));
            adj.get(locations.get(Integer.parseInt(data[0]))).add(locations.get(Integer.parseInt(data[1])));
        }
        scan.close();
    }

    /**
     * NOT USED IN FALL 2023, no need to implement
     * @return list of all vertices in graph
     */

    public List<Point> getVertices(){
        return null;
    }

    /**
     * NOT USED IN FALL 2023, no need to implement
     * @return all edges in graph
     */
    public List<Point[]> getEdges(){
        return null;
    }

    /**
     * Searches for the point in the graph that is closest in
     * straight-line distance to the parameter point p
     * @param p is a point, not necessarily in the graph
     * @return The closest point in the graph to p
     */
    public Point nearestPoint(Point p) {
        Point currentPoint = new Point(0.0,0.0);
        double distance = Double.MAX_VALUE;
        for (Point i : adj.keySet()) {
            if (i.distance(p) < distance) {
                distance = i.distance(p);
                currentPoint = i;
            }
        }
        return currentPoint;
    }

    /**
     * Calculates the total distance along the route, summing
     * the distance between the first and the second Points, 
     * the second and the third, ..., the second to last and
     * the last. Distance returned in miles.
     * @param start Beginning point. May or may not be in the graph.
     * @param end Destination point May or may not be in the graph.
     * @return The distance to get from start to end
     */
    public double routeDistance(List<Point> route) {
        double d = 0.0;
        for (int i = 0; i < route.size() - 1; i++) {
            d += route.get(i).distance(route.get(i + 1));
        }
        return d;
    }
    

    /**
     * Checks if input points are part of a connected component
     * in the graph, that is, can one get from one to the other
     * only traversing edges in the graph
     * @param p1 one point
     * @param p2 another point
     * @return true if and onlyu if p2 is reachable from p1 (and vice versa)
     */
    public boolean connected(Point p1, Point p2) {
        Stack<Point> stack = new Stack<>();
        stack.push(p1);
        Point v = null;
        while (stack.size() > 0) {
            v = stack.pop();
            if (v.equals(p2)) {
                return true;
            }
            visited.add(v);
            for (Point adjacent: adj.get(v)) {
                if (!visited.contains(adjacent)) {
                    stack.push(adjacent);
                }
            }
        }
        return false;
    }

    /**
     * Returns the shortest path, traversing the graph, that begins at start
     * and terminates at end, including start and end as the first and last
     * points in the returned list. If there is no such route, either because
     * start is not connected to end or because start equals end, throws an
     * exception.
     * @param start Beginning point.
     * @param end Destination point.
     * @return The shortest path [start, ..., end].
     * @throws IllegalArgumentException if there is no such route, 
     * either because start is not connected to end or because start equals end.
     */

    public List<Point> route(Point start, Point end) throws IllegalArgumentException {
        if (start.equals(end)) {
            throw new IllegalArgumentException("No path between start and end");
        }
        Map<Point, Double> distanceMap = new HashMap<>();
        Map<Point, Point> predMap = new HashMap<>();
        predMap.put(start, null);
        final Comparator<Point> comp = new Comparator<Point>() {
            @Override
            public int compare (Point a, Point b) {
                return distanceMap.get(a).compareTo(distanceMap.get(b));
            }
        };
        PriorityQueue<Point> pq = new PriorityQueue<>(comp);
        Point current = start;
        distanceMap.put(current, 0.0);
        pq.add(current);

        while (pq.size() > 0) {
            current = pq.remove();
            if (current.equals(end)) {
                break;
            }
            for (Point p : adj.get(current)) {
                double weight = current.distance(p);
                double newDist = distanceMap.get(current) + weight;
                if (!distanceMap.containsKey(p) || newDist < distanceMap.get(p)) {
                    distanceMap.put(p, newDist);
                    predMap.put(p, current);
                    pq.add(p);
                }
            }
        }
        if (predMap.get(end) == null) {
            throw new IllegalArgumentException("No path between start and end");
        }
        List<Point> ret = new ArrayList<Point>();
        while (current != null) {
            ret.add(current);
            current = predMap.get(current);
        }
        Collections.reverse(ret);
        return ret;
    }
    public static void main(String[] args) throws FileNotFoundException, IOException {
        String name = "data/simple.graph";
        GraphProcessor gp = new GraphProcessor();
        System.out.println("running GraphProcessor");
        gp.initialize(new FileInputStream(name));
    }    
}
