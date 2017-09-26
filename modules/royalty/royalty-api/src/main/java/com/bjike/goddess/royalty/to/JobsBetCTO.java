package com.bjike.goddess.royalty.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.royalty.entity.JobsBetB;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 岗位间对赌表C
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 04:33 ]
 * @Description: [ 岗位间对赌表C ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class JobsBetCTO extends BaseTO {

    /**
     * 部门
     */
    @NotBlank(message = "部门不能为空",groups = {ADD.class, EDIT.class})
    private String department;
    /**
     * 岗位间对赌表C
     */
    private List<JobsBetETO> jobsBetETOS;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<JobsBetETO> getJobsBetETOS() {
        return jobsBetETOS;
    }

    public void setJobsBetETOS(List<JobsBetETO> jobsBetETOS) {
        this.jobsBetETOS = jobsBetETOS;
    }
}