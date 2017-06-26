package com.bjike.goddess.progressmanage.vo;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 进度表表头对应Value
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-19 04:48 ]
 * @Description: [ 进度表表头对应Value ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class TableHeadValueReceiveVO {


    /**
     * 表头Id
     */
    private String tableHeadId;

    /**
     * 值
     */
    private String value;


    public String getTableHeadId() {
        return tableHeadId;
    }

    public void setTableHeadId(String tableHeadId) {
        this.tableHeadId = tableHeadId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}