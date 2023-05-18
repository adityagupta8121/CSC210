/**
 * Tree is used for trees in the simulator
 * Tree start in the bottom middle
 * Trees are cut
 */
public class Tree extends Plant
{
	private int growList;
	
	/*
	 * Creates new Tree
	 */
    public Tree(String crop) 
    {
        initialG = crop.substring(0, 1).toLowerCase();
        growList = 0;
        growRow = 4;
        growCol = 2;
    }

	/*
	 * Plants new Tree
	 */
    public void plant() 
    {
        super.setGrid(growRow, growCol, initialG);
    }

	/*
	 * Grows the tree
	 */
    private void firstGrow() 
    {
        super.grid[3][2] = initialG;
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
            for (int i = 3; i > 0; i--) 
            {
                for (int j = 3; j > 0; j--) 
                {
                    if (super.grid[i][j].equals(initialG) && super.grid[i - 1][j].equals(".")) 
                        super.grid[i - 1][j] = "&";
                }
            }
        }
        
        for (int i = 0; i < 5; i++) 
        {
            for (int j = 0; j < 5; j++) 
            {
                if (super.grid[i][j].equals("&"))
                    super.grid[i][j] = initialG;
            }
        }
    }
    
	/*
	 * Cuts the tree
	 */
    public void cut() 
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