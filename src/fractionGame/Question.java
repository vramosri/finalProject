package fractionGame;

import java.util.ArrayList;

public abstract class Question {
	
	//Question.java is an abstract class from which the other, implementable question types inherit methods and variables.
	//The main purpose of Question.java is to hold variables common to each question type, and to provide the reduceCoins() function.
	
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

	public abstract Fraction generateOption(int difficulty);

	public abstract Fraction generateQuestion(int difficulty);

	public abstract Fraction generateAnswer(int difficulty);
	
}
