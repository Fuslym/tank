package com.lym.tank.cor;

import com.lym.tank.*;

/**
 * @author li yamin
 * @create 2022-09-22
 */
public class BulletTankCollide implements Collide {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Tank){
            Bullet bullet = (Bullet)o1;
            Tank tank = (Tank)o2;
            //将子弹和tank碰撞方法抽到这里，可以将子弹类和坦克类解耦
            if (bullet.group == tank.getGroup()) return false;// 子弹对同类无效
            if (bullet.rectangle.intersects(tank.rectangle)){
                tank.die();
                bullet.die();
                int ex = tank.getX() + Tank.WIDTH / 2 - Explode.WIDTH / 2;
                int ey = tank.getY() + Tank.HEIGHT / 2 - Explode.HEIGHT / 2;
                GameModel.getInstance().add(new Explode(ex, ey));// 爆炸
                return true;
            }
            return false;
        }else if (o1 instanceof Tank && o2 instanceof Bullet){
            return collide(o2, o1);
        }else{
            return false;
        }
    }
}
