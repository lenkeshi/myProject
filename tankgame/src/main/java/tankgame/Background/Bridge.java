package tankgame.Background;

import java.awt.*;

/**
 * @author 张俊斌
 * @version 1.0
 */
public class Bridge extends Background{
    public Bridge(int x, int y) {
        super(x, y);
    }

    //画桥
    public void drawBridge(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(x,y,130,100);
    }
}
