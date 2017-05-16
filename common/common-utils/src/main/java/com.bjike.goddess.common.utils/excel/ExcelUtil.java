package com.bjike.goddess.common.utils.excel;

import com.bjike.goddess.common.utils.bean.ClazzUtils;
import com.bjike.goddess.common.utils.bean.DataTypeUtils;
import com.bjike.goddess.common.utils.date.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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
     * excel文件转换成实体类
     *
     * @param file  文件
     * @param clazz 转换类 该类必须要有@ExcelTitle注解作为表头
     * @param <T>
     * @return
     */
    public static <T> List<T> excelToClazz(File file, Class clazz, Excel excel) {
        try {
            InputStream is = new FileInputStream(file);
            return excelToClazz(is, clazz, excel);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    /**
     * excel文件流转换成实体类
     *
     * @param is    文件流
     * @param clazz 转换类 该类必须要有@ExcelTitle注解作为表头
     * @param <T>
     * @return
     */
    public static <T> List<T> excelToClazz(InputStream is, Class clazz, Excel excel) {
        XSSFWorkbook wb = null;
        try {
            List<Field> fields = ClazzUtils.getFields(clazz);// 类上所有字段信息
            List<ExcelHeader> headers = getExcelHeaders(fields, null);
            List<T> objects = new ArrayList<>();
            wb = new XSSFWorkbook(is); // 创建一个工作execl文档
            XSSFSheet sheet = wb.getSheetAt(0);
            validateHeader(headers, sheet.getRow(excel.getHeaderStartRow()));
            int rowSize = sheet.getLastRowNum(); //总行数
            for (int i = 0; i < rowSize; i++) {
                int rowIndex = excel.getContentStartRow() + i;
                XSSFRow row = sheet.getRow(rowIndex);
                if (null != row) {
                    int cellSize = row.getLastCellNum();//总列数
                    if (cellSize > 0) {
                        Object obj = clazz.newInstance();
                        for (int j = 0; j < cellSize; j++) {
                            ExcelHeader eh = headers.get(j);
                            String cellVal = getCellValue(row.getCell(j), eh);
                            Object val = convertValue(cellVal, eh, fields);
                            if (eh.notNull() && null == val) {
                                throw new RuntimeException(rowIndex + " 行,列[" + eh.name() + "]不能为空!");
                            } else if (null != val) {
                                setFieldValue(obj, eh.name(), val, fields);
                            }
                        }
                        objects.add((T) obj);
                    }
                }

            }
            return objects;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    /**
     * 实体类转换成excel流
     *
     * @param objects 转换类 任意类属性作为表头
     * @param <T>
     * @return
     */
    public static <T> byte[] clazzToExcel(List<T> objects, Excel excel) {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet(excel.getSheetName());
        if (null != objects && objects.size() > 0) {
            List<Field> fields = ClazzUtils.getFields(objects.get(0).getClass()); //获得列表对象属性
            List<ExcelHeader> excelHeaders = getExcelHeaders(fields, excel.getExcludes()); //获得表头
            int rowSize = objects.size(); //数据行数
            int cellSize = excelHeaders.size();//数据列数
            int rowIndex = initTitleAndHeader(wb, sheet, excel, excelHeaders, cellSize);//初始化标题行及表头
            XSSFCellStyle contentStyle = getStyle(wb, excel.getContentBGColor());
            for (int i = 0; i < rowSize; i++) {
                Object obj = objects.get(i);
                XSSFRow row = sheet.createRow(rowIndex++);
                row.setHeight(excel.getContentHeight());
                for (int j = 0; j < cellSize; j++) {
                    XSSFCell cell = row.createCell(j);
                    try {
                        Object val = null;
                        for (Field field : fields) {
                            if (field.getAnnotation(ExcelHeader.class).name().equals(excelHeaders.get(j).name())) {
                                field.setAccessible(true);
                                val = field.get(obj);
                                if (null != val && field.getType().getTypeName().equals(LocalDateTime.class.getTypeName())) { //处理时间
                                    val = DateUtil.dateToString((LocalDateTime) val);
                                } else if (null != val && field.getType().isEnum()) {
                                    val = fieldToEnum(field, val);
                                }
                                break;
                            }
                        }
                        if (null != val) {
                            String cellValue = val.toString();
                            cell.setCellValue(val.toString());
                            if (excel.isAutoColumnWidth()) {
                                int val_length = cellValue.getBytes().length; //获取数据值长度
                                int name_length = excelHeaders.get(j).name().getBytes().length;//获取表头长度
                                int columnWidth = val_length > name_length ? val_length : name_length;
                                columnWidth = columnWidth > 30 ? 30 : columnWidth;
                                sheet.setColumnWidth(j, columnWidth * 275);//设置自动宽度
                            }
                        }
                        cell.setCellStyle(contentStyle);
                    } catch (Exception e) {
                        throw new RuntimeException(e.getMessage());
                    }
                }
            }
        } else {
            LOGGER.info("clazzToExcel: objects is null !");
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            wb.write(os);

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        return os.toByteArray();
    }

    /**
     * 获取标注excel的信息字段
     *
     * @param fields
     * @return
     */
    private static List<ExcelHeader> getExcelHeaders(List<Field> fields, String[] excludes) {
        List<ExcelHeader> excelHeaders = new ArrayList<>(0);// 获取类上的所有注解信息
        for (Field field : fields) {
            ExcelHeader eh = field.getAnnotation(ExcelHeader.class);
            if (null != eh) {
                boolean exist = false;
                if (null != excludes) { //过滤字段
                    for (String ex : excludes) {
                        if (ex.equals(field.getName())) {
                            exist = true;
                            break;
                        }
                    }
                }
                if (!exist) {
                    excelHeaders.add(eh);
                }
            }
        }
        return excelHeaders;
    }

    /**
     * 设置对象属性值
     */
    private static void setFieldValue(Object obj, String name, Object val, List<Field> fields) {
        try {
            for (Field field : fields) {
                if (field.getAnnotation(ExcelHeader.class).name().equals(name)) {
                    field.setAccessible(true);// 设置属性可访问
                    if (field.getType().isEnum()) {
                        enumToField(field, obj, val);
                    } else {
                        field.set(obj, val);
                    }

                    break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }


    /**
     * excel数据类型转换成java对应类型
     *
     * @param val
     * @param eh
     * @param fields
     * @return
     */
    private static Object convertValue(String val, ExcelHeader eh, List<Field> fields) {
        Object value = null;
        if (StringUtils.isBlank(val)) {
            return value;
        }
        try {
            for (Field f : fields) {
                if (f.getAnnotation(ExcelHeader.class).name().equals(eh.name())) {
                    return DataTypeUtils.convertDataType(val, f.getType().getSimpleName());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("请检查列【" + eh.name() + "】类型");
        }
        return value;
    }

    /**
     * 获取excel数据
     *
     * @param cell
     * @return
     */
    private static String getCellValue(Cell cell, ExcelHeader eh) {
        String val = null;
        try {
            if (null != cell) {
                switch (cell.getCellTypeEnum()) {
                    case NUMERIC: //数值型
                        if (!HSSFDateUtil.isCellDateFormatted(cell)) {
                            BigDecimal big = new BigDecimal(cell.getNumericCellValue());
                            val = big.toString();
                            if (StringUtils.isNotBlank(val)) {//解决1234.0  去掉后面的.0
                                String[] item = val.split("[.]");
                                if (1 < item.length && "0".equals(item[1])) {
                                    val = item[0];
                                }
                            }
                        } else {
                            Date date = cell.getDateCellValue();
                            return String.valueOf(date.getTime());
                        }
                        break;

                    case STRING://字符串类型
                        val = String.valueOf(cell.getStringCellValue());
                        break;

                    case FORMULA: // 公式类型
                        val = String.valueOf(cell.getNumericCellValue()); //读公式计算值
                        if (val.equals("NaN")) {// 如果获取的数据值为非法值,则转换为获取字符串
                            val = cell.getStringCellValue();
                        }
                        break;

                    case BOOLEAN:// 布尔类型
                        val = String.valueOf(cell.getBooleanCellValue());
                        break;
                    default:
                        val = cell.getStringCellValue().toString();
                }
                return val;
            } else {
                return val;
            }
        } catch (Exception e) {
            throw new RuntimeException("请检查列【" + eh.name() + "】类型");
        }

    }

    /**
     * 验证表头是否正确
     *
     * @param excelHeaders 表头
     * @param row
     */
    private static void validateHeader(List<ExcelHeader> excelHeaders, XSSFRow row) {
        int cellSize = excelHeaders.size();
        int maxSize = row.getLastCellNum();
        for (int i = 0; i < cellSize; i++) {
            if (i <= maxSize) {
                XSSFCell cell = row.getCell(i);
                String title = null;
                try {
                    title = cell.getStringCellValue();
                } catch (Exception e) {
                    throw new RuntimeException("excel表头数据类型只能为文本类型!");
                }
                String sysTitle = excelHeaders.get(i).name();
                if (!title.equals(sysTitle)) {
                    throw new RuntimeException("[" + title + "]列与系统设置的列[" + sysTitle + "]不匹配");
                }
            } else {
                throw new RuntimeException("缺少该列[" + excelHeaders.get(i).name() + "]");
            }
        }
    }


    /**
     * 初始化标题行及表头
     *
     * @param wb           excel对象
     * @param sheet        哪个sheet
     * @param excel        excel实体信息
     * @param excelHeaders excel头
     * @param cellSize     列长度
     * @return
     */
    private static int initTitleAndHeader(XSSFWorkbook wb, XSSFSheet sheet, Excel excel, List<ExcelHeader> excelHeaders, int cellSize) {
        int rowIndex = 0;
        XSSFCellStyle titleStyle = wb.createCellStyle();  // 标题的样式
        titleStyle.setAlignment(HorizontalAlignment.CENTER); //水平布局：居中
        titleStyle.setWrapText(true);

        XSSFCellStyle headerStyle = getStyle(wb, excel.getHeaderBGColor());
        headerStyle.setAlignment(HorizontalAlignment.CENTER); //水平布局：居中
        headerStyle.setWrapText(true);

        if (StringUtils.isNotBlank(excel.getTitle())) { //设置标题
            XSSFRow titleRow = sheet.createRow(rowIndex++);
            titleRow.setHeight(excel.getTitleHeight());
            XSSFCell cell = titleRow.createCell(0);
            cell.setCellValue(excel.getTitle());
            cell.setCellStyle(titleStyle);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, cellSize - 1));
        }
        XSSFRow headerRow = sheet.createRow(rowIndex++);
        headerRow.setHeight(excel.getHeaderHeight());
        for (int i = 0; i < excelHeaders.size(); i++) {//创建表头
            XSSFCell cell = headerRow.createCell(i);
            cell.setCellStyle(headerStyle);
            cell.setCellValue(excelHeaders.get(i).name());

        }
        return rowIndex;
    }

    /**
     * 获取样式
     *
     * @param wb
     * @param color
     * @return
     */
    private static XSSFCellStyle getStyle(XSSFWorkbook wb, short color) {
        // 内容的样式
        XSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER); //水平布局：居中
        if (color != IndexedColors.WHITE.getIndex()) {
            style.setFillForegroundColor(color);
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND); //设置单元格颜色
            style.setBorderLeft(BorderStyle.THIN); // 单元格边框粗细
            style.setBorderRight(BorderStyle.THIN);// 单元格边框粗细
            style.setBorderTop(BorderStyle.THIN);// 单元格边框假粗细
            style.setBorderBottom(BorderStyle.THIN);// 单元格边框粗细
        }
        return style;
    }

    /**
     * 属性转换枚举
     *
     * @param field
     * @param val
     * @return
     */

    private static String fieldToEnum(Field field, Object val) {
        Field[] enumFields = field.getType().getFields();
        String value = val.toString();
        for (Field f : enumFields) {
            if (value.equals(f.getName())) {
                try {
                    return f.getAnnotation(ExcelValue.class).name();
                }catch (Exception e){
                    throw new RuntimeException("枚举未添加Excel注解");
                }

            }
        }
        return value;
    }


    /**
     * 枚举转换属性
     *
     * @param field
     * @param obj
     * @param val
     */
    private static void enumToField(Field field, Object obj, Object val) {
        String value = val.toString();
        try {
            Field[] enumFields = field.getType().getFields();
            for (Field f : enumFields) {
                if (value.equals(f.getAnnotation(ExcelValue.class).name())) {
                    field.set(obj, field.getType().getField(f.getName()).get(f.getName()));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }


}
