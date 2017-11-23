package com.bjike.goddess.lendreimbursement.entity.olddata;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 老系统的摘要
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-11-17 10:35 ]
 * @Description: [ 老系统的摘要 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "aj_oa_finance_matters")
public class AjoaMatters extends BaseEntity {

    /**
     * 摘要
     */
    @Column(name = "matter",  columnDefinition = "VARCHAR(255)   COMMENT '摘要'")
    private String matter;

    /**
     * 金额
     */
    @Column(name = "money",  columnDefinition = "VARCHAR(255)   COMMENT '金额'")
    private String money;

    /**
     * 报销id
     */
    @Column(name = "reimburse_id",  columnDefinition = "VARCHAR(255)   COMMENT '报销id'")
    private String reimburse_id;

    /**
     * 状态
     */
    @Column(name = "status",  columnDefinition = "INT(2)   COMMENT '状态'")
    private int status;


    public String getMatter() {
        return matter;
    }

    public void setMatter(String matter) {
        this.matter = matter;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getReimburse_id() {
        return reimburse_id;
    }

    public void setReimburse_id(String reimburse_id) {
        this.reimburse_id = reimburse_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}