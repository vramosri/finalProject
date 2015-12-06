package fractionGame;

import java.util.Random;

public class AdditionQuestion extends Question {
	
	private Fraction additionFraction;
	
	public AdditionQuestion(){
		this.coinValue = 20;
		this.coins = this.coinValue;
	}
	
	public Fraction generateQuestion(int difficulty) {
		// TODO: Make sure that the denominator is not 0 when generating random fraction
		int numerator = 0;
		int denominator = 0;
		int anumerator = 0;
		int adenominator = 0;
		Random r = new Random();
		switch(difficulty){
		case 1:
			while ((denominator == 0) || (denominator % 2 == 1)) {
				denominator = r.nextInt(difficulty * 4) + 1; 
			}
			while ((numerator == 0) || (numerator >= denominator)) {
				numerator = r.nextInt(difficulty * 4) + 1;
			}
			adenominator = denominator;
			while ((numerator + anumerator) > denominator || anumerator == 0) {
				anumerator = r.nextInt(difficulty * 4) + 1;
			}
			break;
		case 2:
			while ((denominator == 0) || (denominator < 4)) {
				denominator = r.nextInt(difficulty * 4 + 1);
			}
			while ((numerator == 0) || (numerator >= denominator)) {
				numerator = r.nextInt(difficulty * 4);
			}
			adenominator = denominator;
			while ((numerator + anumerator) > denominator || anumerator == 0) {
				anumerator = r.nextInt(difficulty * 4) + 1;
			}
			break;
		case 3:
			while ((denominator == 0) || (denominator % 2 == 1) || (denominator > 6)) {
				denominator = r.nextInt(difficulty * 4) + 1;
			}
			while ((numerator == 0) || (numerator >= denominator)) {
				numerator = r.nextInt(difficulty * 4) + 1;
			}
			while ((adenominator == 0) || (adenominator % 2 == 1) || (adenominator > 6)) {
				adenominator = r.nextInt(difficulty * 4) + 1;
			}
			while ((anumerator == 0) || (anumerator >= adenominator)) {
				anumerator = r.nextInt(difficulty * 4) + 1;
			}
			break;
		case 4:
			while ((denominator <= 1) || (denominator > 8)) {
				denominator = r.nextInt(difficulty * 4) + 1;
			}
			while ((numerator == 0) || (numerator >= denominator)) {
				numerator = r.nextInt(difficulty * 4);
			}
			while ((adenominator <= 1) || (adenominator > 8)) {
				adenominator = r.nextInt(difficulty * 4) + 1;
			}
			while ((anumerator == 0) || (anumerator >= adenominator)) {
				anumerator = r.nextInt(difficulty * 4) + 1;
			}
			break;
		}
		additionFraction = new Fraction(anumerator, adenominator);
		questionFraction = new Fraction(numerator, denominator); 
		return questionFraction;
	}

	
	public Fraction getAdditionFraction() {
		return additionFraction;
	}

	public Fraction generateAnswer(int difficulty) {
		int numerator = 0;
		int denominator = 0;
		Random r = new Random();
		switch(difficulty){
		case 1:
			numerator = questionFraction.getNumerator() + additionFraction.getNumerator();
			denominator = questionFraction.getDenominator();
			break;
		case 2:
			numerator = questionFraction.getNumerator() + additionFraction.getNumerator();
			denominator = questionFraction.getDenominator();
			break;
		case 3:
			numerator = (questionFraction.getNumerator() * additionFraction.getDenominator()) + (additionFraction.getNumerator() * questionFraction.getDenominator());
			denominator = questionFraction.getDenominator() * additionFraction.getDenominator();
			while(numerator % 2 == 0 && denominator % 2 == 0){
				numerator = numerator / 2;
				denominator = denominator / 2;
			}
			break;
		case 4:
			numerator = (questionFraction.getNumerator() * additionFraction.getDenominator()) + (additionFraction.getNumerator() * questionFraction.getDenominator());
			denominator = questionFraction.getDenominator() * additionFraction.getDenominator();
			break;
		default: break;
		}
		correctAnswer = new Fraction(numerator, denominator);
		return correctAnswer;

	}
	
	public Fraction generateOption(int difficulty) {
		int num;
		int den;

		Random randy = new Random();
		int random = randy.nextInt(5);

		num = correctAnswer.getNumerator();
		den = correctAnswer.getDenominator();
		switch (random) {
		case 0:
			num--;
			break;
		case 1:
			num++;
			break;
		case 2:
			num = num / 2;
			break;
		case 3:
			num = num * 2;
			break;
		case 4:
			num++;
			break;
		default:
			break;
		}

		return new Fraction(num, den);
	}

	@Override
	public boolean checkAnswer(Fraction fraction) {
		// TODO Auto-generated method stub
		if(correctAnswer.checkEquals(fraction))
			return true;
		else
			return false;
	}
	
	public int getCoinValue(){
		return coinValue;
	}
	
}
