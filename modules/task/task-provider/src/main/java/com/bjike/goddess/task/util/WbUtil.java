package com.bjike.goddess.task.util;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.task.entity.Field;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;

import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-09-16 15:40]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class WbUtil {
    public static void validate(XSSFRow row, List<Field> fields) throws SerException {
        int fieldCount = row.getLastCellNum();
        for (int i = 0; i < fieldCount; i++) {
            String excelField = row.getCell(i).getStringCellValue();
            String field = fields.get(i).getName();
            if (!field.equals(excelField)) {
                throw new SerException("请检查表头,第" + (i + 1) + "列列名应为系统设置的[" + field + "]");
            }
        }
    }

    public static void initHeader(XSSFWorkbook wb, XSSFSheet sheet, List<Field> fields) {
        XSSFRow headerRow = sheet.createRow(0);
        headerRow.setHeight((short) 400);
        int cellIndex = 0;
        XSSFCellStyle headerStyle = ExcelUtil.getStyle(wb, IndexedColors.PALE_BLUE.getIndex());
        headerStyle.setAlignment(HorizontalAlignment.CENTER); //水平布局：居中
        headerStyle.setWrapText(true);
        for (Field field : fields) {
            XSSFCell cell = headerRow.createCell(cellIndex++);
            cell.setCellValue(field.getName());
            cell.setCellStyle(headerStyle);
        }
    }

    public static void initRows(XSSFWorkbook wb, XSSFSheet sheet, List<Field> fields, List<CglibBean> beans) {
        int rowCount = 1;
        XSSFCellStyle contentStyle = ExcelUtil.getStyle(wb, IndexedColors.WHITE.getIndex());
        for (CglibBean bean : beans) {
            XSSFRow row = sheet.createRow(rowCount++);
            row.setHeight((short) 300);
            int cellIndex = 0;
            for (Field field : fields) {
                XSSFCell cell = row.createCell(cellIndex);
                String val = String.valueOf(bean.getValue(field.getName()));
                cell.setCellStyle(contentStyle);
                cell.setCellValue(val);
                int val_length = val.getBytes().length; //获取数据值长度
                int name_length = field.getName().getBytes().length;//获取表头长度
                int columnWidth = val_length > name_length ? val_length : name_length;
                columnWidth = columnWidth > 30 ? 30 : columnWidth;
                sheet.setColumnWidth(cellIndex, columnWidth * 300);//设置自动宽度
                cellIndex++;
            }
        }
    }
}
