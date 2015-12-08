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
import java.util.Scanner;
import java.util.Set;
import java.io.File;
import java.io.FileReader;

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
	private ArrayList<String> titleDialog = new ArrayList<>();
	private ArrayList<String> introDialog = new ArrayList<>();
	private ArrayList<String> questionDialog  = new ArrayList<>();
	private ArrayList<String> wrongDialog  = new ArrayList<>();
	private ArrayList<String> solvedDialog  = new ArrayList<>();
	private boolean gotAnswer;
	private int tempCoins;
	

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
		loadDialog();
		
		// Load custom font
		InputStream is = GameGUI.class.getResourceAsStream("/graphics/Quintessential-Regular.ttf");
		try {
			Font font = Font.createFont(Font.TRUETYPE_FONT, is);
			Font sizedFont = font.deriveFont(30f);
			setFont(sizedFont);
		} catch (FontFormatException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
		
		mainPlayer = new Player();
		scenes = new ArrayList<Scene>();
		currentSceneNum = 0;
		dialogueType = 0;
		gotAnswer = false;
		tempCoins = 0;
		
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
		

		this.playSound(scenes.get(0).getMusicFile());
	}
	
	public void loadDialog()
	{
		String dialogFile = "src/dialog.txt";
		FileReader file;
		try {
			file = new FileReader(dialogFile);
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			System.out.println();
			return;
		}
		
		// Read in the values from the file
		Scanner scan = new Scanner(file);
		
		while (scan.hasNext()) {
			String line = scan.nextLine();
			
			if(line.length() <= 0){
				
			}
			else if (line.substring(0, 1).equals("#"))
			{
				titleDialog.add(line); 
			}
			else if(line.substring(0, 1).equals("$"))
			{
				introDialog.add(line); 
			}
			else if (line.substring(0, 1).equals("*"))
			{
				questionDialog.add(line); 
			}
			else if (line.substring(0, 1).equals("^"))
			{
				wrongDialog.add(line); 
			}
			else if (line.substring(0, 1).equals("+"))
			{
				solvedDialog.add(line); 
			}
			
		}

		scan.close();

	}
	
	
	
	public void changeScene(Player mainPlayer){
		clip.stop();
		clip.close();
		this.playSound(scenes.get(currentSceneNum + 1).getMusicFile());
		mainPlayer.setProgress(scenes.get(currentSceneNum).getSceneNum());
		
		if (currentSceneNum == scenes.size()-1){
			currentSceneNum = 0;
			dialogueType = 0;
		}
		else
			currentSceneNum++;
		
		//testing
		if (currentSceneNum == 1)
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
		else if (scenes.get(currentSceneNum).sceneType == SceneType.STORY){
			g.drawImage(textBox, 0, 350, null);
			g.drawImage(coinBox, 1064, 0, null);
		}
		else
			g.drawImage(coinBox, 1064, 0, null);
			
		// draw the buttons if the dialogue is ready for interaction
		if(dialogueType == 2 && scenes.get(currentSceneNum).sceneType == SceneType.STORY) {
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
		public void mouseReleased(MouseEvent e) {
			
			if(scenes.get(currentSceneNum).sceneType == SceneType.ENDING){
				
				main(null);
				System.exit(0);
			}
		
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
			
			
			// Intro Dialog
			else if (dialogueType == 1 && scenes.get(currentSceneNum).sceneType == SceneType.INTRO){
				//After clicking through dialogue, dialogue type will be set to 2 and repaint which will display question and buttons
				changeScene(mainPlayer);
				dialogueType = 2;
				repaint(); 

			}
			
			// Click through dialogue
			/*if (dialogueType == 1 && scenes.get(currentSceneNum).sceneType != SceneType.INTRO){
				//After clicking through dialogue, dialogue type will be set to 2 and repaint which will display question and buttons
				//if(scenes.get(currentSceneNum).sceneType == SceneType.INTRO){
					//dialogueType = 3;
				//}
				//else
				dialogueType = 2; 
				repaint(); 
			}*/
			
			// Dialogue Buttons
			else if (dialogueType == 2){
				if (e.getX() > 200 && e.getX() < (200+197) && e.getY() > 520 && e.getY() < (520+56) && position == 0)
				{
					mainPlayer.addCoins(currentQuestion.getCoins());					
					//drawString(getGraphics(), solvedDialog.get(currentSceneNum-2).substring(1), 10, 180);
					gotAnswer = true;
					dialogueType = 1;
					tempCoins = currentQuestion.getCoins();
					//changeScene(mainPlayer);
					repaint(); 
					//dialogueType =3; 
				
					// instead of changing scenes, dialogue type will be set to 3 for conclusion dialogue and repaint
					
				}
				else if (e.getX() > 200 && e.getX() < (200+197) && e.getY() > 520 && e.getY() < (520+56))
				{
					currentQuestion.reduceCoins();
					String quote = wrongDialog.get(currentSceneNum-2);
					quote = quote.substring(1);
					drawString(getGraphics(), quote, 10, 150);
				}
				
				if (e.getX() > 542 && e.getX() < (542+197) && e.getY() > 520 && e.getY() < (520+56) && position == 1)
				{
					mainPlayer.addCoins(currentQuestion.getCoins());
					//changeScene(mainPlayer);
					//drawString(getGraphics(), solvedDialog.get(currentSceneNum-2).substring(1), 10, 180);
					gotAnswer = true;
					dialogueType = 1;
					tempCoins = currentQuestion.getCoins();
					repaint();
					 
				}
				else if (e.getX() > 542 && e.getX() < (542+197) && e.getY() > 520 && e.getY() < (520+56))
				{
					currentQuestion.reduceCoins();
					String quote = wrongDialog.get(currentSceneNum-2);
					quote = quote.substring(1);
					drawString(getGraphics(), quote, 10, 150); //TODO: WAS 80 150

				}
				if (e.getX() > 883 && e.getX() < (883+197) && e.getY() > 520 && e.getY() < (520+56) && position == 2)
				{
					mainPlayer.addCoins(currentQuestion.getCoins());
					//changeScene(mainPlayer);
					//drawString(getGraphics(), solvedDialog.get(currentSceneNum-2).substring(1), 10, 180);
					gotAnswer =true;
					dialogueType = 1;
					tempCoins = currentQuestion.getCoins();
					repaint();
					 
				}
				else if (e.getX() > 883 && e.getX() < (883+197) && e.getY() > 520 && e.getY() < (520+56))
				{
					currentQuestion.reduceCoins();
					String quote = wrongDialog.get(currentSceneNum-2);
					quote = quote.substring(1);
					drawString(getGraphics(), quote, 10, 150); //TODO: was 80 150

				}
				
				System.out.println("Coins: " + mainPlayer.getCoins());
			}	
			else if(gotAnswer){
				changeScene(mainPlayer);
				dialogueType = 2;
				gotAnswer = false;
			}
		
			
			
			
			// Click through the outro dialogue, will change the scene
		//	else if (dialogueType == 3){
				// After clicking through conclusion dialogue, the scene will be changed which will automatically set dialogue type to 1
			//	dialogueType = 1; 
				//changeScene(mainPlayer);
			//	repaint();
			//}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {}
		@Override
		public void mouseExited(MouseEvent arg0) {}
		@Override
		public void mousePressed(MouseEvent arg0) {}
		@Override
		public void mouseClicked(MouseEvent arg0) {}
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
		if (scenes.get(currentSceneNum).sceneType == SceneType.STORY && currentSceneNum > 1 ) {
			options = generate(scenes.get(currentSceneNum).getDifficulty()); // Make sure to change according to scene 

			if(!gotAnswer){
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
				
				// Draw the options over the buttons and the question
				
				drawString(g, options.get(random).toString() , 985, 510);
				
				drawString(g, introDialog.get(currentSceneNum-2).substring(1), 10, 10); //TODO: BLAH FBJWK comment out or delete to undo intro dialog
				
				String[] str =questionDialog.get(currentSceneNum-2).split("@"); 
				String quote = "";
				
				
				if (currentSceneNum == 2 || currentSceneNum == 3 || currentSceneNum == 4 || currentSceneNum == 5)
				{
					quote  = str[0] + q.getNumerator()+ str[1] + q.getDenominator() + str[2];
					quote = quote.substring(1);
					//drawString(g, quote , 200, 400);
				}
				else if (currentSceneNum == 6 || currentSceneNum == 7 || currentSceneNum == 8 || currentSceneNum == 9)
				{
					quote  = str[0] + q.toString()+ str[1] + additionFraction.toString() + str[2];
					quote = quote.substring(1);
					//drawString(g, " Test: If I have " + q.toString() + " seashells that\nare blue out of "  + additionFraction.toString() + "What fraction are blue?"  , 400, 400);
				}
				else if (currentSceneNum == 10 || currentSceneNum == 11 || currentSceneNum == 12 || currentSceneNum == 13)
				{
					quote  = str[0] + q.toString() + str[1] + currentQuestion.getQuestionFraction() + str[2];
					quote = quote.substring(1);
					//drawString(g, " Test: If I have " + q.toString() + " seashells that\nare blue out of "  + currentQuestion.getQuestionFraction() + "What fraction are blue?"  , 400, 400);
				}
				drawString(g, quote , 80, 400);
			}
			else{
				String[] str = solvedDialog.get(currentSceneNum-2).split("@"); 
				//currentQuestion.setCoins(100);
				String quote = str[0] + tempCoins + str[1];
				
				drawString(g, quote.substring(1), 80, 400);
			}
		}
		
		// display intro text
		if(scenes.get(currentSceneNum).sceneType == SceneType.INTRO){
			drawString(g, titleDialog.get(0).substring(1)+ "&" +titleDialog.get(1).substring(1), 80, 80);
		}
		// display ending text based on amount of coins
		else if(scenes.get(currentSceneNum).sceneType == SceneType.ENDING){
			String[] str = titleDialog.get(2).substring(1).split("@");
			
			if (mainPlayer.getCoins() >= 0 && mainPlayer.getCoins() < 80)
				drawString(g, str[0] + mainPlayer.getCoins() + str[1] + "&" +titleDialog.get(3).substring(1), 80, 80);
			else if (mainPlayer.getCoins() >= 80 && mainPlayer.getCoins() < 160)
				drawString(g, str[0] + mainPlayer.getCoins() + str[1] + "&" +titleDialog.get(4).substring(1), 80, 80);
			else if (mainPlayer.getCoins() >= 160 && mainPlayer.getCoins() <= 240)
				drawString(g, str[0] + mainPlayer.getCoins() + str[1] + "&" +titleDialog.get(5).substring(1), 80, 80);
		}


		
		// Draw the coins in the coin box
		if (scenes.get(currentSceneNum).sceneType != SceneType.TITLE)
			drawString(g, "" + mainPlayer.getCoins() +"" , 1210, 18);
	}
	
	// function to allow newlines to be used in a drawString function that also adds a border to the text
	private void drawString(Graphics g, String text, int x, int y){
		for (String line : text.split("&")){
			g.setColor(new Color(50, 50, 0));
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
		JFrame frame = new JFrame("Squiggly's Fraction Adventures");
		GameGUI panel = new GameGUI();
		frame.add(panel, BorderLayout.CENTER);
		frame.setSize(1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	
}


