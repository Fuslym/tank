import com.lym.tank.*;

/**
 * @author li yamin
 * @create 2022-09-20
 */
public class TankApplication {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();
        while (true){
            Thread.sleep(50);
            tankFrame.repaint();
        }
    }
}
