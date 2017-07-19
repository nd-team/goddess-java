package com.bjike.goddess.royalty.vo;

import java.util.List;

/**
 * 岗位间对赌表表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 04:34 ]
 * @Description: [ 岗位间对赌表表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class JobsBetVO {

    /**
     * id
     */
    private String id;
    /**
     * 岗位间对赌表A
     */
    private JobsBetAVO jobsBetAVO;

    /**
     * 岗位间对赌表B
     */
    private List<JobsBetBVO> jobsBetBVOS;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public JobsBetAVO getJobsBetAVO() {
        return jobsBetAVO;
    }

    public void setJobsBetAVO(JobsBetAVO jobsBetAVO) {
        this.jobsBetAVO = jobsBetAVO;
    }

    public List<JobsBetBVO> getJobsBetBVOS() {
        return jobsBetBVOS;
    }

    public void setJobsBetBVOS(List<JobsBetBVO> jobsBetBVOS) {
        this.jobsBetBVOS = jobsBetBVOS;
    }
}