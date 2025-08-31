package tankgame.Background;

import java.awt.*;

/**
 * @author 张俊斌
 * @version 1.0
 */
public class River extends Background{
    public River(int x, int y) {
        super(x, y);
    }

    //画河
    public void drawRiver(Graphics g){
        g.setColor(Color.BLUE);
        g.fillRect(x,y,200,100);
    }
}
