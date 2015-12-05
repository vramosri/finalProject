package fractionGame;

public class AdditionQuestion extends Question {

	
	public AdditionQuestion(){
		this.coinValue = 20;
		this.coins = this.coinValue;
	}
	
	public Fraction generateQuestion(int difficulty) {
		// TODO: Make sure that the denominator is not 0 when generating random fraction
		return null; 
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
		else
			return false;
	}
	
	public int getCoinValue(){
		return coinValue;
	}

	@Override
	public Fraction generateOption(int difficulty) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
