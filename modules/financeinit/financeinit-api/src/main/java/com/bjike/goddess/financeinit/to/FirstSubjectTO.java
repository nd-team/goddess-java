package com.bjike.goddess.financeinit.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 一级科目
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-29 03:57 ]
 * @Description: [ 一级科目 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FirstSubjectTO extends BaseTO {

    /**
     * 编号
     */
    private String code;

    /**
     * 会计科目名称
     */
    @NotBlank(message = "会计科目名称不能为空")
    private String name;

    /**
     * 级别所属类别
     */
    @NotBlank(message = "级别所属类别不能为空，有如下类别(资产类/负债类/共同类/权益类/成本类/损益类)可选")
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