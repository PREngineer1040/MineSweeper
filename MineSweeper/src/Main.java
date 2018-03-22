import java.awt.TrayIcon.MessageType;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main {
	public static void main(String[] args) throws InterruptedException {

		
		int tryAgain = 2;
		while (tryAgain != 1) {
			JFrame myFrame = new JFrame("Bienve's Grid");
			myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //This had to be changed, because otherwise the program would keep running after exiting the window. 
			myFrame.setLocation(400, 150);
			myFrame.setSize(700,730);
			
			MyPanel myPanel = new MyPanel();
			myFrame.add(myPanel);

			MyMouseAdapter myMouseAdapter = new MyMouseAdapter();
			myFrame.addMouseListener(myMouseAdapter);
			myFrame.setVisible(true);
			myPanel.repaint();
			while (!myMouseAdapter.istheGameOver()) {
				Thread.sleep(500);
			}
			tryAgain = JOptionPane.showConfirmDialog(null, "Would you like to play again?", "Game Over", JOptionPane.YES_NO_OPTION);
			System.out.println(tryAgain + "");
			myPanel.repaint();
			myFrame.dispose();

		}
		System.exit(0);
	}
}
