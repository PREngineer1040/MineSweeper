import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JFrame;

public class MyMouseAdapter extends MouseAdapter {

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

		else 
		{

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

						/*
						//Released the mouse button on the same cell where it was pressed
						if ((gridX == 0) || (gridY == 0)) {
							//On the left column and on the top row... do nothing
						} 
						else {


							//On the grid other than on the left column and on the top row:
							Color newColor = myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY];
							while(newColor.getRed() == myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].getRed() && 
									newColor.getBlue() == myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].getBlue()&& 
									newColor.getGreen() == myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].getGreen())
							{
								switch (generator.nextInt(5)) {
								case 0:
									newColor = Color.YELLOW;
									break;
								case 1:
									newColor = Color.MAGENTA; 
									break;
								case 2:
									newColor = Color.BLACK;
									break;
								case 3:
									newColor = new Color(0x964B00);   //Brown (from http://simple.wikipedia.org/wiki/List_of_colors)
									break;
								case 4:
									newColor = new Color(0xB57EDC);   //Lavender (from http://simple.wikipedia.org/wiki/List_of_colors)
									break;
								}
							}*/
						//	Color newColor = Color.WHITE;

						if (myPanel.bomb.bombLocator(myPanel.mouseDownGridX,myPanel.mouseDownGridY)==true)
						{
							myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = Color.BLACK;
							myPanel.repaint();
						}
						else 
						{
							myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = Color.WHITE;
							myPanel.repaint();
						}
					}


					//this is the code for the warmup exercise number 3
					/*
						if ((gridX == 0) && (gridY != 0) && (gridY!=MyPanel.extractRows()))
						{
							for (int i=1;i<MyPanel.extractRows();i++)
							{
								myPanel.mouseDownGridX = i;
								Color newColor = myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY];
								while(newColor.getRed() == myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].getRed() && 
										newColor.getBlue() == myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].getBlue()&& 
										newColor.getGreen() == myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].getGreen())
								{
									switch (generator.nextInt(5)) {
									case 0:
										newColor = Color.YELLOW;
										break;
									case 1:
										newColor = Color.MAGENTA; 
										break;
									case 2:
										newColor = Color.BLACK;
										break;
									case 3:
										newColor = new Color(0x964B00);   //Brown (from http://simple.wikipedia.org/wiki/List_of_colors)
										break;
									case 4:
										newColor = new Color(0xB57EDC);   //Lavender (from http://simple.wikipedia.org/wiki/List_of_colors)
										break;
									}
								}
								myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = newColor;
								myPanel.repaint();

							}

						}

						//this is the code for the warm up exercise 4

						if ((gridX != 0) && (gridY == 0))
						{
							for (int i=1;i<MyPanel.extractColumns();i++)
							{
								myPanel.mouseDownGridY = i;
								Color newColor = myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY];
								while(newColor.getRed() == myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].getRed() && 
										newColor.getBlue() == myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].getBlue()&& 
										newColor.getGreen() == myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].getGreen())
								{
									switch (generator.nextInt(5)) {
									case 0:
										newColor = Color.YELLOW;
										break;
									case 1:
										newColor = Color.MAGENTA; 
										break;
									case 2:
										newColor = Color.BLACK;
										break;
									case 3:
										newColor = new Color(0x964B00);   //Brown (from http://simple.wikipedia.org/wiki/List_of_colors)
										break;
									case 4:
										newColor = new Color(0xB57EDC);   //Lavender (from http://simple.wikipedia.org/wiki/List_of_colors)
										break;
									}
								}
								myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = newColor;
								myPanel.repaint();

							}

						}
						// Warmup Exercise 5
						/*
						if ((gridX == 0) && (gridY == 0))
						{
							for (int i = 1; i < 9; i++)
							{
								myPanel.mouseDownGridX = i;
								myPanel.mouseDownGridY = i;
								Color newColor = myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY];
								while(newColor.getRed() == myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].getRed() && 
										newColor.getBlue() == myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].getBlue()&& 
										newColor.getGreen() == myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].getGreen())
								{
									switch (generator.nextInt(5)) {
									case 0:
										newColor = Color.YELLOW;
										break;
									case 1:
										newColor = Color.MAGENTA; 
										break;
									case 2:
										newColor = Color.BLACK;
										break;
									case 3:
										newColor = new Color(0x964B00);   //Brown (from http://simple.wikipedia.org/wiki/List_of_colors)
										break;
									case 4:
										newColor = new Color(0xB57EDC);   //Lavender (from http://simple.wikipedia.org/wiki/List_of_colors)
										break;
									}
								}
								myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = newColor;
								myPanel.repaint();
							}
						}

						// Warmup Exercise 6

						if ((gridX == 0) && (gridY == 9))
						{
							int j=0;
							for (int i = 10; i > 0; i--)
							{
								j+=1; 

									myPanel.mouseDownGridX = i;
									myPanel.mouseDownGridY = j;
									Color newColor = myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY];
									while(newColor.getRed() == myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].getRed() && 
											newColor.getBlue() == myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].getBlue()&& 
											newColor.getGreen() == myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].getGreen())
									{
										switch (generator.nextInt(5)) {
										case 0:
											newColor = Color.YELLOW;
											break;
										case 1:
											newColor = Color.MAGENTA; 
											break;
										case 2:
											newColor = Color.BLACK;
											break;
										case 3:
											newColor = new Color(0x964B00);   //Brown (from http://simple.wikipedia.org/wiki/List_of_colors)
											break;
										case 4:
											newColor = new Color(0xB57EDC);   //Lavender (from http://simple.wikipedia.org/wiki/List_of_colors)
											break;
										}
									}
									myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = newColor;
									myPanel.repaint();

							}
						}

					 */
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

						/*
						//Released the mouse button on the same cell where it was pressed
						if ((gridX == 0) || (gridY == 0)) {
							//On the left column and on the top row... do nothing
						} 
						else {


							//On the grid other than on the left column and on the top row:
							Color newColor = myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY];
							while(newColor.getRed() == myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].getRed() && 
									newColor.getBlue() == myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].getBlue()&& 
									newColor.getGreen() == myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].getGreen())
							{
								switch (generator.nextInt(5)) {
								case 0:
									newColor = Color.YELLOW;
									break;
								case 1:
									newColor = Color.MAGENTA; 
									break;
								case 2:
									newColor = Color.BLACK;
									break;
								case 3:
									newColor = new Color(0x964B00);   //Brown (from http://simple.wikipedia.org/wiki/List_of_colors)
									break;
								case 4:
									newColor = new Color(0xB57EDC);   //Lavender (from http://simple.wikipedia.org/wiki/List_of_colors)
									break;+
								}
							}*/
						//	Color newColor = Color.WHITE;

						if (myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(Color.LIGHT_GRAY))
						{
							myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = Color.RED;
							myPanel.repaint();
						}

						else if (myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(Color.RED)){
							myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = Color.LIGHT_GRAY;
							myPanel.repaint();
						}




						//this is the code for the warmup exercise number 3
						/*
						if ((gridX == 0) && (gridY != 0) && (gridY!=MyPanel.extractRows()))
						{
							for (int i=1;i<MyPanel.extractRows();i++)
							{
								myPanel.mouseDownGridX = i;
								Color newColor = myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY];
								while(newColor.getRed() == myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].getRed() && 
										newColor.getBlue() == myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].getBlue()&& 
										newColor.getGreen() == myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].getGreen())
								{
									switch (generator.nextInt(5)) {
									case 0:
										newColor = Color.YELLOW;
										break;
									case 1:
										newColor = Color.MAGENTA; 
										break;
									case 2:
										newColor = Color.BLACK;
										break;
									case 3:
										newColor = new Color(0x964B00);   //Brown (from http://simple.wikipedia.org/wiki/List_of_colors)
										break;
									case 4:
										newColor = new Color(0xB57EDC);   //Lavender (from http://simple.wikipedia.org/wiki/List_of_colors)
										break;
									}
								}
								myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = newColor;
								myPanel.repaint();

							}

						}

						//this is the code for the warm up exercise 4

						if ((gridX != 0) && (gridY == 0))
						{
							for (int i=1;i<MyPanel.extractColumns();i++)
							{
								myPanel.mouseDownGridY = i;
								Color newColor = myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY];
								while(newColor.getRed() == myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].getRed() && 
										newColor.getBlue() == myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].getBlue()&& 
										newColor.getGreen() == myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].getGreen())
								{
									switch (generator.nextInt(5)) {
									case 0:
										newColor = Color.YELLOW;
										break;
									case 1:
										newColor = Color.MAGENTA; 
										break;
									case 2:
										newColor = Color.BLACK;
										break;
									case 3:
										newColor = new Color(0x964B00);   //Brown (from http://simple.wikipedia.org/wiki/List_of_colors)
										break;
									case 4:
										newColor = new Color(0xB57EDC);   //Lavender (from http://simple.wikipedia.org/wiki/List_of_colors)
										break;
									}
								}
								myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = newColor;
								myPanel.repaint();

							}

						}
						// Warmup Exercise 5
						/*
						if ((gridX == 0) && (gridY == 0))
						{
							for (int i = 1; i < 9; i++)
							{
								myPanel.mouseDownGridX = i;
								myPanel.mouseDownGridY = i;
								Color newColor = myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY];
								while(newColor.getRed() == myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].getRed() && 
										newColor.getBlue() == myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].getBlue()&& 
										newColor.getGreen() == myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].getGreen())
								{
									switch (generator.nextInt(5)) {
									case 0:
										newColor = Color.YELLOW;
										break;
									case 1:
										newColor = Color.MAGENTA; 
										break;
									case 2:
										newColor = Color.BLACK;
										break;
									case 3:
										newColor = new Color(0x964B00);   //Brown (from http://simple.wikipedia.org/wiki/List_of_colors)
										break;
									case 4:
										newColor = new Color(0xB57EDC);   //Lavender (from http://simple.wikipedia.org/wiki/List_of_colors)
										break;
									}
								}
								myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = newColor;
								myPanel.repaint();
							}
						}

						// Warmup Exercise 6

						if ((gridX == 0) && (gridY == 9))
						{
							int j=0;
							for (int i = 10; i > 0; i--)
							{
								j+=1; 

									myPanel.mouseDownGridX = i;
									myPanel.mouseDownGridY = j;
									Color newColor = myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY];
									while(newColor.getRed() == myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].getRed() && 
											newColor.getBlue() == myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].getBlue()&& 
											newColor.getGreen() == myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].getGreen())
									{
										switch (generator.nextInt(5)) {
										case 0:
											newColor = Color.YELLOW;
											break;
										case 1:
											newColor = Color.MAGENTA; 
											break;
										case 2:
											newColor = Color.BLACK;
											break;
										case 3:
											newColor = new Color(0x964B00);   //Brown (from http://simple.wikipedia.org/wiki/List_of_colors)
											break;
										case 4:
											newColor = new Color(0xB57EDC);   //Lavender (from http://simple.wikipedia.org/wiki/List_of_colors)
											break;
										}
									}
									myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = newColor;
									myPanel.repaint();

							}
						}

						 */
					}
				} 

				myPanel.repaint();



			}
		}
	}
}
