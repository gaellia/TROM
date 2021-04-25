package graphics;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import logic.StartMenu;

public class InstructionMenu extends MenuItem {
	
	public InstructionMenu(StartMenu initStartMenu) {
		super(initStartMenu, "<html><br><br><br>Player 1- Up-&#8593; Left-&#8592; Down-&#8595 Right-&#8594<br><br>Player 2- Up-W Left-A Down-S Right-D<br><br>Player 3- Up-U Left-H Down-J Right-K <br><br>Player 4- Up-8 Left-4 Down-5 Right-6 </html>",
				600, 500, 100, 150);
	}

	public void showInstructions(){

        // Create JPanel and JLabel to add logo
		JPanel panel = (JPanel) frame.getContentPane();
        panel.setLayout(null);
        JLabel img = new JLabel(new ImageIcon("assets/INSTRUCTIONS.png"));
        panel.add(img);
        Dimension size = img.getMaximumSize();
        img.setBounds(170, 40, size.width, size.height);

        frame.add(img);
        frame.setVisible(true);
	}
}
