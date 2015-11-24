package fractionGame;

public class MatchingQuestion extends Question {

	
	public MatchingQuestion(){
		
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
		return false;
	}
	
	public int getCoinValue(){
		return 0;
	}
	
}
