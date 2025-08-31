package tankgame.Background;

import java.awt.*;

/**
 * @author 张俊斌
 * @version 1.0
 */
public class WoodenCrate extends Background{
    public WoodenCrate(int x, int y) {
        super(x, y);
    }

    //画木箱
    public void drawWoodenCrate(Graphics g){
        g.setColor(Color.orange);

        g.fill3DRect(x,y,40,40,false);
    }
}
