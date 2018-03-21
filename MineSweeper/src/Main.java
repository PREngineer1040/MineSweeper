import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		
		//boolean isGameOver = false;
		
		JFrame myFrame = new JFrame("Bienve's Grid");
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //This had to be changed, because otherwise the program would keep running after exiting the window. 
		myFrame.setLocation(400, 150);
		myFrame.setSize(700,730);

		MyPanel myPanel = new MyPanel();
		myFrame.add(myPanel);

		MyMouseAdapter myMouseAdapter = new MyMouseAdapter();
		myFrame.addMouseListener(myMouseAdapter);
		myFrame.setVisible(true);

//		if (isGameOver == true)
//		{
//			myFrame.setVisible(false);
//			/*
//			 * Trying to figure out a while or a possible if.
//			 * If the game detects the gameover, boolean is switched to true and the window for the main minesweeper game is made invisible.
//			 * The user is prompted with two selections from a dialoug box. 
//			 * Choosing no causes the entire program to terminate and all windows to close.
//			 * Choosing yes restarts the game from scratch and closes the dialogue box while making the primary window visible again.
//			 */
//			
//		}
	}
}
