package com.bjike.goddess.organize.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.organize.enums.StaffStatus;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 用户职位
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-14 02:33 ]
 * @Description: [ 用户职位 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PositionDetailUserTO extends BaseTO {

    /**
     * 姓名
     */
    @NotNull(message = "姓名", groups = {ADD.class})
    private String name;
    /**
     * 员工编号
     */
    @NotNull(message = "员工编号", groups = {ADD.class})
    private String number;
    /**
     * 职位详情
     */
    private List<PositionUserDetailTO> detailTOS;
    /**
     * 人员状态
     */
    @NotNull(message = "人员状态不能为空", groups = {ADD.class, EDIT.class})
    private StaffStatus staffStatus;

    public StaffStatus getStaffStatus() {
        return staffStatus;
    }

    public void setStaffStatus(StaffStatus staffStatus) {
        this.staffStatus = staffStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<PositionUserDetailTO> getDetailTOS() {
        return detailTOS;
    }

    public void setDetailTOS(List<PositionUserDetailTO> detailTOS) {
        this.detailTOS = detailTOS;
    }
}