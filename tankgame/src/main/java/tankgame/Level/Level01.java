package tankgame.Level;


import tankgame.Background.Rock;
import tankgame.Tank.EnemyTank;
import tankgame.Tank.Player;

import java.util.Vector;

/**
 * @author 张俊斌
 * @version 1.0
 */
public class Level01 extends Level {
    public Level01() {//初始化

        player = new Player(630, 680);
        et = new Vector<>();
        et.add(new EnemyTank(330, 80));
        et.add(new EnemyTank(930, 80));
        for (int i = 0; i < et.size(); i++) {
            new Thread(et.get(i)).start();
        }
        //初始化背景
        initialize();

        //将所有背景统装到集合背景中
        getBackgrounds();
    }

    //初始化关卡1背景
    public void initialize() {
        for (int i = 150; i < 550; i += 40) {
            for (int j = 200; j < 600; j += 40) {
                rocks.add(new Rock(i, j));
            }
        }
        for (int i = 750; i < 1150; i += 40) {
            for (int j = 200; j < 600; j += 40) {
                rocks.add(new Rock(i, j));
            }
        }

    }
}
