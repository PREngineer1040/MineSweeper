import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyPanel extends JPanel {
	private static final long serialVersionUID = 3426940946811133635L;
	private static final int GRID_X = 25;
	private static final int GRID_Y = 25;
	private static final int INNER_CELL_SIZE = 70;
	public static final int TOTAL_COLUMNS =9;
	public static final int TOTAL_ROWS = 9;   //Last row has only one cell
	public static final int mines=13; 
	public int x = -1;
	public int y = -1;
	public int mouseDownGridX = 0;
	public int mouseDownGridY = 0;
	public Color[][] colorArray = new Color[TOTAL_COLUMNS][TOTAL_ROWS];

	//Here we create an object of the Bombs class and create the mine Field. 
	//The mine field is then stored as an instance of the Bombs class. 
	//private Random generator = new Random();
	Bombs bomb=new Bombs(mines,TOTAL_COLUMNS,TOTAL_ROWS);
	
	

	public MyPanel() {   //This is the constructor... this code runs first to initialize
		if (INNER_CELL_SIZE + (new Random()).nextInt(1) < 1) {	//Use of "random" to prevent unwanted Eclipse warning
			throw new RuntimeException("INNER_CELL_SIZE must be positive!");
		}
		if (TOTAL_COLUMNS + (new Random()).nextInt(1) < 2) {	//Use of "random" to prevent unwanted Eclipse warning
			throw new RuntimeException("TOTAL_COLUMNS must be at least 2!");
		}
		if (TOTAL_ROWS + (new Random()).nextInt(1) < 3) {	//Use of "random" to prevent unwanted Eclipse warning
			throw new RuntimeException("TOTAL_ROWS must be at least 3!");
		}
		/*		for (int x = 0; x < TOTAL_COLUMNS; x++) {   //Top row
			colorArray[x][0] = Color.LIGHT_GRAY;
		}

		for (int y = 0; y < TOTAL_ROWS; y++) {   //Left column
			colorArray[0][y] = Color.LIGHT_GRAY;
		}
		 */
		for (int x = 0; x < TOTAL_COLUMNS; x++) {   //The rest of the grid
			for (int y = 0; y < TOTAL_ROWS; y++) {
				colorArray[x][y] = Color.LIGHT_GRAY;
			}
		}
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Font newFont = new Font("Comic Sans MS", Font.BOLD, 28);
		//Compute interior coordinates
		Insets myInsets = getInsets();
		int x1 = myInsets.left;
		int y1 = myInsets.top;
		int x2 = getWidth() - myInsets.right -1;
		int y2 = getHeight() - myInsets.bottom -1;
		int width = x2 - x1;
		int height = y2 - y1;

		//Paint the background
		g.setColor(Color.DARK_GRAY);
		g.fillRect(x1, y1, width + 1, height + 1);

		//Draw the grid 
		//By default, the grid will be 9x9 (see above: TOTAL_COLUMNS and TOTAL_ROWS) 
		g.setColor(Color.BLACK);
		for (int y = 0; y < TOTAL_ROWS ; y++) {
			g.drawLine(x1 + GRID_X, y1 + GRID_Y + (y * (INNER_CELL_SIZE + 1)), x1 + GRID_X + ((INNER_CELL_SIZE + 1) * TOTAL_COLUMNS), y1 + GRID_Y + (y * (INNER_CELL_SIZE + 1)));
		}
		for (int x = 0; x < TOTAL_COLUMNS; x++) {
			g.drawLine(x1 + GRID_X + (x * (INNER_CELL_SIZE + 1)), y1 + GRID_Y, x1 + GRID_X + (x * (INNER_CELL_SIZE + 1)), y1 + GRID_Y + ((INNER_CELL_SIZE + 1) * (TOTAL_ROWS)));
		}


		//Paint cell colors
		//Here we draw the adjacent number of bombs of a cell in the cell. 
		for (int x = 0; x < TOTAL_COLUMNS; x++) {
			for (int y = 0; y < TOTAL_ROWS; y++) {
				Color c = colorArray[x][y];
				g.setColor(c);
				g.fillRect(x1 + GRID_X + (x * (INNER_CELL_SIZE + 1)) + 1, y1 + GRID_Y + (y * (INNER_CELL_SIZE + 1)) + 1, INNER_CELL_SIZE, INNER_CELL_SIZE);
				if (MyMouseAdapter.firstClick==true)
				{
					if (bomb.adjacentDisplay[x][y]==true)
					{
					g.setColor(Color.BLUE);
					g.setFont(newFont);
					g.drawString(bomb.adjacentMineString[x][y], INNER_CELL_SIZE*(x+1)-15, (y+1)*INNER_CELL_SIZE);
					}
				}
			}	
		}
		


	}

	public int getGridX(int x, int y) {
		Insets myInsets = getInsets();
		int x1 = myInsets.left;
		int y1 = myInsets.top;
		x = x - x1 - GRID_X;
		y = y - y1 - GRID_Y;
		if (x < 0) {   //To the left of the grid
			return -1;
		}
		if (y < 0) {   //Above the grid
			return -1;
		}
		if ((x % (INNER_CELL_SIZE + 1) == 0) || (y % (INNER_CELL_SIZE + 1) == 0)) {   //Coordinate is at an edge; not inside a cell
			return -1;
		}
		x = x / (INNER_CELL_SIZE +1);
		y = y / (INNER_CELL_SIZE +1);
		/*if (x == 0 && y == TOTAL_ROWS ) {    //The lower left extra cell
			return x;

//Here i Had to substract 1 from total rows and total columns to prevent an out of bounds bug that we would get otherwise 
		}*/
		if (x < 0 || x > TOTAL_COLUMNS-1|| y < 0 || y > TOTAL_ROWS-1 ) {   //Outside the rest of the grid
			return -1;
		}

		return x;
	} 
	public int getGridY(int x, int y) {
		Insets myInsets = getInsets();
		int x1 = myInsets.left;
		int y1 = myInsets.top;
		x = x - x1 - GRID_X;
		y = y - y1 - GRID_Y;
		if (x < 0) {   //To the left of the grid
			return -1;
		}
		if (y < 0) {   //Above the grid
			return -1;
		}
		if ((x % (INNER_CELL_SIZE + 1) == 0) || (y % (INNER_CELL_SIZE + 1) == 0)) {   //Coordinate is at an edge; not inside a cell
			return -1;
		}
		x = x / (INNER_CELL_SIZE +1);
		y = y / (INNER_CELL_SIZE +1);
		/*if (x == 0 && y == TOTAL_ROWS - 1) {    //The lower left extra cell
			return y;
		}
		 */
		//Here i Had to substract 1 from total rows and total columns to prevent an out of bounds bug that we would get otherwise 
		if (x < 0 || x > TOTAL_COLUMNS-1 || y < 0 || y > TOTAL_ROWS-1 ) {   //Outside the rest of the grid
			return -1;
		}
		return y;
	}



}