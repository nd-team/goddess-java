package com.bjike.goddess.bankrecords.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

import javax.persistence.Column;

/**
 * 账号信息数据传输对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-22 03:58 ]
 * @Description: [ 账号信息数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class  BankAccountInfoDTO extends BaseDTO {
    /**
     * 搜索名字
     */
    private String name;

    /**
     * 分页
     */
    private Integer page;

    @Override
    public Integer getPage() {
        return page;
    }

    @Override
    public void setPage(Integer page) {
        this.page = page;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}