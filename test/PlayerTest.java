package test;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import logic.Main;
import logic.Player;

public class PlayerTest {
	
	private Main main;
	private Player player;
	
	@Before
	public void init() {
		main = new Main();
		main.setPlayers(2);
		player = main.getPlayer(1);
	}

	@Test
	public void getPlayerTest() {
		assertEquals(player.getPlayerColor(), Color.RED);
	}
	
	@Test
	public void resetPlayerTest() {
		player.resetPlayer();
		assertTrue(player.getAlive());
	}

}
