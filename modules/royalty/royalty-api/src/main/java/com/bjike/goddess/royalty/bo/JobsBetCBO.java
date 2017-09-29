package com.bjike.goddess.royalty.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 岗位间对赌表C业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 04:33 ]
 * @Description: [ 岗位间对赌表C业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class JobsBetCBO extends BaseBO {

    /**
     * 部门
     */
    private String department;
    /**
     * 岗位间对赌表D
     */
    private List<JobsBetDBO> jobsBetDBOS;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<JobsBetDBO> getJobsBetDBOS() {
        return jobsBetDBOS;
    }

    public void setJobsBetDBOS(List<JobsBetDBO> jobsBetDBOS) {
        this.jobsBetDBOS = jobsBetDBOS;
    }
}