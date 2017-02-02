package colorCreator;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * The main frame for this project.
 *
 * @author Ashlee Daniel
 * @version 1.0
 * 
 */

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class Creator extends JFrame {
	
	/**
	 * Constructor, calls the method to create the main
	 * window.
	 * 
	 * @param None
	 */
	public Creator(){
		
		mainWindow();
		
	}
	
	
	/**
	 * Creating the main window's dimensions and adding
	 * the menu bar.
	 * 
	 * @param None
	 * @return N/A
	 */
	private void mainWindow(){
		
		createMenuBar();
		
		setTitle("Color Swatch Generator");
		setSize(500,500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	/**
	 * Creates the menubar and the items on it.
	 * 
	 * @param None
	 * @return N/A
	 */
	private void createMenuBar(){
		
		//Creating the menu bar
		JMenuBar menuBar = new JMenuBar();
		
		//Creating a menu
		JMenu file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);
		
		//Creating menu items
		JMenuItem exit = new JMenuItem("Exit");
		exit.setMnemonic(KeyEvent.VK_E);
		exit.setToolTipText("Exit");
		exit.addActionListener(
				(ActionEvent event) -> {System.exit(0);});
		
		//Adding items to menus
		file.add(exit);
		
		//Adding menus to menu bar
		menuBar.add(file);
		
		//Setting menu bar
		setJMenuBar(menuBar);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		EventQueue.invokeLater(
				() -> {
					Creator c = new Creator();
					c.setVisible(true);
				});

	}

}
