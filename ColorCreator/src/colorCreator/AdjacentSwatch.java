package colorCreator;

import javax.swing.*;
import java.awt.*;

/**
 * Created by adani14 on 2/21/2017.
 */
public class AdjacentSwatch implements Swatch {

    private Color motherColor;
    private Color[] swatch;
    private Color[] baseColors;

    public AdjacentSwatch(){
        
        Color color = new Color(000);
        getDistance(color);

    }

    public Color[] getDistance(Color baseColor){

        float[] motherValues = getHSV(baseColor);
        float distance;
        JFrame frame = new JFrame();

        /*
         * Ask user about the distance away from the mother color.
         * It needs to be between 0 and 180. If the value is 0, act like it's
         * 5 degrees, though.
         *
         * Change the hue positively and negatively with the distance values.
         *
         * if hue = negative, recalculate the value as such:
         * 360 - distance
         *
         * default to 30 degrees
         */

        String customNumber;
        customNumber = JOptionPane.showInputDialog(frame, "Enter the number of colors you " +
                "would like to generate. \nMust be 20 or less.", "Custom Value", JOptionPane.PLAIN_MESSAGE);

        baseColors[0] = baseColor;

        try {
            distance = new Float(customNumber);
            if(distance < 0 && distance > 180){
                //tell user the values entered are bad
            }
            else{

                //This is using lightness, not brightness
                baseColors[1] = new Color(motherValues[0] + distance, motherValues[3], motherValues[5]);

                if(motherValues[0] - distance < 0){

                    baseColors[2] = new Color(360f - distance, motherValues[3], motherValues[5]);

                }

            }
        }
        catch(NumberFormatException nfe){
            System.err.println(nfe.getMessage());
        }

        return baseColors;
    }

    /**
     * This method is used to swap colors in the swatch array. To make said colors show up in order from darkest to lightest.
     *
     * @param swatch the completed swatch, out of order
     * @param init Not sure right now
     * @param fin Also not sure, I'm getting there, give me a break
     * @return This could be void, for the moment whatever.
     */
    public void swap(Color[] swatch, int init, int fin){

    }

    /**
     *
     * @param color
     * @return
     */
    public Color complementaryColors(Color color){

        return new Color(0,0,0);
    }

    /**
     *
     * @return
     */
    public String toString(){

        String information = "Colors: ";

        for(int i = 0; i < baseColors.length; i++)
        {
            information = information + " " + colorToHex(baseColors[i]) + " ";
        }

        information = information + "\nBase Color: " + colorToHex(baseColors[0]);


        return information;
    }

}
