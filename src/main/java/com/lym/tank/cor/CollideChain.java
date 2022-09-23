package com.lym.tank.cor;

import com.lym.tank.FireStrategy;
import com.lym.tank.GameObject;
import com.lym.tank.PropertyMgr;

import java.util.LinkedList;
import java.util.List;

/**
 * @author li yamin
 * @create 2022-09-23
 */
public class CollideChain implements Collide {
    private List<Collide> collideList = new LinkedList<>();

    public CollideChain(){
//        String className = (String) PropertyMgr.get("collision");
//        try {
//            List<> = Class.forName(className).getDeclaredConstructor().newInstance();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        add(new BulletTankCollide());
        add(new TankTankCollide());
    }
    public void add(Collide c){
        collideList.add(c);
    }

    public boolean collide(GameObject o1, GameObject o2) {
        boolean flag = false;
        for (int i = 0; i < collideList.size(); i++) {
            flag = collideList.get(i).collide(o1, o2);
            if (flag == true) break;//撞上结束后续碰撞
        }
        return flag;
    }
}
