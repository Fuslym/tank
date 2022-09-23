package com.lym.tank.cor;

import com.lym.tank.GameObject;

/**
 * @author li yamin
 * @create 2022-09-22
 */
public interface Collider {
    void collide(GameObject o1, GameObject o2);
}
