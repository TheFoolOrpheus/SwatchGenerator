package colorCreator;

import java.awt.ComponentOrientation;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import javax.swing.*;
import java.awt.Insets;


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
	
	public Palette(){
		
		paletteWindow();
		
	}
	
	private void paletteWindow(){

		createMenuBar();

		/*
		 * Creating a way to save the palettes into a text file.
		 * This should append depending on date... so, it should:
		 * 1) get today's date
		 * 2) create a file based on today's date
		 * 3) add the new swatch to the text file
		 *
		 * Then, the old complementary button will become a "Saved
		 * Swatch" button. This will:
		 *
		 * 1) Ask the user to pick a file (jFileChooser)
		 * 2) After the file is chosen, look for a regular expression
		 * 3) Split the file based on that regular expression
		 * 4) Show the user all of the swatches, split by the regular expression
		 *
		 */
		LocalDate ld = LocalDate.now();
		String fileDate = DateTimeFormatter.ofPattern("ddmmyyyy").format(ld);

		try{

			Files file = new Files();
			if(file.exists(Paths.get("./swatches"))){

			}
			else{
				Path directory = Files.createDirectory(Paths.get("./swatches"));
			}


		}
		catch(FileAlreadyExistsException faee){
			faee.getMessage();
		}
		catch(IOException ioe){
			ioe.getMessage();
		}

		
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
		setAlwaysOnTop(false);
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
				
			}
		});
	    this.add(multi, c);
		
	    
	    JButton shift = new JButton("Hue Shift Ramp");
		shift.setActionCommand("Hue Shift Ramp");
		shift.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
			}
		});
		c.gridx = 4;
	    c.gridy = 0;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    c.weightx = c.weighty = 0.0;
	    this.add(shift, c);

	    JColorChooser panel = new JColorChooser();
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

				MonochromeSwatch ms = new MonochromeSwatch(paletteNumber());
				JOptionPane.showMessageDialog(frame, ms.toString());
				
			}
		});
        add(monochrome, c);
		
        
        /*
		 * Creating a adjacent color button. Should pass isPastel and
		 *  paletteNumber to the adjacent class.
		 */
		JButton harmonies = new JButton("Harmonies");
		harmonies.setActionCommand("Harmonies");

		c.gridx = 1;
	    c.gridy = 0;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    c.weightx = c.weighty = 0.0;
		harmonies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				HarmoniousSwatch hs = new HarmoniousSwatch(paletteNumber());
				JOptionPane.showMessageDialog(frame, hs.toString());

			}
		});

		add(harmonies, c);


		/*
		 * Creating a Triad color button. Should pass isPastel and
		 *  paletteNumber to the adjacent class.
		 */
		JButton gradient = new JButton("Gradient");
		gradient.setActionCommand("Gradient");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
	    c.gridy = 0;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    c.weightx = c.weighty = 0.0;
		gradient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){

			}
		});

		add(gradient, c);
		
		/*
		 * Creating the complementary colors button. Should go across the bottom
		 * entirely but... I mean of course it isn't, I suck at gridBag.
		 */
		JButton compColor = new JButton("Saved Swatches");
		compColor.setActionCommand("Saved Swatches");
		c.gridx = 0;
	    c.gridy = 5;
	    c.gridwidth = 5;
	    c.gridheight = 1;
		compColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){

			}
		});

		add(compColor, c);
		

	}
	
	private void createMenuBar(){
		
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
		int colors;

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(HIDE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setAlwaysOnTop(true);
		int opt;

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
				String customNumber;
				//JOptionPane.showMessageDialog(frame, "This is for custom colors. Currently returns 3.");
				customNumber = JOptionPane.showInputDialog(frame, "Enter the number of colors you " +
				"would like to generate. \nMust be 20 or less.", "Custom Value", JOptionPane.PLAIN_MESSAGE);

				try{
					int testing;

					testing = parseInt(customNumber);

                    if(testing > 20)
                    {
                        JOptionPane.showMessageDialog(frame, "Too many colors.");
                        colors = 0;
                    }
                    else {

                        //JOptionPane.showMessageDialog(frame, "Generating swatch with " + testing +
                               // " colors. UNAVAILABLE, DEFAULTS TO 3");
                        colors = testing;
                        //colors = 3;
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
	 * Used in AdjacentSwatch, this is based off of the theories posited here:
	 *
	 * http://www.tigercolor.com/color-lab/color-theory/color-harmonies.htm
	 *
	 * Essentially, rather than choosing only a number, the user chooses a type of swatch that they
	 * would like as well. This chooses the colors based on the mother color.
	 *
	 * The types are:
	 * Complimentary: Gets motherColor, adds one more color.
	 * Analogous: Gets motherColor, adds two more colors.
	 * Triad: Gets motherColor, adds two more colors.
	 * Rectangle: Gets motherColor, adds three more colors.
	 * Square: Gets motherColor, adds three more colors.
	 * Adjacent: I'm not sure, we'll see.
	 * @return
	 */
	private String paletteType(){

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(HIDE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setAlwaysOnTop(true);
		int opt;
		String type;

		String[] options = { "Complementary", "Analogous", "Triad", "Rectangle", "Square"};
		opt = JOptionPane.showOptionDialog(frame, "Please choose the size of your palette.", "Number of colors",
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
				null, options, options[0]);

		switch (opt){
			case 0:
				type = "Complementary";
				break;
			case 1:
				type = "Analogous";
				break;
			case 2:
				type = "Triadic";
				break;
			case 3:
				type = "Rectangle";
				break;
			case 4:
				type = "Square";
				break;
			default:
				type = null;
				break;
		}

		return type;
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

		if(!complementary){
			complementary = true;
		}
		else{
			complementary = false;
		}

	}
	

}
