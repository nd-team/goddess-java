package com.bjike.goddess.intromanage.util;

import com.bjike.goddess.common.utils.bean.ClazzUtils;
import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.intromanage.excel.CommunicationPathExprot;
import com.bjike.goddess.intromanage.excel.FirmIntroExport;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.util.Base64;
import scala.util.parsing.combinator.testing.Str;

import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.lang.reflect.Field;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class WbUtil {
    private static int maxIndex = 0;

    /**
     * 创建excel
     *
     * @param firmIntros
     * @return
     */
    public static ByteArrayOutputStream createExcel(List<FirmIntroExport> firmIntros) {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("test");
        XSSFRow headerRow = sheet.createRow(0);
        int rowIndex = 1;
        int cellSize = 0;
        if (null != firmIntros && firmIntros.size() > 0) {
            List<Field> fields = ClazzUtils.getFields(firmIntros.get(0).getClass()); //获得列表对象属性
            List<ExcelHeader> excelHeaders = ExcelUtil.getExcelHeaders(fields, null); //获得表头
            cellSize = excelHeaders.size();
            maxIndex = cellSize;
            createHead(firmIntros.get(0), headerRow);
//            for (FirmIntroExport firm : firmIntros) {
//                int size = getListMaxSize(firm);
//                for (int i = 0; i < size; i++) {
//                    XSSFRow row = sheet.createRow(rowIndex++);
//                    for (int j = 0; j < cellSize; j++) {
//                        row.createCell(j).setCellValue(" null ");
//                    }
//                }
//            }
            firstList(firmIntros, sheet, cellSize);


        }
        ByteArrayOutputStream os = new ByteArrayOutputStream(1024);
        try {
            wb.write(os);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return os;
    }




    private static void createHead(FirmIntroExport firm, XSSFRow headerRow) {
        List<Field> fields = ClazzUtils.getFields(firm.getClass()); //获得列表对象属性
        List<ExcelHeader> excelHeaders = ExcelUtil.getExcelHeaders(fields, null); //获得表头
        int cellIndex = 0;
        for (ExcelHeader header : excelHeaders) {
            XSSFCell cell = headerRow.createCell(cellIndex++);
            cell.setCellValue(header.name());
        }
    }

    private static void createContent(Object obj, XSSFSheet sheet, int rowIndex, int startCell, int endCell) {
        List<Field> fields = ClazzUtils.getFields(obj.getClass()); //获得列表对象属性
        List<ExcelHeader> excelHeaders = ExcelUtil.getExcelHeaders(fields, null); //获得表头

        XSSFRow row = sheet.getRow(rowIndex);
        for (int j = startCell; j < endCell; j++) {
            XSSFCell cell = row.getCell(j);
            int k = 0;
            try {
                Object val = null;
                for (Field fd : fields) {
                    if (fd.getAnnotation(ExcelHeader.class).name().equals(excelHeaders.get(k++).name())) {
                        fd.setAccessible(true);
                        val = fd.get(obj);
                        break;
                    }
                }
                if (null != val) {
                    String cellValue = val.toString();
                    cell.setCellValue(cellValue);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }

        }
    }
//
//    public static int getListMaxSize(FirmIntroExport firm) {
//        int a = null != firm.getCommunicationPathExprotList() ? firm.getCommunicationPathExprotList().size() : 0;
//        int b = null != firm.getCustomerAndPartnerExportList() ? firm.getCustomerAndPartnerExportList().size() : 0;
//        int c = null != firm.getHonorAndQualityExportList() ? firm.getHonorAndQualityExportList().size() : 0;
//        int d = null != firm.getMainBusinessIntroExportList() ? firm.getMainBusinessIntroExportList().size() : 0;
//        int e = null != firm.getSuccessStoriesExportList() ? firm.getSuccessStoriesExportList().size() : 0;
//        List<Integer> integers = new ArrayList<>();
//        integers.add(a);
//        integers.add(b);
//        integers.add(c);
//        integers.add(d);
//        integers.add(e);
//        return integers.stream().max(Comparator.comparing(u -> u)).get();
//    }
//
    private static void firstList(List<FirmIntroExport> firmIntros, XSSFSheet sheet, int cellSize) {
        int rowIndex = 1;
        for (FirmIntroExport firm : firmIntros) { //主数据行
            createContent(firm, sheet, rowIndex++, 0, cellSize);

//            List<CommunicationPathExprot> cpts = firm.getCommunicationPathExprotList();
//            if (null != cpts) {
//                CommunicationPathExprot exprot = cpts.get(0);
//                List<Field> fields = ClazzUtils.getFields(exprot.getClass()); //获得列表对象属性
//                List<ExcelHeader> headers = ExcelUtil.getExcelHeaders(fields, null);
//                maxIndex = cellSize + headers.size();
//                int index = 0;
//                for (int i = cellSize; i < maxIndex; i++) {
//                    XSSFCell cell = sheet.getRow(0).createCell(i);
//                    cell.setCellValue(headers.get(index++).name());
//                }
//
//                for (CommunicationPathExprot pe : cpts) {
//                    createContent(pe, sheet, rowIndex++, cellSize, maxIndex);
//                }
//            }


        }
    }

}
