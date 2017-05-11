package com.bjike.goddess.common.utils.excel;

import com.bjike.goddess.common.utils.bean.ClazzUtils;
import com.bjike.goddess.common.utils.bean.DataTypeUtils;
import com.bjike.goddess.common.utils.date.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
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
            int rowTotal = sheet.getLastRowNum(); //总行数
            for (int i = 0; i < rowTotal; i++) {
                XSSFRow row = sheet.getRow(excel.getContentStartRow() + i);
                if (null != row) {
                    int cellTotal = row.getLastCellNum();//总列数
                    if (cellTotal > 0) {
                        Object obj = clazz.newInstance();
                        for (int j = 0; j < cellTotal; j++) {
                            ExcelHeader eh = headers.get(j);
                            Object val = convertValue(getCellValue(row.getCell(j)), eh, fields);
                            if (eh.notNull() && null == val) {
                                throw new RuntimeException("列:" + eh.name() + "不能为空!");
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
            e.printStackTrace();
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
    public static <T> byte[] clazzToExcel(List<T> objects, Excel excel) throws Exception {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet(excel.getSheetName());

        XSSFCellStyle contentStyle = wb.createCellStyle();  // 内容的样式
        contentStyle.setAlignment(HorizontalAlignment.CENTER); //水平布局：居中

        if (null != objects && objects.size() > 0) {
            List<Field> fields = ClazzUtils.getFields(objects.get(0).getClass()); //获得列表对象属性
            List<ExcelHeader> excelHeaders = getExcelHeaders(fields, excel.getExcludes()); //获得表头
            int rowSize = objects.size(); //数据行数
            int cellSize = excelHeaders.size();//数据列数
            int rowIndex = initTitleAndHeader(wb, sheet, excel, excelHeaders, cellSize);//初始化标题行及表头
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
                                if (field.getType().getTypeName().equals(LocalDateTime.class.getTypeName())) { //处理时间
                                    val = DateUtil.dateToString((LocalDateTime) val);
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
                                columnWidth = columnWidth > 30 ? columnWidth = 30 : columnWidth;
                                sheet.setColumnWidth(j, columnWidth * 275);
                            }
                            cell.setCellStyle(contentStyle);
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e.getMessage());
                    }
                }
            }
        } else {
            LOGGER.info("clazzToExcel: objects is null !");
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        wb.write(os);
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
                    field.set(obj, val);
                    break;
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.getMessage());
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
    private static Object convertValue(String val, ExcelHeader et, List<Field> fields) {
        Object value = null;
        if (StringUtils.isBlank(val)) {
            return value;
        }
        for (Field f : fields) {
            if (f.getAnnotation(ExcelHeader.class).name().equals(et.name())) {
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
    }

    /**
     * 验证表头是否正确
     *
     * @param excelHeaders 表头
     * @param row
     */
    private static void validateHeader(List<ExcelHeader> excelHeaders, XSSFRow row) {
        int cellSize = excelHeaders.size();
        for (int i = 0; i < cellSize; i++) {
            XSSFCell cell = row.getCell(i);
            try {
                String title = cell.getStringCellValue();
                String sysTitle = excelHeaders.get(i).name();
                if (!title.equals(sysTitle)) {
                    throw new RuntimeException(title + "与系统设置的列[" + sysTitle + "]不匹配");
                }
            } catch (Exception e) {
                throw new RuntimeException("Title数据类型只能为文本类型!");
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

        XSSFCellStyle headerStyle = wb.createCellStyle();  // 表头样式
        headerStyle.setBorderLeft(BorderStyle.THIN); // 单元格边框粗细
        headerStyle.setBorderRight(BorderStyle.THIN);// 单元格边框粗细
        headerStyle.setBorderTop(BorderStyle.THIN);// 单元格边框假粗细
        headerStyle.setBorderBottom(BorderStyle.THIN);// 单元格边框粗细
        headerStyle.setFillForegroundColor(excel.getHeaderBGColor()); //DARK_YELLOW
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND); //设置单元格颜色solid_foreground
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


    public static void main(String[] args) throws Exception {
        try {
            /**
             * excel 转对象
             */
            File file = new File("/home/lgq/user.xlsx");
            InputStream is = new FileInputStream(file);
            Excel excel = new Excel();
            List<UserExcel> users = excelToClazz(is, UserExcel.class, excel);

            /**
             * 对象列表转excel bytes
             */
            Excel ex= new Excel(1, 2);
            ex.setTitle("导出用户数据");
         //   ex.setExcludes(new String[]{"name","phone"}); //过滤字段
            byte[] bytes = clazzToExcel(users, ex);
            File out = new File("/home/lgq/out.xlsx");
            FileOutputStream fos = new FileOutputStream(out);
            fos.write(bytes);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
