package tankgame.Tank;

import tankgame.Background.*;
import tankgame.Level.Level;
import tankgame.SaveAndLoad.Record;
import tankgame.Tank.Cartridge;
import tankgame.Tank.EnemyTank;
import tankgame.Tank.Player;
import tankgame.Tank.Tank;

import java.util.Vector;

/**
 * @author 张俊斌
 * @version 1.0
 */
public class Collision {

    public boolean certainCollision(Level level) {

        Player player = level.getPlayer();
        Vector<EnemyTank> eT = level.getEnemyTank();
        Vector<Background> backgrounds = level.getBackgrounds();

        Vector<Cartridge> cartridges = player.getCartridges();

        //玩家子弹的碰撞情况
        for (int i = 0; i < cartridges.size(); i++) {
            Cartridge cartridge = cartridges.get(i);

            for (int j = 0; j < eT.size(); j++) {
                //判断是否击中敌人坦克
                if (certainCollision(cartridge, eT.get(j))) {
                    //将被击中的敌人移除
                    eT.remove(j);
                    //将击中后的子弹移除
                    cartridges.remove(i);
                    //跳出循环，去判断下一颗子弹
                    break;
                }

            }

            //判断子弹是否击中场景物品
            if (certainCollision(cartridge, backgrounds)) {
                //将击中后的子弹移除
                cartridges.remove(i);
            }
        }


        //敌人子弹的碰撞情况
        for (int i = 0; i < eT.size(); i++) {

            cartridges = eT.get(i).getCartridges();

            for (int j = 0; j < cartridges.size(); j++) {
                Cartridge cartridge = cartridges.get(j);
                //判断敌人子弹是否击中玩家
                if (certainCollision(cartridge, player)) {
                    //将击中后的子弹移除
                    cartridges.remove(j);
                    //判断玩家生命值
                    if (Record.playerLive == 0) {
                        //死亡跳出该方法
                        return true;
                    }
                }

                //判断子弹是否击中场景物品
                if (certainCollision(cartridge, backgrounds)) {
                    //将击中后的子弹移除
                    cartridges.remove(j);
                }
            }
        }


        //玩家坦克碰撞情况
        if (player.getNextLocation() == 'A'
                || player.getNextLocation() == 'W'
                || player.getNextLocation() == 'S'
                || player.getNextLocation() == 'D') {//坦克下一步是否是移动或换位

            for (int i = 0; i < eT.size(); i++) {
                //判断玩家坦克是否会和敌人坦克重叠
                certainCollision(player, eT.get(i));

                //判断玩家下一步行为是否被置' '
                if (player.getNextLocation() == ' ') {
                    //结束循环
                    break;
                }
            }
            //判断是否和场景物品重叠
            certainCollision(player, backgrounds);
        }


        //敌人坦克碰撞情况
        for (int i = 0; i < eT.size(); i++) {
            if (eT.get(i).getNextLocation() == 'A'
                    || eT.get(i).getNextLocation() == 'W'
                    || eT.get(i).getNextLocation() == 'S'
                    || eT.get(i).getNextLocation() == 'D') {//坦克下一步是否是移动或换位

                //判断敌人坦克是否会和玩家坦克重叠
                certainCollision(eT.get(i), player);
                //判断敌人下一步行为是否被置' '
                if (eT.get(i).getNextLocation() == ' ') {
                    //继续下一敌人的判断
                    continue;
                }

                for (int j = 0; j < eT.size(); j++) {
                    //判断敌人坦克是否会和其他敌人坦克重叠
                    certainCollision(eT.get(i), eT.get(j));
                    //判断敌人下一步行为是否被置' '
                    if (eT.get(i).getNextLocation() == ' ') {
                        //结束循环
                        break;
                    }
                }
                //判断是否和场景物品重叠
                certainCollision(eT.get(i), backgrounds);
            }
        }
        return false;
    }

    //坦克和所有背景物品的碰撞情况
    private void certainCollision(Tank tank, Vector<Background> backgrounds) {
        for (int i = 0; i < backgrounds.size(); i++) {

            Background background = backgrounds.get(i);

            if (background instanceof River) {//背景为河流

                River river = (River) backgrounds.get(i);
                //坦克和河流是否碰撞
                certainCollision(tank, river);

            } else if (background instanceof Rock) {//背景为石头

                Rock rock = (Rock) backgrounds.get(i);
                //坦克和石头是否碰撞
                certainCollision(tank, rock);

            } else if (background instanceof WoodenCrate) {//背景为木箱

                WoodenCrate woodenCrate = (WoodenCrate) backgrounds.get(i);
                //坦克和木箱是否碰撞
                certainCollision(tank, woodenCrate);

            }

            //判断坦克下一步行为是否被置' '
            if (tank.getNextLocation() == ' ') {
                //退出循环
                break;
            }
        }
    }

    //子弹和所有背景物品的碰撞情况
    private boolean certainCollision(Cartridge cartridge, Vector<Background> backgrounds) {
        for (int i = 0; i < backgrounds.size(); i++) {


            Background background = backgrounds.get(i);
            if (background instanceof Rock) {//背景为石头

                Rock rock = (Rock) backgrounds.get(i);
                //子弹是否和击中石头
                if (certainCollision(cartridge, rock)) {
                    //返回结果
                    return true;
                }

            } else if (background instanceof WoodenCrate) {

                WoodenCrate woodenCrate = (WoodenCrate) backgrounds.get(i);
                //子弹是否击中木箱
                if (certainCollision(cartridge, woodenCrate)) {
                    //移除被撞
                    backgrounds.remove(i);
                    //返回结果
                    return true;
                }

            }
        }

        return false;
    }

    //坦克是否会重叠
    private void certainCollision(Tank tank1, Tank tank2) {
        int x1 = tank1.getX();
        int y1 = tank1.getY();
        int x2 = tank2.getX();
        int y2 = tank2.getY();
        int speed = tank1.getSpeed();
        switch (tank2.getLocation()) {
            case 'W':
            case 'S':
                switch (tank1.getNextLocation()) {
                    case 'W':
                        if (y1 - speed - 30 <= y2 + 70 && y1 - 30 - speed >= y2 - 30 &&
                                (x1 + 60 <= x2 + 60 && x1 + 60 >= x2 - 20 ||
                                        x1 - 20 >= x2 - 20 && x1 - 20 <= x2 + 60)) {
                            tank1.setNextLocation(' ');
                        }
                        break;
                    case 'S':
                        if (y1 + speed + 70 >= y2 - 30 && y1 + 70 + speed <= y2 + 70 &&
                                (x1 + 60 <= x2 + 60 && x1 + 60 >= x2 - 20 ||
                                        x1 - 20 >= x2 - 20 && x1 - 20 <= x2 + 60)) {
                            tank1.setNextLocation(' ');
                        }
                        break;
                    case 'A':
                        if (x1 - 30 - speed <= x2 + 60 && x1 - 30 - speed >= x2 - 20 &&
                                (y1 - 20 <= y2 + 70 && y1 - 20 >= y2 - 30 ||
                                        y1 + 60 >= y2 - 30 && y1 + 60 <= y2 + 70)) {
                            tank1.setNextLocation(' ');
                        }
                        break;
                    case 'D':
                        if (x1 + 70 + speed >= x2 - 20 && x1 + 70 + speed <= x2 + 60 &&
                                (y1 - 20 <= y2 + 70 && y1 - 20 >= y2 - 30 ||
                                        y1 + 60 >= y2 - 30 && y1 + 60 <= y2 + 70)) {
                            tank1.setNextLocation(' ');
                        }
                        break;
                    default:
                        break;
                }
                break;
            case 'A':
            case 'D':
                switch (tank1.getNextLocation()) {
                    case 'W':
                        if (y1 - 30 - speed <= y2 + 60 && y1 - 30 - speed >= y2 - 20 &&
                                (x1 - 20 >= x2 - 30 && x1 - 20 <= x2 + 70 ||
                                        x1 + 70 >= x2 - 30 && x1 + 70 <= x2 + 70)) {
                            tank1.setNextLocation(' ');
                        }
                        break;
                    case 'S':
                        if (y1 + 70 + speed >= y2 - 20 && y1 + 70 + speed <= y2 + 60 &&
                                (x1 - 20 >= x2 - 30 && x1 - 20 <= x2 + 70 ||
                                        x1 + 70 >= x2 - 30 && x1 + 70 <= x2 + 70)) {
                            tank1.setNextLocation(' ');
                        }
                        break;
                    case 'A':
                        if (x1 - 30 - speed <= x2 + 70 && x1 - 30 - speed >= x2 - 30 &&
                                (y1 - 20 >= y2 - 20 && y1 - 20 <= y2 + 60 ||
                                        y1 + 60 <= y2 + 60 && y1 + 60 >= y2 - 20)) {
                            tank1.setNextLocation(' ');
                        }
                        break;
                    case 'D':
                        if (x1 + 70 + speed >= x2 - 30 && x1 + 70 + speed <= x2 + 70 &&
                                (y1 - 20 >= y2 - 20 && y1 - 20 <= y2 + 60 ||
                                        y1 + 60 <= y2 + 60 && y1 + 60 >= y2 - 20)) {
                            tank1.setNextLocation(' ');
                        }
                        break;
                    default:
                        break;
                }
                break;
        }
    }

    //坦克是否会撞到河流
    private void certainCollision(Tank tank, River river) {
        int tx = tank.getX();
        int ty = tank.getY();
        int rx = river.getX();
        int ry = river.getY();
        int speed = tank.getSpeed();
        //对坦克方向判断
        switch (tank.getNextLocation()) {
            case 'W':
                if (ty - 30 - speed >= ry && ty - 30 - speed <= ry + 100
                        && (tx - 20 >= rx && tx - 20 <= rx + 200
                        || tx + 60 >= rx && tx + 60 <= rx + 200)) {
                    //发生碰撞，将坦克下一步行动置' '
                    tank.setNextLocation(' ');
                }
                break;
            case 'S':
                if (ty + 70 + speed >= ry && ty + 70 + speed <= ry + 100
                        && (tx - 20 >= rx && tx - 20 <= rx + 200
                        || tx + 60 >= rx && tx + 60 <= rx + 200)) {
                    tank.setNextLocation(' ');
                }
                break;
            case 'A':
                if (tx - 30 - speed <= rx + 200 && tx - 30 - speed >= rx
                        && (ty - 20 >= ry && ty - 20 <= ry + 100
                        || ty + 60 >= ry && ty + 60 <= ry + 100)) {
                    tank.setNextLocation(' ');
                }
                break;
            case 'D':
                if (tx + 70 + speed <= rx + 200 && tx + 70 + speed >= rx
                        && (ty - 20 >= ry && ty - 20 <= ry + 100
                        || ty + 60 >= ry && ty + 60 <= ry + 100)) {
                    tank.setNextLocation(' ');
                }
                break;
        }
    }

    //坦克是否会撞到石头
    private void certainCollision(Tank tank, Rock rock) {
        int tx = tank.getX();
        int ty = tank.getY();
        int rx = rock.getX();
        int ry = rock.getY();
        int speed = tank.getSpeed();
        //对坦克方向判断
        switch (tank.getNextLocation()) {
            case 'W':
                if (ty - 30 - speed >= ry && ty - 30 - speed <= ry + 40
                        && (tx - 20 >= rx && tx - 20 <= rx + 40
                        || tx + 60 >= rx && tx + 60 <= rx + 40
                        || rx >= tx - 20 && rx + 40 <= tx + 60)) {
                    //发生碰撞，将坦克下一步行动置' '
                    tank.setNextLocation(' ');
                }
                break;
            case 'S':
                if (ty + 70 + speed >= ry && ty + 70 + speed <= ry + 40
                        && (tx - 20 >= rx && tx - 20 <= rx + 40
                        || tx + 60 >= rx && tx + 60 <= rx + 40
                        || rx >= tx - 20 && rx + 40 <= tx + 60)) {
                    tank.setNextLocation(' ');
                }
                break;
            case 'A':
                if (tx - 30 - speed <= rx + 40 && tx - 30 - speed >= rx
                        && (ty - 20 >= ry && ty - 20 <= ry + 40
                        || ty + 60 >= ry && ty + 60 <= ry + 40
                        || ry >= ty - 20 && ry + 40 <= ty + 60)) {
                    tank.setNextLocation(' ');
                }
                break;
            case 'D':
                if (tx + 70 + speed <= rx + 40 && tx + 70 + speed >= rx
                        && (ty - 20 >= ry && ty - 20 <= ry + 40
                        || ty + 60 >= ry && ty + 60 <= ry + 40
                        || ry >= ty - 20 && ry + 40 <= ty + 60)) {
                    tank.setNextLocation(' ');
                }
                break;
        }
    }

    //坦克是否会撞到木箱
    private void certainCollision(Tank tank, WoodenCrate woodenCrate) {
        int tx = tank.getX();
        int ty = tank.getY();
        int wx = woodenCrate.getX();
        int wy = woodenCrate.getY();
        int speed = tank.getSpeed();
        //对坦克方向判断
        switch (tank.getNextLocation()) {
            case 'W':
                if (ty - 30 - speed >= wy && ty - 30 - speed <= wy + 40
                        && (tx - 20 >= wx && tx - 20 <= wx + 40
                        || tx + 60 >= wx && tx + 60 <= wx + 40
                        || wx >= tx - 20 && wx + 40 <= tx + 60)){
                    //发生碰撞，将坦克下一步行动置' '
                    tank.setNextLocation(' ');
                }
                break;
            case 'S':
                if (ty + 70 + speed >= wy && ty + 70 + speed <= wy + 40
                        && (tx - 20 >= wx && tx - 20 <= wx + 40
                        || tx + 60 >= wx && tx + 60 <= wx + 40
                        || wx >= tx - 20 && wx + 40 <= tx + 60)) {
                    tank.setNextLocation(' ');
                }
                break;
            case 'A':
                if (tx - 30 - speed <= wx + 40 && tx - 30 - speed >= wx
                        && (ty - 20 >= wy && ty - 20 <= wy + 40
                        || ty + 60 >= wy && ty + 60 <= wy + 40
                        || wy >= ty - 20 && wy + 40 <= ty + 60)) {
                    tank.setNextLocation(' ');
                }
                break;
            case 'D':
                if (tx + 70 + speed <= wx + 40 && tx + 70 + speed >= wx
                        && (ty - 20 >= wy && ty - 20 <= wy + 40
                        || ty + 60 >= wy && ty + 60 <= wy + 40
                        || wy >= ty - 20 && wy + 40 <= ty + 60
                )) {
                    tank.setNextLocation(' ');
                }
                break;
        }
    }

    //子弹是否击中坦克
    private boolean certainCollision(Cartridge cartridge, Tank tank) {
        int x = cartridge.getX();
        int y = cartridge.getY();
        int tx = tank.getX();
        int ty = tank.getY();
        switch (tank.getLocation()) {//判断坦克朝向坐标
            case 'A':
            case 'D':
                if (x + 6 > tx - 30 && x < tx + 70 &&
                        y + 6 > ty - 20 && y < ty + 60) {
                    if (tank instanceof EnemyTank) {
                        tank.setSurvival(false);//坦克被消灭
                        Record.kills++;//击杀数加1
                    } else if (tank instanceof Player) {
                        Record.playerLive--;//生命值减1
                    }

                    cartridge.setSurvival(false);//子弹消失

                    //加载爆炸动画
                    Explosion.explosions.add(new Explosion(tx - 30, ty - 20));

                    return true;
                }
                break;
            case 'S':
            case 'W':
                if (x + 6 > tx - 20 && x < tx + 60 &&
                        y + 6 > ty - 30 && y < ty + 70) {
                    if (tank instanceof EnemyTank) {
                        tank.setSurvival(false);//坦克被消灭
                        Record.kills++;//击杀数加1
                    } else if (tank instanceof Player) {
                        Record.playerLive--;//生命值减1
                    }
                    cartridge.setSurvival(false);//子弹消失

                    //加载爆炸动画
                    Explosion.explosions.add(new Explosion(tx - 30, ty - 20));

                    return true;
                }
                break;
        }
        return false;
    }

    //子弹是否会撞到石头
    private boolean certainCollision(Cartridge cartridge, Rock rock) {
        int cx = cartridge.getX();
        int cy = cartridge.getY();
        int rx = rock.getX();
        int ry = rock.getY();
        int speed = cartridge.getSpeed();
        //对子弹方向判断
        switch (cartridge.getLocation()) {
            case 'W':
                if (cy - speed <= ry + 40 && cy - speed >= ry
                        && (cx >= rx && cx <= rx + 40
                        || cx + 6 >= rx && cx + 6 <= rx + 40)) {
                    //击中，将子弹销毁
                    cartridge.setSurvival(false);
                    //返回结果
                    return true;
                }
                break;
            case 'S':
                if (cy + speed <= ry + 40 && cy + speed >= ry
                        && (cx >= rx && cx <= rx + 40
                        || cx + 6 >= rx && cx + 6 <= rx + 40)) {
                    cartridge.setSurvival(false);
                    return true;
                }
                break;
            case 'A':
                if (cx - speed <= rx + 40 && cx - speed >= rx
                        && (cy >= ry && cy <= ry + 40
                        || cy + 6 >= ry && cy + 6 <= ry + 40)) {
                    cartridge.setSurvival(false);
                    return true;
                }
                break;
            case 'D':
                if (cx + speed <= rx + 40 && cx + speed >= rx
                        && (cy >= ry && cy <= ry + 40
                        || cy + 6 >= ry && cy + 6 <= ry + 40)) {
                    cartridge.setSurvival(false);
                    return true;
                }
                break;
        }
        return false;
    }

    //子弹是否会撞到木箱
    private boolean certainCollision(Cartridge cartridge, WoodenCrate woodenCrate) {
        int cx = cartridge.getX();
        int cy = cartridge.getY();
        int wx = woodenCrate.getX();
        int wy = woodenCrate.getY();
        int speed = cartridge.getSpeed();
        //对子弹方向判断
        switch (cartridge.getLocation()) {
            case 'W':
                if (cy - speed <= wy + 40 && cy - speed >= wy
                        && (cx >= wx && cx <= wx + 40
                        || cx + 6 >= wx && cx + 6 <= wx + 40)) {
                    //击中，将子弹销毁
                    cartridge.setSurvival(false);
                    //返回结果
                    return true;
                }
                break;
            case 'S':
                if (cy + speed <= wy + 40 && cy + speed >= wy
                        && (cx >= wx && cx <= wx + 40
                        || cx + 6 >= wx && cx + 6 <= wx + 40)) {
                    cartridge.setSurvival(false);
                    return true;
                }
                break;
            case 'A':
                if (cx - speed <= wx + 40 && cx - speed >= wx
                        && (cy >= wy && cy <= wy + 40
                        || cy + 6 >= wy && cy + 6 <= wy + 40)) {
                    cartridge.setSurvival(false);
                    return true;
                }
                break;
            case 'D':
                if (cx + speed <= wx + 40 && cx + speed >= wx
                        && (cy >= wy && cy <= wy + 40
                        || cy + 6 >= wy && cy + 6 <= wy + 40)) {
                    cartridge.setSurvival(false);
                    return true;
                }
                break;
        }
        return false;
    }
}
