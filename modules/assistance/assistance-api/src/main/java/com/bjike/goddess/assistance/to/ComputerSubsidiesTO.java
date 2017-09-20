package com.bjike.goddess.assistance.to;

import com.bjike.goddess.assistance.enums.SubsidiesStatus;
import com.bjike.goddess.assistance.enums.Usage;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.organize.enums.StaffStatus;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 电脑补助
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-16 02:59 ]
 * @Description: [ 电脑补助 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ComputerSubsidiesTO extends BaseTO {

    /**
     * 是否领用电脑
     */
    @NotNull(message = "是否领用电脑不能为空",groups = {EDIT.class})
    private Boolean necklineComputer;

    /**
     * 使用情况
     */
    @NotNull(message = "使用情况不能为空",groups = {EDIT.class})
    private Usage usage;

    public Boolean getNecklineComputer() {
        return necklineComputer;
    }

    public void setNecklineComputer(Boolean necklineComputer) {
        this.necklineComputer = necklineComputer;
    }

    public Usage getUsage() {
        return usage;
    }

    public void setUsage(Usage usage) {
        this.usage = usage;
    }
}