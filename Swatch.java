package colorCreator;

import javax.swing.*;
import java.awt.*;

/**
 *
 * Swatch is called by all the Swatch type classes (Monochrome, Adj, etc. etc.)
 *
 * It contains the methods that converts to and from hexcodes, determines if the number of colors
 * are even or odd, etc. etc.
 *
 * In other words, anything shared by all of the  Swatch Type classes should be in here.
 *
 * Created by adani14 on 2/7/2017.
 */
public interface Swatch {


    Color[] colors = null;
    Color baseColor = new Color(0,0,0);
    /**
     *
     * When given a color, finds the hexadecimal associated with it.
     *
     * @param color a color value
     * @return a string with the hexadecimal value
     */
    default String colorToHex(Color color){
        return String.format("%02X%02X%02X", color.getRed(), color.getGreen(), color.getBlue());
    }

    /**
     * When given an rgb int,  returns the hexadecimal value associated with it
     * @param RGB an rgb int
     * @return a string with the hexadecimal value
     */
    default String RGBToHex(int RGB){
        Color color = new Color(RGB);
        return colorToHex(color);
    }

    /**
     * When given r, g, b values, returns the hexadecimal value associated with it
     * @param r value for red
     * @param g value for blue
     * @param b value for green
     * @return a string with the hexadecimal value or null
     */
    default String decimaltoHex(int r, int g, int b){

        if((r >= 0 && r <=255) && (r >= 0 && r <=255) &&(r >= 0 && r <=255))
        {
            Color color = new Color(r, g, b);
            return colorToHex(color);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "This is not a valid color.", "Invalid entry", JOptionPane.ERROR_MESSAGE);
            return null;
        }

    }

    /**
     * Changes a hex string to a int [] representing the R, G, and B values
     * @param hex a hexcode
     * @return RGB an int array with r, g, and b values
     */
    default int[] hexToDecimal(String hex){
        int[] RGB = new int[3];
        RGB[0] = Integer.parseInt(hex.substring(0,2), 16);
        RGB[1] = Integer.parseInt(hex.substring(2,4), 16);
        RGB[2] = Integer.parseInt(hex.substring(4), 16);

        return RGB;
    }

    /**
     * Chooses a random color for the user.
     * @return color a random color
     */
    default Color randomColorChooser()
    {

        int min = 0;
        int max = 255;

        int red = min + (int)Math.random() * ((max - min) + 1);
        int green =  min + (int)Math.random() * ((max - min) + 1);
        int blue =  min + (int)Math.random() * ((max - min) + 1);

        Color color = new  Color(red, blue, green);

        return color;


    }

    /**
     * Determines whether or not a number is odd or even.
     * @param number any number
     * @return a boolean that tells us whether the number is even or odd.
     */
    default boolean isOdd(int number){

        if(number%2 == 0){
            return false;
        }
        else {
            return true;
        }
    }

    /**
     *
     * Allows the user to choose a color. If the information entered is unusable, then returns null.
     *
     * @return a color or null
     */
    default Color chooseColor(){

    	JFrame frame = new JFrame();
    	frame.setDefaultCloseOperation(frame.HIDE_ON_CLOSE);
    	frame.setLocationRelativeTo(null);
    	frame.setAlwaysOnTop(true);
        int opt = 0;
        String options[] = {"RGB", "Hexcode"};
        opt = JOptionPane.showOptionDialog(frame, "Choose a method for entry.", "Base color",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        
        /*
         * This probably works and all, but... I think I can use JColorChooser for RGB. There's no way to use it for
         * hex, though.
         */

        switch(opt) {
            case 0:
                /*
                 * I would like to use the regular expressions \p{punct} and \p{blank} so that spaces can be used as
                 * well, but for right now let's just stick with this particular implementation.
                 */
                String RGBval;
                RGBval = JOptionPane.showInputDialog(frame, "Enter the RGB value of your" +
                                " color.\nSeparate the values with a \",\" and do not use spaces, like so:\n 10,10,10",
                        "Hexcode", JOptionPane.PLAIN_MESSAGE);
                String RGB[] = RGBval.split(",");

                try {
                    Color color = new Color(Integer.parseInt(RGB[0]), Integer.parseInt(RGB[1]), Integer.parseInt(RGB[2]));
                    return color;
                } catch (Exception e) {
                    System.err.println("Length of RGB[]: " + RGB.length + "\nItems in RGB[0], RGB[1], and RGB[2]:" +
                            RGB[0] + " " + RGB[1] + " " + RGB[2] + ".");
                }

                break;
            case 1:
                String hexcode;
                hexcode = JOptionPane.showInputDialog(frame, "Enter the hexcode value of your" +
                        " color.", "Hexcode", JOptionPane.PLAIN_MESSAGE);

                int[] RGBint = new int[0];
                try {
                    RGBint = hexToDecimal(hexcode);
                    Color color = new Color(RGBint[0], RGBint[1], RGBint[2]);
                    return color;
                } catch (Exception e) {
                    System.err.println("Length of RGBint[]: " + RGBint.length + "\nItems in RGBint[0], RGBint[1], and RGBint[2]:" +
                            RGBint[0] + " " + RGBint[1] + " " + RGBint[2] + ".");
                }

                break;
            default:
            	
            	String hexcodeDefault;
                hexcode = JOptionPane.showInputDialog(frame, "Enter the hexcode value of your" +
                        " color.", "Hexcode", JOptionPane.PLAIN_MESSAGE);

                int[] RGBintDefault = new int[0];
                try {
                    RGBintDefault = hexToDecimal(hexcode);
                    Color color = new Color(RGBintDefault[0], RGBintDefault[1], RGBintDefault[2]);
                    return color;
                } catch (Exception e) {
                    System.err.println("Length of RGBint[]: " + RGBintDefault.length + "\nItems in RGBint[0], RGBint[1], and RGBint[2]:" +
                            RGBintDefault[0] + " " + RGBintDefault[1] + " " + RGBintDefault[2] + ".");
                }

                break;
        }
        return null;
    }
    
    default Color[] divisionOfColors(int numColors, Color color, float factor){
    	
    	
    	/*
         * Asks the user if they want more tints or more shades.
         * After wards, begins processing the colors.
         * Uses the abstract method in swatch, create colors.
         * Actually, I'm going to make a seperate method called
         * odd create colors. This method will take in two ints,
         * even and odd, plus a boolean, moreTint. If true, then
         * check which number is larger and use that to create tints.
         * If false, check which number is larger and use that to
         * create shades.
         */

        /*
         * So the user is asked "do you want more tints or more shades"
         * in this method, and then is handed off to the oddCreateColors
         * method.
         */
    	
    	//First brings up a JOptionPane that asks what the person wants.
    	JFrame frame = new JFrame();
    	boolean moreTint = false;
    	
    	Object[] options = {"Tints",
        "Shades"};
    	
    	int n = JOptionPane.showOptionDialog(frame,
    		    "You've chosen an odd number of colors. Would you like more tints or shades?",
    		    "Tints and Shades",
    		    JOptionPane.YES_NO_OPTION,
    		    JOptionPane.QUESTION_MESSAGE,
    		    null,     //do not use a custom Icon
    		    options,  //the titles of buttons
    		    options[0]); //default button title
    	
    	//switch around the answer, use boolean MoreTint
    	switch(n){
    	
    		case (JOptionPane.YES_OPTION):
    			moreTint = true;
    			break;
    		case (JOptionPane.NO_OPTION):
    			moreTint = false;    			
    			break;
    		default:
    			moreTint = false;
    			break;
    		    	
    	}
    	
    	//call oddCreateColors
    	    	
    	return oddCreateColors(moreTint, color, numColors, factor);
    }

    /**
     *
     * @param color
     * @return HSV, a float array with the following values in order: Hue, Saturation HSV, Saturation HSI,
     * Saturation HSL, Intensity, and Lightness
     */
    default float[] getHSV(Color color){


        float HSV[] = new float[6];

        /*
         *
         * I'm more or less using this: http://www.camick.com/java/source/HSLColor.java
         * but this is subject to change if it's not working the way
         * I want it to.
         *
         * OK, so apparently you need to get RGB values  in the range of 0 -1.
         * I don't know how you do that via code, but apparently
         * getRGBColorComponents does that.
         *
         */

        float components[] = color.getRGBColorComponents(null);
        float r = components[0];
        float g = components[1];
        float b = components[2];

        /*
         * HSL uses the largest and smallest RGB values, apparently.
         * I need to look into that. According to wikipedia it's vector
         * math, ugh. see here: https://en.wikipedia.org/wiki/HSL_and_HSV#Color-making_attributes
         *
         * So wikipedia claims that hue and chroma are related. Hue is the
         * angle of the vector to a point in the projection (red being 0 degrees)
         * and chroma is the distance from the point from the origin. So, because
         * of this, M or max = the maximum value between r g and b, and m or min
         * = the minimum value between r g and b. And then C, chroma, is M - m,
         * which means any color that has r = g = b= has 0 chroma. Thus the colors
         * (r,g,b) and (r-m,g-m,b-m) project on the same point, and have the same
         * chroma.
         *
         * ATM I don't care about chroma. Also it seems like there's no way to figure
         * out the hue from the chroma. For the moment, this is just the way to get the
         * min and max values.
         */

        float min = Math.min(r, Math.min(g,b));
        float max = Math.max(r, Math.max(g,b));

        /*
         * OK SO. This is where things are getting a bit crazy, yeah? So right now, I'm
         * using an r,g,b that are on a scale from 0 to 1. But there is a way to do this
         * with degrees. I just don't know how useful it is, but I think it might be helpful
         * to understand. Now, the thing is that H or hue = 60 degrees times the derivative of
         * H. The derivative of H is a piecewise equation. So like I said before, chroma
         * and hue more or less don't seem to have any relation outside of their definintions
         * so as it turns out, if C = 0, H' is undefined.
         *
         * If M = r, so when r is the largest value in the color,((g-b)/c))%6 is the equation
         * used. I want to test this, so... let's use the color red, ff0000 as an example for this
         * Red is composed of 100% red, 0% green, and 0% blue. M, in this case, would be 100,
         * and m would be 0, so c = M - m = 100 - 0 = 100. Thus... the equation is ((0-0)/100)%6.
         * so... then the hue is (0/100)%6. 0%6 is... 0 right? So then H = 60 degrees times 0, which
         * is 0, so... is the hue of red 0 degrees? Yes, this checks out.
         *
         * if M = g, or when g is the largest value in the color, ((B-R)/C)+ 2 is the equation
         * used. I may test this in a bit.
         *
         * Finally, if M = b, or b is the largest value in the color, ((R-G)/C)+ 4 is the equation
         * used. Again, may test later.
         *
         * Again this is for use SPECIFICALLY with percentages. And tbh, this is way easier for me
         * to understand than the stuff in the code before, like I don't like vector math,
         * but I understand it.
         *
         * Now the issue is that when you have a value between 0 and 1, technically isn't that a percentage?
         * I guess, yes.. the percentage is only if the number is in the form 0.xx, where xx are real numbers
         * and so IN THAT REGARD, if I could truncate the values... couldn't I use... this kind of thing?
         *
         * Because otherwise I have to use the code on the website, which states...
         *
         * if M = m, H = 0. So, if r = 0, g = 0, and b = 0, the hue itself is 0. That holds up.
         * black has a hue of 0. I think that's the monochrome black to white color scheme... all the grays
         * seem to be hue 0. So I'll say yes, that holds up.
         *
         * if M = r, then... Well, the muliplcation by 60 part of these, I understand
         * that as just being the way you find the hue from the derivative of hue, so disregarding that,
         * it seems like the equations used are the same as the other one? OK, so... I guess they ARE using
         * percentages? The website that directed me to this code wasn't very clear. Mmk, so I
         * understand this but there are some fundamental differences between the two.
         *
         * So previously, when M = r we used ((g-b)/c))%6. But in this case, without using chroma,
         * if M = r, then we say (960 * (g-b)/ (max - min)) + 360) % 360.
         *
         * Like... OK, but why not just create chroma then? Like doesn't that make the most sense?
         *
         * I think I'll just create a chroma and go with that.
         *
         */

        float chroma = max - min;

        float hue = 0;
        float dHue = 0;

        if(max == min){
            hue = 0;
        }
        else if (max == r){
            dHue = ((g - b)/ chroma)%6;
            hue = 60 * dHue;
        }
        else if(max == g){
            dHue = ((b - r)/ chroma) + 2;
            hue = 60 * dHue;
        }
        else if (max == b){
            dHue = ((r - g)/ chroma) + 4;
            hue = 60 * dHue;
        }
        HSV[0] = hue;

        /*
         * So, I wrote the comment for saturation first, but I need to do the
         * stuff for Lightness and Intensity first.  Let's do it!
         *
         * So, there's intensity, which is the average of the rgb components. That's
         * easy.
         *
         * Next we're using lightness, which is the average of the minimum and maximum
         * values of  the color components. Also easy!
         *
         */

        float intensity = (r + g + b)/3;
        HSV[4] = intensity;
        float lightness = (max + min)/2;
        HSV[5] = lightness;

        /*
         * OK, saturation time.
         *
         * The code in the link doesn't explain WHY their stuff works, like
         * why do we do what we do in that code. So again, I went to wikipedia to
         * determine what the reasoning behind determining saturation from rgb
         * values is.
         *
         * So according to Wikipedia, V, or value, is the same as the largest component
         * so they use V to mean M. I'm gonna ignore that for now, like... it doesn't
         * matter since I already have M.
         *
         * Essentially then... S, or saturation, in th HSV model, is 0 if V = 0, and
         * otherwise, is C/V.
         *
         * HOWEVER, in HSL, S depends on L, or lightness. If L = 1, then S = 0, otherwise
         * S = C/(1 - |2L -1|).
         *
         * AND in the HSI method, it's dependent on I or intensity, where if I = 0, S = 0, else
         * S = 1 - (m/I).
         *
         * I chose HSV as the method name for this... but which of these would I use if
         * I wanted to make something useful for pixel artists? I guess I'm going to have to
         * research that a bit more...
         *
         * Ugh, for now, I think I'll calculate all of the values. It might be helpful,
         * when I'm printing out data to have these values... we'll just stick with that story,
         * and not that I have no idea what I'm doing. ._. Yeah, that's it.
         *
         */

        float saturationV = 0;
        if(max == 0){
            saturationV = 0;
        }
        else{
            saturationV = chroma/ max;
        }
        HSV[1] = saturationV;

        float saturationI = 0;
        if(intensity == 0){
            saturationI = 0;
        }
        else{
            saturationI = 1 - (min/intensity);
        }
        HSV[2] = saturationI;

        float saturationL = 0;
        if(lightness == 1){
            saturationL = 0;
        }
        else{

            float light = 2 * (lightness) - 1;

            saturationL = chroma/(1 - Math.abs(light));
        }
        HSV[3] = saturationL;


        return HSV;
    }

    /**
     * The method that computes the colors being created.
     * @param color the base color
     * @return an array of colors
     */
    default Color[] createColors(Color color, int numColors, float factor){
    	
    	int half = numColors/2;
    	int index = half;
    	Color[] swatch = new Color[numColors];
    	
    	for(int i = 0; i < half; i++){
    		
    		if(i == 0){
    			swatch[i] = darkenBy(color, factor);
    		}
    		else{
    			swatch[i] = darkenBy(swatch[i-1], factor);
    		}
    		
    	}
    	swatch[index] = color;
    	index++;
    	for(int i = half + 1; i < numColors; i++){
    		if(i == half + 1){
    			swatch[i] = brightenBy(color, factor);
    		}
    		else{
    			swatch[i-1] = brightenBy(swatch[index], factor);
    		}
    		index++;
    	}
    	
    	
    	return swatch;
    }

    /**
     * A method that computes colors being created when the number of colors that will be
     * generated is odd.
     * @param color the base color
     * @return an array of colors
     */
    default Color[] oddCreateColors(boolean moreTint, Color color, int numColors, float factor){

        /*
         * How it works:
         * 1) check moreTint. If moreTint == true, use "more" value for tints.
         * 2) create shades first. Store dark colors in an array called shades. The shades array
         * creates dark colors as they are created, which is lightest to darkest. This array is
         * size less.
         * 3) create tints second. Store tints in an array called tints, for now. this will save
         * in the correct order. This array is size more.
         * 4) At the end of the arrays, add all the shades in reverse order to the final array, swatch. then,
         * add the original colors. Then add all the tints to swatch. Return swatch.
         * 5) If moreTint == false, use the more value for shades.
         * 6) Still creating shades first, this time the shades array is of size more.
         * 7) Tints created second, this time the tints array is of size less.
         * 8) Add shades in reverse order to swatch, then add original color, then tint. Return swatch.
         *
         * NOTES:
         *
         * If possible, a sort algorithm would be great here. I think to do that I would need to know how getRGB,
         * which returns an int, works. Then I would just need to have one array, so, here are the steps for THAT:
         *
         * 1) check moreTint. If moreTint == true, use more value for tints. else, use more value for shades.
         * 2) add the original color to swatch.
         * 3) create shades, then tints.
         * 4) run some kind of sort algorithm? Bubble sort takes 2n time... maybe comb sort? I'm never done a comb sort before.
         *
         * In order to do the second one, which is better because I don't want to write code forever here, I need to know
         * how getRGB works AND how combSort works. Best case is n log n, which is good, but... on average it's
         * n^2/ 2^p, where p = the number of increments.
         *
         * The way that I want it to be sorted though, more or less the only values that will need to be sorted are in the beginning
         * of the list, on average, there will be maybe 2 or three of them...
         *
         * So Comb might not be the best sort method here... Maybe just bubble sort? Because like, what should be moving are all at the
         * beginning of the list and will be swapped with each other.
         *
         * Tints are always going to come out in the right order so I feel like a quicksort might not be reasonable. If I made the pivot
         * the original color... except for the fact that I don't always know where that will lie, 1, and 2 if I add it initially then like
         * whatever...
         *
         * I dunno. Bubble sort for now. If it takes too long or starts getting crazy complex, we switch to a new sorting method.
         */


    	int more = (numColors/2) + numColors%2;
    	int less = numColors/2;
    	Color[] swatch = new Color[numColors];
    	int index;

    	//set the first array index to original color
    	swatch[0] = color;

    	//Check more tint
    	if(moreTint) {
            //loop until we reach the end of the array.
            for (int i = 1; i < swatch.length; i++) {
                //Until i = less, darken.
                if (i <= less) {
                    swatch[i] = darkenBy(color, factor);
                }
                //when i is greater than less, but less than more, lighten
                else if (i > less && i < more) {
                    swatch[i] = brightenBy(color, factor);
                }
            }
        }
        //moreTint == false
        else{
            //loop until the end of the array
    	    for (int i = 1; i < swatch.length; i++){
    	        //until i = more, darken.
                if(i <= more){
                    swatch[i] = darkenBy(color, factor);
                }
    	        //when i is greater than less, but less than more, lighten
                else if(i > less && i < more){
                    swatch[i] = brightenBy(color, factor);
                }

            }

        }



    	
    	return swatch;
    }

    /**
     * Allows the user to determine the percentage of white to add to the colors in question.
     *
     * If the color given is white, returns white.
     * @param factor the percentage of white being added to the color
     * @return a lighter color.
     */
    default Color brightenBy(Color color, float factor){
    	  /*
         * Steps:
         * 255 - previous value
         * multiply by factor
         * add to previous value
         */

        int r;
        int g;
        int b;
        Color newTint;

        if(color == Color.WHITE){
            newTint = Color.WHITE;
        }
        else {
            //Clean up this later
            float r1 = (255f - (float) color.getRed()) * (factor);
            float g1 = (255f - (float) color.getGreen()) * (factor);
            float b1 = (255f - (float) color.getBlue()) * (factor);

            //Can combine with above
            r = Math.round(r1 + (float) color.getRed());
            g = Math.round(g1 + (float) color.getGreen());
            b = Math.round(b1 + (float) color.getBlue());
            newTint = new Color(r, g, b);
        }

        return newTint;
    }

    /**
     * Allows the user to determine the percentage of black to add to the colors in question.
     * Uses a float because decimal places... might switch to a double, I don't need repeating
     * numbers all over the place.
     * @param factor the percentage of black being added to the color
     * @return a darker color.
     */
    default Color darkenBy(Color color, float factor){
    	
    	/*
         * Steps:
         * multiply by factor
         *
         */

    	//I can probably just do this in one line when I come back to clean up
        float r1 = (float)color.getRed() * (factor);
        float g1 = (float)color.getGreen() * (factor);
        float b1 = (float)color.getBlue() * (factor);
        
        //Combine with the above
        int r = Math.round(r1);
        int g = Math.round(g1);
        int b = Math.round(b1);
        Color newShade =  new Color(r, g, b);

        return newShade;
    }
    
    default float getFactor(){
    	
    	/*
    	 * Get a percentage. Give them options like 1/4, 1/2, and 3/4,
    	 * and then for custom we'll figure it out later.
    	 *
    	 */
    	JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setAlwaysOnTop(true);
		int opt = 0;
		float factor = 0.25f;
		String[] options = { "1/4", "1/2", "3/4"};
		opt = JOptionPane.showOptionDialog(frame, "We change the hue of your color by a factor.\n" +
		"By what factor would you like your color changed?", "Factor",
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
				null, options, options[0]);
		
		switch(opt){
		
			case 0:
				
				factor = 1/4f;
				
				break;
			case 1:
				
				factor = 1/2f;
				
				break;
			case 2:
				
				factor = 3/4f;
				
				break;
			default:
				
				factor = 1/4f;
				
				break;
		
		}
    	
    	return factor;
    }
    
    /**
     * pastelColors runs by taking in some color and then running brightenBy.
     *
     * It creates a color array from darkest to lightest.
     * @param color
     * @return
     */
    default Color[] pastelColors(Color color){

        return new Color[0];
    }
    
    /**
     * 
     * @param color
     * @return
     */
    Color complementaryColors(Color color);
    
    /**
     * 
     * @return
     */
    String toString();


}