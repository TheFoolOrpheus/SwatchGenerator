package colorCreator;

import java.awt.ComponentOrientation;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
		 * Not sure why, I need to read up on GridBagLayout to
		 * see what the purpose of doing this kind of thing is.
		 */
		boolean shouldFill = true;
		boolean shouldWeightX = true;
	
		/*
		 * Creating the frame, which I might have to make non-resizable.
		 * The layout is GridBag.
		 *
		 * I need to add a menubar with a bar that offers help so that people don't get confused by
		 * what some of these buttons do.
		 */
		setTitle("Swatch Creator");
		//Prev. size 1000 x 1000
		setSize(525,525);
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

		
		c.gridx = 3;
	    c.gridy = 0;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    c.weightx = c.weighty = 0.0;
	    this.add(new JButton("PlaceHolder 1"), c);
		
		c.gridx = 4;
	    c.gridy = 0;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    c.weightx = c.weighty = 0.0;
	    this.add(new JButton("PlaceHolder 2"), c);

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
				MonochromeSwatch ms = new MonochromeSwatch(paletteNumber(), complementary);
				/*
				 * OK, I need this to draw onto a panel but for now I'm just going to print out
				 * the color names and shiiiiiii-
				 */
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
				isPastel();
			}
		});

		add(adjacent, c);


		/*
		 * Creating a Triad color button. Should pass isPastel and
		 *  paletteNumber to the adjacent class.
		 */
		JButton triad = new JButton("Triad");
		triad.setActionCommand("Triad");
		c.gridx = 2;
	    c.gridy = 0;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    c.weightx = c.weighty = 0.0;
		triad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				isPastel();
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
