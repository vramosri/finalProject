package fractionGame;

import java.util.Random;

public class MatchingQuestion extends Question {
	
	public MatchingQuestion(){
		//Matching Question has a starting value of 10 coins.
		//The player will receive 10 coins for getting a question right on their first try, 
		//5 coins on their second, and no coins after that.
		this.coinValue = 10;
		this.coins = this.coinValue;
	}
	
	public Fraction generateQuestion(int difficulty) {
		// TODO: Make sure that the denominator is not 0 when generating random fraction
		int numerator = 0;
		int denominator = 0;
		Random r = new Random();
		//The random fraction generated to be used in the question is dependent on the difficulty level.
		switch(difficulty){
		case 1:
			//Difficulty Level 1 will generate even denominator fractions with a maximum denominator of 4.
			while ((denominator == 0) || (denominator % 2 == 1)) {
				denominator = r.nextInt(difficulty * 4) + 1; 
			}
			while ((numerator == 0) || (numerator > denominator)) {
				numerator = r.nextInt(difficulty * 4) + 1;
			}
			break;
		case 2:
			//Difficulty Level 2 will generate fractions with a denominator between 4 and 8 inclusive.
			while ((denominator == 0) || (denominator < 4)) {
				denominator = r.nextInt(difficulty * 4 + 1);
			}
			while ((numerator == 0) || (numerator > denominator)) {
				numerator = r.nextInt(difficulty * 4);
			}
			break;
		case 3:
			//Difficulty Level 3 will generate even denominator questions with a denominator between 8 and 12 inclusive.
			while ((denominator == 0) || (denominator % 2 == 1) || (denominator < 8)) {
				denominator = r.nextInt(difficulty * 4) + 1;
			}
			while ((numerator <= 1) || (numerator > denominator - 2)) {
				numerator = r.nextInt(difficulty * 4) + 1;
			}
			break;
		case 4:
			//Difficulty Level 4 will generate fractions with a denominator between 10 and 16 inclusive.
			while ((denominator == 0) || (denominator < 10)) {
				denominator = r.nextInt(difficulty * 4) + 1;
			}
			while ((numerator <= 1) || (numerator > denominator)) {
				numerator = r.nextInt(difficulty * 4);
			}
			break;
		}
		//questionFraction is the Fraction given to the text display in order to display the problem.
		questionFraction = new Fraction(numerator, denominator); 
		return questionFraction;
	}
	
	public Fraction generateAnswer(int difficulty) {
		int numerator = 0;
		int denominator = 0;
		Random r = new Random();
		//An answer is generated based on the random fraction from the previous function, as well as the difficulty.
		switch(difficulty){
		case 1:
			//Difficulty level 1 and 2 both generate a fraction equivalent to the question fraction.
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
			//Difficulty level 3 and 4 generate some answer greater than the question fraction (even mixed fractions with a value > 1)
			denominator = 1;
			while(((double) numerator / denominator <= (double) questionFraction.getNumerator() / questionFraction.getDenominator())){
				numerator = (questionFraction.getNumerator() + r.nextInt(difficulty));
				denominator = questionFraction.getDenominator();
			}
			break;
		case 4:
			denominator = 1;
			while(((double) numerator / denominator <= (double) questionFraction.getNumerator() / questionFraction.getDenominator()) || (numerator > denominator + 2)){
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
		//Generate option generates an incorrect answer, choosing randomly from the options below.
		//Each option should be incorrect regardless of the question fraction, as shown in the JUnit test.
		int num;
		int den;
	
		Random randy = new Random();
		int random = randy.nextInt(5);
		
		if (difficulty == 1 || difficulty == 2) {
			num = correctAnswer.getNumerator();
			den = correctAnswer.getDenominator(); 
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
			num = questionFraction.getNumerator();
			den = questionFraction.getDenominator();
			switch (random) {
			case 0:
				if(num == den)
					num = 1;
				else{
					num--;
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
				while(num == questionFraction.getNumerator() || num <= 0){
					num = (questionFraction.getNumerator() - randy.nextInt(difficulty));
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
