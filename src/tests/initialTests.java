package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fractionGame.*;


public class initialTests {
	
	private Fraction testFraction;
	private MatchingQuestion testQuestion1;
	private AdditionQuestion testQuestion2;
	private MultiplicationQuestion testQuestion3;
	private Player testPlayer;
	private GameGUI testGame;	
	
	@Before
	public void initialize(){
		//Initializes player, a question, and a fraction for testing purposes.
		testFraction = new Fraction(1, 2);
		testQuestion1 = new MatchingQuestion();
		testQuestion1.setCorrectAnswer(testFraction);
		testQuestion2 = new AdditionQuestion();
		testQuestion3 = new MultiplicationQuestion();
		testPlayer = new Player();
	}

	@Test
	public void testCheckEquals() {
		//Tests that equal fractions are equal
		assertTrue(testFraction.checkEquals(new Fraction(1, 2)));
		assertTrue(testFraction.checkEquals(new Fraction(2, 4)));
		assertFalse(testFraction.checkEquals(new Fraction(1, 3)));
	}
	
	@Test
	public void testCheckAnswer(){
		//Tests that the selected option is equivalent to the answer.
		testQuestion1.setCorrectAnswer(testFraction);
		assertTrue(testQuestion1.checkAnswer(new Fraction(1, 2)));
		assertTrue(testQuestion1.checkAnswer(new Fraction(2, 4)));
		assertFalse(testQuestion1.checkAnswer(new Fraction(1, 3)));
	}
	
	@Test
	public void testGiveCoins(){
		//Tests that player receives the correct amount of coins given the question.
		testQuestion1.setCoins(10);
		testPlayer.setCoins(0);
		testPlayer.addCoins(testQuestion1.getCoins());
		assertEquals(testPlayer.getCoins(), 10);
		testPlayer.setCoins(0);
		testPlayer.addCoins(testQuestion2.getCoins());
		assertEquals(testPlayer.getCoins(), 20);
		testPlayer.setCoins(0);
		testPlayer.addCoins(testQuestion3.getCoins());
		assertEquals(testPlayer.getCoins(), 30);
	}
	
	@Test
	public void testReduceCoins(){
		//Tests that question rewards half coins for one wrong answer, and no coins for two wrong answers.
		int coins = testQuestion1.getCoinValue();
		testQuestion1.setCoins(10);
		testQuestion1.reduceCoins();
		assertEquals(testQuestion1.getCoins(), (coins / 2));
		testQuestion1.reduceCoins();
		assertEquals(testQuestion1.getCoins(), 0);
	}
	
	@Test
	public void testWrongAnswer(){
		//Tests that one wrong answer reduces coins rewarded by half.
		int coins = testQuestion1.getCoinValue();
		testQuestion1.setCoins(10);
		testQuestion1.checkAnswer(new Fraction(1, 3));
		assertEquals(testQuestion1.getCoins(), (coins / 2));
	}
	
	@Test
	public void testMatchingQuestionGeneration(){
		//Tests that random matching questions are generated correctly.
		Fraction f = new Fraction(1, 1);
		for(int i = 0; i < 100; i++){
			f = testQuestion1.generateQuestion(1);
			assertTrue(f.getNumerator() <= f.getDenominator());
		}
		for(int i = 0; i < 100; i++){
			f = testQuestion1.generateQuestion(2);
			assertTrue(f.getNumerator() <= f.getDenominator());
		}
		for(int i = 0; i < 100; i++){
			f = testQuestion1.generateQuestion(3);
			assertTrue(f.getNumerator() <= f.getDenominator());
		}
		for(int i = 0; i < 100; i++){
			f = testQuestion1.generateQuestion(4);
			assertTrue(f.getNumerator() <= f.getDenominator());
		}
	}
	
	@Test
	public void testMatchingAnswerGeneration(){
		//Tests that matching questions generate correct answers.
		for(int i = 0; i < 100; i++){
			testQuestion1.generateQuestion(1);
			Fraction f = testQuestion1.generateAnswer(1);
			//System.out.println("Generated Question: " + testQuestion1.getQuestionFraction().getNumerator() + "/" + testQuestion1.getQuestionFraction().getDenominator());
			//System.out.println("Generated Answer: " + testQuestion1.getCorrectAnswer().getNumerator() + "/" + testQuestion1.getCorrectAnswer().getDenominator() + "\n");
			assertTrue(testQuestion1.checkAnswer(f));
		}
		for(int i = 0; i < 100; i++){
			testQuestion1.generateQuestion(2);
			Fraction f = testQuestion1.generateAnswer(2);
			//System.out.println("Generated Question: " + testQuestion1.getQuestionFraction().getNumerator() + "/" + testQuestion1.getQuestionFraction().getDenominator());
			//System.out.println("Generated Answer: " + testQuestion1.getCorrectAnswer().getNumerator() + "/" + testQuestion1.getCorrectAnswer().getDenominator() + "\n");
			assertTrue(testQuestion1.checkAnswer(f));
		}
		for(int i = 0; i < 100; i++){
			testQuestion1.generateQuestion(3);
			Fraction f = testQuestion1.generateAnswer(3);
			//System.out.println("Generated Question: " + testQuestion1.getQuestionFraction().getNumerator() + "/" + testQuestion1.getQuestionFraction().getDenominator());
			//System.out.println("Generated Answer: " + testQuestion1.getCorrectAnswer().getNumerator() + "/" + testQuestion1.getCorrectAnswer().getDenominator() + "\n");
			assertTrue(testQuestion1.checkAnswer(f));
		}
		for(int i = 0; i < 100; i++){
			testQuestion1.generateQuestion(4);
			Fraction f = testQuestion1.generateAnswer(4);
			//System.out.println("Generated Question: " + testQuestion1.getQuestionFraction().getNumerator() + "/" + testQuestion1.getQuestionFraction().getDenominator());
			//System.out.println("Generated Answer: " + testQuestion1.getCorrectAnswer().getNumerator() + "/" + testQuestion1.getCorrectAnswer().getDenominator() + "\n");
			assertTrue(testQuestion1.checkAnswer(f));
		}
	}

	@Test
	public void testMatchingOptionGeneration() {
		//Tests matching questions generate incorrect options.
		for(int i = 0; i< 100; i++){
			testQuestion1.generateQuestion(1);
			Fraction answer = testQuestion1.generateAnswer(1);
			assertFalse(testQuestion1.generateOption(1).checkEquals(testQuestion1.getCorrectAnswer()));
		}
		for(int i = 0; i< 100; i++){
			testQuestion1.generateQuestion(2);
			Fraction answer = testQuestion1.generateAnswer(2);
			assertFalse(testQuestion1.generateOption(2).checkEquals(testQuestion1.getCorrectAnswer()));
		}
		for(int i = 0; i< 100; i++){
			testQuestion1.generateQuestion(3);
			Fraction answer = testQuestion1.generateAnswer(3);
			assertFalse(testQuestion1.generateOption(4).checkEquals(testQuestion1.getCorrectAnswer()));
		}
		for(int i = 0; i< 100; i++){
			testQuestion1.generateQuestion(4);
			Fraction answer = testQuestion1.generateAnswer(4);
			assertFalse(testQuestion1.generateOption(4).checkEquals(testQuestion1.getCorrectAnswer()));
		}
	}
	
	@Test
	public void testAdditionQuestionGeneration() {
		//Tests that random addition questions are generated correctly
		Fraction f1 = new Fraction(1, 1);
		Fraction f2 = new Fraction(1, 1);
		for(int i = 0; i < 100; i++){
			f1 = testQuestion2.generateQuestion(1);
			f2 = testQuestion2.getAdditionFraction();
			assertTrue(f1.getNumerator() <= f1.getDenominator());
			assertTrue(f2.getNumerator() <= f2.getDenominator());
		}
		for(int i = 0; i < 100; i++){
			f1 = testQuestion2.generateQuestion(2);
			f2 = testQuestion2.getAdditionFraction();
			assertTrue(f1.getNumerator() <= f1.getDenominator());
			assertTrue(f2.getNumerator() <= f2.getDenominator());
		}
		for(int i = 0; i < 100; i++){
			f1 = testQuestion2.generateQuestion(3);
			f2 = testQuestion2.getAdditionFraction();
			assertTrue(f1.getNumerator() <= f1.getDenominator());
			assertTrue(f2.getNumerator() <= f2.getDenominator());
		}
		for(int i = 0; i < 100; i++){
			f1 = testQuestion2.generateQuestion(4);
			f2 = testQuestion2.getAdditionFraction();
			assertTrue(f1.getNumerator() <= f1.getDenominator());
			assertTrue(f2.getNumerator() <= f2.getDenominator());
		}
	}
	
	@Test
	public void testAdditionAnswerGeneration() {
		//Tests that addition questions generate correct answers.
		for(int i = 0; i < 100; i++){
			testQuestion2.generateQuestion(1);
			Fraction f = testQuestion2.generateAnswer(1);
			//System.out.println("Generated Question: " + testQuestion2.getQuestionFraction().getNumerator() + "/" + testQuestion2.getQuestionFraction().getDenominator() + "+" + testQuestion2.getAdditionFraction().getNumerator() + "/" + testQuestion2.getAdditionFraction().getDenominator());
			//System.out.println("Generated Answer: " + testQuestion2.getCorrectAnswer().getNumerator() + "/" + testQuestion2.getCorrectAnswer().getDenominator() + "\n");
			assertTrue(testQuestion2.checkAnswer(f));
		}
		for(int i = 0; i < 100; i++){
			testQuestion2.generateQuestion(2);
			Fraction f = testQuestion2.generateAnswer(2);
			//System.out.println("Generated Question: " + testQuestion2.getQuestionFraction().getNumerator() + "/" + testQuestion2.getQuestionFraction().getDenominator() + "+" + testQuestion2.getAdditionFraction().getNumerator() + "/" + testQuestion2.getAdditionFraction().getDenominator());
			//System.out.println("Generated Answer: " + testQuestion2.getCorrectAnswer().getNumerator() + "/" + testQuestion2.getCorrectAnswer().getDenominator() + "\n");
			assertTrue(testQuestion2.checkAnswer(f));
		}
		for(int i = 0; i < 100; i++){
			testQuestion2.generateQuestion(3);
			Fraction f = testQuestion2.generateAnswer(3);
			//System.out.println("Generated Question: " + testQuestion2.getQuestionFraction().getNumerator() + "/" + testQuestion2.getQuestionFraction().getDenominator() + "+" + testQuestion2.getAdditionFraction().getNumerator() + "/" + testQuestion2.getAdditionFraction().getDenominator());
			//System.out.println("Generated Answer: " + testQuestion2.getCorrectAnswer().getNumerator() + "/" + testQuestion2.getCorrectAnswer().getDenominator() + "\n");
			assertTrue(testQuestion2.checkAnswer(f));
		}
		for(int i = 0; i < 100; i++){
			testQuestion2.generateQuestion(4);
			Fraction f = testQuestion2.generateAnswer(4);
			//System.out.println("Generated Question: " + testQuestion2.getQuestionFraction().getNumerator() + "/" + testQuestion2.getQuestionFraction().getDenominator() + "+" + testQuestion2.getAdditionFraction().getNumerator() + "/" + testQuestion2.getAdditionFraction().getDenominator());
			//System.out.println("Generated Answer: " + testQuestion2.getCorrectAnswer().getNumerator() + "/" + testQuestion2.getCorrectAnswer().getDenominator() + "\n");
			assertTrue(testQuestion2.checkAnswer(f));
		}
	}
	
	@Test
	public void testAdditionOptionGeneration() {
		//Tests that addition questions generate incorrect options.
		for(int i = 0; i< 100; i++){
			testQuestion2.generateQuestion(1);
			Fraction answer = testQuestion2.generateAnswer(1);
			assertFalse(testQuestion2.generateOption(1).checkEquals(testQuestion2.getCorrectAnswer()));
		}
		for(int i = 0; i< 100; i++){
			testQuestion2.generateQuestion(2);
			Fraction answer = testQuestion2.generateAnswer(2);
			assertFalse(testQuestion2.generateOption(2).checkEquals(testQuestion2.getCorrectAnswer()));
		}
		for(int i = 0; i< 100; i++){
			testQuestion2.generateQuestion(3);
			Fraction answer = testQuestion2.generateAnswer(3);
			assertFalse(testQuestion2.generateOption(4).checkEquals(testQuestion2.getCorrectAnswer()));
		}
		for(int i = 0; i< 100; i++){
			testQuestion2.generateQuestion(4);
			Fraction answer = testQuestion2.generateAnswer(4);
			assertFalse(testQuestion2.generateOption(4).checkEquals(testQuestion2.getCorrectAnswer()));
		}
	}
}