package com.bjike.goddess.projectmeasure.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.projectmeasure.type.InterfaceSelect;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 多项目多个界面
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 11:04 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MultipleProjectMultipleUIBTO extends BaseTO {



    /**
     * 界面选择
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "界面选择不能为空")
    private InterfaceSelect interfaceSelect;

    /**
     * 工作量
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "工作量不能为空")
    private Integer workload;

    /**
     * 利润
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "利润不能为空")
    private Double profit;


    public InterfaceSelect getInterfaceSelect() {
        return interfaceSelect;
    }

    public void setInterfaceSelect(InterfaceSelect interfaceSelect) {
        this.interfaceSelect = interfaceSelect;
    }

    public Integer getWorkload() {
        return workload;
    }

    public void setWorkload(Integer workload) {
        this.workload = workload;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

}