package fractionGame;

import java.util.Random;

public class MultiplicationQuestion extends Question {

	private Fraction multFraction;
	
	public MultiplicationQuestion(){
		//Multiplication Question has a starting value of 30 coins.
		//The player will receive 30 coins for getting a question right on their first try, 
		//15 coins on their second, and no coins after that.
		this.coinValue = 30;
		this.coins = this.coinValue;
	}
	
	public Fraction generateQuestion(int difficulty) {
		// TODO: Make sure that the denominator is not 0 when generating random fraction'
		multFraction = new Fraction(1,1);
		int numerator = 0;
		int denominator = 0;
		Random r = new Random();
		
		switch(difficulty){
		case 1:
			int randMult = r.nextInt(3)+1;
			multFraction.setNumerator(randMult);
			multFraction.setDenominator(randMult);
			
			while(denominator <= 1)
				denominator = r.nextInt(5);
			while(numerator <= 0)
				numerator = r.nextInt(denominator);
			break;
		case 2:
			while(denominator <= 1)
				denominator = r.nextInt(5);
			while(numerator <= 0)
				numerator = r.nextInt(denominator);
			
			
				multFraction.setDenominator(r.nextInt(3)+1);
			
				multFraction.setNumerator(multFraction.getDenominator()*(r.nextInt(3)+1));
			break;
		case 3:
			while(denominator <= 2)
				denominator = r.nextInt(5);
			while(numerator <= 1)
				numerator = r.nextInt(denominator);
			
			while(multFraction.getDenominator() <= 1)
				multFraction.setDenominator(r.nextInt(5));
			while(multFraction.getNumerator() <= 0)
				multFraction.setNumerator(r.nextInt(multFraction.getDenominator()));
			break;
		case 4:
			while(denominator <= 3)
				denominator = r.nextInt(7);
			while(numerator <= 1)
				numerator = r.nextInt(denominator);
			
			while(multFraction.getDenominator() <= 3)
				multFraction.setDenominator(r.nextInt(7));
			while(multFraction.getNumerator() <= 1)
				multFraction.setNumerator(r.nextInt(multFraction.getDenominator()));
			break;
		default:
			break;
		}
		questionFraction = new Fraction(numerator, denominator);

		correctAnswer = new Fraction(numerator*multFraction.getNumerator(),denominator*multFraction.getDenominator());
		System.out.println(questionFraction);
		System.out.println(multFraction);
		
		System.out.println(correctAnswer);
		return multFraction;
		
	}
	public Fraction generateAnswer(int difficulty) {
		return correctAnswer; 
	}

	@Override
	public boolean checkAnswer(Fraction fraction) {
		
		return fraction.checkEquals(correctAnswer);
	}
	
	public int getCoinValue(){
		return coinValue;
	}

	@Override
	public Fraction generateOption(int difficulty) {
		int numerator = 0;
		int denominator = 0;
		int numerator2 = 0;
		int denominator2 = 0;
		Random r = new Random();
	
		switch(difficulty){
		case 1:
			int randMult = r.nextInt(3)+1;
			numerator2 =randMult;
			denominator2 =randMult;
			
			while(denominator <= 1)
				denominator = r.nextInt(5);
			while(numerator <= 0)
				numerator = r.nextInt(denominator);
			break;
		case 2:
			while(denominator <= 1)
				denominator = r.nextInt(5);
			while(numerator <= 0)
				numerator = r.nextInt(denominator);
			
			
				denominator2 =r.nextInt(3)+1;
			
				numerator2 =denominator2*(r.nextInt(3)+1);
			break;
		case 3:
			while(denominator <= 2)
				denominator = r.nextInt(5);
			while(numerator <= 1)
				numerator = r.nextInt(denominator);
			
			while(denominator2 <= 1)
				denominator2 =r.nextInt(5);
			while(numerator2 <= 0)
				numerator2 =r.nextInt(denominator2);
			break;
		case 4:
			while(denominator <= 3)
				denominator = r.nextInt(7);
			while(numerator <= 1)
				numerator = r.nextInt(denominator);
			
			while(denominator2 <= 3)
				denominator2 =r.nextInt(7);
			while(numerator2 <= 1)
				numerator2 =r.nextInt(denominator2);
			break;
		default:
			break;
		}
		
		Fraction tFrac = new Fraction(numerator*numerator2, denominator*denominator2);
		if(tFrac.checkEquals(correctAnswer)){
			tFrac = generateOption( difficulty);
		}
		
		System.out.println(tFrac);
		return tFrac;
	}
	
	public static void main(String[] args){
		MultiplicationQuestion  t = new MultiplicationQuestion();
		t.generateQuestion(4);
		t.generateOption(4);
	}

}
