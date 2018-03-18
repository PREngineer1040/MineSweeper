import java.util.Random;

public class Bombs 
{
	private static int mines; 
	private static int rows;
	private static int columns;
	boolean[][] mineArray;
	
	//This is the constructor for the Bombs object
	public Bombs(int mines, int columns, int rows)
		{
		this.mines=mines; 
		this.columns=columns;
		this.rows=rows; 
		}
	
	//This creates an array that will contain the location of the bombs, initially no space contains any bomb (all fields are false).
	//This method also uses the random class to create the mine field. 

	public void bombLocator()
		{ 
		this.mineArray = new boolean [rows][columns];
			int currentMines = 0; 
			Random randomGenerator = new Random(); 

			while (currentMines<15 )
			{
				int i = randomGenerator.nextInt(rows);
				int j = randomGenerator.nextInt(columns);
					if (currentMines<15)
					{
						mineArray[i][j]= randomGenerator.nextBoolean(); 
						if (mineArray[i][j]==true)
							{
								currentMines+=1; 
							}
					}
			}
		}
}
