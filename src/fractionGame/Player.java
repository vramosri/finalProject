package fractionGame;

import java.util.ArrayList;

public class Player {

	
	private int coins;
	private String name;
	private ArrayList<String> items; //Optional right now
	private int progress;
	
	public final static int TOTALPROGRESS = 12;
	
	public Player(){
		coins = 0;
		progress = 0;
	}
	
	public void addCoins(int coins){
		this.coins+=coins;
	}

	public int getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}
	
	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}
	
}
