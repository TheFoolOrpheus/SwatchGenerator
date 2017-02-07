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
            return true;
        }
        else {
            return false;
        }
    }




}
