package tankgame.Tank;

import tankgame.Background.Background;

import java.io.Serializable;
import java.util.Vector;

/**
 * @author 张俊斌
 * @version 1.0
 */
public class Tank implements Serializable {

    private int x;
    private int y;
    private int speed = 2;
    private char location;//坦克当前位置
    private char nextLocation;//坦克下一个位置
    private boolean survival = true;//坦克存活情况
    private Vector<Cartridge> cartridges = new Vector<>();//发射的所有子弹

    public Tank(int x, int y, char location) {
        this.x = x;
        this.y = y;
        this.location = location;
    }

    //进行下一步行动
    public void nextAction() {
        if (nextLocation == 'J') {
            //J键开火
            fire();
        }else if (location == nextLocation) {
            //当前朝向和下一步行动朝向相同，进行移动
            movement();
        } else {
            //进行坦克转向
            rotation();
        }

        //这条语句用于在不进行指令输入下，让坦克一直自行同向移动，去掉后坦克只由指令调动
        nextLocation = location;
    }

    //发射子弹
    public void fire() {
        //创建一个新的子弹放入子弹集合
        cartridges.add(new Cartridge(x, y, location, speed * 2));
    }

    //坦克移动
    public void movement() {
        switch (nextLocation) {
            //对应朝向，进行对应的位置改变，并且移动不能超出地图边界
            case 'A':
                if (x - 30 - speed < 0) {
                    break;
                }
                left();
                break;
            case 'W':
                if (y - 30 - speed < 0) {
                    break;
                }
                up();
                break;
            case 'S':
                if (y + 70 + speed > Background.Y_MAX) {
                    break;
                }
                down();
                break;
            case 'D':
                if (x + 70 + speed > Background.X_MAX) {
                    break;
                }
                right();
                break;
            default:
                break;
        }
        nextLocation = ' ';
    }

    //坦克转向
    public void rotation() {
        //是否是正常方向指令
        if (nextLocation == 'W'
                || nextLocation == 'S'
                || nextLocation == 'A'
                || nextLocation == 'D'){
            //将指令给到当前指令
            location = nextLocation;
        }
        nextLocation = ' ';
    }
//    public void tankOverlapDetection(Tank tank) {
//        int tx = tank.getX();
//        int ty = tank.getY();
//        switch (tank.getLocation()) {
//            case 'W':
//            case 'S':
//                switch (nextLocation) {
//                    case 'W':
//                        if (y - speed - 30<= ty + 70 && y - 30 - speed >= ty - 30 &&
//                                (x + 60 <= tx + 60 && x + 60 >= tx - 20 ||
//                                        x - 20 >= tx - 20 && x - 20 <= tx + 60)) {
//                            nextLocation = ' ';
//                        }
//                        break;
//                    case 'S':
//                        if (y + speed + 70  >= ty - 30 && y + 70 + speed <= ty + 70 &&
//                                (x + 60 <= tx + 60 && x + 60 >= tx - 20 ||
//                                        x - 20 >= tx - 20 && x - 20 <= tx + 60)) {
//                            nextLocation = ' ';
//                        }
//                        break;
//                    case 'A':
//                        if (x - 30 - speed <= tx + 60 && x - 30 - speed >= tx - 20 &&
//                                (y - 20 <= ty + 70 && y - 20 >= ty - 30 ||
//                                        y + 60 >= ty - 30 && y + 60 <= ty + 70)) {
//                            nextLocation = ' ';
//                        }
//                        break;
//                    case 'D':
//                        if (x + 70 + speed >= tx - 20 && x + 70 + speed <= tx + 60 &&
//                                (y - 20 <= ty + 70 && y - 20 >= ty - 30 ||
//                                        y + 60 >= ty - 30 && y + 60 <= ty + 70)) {
//                            nextLocation = ' ';
//                        }
//                        break;
//                    default:
//                        break;
//                }
//                break;
//            case 'A':
//            case 'D':
//                switch (nextLocation) {
//                    case 'W':
//                        if (y - 30 - speed <= ty + 60 && y - 30 - speed >= ty - 20 &&
//                                (x - 20 >= tx - 30 && x - 20 <= tx + 70 ||
//                                        x + 70 >= tx - 30 && x + 70 <= tx + 70)) {
//                            nextLocation = ' ';
//                        }
//                        break;
//                    case 'S':
//                        if (y + 70 + speed >= ty - 20 && y + 70 + speed <= ty + 60 &&
//                                (x - 20 >= tx - 30 && x - 20 <= tx + 70 ||
//                                        x + 70 >= tx - 30 && x + 70 <= tx + 70)) {
//                            nextLocation = ' ';
//                        }
//                        break;
//                    case 'A':
//                        if (x - 30 - speed <= tx + 70 && x - 30 - speed >= tx - 30 &&
//                                (y - 20 >= ty - 20 && y - 20 <= ty + 60 ||
//                                        y + 60 <= ty + 60 && y + 60 >= ty - 20)) {
//                            nextLocation = ' ';
//                        }
//                        break;
//                    case 'D':
//                        if (x + 70 + speed >= tx - 30 && x + 70 + speed <= tx + 70 &&
//                                (y - 20 >= ty - 20 && y - 20 <= ty + 60 ||
//                                        y + 60 <= ty + 60 && y + 60 >= ty - 20)) {
//                            nextLocation = ' ';
//                        }
//                        break;
//                    default:
//                        break;
//                }
//                break;
//        }
//    }

    //向左移动
    public void left() {//向左移动
        x -= speed;
    }

    //向右移动
    public void right() {//向右移动
        x += speed;
    }

    //向上移动
    public void up() {//向上移动
        y -= speed;
    }

    //向下移动
    public void down() {//向下移动
        y += speed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getLocation() {
        return location;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void setLocation(char location) {
        this.location = location;
    }

    public char getNextLocation() {
        return nextLocation;
    }

    public void setNextLocation(char nextLocation) {
        this.nextLocation = nextLocation;
    }

    public Vector<Cartridge> getCartridges() {
        return cartridges;
    }

    public boolean getSurvival() {
        return survival;
    }

    public void setSurvival(boolean survival) {
        this.survival = survival;
    }
}
