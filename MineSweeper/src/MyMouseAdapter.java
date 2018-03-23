import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JFrame;

public class MyMouseAdapter extends MouseAdapter {

	private boolean isGameOver = false;
	private boolean shouldWeEnd = false;
	private int clickCounter = 0;

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
							
						if (myPanel.bomb.bombLocator(myPanel.mouseDownGridX,myPanel.mouseDownGridY)==true && !myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(Color.RED))
						{
							myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = Color.BLACK;
							myPanel.repaint();
							this.shouldWeEnd = true;
						}
						else if (!myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(Color.RED))
						{
							myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = Color.WHITE; 
							myPanel.bomb.adjacentDisplay[myPanel.mouseDownGridX][myPanel.mouseDownGridY]=true;
							this.clickCounter++;
							//myPanel.repaint(); 
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

						if (myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(Color.LIGHT_GRAY))
						{
							myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = Color.RED;
							myPanel.repaint();
						}

						else if (myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(Color.RED)){
							myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = Color.LIGHT_GRAY;
							myPanel.repaint();
						}
					}
				} 
			
		

				myPanel.repaint();

			}
		}
	}

	public boolean istheGameOver() {
		if (this.shouldWeEnd == true || this.clickCounter == 68)
		{
			this.isGameOver = true;
		}
		return isGameOver;
	}
	}

