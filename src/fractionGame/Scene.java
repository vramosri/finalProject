package fractionGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Scene extends JPanel{

	private String backgroundFile; 
	private String characterFile;
	private String progressFile;
	private Question question;
	private int difficulty;
	private int sceneNum;
	
	private Image background;
	private Image character;
	private Image progress;
	private MediaTracker tracker;
	
	public Scene(String backgroundFile, int sceneNum) {
		this.backgroundFile = backgroundFile;
		this.sceneNum = sceneNum;
		
		tracker = new MediaTracker(this);
		background = getImage(backgroundFile);
		progress = getImage("/graphics/System/ProgressBar" + sceneNum + ".png");
		tracker.addImage(background, 0);
		tracker.addImage(progress, 0);
		try {
			tracker.waitForID(0);
		} catch (InterruptedException e) {
			return;
		}
	}
	
	public Scene(String backgroundFile, int sceneNum, String characterFile, int difficulty) {
		this(backgroundFile, sceneNum);
		this.characterFile = characterFile;
		this.difficulty = difficulty;
		
		
		tracker = new MediaTracker(this);
		background = getImage(backgroundFile);
		character = getImage(characterFile);
		progress = getImage("/graphics/System/ProgressBar" + sceneNum + ".png");
		tracker.addImage(background, 0);
		tracker.addImage(character, 0);
		tracker.addImage(progress, 0);
		try {
			tracker.waitForID(0);
		} catch (InterruptedException e) {
			return;
		}
	}

	public void draw(Graphics g) {
		g.drawImage(background, 0, 0, null);
		g.drawImage(character, 680, 30, null);
		g.drawImage(progress, 0, 620, null);
	}
	
	
	public void paintComponent(Graphics g){
		draw(g);
	}
	
	private Image getImage(String pathName){
		URL url = getClass().getResource(pathName);
		Image image = Toolkit.getDefaultToolkit().getImage(url);
		return image;
	}

	public int getSceneNum() {
		return sceneNum;
	}

	public int getDifficulty() {
		return difficulty;
	}
	
	
}
