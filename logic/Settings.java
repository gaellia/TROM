package logic;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import graphics.ButtonCreator;
import graphics.Window;

public class Settings {
    
    private boolean boostBool = true;
    private int numOfPlayers = 2;
    private int numOfPlayersText = 1;
    private int difficultySpeed = 100;
    private boolean gameGoing = false;
    private boolean restart = false;
    private boolean timer = true;
    public StartMenu startMenu;
    public Main main;
    
    public Settings(Main initMain) {
        main = initMain;
        startMenu = new StartMenu(this);
    }
    
    /** 
     * Runs the Settings menu
     *
     * @param width Sets the width of the window
     * @param height Sets the height of the window
     * @param name Sets the name of the window
     */
    public void run(int w,int h, String name){
        
        // Creates a new window
        Window frame = new Window(w, h, name);
        
        // Creates buttons
        ButtonCreator startButton = new ButtonCreator(300, 250, 300, 100, Color.CYAN, "Start");
        ButtonCreator playersButton = new ButtonCreator(275, 350, 350, 100, Color.YELLOW, "2 Players");
        ButtonCreator boostsButton = new ButtonCreator(250, 450, 400, 100, Color.GREEN, "Boosts On");
        ButtonCreator difficultyButton = new ButtonCreator(230, 550, 450, 100, Color.MAGENTA, "Easy");
        ButtonCreator backButton = new ButtonCreator(305, 650, 300, 100, Color.RED, "Back");
        
        // Initializes the title for settings
        JPanel panel = (JPanel) frame.getContentPane();
        panel.setLayout(null);
        JLabel img = new JLabel(new ImageIcon("assets/SETTINGS.png"));
        panel.add(img);
        Dimension size = img.getMaximumSize();
        img.setBounds(170, 40, size.width, size.height);
        
        // Adds title, buttons and makes frame visible
        frame.add(startButton);
        frame.add(playersButton);
        frame.add(img);
        frame.add(boostsButton);
        frame.add(backButton);
        frame.add(difficultyButton);
        frame.setVisible(true);
        
        
        // Adds action listener for the back button
        backButton.addActionListener( new ActionListener(){
    
            public void actionPerformed(ActionEvent e){
            	startMenu.run(w, h, name);
            	frame.dispose();
            }
        });
        
        // Adds action listener for the boosts button
        boostsButton.addActionListener( new ActionListener() {
            
            public void actionPerformed(ActionEvent e){
            	toggleBoosts(boostsButton);
            }
        });
            
        // Adds action listener for the players button
        playersButton.addActionListener( new ActionListener() {
            
        	public void actionPerformed(ActionEvent e){
        		togglePlayers(playersButton);
        	}
        });
            
        // Adds action listener for the difficulty button
        difficultyButton.addActionListener( new ActionListener() {
           
           public void actionPerformed(ActionEvent e) {
        	   toggleDifficulty(difficultyButton);
           }

        });   
            
        // Adds action listener for the start button
        startButton.addActionListener( new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
            	gameGoing = true;
            	timer = false;
            	frame.dispose();
            }
        });

    }
    
    // Toggle method for boost button
    public void toggleBoosts(ButtonCreator boostsButton) {
		if (boostBool == true) {
			boostsButton.setText("Boosts Off");  
			boostBool = false;
		}
		else {
		    boostsButton.setText("Boosts On");
		    boostBool = true;
		}
    }
    
    // Toggle method for number of players button
    public void togglePlayers(ButtonCreator playersButton) {
    	
        if (numOfPlayersText == 0) {
            playersButton.setText("2 Players");
            numOfPlayers = 2;
            numOfPlayersText += 1;
        }
        
        else if (numOfPlayersText == 1) {
            playersButton.setText("3 Players");
            numOfPlayers = 3;
            numOfPlayersText += 1;
        }
        
        else if (numOfPlayersText == 2) {
            playersButton.setText("4 Players");
            numOfPlayers = 4;
            numOfPlayersText -= 2;
        }
    }
    
    // Toggle method for difficulty button
    public void toggleDifficulty(ButtonCreator difficultyButton) {
        if (difficultySpeed == 100) {
            difficultyButton.setText("Intermediate");
            difficultySpeed -= 30;
            System.out.println(difficultySpeed);
        }
        
        else if (difficultySpeed == 70) {
            difficultyButton.setText("Expert");
            difficultySpeed -= 35;
            System.out.println(difficultySpeed);
        }
        
        else if (difficultySpeed == 35) {
            difficultyButton.setText("Easy");
            difficultySpeed += 65;
            System.out.println(difficultySpeed);
        }
    }
    
    
    /**
     * Getter for number of players
     */
    public int getPlayers() {
        return numOfPlayers;
    }
    
    /**
    * Setter method for Players
    *
    * @param number passes in setter for players
    */
    public void setPlayers(int number) {
        if (number < 1)
            numOfPlayers = 1;
        if (number > 4)
            numOfPlayers = 4;
        else
            numOfPlayers = number;
    }
    
    /**
     * Getter for the boost boolean
    */
    public boolean getBoost() {
        return boostBool;
    }
 
    /**
     * Getter for difficulty
     */
    public int getDifficulty() {
        return difficultySpeed;
    }

	/**
     *Getter for getGameGoing
     */
    public boolean getGameGoing() {
	    return gameGoing;
    }

    /**
    * Getter for timer
    */
    public boolean getTimer() {
	    return timer;
	}
    
    /**
    * Setter method for gameGoing
    *
    * @param setter passes in setter for gameGoing
    */
	public void setGameGoing(boolean setter) {
	    gameGoing = setter;
	}
    
    /**
    * Setter method for the timer
    *
    * @param time passes in setter for setTimer
    */
    public void setTimer(boolean time){
        timer = time;
    }
    
    /**
     * Setter for the restart boolean
     *
     * @param change Switch for the given boolean
     */
    public void setRestart(Boolean change) {
        restart = change;
    }
    
    /**
     * Getter for the restart boolean
     */
    public boolean getRestart() {
        return restart;
    }

}
