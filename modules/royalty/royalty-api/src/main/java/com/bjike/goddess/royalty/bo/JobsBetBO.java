package com.bjike.goddess.royalty.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 岗位间对赌表业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 04:34 ]
 * @Description: [ 岗位间对赌表业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class JobsBetBO extends BaseBO {

    /**
     * 岗位间对赌表A
     */
    private JobsBetABO jobsBetABO;



    public JobsBetABO getJobsBetABO() {
        return jobsBetABO;
    }

    public void setJobsBetABO(JobsBetABO jobsBetABO) {
        this.jobsBetABO = jobsBetABO;
    }

}