import jdk.nashorn.internal.AssertsEnabled;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author li yamin
 * @create 2022-09-21
 */
public class ImageTest {
    @Test
    public void test() throws IOException {
        try{
            // this.getClass()可以换成ImageTest.class
            // resource下的路径都是classpath路径下
            BufferedImage image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("images/bulletD.gif"));
            System.out.println(image != null);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
