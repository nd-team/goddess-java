package com.bjike.goddess.bonus.vo;

/**
 * 奖罚记录排名表现层对象
 *
 * @Author: [dengjunren]
 * @Date: [2017-04-10 10:33]
 * @Description: [ 奖罚记录排名表现层对象 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DisciplineRecordRankVO {

    /**
     * 开始时间
     */
    private String start;

    /**
     * 结束时间
     */
    private String end;

    /**
     * 项目组
     */
    private String department;

    /**
     * 地区
     */
    private String area;

    /**
     * 用户名
     */
    private String username;

    /**
     * 编号
     */
    private String serialNumber;

    /**
     * 总次数
     */
    private String frequency;

    /**
     * 总分
     */
    private Double total;

    /**
     * 排名
     */
    private Integer rank;

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

}
