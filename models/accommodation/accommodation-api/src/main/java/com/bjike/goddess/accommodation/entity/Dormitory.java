package com.bjike.goddess.accommodation.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author: [xiazhili]
 * @Date: [2017-3-11 16:16]
 * @Description: [宿舍信息]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "dormitory")
public class Dormitory extends BaseEntity {
    /**
     * 项目
     */
    private String project;
    /**
     * 地区
     */
    private String area;
    /**
     * 宿舍地址
     */
    private String dormitoryAddress;
    /**
     * 宿舍钥匙数量（把）
     */
    private String keysNumber;
    /**
     * 宿舍钥匙分配情况
     */
    private String keysDistribution;
    /**
     * 宿舍负责人
     */
    private String head;
    /**
     * 负责人联系方式
     */
    private String headContact;
    /**
     * 宿舍床位数量（个）
     */
    private String berth;
    /**
     * 已入住床位（个）
     */
    private String beeBerth;
    /**
     * 闲置床位（个）
     */
    private String idleBerth;
    /**
     * 宿舍床上3件套数量（件）
     */
    private String suit;
    /**
     * 已领用数量（件）
     */
    private String recipientsSuit;
    /**
     * 闲置数量（件）
     */
    private String idleSuit;
    /**
     * 宿舍被褥数量（件）
     */
    private String bedding;
    /**
     * 已领用数量（件）
     */
    private String recipientsBedding;
    /**
     * 闲置数量（件）
     */
    private String bidleBedding;
    /**
     * 备注
     */
    private String remark;

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDormitoryAddress() {
        return dormitoryAddress;
    }

    public void setDormitoryAddress(String dormitoryAddress) {
        this.dormitoryAddress = dormitoryAddress;
    }

    public String getKeysNumber() {
        return keysNumber;
    }

    public void setKeysNumber(String keysNumber) {
        this.keysNumber = keysNumber;
    }

    public String getKeysDistribution() {
        return keysDistribution;
    }

    public void setKeysDistribution(String keysDistribution) {
        this.keysDistribution = keysDistribution;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getHeadContact() {
        return headContact;
    }

    public void setHeadContact(String headContact) {
        this.headContact = headContact;
    }

    public String getBerth() {
        return berth;
    }

    public void setBerth(String berth) {
        this.berth = berth;
    }

    public String getBeeBerth() {
        return beeBerth;
    }

    public void setBeeBerth(String beeBerth) {
        this.beeBerth = beeBerth;
    }

    public String getIdleBerth() {
        return idleBerth;
    }

    public void setIdleBerth(String idleBerth) {
        this.idleBerth = idleBerth;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String getRecipientsSuit() {
        return recipientsSuit;
    }

    public void setRecipientsSuit(String recipientsSuit) {
        this.recipientsSuit = recipientsSuit;
    }

    public String getIdleSuit() {
        return idleSuit;
    }

    public void setIdleSuit(String idleSuit) {
        this.idleSuit = idleSuit;
    }

    public String getBedding() {
        return bedding;
    }

    public void setBedding(String bedding) {
        this.bedding = bedding;
    }

    public String getRecipientsBedding() {
        return recipientsBedding;
    }

    public void setRecipientsBedding(String recipientsBedding) {
        this.recipientsBedding = recipientsBedding;
    }

    public String getBidleBedding() {
        return bidleBedding;
    }

    public void setBidleBedding(String bidleBedding) {
        this.bidleBedding = bidleBedding;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
