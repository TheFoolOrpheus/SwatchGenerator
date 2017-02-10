package colorCreator;

import javax.swing.*;
import java.awt.*;

/**
 * Created by adani14 on 2/7/2017.
 */
public class MonochromeSwatch implements Swatch {

    Color baseColor;
    Color colors[];

    /**
     * Default constructor, unsued for now. Not sure if I need it?
     */
    public MonochromeSwatch(boolean complementary){
    	complementaryColors(baseColor);
    }

    /**
     * This is the constructor I intend to use for the most part, takes in the number of
     * colors, whether or not the colors should be complementary, and eventually whether or not
     * the palette will be pastel.
     * @param numColors the number of colors the user wants.
     * @param complementary whether or not the swatch is made of complimentary colors
     */
    public MonochromeSwatch(int numColors, boolean pastel){


        /*
         *  Ask the user if they want a random color or if they have a base color in mind.
         * - If they don't have a color in mind, run random color chooser.
         *  - Ask if they like the chosen color.
         *  - If no, re-run the random color chooser.
         * -If they do have a color in mind, have them input the color.
         * Check complementary. If true, then disregard numColors, run complementaryColors.
         * If false, run createColors.
         *
         */

        //A message dialog here, or whatever, I'll get to it later. The choice will be stored in int choice.
        boolean odd = isOdd(numColors);
        float factor = 0;
        JFrame frame = new JFrame();
        frame.setLocationRelativeTo(null);
        frame.setAlwaysOnTop(true);

            /*
             * Give the user a choice of entering a hexcode or a RGB value.
             */
        	int opt = 0;
            String options[] = {"Yes", "No"};
            opt = JOptionPane.showOptionDialog(frame, "Do you have a color in mind as your base color?", "Base color",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            
            switch(opt){
            
            	case 0:       		
            		baseColor = chooseColor();
            		break;
            	case 1:
            		baseColor = randomColorChooser();
            		break;
        		default:
        			baseColor = randomColorChooser();
        			break;
            
            }
            
            JOptionPane.showMessageDialog(frame, "Your base color is " + decimaltoHex(baseColor.getRed(),
            		baseColor.getGreen(), baseColor.getBlue()));

            //not sure what kind of dialog here
            if(pastel == true)
            {
            	JOptionPane.showMessageDialog(frame, "Running pastelColors." + "\nBase Color: " + decimaltoHex(baseColor.getRed(),
                		baseColor.getGreen(), baseColor.getBlue()));
                pastelColors(baseColor);
            }
            else if(pastel == false){
            	
            	JOptionPane.showMessageDialog(frame, "Getting factor.");
            	factor = getFactor();
            	
            	//createColors(baseColor, numColors, factor);
            	JOptionPane.showMessageDialog(frame, "Checking to see if odd");
                if(odd == true){
                    colors = divisionOfColors(numColors, baseColor, factor);
                }
                else{
                    colors = createColors(baseColor, numColors, factor);
                }
            }


    }

    
    @SuppressWarnings("static-access")
	public Color complementaryColors(Color color){

        /*
         * Steps:
         * - Take Hue
         * - Subtract 180 or add 180 (the number needs to be between 0 and 360)
         * - Create new color
         *
         * OK, so...hue is expressed in degrees, yeah? Red's hue is 0, and cyan is 180.
         * Green is 120. Blue is 240. Yellow is 60. Magenta is 300. OK, and obv. red is
         * also 360, but I assume they just go with 0.
         *
         * Unfortunately, Color.java doesn't have a getHSV/HSB/HSL method. That means I
         * have to get those myself via math. So in swatch I'm going to create a default
         * method that gets these values but the values themselves are float values. I should
         * consider making this method also a default one? I mean just about every swatch uses
         * this.
         *
         */
    	
    	float[] HSV = getHSV(color);

        Color complement = new Color(0, 0, 0);
        
        int rgbInt = 0;
        float newHue = HSV[0] - 180;
        
        if((newHue) < 360 && (newHue) > 0){
        	
        	rgbInt = complement.HSBtoRGB(newHue, HSV[3], HSV[5]);
        	complement = new Color(rgbInt);
        }
        else{
        	
        	newHue = HSV[0] + 180;
        	rgbInt = complement.HSBtoRGB(newHue, HSV[3], HSV[5]);
        	complement = new Color(rgbInt);
        	
        }

        return complement;
    }
    
    public Color pastelColors(Color color){
    	
    	return new Color(0,0,0);
    }
    
    public String toString(){
    	
    	String information = "Colors: ";
    	
    	for(int i = 0; i < colors.length; i++)
    	{
    		information = information + " " + colorToHex(colors[i]) + " ";
    	}
    	
    	information = information + "\nBase Color: " + colorToHex(baseColor);
    	
    	
    	return information;
    }



}
