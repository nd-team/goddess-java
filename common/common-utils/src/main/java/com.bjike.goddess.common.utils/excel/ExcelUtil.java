package com.bjike.goddess.common.utils.excel;

import com.bjike.goddess.common.utils.bean.ClazzUtils;
import com.bjike.goddess.common.utils.bean.DataTypeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * @Author: [liguiqin]
 * @Date: [2017-05-09 16:01]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ExcelUtil {
    private static final Logger LOGGER = Logger.getLogger(ExcelUtil.class.getName());

    /**
     * excel文件流转换成实体类
     *
     * @param is    文件流
     * @param clazz 转换类
     * @param <T>
     * @return
     */
    public static <T> List<T> transToClazz(InputStream is, Class clazz, Excel excel) {
        XSSFWorkbook wb = null;
        try {
            List<Field> fields = ClazzUtils.getFields(clazz);// 类上所有字段信息
            List<ExcelTitle> titles = getExcelTitles(clazz, fields);
            List<T> objects = new ArrayList<>();
            wb = new XSSFWorkbook(is); // 创建一个工作execl文档
            XSSFSheet sheet = wb.getSheetAt(0);
            int rowTotal = sheet.getLastRowNum(); //总行数
            for (int i = 0; i < rowTotal; i++) {
                XSSFRow row = sheet.getRow(excel.getContentStartRow() + i);
                if (null != row) {
                    int cellTotal = row.getLastCellNum();//总列数
                    if (cellTotal > 0) {
                        Object obj = clazz.newInstance();
                        List<Field> _fields = ClazzUtils.getFields(obj.getClass());
                        for (int j = 0; j < cellTotal; j++) {
                            ExcelTitle et = titles.get(j);
                            Object val = convertValue(getCellValue(row.getCell(j)), et, _fields);
                            if (et.notNull() && null == val) {
                                throw new RuntimeException("列:" + et.name() + "不能为空!");
                            } else if (null != val) {
                                setFieldValue(obj, et.name(), val, _fields);
                            }
                        }
                        objects.add((T) obj);
                    }
                }

            }
            return objects;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

    }

    /**
     * excel文件转换成实体类
     *
     * @param file  文件
     * @param clazz 转换类
     * @param <T>
     * @return
     */
    public static <T> List<T> transToClazz(File file, Class clazz) {
        return null;
    }

    /**
     * 实体类转换成excel流
     *
     * @param clazz 转换类
     * @param <T>
     * @return
     */
    public static <T> InputStream clazzToExcel(List<T> clazz) {
        return null;
    }

    /**
     * 实体类转换成excel字节流
     *
     * @param clazz 转换类
     * @param <T>
     * @return
     */
    public static <T> byte[] clazzToBytesExcel(List<T> clazz) {
        return null;
    }

    /**
     * 获取标注excel的信息子字段
     *
     * @param clazz
     * @param fields
     * @return
     */
    private static List<ExcelTitle> getExcelTitles(Class clazz, List<Field> fields) {
        List<ExcelTitle> excelTitles = new ArrayList<>(0);// 获取类上的所有注解信息
        for (Field field : fields) {
            ExcelTitle et = field.getAnnotation(ExcelTitle.class);
            if (null != et) {
                excelTitles.add(et);
            }
        }
        return excelTitles;
    }

    /**
     * 设置对象属性值
     */
    private static void setFieldValue(Object obj, String name, Object val, List<Field> fields) {
        try {
            for (Field field : fields) {
                if (field.getAnnotation(ExcelTitle.class).name().equals(name)) {
                    field.setAccessible(true);// 设置属性可访问
                    field.set(obj, val);
                    break;
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    public static void main(String[] args) throws Exception {
        try {
            File file = new File("/home/lgq/user.xlsx");
            InputStream is = new FileInputStream(file);
            Excel excel = new Excel();
            List<UserExcel> users = transToClazz(is, UserExcel.class, excel);
            System.out.println(users);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * excel数据类型转换成java对应类型
     *
     * @param val
     * @param et
     * @param fields
     * @return
     */
    private static Object convertValue(String val, ExcelTitle et, List<Field> fields) {
        Object value = null;
        if (StringUtils.isBlank(val)) {
            return value;
        }
        for (Field f : fields) {
            if (f.getAnnotation(ExcelTitle.class).name().equals(et.name())) {
                return DataTypeUtils.convertDataType(val, f.getType().getSimpleName());
            }
        }
        return value;
    }

    /**
     * 获取excel数据
     *
     * @param cell
     * @return
     */
    private static String getCellValue(Cell cell) {
        String val = null;

        if(null!=cell){
            switch (cell.getCellTypeEnum()) {
                //数值型
                case NUMERIC:
                    val = String.valueOf(cell.getNumericCellValue());
                    if (!HSSFDateUtil.isCellDateFormatted(cell)) {
                        BigDecimal big = new BigDecimal(cell.getNumericCellValue());
                        val = big.toString();
                        //解决1234.0  去掉后面的.0
                        if (StringUtils.isNotBlank(val)) {
                            String[] item = val.split("[.]");
                            if (1 < item.length && "0".equals(item[1])) {
                                val = item[0];
                            }
                        }
                    }else {
                        Date date = cell.getDateCellValue();
                        return String.valueOf(date.getTime());
                    }
                    break;
                //字符串类型
                case STRING:
                    val = String.valueOf(cell.getStringCellValue());
                    break;
                // 公式类型
                case FORMULA:
                    //读公式计算值
                    val = String.valueOf(cell.getNumericCellValue());
                    if (val.equals("NaN")) {// 如果获取的数据值为非法值,则转换为获取字符串
                        val = cell.getStringCellValue();
                    }
                    break;
                // 布尔类型
                case BOOLEAN:
                    val = String.valueOf(cell.getBooleanCellValue());
                    break;
                default:
                    val = cell.getStringCellValue().toString();
            }
            return val;
        }else {
            return val;
        }
    }

}
