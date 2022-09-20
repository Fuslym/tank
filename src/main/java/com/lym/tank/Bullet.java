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
    private static final int WIDTH = 30, HEIGHT = 30;

    public Bullet(){}

    public Bullet(int x, int y, Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.red);
        g.fillOval(x, y, WIDTH, HEIGHT);
        g.setColor(c);
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
    }
}
