package com.bjike.goddess.royalty.vo;

import com.bjike.goddess.royalty.bo.SystemBetBBO;

import java.util.List;

/**
 * 体系间对赌表A表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-10 04:22 ]
 * @Description: [ 体系间对赌表A表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SystemBetAVO {

    /**
     * id
     */
    private String id;
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
    private List<SystemBetBVO> systemBetBBOS;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public List<SystemBetBVO> getSystemBetBBOS() {
        return systemBetBBOS;
    }

    public void setSystemBetBBOS(List<SystemBetBVO> systemBetBBOS) {
        this.systemBetBBOS = systemBetBBOS;
    }
}