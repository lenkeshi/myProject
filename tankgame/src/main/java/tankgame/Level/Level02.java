package tankgame.Level;

import tankgame.Background.Bridge;
import tankgame.Background.River;
import tankgame.Background.Rock;
import tankgame.Tank.EnemyTank;
import tankgame.Tank.Player;


import java.util.Vector;

/**
 * @author 张俊斌
 * @version 1.0
 */
public class Level02 extends Level {
    public Level02() {//初始化

        player = new Player(630, 705);
        et = new Vector<>();
        et.add(new EnemyTank(330, 55));
        et.add(new EnemyTank(930, 55));
        for (int i = 0; i < et.size(); i++) {
            new Thread(et.get(i)).start();
        }
        //初始化背景
        initialize();

        //将所有背景统装到集合背景中
        getBackgrounds();
    }

    //初始化关卡2背景
    public void initialize() {
        bridges.add(new Bridge(200, 550));
        bridges.add(new Bridge(970, 550));
        bridges.add(new Bridge(585, 150));

        rivers.add(new River(0, 550));
        rivers.add(new River(330, 550));
        rivers.add(new River(530, 550));
        rivers.add(new River(730, 550));
        rivers.add(new River(770, 550));
        rivers.add(new River(1100, 550));
        rivers.add(new River(0, 150));
        rivers.add(new River(200, 150));
        rivers.add(new River(385, 150));
        rivers.add(new River(715, 150));
        rivers.add(new River(915, 150));
        rivers.add(new River(1100, 150));

        for (int i = 140; i < 1160; i += 40) {
            rocks.add(new Rock(i, 380));
        }
    }
}
