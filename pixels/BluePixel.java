package pixels;

import java.awt.*;

public class BluePixel extends Pixel{
    static Color blueColor = new Color(0, 0, 100);

    public BluePixel(int x, int y){
        super(x, y, blueColor);
    }
}
