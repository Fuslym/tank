package com.lym.tank.cor;

import com.lym.tank.Bullet;
import com.lym.tank.GameObject;
import com.lym.tank.Tank;

/**
 * @author li yamin
 * @create 2022-09-22
 */
public class BulletTankCollider implements Collider {
    @Override
    public void collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Tank){
            Bullet bullet = (Bullet)o1;
            Tank tank = (Tank)o2;
            bullet.collideWith(tank);
        }else if (o1 instanceof Tank && o2 instanceof Bullet){
            collide(o2, o1);
        }else{
            return;
        }
    }
}
