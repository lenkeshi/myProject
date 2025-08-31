package tankgame.Level;

import tankgame.Background.Bridge;
import tankgame.Background.River;
import tankgame.Background.Rock;
import tankgame.Background.WoodenCrate;
import tankgame.Tank.EnemyTank;
import tankgame.Tank.Player;

import java.awt.*;
import java.util.Vector;

/**
 * @author 张俊斌
 * @version 1.0
 */
public class Level03 extends Level {
    public Level03() {//初始化

        player = new Player(630, 720);
        et = new Vector<>();
        et.add(new EnemyTank(630, 40));
        et.add(new EnemyTank(45, 320));
        et.add(new EnemyTank(1215, 320));
        et.add(new EnemyTank(45, 580));
        et.add(new EnemyTank(1215, 580));
        for (int i = 0; i < et.size(); i++) {
            new Thread(et.get(i)).start();
        }
        //初始化背景
        initialize();

        //将所有背景统装到集合背景中
        getBackgrounds();
    }

    //初始化关卡3背景
    public void initialize() {
        bridges.add(new Bridge(200, 160));
        bridges.add(new Bridge(970, 160));
        bridges.add(new Bridge(585, 420));


        rivers.add(new River(0, 160));
        rivers.add(new River(330, 160));
        rivers.add(new River(530, 160));
        rivers.add(new River(730, 160));
        rivers.add(new River(770, 160));
        rivers.add(new River(1100, 160));
        rivers.add(new River(0, 420));
        rivers.add(new River(200, 420));
        rivers.add(new River(385, 420));
        rivers.add(new River(715, 420));
        rivers.add(new River(915, 420));
        rivers.add(new River(1100, 420));


        for (int i = 330; i < 970; i += 40) {
            rocks.add(new Rock(i, 120));
        }
        for (int i = 260; i < 420; i += 40) {
            rocks.add(new Rock(130, i));
        }
        for (int i = 260; i < 420; i += 40) {
            rocks.add(new Rock(1130, i));
        }
        for (int i = 130; i < 1170; i += 40) {
            rocks.add(new Rock(i, 640));
        }


        for (int i = 170; i < 1130; i += 40) {
            for (int j = 280; j < 400; j += 40) {
                woodenCrates.add(new WoodenCrate(i, j));
            }
        }
        for (int i = 130; i < 1170; i += 40) {
            for (int j = 540; j < 620; j += 40) {
                woodenCrates.add(new WoodenCrate(i, j));
            }
        }
        for (int i = 40; i < 560; i += 40) {
            for (int j = 700; j < 760; j += 40) {
                woodenCrates.add(new WoodenCrate(i, j));
            }
        }
        for (int i = 740; i < 1260; i += 40) {
            for (int j = 700; j < 760; j += 40) {
                woodenCrates.add(new WoodenCrate(i, j));
            }
        }
    }
}
