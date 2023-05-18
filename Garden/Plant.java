/**
 * Plant here is a 5x5 grid
 * Used to control all types of plants/crops
 * Plants: Vegetable, Flower and Trees
 *
 */
public class Plant 
{
	protected String initialG;
    protected String[][] grid;
    protected int growRow;
    protected int growCol;
    protected String crop;

	private Flower flower;
    private Tree tree;
    private Vegetable vegetable;
    
    //Enumerations to store FLowers, Trees & Vegetables
    public enum Flowers { Iris, Lily, Rose, Daisy, Tulip, Sunflower };
    public enum Trees { Oak, Willow, Banana, Coconut, Pine };
    public enum Vegetables { Garlic, Zucchini, Tomato, Yam, Lettuce };

    /**
     * Class constructor
     */
    public Plant() 
    {
        grid = new String[5][5];
        for (int x = 0; x < 5; x++) 
        {
            for (int y = 0; y < 5; y++) 
                setGrid(x, y, ".");
        }
    }
    
    /**
     * Plant gets the crop/plant names and calls the plant function
     * @param crop
     */
    public void plant(String crop) 
    {
        String cropName = getCrops(crop);
        
        switch (cropName) 
        {
        case "Flowers":
            flower = new Flower(crop);
            flower.plant();
            break;
            
        case "Trees":
            tree = new Tree(crop);
            tree.plant();
            break;
            
        case "Vegetables":
            vegetable = new Vegetable(crop);
            vegetable.plant();
            break;
            
        }
    }
    
    /**
     * getFlower
     * @return flower
     */
    public Flower getFlower() 
    {
        return flower;
    }

    /**
     * getTree
     * @return tree
     */
    public Tree getTree() 
    {
        return tree;
    }

    /**
     * getVegetable
     * @return vegetable
     */
    public Vegetable getVegetable() 
    {
        return vegetable;
    }
    
    /**
     * function to remove flower
     */
    public void pick() 
    {
        flower.pick();
    }

    /**
     * function to remove tree
     */
    public void cut() 
    {
        tree.cut();
    }

    /**
     * function to remove vegetable
     */
    public void harvest() 
    {
        vegetable.harvest();
    }
    
    /**
     * function to show grid
     * @return grid
     */
    public String[][] showGrid() 
    {
        return grid;
    }
    
    /**
     * @param crop
     * @return null
     */
    public String getCrops(String crop) 
    {
        crop = crop.toLowerCase();

        for (Flowers flower : Flowers.values()) 
        {
            if (crop.equalsIgnoreCase(flower.name()))
                return "Flowers";
        }

        for (Trees tree : Trees.values()) 
        {
            if (crop.equalsIgnoreCase(tree.name()))
                return "Trees";
        }

        for (Vegetables vegetable : Vegetables.values()) 
        {
            if (crop.equalsIgnoreCase(vegetable.name())) 
                return "Vegetables";
        }
        return null;
        
    }
    
    /**
     * @param x
     * @param y
     * @param firstLetter
     */
    public void setGrid(int x, int y, String firstLetter) 
    {
        this.grid[x][y] = firstLetter;
    }
    
    /**
     * read the row
     * @param row
     * @return
     */
    public String getRowElement(int row)
    {
        String returnStr = new String();
        
        for (String element : grid[row]) 
            returnStr += element;
        
        return returnStr;
    }
    
    /**
     * grows the plants all at once
     */
    public void grow() 
    {
        if (flower != null) 
            flower.grow();
        
        if (tree != null) 
            tree.grow();
        
        if (vegetable != null)
            vegetable.grow();
        
    }

    /**
     * grows the plants as mentioned
     * @param crop
     */
    public void grow(String crop) 
    {
        String cropName = getCrops(crop);
        
        switch (cropName) 
        {
        case "Flowers":
            flower.grow();
            break;
            
        case "Tree":
            tree.grow();
            break;
            
        case "Vegetables":
            vegetable.grow();
            break;
        }
    }

    /**
     * toString converts all rows to string
     * and returns table as a string
     * @return returnStr
     */
    public String toString() 
    {
        String returnStr = new String();
        for (int x = 0; x < 5; x++) 
        {
            for (int y = 0; y < 5; y++) 
            {
                returnStr += grid[x][y];
            }
            
            returnStr += "\n";
        }
        return returnStr;
    }

}