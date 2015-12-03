package fractionGame;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class GameGUI extends JPanel{
	private ArrayList<Scene> scenes;
	Player mainPlayer;
	
	private Image textBox;
	private Image coinBox;
	private Image buttonBox;
	private MediaTracker tracker;
	
	//testing
	private Scene test1;
	private Scene test2;
	private ImageIcon myImage;
	int sceneNum = 1;
	
	public GameGUI(){
		mainPlayer = new Player();
		
		test1 = new Scene("/graphics/Backgrounds/Beach.png", "Test", 1, "/graphics/Characters/Mermaid.png", 1);
		test2 = new Scene("/graphics/Backgrounds/Cave.png", "Test", 2, "/graphics/Characters/Dwarf.png", 1);
		
		//JButton button = new JButton();
		//add(button);
		//button.addActionListener(new ChangeSceneListener());
		myImage = new ImageIcon(getImage("/graphics/System/Button.png"));
		
	//	Image original = myImage.getImage();
	//	button.setIcon(myImage);
		this.addMouseListener(new ChangeSceneListener());
		
		tracker = new MediaTracker(this);
		textBox = getImage("/graphics/System/TextBox.png");
		coinBox = getImage("/graphics/System/Coins.png");
		buttonBox = getImage("/graphics/System/Button.png");
		tracker.addImage(textBox, 0);
		tracker.addImage(coinBox, 0);
		tracker.addImage(buttonBox, 0);
		try {
			tracker.waitForID(0);
		} catch (InterruptedException e) {
			return;
		}
		
	}
	
	public void changeScene(Player mainPlayer){
		mainPlayer.setProgress(mainPlayer.getProgress() + 1);
		
		if (sceneNum == 1)
			sceneNum = 2;
		else
			sceneNum = 1;
		repaint();
	}
	

	
	public void paintComponent(Graphics g){
		if (sceneNum == 1)
			test1.draw(g);
		else
			test2.draw(g);
		
		g.drawImage(textBox, 0, 350, null);
		g.drawImage(coinBox, 1064, 0, null);
		g.drawImage(buttonBox, 200, 520, null);
		g.drawImage(buttonBox, 542, 520, null);
		g.drawImage(buttonBox, 883, 520, null);
	}
	
	public Image getImage(String pathName){
		URL url = getClass().getResource(pathName);
		Image image = Toolkit.getDefaultToolkit().getImage(url);
		return image;
	}
	
	class ChangeSceneListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
	
			if (e.getX() > 200 && e.getX() < (200+197) && e.getY() > 520 && e.getY() < (520+56))
			{
				changeScene(mainPlayer);
			}
			
			if (e.getX() > 542 && e.getX() < (542+197) && e.getY() > 520 && e.getY() < (520+56))
			{
				changeScene(mainPlayer);
			}
		
			if (e.getX() > 883 && e.getX() < (883+197) && e.getY() > 520 && e.getY() < (520+56))
			{
				changeScene(mainPlayer);
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}
	
	
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Image Play");
		GameGUI panel = new GameGUI();
		frame.add(panel, BorderLayout.CENTER);
		frame.setSize(1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
}


