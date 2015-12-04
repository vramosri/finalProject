package fractionGame;

import java.util.ArrayList;

public abstract class Question {
	
	private ArrayList<Fraction> answers;
	protected Fraction questionFraction;
	protected Fraction correctAnswer; 
	protected int coinValue;
	protected int coins;
	
	public abstract boolean checkAnswer(Fraction fraction);
	
	public void reduceCoins(){
		if(coins > 0)
			coins = coins - (coinValue / 2);
	}
	
	public Fraction getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(Fraction correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public void draw() {
		
	}
	
	public void setCoins(int coins){
		this.coins = coins;
	}
	
	public int getCoins(){
		return coins;
	}

	public Fraction getQuestionFraction() {
		return questionFraction;
	}
	
	
}
