package pixels;

import java.awt.*;

public abstract class Pixel {
    private int sideSize = 10, upperBarSize = 30, x, y;
    private Color color;

    public Pixel(int x, int y, Color color){
        this.x = x * sideSize;
        this.y = (y * sideSize) + upperBarSize;
        this.color = color;
    }

    public void renderPixel(Graphics g){
        g.setColor(color);
        g.fillRect(x, y, sideSize, sideSize);
    }
}
