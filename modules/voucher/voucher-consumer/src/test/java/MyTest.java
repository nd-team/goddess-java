import com.bjike.goddess.common.api.dto.BaseDTO;
import com.bjike.goddess.user.to.DepartmentTO;
import com.bjike.goddess.voucher.entity.VoucherGenerate;
import jdk.management.resource.internal.inst.SocketOutputStreamRMHooks;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-01-12 09:59]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class MyTest {
    static public void main(String[] a) {
        System.out.println(LocalDate.now().getMonthValue());

        Boolean bool = false;
        Integer i = 1;
        t(bool);
        t1(i);
        System.out.println(bool);
        System.out.println(i);

        DepartmentTO departmentTO = new DepartmentTO();

        System.out.println(departmentTO.getName());

        t1(departmentTO);
        System.out.println(departmentTO.getName());

        String id = "";
        String[] str = {"a"};
        for (String s : str) {
            id += "," + s;
        }
        System.out.println("id:" + id);

        BigDecimal bigDecimal = new BigDecimal(0.0);
        bigDecimal = bigDecimal.add(new BigDecimal(2));
        System.out.println(bigDecimal);

    }

    static void t (Boolean bool) {
        bool = true;
    }

    static void t1 (Integer i) {
        i ++;
    }

    static void t1 (DepartmentTO i) {
        i.setName("dsdsdd");
    }
}
