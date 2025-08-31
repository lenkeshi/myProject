package tankgame.Tank;

import tankgame.Tank.Tank;

/**
 * @author 张俊斌
 * @version 1.0
 */
public class Player extends Tank {
    public Player(int x, int y) {
        super(x, y,'W');//玩家坦克默认初始向上
    }
}
