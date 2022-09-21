package com.lym.tank;

import java.awt.*;

/**
 * @author li yamin
 * @create 2022-09-21
 */
public class Explode {
    public static final int HEIGHT = ResourceMgr.explodes[0].getHeight();
    public static final int WIDTH = ResourceMgr.explodes[0].getWidth();
    private int x,y;
    private int step = 0;
    TankFrame tankFrame = null;

    public Explode(int x, int y, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.tankFrame = tankFrame;
    }

    public void paint(Graphics g){
        g.drawImage(ResourceMgr.explodes[step++],x,y,null);
        if (step == ResourceMgr.explodes.length){
            this.tankFrame.explodeList.remove(this);
        }
    }
}
