/**
 * Garden class controls the grid
 * makes it easy to control plants
 */
public class Garden 
{
	private Plant[][] jardin;

	/**
	 * gets the specific plant
	 * @param rows
	 * @param cols
	 * @return
	 */
    public Plant getPlant(int rows, int cols) 
    {
        return jardin[rows][cols];
    }
    
    /**
     * resets the plant
     * @param rows
     * @param cols
     */
    public void resetPlant(int rows, int cols) 
    {
        jardin[rows][cols] = new Plant();
    }
    
    /**
     * Constructor class
     * @param rows
     * @param cols
     */
    public Garden(int rows, int cols) 
    {
        jardin = new Plant[rows][cols];

        for (int i = 0; i < rows; i++) 
        {
            for (int j = 0; j < cols; j++) 
                jardin[i][j] = new Plant();
        }
    }

    /**
     * stringComb as a helper function for toString
     * @param a
     * @param b
     * @return combi
     */
    public String stringComb(String a, String b) 
    {
        String combi = "";
        
        for (int k = 0; k < 5; k++) 
        {
            if (!a.substring(k, k + 1).equals(".")) 
            {
                combi += a.substring(k, k + 1);
            } 
            else if (!b.substring(k, k + 1).equals(".")) 
            {
                combi += b.substring(k, k + 1);
            } 
            else 
            {
                combi += ".";
            }
        }
        
        return combi;
    }
    
    /**
     * toString combines the grid as string
     * @return returnStr
     */
    public String toString() 
    {
        String returnStr = new String();

        for (Plant[] plantRow : jardin) 
        {
            for (int k = 0; k < 5; k++) 
            {
                for (Plant plant : plantRow) 
                {
                    String combi = ".....";
                    
                    if (plant.getFlower() != null) 
                        combi = stringComb(combi, plant.getFlower().getRowElement(k));
                    
                    if (plant.getTree() != null)
                        combi = stringComb(combi, plant.getTree().getRowElement(k));
                    
                    if (plant.getVegetable() != null)
                        combi = stringComb(combi, plant.getVegetable().getRowElement(k));
                    
                    returnStr += combi;
                }
                returnStr += "\n";
                
            }
        }
        return returnStr;
    }
}