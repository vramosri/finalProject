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
		String string  = " " + Numerator + "/" + Denominator;
		return string; 
	} 
	
	public boolean checkEquals(Fraction fraction){
		
		double division = (double) Numerator/Denominator; 
		double secondD = (double) fraction.getNumerator()/fraction.getDenominator();
		
		if (division == secondD)
		{
			return true; 
		}
		else 
		{
			return false; 
		}
	}

	public int getNumerator() {
		return Numerator;
	}

	public void setNumerator(int numerator) {
		Numerator = numerator;
	}

	public int getDenominator() {
		return Denominator;
	}

	public void setDenominator(int denominator) {
		Denominator = denominator;
	}
	
	
	
}
