import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

/**
 * DGraph.java
 * @author adityagupta
 *
 * A directed graph
 */
public class DGraph 
{
    private List<Edge> edges;
    private int numOfNodes;

    /**
     * DGraph
     * Constructs a DGraph
     * @param numOfNodes
     */
    public DGraph(int numOfNodes) 
    {
        edges = new ArrayList<Edge>();
        this.numOfNodes = numOfNodes;
    }

    /**
     * getNumNodes
     * @return integer number of Nodes
     */
    public int getNumNodes() 
    {
        return numOfNodes;
    }

    /**
     * getWeight
     * Returns weight of specifed edge
     * @param a
     * @param b
     * @return integer weight
     */
    public double getWeight(int a, int b) 
    {
        for (Edge e : edges) 
        {
            if (e.node1 == a && e.node2 == b) 
            {
                return e.weight;
            }
        }
        
        return -1;
    }

    /**
     * getNeighbors
     * Returns list of neighbors
     * @param a
     * @return list of a's neighbors
     */
    public List<Integer> getNeighbors(int a) 
    {
        List<Integer> neighbors = new ArrayList<Integer>();
        
        for (Edge e : edges) 
        {
            if (e.node1 == a) 
            {
                neighbors.add(e.node2);
            }
            else if (e.node2 == a) 
            {
                neighbors.add(e.node1);
            }
        }
        
        Collections.sort(neighbors);
        
        return neighbors;
    }

    /**
     * addEdge
     * Adds directed edge
     * @param a
     * @param b
     * @param d
     */
    public void addEdge(int a, int b, double d) 
    {
        Edge addedEdge = new Edge(a, b, d);
        this.edges.add(addedEdge);
        
        if (a > numOfNodes) 
        {
            numOfNodes++;
        }
        if (b > numOfNodes) 
        {
            numOfNodes++;
        }
    }

    /**
     * toDotString
     * String implementation
     * @return string dotStr
     */
    public String toDotString() 
    {
        String dotStr = "digraph {\n";

        for (Edge e : edges) 
        {
            dotStr += e.toDotString() + "\n";
        }
        return dotStr + "}\n";
    }

    public class Edge implements Comparable<Edge> 
    {
        private final int node1;
        private final int node2;
        private final double weight;
    
        /**
         * Edge
         * stores given edge
         * @param node1
         * @param node2
         * @param weight
         */
        public Edge(int node1, int node2, double weight) 
        {
            assert weight >= 0.0;
            this.node1 = node1;
            this.node2 = node2;
            this.weight = weight;
        }

        /**
         * toDotString
         * String implementation
         * @return string dotStr
         */
        public String toDotString() 
        {
            return "" + node1 + " -> " + node2 + " [label=\"" + weight + "\"];";
        }

        /**
         * compareTo
         * ordering edges
         * @return string of directed edge
         */
        public int compareTo(Edge other) 
        {
            if (this.equals(other)) 
            {
                return 0;
            } 
            else if ((node1 < other.node1) || (node1 == other.node1 && node2 < other.node2)) 
            {
                return -1;
            }
            else 
            {
                return 1;
            }
        }

        /**
         * equals
         * @param o
         * @return boolean
         */
        public boolean equals(Object o) 
        {
            if (!(o instanceof Edge)) 
            {
                return false;
            }
            
            Edge other = (Edge) o;
            
            return (node1 == other.node1) && (node2 == other.node2);
        }

        /**
         * hashCode
         * @return hash
         */
        public int hashCode() 
        {
            return getNumNodes() * node1 + node2;
        }
        
    }
    
    /**
     * heuristic
     * implemets heuristic approach to the problem
     * @param graph
     * @return cal which is cal information
     */
	static Calculate heuristic(DGraph graph)
	{
		Calculate cal = new Calculate(graph.getNumNodes());
		int currentCity = 1;
		cal.getNext(currentCity);
		
		for (int i = 2; i <= graph.getNumNodes(); i++)
		{
			List<Integer> neighbors = graph.getNeighbors(currentCity);
			int closestCity = 0;
			double distance = Math.pow(10, 15);
			
			for (int neighbor : neighbors)
			{
				if (cal.remaining.contains(neighbor) && graph.getWeight(currentCity, neighbor) < distance) 
				{
					distance = graph.getWeight(currentCity, neighbor);
					closestCity = neighbor;
				}
			}
			
			cal.getNext(closestCity);
			currentCity = closestCity;
		}
		
		return cal;
	}

    /**
     * backTracking
     * implemets backTrack approach to the problem
     * @param graph
     * @return cal which is cal information
     */
	static Calculate backTracking(DGraph graph)
	{
		Calculate cal = new Calculate(graph.getNumNodes());
		cal.getNext(1);
		Calculate minTrip = new Calculate(graph.getNumNodes());
		backTrackingHelper(graph, cal, minTrip);
		
		return minTrip;
	}

	/**
	 * backTrackingHelper
	 * @param graph
	 * @param soFar
	 * @param previousMin
	 */
	private static void backTrackingHelper(DGraph graph, Calculate soFar,Calculate previousMin) 
	{
		if (soFar.possible(graph)) 
		{
			if (soFar.Cost(graph) < previousMin.Cost(graph)) 
			{
				previousMin.copyOtherIntoSelf(soFar);
				return;
			}
		}

		if (soFar.Cost(graph) < previousMin.Cost(graph))
		{
			for (int city : soFar.remaining())
			{
				soFar.getNext(city);
				backTrackingHelper(graph, soFar, previousMin);
				soFar.notLast();
			}
		}
	}

    /**
     * mine
     * implemets my approach to the problem
     * @param graph
     * @return cal which is cal information
     */
	static Calculate mine(DGraph graph)
	{
		Calculate cal = new Calculate(graph.getNumNodes());
		
		assert cal.remaining.contains(1);         
		cal.order.add(1);         
		cal.remaining.remove(1);     

		Calculate minTrip = new Calculate(graph.getNumNodes());
		mineHelper(graph, cal, minTrip);
		
		return minTrip;
	}

	/**
	 * mineHelper
	 * @param graph
	 * @param soFar
	 * @param previousMin
	 */
	private static void mineHelper(DGraph graph, Calculate soFar, Calculate previousMin)
	{
		if (soFar.possible(graph))
		{
			if (soFar.Cost(graph) < previousMin.Cost(graph))
			{
				previousMin.copyOtherIntoSelf(soFar);
				return;
			}
		}
		
		if (soFar.Cost(graph) < previousMin.Cost(graph))
		{
			for (int city : soFar.remaining())
			{
				soFar.getNext(city);
				if (soFar.Cost(graph) < previousMin.Cost(graph))
				{
					mineHelper(graph, soFar, previousMin);
					soFar.notLast();
				}
			}
		}
	}

	/**
	 * time
	 * calculates timing of each function
	 * prints time as required
	 * @param graph
	 */
	static void time(DGraph graph)
	{
		long startTime = 0;
		long endTime = 0;
		long duration = 0;
		
		startTime = System.nanoTime();
		Calculate heuristicTrip = heuristic(graph);
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;
		
		System.out.println("heuristic: cost = " + heuristicTrip.Cost(graph) + ", " + duration + " milliseconds");

		startTime = System.nanoTime();
		Calculate mineTrip = mine(graph);
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;
		
		System.out.println("mine: cost = " + mineTrip.Cost(graph) + ", " + duration + " milliseconds");

		startTime = System.nanoTime();
		Calculate backTrackingTrip = backTracking(graph);
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;
		
		System.out.println("backtrack: cost = " + backTrackingTrip.Cost(graph)+ ", " + duration + " milliseconds");
	}
}


/**
 * Calculate
 * @author adityagupta
 * Helper class which helps to Calculate Trip
 */
class Calculate 
{
	List<Integer> order;
	TreeSet<Integer> remaining;  

	/**
	 * Calculate
	 * Constructs a Trip
	 * @param numCities
	 */
	public Calculate(int numCities) 
	{         
		order = new ArrayList<>();         
		remaining = new TreeSet<>();         

		for (int i = 1; i <= numCities; i++) 
		{             
			remaining.add(i);         
		}     
	}

	/**
	 * copyOtherIntoSelf
	 * @param tripNow
	 */
	public void copyOtherIntoSelf(Calculate tripNow)
	{         
		remaining = new TreeSet<>(tripNow.remaining);         
		order = new ArrayList<>(tripNow.order);     
	}

	/**
	 * getNext
	 * @param next
	 */

	public void getNext(int next)
	{         
		assert remaining.contains(next);         
		order.add(next);         
		remaining.remove(next);     
	} 

	/**
	 * notLast
	 */
	public void notLast() 
	{         
		assert order.size() > 0;         
		int city = order.get(order.size() - 1);        
		order.remove(order.size() - 1);         
		remaining.add(city);     
	}     

	/**
	 * possible
	 * @param graph
	 * @return
	 */
	public boolean possible(DGraph graph)
	{        
		double cost = Cost(graph);         
		if (order.size() == graph.getNumNodes() && cost != Double.MAX_VALUE)
		{             
			return true;         
		} 
		else 
		{             
			return false;         
		}     
	}
	
	/**
	 * Cost
	 * @param graph
	 * @return
	 */
	public double Cost(DGraph graph)
	{         
		double cost = 0;     

		if (order.size() == 0) 
		{             
			return Double.MAX_VALUE;         
		}
		else if (order.size() == 1)
		{             
			return 0;         
		}         

		int prevCity = order.get(0);

		for (int i = 1; i < order.size(); i++)
		{             
			double distance = graph.getWeight(prevCity, order.get(i));             
			prevCity = order.get(i);             

			if (distance < 0)
			{                 
				cost = Double.MAX_VALUE;                 
				break;             
			} 
			else
			{                 
				cost += distance;             
			}         
		}

		if (remaining.isEmpty())
		{
			double goingHome = graph.getWeight(order.get(order.size() - 1), order.get(0)); 
			
			if (goingHome < 0 || cost == Double.MAX_VALUE) 
			{                 
				cost = Double.MAX_VALUE;             
			}
			else
			{                 
				cost = cost + goingHome;             
			}
		}         

		return cost;     

	}

	/**
	 * remaining
	 * @return
	 */
	public List<Integer> remaining()
	{         
		List<Integer> retval = new ArrayList<>();

		for (Integer city : remaining)
		{             
			retval.add(city);         
		}         

		return retval;     
	}

	/**
	 * toString
	 * @param graph
	 * @return
	 */
	public String toString(DGraph graph)
	{
		String str = "";         
		str += "cost = " + String.format("%.1f", Cost(graph));         
		str += ", visitOrder = " + order;         
		return str;    
	}   

	public String toString()
	{         
		String str = "";         
		str += "order = " + order;         
		str += ", remaining = " + remaining;         
		return str;     
	}      
}