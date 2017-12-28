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
public class JobsBetCVO {

    /**
     * id
     */
    private String id;
    /**
     * 部门
     */
    private String department;
    /**
     * 岗位间对赌表D
     */
    private List<JobsBetDVO> jobsBetDBOS;

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

    public List<JobsBetDVO> getJobsBetDBOS() {
        return jobsBetDBOS;
    }

    public void setJobsBetDBOS(List<JobsBetDVO> jobsBetDBOS) {
        this.jobsBetDBOS = jobsBetDBOS;
    }
}