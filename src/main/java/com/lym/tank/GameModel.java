package com.lym.tank;

import com.lym.tank.cor.BulletTankCollide;
import com.lym.tank.cor.Collide;
import com.lym.tank.cor.CollideChain;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author li yamin
 * @create 2022-09-22
 */
public class GameModel {
    private static final GameModel INSTANCE = new GameModel();
    Tank tank = new Tank(200,500, Dir.UP,Group.GOOD);// 我方坦克
//    List<Bullet> bulletList = new ArrayList<Bullet>(); // 子弹
//    List<Tank> tankList = new ArrayList<Tank>(); // 敌人坦克
//    List<Explode> explodeList = new ArrayList<>();
    List<GameObject> objects = new ArrayList<>();
//    Collide cor = new BulletTankCollide();
    CollideChain collideChain = new CollideChain();
    private static final int count = Integer.parseInt((String) PropertyMgr.get("initTankCount"));

    static {
        INSTANCE.initial();
    }

    private GameModel(){}
    public static GameModel getInstance(){
        return INSTANCE;
    }

    private void initial(){
        this.tank.setMoving(false);// 我方坦克开始静止

        // 初始化敌方坦克
        for (int i = 0; i < count; i++) {
            add(new Tank(100 + i * 50,200, Dir.DOWN, Group.BAD));
        }

        // 初始化墙
        add(new Wall(150, 150, 200, 50));
        add(new Wall(550, 150, 200, 50));
        add(new Wall(300, 300, 50, 200));
        add(new Wall(550, 300, 50, 200));
    }
    public void add(GameObject go){
        this.objects.add(go);
    }

    public void remove(GameObject go){
        this.objects.remove(go);
    }

    public void paint(Graphics g) {

        Color c = g.getColor();
        g.setColor(Color.WHITE);
//        g.drawString("子弹的数量:" + bulletList.size(), 10, 40);
//        g.drawString("敌人的数量:" + tankList.size(), 10, 60);
//        g.drawString("爆炸数量:" + explodeList.size(), 10, 80);
        this.tank.paint(g);// 坦克画自己,我方坦克

        // 画子弹
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).paint(g);
        }

        // 现在子弹和坦克在一个实体里面，如何解决碰撞问题
        // 解决碰撞
        // 坦克与坦克之间得相撞，需要在写一个方法
        for (int i = 0; i < objects.size(); i++) {
            for (int j = i + 1 ; j < objects.size(); j++) {
                GameObject o1 = objects.get(i);
                GameObject o2 = objects.get(j);
                if (collideChain.collide(o1,o2)){// 相撞结束这次得碰撞
                    break;
                }
            }
        }

        // 每个子弹去和敌人去检测碰撞
//        for (int i = 0; i < bulletList.size(); i++) {
//            for (int j = 0; j < tankList.size(); j++) {
//                bulletList.get(i).collideWith(tankList.get(j));
//            }
//        }
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
