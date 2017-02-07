package colorCreator;

import javax.swing.*;
import java.awt.*;

/**
 * Created by adani14 on 2/7/2017.
 */
public class MonochromeSwatch implements Swatch {

    Color baseColor;
    Color colors[];

    public MonochromeSwatch(){

    }

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

        if(choice == JOptionPane.YES_OPTION){

            /*
             * Give the user a choice of entering a hexcode or a RGB value.
             */

            //not sure what kind of dialog here

        }
        else if(choice == JOptionPane.NO_OPTION){

            randomColorChooser();

        }
        else{
            //Not sure what to do if they cancel out... just exit?
        }


        if(complementary == true)
        {
            complementaryColors(baseColor);
        }
        else if(complementary == false){
            createColors(baseColor);
        }

        boolean odd = isOdd(numColors);


    }

    public Color[] divisionOfColors(){

        /*
         * Asks the user if they want more tints or more shades.
         * After wards, begins processing the colors.
         * Uses the abstract method in swatch, create colors.
         */

        return new Color[0];
    }

    /**
     * The method that computes the colors being created.
     * @return an array of colors
     */
    public Color[] createColors(Color color){


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


        return new Color(0,0,0);
    }



}
