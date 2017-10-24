package com.bjike.goddess.carinfo.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import javax.persistence.*;


/**
* 旧服务器上的车辆信息
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-08 05:56 ]
* @Description:	[ 旧服务器上的车辆信息 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Entity
@Table(name = "carinfo_driver")
public class Driver extends BaseEntity {

    /**
     * 审核人
     */
    @Column(name = "auditor",columnDefinition = "VARCHAR(255)   COMMENT '审核人'"  )
    private String  auditor;

    /**
     * 审核意见
     */
    @Column(name = "auditIdea",columnDefinition = "VARCHAR(255)   COMMENT '审核意见'"  )
    private String  auditIdea;

    /**
     * 地区
     */
    @Column(name = "area",columnDefinition = "VARCHAR(255)   COMMENT '地区'"  )
    private String  area;

    /**
     * 结算方式
     */
    @Column(name = "way",columnDefinition = "VARCHAR(255)   COMMENT '结算方式'"  )
    private String  way;

    /**
     * 司机名称
     */
    @Column(name = "driverName",columnDefinition = "VARCHAR(255)   COMMENT '司机名称'"  )
    private String  driverName;

    /**
     * 手机号码
     */
    @Column(name = "phoneNum",columnDefinition = "VARCHAR(255)   COMMENT '手机号码'"  )
    private String  phoneNum;

    /**
     * 身份证
     */
    @Column(name = "idCard",columnDefinition = "VARCHAR(255)   COMMENT '身份证'"  )
    private String  idCard;

    /**
     * 地址
     */
    @Column(name = "address",columnDefinition = "VARCHAR(255)   COMMENT '地址'"  )
    private String  address;

    /**
     * 车牌型号
     */
    @Column(name = "carModel",columnDefinition = "VARCHAR(255)   COMMENT '车牌型号'"  )
    private String  carModel;

    /**
     * 车牌号码
     */
    @Column(name = "carNum",columnDefinition = "VARCHAR(255)   COMMENT '车牌号码'"  )
    private String  carNum;

    /**
     * 发动机号
     */
    @Column(name = "engineNUm",columnDefinition = "VARCHAR(255)   COMMENT '发动机号'"  )
    private String  engineNUm;

    /**
     * 购买时间
     */
    @Column(name = "buyDate",columnDefinition = "VARCHAR(255)   COMMENT '购买时间'"  )
    private String  buyDate;

    /**
     * 使用时长
     */
    @Column(name = "useDate",columnDefinition = "VARCHAR(255)   COMMENT '使用时长'"  )
    private String  useDate;

    /**
     * 排放量
     */
    @Column(name = "discharge",columnDefinition = "DECIMAL(10,2)   COMMENT '排放量'"  )
    private Double  discharge;

    /**
     * 耗油量
     */
    @Column(name = "carFuel",columnDefinition = "DECIMAL(10,2)   COMMENT '耗油量'"  )
    private Double  carFuel;

    /**
     * 是否签订协议
     */
    @Column(name = "isDeal",columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否签订协议'"  , insertable = false  )
    private Boolean  isDeal;

    /**
     * 签订日期
     */
    @Column(name = "dealDate",columnDefinition = "VARCHAR(255)   COMMENT '签订日期'"  )
    private String  dealDate;

    /**
     * 是否提供行驶证照片
     */
    @Column(name = "isTravel",columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否提供行驶证照片'"  , insertable = false  )
    private Boolean  isTravel;

    /**
     * 行驶证所有者姓名
     */
    @Column(name = "travelName",columnDefinition = "VARCHAR(255)   COMMENT '行驶证所有者姓名'"  )
    private String  travelName;

    /**
     * 是否提供驾驶证照片
     */
    @Column(name = "isDrive",columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否提供驾驶证照片'"  , insertable = false  )
    private Boolean  isDrive;

    /**
     * 驾驶证所有者姓名
     */
    @Column(name = "useName",columnDefinition = "VARCHAR(255)   COMMENT '驾驶证所有者姓名'"  )
    private String  useName;

    /**
     * 是否提供本车辆保险
     */
    @Column(name = "isInsure",columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否提供本车辆保险'"  , insertable = false  )
    private Boolean  isInsure;

    /**
     * 合同签订日期
     */
    @Column(name = "signDate",columnDefinition = "VARCHAR(255)   COMMENT '合同签订日期'"  )
    private String  signDate;

    /**
     * 合同到期日期
     */
    @Column(name = "dueDate",columnDefinition = "VARCHAR(255)   COMMENT '合同到期日期'"  )
    private String  dueDate;

    /**
     * 开户人
     */
    @Column(name = "userName",columnDefinition = "VARCHAR(255)   COMMENT '开户人'"  )
    private String  userName;

    /**
     * 银行卡号
     */
    @Column(name = "bankCardNum",columnDefinition = "VARCHAR(255)   COMMENT '银行卡号'"  )
    private String  bankCardNum;

    /**
     * 银行名称
     */
    @Column(name = "bankName",columnDefinition = "VARCHAR(255)   COMMENT '银行名称'"  )
    private String  bankName;

    /**
     * 是否解约
     */
    @Column(name = "isBreak",columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否解约'"  , insertable = false  )
    private Boolean  isBreak;

    /**
     * 是否联系
     */
    @Column(name = "isOpen",columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否联系'"  , insertable = false  )
    private Boolean  isOpen;

    /**
     * 是否备注
     */
    @Column(name = "isSpare",columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否备注'"  , insertable = false  )
    private Boolean  isSpare;

    /**
     * 备注
     */
    @Column(name = "remark",columnDefinition = "VARCHAR(255)   COMMENT '备注'"  )
    private String  remark;

    /**
     * 状态
     */
    @Column(name = "status",columnDefinition = "TINYINT(1) DEFAULT 0   COMMENT '状态'"  )
    private Integer status;

    /**
     * 密码
     */
    @Column(name = "password",columnDefinition = "  VARCHAR(255)  COMMENT '密码'"  )
    private String password;
    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getAuditIdea() {
        return auditIdea;
    }

    public void setAuditIdea(String auditIdea) {
        this.auditIdea = auditIdea;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public String getEngineNUm() {
        return engineNUm;
    }

    public void setEngineNUm(String engineNUm) {
        this.engineNUm = engineNUm;
    }

    public String getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(String buyDate) {
        this.buyDate = buyDate;
    }

    public String getUseDate() {
        return useDate;
    }

    public void setUseDate(String useDate) {
        this.useDate = useDate;
    }

    public Double getDischarge() {
        return discharge;
    }

    public void setDischarge(Double discharge) {
        this.discharge = discharge;
    }

    public Double getCarFuel() {
        return carFuel;
    }

    public void setCarFuel(Double carFuel) {
        this.carFuel = carFuel;
    }

    public Boolean getDeal() {
        return isDeal;
    }

    public void setDeal(Boolean deal) {
        isDeal = deal;
    }

    public String getDealDate() {
        return dealDate;
    }

    public void setDealDate(String dealDate) {
        this.dealDate = dealDate;
    }

    public Boolean getTravel() {
        return isTravel;
    }

    public void setTravel(Boolean travel) {
        isTravel = travel;
    }

    public String getTravelName() {
        return travelName;
    }

    public void setTravelName(String travelName) {
        this.travelName = travelName;
    }

    public Boolean getDrive() {
        return isDrive;
    }

    public void setDrive(Boolean drive) {
        isDrive = drive;
    }

    public String getUseName() {
        return useName;
    }

    public void setUseName(String useName) {
        this.useName = useName;
    }

    public Boolean getInsure() {
        return isInsure;
    }

    public void setInsure(Boolean insure) {
        isInsure = insure;
    }

    public String getSignDate() {
        return signDate;
    }

    public void setSignDate(String signDate) {
        this.signDate = signDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBankCardNum() {
        return bankCardNum;
    }

    public void setBankCardNum(String bankCardNum) {
        this.bankCardNum = bankCardNum;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Boolean getBreak() {
        return isBreak;
    }

    public void setBreak(Boolean aBreak) {
        isBreak = aBreak;
    }

    public Boolean getOpen() {
        return isOpen;
    }

    public void setOpen(Boolean open) {
        isOpen = open;
    }

    public Boolean getSpare() {
        return isSpare;
    }

    public void setSpare(Boolean spare) {
        isSpare = spare;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}