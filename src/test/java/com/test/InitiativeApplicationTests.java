package com.test;

import com.test.domain.Condition;
import com.test.domain.Round;
import com.test.domain.RoundEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InitiativeApplicationTests {

	private Round round;

	@Test
	public void contextLoads() {
	}

	@Before
	public void setup(){
		round = new Round();
	}

	@Test
	public void testlistadding(){
		round.addRoundEntity(new RoundEntity());
		assertFalse(round.getRoundList().size() == 0);
	}

	@Test
	public void testRoundSortingByInitiativeRoll(){
		RoundEntity first = new RoundEntity("First",10,0,10);
		RoundEntity second = new RoundEntity("Second",11,0,10);

		round.addRoundEntity(first);
		round.addRoundEntity(second);
		round.sortRoundByInitiative();
		List<RoundEntity> roundList = round.getRoundList();
		assertTrue(second.equals(roundList.get(0)));
		assertTrue(first.equals(roundList.get(1)));
	}

	@Test
	public void testRoundSortingByInitiativeModifier(){
		RoundEntity first = new RoundEntity("First",10,1,10);
		RoundEntity second = new RoundEntity("Second",11,0,10);

		round.addRoundEntity(first);
		round.addRoundEntity(second);
		round.sortRoundByInitiative();
		List<RoundEntity> roundList = round.getRoundList();
		assertTrue(first.equals(roundList.get(0)));
		assertTrue(second.equals(roundList.get(1)));
	}

	@Test
	public void testRoundSortingComplexExample(){
		RoundEntity first = new RoundEntity("First",20,0,10);
		RoundEntity second = new RoundEntity("Second",17,2,10);
		RoundEntity third = new RoundEntity("Third",18,1,10);
		RoundEntity fourth = new RoundEntity("Fourth",19,0,10);
		RoundEntity fifth = new RoundEntity("Fifth",0,17,10);

		round.addRoundEntity(first);
		round.addRoundEntity(second);
		round.addRoundEntity(third);
		round.addRoundEntity(fourth);
		round.addRoundEntity(fifth);
		round.sortRoundByInitiative();

		List<RoundEntity> roundList = round.getRoundList();
		assertTrue(first.equals(roundList.get(0)));
		assertTrue(second.equals(roundList.get(1)));
		assertTrue(third.equals(roundList.get(2)));
		assertTrue(fourth.equals(roundList.get(3)));
		assertTrue(fifth.equals(roundList.get(4)));
	}

	@Test
	public void testAddingDuplicates(){
		RoundEntity first = new RoundEntity("First",20,0,10);
		RoundEntity second = new RoundEntity("First",17,2,10);

		round.addRoundEntity(first);
		round.addRoundEntity(second);
		assertEquals(1,round.getRoundList().size());
		assertEquals(17,(int) round.getRoundList().get(0).getInitiativeRoll());
	}

	@Test
	public void testNextTurn(){
		RoundEntity first = new RoundEntity("First",20,0,10);
		RoundEntity second = new RoundEntity("Second",17,2,10);
		RoundEntity third = new RoundEntity("Third",17,2,10);

		round.addRoundEntity(first);
		round.addRoundEntity(second);
		round.addRoundEntity(third);
		round.sortRoundByInitiative();

		assertTrue(first.equals(round.getCurrentRoundEntity()));
		round.nextTurn();
		assertTrue(second.equals(round.getCurrentRoundEntity()));
		round.nextTurn();
		assertTrue(third.equals(round.getCurrentRoundEntity()));
	}

	@Test
	public void testRoundCounter(){
		RoundEntity first = new RoundEntity("First",20,0,10);
		RoundEntity second = new RoundEntity("Second",17,2,10);

		round.addRoundEntity(first);
		round.addRoundEntity(second);
		round.sortRoundByInitiative();

		round.nextTurn();
		assertEquals(0,round.getRoundCounter());
		round.nextTurn();
		assertEquals("Round counter should be 1",1,round.getRoundCounter());
		round.nextTurn();
		round.nextTurn();
		assertEquals("Round counter should be 2",2,round.getRoundCounter());

	}

	@Test
	public void testAddingEntityMidRound(){
		RoundEntity first = new RoundEntity("First",20,0,10);
		RoundEntity second = new RoundEntity("Second",17,2,10);
		RoundEntity third = new RoundEntity("Third",18,1,10);
		RoundEntity fourth = new RoundEntity("Fourth",21,1,10);

		round.addRoundEntity(first);
		round.addRoundEntity(second);
		round.addRoundEntity(third);
		round.sortRoundByInitiative();

		assertTrue(first.equals(round.getCurrentRoundEntity()));
		round.nextTurn();
		assertEquals("Counter should be 0",0,round.getRoundCounter());
		round.addRoundEntityToBuffer(fourth);
		assertTrue(second.equals(round.getCurrentRoundEntity()));
		round.nextTurn();
		assertTrue(third.equals(round.getCurrentRoundEntity()));
		round.nextTurn();
		assertTrue("should be " + fourth.getName() + " but is " + round.getCurrentRoundEntity().getName(), fourth.equals(round.getCurrentRoundEntity()));
		assertEquals("Counter should be 1" , 1 , round.getRoundCounter());
		round.nextTurn();
		assertTrue("should be " + first.getName() + " but is " + round.getCurrentRoundEntity().getName(), first.equals(round.getCurrentRoundEntity()));
		assertEquals("Counter should still be 1",1,round.getRoundCounter());

	}

	@Test
	public void testConditiondecrease(){
		RoundEntity first = new RoundEntity("first",0,0,0);
		round.addRoundEntity(first);
		Condition condition = new Condition(2,"a condition");
		round.getCurrentRoundEntity().addCondition(condition);
		assertEquals("Condition amount of turns should be 2" ,2,round.getCurrentRoundEntity().getConditions().get(0).getNumberOfTurns());
		round.nextTurn();
		assertEquals("Condition amount of turns should be 1" , 1 , round.getCurrentRoundEntity().getConditions().get(0).getNumberOfTurns());
		round.nextTurn();
		assertEquals("Condition list should be empty now",0, round.getCurrentRoundEntity().getConditions().size());
	}



}
