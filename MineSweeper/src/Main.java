import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		JFrame myFrame = new JFrame("Bienve's Grid");
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //This had to be changed, because otherwise the program would keep running after exiting the window. 
		myFrame.setLocation(400, 150);
		myFrame.setSize(700,730);

		MyPanel myPanel = new MyPanel();
		myFrame.add(myPanel);

		MyMouseAdapter myMouseAdapter = new MyMouseAdapter();
		myFrame.addMouseListener(myMouseAdapter);
		myFrame.setVisible(true);

		
	}
}
