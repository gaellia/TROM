package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import javax.swing.JLabel;
import javax.swing.JPanel;

import logic.Main;
import logic.Settings;

public class RestartScreen {
	
	private Color winnerColor;
	private int winnerNum;
	private Settings settings;
		
	public RestartScreen(Color initWinnerColor, int initWinnerNum, Settings initSettings) {
		winnerColor = initWinnerColor;
		winnerNum = initWinnerNum;
		settings = initSettings;
	}

   /**
	* Gives player the option to restart the game after the game is over
	*
	* @param winner Color of the winner to set text to that color
	* @param playerNum Number of the winner to say who won that game
	*/
	public boolean restartGame() {
	       
	    Window window = new Window(Main.DEFAULT_WINDOW_WIDTH, Main.DEFAULT_WINDOW_HEIGHT, Main.DEFAULT_WINDOW_NAME);
	    window.setLayout(null);
	        
	    Font font = openFont();
	    font = font.deriveFont(Font.PLAIN, 50);
	    
	        
	    // Creates the play again text
	    JPanel panel = new JPanel();
	    JLabel text = new JLabel("Play Again");
	    text.setFont(font);
	    setPanelText(window, panel, text, 400, 80, 250, 350);
	        
	    // Determines who won or if the game was a tie
	    JPanel panel2 = new JPanel();
	    JLabel winnerText;
	    if (winnerNum == 0) {
	         winnerText = new JLabel("TIE");
	         winnerText.setForeground(Color.WHITE);
	         text.setForeground(Color.WHITE);
	    }
	    else {
	        winnerText = new JLabel("Player " + winnerNum + " Wins");
	        winnerText.setForeground(winnerColor);
	        text.setForeground(winnerColor);
	    }
	     
	    font = font.deriveFont(Font.PLAIN, 65);
	    winnerText.setFont(font);
	    setPanelText(window, panel2, winnerText, 600, 80, 160, 200);
	    
	    // Yes and No buttons
	    createButtons(window);
	    
	    // Restart game if true
		settings.startMenu.setGameGoing(true);
		return settings.getRestart();
	}
	
	
	// Opens font from file
	public Font openFont() {
		Font font = null;
	    try {
	        font = Font.createFont(Font.TRUETYPE_FONT, new File("assets/Tron.ttf"));
	    } catch (FontFormatException | IOException error) {
	        font = new Font("Arial", Font.BOLD, 40);
	    }
	    return font;
	}
	
	
	// Creates the text on the panel
	public void setPanelText(Window window, JPanel panel, JLabel text, int sizeX, int sizeY, int locationX, int locationY) {
	    panel.setBackground(Color.BLACK);
	    panel.add(text);
	    panel.setSize(sizeX, sizeY);
	    panel.setLocation(locationX, locationY);
	    panel.setVisible(true);
	    window.add(panel);
	}
	
	
	// Creates Yes and No buttons
	public void createButtons(Window window) {
	    ButtonCreator yes = new ButtonCreator(200, 450, 150, 100, winnerColor, "Yes");
	    ButtonCreator no = new ButtonCreator(550, 450, 150, 100, winnerColor, "No");
	        
	    if (winnerNum == 0) {
	        yes.setForeground(Color.WHITE);
	        no.setForeground(Color.WHITE);
	    }
	        
	    window.add(no);
	    window.add(yes);
	    window.setVisible(true);
	        
	    CountDownLatch wait2 = new CountDownLatch(1);;
	        
	    // Adds action listener for the yes button
	    yes.addActionListener( new ActionListener() {
	            
	        public void actionPerformed(ActionEvent e) {
			    window.dispose();
			    settings.setRestart(true);
			    wait2.countDown();
	        }
	    
	    });

	    // Adds action listener for the no button
	    no.addActionListener( new ActionListener() {
	            
	        public void actionPerformed(ActionEvent e) {
			    window.dispose();
			    settings.setRestart(false);
			    wait2.countDown();
	        }
	            
	    });
	        
	    try {
	        wait2.await();
	    } catch (InterruptedException e) {
	        System.out.println("Caught");
	    }
	}
}
