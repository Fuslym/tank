package com.lym.tank;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author li yamin
 * @create 2022-09-22
 */
public class GameModel {
    Tank tank = new Tank(200,500, Dir.UP,Group.GOOD,this);// 我方坦克
    List<Bullet> bulletList = new ArrayList<Bullet>(); // 子弹
    public List<Tank> tankList = new ArrayList<Tank>(); // 敌人坦克，public 另一个类才能用
    List<Explode> explodeList = new ArrayList<>();
    private static final int count = Integer.parseInt((String) PropertyMgr.get("initTankCount"));

    public GameModel(){
        for (int i = 0; i < count; i++) {
            this.tankList.add(new Tank(100 + i * 50,200, Dir.DOWN, Group.BAD,this));
        }
    }

    public void paint(Graphics g) {

        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量:" + bulletList.size(), 10, 40);
        g.drawString("敌人的数量:" + tankList.size(), 10, 60);
        g.drawString("爆炸数量:" + explodeList.size(), 10, 80);
        this.tank.paint(g);// 坦克画自己,我方坦克
        this.tank.setMoving(false);// 我方坦克开始静止
        // 画子弹
        for (int i = 0; i < this.bulletList.size(); i++) {
            this.bulletList.get(i).paint(g);
        }

        // 画敌方坦克
        for (int i = 0; i < tankList.size(); i++) {
            tankList.get(i).paint(g);
        }

        // 画子弹
        for (int i = 0; i < explodeList.size(); i++) {
            explodeList.get(i).paint(g);
        }

        // 每个子弹去和敌人去检测碰撞
        for (int i = 0; i < bulletList.size(); i++) {
            for (int j = 0; j < tankList.size(); j++) {
                bulletList.get(i).collideWith(tankList.get(j));
            }
//            bulletList.get(i).collideWith(tank);// 自己加的打我方坦克
        }
        //这种迭代器的也适用
//        for (Iterator it = bulletList.iterator();it.hasNext();){
//            Bullet b = (Bullet) it.next();
//            if (!b.isLive()){
//                it.remove();
//            }
//        }
        // 迭代器会出错
//        for(Bullet bullet : bulletList){
//            bullet.paint(g);
//        }
//        explode.paint(g);

    }
}
