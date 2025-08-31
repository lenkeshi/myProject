package tankgame;


import tankgame.Audio.AePlayWave;
import tankgame.Background.Explosion;
import tankgame.Level.Level;
import tankgame.Level.Level01;
import tankgame.Level.Level02;
import tankgame.Level.Level03;
import tankgame.SaveAndLoad.Record;
import tankgame.Tank.EnemyTank;
import tankgame.Tank.Player;
import tankgame.Tank.Tank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;
import java.util.Vector;

/**
 * @author 张俊斌
 * @version 1.0
 */
public class MyPanel extends JPanel implements KeyListener, Runnable {
//    public final static int X_MAX = 1300;
//    public final static int Y_MAX = 800;
//    private Player player;
//    private Vector<EnemyTank> eT = new Vector<>();
//    private int eTSize = 3;
//    private Vector<Explosion> explosions = new Vector<>();
    private Level level;
    private boolean game = true;
    private Scanner scanner = new Scanner(System.in);
//    private Image image1;
//    private Image image2;
//    private Image image3;

    public MyPanel() {
        //和玩家进行交互
        System.out.println("是否开启新游戏？1为是，2为否，其他的默认退出：");
        switch (scanner.nextInt()) {
            case 2:
                if (Record.loadData()) {//判断是否读取存档成功
                    //调用Record.getLevel()将读取数据给到本类level对象
                    level = Record.getLevel();
                    //启动开局音频线程
                    new AePlayWave("src\\main\\resources\\Bgm\\tankGame.wav").start();
                    break;
                }
                System.out.println("存档损坏或无法读取，已为您开启新游戏~");
            case 1:
                System.out.println("选择关卡(1~3)：");
                boolean key = true;
                while (key) {
                    switch (scanner.nextInt()) {
                        case 1:
                            //创建关卡1
                            level = new Level01();
                            key = false;
                            break;
                        case 2:
                            //创建关卡2
                            level = new Level02();
                            key = false;
                            break;
                        case 3:
                            //创建关卡3
                            level = new Level03();
                            key = false;
                            break;
                        default:
                            System.out.println("关卡不存在，请重新选择关卡(1~3)。");
                            break;
                    }
                }
                //启动开局音频线程
                new AePlayWave("src\\main\\resources\\Bgm\\tankGame.wav").start();
                break;
            default:
                game = false;
                System.out.println("退出游戏~~~");
                System.exit(0);
                break;
        }
//        switch (key) {
//            case 2:
//                if (Record.fileSecurity()) {
//                    eT = Record.loadData();
//                    for (int i = 0; i < eT.size(); i++) {
//                        new Thread(eT.get(i)).start();
//                    }
//                    break;
//                } else {
//                    System.out.println("没有游戏数据，已开始新游戏~");
//                }
//            case 1:
//                for (int i = 0; i < eTSize; i++) {
//                    eT.add(new EnemyTank(180 + i * 100, 100));
//                    new Thread(eT.get(i)).start();
//                }
//                break;
//            default:
//                break;
//        }
//        player = new Player(1000, 500);
//
//        image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/image/bomb_1.gif"));
//        image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/image/bomb_2.gif"));
//        image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/image/bomb_3.gif"));
//        explosions = Explosion.explosions;
    }

    @Override
    public void paint(Graphics g) {//绘画方法
        super.paint(g);
        if (level.allAction()) {//调用方法完成行为逻辑判断
            //玩家死亡
            game = false;
            System.out.println("死掉了~~~~");
            System.exit(0);
        }

        //画出所有内容
        level.drawAllBackground(g);




//        g.drawImage(image1,1300,0,100,100,this);
        //设置背景颜色和大小
//        g.setColor(Color.DARK_GRAY);
//        g.fillRect(0, 0, X_MAX, Y_MAX);
//

//        showInfo(g);

//        if (!player.getSurvival()) {//玩家是否存活
//            return;//退出游戏
//        }
//
//        for (Cartridge o : player.getCartridges()) {
//            o.bulletTravel();//加载一次玩家的子弹移动
//            for (EnemyTank o1 : eT) {
//                //比较玩家子弹是否击中敌人坦克
//                o.cartridgeHitTank(o1, explosions);
//            }
//        }

//        for (int i = 0; i < eT.size(); i++) {
//            EnemyTank o = eT.get(i);
//            if (o.getSurvival()) {//判断敌人坦克是否存活
//                //判断玩家下一步行动是否会和敌人坦克重叠
//                player.tankOverlapDetection(o);
//            } else {
//                Record.addKills();
//                eT.remove(i);
//            }
//        }
        //设置玩家坦克
//        setTank(player, g);
//
//
//        for (int i = 0; i < eT.size(); i++) {
//            EnemyTank o = eT.get(i);
//            //判断敌人下一步行动是否会和玩家坦克重叠
//            o.tankOverlapDetection(player);
//            for (int j = 0; j < eT.size(); j++) {
//                EnemyTank o1 = eT.get(j);
//                //判断敌人下一步行动是否会和敌人坦克重叠
//                o.tankOverlapDetection(o1);
//            }
//            //设置敌人坦克
//            setTank(o, g);
//            for (Cartridge o2 : o.getCartridges()) {
//                o2.bulletTravel();//加载一次敌人的子弹移动
//                //比较敌人子弹是否击中玩家坦克
//                o2.cartridgeHitTank(player, explosions);
//            }
//        }
//
        //爆炸效果
//        for (int i = 0; i < explosions.size(); i++) {
//            Explosion explosion = explosions.get(i);
//            if (explosion.life > 60) {
//                g.drawImage(image1, explosion.getX(), explosion.getY(), 100, 100, this);
//            } else if (explosion.life > 30) {
//                g.drawImage(image2, explosion.getX(), explosion.getY(), 100, 100, this);
//            } else {
//                g.drawImage(image3, explosion.getX(), explosion.getY(), 100, 100, this);
//            }
//            explosion.healthDecrease();
//            if (explosion.life == 0) {
//                explosions.remove(explosion);
//            }
//        }

    }
    //有字符输出时调用
    @Override
    public void keyTyped(KeyEvent e) {

    }

    //键盘摁键时调用
    @Override
    public void keyPressed(KeyEvent e) {
        //使用setNextLocation()接收键盘传入的指令
        level.getPlayer().setNextLocation((char) e.getKeyCode());

        try {
            Thread.sleep(20);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        //重绘
        this.repaint();
    }

    //键盘松键时调用
    @Override
    public void keyReleased(KeyEvent e) {

    }


    @Override
    public void run() {
        while (game) {//无限重绘
            try {
                Thread.sleep(50);//线程睡眠50毫秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //重绘
            this.repaint();
        }
    }
//
//
//    private void setTank(Tank tank, Graphics g) {//设置坦克信息
//        //调用showTank设置对应坦克颜色
//        showTank(tank, g);
//
//        //调用drawTankAction画出坦克行动
//        drawTankAction(tank, g);
//    }
//
//    private void drawTankAction(Tank tank, Graphics g) {//画出坦克
//        //判断当前位置方向和下一个指令是否相同
//        if (tank.getLocation() == tank.getNextLocation()) {
//            setTankXY(tank);//相同进行X，Y的修改
//        }
//
//        //不相同，进行方向调整，相同则绘制坦克
//        setTankAction(tank, g);
//
//        //将坦克下一个位置变为当前位置
//        tank.setLocation(tank.getNextLocation());
//
//        //绘制坦克的子弹
//        showCartridge(tank.getCartridges(), g);
//    }
//
//    private void setTankXY(Tank tank) {//修改tank的XY
//        int x = tank.getX();
//        int y = tank.getY();
//        switch (tank.getLocation()) {//根据位置选择父类的修改方法
//            case 'A':
//                if (x - 30 - tank.getSpeed() < 0) {
//                    tank.setNextLocation(' ');
//                    break;
//                }
//                tank.left();
//                break;
//            case 'W':
//                if (y - 30 - tank.getSpeed() < 0) {
//                    tank.setNextLocation(' ');
//                    break;
//                }
//                tank.up();
//                break;
//            case 'S':
//                if (y + 70 + tank.getSpeed() > Y_MAX) {
//                    tank.setNextLocation(' ');
//                    break;
//                }
//                tank.down();
//                break;
//            case 'D':
//                if (x + 70 + tank.getSpeed() > X_MAX) {
//                    tank.setNextLocation(' ');
//                    break;
//                }
//                tank.right();
//                break;
//            default:
//                break;
//        }
//    }
//
//    private void setTankAction(Tank tank, Graphics g) {
//        switch (tank.getNextLocation()) {//依据指令，绘制对应方向的坦克
//            case 'A':
//                //绘制向左的坦克
//                drawTankLeft(tank, g);
//                break;
//            case 'W':
//                //绘制向上的坦克
//                drawTankUp(tank, g);
//                break;
//            case 'S':
//                //绘制向下的坦克
//                drawTankDown(tank, g);
//                break;
//            case 'D':
//                //绘制向右的坦克
//                drawTankRight(tank, g);
//                break;
//            case 'J':
//                //发射子弹，不画出坦克和改变坦克位置
//                tank.getCartridges().add(new Cartridge(tank.getX(),
//                        tank.getY(), tank.getLocation(), tank.getSpeed() * 2));
//            default:
//                //将坦克下一个指令设置为当前位置，重新调用该方法画出当前位置坦克
//                tank.setNextLocation(tank.getLocation());
//                setTankAction(tank, g);
//                break;
//        }
//    }
//
//    private void showTank(Tank tank, Graphics g) {//设置坦克颜色
//        if (tank instanceof Player) {
//            //玩家操控的颜色为青色
//            g.setColor(Color.cyan);
//        } else if (tank instanceof EnemyTank) {
//            //敌人颜色为黄色
//            g.setColor(Color.YELLOW);
//        }
//    }
//
//    //显示子弹
//    private void showCartridge(Vector<Cartridge> zD, Graphics g) {
//        for (int i = 0; i < zD.size(); i++) {
//            Cartridge cartridge = zD.get(i);
//            if (cartridge.getSurvival()) {//判断子弹是否还存活
//                //画出子弹
//                drawCartridge(cartridge, g);
//            } else {
//                zD.remove(i);//移除子弹
//            }
//        }
//    }
//
//    //绘制向上的坦克
//    private void drawTankUp(Tank tank, Graphics g) {
//        int x = tank.getX();
//        int y = tank.getY();
//        g.fill3DRect(x - 20, y - 30, 20, 100, false);
//        g.fill3DRect(x + 40, y - 30, 20, 100, false);
//        g.fill3DRect(x, y - 15, 40, 70, false);
//        g.fillRect(x + 17, y - 30, 6, 30);
//        g.fillOval(x, y, 40, 40);
//    }
//
//    //绘制向左的坦克
//    private void drawTankLeft(Tank tank, Graphics g) {
//        int x = tank.getX();
//        int y = tank.getY();
//        g.fill3DRect(x - 30, y - 20, 100, 20, false);
//        g.fill3DRect(x - 30, y + 40, 100, 20, false);
//        g.fill3DRect(x - 15, y, 70, 40, false);
//        g.fillRect(x - 30, y + 17, 30, 6);
//        g.fillOval(x, y, 40, 40);
//    }
//
//    //绘制向下的坦克
//    private void drawTankDown(Tank tank, Graphics g) {
//        int x = tank.getX();
//        int y = tank.getY();
//        g.fill3DRect(x - 20, y - 30, 20, 100, false);
//        g.fill3DRect(x + 40, y - 30, 20, 100, false);
//        g.fill3DRect(x, y - 15, 40, 70, false);
//        g.fillRect(x + 17, y + 40, 6, 30);
//        g.fillOval(x, y, 40, 40);
//    }
//
//    //绘制向右的坦克
//    private void drawTankRight(Tank tank, Graphics g) {
//        int x = tank.getX();
//        int y = tank.getY();
//        g.fill3DRect(x - 30, y - 20, 100, 20, false);
//        g.fill3DRect(x - 30, y + 40, 100, 20, false);
//        g.fill3DRect(x - 15, y, 70, 40, false);
//        g.fillRect(x + 40, y + 17, 30, 6);
//        g.fillOval(x, y, 40, 40);
//    }
//
//    //绘制子弹
//    private void drawCartridge(Cartridge cartridge, Graphics g) {
//        int x = cartridge.getX();
//        int y = cartridge.getY();
//        g.fillOval(x, y, 6, 6);
//    }


//    //显示我方击毁敌方坦克数
//    private void showInfo(Graphics g) {
//        g.setColor(Color.black);
//        Font font = new Font("宋体", Font.BOLD, 25);
//        g.setFont(font);
//
//        g.drawString("累计击毁敌方坦克", X_MAX + 100, 80);
//        g.drawString(String.valueOf(Record.getKills()), X_MAX + 250, 170);
//
//        g.drawString("我方坦克剩余血量", X_MAX + 100, 460);
//        g.drawString(String.valueOf(Record.getKills()), X_MAX + 250, 550);
//
//        setTank(new EnemyTank(X_MAX + 120, 150), g);
//        setTank(new Player(X_MAX + 120, 530), g);
//
//
//
//    }



//    public Vector<EnemyTank> geteT() {
//        return eT;
//    }
//
//    public Player getPlayer() {
//        return player;
//    }
}
