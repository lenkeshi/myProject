package tankgame.Background;

import java.awt.*;

/**
 * @author 张俊斌
 * @version 1.0
 */
public class Rock extends Background{
    public Rock(int x, int y) {
        super(x, y);
    }

    //画石头
    public void drawRock(Graphics g){
        g.setColor(Color.lightGray);
        g.fill3DRect(x,y,40,40,false);
    }
}
