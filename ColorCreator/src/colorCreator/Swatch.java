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
         * So, there's intensity, whichis the average of the rgb components. That's
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
    Color[] createColors(Color color);

    /**
     * A method that computes colors being created when the number of colors that will be
     * generated is odd.
     * @param color the base color
     * @return an array of colors
     */
    Color[] oddCreateColors(Color color);

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
