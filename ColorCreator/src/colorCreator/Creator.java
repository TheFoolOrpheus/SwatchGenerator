package colorCreator;



import java.awt.EventQueue;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

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
		
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		//Creating Buttons
		JButton createSwatch = new JButton("Create Swatch");
		createSwatch.setActionCommand("Create Swatch");
		createSwatch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				Palette newPalette = new Palette();
			}
		});
		add(createSwatch);
		
		JButton aboutMe = new JButton("About");
		aboutMe.setActionCommand("About");
		aboutMe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
				JFrame frame = new JFrame();
				
				JOptionPane.showMessageDialog(frame,
					    "Thanks for checking out this little program.\n" +
					    		"You can go ahead and call me 404. This program was written as a test" +
					    		" to get used to writing GUIs again.\nI hope it's useful to you, because" +
					    		" it was a really difficult to write program!\nI don't plan on making many" +
					    		" updates unless it's of particular use to anyone, so if you would like more" +
					    		" please let me know. Thanks! -404");
				
				
			}
		});
		add(aboutMe);
		
		//Main frame
		setTitle("Color Swatch Generator");
		setSize(500,300);
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
	
	/*
	 * Delete this method
	 */
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
