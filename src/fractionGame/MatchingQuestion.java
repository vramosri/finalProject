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
				denominator = r.nextInt(difficulty * 4 +1); // is the 1 supposed to be inside 
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
			while ((numerator == 0) || (numerator > denominator - 2)) {
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
		questionFraction = new Fraction(numerator, denominator); 
		return questionFraction;
	}
	
	public Fraction generateAnswer(int difficulty) {
		int numerator = 0;
		int denominator = 0;
		Random r = new Random();
		switch(difficulty){
		case 1:
			if((questionFraction.getNumerator() % 2 == 0) && (questionFraction.getDenominator() % 2 == 0)){
				numerator = questionFraction.getNumerator() / 2;
				denominator = questionFraction.getDenominator() / 2;
			}
			else{
				numerator = questionFraction.getNumerator() * 2;
				denominator = questionFraction.getDenominator() * 2;
			}
			break;
		case 2:
			if((questionFraction.getNumerator() % 2 == 0) && (questionFraction.getDenominator() % 2 == 0)){
				numerator = questionFraction.getNumerator() / 2;
				denominator = questionFraction.getDenominator() / 2;
			}
			else{
				numerator = questionFraction.getNumerator() * 2;
				denominator = questionFraction.getDenominator() * 2;
			}
			break;
		case 3:
			while(((double) numerator / denominator < (double) questionFraction.getNumerator() / questionFraction.getDenominator())){
				numerator = (questionFraction.getNumerator() + r.nextInt(difficulty));
				denominator = questionFraction.getDenominator();
			}
			break;
		case 4:
			denominator = 1;
			while(((double) numerator / denominator < (double) questionFraction.getNumerator() / questionFraction.getDenominator()) || (numerator > denominator + 2)){
				numerator = r.nextInt(difficulty * 4);
				denominator = r.nextInt(difficulty * 4) + 1;
			}
			break;
		default: break;
		}
		correctAnswer = new Fraction(numerator, denominator);
		return correctAnswer;
	}

	public Fraction generateOption(int difficulty)
	{
		int num = correctAnswer.getNumerator();
		int den = correctAnswer.getDenominator(); 
	
		Random randy = new Random();
		int random = randy.nextInt(5);
		
		if (difficulty == 1 || difficulty == 2) {
			switch (random) {
			case 0:
				den = den + 1;
				num = num + 2;
				break;
			case 1:
				if (num == den) {
					den += 2;
				} else {
					int temp = den;
					den = num;
					num = temp;
				}
				break;
			case 2:
				if (den % 2 == 0 && den != 2) {
					den = den / 2;
				} else {
					den++;
				}
				break;
			case 3:
				den = den * 2;
				num = num * 3;
				break;
			case 4:
				num = 1;
				den++;
				break;
			default:
				break;
			}
		}
		else{
			switch (random) {
			case 0:
				if(num == den)
					num = 1;
				else{
					num = questionFraction.getNumerator();
					den = questionFraction.getDenominator();
				}
				break;
			case 1:
				if(num > 1)
					num--;
				else
					den++;
				break;
			case 2:
				if(num > 1)
					num--;
				den++;
				break;
			case 3:
				if(num == den)
					num = 1;
				else{
					num = questionFraction.getNumerator();
					den = questionFraction.getDenominator();
				}
				break;
			case 4:
				den += 2;
				break;
			default:
				break;
			}
		}
		
		//System.out.println(random);
		//System.out.println("Question: " + questionFraction.getNumerator() + "/" + questionFraction.getDenominator());
		//System.out.println("Answer: " + correctAnswer.getNumerator() + "/" + correctAnswer.getDenominator());
		//System.out.println("Option: " + num + "/" + den);
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
