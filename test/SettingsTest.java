package test;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import graphics.ButtonCreator;
import logic.Settings;

public class SettingsTest {
	
	Settings settings;
	
	@Before
	public void init() {
		settings = new Settings(null);
	}
	
	
	/** checks toggling on the boosts button **/
	@Test
	public void toggleBoostsTest() {
		ButtonCreator boostsButton = new ButtonCreator(250, 450, 400, 100, Color.GREEN, "Boosts On");
		boolean boostBool = settings.getBoost();
		settings.toggleBoosts(boostsButton);
		boostBool = settings.getBoost();
		assertFalse(boostBool);
		settings.toggleBoosts(boostsButton);
		boostBool = settings.getBoost();
		assertTrue(boostBool);
	}
	
	/** checks toggling on the number of players button **/
	@Test
	public void togglePlayersTest() {
		ButtonCreator playersButton = new ButtonCreator(275, 350, 350, 100, Color.YELLOW, "2 Players");
		int players = settings.getPlayers();
		assertEquals(2, players);
		settings.togglePlayers(playersButton);
		players = settings.getPlayers();
		assertEquals(3, players);
		settings.togglePlayers(playersButton);
		players = settings.getPlayers();
		assertEquals(4, players);
		settings.togglePlayers(playersButton);
		players = settings.getPlayers();
		assertEquals(2, players);
	}
	
	/** checks toggling on the difficulty button **/
	@Test
	public void toggleDifficultyTest() {
		ButtonCreator difficultyButton = new ButtonCreator(230, 550, 450, 100, Color.MAGENTA, "Easy");
		int difficulty = settings.getDifficulty();
		assertEquals(100, difficulty);
		settings.toggleDifficulty(difficultyButton);
		difficulty = settings.getDifficulty();
		assertEquals(70, difficulty);
		settings.toggleDifficulty(difficultyButton);
		difficulty = settings.getDifficulty();
		assertEquals(35, difficulty);
		settings.toggleDifficulty(difficultyButton);
		difficulty = settings.getDifficulty();
		assertEquals(100, difficulty);
	}
	
	/** Tests if restart is false **/
	@Test
	public void setRestartFalseTest() {
		settings.setRestart(false);
		assertFalse(settings.getRestart());
	}
	
	/** Tests if restart is true **/
	@Test
	public void setRestartTrueTest() {
		settings.setRestart(true);
		assertTrue(settings.getRestart());
	}
	
}