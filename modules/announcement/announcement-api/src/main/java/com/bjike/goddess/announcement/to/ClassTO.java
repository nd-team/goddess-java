package com.bjike.goddess.announcement.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 公告分类
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-07 02:20 ]
 * @Description: [ 公告分类 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ClassTO extends BaseTO {

    /**
     * 名称
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "名称不能为空")
    private String name;

    /**
     * 描述
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "描述不能为空")
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}