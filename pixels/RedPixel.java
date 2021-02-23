package pixels;

import java.awt.*;

public class RedPixel extends Pixel{
    static Color redColor = new Color(100, 0, 0);

    public RedPixel(int x, int y){
        super(x, y, redColor);
    }
}
