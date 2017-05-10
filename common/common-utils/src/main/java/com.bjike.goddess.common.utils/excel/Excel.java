package com.bjike.goddess.common.utils.excel;

import org.apache.poi.ss.usermodel.IndexedColors;

/**
 * @Author: [liguiqin]
 * @Date: [2017-05-09 16:21]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class Excel {
    /**
     * 标题
     */
    private String title;
    /**
     * title背景色
     */
    private short titleBGColor = IndexedColors.WHITE.getIndex();

    /**
     * 开始行
     */
    private Integer contentStartRow = 2;
    /**
     * 指定读取某个sheet
     */
    private Integer sheetIndex = 0;

    /**
     * 工作蒲命名
     */
    private String sheetName = "无";

    /**
     * 内容
     */
    private short contentBGColor = IndexedColors.WHITE.getIndex();

    /**
     * title行高
     */
    private short titleHeight = 400;
    /**
     * content行高
     */
    private short contentHeight = 400;

    /**
     * 自动宽度
     */
    private boolean autoColumnWidth = true;
    /**
     * 合并某行单元格居中
     */
    private Integer mergedRegionIndex;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public short getTitleBGColor() {
        return titleBGColor;
    }

    public void setTitleBGColor(short titleBGColor) {
        this.titleBGColor = titleBGColor;
    }

    public Integer getContentStartRow() {
        return contentStartRow;
    }

    public void setContentStartRow(Integer contentStartRow) {
        this.contentStartRow = contentStartRow;
    }

    public Integer getSheetIndex() {
        return sheetIndex;
    }

    public void setSheetIndex(Integer sheetIndex) {
        this.sheetIndex = sheetIndex;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public short getContentBGColor() {
        return contentBGColor;
    }

    public void setContentBGColor(short contentBGColor) {
        this.contentBGColor = contentBGColor;
    }

    public short getTitleHeight() {
        return titleHeight;
    }

    public void setTitleHeight(short titleHeight) {
        this.titleHeight = titleHeight;
    }

    public short getContentHeight() {
        return contentHeight;
    }

    public void setContentHeight(short contentHeight) {
        this.contentHeight = contentHeight;
    }

    public boolean isAutoColumnWidth() {
        return autoColumnWidth;
    }

    public void setAutoColumnWidth(boolean autoColumnWidth) {
        this.autoColumnWidth = autoColumnWidth;
    }

    public Integer getMergedRegionIndex() {
        return mergedRegionIndex;
    }

    public void setMergedRegionIndex(Integer mergedRegionIndex) {
        this.mergedRegionIndex = mergedRegionIndex;
    }
}
