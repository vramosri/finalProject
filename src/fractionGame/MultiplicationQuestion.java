package fractionGame;

import java.util.Random;

public class MultiplicationQuestion extends Question {

	public MultiplicationQuestion(){
		this.coinValue = 30;
		this.coins = this.coinValue;
	}
	
	public Fraction generateQuestion(int difficulty) {
		// TODO: Make sure that the denominator is not 0 when generating random fraction
		int numerator = 0;
		int denominator = 0;
		Random r = new Random();
		return new Fraction(numerator, denominator); 
	}
	public Fraction generateAnswer() {
		Fraction fra = new Fraction(1,2); 
		return fra; 
	}

	@Override
	public boolean checkAnswer(Fraction fraction) {
		
		return false;
	}
	
	public int getCoinValue(){
		return coinValue;
	}
	
}
