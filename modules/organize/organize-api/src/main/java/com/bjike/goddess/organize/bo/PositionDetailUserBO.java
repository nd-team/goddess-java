package com.bjike.goddess.organize.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.organize.enums.StaffStatus;

import java.util.List;

/**
 * 用户职位业务传输对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-14 02:33 ]
 * @Description: [ 用户职位业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PositionDetailUserBO extends BaseBO {

    /**
     * 员工名称
     */
    private String name;

    /**
     * 员工编号
     */
    private String number;

//    /**
//     * 职位
//     */
//    private String position;
//
//    /**
//     * 职位id
//     */
//    private String positionIds;

    /**
     * 人员状态
     */
    private StaffStatus staffStatus;
    /**
     * 详细信息
     */
    private List<PositionUserDetailBO> detailS;

    public List<PositionUserDetailBO> getDetailS() {
        return detailS;
    }

    public void setDetailS(List<PositionUserDetailBO> detailS) {
        this.detailS = detailS;
    }

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
}