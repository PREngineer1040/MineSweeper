import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MyMouseAdapter extends MouseAdapter {

	private boolean isGameOver = false;
	private boolean shouldWeEnd = false;
	private int clickCounter = 0;
	public static boolean firstClick=false; 
	public void mousePressed(MouseEvent e)
	{

		//	switch (e.getButton()) {
		if (e.getButton() == 1){		//Left mouse button
			Component c = e.getComponent();
			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null) {
					return;
				}
			}
			JFrame myFrame = (JFrame) c;
			MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);
			Insets myInsets = myFrame.getInsets();
			int x1 = myInsets.left;
			int y1 = myInsets.top;
			e.translatePoint(-x1, -y1);
			int x = e.getX();
			int y = e.getY();
			myPanel.x = x;
			myPanel.y = y;
			myPanel.mouseDownGridX = myPanel.getGridX(x, y);
			myPanel.mouseDownGridY = myPanel.getGridY(x, y);
			myPanel.repaint();
		}

		else if (e.getButton() == 3){		//Right mouse button
			Component c = e.getComponent();
			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null) {
					return;
				}
			}
			JFrame myFrame = (JFrame) c;
			MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);
			Insets myInsets = myFrame.getInsets();
			int x1 = myInsets.left;
			int y1 = myInsets.top;
			e.translatePoint(-x1, -y1);
			int x = e.getX();
			int y = e.getY();
			myPanel.x = x;
			myPanel.y = y;
			myPanel.mouseDownGridX = myPanel.getGridX(x, y);
			myPanel.mouseDownGridY = myPanel.getGridY(x, y);
			myPanel.repaint();

		}



	}
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == 1) {
			//Left mouse button
			Component c = e.getComponent();
			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null) {
					return;
				}
			}
			JFrame myFrame = (JFrame)c;
			MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);  //Can also loop among components to find MyPanel
			Insets myInsets = myFrame.getInsets();
			int x1 = myInsets.left;
			int y1 = myInsets.top;
			e.translatePoint(-x1, -y1);
			int x = e.getX();
			int y = e.getY();
			myPanel.x = x;
			myPanel.y = y;
			int gridX = myPanel.getGridX(x, y);
			int gridY = myPanel.getGridY(x, y);			
			if ((myPanel.mouseDownGridX == -1) || (myPanel.mouseDownGridY == -1)) {
				//Had pressed outside
				//Do nothing
			} else {
				if ((gridX == -1) || (gridY == -1)) {
					//Is releasing outside
					//Do nothing
				} else {
					if ((myPanel.mouseDownGridX != gridX) || (myPanel.mouseDownGridY != gridY)) {
						//Released the mouse button on a different cell where it was pressed
						//Do nothing
					} else {

						// If the user has just started and actually clicked on a bomb, it will prompt the user with a yes or no option
						/*if (myPanel.bomb.bombLocator(myPanel.mouseDownGridX,myPanel.mouseDownGridY)==true 
								&& !myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(Color.RED)
								&& firstClick ==false
								&& !myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(Color.BLUE))
						{
						*/
						
							/*
						{

							int choose = 0;
							choose = JOptionPane.showConfirmDialog(null, "This is actually a bomb. You're too early into the game. Would you like to end the game?", "Second Wind", JOptionPane.YES_NO_OPTION);
							if (choose == 0) {
								myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = Color.BLACK;
								myPanel.repaint();
								this.shouldWeEnd = true;
							}

							else if (choose == 1) {
								myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = Color.RED;
								myPanel.repaint();
							}
						}
						*/
						if (firstClick==false)
						{
								myPanel.bomb.mineField(myPanel.mouseDownGridX,myPanel.mouseDownGridY);
								firstClick=true;
								myPanel.repaint(); 
						}
						// If the user has clicked on a bomb, the game will paint it black and end.
						if (myPanel.bomb.bombLocator(myPanel.mouseDownGridX,myPanel.mouseDownGridY)==true 
								&& !myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(Color.RED)
								&& !myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(Color.BLUE)) {
							for (int i=0;i<myPanel.TOTAL_COLUMNS;i++)
							{
								for (int j=0;j< myPanel.TOTAL_ROWS ; j ++)
								{
									if (myPanel.bomb.bombLocator(i,j)==true)
									{
										myPanel.colorArray[i][j] = Color.BLACK;
									}
								}
							}
							myPanel.repaint();
							this.shouldWeEnd = true;
						}
						//If the user is not clicking on a previously marked red box, we enter the nested if.
						else if (!myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(Color.RED))
						{
							// If the user has clicked on a tile that was light gray and not a bomb, we can give the number 
							// adjacents and add to our clickCounter for possible box clicks.
							if (!myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(Color.WHITE)) {
								myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = Color.WHITE; 
								myPanel.bomb.adjacentDisplay[myPanel.mouseDownGridX][myPanel.mouseDownGridY]=true;
								this.clickCounter++; 
								myPanel.repaint(); 
							}
						}
					}
				}
			} 

			myPanel.repaint();
		}
		else if (e.getButton() == 3){

			//Left mouse button
			Component c = e.getComponent();
			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null) {
					return;
				}
			}
			JFrame myFrame = (JFrame)c;
			MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);  //Can also loop among components to find MyPanel
			Insets myInsets = myFrame.getInsets();
			int x1 = myInsets.left;
			int y1 = myInsets.top;
			e.translatePoint(-x1, -y1);
			int x = e.getX();
			int y = e.getY();
			myPanel.x = x;
			myPanel.y = y;
			int gridX = myPanel.getGridX(x, y);
			int gridY = myPanel.getGridY(x, y);			
			if ((myPanel.mouseDownGridX == -1) || (myPanel.mouseDownGridY == -1)) {
				//Had pressed outside
				//Do nothing
			} else {
				if ((gridX == -1) || (gridY == -1)) {
					//Is releasing outside
					//Do nothing
				} else {
					if ((myPanel.mouseDownGridX != gridX) || (myPanel.mouseDownGridY != gridY)) {
						//Released the mouse button on a different cell where it was pressed
						//Do nothing
					} else {

						// If right clicking on a light gray box, it will turn it red.
						if (myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(Color.LIGHT_GRAY))
						{
							myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = Color.RED;
							myPanel.repaint();
						}
						// If right clicking on an already red box, it will turn it back to light gray.
						else if (myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(Color.RED)){
							myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = Color.LIGHT_GRAY;
							myPanel.repaint();
						}
					}
				} 
				//myPanel.repaint();
			}
		}
	}

	// Check if the game has ended by either winning or by clicking on a mine.
	public boolean istheGameOver() {
		if (this.shouldWeEnd == true || this.clickCounter == 68)
		{
			this.isGameOver = true;
			this.firstClick=false; 
		}
		return isGameOver;
	}
}
