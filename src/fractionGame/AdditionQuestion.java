package fractionGame;

public class AdditionQuestion extends Question {

	
	public AdditionQuestion(){
		this.coinValue = 30;
		this.coins = this.coinValue;
	}
	
	public String generateQuestion() {
		// TODO: Make sure that the denominator is not 0 when generating random fraction
		return ""; 
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
		else
			return false;
	}
	
	public int getCoinValue(){
		return 0;
	}
	
}
