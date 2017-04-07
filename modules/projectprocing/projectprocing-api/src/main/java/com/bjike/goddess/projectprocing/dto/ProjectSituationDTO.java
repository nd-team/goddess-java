package com.bjike.goddess.projectprocing.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 项目情况数据传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-31 02:16 ]
 * @Description: [ 项目情况数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProjectSituationDTO extends BaseDTO {
    public interface TESTSearch{}


    /**
     * 工程地点
     */
    private String enginPlace;


    /**
     * 完工情况
     */
    private String completeCondition;


    public String getEnginPlace() {
        return enginPlace;
    }

    public void setEnginPlace(String enginPlace) {
        this.enginPlace = enginPlace;
    }

    public String getCompleteCondition() {
        return completeCondition;
    }

    public void setCompleteCondition(String completeCondition) {
        this.completeCondition = completeCondition;
    }
}