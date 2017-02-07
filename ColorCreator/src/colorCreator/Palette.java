package colorCreator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
		
		setTitle("Swatch Creator");
		setSize(1000,1000);
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		//Testing
		JButton newButton = new JButton("Test Button");
		newButton.setActionCommand("Test Button");
		newButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				isPastel();
			}
		});
		add(newButton);
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
		frame.setVisible(true);
		frame.setDefaultCloseOperation(HIDE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setAlwaysOnTop(true);
		/*JFrame frame2 = new JFrame();
		frame2.setVisible(true);
		frame2.setDefaultCloseOperation(HIDE_ON_CLOSE);
		frame2.setLocationRelativeTo(null);
		frame2.setAlwaysOnTop(true);*/
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
	
	
	

}
