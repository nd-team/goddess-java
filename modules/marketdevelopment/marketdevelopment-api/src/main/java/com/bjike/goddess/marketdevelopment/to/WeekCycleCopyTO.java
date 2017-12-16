package com.bjike.goddess.marketdevelopment.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.marketdevelopment.enums.DateType;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 周计划的周期
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-02 05:37 ]
 * @Description: [ 周计划的周期 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class WeekCycleCopyTO extends BaseTO {

//    /**
//     * 业务方向科目id
//     */
//    private String subjectDataId;

    /**
     * 周期
     */
    @NotBlank(message = "周期不能为空", groups = {ADD.class, EDIT.class})
    private String cycle;

    /**
     * 各周工作量在整月占比
     */
//    @NotBlank(message = "周期不能为空", groups = {ADD.class, EDIT.class})
    private String radio;

    /**
     * 类型
     */
    @NotNull(message = "类型不能为空", groups = {ADD.class, EDIT.class})
    private DateType weekType;


    /**
     * 表头数据集合
     */
    private List<WeekFilesTO> weekFilesTOs;

    /**
     * 备注
     */
    private String remark;


    public DateType getWeekType() {
        return weekType;
    }

    public void setWeekType(DateType weekType) {
        this.weekType = weekType;
    }

    public List<WeekFilesTO> getWeekFilesTOs() {
        return weekFilesTOs;
    }

    public void setWeekFilesTOs(List<WeekFilesTO> weekFilesTOs) {
        this.weekFilesTOs = weekFilesTOs;
    }

    public String getCycle() {

        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getRadio() {
        return radio;
    }

    public void setRadio(String radio) {
        this.radio = radio;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}