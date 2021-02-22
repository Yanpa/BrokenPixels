package pixels;

import java.awt.*;

public class GreenPixel extends Pixel{
    static Color greenColor = new Color(0, 100, 0);

    public GreenPixel(int x, int y){
        super(x, y, greenColor);
    }
}
