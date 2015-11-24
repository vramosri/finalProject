package fractionGame;

import java.util.ArrayList;

public abstract class Question {
	
	private ArrayList<Fraction> answers = null; 
	private String question; 
	private Fraction correctAnswer; 
	private int coins;
	
	public abstract boolean checkAnswer(Fraction fraction);
	
	public void reduceCoins(){
		
	}
	
	public Fraction getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(Fraction correctAnswer) {
		correctAnswer = correctAnswer;
	}

	public void draw() {
		
	}
	
	public void setCoins(int coins){
		this.coins = coins;
	}
	
	public int getCoins(){
		return coins;
	}
	
}
