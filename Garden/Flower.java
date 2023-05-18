/**
 * Flower is used for flowers in the simulator
 * Flowers start in the middle
 * Flowers are picked
 */
public class Flower extends Plant
{
	private int growList;
	
	
	/*
	 * Creates new Flower
	 */
    public Flower(String crop) 
    {
        initialG = crop.substring(0, 1).toLowerCase();
        growList = 0;
        growRow = 2;
        growCol = 2;
    }

	/*
	 * Plants new Flower
	 */
    public void plant() 
    {
        super.setGrid(growRow, growCol, initialG);
    }

	/*
	 * Grows the Flowers step-wise
	 */
    private void fullGrow() 
    {
        super.grid[0][0] = "c";
        super.grid[0][4] = "c";
        super.grid[4][0] = "c";
        super.grid[4][4] = "c";
    }

    public void grow() 
    {
        growList++;
        
        if (growList >= 4 && super.grid[2][2].equals(initialG)) 
        {
            fullGrow();
        } 
        else 
        {
            for (int i = 1; i < 4; i++) 
            {
                for (int j = 1; j < 4; j++) 
                {
                    if (super.grid[i][j].equals(initialG)) 
                    {
                        super.grid[i - 1][j] = "c";
                        super.grid[i + 1][j] = "c";
                        super.grid[i][j - 1] = "c";
                        super.grid[i][j + 1] = "c";
                    }
                }
            }
        }
        
        for (int i = 0; i < 5; i++) 
        {
            for (int j = 0; j < 5; j++) 
            {
                if (super.grid[i][j].equals("c"))
                    super.grid[i][j] = initialG;
            }
        }
    }
    
	/*
	 * Pick the flower (or remove)
	 */
    public void pick() 
    {
        for (int i = 0; i < 5; i++) 
        {
            for (int j = 0; j < 5; j++) 
            {
                if (super.grid[i][j].equals(initialG)) 
                    super.grid[i][j] = ".";
            }
        }
    }
}
