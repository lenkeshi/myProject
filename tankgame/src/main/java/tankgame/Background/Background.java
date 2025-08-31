package tankgame.Background;

import tankgame.Level.Level03;
import tankgame.SaveAndLoad.Record;
import tankgame.Tank.EnemyTank;
import tankgame.Tank.Player;
import tankgame.Tank.Tank;

import java.awt.*;

/**
 * @author 张俊斌
 * @version 1.0
 */
public class Background {
    public final static int X_MAX = 1300;
    public final static int Y_MAX = 800;
    public int x;
    public int y;

    public Background(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //画初始底色和击杀敌人坦克数和我方坦克生命值
    public static void drawBackground(Graphics g){

        //设置背景颜色和大小
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, X_MAX, Y_MAX);
        Font font = new Font("宋体", Font.BOLD, 25);
        g.setFont(font);

        g.drawString("累计击毁敌方坦克", X_MAX + 100, 80);
        g.drawString(String.valueOf(Record.kills), X_MAX + 250, 170);

        g.drawString("我方坦克剩余血量", X_MAX + 100, 460);
        g.drawString(String.valueOf(Record.playerLive), X_MAX + 250, 550);

        TankModel.drawTank(new EnemyTank(X_MAX + 120, 150),g);
        TankModel.drawTank(new Player(X_MAX + 120, 530),g);

    }

    //画桥
    public void drawBridge(Graphics g){

    }

    //画河
    public void drawRiver(Graphics g){

    }

    //画石头
    public void drawRock(Graphics g){

    }

    //画木箱
    public void drawWoodenCrate(Graphics g){

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
