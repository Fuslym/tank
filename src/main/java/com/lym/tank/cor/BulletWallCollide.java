package com.lym.tank.cor;

import com.lym.tank.Bullet;
import com.lym.tank.GameObject;
import com.lym.tank.Wall;

/**
 * @author li yamin
 * @create 2022-09-23
 */
public class BulletWallCollide implements Collide {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Wall){
            Bullet bullet = (Bullet)o1;
            Wall wall = (Wall)o2;
            if (bullet.rectangle.intersects(wall.rectangle)){
                bullet.die();
            }
        }else if (o2 instanceof Bullet && o1 instanceof Wall){
            return collide(o2, o1);
        }
        return false;
    }
}
