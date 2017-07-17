package com.bjike.goddess.announcement.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 公告分类业务传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-07 02:20 ]
 * @Description: [ 公告分类业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ClassBO extends BaseBO {

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建人
     */
    private String creater;


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

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }
}