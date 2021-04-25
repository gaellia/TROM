package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import logic.Main;
import logic.StartMenu;

public class MenuItem {
	
	private StartMenu startMenu;
	private String text;
	private int panelSizeX;
	private int panelSizeY;
	private int panelLocationX;
	private int panelLocationY;
	
	public Window frame;
	
	public MenuItem(StartMenu initStartMenu, String initText, int initPanelSizeX, int initPanelSizeY, int initPanelLocationX,  int initPanelLocationY) {
	    startMenu = initStartMenu;
	    text = initText;
	    panelSizeX = initPanelSizeX;
	    panelSizeY = initPanelSizeY;
	    panelLocationX = initPanelLocationX;
	    panelLocationY = initPanelLocationY;
	}
	
	public void createNewFrame() {
		frame = new Window(Main.DEFAULT_WINDOW_WIDTH, Main.DEFAULT_WINDOW_HEIGHT, Main.DEFAULT_WINDOW_NAME);
		ButtonCreator back = new ButtonCreator(350, 625, 200, 100, Color.RED, "Back");
		frame.add(back);
		
        // Adds action listener for the back button
        back.addActionListener( new ActionListener(){
    
            public void actionPerformed(ActionEvent e) {
				frame.dispose();
		        startMenu.run(Main.DEFAULT_WINDOW_WIDTH, Main.DEFAULT_WINDOW_HEIGHT, Main.DEFAULT_WINDOW_NAME);
            }
        });
	}
	
	public void setPanel() {
		JPanel panel = new JPanel();
        JLabel textLabel = new JLabel(text);
        textLabel.setFont(new Font("Serif", Font.ITALIC, 30));
        textLabel.setForeground(Color.WHITE);
        panel.setBackground(Color.BLACK);
        panel.add(textLabel);
        panel.setSize(panelSizeX,panelSizeY);
        panel.setLocation(panelLocationX,panelLocationY);
        panel.setVisible(true);
        textLabel.setVisible(true);
        
        frame.add(panel);
	}
}
