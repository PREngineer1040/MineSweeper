import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main {

	public static AudioInputStream audioStream;
	public static Clip audioClip;
	public static File audioFile;	


	public static void main(String[] args) throws InterruptedException, IOException {


		chooseMusic();
		// Invokes method to allow user to pick their preferred song.


		// While that will run the game as long as the game has not been concluded.
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
			myPanel.repaint();
			myFrame.dispose();

		}
		System.exit(0);
	}


	// Method that starts music
	public static void startMusic() throws InterruptedException, IOException {
		AudioFormat format = audioStream.getFormat();
		DataLine.Info info = new DataLine.Info(Clip.class, format);
		try {
			Clip audioClip = (Clip) AudioSystem.getLine(info);
			audioClip.open(audioStream);
			audioClip.start();
			audioClip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (LineUnavailableException e) {

			e.printStackTrace();
		}

	}

	
	// Method that allows user to pick their background music.
	public static void chooseMusic() throws InterruptedException, IOException {
		int choose = 0;
		choose = JOptionPane.showConfirmDialog(null, "Would you like to experience hell?", "Choose your music", JOptionPane.YES_NO_OPTION);

		if (choose == 0) {
			audioFile = new File("music/HellishSong.wav");
			try {
				audioStream = AudioSystem.getAudioInputStream(audioFile);
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			}

		}

		else if (choose == 1) {
			audioFile = new File("music/ChillSong.wav");
			try {
				audioStream = AudioSystem.getAudioInputStream(audioFile);
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			}
		}

		startMusic();
	}
}
