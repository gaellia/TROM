package test;

import static org.junit.Assert.*;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.junit.Before;
import org.junit.Test;

import graphics.RestartScreen;
import graphics.Window;
import logic.Settings;

public class RestartScreenTest {
	
	private RestartScreen restart;
	private Settings settings;
	
	@Before
	public void init() {
		settings = new Settings(null);
		settings.setRestart(true);
		restart = new RestartScreen(null, 0, settings);
	}
	
	
	@Test
	public void restartGameFalseTest() {
		// Press No when window pops up
		assertFalse(restart.restartGame());
	}
	
	@Test
	public void openFontTest() {
		Font font = restart.openFont();
		assertFalse(font.isBold());
	}
	
	@Test(expected = AssertionError.class)
	public void openFontNullTest() {
		// when the filepath to the font doesn't exist
		Font font = restart.openFont();
		assertTrue(font.isBold());
	}
	
	// This tests if the panel text was added
	@Test
	public void setPanelTextTest() {
		Window window = new Window(0, 0, null);
		JPanel panel = new JPanel();
		JLabel text = new JLabel("Test");
		restart.setPanelText(window, panel, text, 0, 0, 0, 0);
		assertNotNull(window.getComponent(0));
	}
	
}
