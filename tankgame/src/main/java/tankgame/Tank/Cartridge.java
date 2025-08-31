package tankgame.Tank;

import tankgame.Background.Background;


import java.io.Serializable;


/**
 * @author 张俊斌
 * @version 1.0
 */
public class Cartridge implements Runnable, Serializable {
    private int x;
    private int y;
    private int speed;
    private boolean survival = true;//子弹存活状态
    private char location;

    public Cartridge(int x, int y, char location, int speed) {
        this.x = x;//记录坦克发射位置X
        this.y = y;//记录坦克发射位置Y
        this.location = location;//子弹诞生方向
        this.speed = speed;//子弹速度

        //修改子弹所在位置
        fire();

        //启动子线程让子弹自己动
        new Thread(this).start();
    }

    //发射子弹的位置
    private void fire() {
        switch (location) {
            case 'W':
                x += 17;
                y -= 36;
                break;
            case 'A':
                x -= 36;
                y += 17;
                break;
            case 'D':
                x += 70;
                y += 17;
                break;
            case 'S':
                x += 17;
                y += 70;
                break;
            default:
                break;
        }
    }

    //子弹移动
    public void bulletTravel() {
        switch (location) {
            case 'W':
                y -= speed;
                break;
            case 'A':
                x -= speed;
                break;
            case 'D':
                x += speed;
                break;
            case 'S':
                y += speed;
                break;
            default:
                break;
        }
    }

    @Override
    public void run() {
        while (survival) {
            //当子弹位置要超过窗口边界时，结束线程
            if (x > Background.X_MAX - speed || x < speed || y > Background.Y_MAX - speed || y < speed) {
                survival = false;
                break;
            }

            //子弹移动规则
            bulletTravel();


            try {
                Thread.sleep(50);//令线程睡眠50毫秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

//    //子弹击毁坦克
//    public void cartridgeHitTank(Tank tank, Vector<Explosion> explosions) {
//        int tx = tank.getX();
//        int ty = tank.getY();
//        switch (tank.getLocation()) {//判断坦克朝向坐标
//            case 'A':
//            case 'D':
//                if (x + 6 > tx - 30 && x < tx + 70 &&
//                        y + 6 > ty - 20 && y < ty + 60) {
//                    tank.setSurvival(false);//坦克被消灭
//                    survival = false;//子弹消失
//
//                    //加载爆炸动画
//                    explosions.add(new Explosion(tx - 30, ty - 20));
//                }
//                break;
//            case 'S':
//            case 'W':
//                if (x + 6 > tx - 20 && x < tx + 60 &&
//                        y + 6 > ty - 30 && y < ty + 70) {
//                    tank.setSurvival(false);//坦克被消灭
//                    survival = false;//子弹消失
//
//                    //加载爆炸动画
//                    explosions.add(new Explosion(tx - 20, ty - 30));
//                }
//                break;
//            default:
//                break;
//        }
//
//    }

    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean getSurvival() {
        return survival;
    }

    public void setSurvival(boolean survival) {
        this.survival = survival;
    }

    public char getLocation() {
        return location;
    }

    public int getSpeed() {
        return speed;
    }
}
