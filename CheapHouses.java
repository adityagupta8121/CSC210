import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
//import java.awt.event.*;
//import java.awt.*;

/**
 * CheapHouses java program reads a csv file which contains data
 * of houses along with their coordinates and plots the data on graph
 * according to it, and the cut off price mentioned by the user
 * 
 */
public class CheapHouses {
	
	static JTextField textField;
	
	/**
	 * main function that only calls createAndShowGUI to run the program
	 * @param args Unused
	 * @return nothing
	 */
	public static void main(String[] args) {
	
		createAndShowGUI();      
		//data();
	}
	
	/**
	 * createAndShowGUI used to create GUI panel including
	 * graphics panel, buttons, textfields and labels
	 * @param unused
	 * @return GUI
	 */
	public static void createAndShowGUI()
	{
		JFrame mainFrame = new JFrame("Assignment 2");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(500,575);

		JPanel mainPanel = new JPanel(null);

		JPanel graphicsPanel  = new GPanel();
		graphicsPanel.setLocation(0, 0);
		graphicsPanel.setSize(500,500);
		graphicsPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		mainPanel.add(graphicsPanel);

		JPanel widgetsPanel  = new JPanel();
		widgetsPanel.setLocation(0, 500);
		widgetsPanel.setSize(500,60);

		JLabel fileLabel = new JLabel("File: ");
		widgetsPanel.add(fileLabel);
		
		JTextField fileName = new JTextField("houses.csv");
		fileName.setColumns(7);
		widgetsPanel.add(fileName);

		JLabel cutOff = new JLabel("Price: ");
		widgetsPanel.add(cutOff);

		JTextField priceField = new JTextField("250000");
		priceField.setColumns(7);
		widgetsPanel.add(priceField);

		JButton okButton = new JButton("Plot");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Plotting points..."); 	
    			Graphics g = graphicsPanel.getGraphics();
    			graphicsPanel.paint(g);
				g.setColor(Color.black);
				//HashMap and calculations to get data from the file
				//Calculating max/min of coordinates
    			HashMap<Double[], Integer> butMap = data(fileName.getText());
    			
				ArrayList<Double> l1 = new ArrayList<>();
				ArrayList<Double> l2 = new ArrayList<>();
    			for(Double[] i: butMap.keySet()) {
    				l1.add(i[0]);
    				l2.add(i[1]);
    			}

    			double maxl1 = Collections.max(l1);
    			double minl1 = Collections.min(l1);
    			double maxl2 = Collections.max(l2);
    			double minl2 = Collections.min(l2);    			 
    			
    			/*for(Double[] a: butMap.keySet()) {
    				
					double rex = (a[0] - minl1)/(maxl1 - minl1) * 400;
					int rex1 = Double.valueOf(rex).intValue();
					double rey = (a[1] - minl2)/(maxl2 - minl2) * 400;
					int rey1 = Double.valueOf(rey).intValue();
					g.fillOval(rey1, rex1, 4, 4);
			
    			}*/
    			
    			//using the formula x' = x - x.min / x.max - x.min
    			//to normalise the graph points
    			//pri is the cut off price, and a are coordinates
    			for (Map.Entry<Double[], Integer> entry : butMap.entrySet()) {
    				Double[] a = entry.getKey();
    				Integer pri = entry.getValue();
    				
    				if(pri <= (Integer.valueOf(priceField.getText()).intValue()))
    				{
						double rex = (a[0] - minl1)/(maxl1 - minl1) * 400;
						int rex1 = Double.valueOf(rex).intValue();
						double rey = (a[1] - minl2)/(maxl2 - minl2) * 400;
						int rey1 = Double.valueOf(rey).intValue();
						g.fillOval(rey1, rex1, 5, 5);
    				}
   			}
    			//System.out.println(Integer.valueOf(priceField.getText()).intValue());

			}
		}); 
		
		widgetsPanel.add(okButton);
		mainPanel.add(widgetsPanel);		
		mainFrame.add(mainPanel);
		mainFrame.setVisible(true);
	}
	
	private static class GPanel extends JPanel 
	{						
	    public void paintComponent(Graphics g) 
		{    
            int width = getSize().width;
            int height = getSize().height;
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, width, height);
			g.setColor(Color.BLACK);
            g.drawString("", 10, 200);     

        }
	}
	
	/**
	 * HashMap function takes the name of file, and stores the data in HashMap
	 * @param f
	 * @return yourMap which is a hashmap with all data
	 */
	public static HashMap<Double[], Integer> data(String f){
		
		HashMap<Double[], Integer> yourMap = new HashMap<>();
		//String f = "/Users/adityagupta/Desktop/Sep 21 CSC 210 A2/houses.csv";
		try(Scanner tfile = new Scanner (new File(f));)
		{
			String line = tfile.nextLine();;
			while (tfile.hasNext()) {
				line = tfile.nextLine();
				String[] arr = line.split(",");
				Integer price = Integer.valueOf(arr[9]).intValue();
				Double latit = Double.valueOf(arr[10]).doubleValue();
				Double longit = Double.valueOf(arr[11]).doubleValue();
				Double[] coord = {latit, longit};
				yourMap.put(coord, price);
			}

		}
		catch(FileNotFoundException e)
		{
			e.getMessage();
		}

		//for(Double[] a: yourMap.keySet()) {
		//	System.out.println(a[0]);
		//}
		return yourMap;
	}
}