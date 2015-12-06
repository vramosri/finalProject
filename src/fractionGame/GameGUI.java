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
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class GameGUI extends JPanel{
	private ArrayList<Scene> scenes;
	Player mainPlayer;
	private AudioInputStream audioInputStream;
	private Clip clip;
	
	private Image textBox;
	private Image coinBox;
	private Image buttonBox;
	private Image menuBox;
	private MediaTracker tracker;
	
	private Fraction q;
	private Fraction answer;
	private Fraction option1;
	private Fraction option2;
	private Question currentQuestion;
	Cursor inactiveCursor;
	Cursor activeCursor;
	private Fraction additionFraction; 
	int currentSceneNum;
	int dialogueType;

	int position = -1; 
	
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
		scenes = new ArrayList<Scene>();
		currentSceneNum = 0;
		dialogueType = 0;
		
		
		// create the scenes
		// title scene
		scenes.add(new Scene("/graphics/Backgrounds/Hills1.png","/music/1_Adventure Meme.wav", 0, SceneType.TITLE));
		// intro scene
		scenes.add(new Scene("/graphics/Backgrounds/Hills1.png","/music/1_Adventure Meme.wav", 0, SceneType.INTRO));
		// game scenes
		scenes.add(new Scene("/graphics/Backgrounds/Hills1.png","/music/2_Local Forecast.wav", 0, SceneType.STORY, "/graphics/Characters/Dwarf.png", 1));
		scenes.add(new Scene("/graphics/Backgrounds/Beach.png","/music/3_Pamgaea.wav", 1, SceneType.STORY, "/graphics/Characters/Mermaid.png", 2));
		scenes.add(new Scene("/graphics/Backgrounds/Hills2.png","/music/4_One-Eyed Maestro.wav", 2, SceneType.STORY, "/graphics/Characters/Traveller.png", 3));
		scenes.add(new Scene("/graphics/Backgrounds/Ruins.png","/music/5_Sneaky Snitch.wav", 3, SceneType.STORY, "/graphics/Characters/ScavengerCat.png", 4));
		
		scenes.add(new Scene("/graphics/Backgrounds/Forest1.png","/music/6_Run Amok.wav", 4, SceneType.STORY, "/graphics/Characters/Elf.png", 1));
		scenes.add(new Scene("/graphics/Backgrounds/Forest2.png","/music/7_Meatball Parade.wav", 5, SceneType.STORY, "/graphics/Characters/Otter.png", 2));
		scenes.add(new Scene("/graphics/Backgrounds/Cave.png","/music/8_Scheming Weasel faster.wav", 6, SceneType.STORY, "/graphics/Characters/Goblin.png", 3));
		scenes.add(new Scene("/graphics/Backgrounds/MountainPeak.png","/music/9_Five Armies.wav", 7, SceneType.STORY, "/graphics/Characters/Dragon.png", 4));
		
		scenes.add(new Scene("/graphics/Backgrounds/MountainPass.png","/music/10_Undaunted.wav", 8, SceneType.STORY, "/graphics/Characters/Golem.png", 1));
		scenes.add(new Scene("/graphics/Backgrounds/Forest3.png","/music/11_The Cannery.wav", 9, SceneType.STORY, "/graphics/Characters/Witch.png", 2));
		scenes.add(new Scene("/graphics/Backgrounds/Village.png","/music/12_Thatched Villagers.wav", 10, SceneType.STORY, "/graphics/Characters/CatLady.png", 3));
		scenes.add(new Scene("/graphics/Backgrounds/Hills3.png","/music/13_Spazzmatica Polka.wav", 11, SceneType.STORY, "/graphics/Characters/Beggar.png", 4));
		// ending scene
		scenes.add(new Scene("/graphics/Backgrounds/Castle.png","/music/14_Truth of the Legend.wav", 12, SceneType.ENDING));
		
		
		this.addMouseListener(new ChangeSceneListener());
		
		// This is to make sure all of the images display after they are loaded
		tracker = new MediaTracker(this);
		textBox = getImage("/graphics/System/TextBox.png");
		coinBox = getImage("/graphics/System/Coins.png");
		buttonBox = getImage("/graphics/System/Button.png");
		menuBox = getImage("/graphics/System/TitleMenu.png");
		tracker.addImage(textBox, 0);
		tracker.addImage(coinBox, 0);
		tracker.addImage(buttonBox, 0);
		tracker.addImage(menuBox, 0);
		try {
			tracker.waitForID(0);
		} catch (InterruptedException e) {
			return;
		}
		this.playSound(scenes.get(currentSceneNum).getMusicFile());
	}
	
	public void changeScene(Player mainPlayer){
		clip.stop();
		this.playSound(scenes.get(currentSceneNum).getMusicFile());
		mainPlayer.setProgress(scenes.get(currentSceneNum).getSceneNum());
		
		if (currentSceneNum == scenes.size()-1){
			currentSceneNum = 0;
			dialogueType = 0;
		}
		else
			currentSceneNum++;
		
		//testing
		if (currentSceneNum == 2)
			dialogueType = 2;
		
		if (scenes.get(currentSceneNum).sceneType == SceneType.ENDING)
			dialogueType = 1;
		//testing
		
		// when scene changes, the dialogue type will be set to 1

		
		
		repaint();
	}
	

	
	public void paintComponent(Graphics g){
		// draw the current scene
		scenes.get(currentSceneNum).draw(g);
		
		// draw the title menu if the title screen is open
		if(currentSceneNum == 0) {
			g.drawImage(menuBox, 0, -80, null);
			drawString(g, "New Game", 580, 348);
			drawString(g, "Continue", 592, 453);
		}
		else {
			g.drawImage(textBox, 0, 350, null);
			g.drawImage(coinBox, 1064, 0, null);
		}
			
		// draw the buttons if the dialogue is ready for interaction
		if(dialogueType == 2) {
			g.drawImage(buttonBox, 200, 520, null);
			g.drawImage(buttonBox, 542, 520, null);
			g.drawImage(buttonBox, 883, 520, null);
		}
		
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
			// Title screen buttons
			if (currentSceneNum == 0){
				// New game button, will just change to next scene
				if (e.getX() > 446 && e.getX() < 837 && e.getY() > (439 - 80) && e.getY() < (493 - 80)) {
					changeScene(mainPlayer);
					dialogueType = 1;
				}
				// Continue button, will read from a text file and set scene and coins based on information read
				if (e.getX() > 446 && e.getX() < 837 && e.getY() > (543 - 80) && e.getY() < (596 - 80)) {
					changeScene(mainPlayer);
				}
			}
			
			
			// Click through dialogue
			if (dialogueType == 1){
				//After clicking through dialogue, dialogue type will be set to 2 and repaint which will display question and buttons
				
				//testing
				changeScene(mainPlayer);
			}
			
			// Dialogue Buttons
			else if (dialogueType == 2){
				if (e.getX() > 200 && e.getX() < (200+197) && e.getY() > 520 && e.getY() < (520+56) && position == 0)
				{
					mainPlayer.addCoins(currentQuestion.getCoins());
					changeScene(mainPlayer);
					// instead of changing scenes, dialogue type will be set to 3 for conclusion dialogue and repaint
					
				}
				else if (e.getX() > 200 && e.getX() < (200+197) && e.getY() > 520 && e.getY() < (520+56))
				{
					currentQuestion.reduceCoins();
				}
				if (e.getX() > 542 && e.getX() < (542+197) && e.getY() > 520 && e.getY() < (520+56) && position == 1)
				{
					mainPlayer.addCoins(currentQuestion.getCoins());
					changeScene(mainPlayer);
				}
				else if (e.getX() > 542 && e.getX() < (542+197) && e.getY() > 520 && e.getY() < (520+56))
				{
					currentQuestion.reduceCoins();
				}
				if (e.getX() > 883 && e.getX() < (883+197) && e.getY() > 520 && e.getY() < (520+56) && position == 2)
				{
					mainPlayer.addCoins(currentQuestion.getCoins());
					changeScene(mainPlayer);
				}
				if (e.getX() > 883 && e.getX() < (883+197) && e.getY() > 520 && e.getY() < (520+56))
				{
					currentQuestion.reduceCoins();
				}
				
				System.out.println("Coins: " + mainPlayer.getCoins());
			}
			
			// Click through the outro dialogue, will change the scene
			//else if (dialogueType == 3){
				// After clicking through conclusion dialogue, the scene will be changed which will automatically set dialogue type to 1
			//	
			//}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {}
		@Override
		public void mouseExited(MouseEvent arg0) {}
		@Override
		public void mousePressed(MouseEvent arg0) {}
		@Override
		public void mouseReleased(MouseEvent arg0) {}
	}
	


	public ArrayList<Fraction> generate(int difficulty) {
		
		
		if (currentSceneNum == 2 || currentSceneNum == 3 || currentSceneNum == 4 || currentSceneNum == 5)
		{
			currentQuestion = new MatchingQuestion(); 
			 q = currentQuestion.generateQuestion(difficulty); 
		}
		else if (currentSceneNum == 6 || currentSceneNum == 7 || currentSceneNum == 8 || currentSceneNum == 9)
		{
			currentQuestion = new AdditionQuestion();
			 q = currentQuestion.generateQuestion(difficulty); 
			 additionFraction = ((AdditionQuestion) currentQuestion).getAdditionFraction();
		} 
		
		else if (currentSceneNum == 10 || currentSceneNum == 11 || currentSceneNum == 12 || currentSceneNum == 13)
		{
			currentQuestion = new MultiplicationQuestion();
			q = currentQuestion.generateQuestion(difficulty); 
		}
		
	
		
		 answer = currentQuestion.generateAnswer(difficulty); 
		 option1 = currentQuestion.generateOption(difficulty); 
		 option2 = currentQuestion.generateOption(difficulty); 
		 
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
				option2 = currentQuestion.generateOption(difficulty); 
			}
			}
			return options; 
			
	}
	
	public void draw(Graphics g) {
		boolean foundAnswer = false; 
		ArrayList options = new ArrayList<Fraction>();
		if (scenes.get(currentSceneNum).sceneType == SceneType.STORY) {
			options = generate(scenes.get(currentSceneNum).getDifficulty()); // Make sure to change according to scene 

			Random randy = new Random();
			int random = randy.nextInt(3);
			if (random == 0 && foundAnswer == false) {
				position = 1;
				foundAnswer = true;
			}
			drawString(g, options.get(random).toString(), 650, 510);
			options.remove(random);
			random = randy.nextInt(2);
			if (random == 0 && foundAnswer == false) {
				position = 0;
				foundAnswer = true;
			}
			drawString(g, options.get(random).toString(), 300, 510);
			options.remove(random);
			random = randy.nextInt(1);
			if (random == 0 && foundAnswer == false) {
				position = 2;
				foundAnswer = true;
			}
			
			// Draw the options over the buttons
			drawString(g, options.get(random).toString() , 985, 510);
		}
		
		
		if (currentSceneNum == 2 || currentSceneNum == 3 || currentSceneNum == 4 || currentSceneNum == 5)
		{
			drawString(g, " Test: If I have " + q.getNumerator() + " seashells that\nare blue out of "  + q.getDenominator() + "What fraction are blue?"  , 400, 400);
		}
		else if (currentSceneNum == 6 || currentSceneNum == 7 || currentSceneNum == 8 || currentSceneNum == 9)
		{
			drawString(g, " Test: If I have " + q.toString() + " seashells that\nare blue out of "  + additionFraction.toString() + "What fraction are blue?"  , 400, 400);
		}
		else if (currentSceneNum == 10 || currentSceneNum == 11 || currentSceneNum == 12 || currentSceneNum == 13)
		{
			drawString(g, " Test: If I have " + q.toString() + " seashells that\nare blue out of "  + currentQuestion.getQuestionFraction() + "What fraction are blue?"  , 400, 400);
		}

		
		// Draw the coins in the coin box
		if (scenes.get(currentSceneNum).sceneType != SceneType.TITLE)
			drawString(g, "" + mainPlayer.getCoins() +"" , 1210, 18);
	}
	
	// function to allow newlines to be used in a drawString function that also adds a border to the text
	private void drawString(Graphics g, String text, int x, int y){
		for (String line : text.split("\n")){
			g.setColor(new Color(100, 100, 50));
			g.drawString(line, x - 1, y += g.getFontMetrics().getHeight() - 1);
			g.drawString(line, x - 1, y + 1);
			g.drawString(line, x + 1, y - 1);
			g.drawString(line, x + 1, y + 1);
			g.setColor(new Color(200, 200, 150));
			g.drawString(line, x, y);
		}
	}
	
	public void playSound(String musicFile){
		// play the music for the current scene
		try {
			audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource(musicFile));
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception e) {

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


