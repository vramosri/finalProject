package fractionGame;

import java.util.ArrayList;

public class Player {

	
	private int coins;
	private String name;
	private ArrayList<String> items; //Optional right now
	private String iconFile;
	
	public Player(){
		
	}
	
	public void draw(){
		
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
	
}
