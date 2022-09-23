package com.lym.tank.cor;

import com.lym.tank.GameObject;

/**
 * @author li yamin
 * @create 2022-09-22
 */
public interface Collide {
    boolean collide(GameObject o1, GameObject o2);//撞上返回true，否则返回false
}
