package com.bjike.goddess.outcarfare.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 资金准备审核数据传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-05 02:41 ]
 * @Description: [ 资金准备审核数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MoneyReadyDTO extends BaseDTO {
    public interface DEPART{}
    public interface AREA{}
    /**
     * 开始时间
     */
    @NotBlank(groups = {MoneyReadyDTO.DEPART.class,MoneyReadyDTO.AREA.class},message = "开始时间不能为空")
    private String startTime;
    /**
     * 结束时间
     */
    @NotBlank(groups = {MoneyReadyDTO.DEPART.class,MoneyReadyDTO.AREA.class},message = "结束时间不能为空")
    private String endTime;
    /**
     * 项目组数组
     */
    @NotNull(groups = MoneyReadyDTO.DEPART.class,message = "项目组数组不能为空")
    private String[] groupTeams;

    /**
     * 地区数组
     */
    @NotNull(groups = MoneyReadyDTO.AREA.class,message = "地区数组不能为空")
    private String[] areas;

    public String[] getAreas() {
        return areas;
    }

    public void setAreas(String[] areas) {
        this.areas = areas;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String[] getGroupTeams() {
        return groupTeams;
    }

    public void setGroupTeams(String[] groupTeams) {
        this.groupTeams = groupTeams;
    }
}