package tankgame.Background;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.Vector;

/**
 * @author 张俊斌
 * @version 1.0
 */
public class Explosion extends Background implements ImageObserver{
    public static Vector<Explosion> explosions = new Vector<>();
    Image image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/image/bomb_1.gif"));
    Image image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/image/bomb_2.gif"));
    Image image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/image/bomb_3.gif"));
    public int life = 10;
    boolean survival = true;

    public Explosion(int x, int y) {
        super(x, y);
    }

    //生命值减少
    public void healthDecrease() {
        if (life > 0) {
            life--;
        } else {
            survival = false;
        }
    }

    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        return true;
    }

    //画爆炸
    public boolean drawExplosion(Graphics g) {
        //爆炸效果

        if (life > 6) {
            g.drawImage(image1, x, y, 100, 100, this);
        } else if (life > 3) {
            g.drawImage(image2, x, y, 100, 100, this);
        } else {
            g.drawImage(image3, x, y, 100, 100, this);
        }
        healthDecrease();
        if (life == 0) {
            return true;
        }
        return false;
    }

}
