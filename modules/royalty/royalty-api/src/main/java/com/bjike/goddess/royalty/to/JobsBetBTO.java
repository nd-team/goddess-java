package com.bjike.goddess.royalty.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.royalty.entity.JobsBetB;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

/**
 * 岗位间对赌表B
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 04:33 ]
 * @Description: [ 岗位间对赌表B ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class JobsBetBTO extends BaseTO {

    /**
     * 体系
     */
    @NotBlank(message = "体系不能为空",groups = {ADD.class, EDIT.class})
    private String system;
    /**
     * 岗位间对赌表C
     */
    private List<JobsBetCTO> jobsBetCTOS;

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public List<JobsBetCTO> getJobsBetCTOS() {
        return jobsBetCTOS;
    }

    public void setJobsBetCTOS(List<JobsBetCTO> jobsBetCTOS) {
        this.jobsBetCTOS = jobsBetCTOS;
    }
}