
import java.util.Timer;
import java.util.TimerTask;

/**
 * session 定时检测测试
 *
 * @Author: [liguiqin]
 * @Date: [2016-12-01 11:42]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class QuartzTest {
    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
//                System.out.println("111");
            }
        }, 2000, 1000);

        Timer timer2 = new Timer();
        timer2.schedule(new TimerTask() {
            public void run() {
//                System.out.println("222");
            }
        }, 5000, 1000);

    }
}
