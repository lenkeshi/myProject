package tankgame.Tank;

import tankgame.Tank.Tank;

/**
 * @author 张俊斌
 * @version 1.0
 */
public class EnemyTank extends Tank implements Runnable{
    private int n;
    public EnemyTank(int x, int y) {
        super(x, y, 'S');//敌人坦克默认初始向下
    }

    @Override
    public void run() {
        while (this.getSurvival()){//敌人坦克自动化逻辑
            n = (int) (Math.random() * 10);
            if(n < 2){
                this.setNextLocation('A');
            }else if(n < 4){
                this.setNextLocation('W');
            }else if(n < 6){
                this.setNextLocation('S');
            }else if(n < 8){
                this.setNextLocation('D');
            }else if(n < 10){
                this.setNextLocation('J');
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
