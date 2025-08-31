package tankgame.Background;

import tankgame.Tank.Cartridge;

import java.awt.*;

/**
 * @author 张俊斌
 * @version 1.0
 */
public class CartridgeModel extends Background{
    public CartridgeModel(int x, int y) {
        super(x, y);
    }

    //画子弹
    public static void CartridgeModelDraw(Cartridge cartridge, Graphics g){
        int x = cartridge.getX();
        int y = cartridge.getY();
        g.fillOval(x, y, 6, 6);
    }
}
