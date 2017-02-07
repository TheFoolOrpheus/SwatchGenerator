package colorCreator;

import java.awt.ComponentOrientation;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
	
	public Palette(){
		
		paletteWindow();
		
	}
	
	private void paletteWindow(){
		
		
		/*
		 * Not sure why, I need to read up on GridBagLayout to
		 * see what the purpose of doing this kind of thing is.
		 */
		boolean shouldFill = true;
		boolean shouldWeightX = true;
		
		/*
		 * Creating the frame, which I might have to make non-resizable.
		 * The layout is GridBag.
		 */
		setTitle("Swatch Creator");
		setSize(1000,1000);
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		if(shouldFill){
			constraints.fill = GridBagConstraints.HORIZONTAL;
		}
				
		/*
		 * Creating the monochrome button. Should pass isPastel and
		 * paletteNumber to the monochrome class.
		 * 
		 */
		JButton monochrome = new JButton("Monochrome");
		monochrome.setActionCommand("Monochrome");
		if(shouldWeightX){
			constraints.weightx = 0.5;
		}
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		//constraints.fill = GridBagConstraints.HORIZONTAL;
		monochrome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				isPastel();
				paletteNumber();
			}
		});
		add(monochrome, constraints);
		
		/*
		 * Creating a adjacent color button. Should pass isPastel and
		 *  paletteNumber to the adjacent class.
		 */
		JButton adjacent = new JButton("Adjacent");
		adjacent.setActionCommand("Adjacent");
		if(shouldWeightX){
			constraints.weightx = 0.5;
		}
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.gridwidth = GridBagConstraints.RELATIVE;
		//constraints.fill = GridBagConstraints.HORIZONTAL;
		adjacent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				isPastel();
			}
		});
		add(adjacent, constraints);
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
				JOptionPane.showMessageDialog(frame, "An equal number of tints and shades will" +
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

					JOptionPane.showMessageDialog(frame, "Generating swatch with " + testing +
							" colors.");

					colors = testing;
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
				JOptionPane.showMessageDialog(frame, "Defaulting to swatch with 4 " +
						"colors.");
				colors = 4;
				break;
		}


		return colors;
	}
	

}
