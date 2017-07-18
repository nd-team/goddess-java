package com.bjike.goddess.royalty.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 岗位间对赌表A
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-12 04:21 ]
 * @Description: [ 岗位间对赌表A ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class JobsBetATO extends BaseTO {

    /**
     * 项目名称
     */
    @NotBlank(message = "项目名称不能为空",groups = {ADD.class, EDIT.class})
    private String projectName;

    /**
     * 分值
     */
    @NotNull(message = "分值不能为空",groups = {ADD.class, EDIT.class})
    private Integer score;
    /**
     * 岗位间对赌表B
     */
    private List<JobsBetBTO> jobsBetBTOS;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public List<JobsBetBTO> getJobsBetBTOS() {
        return jobsBetBTOS;
    }

    public void setJobsBetBTOS(List<JobsBetBTO> jobsBetBTOS) {
        this.jobsBetBTOS = jobsBetBTOS;
    }
}