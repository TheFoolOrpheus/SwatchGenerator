package colorCreator;

import java.awt.*;

/**
 * Created by adani14 on 2/7/2017.
 */
public class MonochromeSwatch implements Swatch {

    public MonochromeSwatch(){

    }

    public MonochromeSwatch(int numColors, boolean complementary){

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
    public Color[] createColors(){


        return new Color[0];
    }

    /**
     * Allows the user to determine the percentage of white to add to the colors in question.
     * Uses a float because decimal places... might switch to a double, I don't need repeating
     * numbers all over the place.
     * @param factor the percentage of white being added to the color
     * @return a lighter color.
     */
    public Color brightenBy(float factor){

        return null;
    }

    /**
     * Allows the user to determine the percentage of black to add to the colors in question.
     * Uses a float because decimal places... might switch to a double, I don't need repeating
     * numbers all over the place.
     * @param factor the percentage of black being added to the color
     * @return a darker color.
     */
    public Color darkenBy(float factor){

        return null;
    }



}
