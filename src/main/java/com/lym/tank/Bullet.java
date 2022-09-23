package com.lym.tank;

import java.awt.*;

/**
 * @author li yamin
 * @create 2022-09-20
 */
public class Bullet extends GameObject{
    private int x, y;
    private static final int SPEED = 8; // 速度
    private Dir dir;
    public static final int HEIGHT = ResourceMgr.bulletD.getHeight();
    public static final int WIDTH = ResourceMgr.bulletD.getWidth();
    private Group group = Group.BAD;
    private boolean living = true;
    GameModel gameModel = null;
    public Bullet(){}
    Rectangle rectangle = new Rectangle();

    public Bullet(int x, int y, Dir dir,Group group,GameModel gameModel) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.gameModel = gameModel;
        this.group = group;

        rectangle.x = this.x;
        rectangle.y = this.y;
        rectangle.width = WIDTH;
        rectangle.height = HEIGHT;

        gameModel.add(this);// new出来时就加入到list集合中，这样在fire()可以直接new对象出来就可以
    }

    public void paint(Graphics g) {
        if (!living){// 死了
            gameModel.remove(this);
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
        rectangle.x = this.x;
        rectangle.y = this.y;
    }
    public boolean isLive() {
        return living;
    }

    public void setLive(boolean live) {
        this.living = live;
    }

    public void collideWith(Tank tank) {
        if (this.group == tank.getGroup()) return;// 子弹对同类无效
        // 每次都要new n* m个对象，需要改为用一个Rect来记录子弹的位置
//        Rectangle bulletRect = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
//        Rectangle tankRect = new Rectangle(tank.getX(),tank.getY(), Tank.WIDTH, Tank.HEIGHT);
        if (this.rectangle.intersects(tank.rectangle)){
            tank.die();
            this.die();
            int ex = tank.getX() + Tank.WIDTH / 2 - Explode.WIDTH / 2;
            int ey = tank.getY() + Tank.HEIGHT / 2 - Explode.HEIGHT / 2;
            gameModel.add(new Explode(ex, ey, this.gameModel));// 爆炸
        }
    }

    private void die() {
        living = false;
    }
}
