package pixels;

import java.awt.*;

public abstract class Pixel {
    private int sideSize = 10, upperBarSize = 30, placeForThePhoneName = 50 , x, y;
    private Color color;

    /**
     * Constructor that gets the coordinates and the color of the pixel
     * @param x
     * @param y
     * @param color
     */
    public Pixel(int x, int y, Color color){
        this.x = x * sideSize;
        this.y = (y * sideSize) + upperBarSize + placeForThePhoneName;
        this.color = color;
    }

    /**
     * Method that draw the pixel
     * @param g
     */
    public void renderPixel(Graphics g){
        g.setColor(color);
        g.fillRect(x + 20, y, sideSize, sideSize);

        g.setColor(Color.GRAY);
        g.drawRect(x + 20, y, sideSize, sideSize);
    }
}
