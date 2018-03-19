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
		boolean [][] mineArray;
		mineArray= new boolean[rows][columns]; 
		this.mineArray= mineArray; 
		}
	
	//This creates an array that will contain the location of the bombs, initially no space contains any bomb (all fields are false).
	//This method also uses the random class to create the mine field. 

	public void mineField()
		{ 
			int currentMines = 0; 
			Random randomGenerator = new Random(); 
			boolean[][] mineArray; 
			mineArray = new boolean[rows][columns];
			
			while (currentMines<mines )
			{
				int i = randomGenerator.nextInt(rows);
				int j = randomGenerator.nextInt(columns);
				if(mineArray[i][j]==false)
				{
					mineArray[i][j]= randomGenerator.nextBoolean(); 
					if (mineArray[i][j]==true)
						{
							currentMines+=1; 
						}
				}
			}
			this.mineArray=mineArray;
		}
	
	//This method returns the value of the mineArray. It can be either true or false. 
	public boolean bombLocator(int i, int j)
	{
		return mineArray[i][j]; 
	}
}


