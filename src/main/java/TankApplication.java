import com.lym.tank.*;

/**
 * @author li yamin
 * @create 2022-09-20
 */
public class TankApplication {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();
        int count = Integer.parseInt((String) PropertyMgr.get("initTankCount"));
        for (int i = 0; i < count; i++) {
            tankFrame.tankList.add(new Tank(100 + i * 50,200, Dir.DOWN, Group.BAD,tankFrame));
        }
        while (true){
            Thread.sleep(50);
            tankFrame.repaint();
        }
    }
}
