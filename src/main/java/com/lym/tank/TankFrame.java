package com.lym.tank;

import javax.lang.model.element.VariableElement;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author li yamin
 * @create 2022-09-20
 */
public class TankFrame extends Frame {

    Tank tank = new Tank(200,200, Dir.DOWN);
    Bullet bullet = new Bullet(200,200,Dir.DOWN);

    public TankFrame(){
        setVisible(true);
        setSize(800,600);
        setResizable(false); // 不能改变窗口
        setTitle("坦克大战");

        // 内部类
        this.addKeyListener(new MyKeyListener());

        // 匿名内部类
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    // 生成、改变图形时自动调用
    @Override
    public void paint(Graphics g){
        tank.paint(g);// 坦克画自己
        bullet.paint(g);

    }

    class MyKeyListener extends KeyAdapter{
        boolean bL = false;
        boolean bR = false;
        boolean bU = false;
        boolean bD = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir(){
            if (!bL && !bR && !bU && !bD){
                tank.setMoving(false);
            }else{
                tank.setMoving(true);
                if (bL) tank.setDir(Dir.LEFT);
                if (bR) tank.setDir(Dir.RIGHT);
                if (bU) tank.setDir(Dir.UP);
                if (bD) tank.setDir(Dir.DOWN);
            }
        }
    }
}