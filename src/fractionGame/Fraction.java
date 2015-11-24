package fractionGame;

public class Fraction {

	private int Numerator; 
	private int Denominator;
	
	public Fraction (int num, int den){
		Numerator = num; 
		Denominator = den; 
	}
	
	@Override
	public String toString() {
		return "Fraction [Numerator=" + Numerator + ", Denominator=" + Denominator + "]";
	} 
	
	public boolean checkEquals(Fraction fraction){
		return true; 
	}
	
	
	
}
