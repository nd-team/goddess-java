import com.alibaba.fastjson.JSON;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-05-12 15:25]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ExcelTest {
    public static void main(String[] args) throws Exception {
        try {
            /**
             * 对象数据
             */
            List<UserExcel> users = new ArrayList<>();
            UserExcel u = new UserExcel();
            u.setCreateTime(LocalDateTime.now());
            u.setDate(LocalDate.now());
            u.setMoney(55.5);
            u.setTime(LocalTime.now());
            u.setAge(68);
            u.setSexType(SexType.WOMAN);
            u.setName("黎黎黎");
            u.setPhone("15622226780");
            users.add(u);
            users.add(u);
            users.add(u);
            /**
             * 对象列表转excel bytes
             */
            Excel ex = new Excel(1, 2);
            ex.setTitle("导出用户数据");
//               ex.setExcludes(new String[]{"name","phone"}); //过滤字段
            byte[] bytes = ExcelUtil.clazzToExcel(users, ex);
            File out = new File("/home/lgq/out.xlsx");
            FileOutputStream fos = new FileOutputStream(out);
            fos.write(bytes);
            //读取excel数据并反射到实体类
            List<UserExcel> ins = ExcelUtil.excelToClazz(new File("/home/lgq/out.xlsx"),UserExcel.class,ex);
            System.out.println(JSON.toJSONString(ins));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
