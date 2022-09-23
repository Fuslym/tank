package com.lym.tank.cor;

import com.lym.tank.GameObject;
import com.lym.tank.Tank;

/**
 * @author li yamin
 * @create 2022-09-23
 */
public class TankTankCollide implements Collide {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Tank){
            Tank tank1 = (Tank)o1;
            Tank tank2 = (Tank)o2;
            if (tank1.rectangle.intersects(tank2.rectangle)){
                tank1.back();
                tank2.back();
            }
        }
        return false;
    }
}
