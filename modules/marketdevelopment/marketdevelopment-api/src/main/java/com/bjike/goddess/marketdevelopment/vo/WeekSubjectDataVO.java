package com.bjike.goddess.marketdevelopment.vo;

import java.util.List;

/**
 * 业务方向科目数据表现层对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-30 03:03 ]
 * @Description: [ 业务方向科目数据表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class WeekSubjectDataVO {

    /**
     * id
     */
    private String id;
    /**
     * 业务方向类型id
     */
    private String businessDataId;

    /**
     * 业务方向科目
     */
    private String subject;

    private List<WeekCycleVO> weekCycleVOs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBusinessDataId() {
        return businessDataId;
    }

    public void setBusinessDataId(String businessDataId) {
        this.businessDataId = businessDataId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<WeekCycleVO> getWeekCycleVOs() {
        return weekCycleVOs;
    }

    public void setWeekCycleVOs(List<WeekCycleVO> weekCycleVOs) {
        this.weekCycleVOs = weekCycleVOs;
    }
}