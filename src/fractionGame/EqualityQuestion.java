package fractionGame;

public class EqualityQuestion extends Question {

	
	public EqualityQuestion(){
		this.coinValue = 20;
		this.coins = this.coinValue;
	}
	
	public Fraction generateQuestion(int difficulty) {
		// TODO: Make sure that the denominator is not 0 when generating random fraction
		return null; 
	}
	public Fraction generateAnswer() {
		Fraction fra = new Fraction(1,2); 
		return fra; 
	}

	@Override
	public boolean checkAnswer(Fraction fraction) {
		
		return false;
	}
	
	public int getCoinValue(){
		return coinValue;
	}
	
}
