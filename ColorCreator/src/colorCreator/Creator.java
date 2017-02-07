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
	 */
	public Creator(){
		
		mainWindow();
		
	}
	
	
	/**
	 * Creating the main window's dimensions and adding
	 * the menu bar.
	 * 
	 * @return N/A
	 */
	private void mainWindow(){
		
		createMenuBar();
		
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		//Creating Buttons
		JButton createSwatch = new JButton("Create Swatch");
		createSwatch.setActionCommand("Create Swatch");
		createSwatch.setAlignmentX(CENTER_ALIGNMENT);
		createSwatch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				Palette newPalette = new Palette();
			}
		});
		add(createSwatch);
		
		JButton aboutMe = new JButton("About");
		aboutMe.setActionCommand("About");
		aboutMe.setAlignmentX(CENTER_ALIGNMENT);
		aboutMe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
				JFrame frame = new JFrame();
				
				JOptionPane.showMessageDialog(frame,
					    "Thanks for checking out this little program.\n" +
					    		"You can go ahead and call me 404. This program was written as a test" +
					    		" to get used to writing GUIs again.\nI hope it's useful to you, if you" +
					    		" actually downloaded it! (Thanks for that!)\nI don't plan on making many" +
					    		" updates unless it's of particular use to anyone, so if you would like more\n" +
					    		" please let me know. As far as I can tell, there will probably be about " +
								"three total updates spread out over\n a few months. I am pretty aware that this" +
								" isn't the world's best swatch generator, so criticism is necessary! \nThanks! -404");
				
				
			}
		});
		add(aboutMe);

		JButton questions = new JButton("FAQ");
		questions.setActionCommand("FAQ");
		questions.setAlignmentX(CENTER_ALIGNMENT);
		questions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				JFrame frame = new JFrame();

				JOptionPane.showMessageDialog(frame,
						"Q: Why is this so pop-up heavy?" +
				"\nA: Sorry about that. I will probably fix that at a later date. Pop-ups happen" +
				" to be the easiest way for me to get all\nthe information I want out in a hurry." +
				"\n\nQ: Why is this so ugly?\nA: Ha! Java's default look is kind of gross, isn't it?" +
				" I can fix that if I move to Javascript or something, but for the\nmoment it's" +
				" just gonna have to be gross. Sorry!\n\nQ: How long did this take you?\nA: Hmm." +
				" It took less than a week to write up a few of the classes for this. The color" +
				" theory part of this has been\nongoing though, because I didn't study this in school" +
				" so I am not well versed in that kind of thing.\n\nQ: Can I see your code?\nA: Yeah," +
				" if it interests you, my code is stored on GitHub. You can view the repository for" +
				" this project here:\nhttps://github.com/TheFoolOrpheus/SwatchGenerator\n\nQ: Will you " +
				" be making anything else?\nA: Yes. But most of what I do isn't stuff that people can play with" +
				" normally, so I wouldn't get too excited about it. \n\nQ: Can I help?\nA: Like you want to work on"  +
				" this project? I don't care, it's basically open source if it's on GitHub. Personally I'm not\n" +
				"looking for help with this project so I'm not asking for help. But you can branch off my project" +
				"since it isn't private.\n\nQ: Do you have a Patreon/PayPal/Venmo/Other payment method?\nA: I do, but I don't " +
				"really expect to be paid for this, it's more of a gift to Pixilart that happened to coincide " +
				"with a\ndifferent project I was working on.");
			}
		});
		add(questions);
		
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
