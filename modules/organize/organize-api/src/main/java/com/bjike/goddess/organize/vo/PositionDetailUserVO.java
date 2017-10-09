package com.bjike.goddess.organize.vo;

import com.bjike.goddess.organize.enums.StaffStatus;

import java.util.List;

/**
 * 用户职位表现层对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-14 02:33 ]
 * @Description: [ 用户职位表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PositionDetailUserVO {

    /**
     * id
     */
    private String id;

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
     * 职位选项数据
     */
    private List<UserPositionVO> positionVo;

    /**
     * 职位信息
     */
    private List<PositionDetailVO> positionDetails;
    /**
     * 详细信息
     */
    private List<PositionUserDetailVO> detailS;
    /**
     * 人员状态
     */
    private StaffStatus staffStatus;

    public StaffStatus getStaffStatus() {
        return staffStatus;
    }

    public void setStaffStatus(StaffStatus staffStatus) {
        this.staffStatus = staffStatus;
    }

    public List<PositionUserDetailVO> getDetailS() {
        return detailS;
    }

    public void setDetailS(List<PositionUserDetailVO> detailS) {
        this.detailS = detailS;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<UserPositionVO> getPositionVo() {
        return positionVo;
    }

    public void setPositionVo(List<UserPositionVO> positionVo) {
        this.positionVo = positionVo;
    }

    public List<PositionDetailVO> getPositionDetails() {
        return positionDetails;
    }

    public void setPositionDetails(List<PositionDetailVO> positionDetails) {
        this.positionDetails = positionDetails;
    }
}