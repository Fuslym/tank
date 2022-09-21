package com.lym.tank;

import java.awt.*;

/**
 * @author li yamin
 * @create 2022-09-20
 */
public class Bullet {
    private int x, y;
    private static final int SPEED = 3; // 速度
    private Dir dir;
    public static final int HEIGHT = ResourceMgr.bulletD.getHeight();
    public static final int WIDTH = ResourceMgr.bulletD.getWidth();
    private Group group = Group.BAD;
    private boolean living = true;
    TankFrame tf = null;
    public Bullet(){}

    public Bullet(int x, int y, Dir dir,Group group,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;
    }

    public void paint(Graphics g) {
        if (!living){
            tf.bulletList.remove(this);
        }
        switch (dir) {
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
        }
        move();
    }

    private void move() {
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
        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT){
            living = false;
        }
    }
    public boolean isLive() {
        return living;
    }

    public void setLive(boolean live) {
        this.living = live;
    }

    public void collideWith(Tank tank) {
        if (this.group == tank.getGroup()) return;
        //TODO：用一个Rect来记录子弹的位置
        Rectangle bulletRect = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
        Rectangle tankRect = new Rectangle(tank.getX(),tank.getY(), Tank.WIDTH, Tank.HEIGHT);
        if (bulletRect.intersects(tankRect)){
            tank.die();
            this.die();
            this.tf.explodeList.add(new Explode(tank.getX(),tank.getY(),this.tf));// 爆炸
        }
    }

    private void die() {
        living = false;
    }
}
