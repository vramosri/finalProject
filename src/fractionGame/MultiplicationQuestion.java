package fractionGame;

import java.util.Random;

public class MultiplicationQuestion extends Question {

	private Fraction multFraction;
	
	public MultiplicationQuestion(){
		this.coinValue = 30;
		this.coins = this.coinValue;
	}
	
	public Fraction generateQuestion(int difficulty) {
		// TODO: Make sure that the denominator is not 0 when generating random fraction'
		multFraction = new Fraction(1,1);
		int numerator = 0;
		int denominator = 0;
		Random r = new Random();
		difficulty+=2;
		
		while(denominator <= 0 || multFraction.getDenominator() <= 0){
			int randMult = 1;
			while(randMult < 2){
				randMult = r.nextInt(difficulty);
			}
			
			int randNum = r.nextInt(difficulty*randMult);
			multFraction.setDenominator(randNum);
			
			randNum =  r.nextInt(difficulty*randMult);
			denominator = randNum;
		}
		
		numerator = r.nextInt(denominator);
		multFraction.setNumerator(r.nextInt(multFraction.getDenominator()));
		
		questionFraction = new Fraction(numerator, denominator);
		
		correctAnswer = new Fraction(numerator*multFraction.getNumerator(),denominator*multFraction.getDenominator());
		
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
		Random r = new Random();
		difficulty+=2;
		
		while(denominator <= 0){
			int randMult = 1;
			while(randMult < 2){
				randMult = r.nextInt(difficulty);
			}
			
			int randNum = r.nextInt(difficulty*randMult);
			denominator = randNum;
			
		}
		numerator = r.nextInt(denominator);
		
		int numerator2 = 0;
		int denominator2 = 0;
		
		while(denominator2 <= 0){
			int randMult = 1;
			while(randMult < 2){
				randMult = r.nextInt(difficulty);
			}
			
			int randNum = r.nextInt(difficulty*randMult);
			denominator2 = randNum;
			
		}
		numerator2 = r.nextInt(denominator2);
		
		Fraction tFrac = new Fraction(numerator*numerator2, denominator*denominator2);
		if(tFrac.checkEquals(correctAnswer)){
			tFrac = generateOption( difficulty);
		}
		
		
		return tFrac;
	}
	
	public static void main(String[] args){
		MultiplicationQuestion  t = new MultiplicationQuestion();
		t.generateQuestion(2);
		t.generateOption(2);
	}

}
