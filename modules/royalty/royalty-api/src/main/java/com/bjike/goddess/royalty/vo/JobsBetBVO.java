package com.bjike.goddess.royalty.vo;

import java.util.List;

/**
 * 岗位间对赌表B表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 04:33 ]
 * @Description: [ 岗位间对赌表B表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class JobsBetBVO {

    /**
     * id
     */
    private String id;
    /**
     * 体系
     */
    private String system;
    /**
     * 岗位间对赌表C
     */
    private List<JobsBetCVO> jobsBetCBOS;

    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public List<JobsBetCVO> getJobsBetCBOS() {
        return jobsBetCBOS;
    }

    public void setJobsBetCBOS(List<JobsBetCVO> jobsBetCBOS) {
        this.jobsBetCBOS = jobsBetCBOS;
    }
}