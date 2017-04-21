package com.bjike.goddess.socialfee.utils;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.ArrayList;
import java.util.List;


/** 表格样式
 * @Author: [tanghaixiang]
 * @Date: [2017-04-20 17:12]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DownloadExeclUtil {
	
	/**
	 * 设置Execl标题样式
	 * @param wb Execl工作簿
	 * @return 返回对应的Execl样式
	 */
	public static XSSFCellStyle titleStyle(XSSFWorkbook wb, short colorIndex) {
		XSSFCellStyle style1 = wb.createCellStyle();  // 创建execl中的样式
		style1.setBorderLeft(CellStyle.BORDER_THIN); // 单元格边框粗细
		style1.setBorderRight(CellStyle.BORDER_THIN);// 单元格边框粗细
		style1.setBorderTop(CellStyle.BORDER_THIN);// 单元格边框假粗细
		style1.setBorderBottom(CellStyle.BORDER_THIN);// 单元格边框粗细
		style1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
		style1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
		style1.setFillForegroundColor(colorIndex); //DARK_YELLOW
		style1.setFillPattern(CellStyle.SOLID_FOREGROUND); //设置单元格颜色solid_foreground
		return style1;
	}
	
	public static XSSFCellStyle titleStyle(XSSFCellStyle style1, short colorIndex) {
		style1.setBorderLeft(CellStyle.BORDER_THIN); // 单元格边框粗细
		style1.setBorderRight(CellStyle.BORDER_THIN);// 单元格边框粗细
		style1.setBorderTop(CellStyle.BORDER_THIN);// 单元格边框假粗细
		style1.setBorderBottom(CellStyle.BORDER_THIN);// 单元格边框粗细
		style1.setFillForegroundColor(colorIndex); //DARK_YELLOW
		style1.setFillPattern(CellStyle.SOLID_FOREGROUND); //设置单元格颜色solid_foreground
		return style1;
	}
	
	public static List<String> addTitleToList(String[] titles){
		List<String> list = new ArrayList<>();
		for (String title : titles) {
			list.add(title);
		}
		
		return list;
		
	}
}
