package logic;

import java.awt.*;
import java.io.*;

import graphics.Cell;
import graphics.Map;
import graphics.RestartScreen;

import java.lang.Thread;
import java.util.Random;

public class Main {

    public static final int DEFAULT_WINDOW_WIDTH = 900;
    public static final int DEFAULT_WINDOW_HEIGHT = 900;
    public static final int DEFAULT_PLAYERS = 1;
    public static final String DEFAULT_WINDOW_NAME = "T R O M";
    private Player[] players;
    private Settings settings;
    private Map map;
    
    public Main() {
        settings = new Settings(this);
    }
    
    public Main(Settings initSettings) {
        settings = initSettings;
    }
    /**
     * Main program function
     * 
     * @param args
     */
    public boolean start() {
        settings.startMenu.run(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT, DEFAULT_WINDOW_NAME);
        while (settings.getTimer()) {
            stop(100);

        }
        
        if (settings.getGameGoing()){
            return initializeGame(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT, DEFAULT_WINDOW_NAME, settings.getPlayers());
        }
        return true;
    }
	
	/**
	 * Returns a player from the main players array.
	 * 
	 * @param num Index number of wanted player
	 * @return Player from players[] array
	 */
	public Player getPlayer(int num) {
	    try {
	        return players[num];
	    }
	    catch (ArrayIndexOutOfBoundsException ex) {
	        return players[0];
	    }
	    finally {}
	}
	
    /**
     * Returns a cell from the main cell array.
     * 
     * @param x X Coordinate of the wanted cell
     * @param y Y Coordinate of the wanted cell
     * @return Cell at (x,y) entry of labels array.
     */
    public Cell getCell(int x, int y) {
        return map.getCell(x, y);
    }
    
	/**
	 * Initializes and runs game
	 * 
	 * @param w Width of the window
	 * @param h Height of the window
	 * @param name Name of the window
	 * @param numPlayers Amount of players
	 */
    private boolean initializeGame(int w, int h, String name, int numPlayers) {
        
        if (numPlayers > 4)
            numPlayers = 4;
        else if (numPlayers < 1)
            numPlayers = 1;
        
        setPlayers(numPlayers);
        
        map = new Map(this, w, h, name);
        
        if (settings.getBoost() == true) {
            powerUp();
        }
        
        int dead = 0;
        int winnerNumber = 0;
        Color winnerColor = Color.RED;
       
        // Main game loop
        int delay = 0;
        while (settings.getGameGoing()) {
            dead = 0;
            for (int i = 0; i < numPlayers; i++) {
                if (players[i].getAlive() == true) {
                    players[i].move();
                }
                else {
                	dead++;
                }
            }
            if (delay == 0) {
                delay = 1;
                map.iniPos(numPlayers,Color.WHITE);
                map.drawThree(Color.WHITE);
                stop(1000);
                map.drawThree(Color.BLACK);
                map.drawTwo(Color.WHITE);
                stop(1000);
                map.drawTwo(Color.BLACK);
                map.drawOne(Color.WHITE);
                stop(1000);
                map.drawOne(Color.BLACK);
                map.iniPos(numPlayers,Color.BLACK);
            }
            
            // Prints the final scores to the console and ends the game
            if (dead >= (numPlayers - 1)) {
                settings.setGameGoing(false);
                for (int i = 0; i < numPlayers; i++) {
                	System.out.println("Player "+ (i+1) + " FINAL SCORE: " + players[i].getScore());
                    if (players[i].getAlive()) {
                        winnerNumber = i+1;
                        winnerColor = players[i].getPlayerColor();
                    }
                }
            }
            map.repaintGame();
            stop(settings.getDifficulty());
        }
       
       // Prints the high scores to a text file
        map.gameOver();
        String fileName = "scores.txt";
        PrintWriter out = null;
        try {
            File file = new File(fileName);
            if (!file.exists())
                file.createNewFile();
            out = new PrintWriter(new FileOutputStream(file));
            for (int i=0;i < numPlayers;i++) {
                out.write("PLAYER " + (i+1) + " SCORE: " + players[i].getScore());
                out.println();
            }
        }
        
        // Catches the IOException if the file is not found
        catch (IOException e) {
            System.out.println("ERROR: No scores.txt found");
        }
        
        out.close();
        stop(1000);
        map.disposeWindow();
        RestartScreen restart = new RestartScreen(winnerColor, winnerNumber, settings);
        return restart.restartGame();
        
        
    }
    // This method uses code copied from http://stackoverflow.com/questions/24104313/how-to-delay-in-java by Ann Ragg
    private void stop(int milliseconds) {
        
        try {
            Thread.sleep(milliseconds);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
    
    /**
     * Spawns the power up to a random cell
     */
    public void powerUp() {
        Color hotPink = new Color(255, 0, 255);
        int randomX = new Random().nextInt(50);
        int randomY = new Random().nextInt(50);
        getCell(randomX, randomY).colorUpdate(hotPink);
        
    }
    
    /**
     * Sets the gameGoing loop to false.
     */
    public void stopGame() {
        settings.setGameGoing(false);
    }
    
    /**
     * Getter for settings
     */
    public Settings getSettings(){
        return settings;
    }
    
    /**
     * Setter for players
     */
    public void setPlayers(int numPlayers) {
        
        players = new Player[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            if (i == 0) {
                players[i] = new Player(5, 5, Color.YELLOW, "down");
                players[i].setMain(this);
            }
            else if (i == 1) {
                players[i] = new Player(44, 5, Color.RED, "left");
            players[i].setMain(this);
            }
            else if (i == 2) {
                players[i] = new Player(44, 44, Color.CYAN, "up");
                players[i].setMain(this);
            }
            else if (i == 3) {
                players[i] = new Player(5, 44, Color.MAGENTA, "right");
                players[i].setMain(this);
            }
        }
    }

}
