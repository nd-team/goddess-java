import java.math.BigDecimal;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-01-24 10:10]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class MyTest {

    public static void main(String[] args) {
        String q = "dsssd:";
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
        System.out.println(c);
    }
}
