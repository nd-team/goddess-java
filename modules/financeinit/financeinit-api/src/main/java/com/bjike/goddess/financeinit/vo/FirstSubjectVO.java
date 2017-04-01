package com.bjike.goddess.financeinit.vo;

/**
 * 一级科目表现层对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-29 03:57 ]
 * @Description: [ 一级科目表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FirstSubjectVO {

    /**
     * id
     */
    private String id;
    /**
     * 编号
     */
    private String code;

    /**
     * 会计科目名称
     */
    private String name;

    /**
     * 级别所属类别
     */
    private String category;

    /**
     * 会计科目适用范围
     */
    private String remark;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
}