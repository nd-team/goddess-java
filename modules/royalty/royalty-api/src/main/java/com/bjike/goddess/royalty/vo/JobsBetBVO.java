package com.bjike.goddess.royalty.vo;

import com.bjike.goddess.royalty.entity.JobsBetB;

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
     * 部门
     */
    private String department;
    /**
     * 岗位间对赌表A
     */
    private JobsBetAVO jobsBetAVO;

    private List<JobsBetCVO> jobsBetCVOS;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public JobsBetAVO getJobsBetAVO() {
        return jobsBetAVO;
    }

    public void setJobsBetAVO(JobsBetAVO jobsBetAVO) {
        this.jobsBetAVO = jobsBetAVO;
    }

    public List<JobsBetCVO> getJobsBetCVOS() {
        return jobsBetCVOS;
    }

    public void setJobsBetCVOS(List<JobsBetCVO> jobsBetCVOS) {
        this.jobsBetCVOS = jobsBetCVOS;
    }
}