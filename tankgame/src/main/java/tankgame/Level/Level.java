package tankgame.Level;

import tankgame.Background.*;
import tankgame.SaveAndLoad.Record;
import tankgame.Tank.Cartridge;
import tankgame.Tank.Collision;
import tankgame.Tank.EnemyTank;
import tankgame.Tank.Player;

import java.awt.*;
import java.util.Vector;

/**
 * @author 张俊斌
 * @version 1.0
 */
public class Level {
    Player player;
    Vector<EnemyTank> et;
    Collision collision;
    Vector<Background> backgrounds;
    Vector<Bridge> bridges;
    Vector<River> rivers;
    Vector<Rock> rocks;
    Vector<WoodenCrate> woodenCrates;

    public Level() {
        collision = new Collision();
        backgrounds = new Vector<>();
        bridges = new Vector<>();
        rivers = new Vector<>();
        rocks = new Vector<>();
        woodenCrates = new Vector<>();
        Record.setLevel(this);
    }

    //初始化关卡内容
    public void initialize() {

    }

    //获取所有的背景物品
    public Vector<Background> getBackgrounds() {
        if(backgrounds.size() != 0){
            //集合不为空，返回集合
            return backgrounds;
        }

        if (bridges.size() != 0) {
            //添加桥的集合的内容
            backgrounds.addAll(bridges);
        }
        if (rivers.size() != 0) {
            //添加河的集合的内容
            backgrounds.addAll(rivers);
        }
        if (rocks.size() != 0) {
            //添加石头的集合的内容
            backgrounds.addAll(rocks);
        }
        if(woodenCrates.size() != 0){
            //添加木箱的集合的内容
            backgrounds.addAll(woodenCrates);
        }
        return backgrounds;
    }


    //所有物体的行动
    public boolean allAction(){
        //调用certainCollision(Level level)方法查看所有可移动物体的碰撞结果
        if(collision.certainCollision(this)){
            //玩家死亡
            //跳出
            return true;
        }

        //玩家坦克进行下一步行动
        player.nextAction();

        //敌人坦克进行下一步行动
        for (EnemyTank e :et) {
            e.nextAction();
        }

        //正常运行
        return false;
    }

    //画所有的物品
    public void drawAllBackground(Graphics g) {
        //画底色
        Background.drawBackground(g);

        //循环背景集合绘画
        for (int i = 0; i < backgrounds.size(); i++) {
            Background bg = backgrounds.get(i);
            //判断该背景的运行态是什么类进行对应绘画方法调用
            if (bg instanceof Bridge) {
                bg.drawBridge(g);
            } else if (bg instanceof River) {
                bg.drawRiver(g);
            } else if (bg instanceof Rock) {
                bg.drawRock(g);
            } else if (bg instanceof WoodenCrate) {
                bg.drawWoodenCrate(g);
            } else {
                System.out.println("存档类型有误！！");
            }
        }

        //画敌人的坦克和子弹
        for (int i = 0; i < et.size(); i++) {
            //画坦克
            TankModel.drawTank(et.get(i),g);

            Vector<Cartridge> cartridges = et.get(i).getCartridges();
            for (int j = 0; j < cartridges.size(); j++) {
                Cartridge cartridge = cartridges.get(j);
                if(!cartridge.getSurvival()){
                    //子弹消亡，将其移除
                    cartridges.remove(cartridge);
                    continue;
                }
                //画子弹
                CartridgeModel.drawCartridgeModel(cartridge,g);
            }
        }

        //画玩家坦克
        TankModel.drawTank(player,g);

        //画玩家子弹
        Vector<Cartridge> cartridges = player.getCartridges();
        for (int i = 0; i < cartridges.size(); i++) {
            Cartridge cartridge = cartridges.get(i);
            if(!cartridge.getSurvival()){
                //子弹消亡，将其移除
                cartridges.remove(cartridge);
                continue;
            }
            //画子弹
            CartridgeModel.drawCartridgeModel(cartridge,g);
        }

        //画爆炸效果
        for (int i = 0; i < Explosion.explosions.size(); i++) {
            //画出爆炸效果并判断爆炸时长是否完尽
            if(Explosion.explosions.get(i).drawExplosion(g)){
                //移除爆炸
                Explosion.explosions.remove(i);
            }
        }

    }

    public Player getPlayer() {
        return player;
    }

    public Vector<EnemyTank> getEnemyTank() {
        return et;
    }

    public void setBackgrounds(Vector<Background> backgrounds) {
        this.backgrounds = backgrounds;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setEt(Vector<EnemyTank> et) {
        this.et = et;
    }
}
