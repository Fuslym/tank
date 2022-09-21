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
    public static final int HEIGHT = ResourceMgr.tankD.getHeight();
    public static final int WIDTH = ResourceMgr.tankD.getWidth();

    public Tank(){}

    public Tank(int x, int y, Dir dir,TankFrame tankFrame){
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
    }

    public void paint(Graphics g) {
        switch (dir){
            case DOWN:
                g.drawImage(ResourceMgr.tankD,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceMgr.tankU,x,y,null);
                break;
            case LEFT:
                g.drawImage(ResourceMgr.tankL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.tankR,x,y,null);
        }
        move();

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

    private  void move(){
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
    public void fire() {
        int bx = this.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int by = this.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        tankFrame.bulletList.add(new Bullet(bx,by,this.dir,tankFrame));
    }
}
