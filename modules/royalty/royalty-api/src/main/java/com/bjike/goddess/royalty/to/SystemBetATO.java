package com.bjike.goddess.royalty.to;

import com.bjike.goddess.common.api.to.BaseTO;

import java.util.List;

/**
 * 体系间对赌表A
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-10 04:22 ]
 * @Description: [ 体系间对赌表A ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SystemBetATO extends BaseTO {

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 分值
     */
    private Integer score;

    /**
     * 体系间对赌表B
     */
    private List<SystemBetBTO> systemBetBTOS;

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

    public List<SystemBetBTO> getSystemBetBTOS() {
        return systemBetBTOS;
    }

    public void setSystemBetBTOS(List<SystemBetBTO> systemBetBTOS) {
        this.systemBetBTOS = systemBetBTOS;
    }
}