package com.bjike.goddess.reportmanagement.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.reportmanagement.enums.DataType;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 补充资料表
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-20 02:29 ]
 * @Description: [ 补充资料表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DataTO extends BaseTO {

    /**
     * 补充资料
     */
    @NotBlank(message = "data", groups = {ADD.class, EDIT.class})
    private String data;

    /**
     * 补充资料类型
     */
    @NotNull(message = "dataType", groups = {ADD.class, EDIT.class})
    private DataType dataType;


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }
}