package pixels;

import java.awt.*;

public class BlackPixel extends Pixel{
    static Color blackColor = new Color(0,0,0);

    public BlackPixel(int x, int y){
        super(x, y, blackColor);
    }
}
