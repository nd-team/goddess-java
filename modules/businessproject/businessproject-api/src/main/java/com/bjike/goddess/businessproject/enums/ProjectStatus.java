package com.bjike.goddess.businessproject.enums;

import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * 项目状态
 *
 * @Author: [xiazhili]
 * @Date: [2017-10-21 19:56]
 * @Description: [项目状态]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum ProjectStatus {
    /**
     * 未进场
     */
    @ExcelValue(name = "未进场")
    NOTAPPROACH(0),
    /**
     * 进场
     */
    @ExcelValue(name = "进场")
    APPROACH(1),
    /**
     * 停工
     */
    @ExcelValue(name = "停工")
    SHUTDOWN(2),
    /**
     * 进行
     */
    @ExcelValue(name = "进行")
    MARCH(3),
    /**
     * 到货
     */
    @ExcelValue(name = "到货")
    GOODS(4),
    /**
     * 初验
     */
    @ExcelValue(name = "初验")
    INITIALTEST(5),
    /**
     * 终验
     */
    @ExcelValue(name = "终验")
    FINALTEST(6),
    /**
     * 完工
     */
    @ExcelValue(name = "完工")
    COMPLETE(7),;


    private int code;

    ProjectStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static ProjectStatus getEnumConvert(int code) {
        ProjectStatus projectStatus = ProjectStatus.NOTAPPROACH;
        if (code == ProjectStatus.NOTAPPROACH.getCode()) {
            projectStatus = ProjectStatus.NOTAPPROACH;
        }
        if (code == ProjectStatus.APPROACH.getCode()) {
            projectStatus = ProjectStatus.APPROACH;
        }
        if (code == ProjectStatus.SHUTDOWN.getCode()) {
            projectStatus = ProjectStatus.SHUTDOWN;
        }
        if (code == ProjectStatus.MARCH.getCode()) {
            projectStatus = ProjectStatus.MARCH;
        }
        if (code == ProjectStatus.GOODS.getCode()) {
            projectStatus = ProjectStatus.GOODS;
        }
        if (code == ProjectStatus.INITIALTEST.getCode()) {
            projectStatus = ProjectStatus.INITIALTEST;
        }
        if (code == ProjectStatus.FINALTEST.getCode()) {
            projectStatus = ProjectStatus.FINALTEST;
        }
        if (code == ProjectStatus.COMPLETE.getCode()) {
            projectStatus = ProjectStatus.COMPLETE;
        }
        return projectStatus;
    }

    public static String getStrConvert(int code) {
        String name = "";
        if (code == ProjectStatus.NOTAPPROACH.getCode()) {
            name = "未进场";
        }
        if (code == ProjectStatus.APPROACH.getCode()) {
            name = "进场";
        }
        if (code == ProjectStatus.SHUTDOWN.getCode()) {
            name = "停工";
        }
        if (code == ProjectStatus.MARCH.getCode()) {
            name = "进行";
        }
        if (code == ProjectStatus.GOODS.getCode()) {
            name = "到货";
        }
        if (code == ProjectStatus.INITIALTEST.getCode()) {
            name = "初验";
        }
        if (code == ProjectStatus.FINALTEST.getCode()) {
            name = "终验";
        }
        if (code == ProjectStatus.COMPLETE.getCode()) {
            name = "完工";
        }
        return name;
    }


    public static String exportStrConvert(ProjectStatus businessType) {
        String name = "";
        if (businessType.equals(ProjectStatus.NOTAPPROACH)) {
            name = "未进场";
        }
        if (businessType.equals(ProjectStatus.APPROACH)) {
            name = "进场";
        }
        if (businessType.equals(ProjectStatus.SHUTDOWN)) {
            name = "停工";
        }
        if (businessType.equals(ProjectStatus.MARCH)) {
            name = "进行";
        }
        if (businessType.equals(ProjectStatus.GOODS)) {
            name = "到货";
        }
        if (businessType.equals(ProjectStatus.INITIALTEST)) {
            name = "初验";
        }
        if (businessType.equals(ProjectStatus.FINALTEST)) {
            name = "终验";
        }
        if (businessType.equals(ProjectStatus.COMPLETE)) {
            name = "完工";
        }
        return name;
    }
}
