package fractionGame;

import java.awt.BorderLayout;
import java.awt.Color;
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
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

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
		{	
			test1.draw(g);
		}
		else
			test2.draw(g);
		
		g.drawImage(textBox, 0, 350, null);
		g.drawImage(coinBox, 1064, 0, null);
		g.drawImage(buttonBox, 200, 520, null);
		g.drawImage(buttonBox, 542, 520, null);
		g.drawImage(buttonBox, 883, 520, null);
		draw(g);
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
	


	
	public void draw(Graphics g) {
		MatchingQuestion question = new MatchingQuestion(); 
		Fraction answer = question.generateQuestion(1); 
		Fraction option1 = question.generateOption(1, answer); 
		Fraction option2 = question.generateOption(1, answer); 
		Fraction option; 
		System.out.println(answer.toString());
		ArrayList options = new ArrayList<Fraction>();
		options.add(answer); 
		options.add(option1);
		options.add(option2);
		Collections.shuffle(options);
		int position = 0; 
		
		for (int i = 0; i < 2; i++)
		{
			if (options.get(i).equals(answer))
			{
				position = i; 
			}
		}
		
		
		g.setFont(g.getFont().deriveFont(20f));
		drawString(g, options.get(0).toString() , 650, 530);
		g.setFont(g.getFont().deriveFont(20f));
		drawString(g, options.get(1).toString() , 300, 530);
		g.setFont(g.getFont().deriveFont(20f));
		drawString(g, options.get(2).toString() , 985, 530);
	}
	
	private void drawString(Graphics g, String text, int x, int y){
		for (String line : text.split("\n")){
			g.setColor(new Color(150, 150, 100));
			g.drawString(line, x - 1, y += g.getFontMetrics().getHeight() - 1);
			g.drawString(line, x - 1, y + 1);
			g.drawString(line, x + 1, y - 1);
			g.drawString(line, x + 1, y + 1);
			g.setColor(new Color(200, 200, 150));
			g.drawString(line, x, y);
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


