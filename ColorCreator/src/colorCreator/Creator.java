package colorCreator;



import java.awt.EventQueue;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.event.ActionListener;
import javax.swing.JLabel;

/**
 * The main frame for this project.
 *
 * @author Ashlee Daniel
 * @version 1.0
 * 
 */

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
		
		//Button createSwatch = new Button("Create Swatch");
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		makeButton("Create Swatch");
		makeButton("Test Button");
		makeButton("Another Test");
		setTitle("Color Swatch Generator");
		setSize(500,500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//testing
		JPanel listPane = new JPanel();
		listPane.setLayout(new BoxLayout(listPane, BoxLayout.X_AXIS ));

		JLabel label = new JLabel();
		listPane.add(Box.createHorizontalGlue());
		listPane.add(label);
		listPane.add(Box.createHorizontalGlue());
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
	
	
	private void makeButton(String name){
		//getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));		
		JButton newButton = new JButton(name);
		newButton.setActionCommand(null);
		newButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
			}
		});
		add(newButton);
	
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
