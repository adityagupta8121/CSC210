/**
 * Vegetable is used for vegetables in the simulator
 * Vegetables start in the top middle
 * Vegetables are harvested
 */
public class Vegetable extends Plant
{
    private int growList;
    
	/*
	 * Creates new Vegetable
	 */
    public Vegetable(String crop) 
    {
        initialG = crop.substring(0, 1).toLowerCase();
        growList = 0;
        growRow = 0;
        growCol = 2;
    }
    
	/*
	 * Plant new Vegetable
	 */
    public void plant() 
    {
        super.setGrid(growRow, growCol, initialG);
    }
    
	/*
	 * Grow new Vegetable
	 */
    private void firstGrow() 
    {
        super.grid[1][2] = initialG;
    }
    
    public void grow() 
    {
        growList++;
        
        if (growList == 1) 
        {
            firstGrow();
        } 
        else 
        {
            for (int x = 1; x < 4; x++) 
            {
                for (int y = 1; y < 4; y++) 
                {
                    if (super.grid[x][y].equals(initialG) && super.grid[x + 1][y].equals("."))
                        super.grid[x + 1][y] = "c";
                }
            }
        }
        
        for (int x = 0; x < 5; x++) 
        {
            for (int y = 0; y < 5; y++) 
            {
                if (super.grid[x][y].equals("c"))
                    super.grid[x][y] = initialG;
            }
        }
    }
    
	/*
	 * Harvests Vegetable
	 */
    public void harvest() 
    {
        for (int x = 0; x < 5; x++) 
        {
            for (int y = 0; y < 5; y++) 
            {
                if (super.grid[x][y].equals(initialG))
                    super.grid[x][y] = ".";
            }
        }
    }
}