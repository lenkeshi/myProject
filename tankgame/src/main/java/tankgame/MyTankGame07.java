package tankgame;

import tankgame.Background.Background;
import tankgame.SaveAndLoad.Record;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author 张俊斌
 * @version 1.0
 */
public class MyTankGame07 extends JFrame {
    MyPanel myPanel;

    public static void main(String[] args) {
        MyTankGame07 myTankGame07 = new MyTankGame07();
    }

    public MyTankGame07(){



        //创建画板对象
        myPanel = new MyPanel();
        this.add(myPanel);

        //设置画板窗口显示大小
        this.setSize(Background.X_MAX + 410, Background.Y_MAX + 48);

        //启动线程
        new Thread(myPanel).start();

        //监听键盘输入
        this.addKeyListener(myPanel);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        //监听通过窗口x关闭窗口
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //保存数据
                Record.saveData();
                //关闭程序
                System.exit(0);
            }
        });
    }
}
