import java.util.Random;

public class Bombs 
{
	private static int mines; 
	private static int rows;
	private static int columns;
	boolean[][] mineArray;
	String[][] adjacentMineString; 
	boolean[][]adjacentDisplay; 


	//This is the constructor for the Bombs object
	public Bombs(int mines, int columns, int rows)
	{
		this.mines=mines; 
		this.columns=columns;
		this.rows=rows; 
		boolean [][] mineArray;
		mineArray= new boolean[columns][rows]; 
		this.mineArray= mineArray;

	}

	//This creates an array that will contain the location of the bombs, initially no space contains any bomb (all fields are false).
	//This method also uses the random class to create the mine field. 

	public void mineField(int x, int y)
	{ 
		int currentMines = 0; 
		Random randomGenerator = new Random(); 
		boolean[][] mineArray; 
		mineArray = new boolean[this.columns][this.rows];

		while (currentMines<mines )
		{
			int i = randomGenerator.nextInt(columns);
			int j = randomGenerator.nextInt(rows);
			if (i!=x || j!=y)
			{
				if(mineArray[i][j]==false)
				{
					mineArray[i][j]=true; 
					currentMines+=1; 
				}
			}
		}
		this.mineArray=mineArray;
		revealAdjacent(); 
		adjacentDisplayer(); 
	}

	//This method returns the value of the mineArray. It can be either true or false. 
	public boolean bombLocator(int i, int j)
	{
			return mineArray[i][j]; 
	}

	//This method finds the adjacent mines for every tile. It stores the adjacent mines location as a string Array. 
	public void revealAdjacent()
	{
		String [][] adjacent = new String[this.columns][this.rows]; 
		int counter=0;
		for (int i=0;i<columns;i++)
		{
			for (int j=0;j<rows;j++)
			{				
				for (int m=-1;m<=1;m++)
				{
					for (int n=-1;n<=1;n++)
					{
						if ((m+i)>=0 && (n+j)>=0 && (m+i)<columns && (n+j)<rows)
						{
							if (n!=0 || m!=0)
							{
								if (mineArray[(i+m)][(j+n)]==true)
								{
									counter+=1; 
								}
							}
						}
					}
				}
				adjacent[i][j]=Integer.toString(counter);
				counter=0;
			}
		}
		this.adjacentMineString = adjacent; 
	}
	
	public void adjacentDisplayer()
	{
		boolean[][] adjacentDisplay; 
		adjacentDisplay = new boolean[columns][rows]; 
		for (int i=0;i<columns;i++)
		{
			for (int j=0;j<rows;j++)
			{
				adjacentDisplay[i][j]=false; 
			}
		}
		this.adjacentDisplay=adjacentDisplay; 
	}
}


