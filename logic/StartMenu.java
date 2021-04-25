package logic;

import java.awt.*;
import javax.swing.*;

import graphics.ButtonCreator;
import graphics.CreditMenu;
import graphics.InstructionMenu;
import graphics.Window;

import java.awt.event.*;

public class StartMenu{
	
	private boolean gameGoing = false;
	private boolean timer = true;
	private Settings settings;
	private StartMenu startMenu;
	
	public StartMenu(Settings initSettings) {
	    settings = initSettings;
	    startMenu = this;
	}
	
    /**
     * Getter for the gameGoing boolean
     */
	public boolean getGameGoing() {
	    return gameGoing;
	}
	
    /** 
     * Setter for the boolean gameGoing
     *
     *@param gameBool sets the gameGoing boolean
     */
    public void setGameGoing(boolean gameBool){
        gameGoing = gameBool;
    }
   
    /**
    * Getter for timer
    */
    public boolean getTimer() {
	    return timer;
	}
	
    /**
     * Run method which runs the Start Menu
     *
     * @param w Sets the width of the frame
     * @param h Sets the height of the frame
     * @param name Sets the name of the frame
     */
    public void run(int w,int h, String name){
	    
        // Initialize JFrame
        Window frame = new Window(w, h, name);
        
        // Create Buttons
	    ButtonCreator start = new ButtonCreator(350, 250, 200, 100, Color.CYAN, "Play");
        ButtonCreator instructionsButton = new ButtonCreator(200, 375, 500, 100, Color.YELLOW, "Instructions");
        ButtonCreator credits = new ButtonCreator(300, 500, 300, 100, Color.MAGENTA, "Credits");
        ButtonCreator exit = new ButtonCreator(350, 625, 200, 100, Color.RED, "Exit");
        
		// Create JPanel and JLabel to add logo
		JPanel panel = (JPanel) frame.getContentPane();
        panel.setLayout(null);
        JLabel img = new JLabel(new ImageIcon("assets/TROM.png"));
        panel.add(img);
        Dimension size = img.getMaximumSize();
        img.setBounds(170, 60, size.width, size.height);
       
        // Adding all of the buttons to the JFrame
		frame.add(start);
        frame.add(instructionsButton);
		frame.add(exit);
        frame.add(credits);
        frame.add(img);
        frame.setVisible(true);
		
        // Adds action listener and switches game going and timer booleans to start the game
        start.addActionListener( new ActionListener(){
    
            public void actionPerformed(ActionEvent e){
		        System.out.println("Start Clicked");
		        settings.run(w, h, name);
		        frame.dispose();
		    }
        });
       
        // Adds action listener and opens the instuctions menu
        instructionsButton.addActionListener( new ActionListener(){
        	
        	public void actionPerformed(ActionEvent e){
        		frame.dispose();
        		InstructionMenu instrMenu = new InstructionMenu(startMenu);
        		instrMenu.createNewFrame();
        		instrMenu.showInstructions();
        		instrMenu.setPanel();
        	}
        });

        // Adds the action listener for the instructions button
        credits.addActionListener( new ActionListener(){
    
            public void actionPerformed(ActionEvent e){
            	
            	frame.dispose();
            	CreditMenu creditMenu = new CreditMenu(startMenu);
            	creditMenu.createNewFrame();
            	creditMenu.showCredits();
            	creditMenu.setPanel();
 
            }
        });
        
        // Adds action listener to exit the game when exit is clicked
        exit.addActionListener( new ActionListener(){
    
        	public void actionPerformed(ActionEvent e){
		        System.out.println("Exit Menu Clicked");
				timer = false;
				gameGoing = false;
		        settings.setTimer(false);
				frame.dispose();
	        }
        });

    }
	  
}
