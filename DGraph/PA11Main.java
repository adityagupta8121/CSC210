import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * PA11Main.java
 * @author adityagupta
 *
 * Main file
 */
public class PA11Main 
{
	public static void main(String[] args) 
	{
		DGraph graph = null;
		try 
		{
			graph = fileReader(args[0]);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		if (args[1].equals("HEURISTIC")) 
		{
			Calculate trip = DGraph.heuristic(graph);
			System.out.println(trip.toString(graph));
		} 
		else if (args[1].equals("BACKTRACK"))
		{
			Calculate minTrip = DGraph.backTracking(graph);
			System.out.println(minTrip.toString(graph));
		} 
		else if (args[1].equals("MINE")) 
		{
			Calculate mineTrip = DGraph.mine(graph);
			System.out.println(mineTrip.toString(graph));
		} 
		else if (args[1].equals("TIME")) 
		{
			DGraph.time(graph);
		}
	}

	/**
	 * fileReader
	 * Reads .mtx file line by line
	 * converts it into a graph
	 * @param fileName
	 * @return graph
	 * @throws IOException
	 */
	private static DGraph fileReader(String fileName) throws IOException 
	{
		BufferedReader br = null;
		try 
		{
			br = new BufferedReader(new FileReader(new File(fileName)));
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}

		String line = br.readLine();

		while (line.contains("%")) 
		{
			line = br.readLine();
		}

		String[] string = line.split("( )+");
		int nodes = (Integer.valueOf(string[0].trim())).intValue();
		DGraph graph = new DGraph(nodes);

		while (true) 
		{
			line = br.readLine();
			if (line == null) 
			{
				break;
			}
			string = line.split("( )+");
			graph.addEdge((Integer.valueOf(string[0].trim())).intValue(),(Integer.valueOf(string[1].trim())).intValue(),(Double.valueOf(string[2].trim())).doubleValue());
		}

		br.close();
		return graph;
	}
}