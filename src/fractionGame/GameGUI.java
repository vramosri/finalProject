package fractionGame;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.InputStream;
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
	private Fraction q;
	private Fraction answer;
	private Fraction option1;
	private Fraction option2;
	Cursor inactiveCursor;
	Cursor activeCursor;
	
	//testing
	private Scene test1;
	private Scene test2;
	int sceneNum = 1;
	
	public GameGUI(){
		// Create custom cursors for active and inactive and set the cursor as inactive
		inactiveCursor = Toolkit.getDefaultToolkit().createCustomCursor(
				getImage("/graphics/System/CursorInactive.png"),
				new Point(0,0), "custom cursor");
		activeCursor = Toolkit.getDefaultToolkit().createCustomCursor(
				getImage("/graphics/System/CursorActive.png"),
				new Point(0,0), "custom cursor");
		
		setCursor(inactiveCursor);
		
		// Load custom font
		InputStream is = GameGUI.class.getResourceAsStream("/graphics/Quintessential-Regular.ttf");
		try {
			Font font = Font.createFont(Font.TRUETYPE_FONT, is);
			Font sizedFont = font.deriveFont(30f);
			setFont(sizedFont);
		} catch (FontFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		mainPlayer = new Player();
		
		// create some scenes to test things with
		test1 = new Scene("/graphics/Backgrounds/Beach.png", "Test", 1, "/graphics/Characters/Mermaid.png", 1);
		test2 = new Scene("/graphics/Backgrounds/Cave.png", "Test", 2, "/graphics/Characters/Dwarf.png", 1);
		
		
		this.addMouseListener(new ChangeSceneListener());
		
		// This is to make sure all of the images display after they are loaded
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
			// change cursor icon to active
			setCursor(activeCursor);
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// change cursor icon to inactive
			setCursor(inactiveCursor);
		}

		@Override
		public void mousePressed(MouseEvent arg0) {}
		@Override
		public void mouseReleased(MouseEvent arg0) {}
	}
	

	public ArrayList<Fraction> generate(int difficulty) {
		 MatchingQuestion question = new MatchingQuestion(); 
		 q = question.generateQuestion(difficulty); 
		 answer = question.generateAnswer(difficulty); 
		 option1 = question.generateOption(difficulty); 
		 option2 = question.generateOption(difficulty); 
		 
			ArrayList options = new ArrayList<Fraction>();
			options.add(answer); 
			options.add(option1);

			
			while(options.size() <3)
			{
			if (!option1.checkEquals(option2))
			{
				options.add(option2);
				
			}
			else {
				option2 = question.generateOption(difficulty); 
			}
			}
			System.out.println("Option 1: " + option1);
			System.out.println("Option 2: " + option2);
			return options; 
			
	}
	
	
	public void draw(Graphics g) {
	
		ArrayList options = new ArrayList<Fraction>();
		options = generate(1); 
		Collections.shuffle(options);
		int position = 0; 
		
		for (int i = 0; i < 3; i++)
		{
			if (options.get(i).equals(answer))
			{
				position = i; 
			}

		}
	
		drawString(g, " Test: If I have " + q.getNumerator() + " seashells that\nare blue out of "  + q.getDenominator() + "What fraction are blue?"  , 400, 400);
		drawString(g, options.get(0).toString() , 650, 510);
		drawString(g, options.get(1).toString() , 300, 510);
		drawString(g, options.get(2).toString() , 985, 510);
		System.out.println(position);
		// test drawstring
		drawString(g, "testing testing\nooh look a new line", 100, 400);
	}
	
	// function to allow newlines to be used in a drawString function that also adds a border to the text
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


