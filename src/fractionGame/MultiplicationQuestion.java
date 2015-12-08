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
		//When the difficulty is one, a random fraction * a form of one will be generated
		case 1:
			int randMult = r.nextInt(3)+1;
			multFraction.setNumerator(randMult);
			multFraction.setDenominator(randMult);
			
			while(denominator <= 1)
				denominator = r.nextInt(5);
			while(numerator <= 0)
				numerator = r.nextInt(denominator);
			break;
		//When the difficulty is two, a random fraction * a form of a multiplier will be generated
		case 2:
			while(denominator <= 1)
				denominator = r.nextInt(5);
			while(numerator <= 0)
				numerator = r.nextInt(denominator);
			
			
				multFraction.setDenominator(r.nextInt(3)+1);
			
				multFraction.setNumerator(multFraction.getDenominator()*(r.nextInt(3)+1));
			break;
		//When the difficulty is three, two random fraction will be generated
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
		//Same as difficulty three, just larger numbers
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
		
		//The correct answer is the product of the two generated numbers
		correctAnswer = new Fraction(numerator*multFraction.getNumerator(),denominator*multFraction.getDenominator());
		
		return multFraction;
		
	}
	public Fraction generateAnswer(int difficulty) {
		//Just returns answer as its generated in the question generation.
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
		//Does the same thing as generateQuestion, generating two random fractions and returning that fraction
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
		
		//Check that the returned option is not the same as the answer
		if(tFrac.checkEquals(correctAnswer)){
			tFrac = generateOption( difficulty);
		}
		
		return tFrac;
	}

}
