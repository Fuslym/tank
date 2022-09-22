package com.lym.tank;

/**
 * @author li yamin
 * @create 2022-09-22
 */
public class FourDirFireStrategy implements FireStrategy {
    @Override
    public void fire(Tank t) {
        int bx = t.getX() + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int by = t.getY() + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        int n = Dir.values().length;
        for (int i = 0; i < n; i++) {
            new Bullet(bx,by,Dir.values()[i],t.getGroup(),t.gameModel);
        }

    }
}
