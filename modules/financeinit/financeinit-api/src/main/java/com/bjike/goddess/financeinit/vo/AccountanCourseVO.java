package com.bjike.goddess.financeinit.vo;

import com.bjike.goddess.financeinit.enums.BalanceDirection;
import com.bjike.goddess.financeinit.enums.CategoryName;

/**
 * 会计科目表现层对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 02:40 ]
 * @Description: [ 会计科目表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AccountanCourseVO {

    /**
     * id
     */
    private String id;

    /**
     * 所属科目id
     */
    private String belongSubjectsId;
    /**
     * 科目级别
     */
    private String subjectsLeve;
    /**
     * 代码
     */
    private String code;

    /**
     * 会计科目名称
     */
    private String accountanName;

    /**
     * 所属类别
     */
    private CategoryName belongCategory;

    /**
     * 余额方向
     */
    private BalanceDirection balanceDirection;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBelongSubjectsId() {
        return belongSubjectsId;
    }

    public void setBelongSubjectsId(String belongSubjectsId) {
        this.belongSubjectsId = belongSubjectsId;
    }

    public String getSubjectsLeve() {
        return subjectsLeve;
    }

    public void setSubjectsLeve(String subjectsLeve) {
        this.subjectsLeve = subjectsLeve;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAccountanName() {
        return accountanName;
    }

    public void setAccountanName(String accountanName) {
        this.accountanName = accountanName;
    }

    public CategoryName getBelongCategory() {
        return belongCategory;
    }

    public void setBelongCategory(CategoryName belongCategory) {
        this.belongCategory = belongCategory;
    }

    public BalanceDirection getBalanceDirection() {
        return balanceDirection;
    }

    public void setBalanceDirection(BalanceDirection balanceDirection) {
        this.balanceDirection = balanceDirection;
    }
}