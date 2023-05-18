import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * PA3 Main is a garden simulator
 * which lets the user perform all options related to planting
 * picking, harvesting and cutting 
 */
public class PA3Main 
{
	/**
	 * Reads the file line by line and extracts commands
	 * to run the simulator
	 * @param fileInp
	 * @param rows
	 * @param cols
	 */
    public static void fileRead(Scanner fileInp, int rows, int cols) 
    {
        fileInp.nextLine();
        Garden jardin = new Garden(rows, cols);
        
        while (fileInp.hasNextLine()) 
        {
            String input = fileInp.nextLine();
            switch (input.substring(0, 3).toLowerCase()) 
            {
            case "pri":
                System.out.println("> PRINT");
                System.out.println(jardin);
                break;
                
            case "pla":
                plantMain(input, jardin);
                break;
                
            case "gro":
                growMain(input, jardin, rows, cols);
                break;
                
            case "har":
                harvestMain(input, jardin, rows, cols);
                break;
                
            case "pic":
                pickMain(input, jardin, rows, cols);
                break;
                
            case "cut":
                cutMain(input, jardin, rows, cols);
                break;
            }
        }
    }
    
    /**
     * Method to get number of rows
     * @param coor
     * @return
     */
    public static int getRow(String coor) 
    {
        coor = coor.split(",")[0];
        if (coor.length() == 2)
        {
            return Integer.parseInt(coor.substring(1, 2));
        } 
        else
        {
            return Integer.parseInt(coor.substring(1, 3));
        }
    }

    /**
     * Method to get number of columns
     * @param coor
     * @return
     */
    public static int getCol(String coor) 
    {
        coor = coor.split(",")[1];
        if (coor.length() == 2) 
        {
            return Integer.parseInt(coor.substring(0, 1));
        } 
        else
        {
            return Integer.parseInt(coor.substring(0, 2));
        }
    }
    
    /**
     * Used to plant a crop/plant on the rows/cols mentioned
     * @param input
     * @param jardin
     */
    public static void plantMain(String input, Garden jardin) 
    {
        String coordinate = input.split(" ")[1];
        String crop = input.split(" ")[2];
        int row = getRow(coordinate);
        int col = getCol(coordinate);
        
        Plant givenPlant = jardin.getPlant(row, col);
        givenPlant.plant(crop);
    }
    
    /**
     * Check if it's possible to grow
     * @param coordinate
     * @param jardin
     * @param time
     * @param rows
     * @param cols
     * @param input
     */
    public static void growCoord(String coordinate, Garden jardin, int time, int rows, int cols, String input) 
    {
        int row = getRow(coordinate);
        int col = getCol(coordinate);
        
        System.out.println("> " + input.toUpperCase());
        
        if (row < rows && row >= 0 && col < cols && col >= 0) 
        {
            Plant givenPlant = jardin.getPlant(row, col);
            for (int i = 0; i < time; i++)
            {
                givenPlant.grow();
            }
        } 
        else 
        {
            System.out.println(" ");
            System.out.println("Can't grow there.");
            System.out.println(" ");
        }
    }
    
    /**
     * @param coordinate
     * @param rows
     * @param cols
     * @param jardin
     * @param time
     * @param input
     */
    public static void growCrop(String coordinate, int rows, int cols, Garden jardin, int time, String input) 
    {
        String crop = coordinate;
        String initialLet = crop.substring(0, 1).toLowerCase();
        
        System.out.println("> " + input.split(" ")[0].toUpperCase() + " " + input.split(" ")[1] + " " + input.split(" ")[2]);
        
        for (int i = 0; i < rows; i++) 
        {
            for (int j = 0; j < cols; j++) 
            {
                Plant givenPlant = jardin.getPlant(i, j);
                String cropName = givenPlant.getCrops(crop);
                
                switch (cropName) 
                {
                case "Flowers":
                    Flower flowerTmp = givenPlant.getFlower();
                    if (flowerTmp != null && flowerTmp.initialG.equals(initialLet)) 
                    {
                        for (int k = 0; k < time; k++) 
                        {
                            flowerTmp.grow();
                        }
                    }
                    
                case "Trees":
                    Tree treeTmp = givenPlant.getTree();
                    if (treeTmp != null && treeTmp.initialG.equals(initialLet)) 
                    {
                        for (int k = 0; k < time; k++) 
                        {
                            treeTmp.grow();
                        }
                    }
                    
                case "Vegetables":
                    Vegetable vegTmp = givenPlant.getVegetable();
                    if (vegTmp != null && vegTmp.initialG.equals(initialLet)) 
                    {
                        for (int k = 0; k < time; k++) 
                        {
                            vegTmp.grow();
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Grow type of plant mentioned
     * @param coordinate
     * @param rows
     * @param cols
     * @param jardin
     * @param time
     * @param input
     */
    public static void growType(String coordinate, int rows, int cols, Garden jardin, int time, String input) 
    {
        String cropName = coordinate;
        
        System.out.println("> " + input.split(" ")[0].toUpperCase() + " " + input.split(" ")[1] + " " + input.split(" ")[2]);
        
        for (int i = 0; i < rows; i++) 
        {
            for (int j = 0; j < cols; j++) 
            {
                Plant givenPlant = jardin.getPlant(i, j);
                if (cropName.equals("flower")) 
                {
                    Flower flowerTmp = givenPlant.getFlower();
                    if (flowerTmp != null) 
                    {
                        for (int k = 0; k < time; k++) 
                        {
                            flowerTmp.grow();
                        }
                    }
                } 
                else if (cropName.equals("tree")) 
                {
                    Tree treeTmp = givenPlant.getTree();
                    if (treeTmp != null) 
                    {
                        for (int k = 0; k < time; k++) 
                        {
                            treeTmp.grow();
                        }
                    }
                } 
                else if (cropName.equals("vegetable")) 
                {
                    Vegetable vegTmp = givenPlant.getVegetable();
                    if (vegTmp != null) 
                    {
                        for (int k = 0; k < time; k++) 
                        {
                            vegTmp.grow();
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Grow all plants given
     * @param jardin
     * @param rows
     * @param cols
     * @param time
     */
    public static void growAll(Garden jardin, int rows, int cols, int time) 
    {
        for (int i = 0; i < rows; i++) 
        {
            for (int j = 0; j < cols; j++) 
            {
                Plant givenPlant = jardin.getPlant(i, j);
                for (int k = 0; k < time; k++) 
                {
                    givenPlant.grow();
                }
            }
        }
    }
    
    /**
     * @param input
     * @param jardin
     * @param rows
     * @param cols
     */
    public static void growMain(String input, Garden jardin,  int rows, int cols) 
    {
        int time = Integer.parseInt(input.split(" ")[1]);
        
        if (input.split(" ").length == 2) 
        {
            System.out.println("> " + input.toUpperCase());
            growAll(jardin, rows, cols, time);
        } 
        else 
        {
            String coordinate = input.split(" ")[2];
            if (coordinate.substring(0, 1).equals("(")) 
            {
                growCoord(coordinate, jardin, time, rows, cols, input);
            } 
            else if (coordinate.equals("flower") || coordinate.equals("tree") || coordinate.contentEquals("vegetable")) 
            {
                growType(coordinate, rows, cols, jardin, time, input);
            } 
            else 
            {
                growCrop(coordinate, rows, cols, jardin, time, input);
            }
        }
    }    

    /**
     * cut all trees
     * @param jardin
     * @param rows
     * @param cols
     */
    private static void cutEvery(Garden jardin, int rows, int cols)
    {
        for (int i = 0; i < rows; i++) 
        {
            for (int j = 0; j < cols; j++) 
            {
                Plant givenPlant = jardin.getPlant(i, j);
                if (givenPlant.getTree() != null)
                    givenPlant.cut();
            }
        }
    }

    /**
     * cut tree on given coordinate
     * @param coordinate
     * @param jardin
     * @param input
     */
    private static void cutCoord(String coordinate, Garden jardin, String input) 
    {
        int row = getRow(coordinate);
        int col = getCol(coordinate);
        
        System.out.println("> " + input.split(" ")[0].toUpperCase() + " " + input.split(" ")[1]);

        Plant givenPlant = jardin.getPlant(row, col);
        if (givenPlant.getTree() == null) 
        {
            System.out.println(" ");
            System.out.println("Can't cut there.");
            System.out.println(" ");
        } 
        else 
        {
            givenPlant.cut();
        }
    }
    
    /**
     * cut specified crop
     * @param crop
     * @param rows
     * @param cols
     * @param jardin
     * @param input
     */
    private static void cutCrop(String crop, int rows, int cols, Garden jardin, String input) 
    {
        String initialLet = crop.substring(0, 1);
        
        System.out.println("> " + input.split(" ")[0].toUpperCase() + " " + input.split(" ")[1]);
         
        for (int i = 0; i < rows; i++) 
        {
            for (int j = 0; j < cols; j++) 
            {
                Plant givenPlant = jardin.getPlant(i, j);
                Tree treeTmp = givenPlant.getTree();
                if (treeTmp != null && treeTmp.initialG.equals(initialLet)) 
                {
                    treeTmp.cut();
                }
            }
        }
    }
    
    /**
     * cut main fucntion
     * @param input
     * @param jardin
     * @param rows
     * @param cols
     */
    public static void cutMain(String input, Garden jardin, int rows, int cols) 
    {
        if (input.split(" ").length == 1) 
        {
            System.out.println("> " + input.toUpperCase());
            cutEvery(jardin, rows, cols);
        } 
        else 
        {
            String coordinate = input.split(" ")[1];
            if (coordinate.substring(0, 1).equals("(")) 
            {
                cutCoord(coordinate, jardin, input);
            } 
            else 
            {
            	String crop = coordinate;
                cutCrop(crop, rows, cols, jardin, input);
            }
        }
    }
    
    /**
     * pick all flowers
     * @param jardin
     * @param rows
     * @param cols
     */
    private static void pickAll(Garden jardin, int rows, int cols)
    {
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++) 
            {
                Plant givenPlant = jardin.getPlant(i, j);
                if (givenPlant.getFlower() != null) 
                {
                    givenPlant.pick();
                }
            }
        }
    }

    /**
     * Pick flower on coordinate
     * @param coordinate
     * @param jardin
     * @param input
     */
    private static void pickCoord(String coordinate, Garden jardin, String input) 
    {
        int row = getRow(coordinate);
        int col = getCol(coordinate);
        
        System.out.println("> " + input.split(" ")[0].toUpperCase() + " " + input.split(" ")[1]);
        
        Plant givenPlant = jardin.getPlant(row, col);
        if (givenPlant.getFlower() == null) 
        {
            System.out.println(" ");
            System.out.println("Can't pick there.");
            System.out.println(" ");
        } 
        else 
        {
            givenPlant.pick();
        }
    }
    
    /**
     * Pick specific flower
     * @param crop
     * @param rows
     * @param cols
     * @param jardin
     * @param input
     */
    private static void pickCrop(String crop, int rows, int cols, Garden jardin, String input) 
    {
        String initialLet = crop.substring(0, 1);
        
        System.out.println("> " + input.split(" ")[0].toUpperCase() + " " + input.split(" ")[1]);
        
        for (int i = 0; i < rows; i++) 
        {
            for (int j = 0; j < cols; j++) 
            {
                Plant givenPlant = jardin.getPlant(i, j);
                Flower flowerTmp = givenPlant.getFlower();
                if (flowerTmp != null && flowerTmp.initialG.equals(initialLet))
                {
                    flowerTmp.pick();
                }
            }
        }
    }

    /**
     * Pick main function
     * @param input
     * @param jardin
     * @param rows
     * @param cols
     */
    public static void pickMain(String input, Garden jardin, int rows, int cols) 
    {
        if (input.split(" ").length == 1) 
        {
            System.out.println("> " + input.toUpperCase());
            pickAll(jardin, rows, cols);
        } 
        else
        {
            String coordinate = input.split(" ")[1];
            if (coordinate.substring(0, 1).equals("(")) 
            {
                pickCoord(coordinate, jardin, input);
            } 
            else 
            {
                String crop = coordinate;
                pickCrop(crop, rows, cols, jardin, input);
            }
        }
    }
    
    /**
     * Harvest all crops
     * @param jardin
     * @param rows
     * @param cols
     */
    private static void harvestAll(Garden jardin, int rows, int cols) 
    {
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++) 
            {
                Plant givenPlant = jardin.getPlant(i, j);
                if (givenPlant.getVegetable() != null)
                {
                    givenPlant.harvest();
                }
            }
        }
    }

    /**
     * Harvest at given coord
     * @param coordinate
     * @param jardin
     * @param input
     */
    private static void harvestCoord(String coordinate, Garden jardin, String input) 
    {
        int row = getRow(coordinate);
        int col = getCol(coordinate);
        
        System.out.println("> " + input.split(" ")[0].toUpperCase() + " " + input.split(" ")[1]);
        
        Plant givenPlant = jardin.getPlant(row, col);
        
        if (givenPlant.getVegetable() == null) 
        {
            System.out.println(" ");
            System.out.println("Can't harvest there.");
            System.out.println(" ");
        } 
        else 
        {
            givenPlant.harvest();
        }
    }

    /**
     * Harvest mentioned crop
     * @param crop
     * @param rows
     * @param cols
     * @param jardin
     * @param input
     */
    private static void harvestCrop(String crop, int rows, int cols, Garden jardin, String input) 
    {
        String initialLet = crop.substring(0, 1);
        
        System.out.println("> " + input.split(" ")[0].toUpperCase() + " " + input.split(" ")[1]);
        
        for (int i = 0; i < rows; i++) 
        {
            for (int j = 0; j < cols; j++) 
            {
                Plant givenPlant = jardin.getPlant(i, j);
                Vegetable vegTmp = givenPlant.getVegetable();
                if (vegTmp != null && vegTmp.initialG.equals(initialLet))
                {
                    vegTmp.harvest();
                }
            }
        }
    }

    /**
     * Harvest main fucntion
     * @param input
     * @param jardin
     * @param rows
     * @param cols
     */
    public static void harvestMain(String input, Garden jardin, int rows, int cols) 
    {
        if (input.split(" ").length == 1) 
        {
            System.out.println("> " + input.toUpperCase());
            harvestAll(jardin, rows, cols);
        }
        else 
        {
            String coordinate = input.split(" ")[1];
            if (coordinate.substring(0, 1).equals("(")) 
            {
                harvestCoord(coordinate, jardin, input);
            } 
            else 
            {
                String crop = coordinate;
                harvestCrop(crop, rows, cols, jardin, input);
            }
        }
    }

    public static void main(String args[]) 
    {
        Scanner fileInp = null;
        
        try 
        {
            fileInp = new Scanner(new File(args[0]));   
        }
        catch (FileNotFoundException f) 
        {
            f.printStackTrace();
        }

        int rows = Integer.parseInt(fileInp.nextLine().split(": ")[1]);
        int cols = Integer.parseInt(fileInp.nextLine().split(": ")[1]);
        
        if (rows >= 15 || cols >= 15) 
        {
            System.out.println("Too many plot columns.");
        }
        else
        {
            fileRead(fileInp, rows, cols);
        }
    }    
    
}