package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import graphics.CreditMenu;
import graphics.InstructionMenu;

public class MenuItemTest {
	
	private InstructionMenu instruct;
	private CreditMenu credit;

	@Before
	public void init() {
		instruct = new InstructionMenu(null);
		credit = new CreditMenu(null);
		instruct.createNewFrame();
	}

	
	@Test
	public void setPanelTest() {
		instruct.setPanel();
		assertNotNull(instruct.frame.getComponent(0));
	}
	
	@Test(expected = NullPointerException.class)
	public void setNullPanelTest() {
		credit.setPanel();
	}
	
	@Test
	public void showInstructionsTest() {
		instruct.showInstructions();
		assertTrue(instruct.frame.isVisible());
	}
	
	@Test
	public void showCreditsTest() {
		credit.createNewFrame();
		credit.showCredits();
		assertTrue(credit.frame.isVisible());
	}

}
