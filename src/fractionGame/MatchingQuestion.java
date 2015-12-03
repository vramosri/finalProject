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
		case 1:
			while ((denominator == 0) || (denominator % 2 == 1)) {
				denominator = r.nextInt(difficulty * 4 + 1);
			}
			while ((numerator == 0) || (numerator > denominator)) {
				numerator = r.nextInt(difficulty * 4) + 1;
			}
			break;
		case 2:
			while ((denominator == 0) || (denominator < 4)) {
				denominator = r.nextInt(difficulty * 4 + 1);
			}
			while ((numerator == 0) || (numerator > denominator)) {
				numerator = r.nextInt(difficulty * 4);
			}
			break;
		case 3:
			while ((denominator == 0) || (denominator % 2 == 1) || (denominator < 8)) {
				denominator = r.nextInt(difficulty * 4) + 1;
			}
			while ((numerator == 0) || (numerator > denominator)) {
				numerator = r.nextInt(difficulty * 4) + 1;
			}
			break;
		case 4:
			while ((denominator == 0) || (denominator < 10)) {
				denominator = r.nextInt(difficulty * 4) + 1;
			}
			while ((numerator == 0) || (numerator > denominator)) {
				numerator = r.nextInt(difficulty * 4);
			}
			break;
		}
		return new Fraction(numerator, denominator); 
	}
	
	public Fraction generateAnswer(int difficulty) {
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
