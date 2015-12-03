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
			while (denominator == 0) {
				denominator = r.nextInt(difficulty * 4) % 2;
			}
			numerator = r.nextInt(difficulty * 4) % 2;
		case 2:
			while (denominator == 0) {
				denominator = r.nextInt(difficulty * 4) + 1;
			}
			numerator = r.nextInt(difficulty * 4);
		case 3:
			while (denominator == 0) {
				denominator = r.nextInt(difficulty * 4) % 2;
			}
			numerator = r.nextInt(difficulty * 4) % 2;
		case 4:
			while (denominator == 0) {
				denominator = r.nextInt(difficulty * 4) + 1;
			}
			numerator = r.nextInt(difficulty * 4);
		}
		return new Fraction(numerator, denominator); 
	}
	public Fraction generateAnswer() {
		Fraction fra = new Fraction(1,2); 
		return fra; 
	}

	public Fraction generateOption(Fraction correctAnswer)
	{
		int num = correctAnswer.getNumerator();
		int den = correctAnswer.getDenominator(); 
	
		Random randy = new Random();
		int random = randy.nextInt(5);
		
		
		switch (random)
		{
		case 0:
			den = den+1;
			num = num+2; 
			break; 
		case 1: 
			int temp = den; 	
			den = num; 
			num = temp; 
			break; 
		case 2:
			if (den %2 == 0 && den !=2)
			{
				den = den/2; 
			}
			else {
				den++; 
			}
			break; 
		case 3: 
			den =den*2; 
			num = num *3; 
			break; 
		case 4: 
			if (den !=1  && num !=1)
			{
			den = den -1;
			num = num -1;
			}
			else {
				den++; 
				num++; 
			}
		default: break; 
		}
		System.out.println(random);
		System.out.println("Num " +num + "Den " + den);
		return new Fraction(num, den); 		
		
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
