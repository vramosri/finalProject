package fractionGame;

import java.util.Random;

public class MatchingQuestion extends Question {
	
	public MatchingQuestion(){
		this.coinValue = 10;
		this.coins = this.coinValue;
	}
	
	public Fraction generateQuestion(int difficulty) {
		// TODO: Make sure that the denominator is not 0 when generating random fraction
		int numerator = 0;
		int denominator = 0;
		Random r = new Random();
		switch(difficulty){
		case 1: while(denominator == 0){
			denominator = r.nextInt(difficulty * 4) % 2;
		}
		numerator = r.nextInt(difficulty * 4) % 2;
		case 2: while(denominator == 0){
			denominator = r.nextInt(difficulty * 4) % 2;
		}
		numerator = r.nextInt(difficulty * 4) % 2;
		case 3: while(denominator == 0){
			denominator = r.nextInt(difficulty * 4) + 1;
		}
		numerator = r.nextInt(difficulty * 4) % 2;
		}
		return new Fraction(numerator, denominator); 
	}
	public Fraction generateAnswer() {
		Fraction fra = new Fraction(1,2); 
		return fra; 
	}

	@Override
	public boolean checkAnswer(Fraction fraction) {
		// TODO Auto-generated method stub
		if(correctAnswer.checkEquals(fraction))
			return true;
		else{
			this.reduceCoins();
			return false;
		}
	}
	
	public int getCoinValue(){
		return coinValue;
	}
	
}
