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

        int opt = 0;
        String options[] = {"RGB", "Hexcode"};
        opt = JOptionPane.showOptionDialog(null, "Choose a method for entry.", "Base color",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        switch(opt) {
            case 0:
                /*
                 * I would like to use the regular expressions \p{punct} and \p{blank} so that spaces can be used as
                 * well, but for right now let's just stick with this particular implementation.
                 */
                String RGBval;
                RGBval = JOptionPane.showInputDialog(null, "Enter the RGB value of your" +
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
                hexcode = JOptionPane.showInputDialog(null, "Enter the hexcode value of your" +
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

                break;
        }
        return null;
    }

    /**
     * The method that computes the colors being created.
     * @return an array of colors
     */
    Color[] createColors(Color color);

    /**
     * Allows the user to determine the percentage of white to add to the colors in question.
     * Uses a float because decimal places... might switch to a double, I don't need repeating
     * numbers all over the place.
     * @param factor the percentage of white being added to the color
     * @return a lighter color.
     */
    Color brightenBy(Color color, float factor);

    /**
     * Allows the user to determine the percentage of black to add to the colors in question.
     * Uses a float because decimal places... might switch to a double, I don't need repeating
     * numbers all over the place.
     * @param factor the percentage of black being added to the color
     * @return a darker color.
     */
    Color darkenBy(Color color, float factor);




}
