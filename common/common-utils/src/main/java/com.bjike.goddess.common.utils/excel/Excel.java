package com.bjike.goddess.common.utils.excel;

import com.bjike.goddess.common.utils.date.DateUtil;
import org.apache.poi.ss.usermodel.IndexedColors;

import java.time.LocalDate;

/**
 * @Author: [liguiqin]
 * @Date: [2017-05-09 16:21]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class Excel {
    public Excel() {

    }

    public Excel(Integer headerStartRow, Integer contentStartRow) {
        this.headerStartRow = headerStartRow;
        this.contentStartRow = contentStartRow;
    }

    /**
     * 标题
     */
    private String title;
    /**
     * 标题行高
     */
    private short titleHeight = 300;
    /**
     * 表头背景色
     */
    private short headerBGColor = IndexedColors.PALE_BLUE.getIndex();
    /**
     * 表头开始行
     */
    private Integer headerStartRow = 1;
    /**
     * 表头行高
     */
    private short headerHeight = 400;
    /**
     * 内容背景色
     */
    private short contentBGColor = IndexedColors.WHITE.getIndex();
    /**
     * 内容开始行
     */
    private Integer contentStartRow = 2;
    /**
     * content行高
     */
    private short contentHeight = 300;
    /**
     * 工作蒲命名
     */
    private String sheetName = DateUtil.dateToString(LocalDate.now());
    /**
     * 指定读取某个工作蒲
     */
    private Integer sheetIndex = 0;
    /**
     * 自动宽度
     */
    private boolean autoColumnWidth = true;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public short getTitleHeight() {
        return titleHeight;
    }

    public void setTitleHeight(short titleHeight) {
        this.titleHeight = titleHeight;
    }

    public short getHeaderBGColor() {
        return headerBGColor;
    }

    public void setHeaderBGColor(short headerBGColor) {
        this.headerBGColor = headerBGColor;
    }

    public Integer getHeaderStartRow() {
        return headerStartRow;
    }

    public void setHeaderStartRow(Integer headerStartRow) {
        this.headerStartRow = headerStartRow;
    }

    public short getHeaderHeight() {
        return headerHeight;
    }

    public void setHeaderHeight(short headerHeight) {
        this.headerHeight = headerHeight;
    }

    public short getContentBGColor() {
        return contentBGColor;
    }

    public void setContentBGColor(short contentBGColor) {
        this.contentBGColor = contentBGColor;
    }

    public Integer getContentStartRow() {
        return contentStartRow;
    }

    public void setContentStartRow(Integer contentStartRow) {
        this.contentStartRow = contentStartRow;
    }

    public short getContentHeight() {
        return contentHeight;
    }

    public void setContentHeight(short contentHeight) {
        this.contentHeight = contentHeight;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public Integer getSheetIndex() {
        return sheetIndex;
    }

    public void setSheetIndex(Integer sheetIndex) {
        this.sheetIndex = sheetIndex;
    }

    public boolean isAutoColumnWidth() {
        return autoColumnWidth;
    }

    public void setAutoColumnWidth(boolean autoColumnWidth) {
        this.autoColumnWidth = autoColumnWidth;
    }
}
