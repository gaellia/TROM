package graphics;


import logic.StartMenu;

public class CreditMenu extends MenuItem {
	
    public CreditMenu(StartMenu initStartMenu) {
		super(initStartMenu, "<html>This game was created by:<br><br> Denis Shevchenko<br><br> Joseph Besenski<br><br>Jason Lyster<br><br>Daniel Sohn<br><br> Kevin Vo </html>",
				400, 600, 100, 100);
	}
    
    public void showCredits() {
        frame.setLayout(null);
        frame.setVisible(true);
    }

}
