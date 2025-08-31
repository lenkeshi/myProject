package tankgame.SaveAndLoad;

import tankgame.Background.*;
import tankgame.Level.Level;
import tankgame.Tank.EnemyTank;
import tankgame.Tank.Player;

import java.io.*;
import java.util.Vector;

/**
 * @author 张俊斌
 * @version 1.0
 */
public class Record {
    private static Level level;
    public static int kills = 0;
    public static int playerLive = 3;
    //敌人坦克保存位置
    private static String saveDataET = "src\\main\\resources\\Save\\saveET.txt";
    //玩家坦克保存位置
    private static String saveDataPR = "src\\main\\resources\\Save\\savePR.txt";
    //地图背景保存位置
    private static String saveDataBG = "src\\main\\resources\\Save\\saveBG.txt";

//    public static void saveData(Player player, Vector<EnemyTank> eT) {
//        try {
//            BufferedWriter bw = new BufferedWriter(new FileWriter(saveDataET));
//            bw.write(kills + "");
//            bw.newLine();
//            for (int i = 0; i < eT.size(); i++) {
//                EnemyTank o = eT.get(i);
//                if(o.getSurvival()){
//                    bw.write(o.getX() + " " + o.getY() + " " + o.getLocation());
//                    bw.newLine();
//                }
//            }
//            bw.close();
//            bw = new BufferedWriter(new FileWriter(saveDataPR));
//            bw.write(playerLive + "");
//            bw.newLine();
//            bw.write(player.getX() + " " + player.getY() + " " + player.getLocation());
//            bw.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    //保存
    public static void saveData() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(saveDataET));
            //在敌人坦克保存文档内，先保存击杀数，再保存所有存活的敌人坦克
            bw.write(++kills + "");
            bw.newLine();
            for (int i = 0; i < level.getEnemyTank().size(); i++) {
                EnemyTank et = level.getEnemyTank().get(i);
                if (et.getSurvival()) {
                    //保存坦克的 x坐标 y坐标 当前朝向
                    bw.write(et.getX() + " " + et.getY() + " " + et.getLocation());
                    //跳行
                    bw.newLine();
                }
            }
            //末尾记录0
            bw.write("0");
            bw.close();


            bw = new BufferedWriter(new FileWriter(saveDataPR));
            //在玩家坦克保存文档内，先保存生命值，再保存位置
            bw.write(playerLive + "");
            bw.newLine();
            //也是保存坦克的 x坐标 y坐标 当前朝向
            bw.write(level.getPlayer().getX() + " " + level.getPlayer().getY() + " " + level.getPlayer().getLocation());
            //末尾记录0
            bw.write("0");
            bw.close();


            //背景物品保存
            bw = new BufferedWriter(new FileWriter(saveDataBG));
            for (int i = 0; i < level.getBackgrounds().size(); i++) {
                Background bg = level.getBackgrounds().get(i);
                //对不同的背景在保存时添加一个序列号，以便在读取时进行初始化
                if (bg instanceof Bridge) {
                    //主要保存背景的 x坐标 y坐标
                    bw.write("1 " + bg.getX() + " " + bg.getY());
                } else if (bg instanceof River) {
                    bw.write("2 " + bg.getX() + " " + bg.getY());
                } else if (bg instanceof Rock) {
                    bw.write("3 " + bg.getX() + " " + bg.getY());
                } else if (bg instanceof WoodenCrate) {
                    bw.write("4 " + bg.getX() + " " + bg.getY());
                }
                bw.newLine();
            }
            //末尾记录0
            bw.write("0");
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public static Vector<EnemyTank> loadData(){
//        String str;
//        Vector<EnemyTank> eT = new Vector<>();
//        try {
//
//            BufferedReader br = new BufferedReader(new FileReader(saveDataET));
//            if(kills == 0){
//                kills = Integer.parseInt(br.readLine());
//            }
//            while ((str = br.readLine()) != null){
//                String[] strings = str.split(" ");
//                EnemyTank enemyTank = new EnemyTank(Integer.parseInt(strings[0]),Integer.parseInt(strings[1]));
//                enemyTank.setLocation(strings[2].charAt(0));
//                eT.add(enemyTank);
//            }
//            br.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        if(eT.size() == 0){
//            return new Vector<>();
//        }
//        return eT;
//    }

    //读取存档
    public static boolean loadData() {
        //判断存档位置是否存在
        if (!fileSecurity()) {
            //位置不存在
            return false;
        }
        //初始化关卡
        level = new Level();
        String str;

        try {

            //读取敌人坦克信息
            BufferedReader br = new BufferedReader(new FileReader(saveDataET));
            Vector<EnemyTank> eT = new Vector<>();
            if (kills == 0) {
                //先读取击杀数
                kills = Integer.parseInt(br.readLine()) - 1;
            }
            while ((str = br.readLine()) != null) {//读取全部内容
                if (str.equals("0")) {
                    //读取到保存时的结束标记退出
                    break;
                }
                //以' '分割字符串
                String[] strings = str.split(" ");
                if (strings.length != 3) {
                    //存档有误
                    return false;
                }
                //初始化敌人坦克
                EnemyTank enemyTank = new EnemyTank(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]));
                enemyTank.setLocation(strings[2].charAt(0));
                //将坦克添加到集合
                eT.add(enemyTank);
                //启动敌人坦克线程
                new Thread(enemyTank).start();
            }
            if (eT.size() == 0) {
                //敌人坦克不存在，存档有误
                br.close();
                return false;
            }
            //无误，给关卡设置敌人坦克集合
            level.setEt(eT);
            br.close();


            //读取玩家存档
            br = new BufferedReader(new FileReader(saveDataPR));
            if (playerLive == 3) {
                //先读取玩家生命值
                playerLive = Integer.parseInt(br.readLine());
            }
            if ((str = br.readLine()) != null) {
                //以' '分割字符串
                String[] strings = str.split(" ");
                if (strings.length != 3) {
                    //存档有误
                    return false;
                }
                //无误，初始化玩家坦克
                Player player = new Player(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]));
                player.setLocation(strings[2].charAt(0));
                //给关卡设置玩家坦克
                level.setPlayer(player);
                br.close();

            }


            //读取背景存档
            br = new BufferedReader(new FileReader(saveDataBG));
            Vector<Background> bgs = new Vector<>();
            while ((str = br.readLine()) != null) {
                if (str.equals("0")) {
                    //读取到保存时的结束标记退出
                    break;
                }

                Background bg;
                //以' '分割字符串
                String[] strings = str.split(" ");
                //对第一个字符进行判断，来初始化对应背景
                if (strings[0].equals("1")) {
                    bg = new Bridge(Integer.parseInt(strings[1]), Integer.parseInt(strings[2]));
                    bgs.add(bg);
                } else if (strings[0].equals("2")){
                    bg = new River(Integer.parseInt(strings[1]), Integer.parseInt(strings[2]));
                    bgs.add(bg);
                } else if (strings[0].equals("3")) {
                    bg = new Rock(Integer.parseInt(strings[1]), Integer.parseInt(strings[2]));
                    bgs.add(bg);
                } else if (strings[0].equals("4")) {
                    bg = new WoodenCrate(Integer.parseInt(strings[1]), Integer.parseInt(strings[2]));
                    bgs.add(bg);
                }else {
                    //背景存档有误
                    return false;
                }
            }
            if (bgs.size() == 0) {
                //背景存档有误
                br.close();
                return false;
            }
            //无误，设置关卡背景
            level.setBackgrounds(bgs);
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        //无误
        return true;
    }


    //文档位置安全
    public static boolean fileSecurity() {
        File file = new File(saveDataET);

        if (!file.exists()) {
            //敌人坦克存档不存在
            return false;
        }

        file = new File(saveDataPR);
        if (!file.exists()) {
            //玩家坦克存档不存在
            return false;
        }

        file = new File(saveDataBG);
        if (!file.exists()) {
            //背景存档不存在
            return false;
        }
        return true;
    }

    public static void setLevel(Level level) {
        Record.level = level;
    }

    public static Level getLevel() {
        return level;
    }
}
