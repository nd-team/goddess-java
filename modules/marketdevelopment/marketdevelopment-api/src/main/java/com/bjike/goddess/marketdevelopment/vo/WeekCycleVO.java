package com.bjike.goddess.marketdevelopment.vo;

/**
 * 周计划的周期表现层对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-02 05:37 ]
 * @Description: [ 周计划的周期表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class WeekCycleVO {

    /**
     * id
     */
    private String id;
    /**
     * 业务方向科目id
     */
    private String subjectDataId;

    /**
     * 周期
     */
    private String cycle;

    /**
     * 各周工作量在整月占比
     */
    private String radio;

    /**
     * 表头数据
     * @return
     */
    private WeekFilesVO weekFilesVO;

    /**
     * 合计(数组,数组第一个为合计的数据,第二个为备注的数据)
     */
    private String[] total;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubjectDataId() {
        return subjectDataId;
    }

    public void setSubjectDataId(String subjectDataId) {
        this.subjectDataId = subjectDataId;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getRadio() {
        return radio;
    }

    public void setRadio(String radio) {
        this.radio = radio;
    }

    public WeekFilesVO getWeekFilesVO() {
        return weekFilesVO;
    }

    public void setWeekFilesVO(WeekFilesVO weekFilesVO) {
        this.weekFilesVO = weekFilesVO;
    }

    public String[] getTotal() {
        return total;
    }

    public void setTotal(String[] total) {
        this.total = total;
    }
}