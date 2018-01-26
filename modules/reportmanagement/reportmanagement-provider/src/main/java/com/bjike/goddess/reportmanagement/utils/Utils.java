package com.bjike.goddess.reportmanagement.utils;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.ClazzUtils;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelHeader;
import org.apache.poi.xssf.usermodel.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;

/**
 * 工具类
 *
 * @Author: [chenjunhao]
 * @Date: [2017-06-19 11:56]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class Utils {
    public static LocalDate tranTime(String time) throws SerException {
        LocalDate a = null;
        try {
            a = DateUtil.parseDate(time);
        } catch (Exception e) {
            throw new SerException("日期格式错误");
        }
        return a;
    }
//
//    /**
//     * 实体类转换成excel流
//     *
//     * @param objects 转换类 任意类属性作为表头
//     * @param <T>
//     * @return
//     */
//    public static <T> byte[] clazzToExcel(List<T> objects, Excel excel) {
//        XSSFWorkbook wb = new XSSFWorkbook();
//        XSSFSheet sheet = wb.createSheet(excel.getSheetName());
//        if (null != objects && objects.size() > 0) {
//            List<Field> fields = ClazzUtils.getFields(objects.get(0).getClass()); //获得列表对象属性
//            List<ExcelHeader> excelHeaders = getExcelHeaders(fields, excel.getExcludes()); //获得表头
//            int rowSize = objects.size(); //数据行数
//            int cellSize = excelHeaders.size();//数据列数
//            int rowIndex = initTitleAndHeader(wb, sheet, excel, excelHeaders, cellSize);//初始化标题行及表头
//            XSSFCellStyle contentStyle = getStyle(wb, excel.getContentBGColor());
//            for (int i = 0; i < rowSize; i++) {
//                Object obj = objects.get(i);
//                XSSFRow row = sheet.createRow(rowIndex++);
//                row.setHeight(excel.getContentHeight());
//                for (int j = 0; j < cellSize; j++) {
//                    XSSFCell cell = row.createCell(j);
//                    try {
//                        Object val = null;
//                        Field field = null;
//                        for (Field fd : fields) {
//                            if (fd.getAnnotation(ExcelHeader.class).name().equals(excelHeaders.get(j).name())) {
//                                fd.setAccessible(true);
//                                val = fd.get(obj);
//                                field = fd;
//                                break;
//                            }
//                        }
//                        if (null != val) {
//                            String cellValue = val.toString();
//                            setCellValue(cell, field, val);
//                            if (excel.isAutoColumnWidth()) {
//                                int val_length = cellValue.getBytes().length; //获取数据值长度
//                                int name_length = excelHeaders.get(j).name().getBytes().length;//获取表头长度
//                                int columnWidth = val_length > name_length ? val_length : name_length;
//                                columnWidth = columnWidth > 30 ? 30 : columnWidth;
//                                sheet.setColumnWidth(j, columnWidth * 275);//设置自动宽度
//                            }
//                        }
//                        cell.setCellStyle(contentStyle);
//                    } catch (Exception e) {
//                        throw new RuntimeException(e.getMessage());
//                    }
//                }
//            }
//        } else {
//            LOGGER.info("clazzToExcel: objects is null !");
//        }
//        ByteArrayOutputStream os = new ByteArrayOutputStream();
//        try {
//            wb.write(os);
//
//        } catch (IOException e) {
//            throw new RuntimeException(e.getMessage());
//        }
//
//        return os.toByteArray();
//    }
}
