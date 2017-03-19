package colorCreator;

import javax.swing.*;
import java.awt.*;

/**
 * Created by adani14 on 2/21/2017.
 */
public class HarmoniousSwatch implements Swatch {

    private Color motherColor;
    private Color[] swatch;
    private Color[] baseColors;
    private String swatchType;

    public HarmoniousSwatch(){

    }

    public HarmoniousSwatch(String type){
        JFrame frame = new JFrame();
        frame.setLocationRelativeTo(null);
        frame.setAlwaysOnTop(true);
        setSwatchType(type);

        /*
         * Give the user a choice of entering a hexcode or a RGB value.
         */
        int opt;
        String options[] = {"Yes", "No"};
        opt = JOptionPane.showOptionDialog(frame, "Do you have a color in mind as your mother color?", "Mother color",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        switch(opt){

            case 0:
               setMotherColor(chooseColor());
                if(motherColor == null){
                    return;
                }
                break;
            case 1:
                motherColor = randomColorChooser();
                if(motherColor == null){
                    return;
                }
                break;
            default:
                motherColor = randomColorChooser();
                if(motherColor == null){
                    return;
                }
                break;

        }

        JOptionPane.showMessageDialog(frame, "Your mother color is " + decimaltoHex(getMotherColor().getRed(),
                getMotherColor().getGreen(), getMotherColor().getBlue()));

        switch (type){
            case "Complementary":
                break;
            case "Analogous":
                break;
            case "Triad":
                getDistance(getMotherColor(), 3, getSwatchType());
                break;
            case "Rectangle":
                break;
            case "Square":
                break;
            case "Adjacent":
                //Eventually this should do more than three colors, but for now it's only 3.
                getDistance(motherColor, 3, getSwatchType());
                createColors(3);
                break;
            default:
                break;
        }

    }

    /**
     * A depreciated method, using numColors instead of type.
     * @param numColors
     */
    public HarmoniousSwatch(int numColors){

        JFrame frame = new JFrame();
        frame.setLocationRelativeTo(null);
        frame.setAlwaysOnTop(true);

        /*
         * Give the user a choice of entering a hexcode or a RGB value.
         */
        int opt;
        String options[] = {"Yes", "No"};
        opt = JOptionPane.showOptionDialog(frame, "Do you have a color in mind as your mother color?", "Mother color",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        switch(opt){

            case 0:
                setMotherColor(chooseColor());
                if(motherColor == null){
                    return;
                }
                break;
            case 1:
                motherColor = randomColorChooser();
                if(motherColor == null){
                    return;
                }
                break;
            default:
                motherColor = randomColorChooser();
                if(motherColor == null){
                    return;
                }
                break;

        }

        JOptionPane.showMessageDialog(frame, "Your mother color is " + decimaltoHex(getMotherColor().getRed(),
                getMotherColor().getGreen(), getMotherColor().getBlue()));


        //getDistance(motherColor, numColors);
        createColors(numColors);

    }

    /*
     * Getters and Setters
     */

    public Color getMotherColor(){
        return motherColor;
    }

    public void setMotherColor(Color c){
        motherColor = c;
    }

    public String getSwatchType(){
        return swatchType;
    }

    public void setSwatchType(String t){
        swatchType = t;
    }

    public void setBaseColors(int numColors){
        baseColors = new Color[numColors];
    }

    public void getDistance(Color baseColor, int numColors, String type){

        float[] motherValues = getHSV(baseColor);
        float distance = 0f;
        String customNumber;
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setAlwaysOnTop(true);
        setBaseColors(numColors);
        baseColors[0] = baseColor;
        float hue;
        float saturation = motherValues[3];
        float lightness = motherValues[5];

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

        //{ "Complementary", "Analogous", "Triad", "Rectangle", "Square", "Adjacent"};
        if(type == "Complimentary"){
            // Distance doesn't matter for complimentary, calls a complimentary colors method.
        }
        if(type == "Analogous"){
            distance = 30f;
            hue = motherValues[0] + distance;
            //three colors
            try{
                baseColors[1] = Color.getHSBColor(hue, saturation, lightness);
                if (motherValues[0] - distance < 0) {
                    hue = 360f - distance;
                    baseColors[2] = Color.getHSBColor(hue, saturation, lightness);


                } else {
                    hue = motherValues[0] - distance;
                    baseColors[2] = Color.getHSBColor(hue, saturation, lightness);

                }
            }
            catch(NumberFormatException nfe){

            }
        }
        if(type == "Triad"){
            distance = 50f;
            hue = motherValues[0] + distance;
            //three colors
            try{

                baseColors[1] = Color.getHSBColor(hue, saturation, lightness);
                if (motherValues[0] - distance < 0) {
                    hue = 360f - distance;
                    baseColors[2] = Color.getHSBColor(hue, saturation, lightness);


                } else {
                    hue = motherValues[0] - distance;
                    baseColors[2] = Color.getHSBColor(hue, saturation, lightness);

                }

            }
            catch(NumberFormatException nfe){

            }
        }
        if(type == "Rectangle"){
            /*
             * this uses two complimentary pairs, so it will call a special method
             * that asks for another base color. I need to write up a special case in toString
             * that looks into this particular thing.
             */

        }
        if(type == "Square"){
                distance = 90f;
            hue = motherValues[0] + distance;
            //four colors
            try{
                baseColors[1] = Color.getHSBColor(hue, saturation, lightness);
                if (motherValues[0] - distance < 0) {
                    hue = 360f - distance;
                    baseColors[2] = Color.getHSBColor(hue, saturation, lightness);


                } else {
                    hue = motherValues[0] - distance;
                    baseColors[2] = Color.getHSBColor(hue, saturation, lightness);

                }

            }
            catch(NumberFormatException nfe){

            }
        }
        if(type == "Adjacent") {

            do {
                customNumber = JOptionPane.showInputDialog(frame, "In degrees, what distance away from the mother color would " +
                        "you like the other colors to be? \nThe number must be between 0 and 180.", "Distance", JOptionPane.PLAIN_MESSAGE);


                distance = new Float(customNumber);
            }
            while (distance < 0 && distance > 180);

            if (distance == 0) {
                distance = 5.13f;
            }


            try {

                //This is using lightness, not brightness

            /*
             * Okaaaaaaaay. Gotta fix some shiiii--
             */
                hue = motherValues[0] + distance;
                double garbage = 5.6 - Math.floor(5.6);
                double mathStuff = 360 * garbage;
                double thing = mathStuff;

                baseColors[1] = Color.getHSBColor(hue, saturation, lightness);
                String test = colorToHex(baseColors[1]);

                if (motherValues[0] - distance < 0) {
                    hue = 360f - distance;
                    baseColors[2] = Color.getHSBColor(hue, saturation, lightness);
                    String test2 = colorToHex(baseColors[2]);

                } else {
                    hue = motherValues[0] - distance;
                    baseColors[2] = Color.getHSBColor(hue, saturation, lightness);
                    String test2 = colorToHex(baseColors[2]);
                }

            } catch (NumberFormatException nfe) {
                System.err.println(nfe.getMessage());
            }
        }


    }


    /**
     * Creating colors based on the number of colors. Uses base colors and saves the colors created in
     * swatch.
     */
    public void createColors(int numColors){

        swatch = new Color[(3 * numColors)];

        for(int i = 0; i < 3; i++){

            switch(i) {
                case 0:
                    for (int k = 0; k < numColors; k++) {

                        if (k == 0) {
                            swatch[k] = brightenBy(baseColors[i], 1 / 4f);
                        } else {
                            swatch[k] = brightenBy(swatch[k - 1], 1 / 4f);
                        }

                    }
                    break;
                case 1:
                    for (int k = numColors; k < numColors * 2; k++) {

                        if (k == numColors) {
                            swatch[k] = brightenBy(baseColors[i], 1 / 4f);
                        } else {
                            swatch[k] = brightenBy(swatch[k - 1], 1 / 4f);
                        }

                    }
                    break;
                case 2:
                    for (int k = numColors * 2; k < swatch.length; k++) {

                        if (k == numColors * 2) {
                            swatch[k] = brightenBy(baseColors[i], 1 / 4f);
                        } else {
                            swatch[k] = brightenBy(swatch[k - 1], 1 / 4f);
                        }

                    }
                    break;
                default:
                    for (int k = 0; k < swatch.length; k++) {

                            swatch[k] = Color.BLACK;

                    }
            }

        }

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

    /**
     *
     * @return information, a string with all the color information in it.
     */
    public String toString(){

        String information = "Colors :";

        for(int k = 0; k < swatch.length; k++){
            information = information + " " + colorToHex(swatch[k]) + " ";
        }

        information = information + "\nBase Colors: ";

        for(int i = 0; i < baseColors.length; i++)
        {
            information = information + " " + colorToHex(baseColors[i]) + " ";
        }

        information = information + "\nMother Color: " + colorToHex(motherColor);


        return information;
    }

}
