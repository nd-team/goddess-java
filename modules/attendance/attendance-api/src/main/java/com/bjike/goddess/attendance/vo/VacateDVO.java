package com.bjike.goddess.attendance.vo;

/**
 * 请假汇总第四层
 *
 * @Author: [chenjunhao]
 * @Date: [2017-10-12 17:23]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class VacateDVO {
    /**
     * id
     */
    private String id;
    /**
     * 请假人
     */
    private String name;
    /**
     * 补班请假时长
     */
    private Double fillTime;
    /**
     * 正常工作日内请假时长
     */
    private Double normalTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getFillTime() {
        return fillTime;
    }

    public void setFillTime(Double fillTime) {
        this.fillTime = fillTime;
    }

    public Double getNormalTime() {
        return normalTime;
    }

    public void setNormalTime(Double normalTime) {
        this.normalTime = normalTime;
    }

}
