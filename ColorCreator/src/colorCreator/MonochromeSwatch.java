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
    public MonochromeSwatch(){

    }

    /**
     * This is the constructor I intend to use for the most part, takes in the number of
     * colors, whether or not the colors should be complementary, and eventually whether or not
     * the palette will be pastel.
     * @param numColors the number of colors the user wants.
     * @param complementary whether or not the swatch is made of complimentary colors
     */
    public MonochromeSwatch(int numColors, boolean complementary){


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
        int choice = 1;
        boolean odd = isOdd(numColors);

        if(choice == JOptionPane.YES_OPTION){

            /*
             * Give the user a choice of entering a hexcode or a RGB value.
             */

            //not sure what kind of dialog here
            if(complementary == true)
            {
                complementaryColors(baseColor);
            }
            else if(complementary == false){
                createColors(baseColor);

                if(odd == true){
                    divisionOfColors();
                }
                else{
                    colors = createColors(baseColor);
                }
            }


        }
        else if(choice == JOptionPane.NO_OPTION){

            randomColorChooser();

        }
        else{
            //Not sure what to do if they cancel out... just exit?
        }







    }

    public Color[] divisionOfColors(){

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

        return new Color[0];
    }

    /**
     * The method that computes the colors being created.
     * @param color the base color
     * @return an array of colors
     */
    public Color[] createColors(Color color){


        return new Color[0];
    }

    /**
     * A method that computes colors being created when the number of colors that will be
     * generated is odd.
     * @param color the base color
     * @return an array of colors
     */
    public Color[] oddCreateColors(Color color){

        return new Color[0];
    }

    /**
     * Allows the user to determine the percentage of white to add to the colors in question.
     * Uses a float because decimal places... might switch to a double, I don't need repeating
     * numbers all over the place.
     * @param factor the fraction value of white being added to the color
     * @param color the base color
     * @return a lighter color.
     */
    public Color brightenBy(Color color, float factor){

        /*
         * Steps:
         * 255 - previous value
         * multiply by factor
         * add to previous value
         */

        double r = (255 - (double)color.getRed()) * (factor);
        double g = (255 - (double)color.getGreen()) * (factor);
        double b = (255 - (double)color.getBlue()) * (factor);

        Color newTint = new Color((float)r + color.getRed(), (float)g + color.getGreen(), (float)b + color.getBlue());

        return newTint;
    }

    /**
     * Allows the user to determine the percentage of black to add to the colors in question.
     * Uses a float because decimal places... might switch to a double, I don't need repeating
     * numbers all over the place.
     * @param factor the percentage of black being added to the color
     * @param color the base color
     * @return a darker color.
     */
    public Color darkenBy(Color color, float factor){

        /*
         * Steps:
         * multiply by factor
         *
         */

        double r = (double)color.getRed() * (1/factor);
        double g = (double)color.getGreen() * (1/factor);
        double b = (double)color.getBlue() * (1/factor);

        Color newShade =  new Color((float)r, (float)g, (float) b);

        return newShade;
    }

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

        Color complement = new Color(0, 0, 0);


        return complement;
    }



}
