import com.bjike.goddess.common.utils.date.DateUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-01-24 10:10]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class MyTest {

    public static void main(String[] args) {
        /*String q = "dsssd:";
        System.out.println(q.substring(q.length() - 1, q.length()));
        if (q.indexOf("：") > 0 || q.indexOf(":") > 0) {
            System.out.println(q.substring(0, q.length() - 1));
        }
        System.out.println(Double.valueOf("0.0")/ Double.valueOf("0.0"));

        String str = String.format("%.2f", Double.valueOf("0.0") / Double.valueOf("0.0") * 100) + "%";
        System.out.println(str);
        System.out.println(0.0/0.0);

        BigDecimal a = new BigDecimal("0.0");
        BigDecimal b = new BigDecimal("0.0");
        Double c = a.divide(b,4,BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(c);*/
        pritNoBug();


        LocalDate localDate = LocalDate.now();
        System.out.println(DateUtil.dateToString(localDate));
        System.out.println(DateUtil.parseDate("2018-03-30").getMonthValue() - 1);
        test();
        System.out.println(new Date());
        System.out.println(LocalDateTime.now());
    }

    public static void pritNoBug(){
        System.out.println(" ......................我佛慈悲......................");
        System.out.println("                       _oo0oo_                      ");
        System.out.println("                      o8888888o                     ");
        System.out.println("                      88\" . \"88                     ");
        System.out.println("                      (| -_- |)                     ");
        System.out.println("                      0\\  =  /0                     ");
        System.out.println("                    ___/‘---’\\___                   ");
        System.out.println("                  .' \\|       |/ '.                 ");
        System.out.println("                 / \\\\|||  :  |||// \\                ");
        System.out.println("                / _||||| -卍-|||||_ \\               ");
        System.out.println("               |   | \\\\\\  -  /// |   |              ");
        System.out.println("               | \\_|  ''\\---/''  |_/ |              ");
        System.out.println("               \\  .-\\__  '-'  ___/-. /              ");
        System.out.println("             ___'. .'  /--.--\\  '. .'___            ");
        System.out.println("          .\"\" ‘<  ‘.___\\_<|>_/___.’ >’ \"\".          ");
        System.out.println("         | | :  ‘- \\‘.;‘\\ _ /’;.’/ - ’ : | |        ");
        System.out.println("         \\  \\ ‘_.   \\_ __\\ /__ _/   .-’ /  /        ");
        System.out.println("     =====‘-.____‘.___ \\_____/___.-’___.-’=====     ");
        System.out.println("                       ‘=---=’                      ");
        System.out.println("                                                    ");
        System.out.println("....................佛祖开光 ,永无BUG...................");
    }

    static void test() {
        int year = 10;
        LocalDate now = LocalDate.now();
        LocalDate before = now.minusYears(year);
        now = now.minusYears(-1);
        while (!now.isEqual(before)) {

            for (int i = 1; i <= 12; i ++) {

                LocalDate first = before.withMonth(i);
                if (now.getYear() - 1 == before.getYear() && first.getMonthValue() > before.getMonthValue()) {
                    continue;
                }

                System.out.println(first.with(TemporalAdjusters.lastDayOfMonth()) + " - " + first.with(TemporalAdjusters.firstDayOfMonth()));
            }

            year --;

            before = now.minusYears(year);
        }

    }


}
