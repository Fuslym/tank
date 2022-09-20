package com.lym.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * @author li yamin
 * @create 2022-09-20
 */
public class TankFrame extends Frame {

    private static int GAME_WIDTH = 800, GAME_HEIGHT = 600;
    java.util.List<Bullet> bulletList = new java.util.ArrayList<Bullet>();
    Tank tank = new Tank(200,200, Dir.DOWN,this);
    Bullet bullet = new Bullet(200,200,Dir.DOWN);

    public TankFrame(){
        setVisible(true);
        setSize(GAME_WIDTH,GAME_HEIGHT);
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
        for(Bullet bullet : bulletList){
            bullet.paint(g);
        }

    }

    Image offScreenImage = null;

    @Override
    public void update(Graphics g){
        if (offScreenImage == null){
            offScreenImage = this.createImage(GAME_WIDTH,GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.black);
        gOffScreen.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage,0,0,null);
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
                case KeyEvent.VK_CONTROL:
                    tank.fire();
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
