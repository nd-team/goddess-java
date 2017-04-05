package com.bjike.goddess.contacts.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * QQ群管理
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 05:51 ]
 * @Description: [ QQ群管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class QQGroupTO extends BaseTO {

    /**
     * Q群号
     */
    @NotNull(message = "Q群号不能为空", groups = {ADD.class, EDIT.class})
    private String number;

    /**
     * Q群名称
     */
    @NotNull(message = "Q群名称不能为空", groups = {ADD.class, EDIT.class})
    private String name;

    /**
     * Q群对象
     */
    @NotNull(message = "Q群对象不能为空", groups = {ADD.class, EDIT.class})
    private String object;

    /**
     * Q群管理人
     */
    @NotNull(message = "Q群管理人不能为空", groups = {ADD.class, EDIT.class})
    private String manager;


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }
}