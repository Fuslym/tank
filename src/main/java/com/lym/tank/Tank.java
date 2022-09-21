package com.lym.tank;

import java.awt.*;
import java.util.Random;

/**
 * @author li yamin
 * @create 2022-09-20
 */
public class Tank {
    private int x;
    private int y; // 位置
    private Dir dir = Dir.DOWN; // 方向
    private static final int SPEED = 5; // 速度
    private boolean moving = true; // 是否静止
    private TankFrame tankFrame = null;
    public static final int HEIGHT = ResourceMgr.tankD.getHeight();
    public static final int WIDTH = ResourceMgr.tankD.getWidth();
    private boolean living = true; //生命
    private Group group = Group.BAD;
    private Random random = new Random();
    Rectangle rectangle = new Rectangle();// 只需要一个对象
    public Tank(){}

    public Tank(int x, int y, Dir dir, Group group, TankFrame tankFrame){
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tankFrame = tankFrame;

        // rectangle赋值
        rectangle.x = this.x;
        rectangle.y = this.y;
        rectangle.width = WIDTH;
        rectangle.height = HEIGHT;
    }

    public void paint(Graphics g) {
        if (!living){
            tankFrame.tankList.remove(this);
        }
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
    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
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
        if (!moving) return;
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
            default:
                break;
        }

        // 敌方坦克随机发射
        if (this.group == Group.BAD && random.nextInt(100) > 95){
            this.fire();
        }

        // 敌方坦克随机改变方向
        if (this.group == Group.BAD && random.nextInt(100) > 95){
            randomDir();
        }

        // 边界检测
        boundsCheck();

        // 更新区域
        rectangle.x = this.x;
        rectangle.y = this.y;

    }

    private void boundsCheck() {
        if (this.x < 0) x = 2;// 控制左边界
        if (this.y < 20) y = 20;// 控制上边界
        if (this.x > TankFrame.GAME_WIDTH - Tank.WIDTH)
            x = TankFrame.GAME_WIDTH - Tank.WIDTH;
        if (this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT)
            y = TankFrame.GAME_HEIGHT - Tank.HEIGHT;
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void fire() {
        int bx = this.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int by = this.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        tankFrame.bulletList.add(new Bullet(bx,by,this.dir,this.group,tankFrame));
    }

    public void die() {
        living = false;
    }
}
