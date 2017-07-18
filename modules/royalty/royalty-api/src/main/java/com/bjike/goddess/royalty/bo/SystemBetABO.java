package com.bjike.goddess.royalty.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.royalty.entity.DepartmentBetB;
import com.bjike.goddess.royalty.entity.SystemBetB;

import java.util.List;

/**
 * 体系间对赌表A业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-10 04:22 ]
 * @Description: [ 体系间对赌表A业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SystemBetABO extends BaseBO {

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 分值
     */
    private Integer score;
    /**
     * 体系间对赌B
     */
    private List<SystemBetBBO> systemBetBBOS;


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
    public List<SystemBetBBO> getSystemBetBBOS() {
        return systemBetBBOS;
    }

    public void setSystemBetBBOS(List<SystemBetBBO> systemBetBBOS) {
        this.systemBetBBOS = systemBetBBOS;
    }

}