package com.bjike.goddess.financeinit.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.financeinit.enums.BalanceDirection;
import com.bjike.goddess.financeinit.enums.CategoryName;
import com.bjike.goddess.financeinit.excel.AccountanCourseExport;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;


/**
 * 会计科目
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 02:40 ]
 * @Description: [ 会计科目 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "financeinit_accountancourse")
public class AccountanCourse extends BaseEntity {

    @Transient
    private List<AccountanCourse> level=new ArrayList<>();
    @Transient String name;

    /**
     * 所属科目id
     *
     */
    @Column(name = "belongSubjectsId", columnDefinition = "VARCHAR(255)   COMMENT '所属科目id'")
    private String belongSubjectsId;
    /**
     * 科目级别
     */
    @Column(name = "subjectsLeve", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '科目级别'")
    private String subjectsLeve;
    /**
     * 代码
     */
    @Column(name = "code", nullable = false,unique = true, columnDefinition = "VARCHAR(255)   COMMENT '代码'")
    private String code;

    /**
     * 会计科目名称
     */
    @Column(name = "accountanName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '会计科目名称'")
    private String accountanName;

    /**
     * 所属类别
     */
    @Column(name = "belongCategory", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '所属类别'")
    private CategoryName belongCategory;

    /**
     * 余额方向
     */
    @Column(name = "balanceDirection", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '余额方向'")
    private BalanceDirection balanceDirection;

    /**
     * 公司编号
     */
    @Column(name = "systemId", updatable = false, columnDefinition = "VARCHAR(20)   COMMENT '公司编号'")
    private String systemId;

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
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

    public List<AccountanCourse> getLevel() {
        return level;
    }

    public void setLevel(List<AccountanCourse> level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}