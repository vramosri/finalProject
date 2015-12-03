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
	private ProgressBar testBar;
	
	
	@Before
	public void initialize(){
		//Initializes player, a question, and a fraction for testing purposes.
		testFraction = new Fraction(1, 2);
		testQuestion1 = new MatchingQuestion();
		testQuestion1.setCorrectAnswer(testFraction);
		testQuestion2 = new AdditionQuestion();
		testQuestion3 = new MultiplicationQuestion();
		testPlayer = new Player();
		testGame = new GameGUI();
		testBar = new ProgressBar();
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
		assertEquals(testPlayer.getCoins(), 30);
		testPlayer.setCoins(0);
		testPlayer.addCoins(testQuestion3.getCoins());
		assertEquals(testPlayer.getCoins(), 20);
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
	public void testProgressBar(){
		//Tests that the progress bar correctly keeps track of player's progress.
		assertEquals(testBar.getProgress(), 0);
		testGame.changeScene(testBar);
		assertEquals(testBar.getProgress(), 1);
	}

}