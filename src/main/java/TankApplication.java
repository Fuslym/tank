import com.lym.tank.Dir;
import com.lym.tank.Tank;
import com.lym.tank.TankFrame;

/**
 * @author li yamin
 * @create 2022-09-20
 */
public class TankApplication {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();
        for (int i = 0; i < 5; i++) {
            tankFrame.tankList.add(new Tank(100 + i * 50,200, Dir.DOWN,tankFrame));
        }
        while (true){
            Thread.sleep(50);
            tankFrame.repaint();
        }
    }
}
