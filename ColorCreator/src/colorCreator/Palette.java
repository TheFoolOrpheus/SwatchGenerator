package colorCreator;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.JColorChooser;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import net.miginfocom.swing.*;


import static java.lang.Integer.parseInt;

/**
 * The window that pops up when you click on "Create Swatch".
 *
 * This window has three frames. One frame shows information,
 * another shows the colors, their RBG values and their hexcodes,
 * along with a slider for hue and saturation, and a final frame with
 * some buttons to allow you to choose the type of palette and maybe
 * some other options.
 *
 * The palette types are 16 color, 8 color, and 4 color, as well as
 * monochrome and gradient.
 * @author Ashlee Daniel
 *
 */
public class Palette extends JFrame {

	boolean complementary = false;
	JColorChooser panel;

	public Palette(){

		paletteWindow();

	}

	private void paletteWindow(){

		createMenuBar();

		/*
         * OMG GRIDBAG SOLVED
         * IT WAS THE WEIGHT
         * THANK YOU http://www.java2s.com/Code/Java/Swing-JFC/GridBagLayoutPane.htm
         * MY HERO
         */
		GridBagLayout gridBag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		//This makes the components grow in both dimensions.
		c.fill = GridBagConstraints.BOTH;
		// this gives a 2 -pixel margin on all sides
		c.insets = new Insets(2,2,2,2);

		/*
		 * Creating the frame, which I might have to make non-resizable.
		 * The layout is GridBag.
		 *
		 * I need to add a menubar with a bar that offers help so that people don't get confused by
		 * what some of these buttons do.
		 */
		setTitle("Swatch Creator");
		//Prev. size 1000 x 1000
		setSize(575,600);
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setResizable(false);
		setLayout(gridBag);

		/*
		 * The frame used for dialog boxes when buttons are being pressed.
		 */
		JFrame frame = new JFrame();
		frame.setAlwaysOnTop(true);
		frame.setDefaultCloseOperation(HIDE_ON_CLOSE);
		frame.setLocationRelativeTo(null);



		JButton multi = new JButton("Multi-Color");
		multi.setActionCommand("Multi-Color");
		c.gridx = 3;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = c.weighty = 0.0;
		multi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				JOptionPane.showMessageDialog(frame, "Not Yet Implemented");

			}
		});
		this.add(multi, c);


		JButton shift = new JButton("Hue Shift Ramp");
		shift.setActionCommand("Hue Shift Ramp");
		shift.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				JOptionPane.showMessageDialog(frame, "Not Yet Implemented");

			}
		});
		c.gridx = 4;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = c.weighty = 0.0;
		this.add(shift, c);

		panel = new JColorChooser();
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 5;
		c.gridheight = 4;
		c.weightx = c.weighty = 1.0;
		add(panel, c);

		/*
		 * Creating the monochrome button. Should pass isPastel and
		 * paletteNumber to the monochrome class.
		 *
		 */
		JButton monochrome = new JButton("Monochrome");
		monochrome.setActionCommand("Monochrome");
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = c.weighty = 0.0;
		monochrome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){

				//MonochromeSwatch ms = new MonochromeSwatch(paletteNumber(), complementary);
				//JOptionPane.showMessageDialog(frame, ms.toString());
				/*
				 * To be implemented:
				 */
				formCreate(1);


			}
		});
		add(monochrome, c);


        /*
		 * Creating a adjacent color button. Should pass isPastel and
		 *  paletteNumber to the adjacent class.
		 */
		JButton adjacent = new JButton("Adjacent");
		adjacent.setActionCommand("Adjacent");

		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = c.weighty = 0.0;
		adjacent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				JOptionPane.showMessageDialog(frame, "Not Yet Implemented");
			}
		});

		add(adjacent, c);


		/*
		 * Creating a Triad color button. Should pass isPastel and
		 *  paletteNumber to the adjacent class.
		 */
		JButton triad = new JButton("Triad");
		triad.setActionCommand("Triad");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = c.weighty = 0.0;
		triad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				JOptionPane.showMessageDialog(frame, "Not Yet Implemented");
			}
		});

		add(triad, c);

		/*
		 * Creating the complementary colors button. Should go across the bottom
		 * entirely but... I mean of course it isn't, I suck at gridBag.
		 */
		JButton compColor = new JButton("Complementary Color");
		compColor.setActionCommand("Complementary Color");
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 5;
		c.gridheight = 1;
		compColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				complementaryOn();
				if(complementary == true) {
					JOptionPane.showMessageDialog(frame, "Complementary colors on.");
				}
				else{
					JOptionPane.showMessageDialog(frame, "Complementary colors off.");
				}
			}
		});

		add(compColor, c);


	}

	public void createMenuBar(){

		JMenuBar menuBar = new JMenuBar();

		JMenuItem how = new JMenuItem("How To Use This Program");
		how.setMnemonic(KeyEvent.VK_H);
		how.setToolTipText("How To");
		how.addActionListener(
				(ActionEvent event) -> {
					JFrame f = new JFrame("How To");
					f.setDefaultCloseOperation(HIDE_ON_CLOSE);
					f.setVisible(true);
					f.setSize(200, 200);
					f.setLocationRelativeTo(null);
					f.setAlwaysOnTop(true);
					JSplitPane howTo = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
					f.add(howTo);
				}

		);

		//Adding menus to menu bar
		menuBar.add(how);


		//Setting menu bar
		setJMenuBar(menuBar);

	}

	/**
	 *
	 */
	private String formCreate(int paletteType){

		/*
		 * Essentially this code is a better looking version of the pop-ups from
		 * earlier. I wrote it that way but the multiple popup thing is annoying.
		 * it might be better to re-work the entire thing but instead of doing that
		 * especially since have no idea how to change JColorChooser to a colorwheel
		 * and because as this gets more complicated the user will need to add more
		 * information into the text fields, I want to have a form so that when you
		 * click on the button, it shows you a screen and then allows you to view data
		 * before you submit it.
		 *
		 * As such, I'm doing the following:
		 * - limiting palette size to 4, 5, 6, and 16.
		 * - Making some thing inaccessible unless previous conditions are met.
		 * - showing what the end product will look like.
		 * - implementing a screen that shows the end product.
		 */


		//creating some variables...
		JFrame frame = new JFrame("Information");
		JPanel mainPanel = new JPanel();
		JPanel infoPanel = new JPanel();
		JTextField colorValue = new JTextField("#HEXCOD or rgb(r,g,b)");
		Insets in = new Insets(2,2,2,2);
		GridBagLayout gridBag = new GridBagLayout();
		GridBagLayout gridBagPanel1 = new GridBagLayout();
		GridBagLayout gridBagPanel2 = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		frame.setLayout(gridBag);
		mainPanel.setLayout(gridBagPanel1);
		infoPanel.setLayout(gridBagPanel2);
		frame.setVisible(true);
		frame.setSize(700,400);
		mainPanel.setVisible(true);
		infoPanel.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);


		//Constraints stuff.
		c.insets = in;
		c.fill = GridBagConstraints.BOTH;

		//An array of radio buttons and their group
		JRadioButton[] numColorsButtons = new JRadioButton[10];
		ButtonGroup howManyColors = new ButtonGroup();
		ButtonGroup baseColorOptions = new ButtonGroup();
		ButtonGroup pastelColorOptions = new ButtonGroup();
		ButtonGroup shadesTints = new ButtonGroup();

		numColorsButtons[0] = new JRadioButton("4");
		numColorsButtons[0].setActionCommand("4");

		numColorsButtons[1] = new JRadioButton("5");
		numColorsButtons[1].setActionCommand("5");

		numColorsButtons[2] = new JRadioButton("6");
		numColorsButtons[2].setActionCommand("6");

		numColorsButtons[3] = new JRadioButton("16");
		numColorsButtons[3].setActionCommand("16");

		numColorsButtons[4] = new JRadioButton("Yes");
		numColorsButtons[4].setActionCommand("Yes");

		numColorsButtons[5] = new JRadioButton("No");
		numColorsButtons[5].setActionCommand("No");

		numColorsButtons[6] = new JRadioButton("Yes");
		numColorsButtons[6].setActionCommand("Yes");

		numColorsButtons[7] = new JRadioButton("No");
		numColorsButtons[7].setActionCommand("No");

		numColorsButtons[8] = new JRadioButton("Shades");
		numColorsButtons[8].setActionCommand("Shades");

		numColorsButtons[9] = new JRadioButton("Tints");
		numColorsButtons[9].setActionCommand("Tints");


		for(int i = 0; i < 4; i++){howManyColors.add(numColorsButtons[i]);}
		for(int i = 4; i < 6; i++){baseColorOptions.add(numColorsButtons[i]);}
		for(int i = 6; i < 8; i++){pastelColorOptions.add(numColorsButtons[i]);}
		for(int i = 8; i < 10; i++){shadesTints.add(numColorsButtons[i]);}

		/*
		 * Creating the header for this thing
		 */
		JLabel header;
		switch(paletteType){

			case 1:
				header = new JLabel("Monochrome Palette");
				header.setFont(new Font("Serif", Font.BOLD, 15));
				break;
			case 2:
				header = new JLabel("Default");
				break;
			case 3:
				header = new JLabel("Default");
				break;
			case 4:
				header = new JLabel("Default");
				break;
			default:
				header = new JLabel("Default");
				break;

		}


		JLabel aboutBase = new JLabel("Do you have a base color in mind?");
		JLabel aboutColors = new JLabel("How many colors would you like made?");
		JLabel aboutPastel = new JLabel("Do you want a pastel palette?");
		JLabel aboutShades = new JLabel("Do you want more shades or tints");
		JLabel confirmSelection = new JLabel("Is this information correct?");
		JLabel confirmBase = new JLabel();
		JLabel confirmColors = new JLabel();
		JLabel confirmPastel = new JLabel();
		JLabel confirmShades = new JLabel();


		c.gridx = 4;
		c.gridy = 0;
		c.weightx = c.weighty = 1.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		mainPanel.add(header, c);

		c.gridx = 0;
		c.gridy = 0;
		c.weightx = c.weighty = 0.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		mainPanel.add(aboutBase, c);

		c.gridx = 0;
		c.gridy = 1;
		c.weightx = c.weighty = 0.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		/*
		 * This is about base color options.
		 * [4] is the button for Yes and [5] is the button for no.
		 * If [4] == selected allow the user to enter a value into
		 * a textField.
		 * If not, do not allow entry into that field.
		 * Also, replace the string in ConfirmBase to read, "Base color"
		 * and the color's hexcode when true. When not chosen, state that
		 * a random color will be chosen.
		 */
		numColorsButtons[4].addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){

				}
				else if(e.getStateChange() ==ItemEvent.DESELECTED){

				}
			}
		});
		mainPanel.add(numColorsButtons[4], c);

		//Everything having to do with this should be done in button 4.
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = c.weighty = 0.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		mainPanel.add(numColorsButtons[5], c);

		c.gridx = 0;
		c.gridy = 2;
		c.weightx = c.weighty = 0.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		mainPanel.add(aboutColors,c);

		c.gridx = 0;
		c.gridy = 3;
		c.weightx = c.weighty = 0.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		/*
		 * This button will change the confirmColor's dialog
		 * when selected (remember to paint the panel)
		 *
		 * When deselected it does nothing.
		 */
		numColorsButtons[0].addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){

				}
				else if(e.getStateChange() ==ItemEvent.DESELECTED){

				}
			}
		});
		mainPanel.add(numColorsButtons[0], c);

		c.gridx = 1;
		c.gridy = 3;
		c.weightx = c.weighty = 0.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		/*
		 * This button will change the confirmColor's dialog
		 * when selected (remember to paint the panel)
		 *
		 * When deselected it does nothing.
		 */
		numColorsButtons[1].addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){

				}
				else if(e.getStateChange() ==ItemEvent.DESELECTED){

				}
			}
		});
		mainPanel.add(numColorsButtons[1], c);

		c.gridx = 0;
		c.gridy = 4;
		c.weightx = c.weighty = 0.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		/*
		 * This button will change the confirmColor's dialog
		 * when selected (remember to paint the panel)
		 *
		 * When deselected it does nothing.
		 */
		numColorsButtons[2].addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){

				}
				else if(e.getStateChange() ==ItemEvent.DESELECTED){

				}
			}
		});
		mainPanel.add(numColorsButtons[2], c);

		c.gridx = 1;
		c.gridy = 4;
		c.weightx = c.weighty = 0.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		/*
		 * This button will change the confirmColor's dialog
		 * when selected (remember to paint the panel)
		 *
		 * When deselected it does nothing.
		 */
		numColorsButtons[3].addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){

				}
				else if(e.getStateChange() ==ItemEvent.DESELECTED){

				}
			}
		});
		mainPanel.add(numColorsButtons[3], c);

		c.gridx = 0;
		c.gridy = 5;
		c.weightx = c.weighty = 0.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		mainPanel.add(aboutPastel, c);

		c.gridx = 0;
		c.gridy = 6;
		c.weightx = c.weighty = 0.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		/*
		 * This is about pastel options.
		 * [6] is the button for Yes and [7] is the button for no.
		 * Replace the string in ConfirmPastel to read, "Pastel Palette".
		 * When not selected, have the string read "Non-Pastel Palette"
		 *
		 * Remember to paint.
		 *
		 */
		numColorsButtons[6].addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){

				}
				else if(e.getStateChange() ==ItemEvent.DESELECTED){

				}
			}
		});
		mainPanel.add(numColorsButtons[6], c);

		//Everything here should be covered in button 6
		c.gridx = 1;
		c.gridy = 6;
		c.weightx = c.weighty = 0.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		mainPanel.add(numColorsButtons[7], c);

		c.gridx = 0;
		c.gridy = 7;
		c.weightx = c.weighty = 0.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		mainPanel.add(aboutShades, c);

		c.gridx = 0;
		c.gridy = 8;
		c.weightx = c.weighty = 0.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		/*
		 * This is about pastel options.
		 * [8] is the button for Yes and [9] is the button for no.
		 * Replace the string in ConfirmShades to read, "Pastel Palette".
		 * When not selected, have the string read "Non-Pastel Palette"
		 *
		 * Remember to paint.
		 *
		 */
		numColorsButtons[4].addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED){

				}
				else if(e.getStateChange() ==ItemEvent.DESELECTED){

				}
			}
		});
		mainPanel.add(numColorsButtons[8], c);

		//Covered in button 8
		c.gridx = 1;
		c.gridy = 8;
		c.weightx = c.weighty = 0.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		mainPanel.add(numColorsButtons[9], c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridheight = 8;
		c.weightx = c.weighty = 1.0;
		mainPanel.setSize(400,400);
		frame.add(mainPanel, c);

		c.gridx = 0;
		c.gridy = 0;
		c.weightx = c.weighty = 0.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		infoPanel.add(confirmSelection, c);

		c.gridx = 0;
		c.gridy = 0;
		c.weightx = c.weighty = 0.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		infoPanel.add(confirmBase, c);

		c.gridx = 0;
		c.gridy = 0;
		c.weightx = c.weighty = 0.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		infoPanel.add(confirmColors, c);

		c.gridx = 0;
		c.gridy = 0;
		c.weightx = c.weighty = 0.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		infoPanel.add(confirmPastel, c);

		c.gridx = 0;
		c.gridy = 0;
		c.weightx = c.weighty = 0.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		infoPanel.add(confirmShades, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 8;
		c.gridy = 0;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridheight = 3;
		c.weightx = c.weighty = 0.0;
		infoPanel.setSize(300,300);
		frame.add(infoPanel);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 4;
		c.gridheight = c.gridwidth = 1;
		c.weightx = c.weighty = 0.0;
		JButton cancel = new JButton("Cancel");
		cancel.setActionCommand("Cancel");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				frame.dispose();

			}
		});
		frame.add(cancel);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 2;
		c.gridheight = c.gridwidth = 1;
		c.weightx = c.weighty = 0.0;
		JButton okay = new JButton("OK");
		okay.setActionCommand("OK");
		okay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){

				switch(paletteType){
					case 1:

						break;
					case 2:

						break;
					case 3:

						break;
					case 4:

						break;
					default:
						frame.dispose();
						break;
				}

			}
		});
		frame.add(okay);



		return "";
	}


	/**
	 *
	 * A method that gets the user's input about whether or not they want a pastel 
	 * palette.
	 *
	 * @return pastel A boolean that determines whether or not the palette is pastel
	 */
	private boolean isPastel(){

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(HIDE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setAlwaysOnTop(true);
		boolean pastel = false;

		int n = JOptionPane.showConfirmDialog(frame, "Are you creating a pastel palette?",
				"Pastel?", JOptionPane.YES_NO_CANCEL_OPTION);

		switch(n){

			case JOptionPane.YES_OPTION:
				JOptionPane.showMessageDialog(frame, "Only white will be added to your base" +
						"color.");
				pastel = true;
				break;

			case JOptionPane.NO_OPTION:
				JOptionPane.showMessageDialog(frame, "An equal number of tints and shades will " +
						"be made.");
				pastel = false;
				break;

			case JOptionPane.CANCEL_OPTION:
				JOptionPane.showMessageDialog(frame, "Defaulting to non-pastel palette.");
				pastel = false;
				break;

			default:
				break;

		}


		return pastel;
	}

	/**
	 *
	 * A way to know how many colors the user wants. The current system will do 3, 4, 5, 6, 16, and custom.
	 *
	 * The system will then create that number of colors. Within the menu for the different palettes,
	 * it will have to use mod to determine whether a number is odd or even, but this method only determines the
	 * color #.
	 *
	 * @return colors the number of colors in the palette
	 */

	private int paletteNumber(){
		int colors = 0;

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(HIDE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setAlwaysOnTop(true);
		int opt = 0;

		String[] options = { "3", "4", "5", "6", "16", "custom" };
		opt = JOptionPane.showOptionDialog(frame, "Please choose the size of your palette.", "Number of colors",
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
				null, options, options[0]);

		switch (opt){
			case 0:
				JOptionPane.showMessageDialog(frame, "Generating swatch with 3 " +
						"colors.");

				colors = 3;
				break;
			case 1:
				JOptionPane.showMessageDialog(frame, "Generating swatch with 4 " +
						"colors.");
				colors = 4;
				break;
			case 2:
				JOptionPane.showMessageDialog(frame, "Generating swatch with 5 " +
						"colors.");
				colors = 5;
				break;
			case 3:
				JOptionPane.showMessageDialog(frame, "Generating swatch with 6 " +
						"colors.");
				colors = 6;
				break;
			case 4:
				JOptionPane.showMessageDialog(frame, "Generating swatch with 16 " +
						"color.");

				colors = 16;
				break;
			case 5:
				String customNumber = "";
				//JOptionPane.showMessageDialog(frame, "This is for custom colors. Currently returns 3.");
				customNumber = JOptionPane.showInputDialog(frame, "Enter the number of colors you " +
						"would like to generate.", "Custom Value", JOptionPane.PLAIN_MESSAGE);

				try{
					int testing;

					testing = parseInt(customNumber);

					if(testing > 20)
					{
						JOptionPane.showMessageDialog(frame, "Too many colors.");
						colors = 0;
					}
					else {

						JOptionPane.showMessageDialog(frame, "Generating swatch with " + testing +
								" colors. UNAVAILABLE, DEFAULTS TO 3");


						//colors = testing;
						colors = 3;
					}
				}
				catch (NumberFormatException nfe){
					JOptionPane.showMessageDialog(frame, "This is not a number value." +
							"\nDefaulting to 3 colors.");
					colors = 3;
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(frame, "This is not a number value." +
							"\nDefaulting to 3 colors.");
					colors = 3;
				}
				//colors = 3;
				break;
			default:
				colors = 0;
				break;
		}


		return colors;
	}

	/**
	 * When this is turned on it changes the way the swatches work.
	 *
	 * If activated:
	 * - Monochrome only prints two colors: the shade and it's complementary color.
	 * - Adjacent adds one more color to the palette-- the base color's complementary color.
	 * - Triad switches to Complementary Triad.
	 * - Does not affect Multi-color.
	 *
	 */
	private void complementaryOn(){

		if(complementary == false){
			complementary = true;
		}
		else if (complementary == true){
			complementary = false;
		}

	}
	
}
