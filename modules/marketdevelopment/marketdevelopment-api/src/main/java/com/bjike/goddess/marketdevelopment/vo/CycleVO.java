package com.bjike.goddess.marketdevelopment.vo;

import java.util.List;

/**
 * 周期表现层对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-30 03:05 ]
 * @Description: [ 周期表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CycleVO {

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

    private List<DateDataVO> dateDataVOs;



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

    public List<DateDataVO> getDateDataVOs() {
        return dateDataVOs;
    }

    public void setDateDataVOs(List<DateDataVO> dateDataVOs) {
        this.dateDataVOs = dateDataVOs;
    }

}