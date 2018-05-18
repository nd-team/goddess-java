package com.bjike.goddess.royalty.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.royalty.entity.JobsBetB;

import java.util.List;

/**
 * 岗位间对赌表B业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 04:33 ]
 * @Description: [ 岗位间对赌表B业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class JobsBetBBO extends BaseBO {
    /**
     * 体系
     */
    private String system;
    /**
     * 部门间对赌表C
     */
    private List<JobsBetCBO> jobsBetCBOS;

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public List<JobsBetCBO> getJobsBetCBOS() {
        return jobsBetCBOS;
    }

    public void setJobsBetCBOS(List<JobsBetCBO> jobsBetCBOS) {
        this.jobsBetCBOS = jobsBetCBOS;
    }
}