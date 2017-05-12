import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
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
             * excel 转对象
             */
            File file = new File("/home/lgq/user.xlsx");
            InputStream is = new FileInputStream(file);
            Excel excel = new Excel();
            List<UserExcel> users = ExcelUtil.excelToClazz(is, UserExcel.class, excel);

            /**
             * 对象列表转excel bytes
             */
            Excel ex = new Excel(1, 2);
            ex.setTitle("导出用户数据");
            //   ex.setExcludes(new String[]{"name","phone"}); //过滤字段
            byte[] bytes = ExcelUtil.clazzToExcel(users, ex);
            File out = new File("/home/lgq/out.xlsx");
            FileOutputStream fos = new FileOutputStream(out);
            fos.write(bytes);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
