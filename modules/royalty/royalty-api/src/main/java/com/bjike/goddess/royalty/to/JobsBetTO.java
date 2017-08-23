package com.bjike.goddess.royalty.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.royalty.bo.JobsBetABO;
import com.bjike.goddess.royalty.bo.JobsBetBBO;

import java.util.List;

/**
 * 岗位间对赌表
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 04:34 ]
 * @Description: [ 岗位间对赌表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class JobsBetTO extends BaseTO {

    /**
     * 岗位间对赌表A
     */
    private JobsBetATO jobsBetATO;

    /**
     * 岗位间对赌表B
     */
    private List<JobsBetBTO> jobsBetBTOS;

    public JobsBetATO getJobsBetATO() {
        return jobsBetATO;
    }

    public void setJobsBetATO(JobsBetATO jobsBetATO) {
        this.jobsBetATO = jobsBetATO;
    }

    public List<JobsBetBTO> getJobsBetBTOS() {
        return jobsBetBTOS;
    }

    public void setJobsBetBTOS(List<JobsBetBTO> jobsBetBTOS) {
        this.jobsBetBTOS = jobsBetBTOS;
    }
}