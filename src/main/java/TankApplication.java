import com.lym.tank.TankFrame;

/**
 * @author li yamin
 * @create 2022-09-20
 */
public class TankApplication {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tank = new TankFrame();
        while (true){
            Thread.sleep(50);
            tank.repaint();
        }
    }
}
