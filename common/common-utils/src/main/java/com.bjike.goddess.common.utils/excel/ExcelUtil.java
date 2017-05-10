package com.bjike.goddess.common.utils.excel;

import com.bjike.goddess.common.utils.bean.ClazzUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-05-09 16:01]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ExcelUtil {

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
                XSSFRow row = sheet.getRow(i);
                int cellTotal = row.getLastCellNum();//总列数
                if (cellTotal > 0) {
                    Object obj = clazz.newInstance();
                    for (int j = 0; j < cellTotal; j++) {
                        Object val = (row.getCell(j));
                        titles.stream().forEach(et -> {
                            if (et.notNull() && null == val) {
                                throw new RuntimeException("列:" + et.name() + "不能为空!");
                            } else {
                                setFieldValue(clazz,et.name(), val, fields);
                            }
                        });
                    }
                    objects.add((T) obj);
                }
            }
            return objects;
        } catch (Exception e) {
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
     * 设置对象熟悉值
     */
    private static void setFieldValue(Object obj,String name,Object val,List<Field> fields)  {
        try {
            for(Field field: fields){
                if(field.getName().equals(name)){
                    field.setAccessible(true);// 设置属性可访问
                    field.set(obj,val);
                    break;
                }
            }
        }catch (IllegalAccessException e){
            throw new RuntimeException(e.getMessage());
        }

    }



}
