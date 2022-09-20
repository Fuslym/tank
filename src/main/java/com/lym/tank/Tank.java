package com.lym.tank;

import java.awt.*;

/**
 * @author li yamin
 * @create 2022-09-20
 */
public class Tank {
    private int x, y; // 位置
    private Dir dir = Dir.DOWN; // 方向
    private static final int SPEED = 10; // 速度
    private boolean moving = false; // 是否静止
    private TankFrame tankFrame = null;

    public Tank(){}

    public Tank(int x, int y, Dir dir,TankFrame tankFrame){
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.fillRect(x,y,50,50);
        g.setColor(c);
        if (moving){
            switch (dir){
                case LEFT:
                    x -= SPEED;
                    break;
                case RIGHT:
                    x += SPEED;
                    break;
                case UP:
                    y -= SPEED;
                    break;
                case DOWN:
                    y += SPEED;
                    break;
            }
        }
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }
    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void fire() {
        tankFrame.bulletList.add(new Bullet(this.x,this.y,this.dir,tankFrame));
    }
}
