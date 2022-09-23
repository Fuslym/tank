package com.lym.tank;

import java.awt.*;

/**
 * @author li yamin
 * @create 2022-09-23
 */
public class Wall extends GameObject{
    int weight, height;
    public Rectangle rectangle;
    public Wall(int x, int y, int w, int h){
        this.x = x;
        this.y = y;
        this.weight = w;
        this.height = h;

        this.rectangle = new Rectangle(x,y,w,h);
    }
    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.DARK_GRAY);
        g.fillRect(x,y,weight,height);
        g.setColor(c);
    }
}
