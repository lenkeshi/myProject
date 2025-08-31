package tankgame.Background;

import tankgame.Tank.EnemyTank;
import tankgame.Tank.Player;
import tankgame.Tank.Tank;

import java.awt.*;

/**
 * @author 张俊斌
 * @version 1.0
 */
public class TankModel extends Background{
    public TankModel(int x, int y) {
        super(x, y);
    }

    //画坦克
    public static void drawTank(Tank tank, Graphics g){
        if(tank instanceof Player){
            //玩家坦克颜色
            g.setColor(Color.cyan);
        }else if(tank instanceof EnemyTank){
            //敌人坦克颜色
            g.setColor(Color.yellow);
        }

        switch (tank.getLocation()){
            case 'W':
                drawTankUp(tank.getX(),tank.getY(),g);
                break;
            case 'A':
                drawTankLeft(tank.getX(),tank.getY(),g);
                break;
            case 'S':
                drawTankDown(tank.getX(),tank.getY(),g);
                break;
            case 'D':
                drawTankRight(tank.getX(),tank.getY(),g);
                break;
        }
    }

    //绘制向上的坦克
    private static void drawTankUp(int x,int y, Graphics g) {

        g.fill3DRect(x - 20, y - 30, 20, 100, false);
        g.fill3DRect(x + 40, y - 30, 20, 100, false);
        g.fill3DRect(x, y - 15, 40, 70, false);
        g.fillRect(x + 17, y - 30, 6, 30);
        g.fillOval(x, y, 40, 40);
    }

    //绘制向左的坦克
    private static void drawTankLeft(int x,int y, Graphics g) {

        g.fill3DRect(x - 30, y - 20, 100, 20, false);
        g.fill3DRect(x - 30, y + 40, 100, 20, false);
        g.fill3DRect(x - 15, y, 70, 40, false);
        g.fillRect(x - 30, y + 17, 30, 6);
        g.fillOval(x, y, 40, 40);
    }

    //绘制向下的坦克
    private static void drawTankDown(int x,int y,Graphics g) {

        g.fill3DRect(x - 20, y - 30, 20, 100, false);
        g.fill3DRect(x + 40, y - 30, 20, 100, false);
        g.fill3DRect(x, y - 15, 40, 70, false);
        g.fillRect(x + 17, y + 40, 6, 30);
        g.fillOval(x, y, 40, 40);
    }

    //绘制向右的坦克
    private static void drawTankRight(int x,int y, Graphics g) {
        g.fill3DRect(x - 30, y - 20, 100, 20, false);
        g.fill3DRect(x - 30, y + 40, 100, 20, false);
        g.fill3DRect(x - 15, y, 70, 40, false);
        g.fillRect(x + 40, y + 17, 30, 6);
        g.fillOval(x, y, 40, 40);
    }
}
