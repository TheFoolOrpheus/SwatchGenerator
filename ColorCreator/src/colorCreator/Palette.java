package colorCreator;

import javax.swing.JFrame;

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
	}

}
