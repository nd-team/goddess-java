package com.bjike.goddess.progressmanage.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 进度表表头对应Value
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-19 04:48 ]
 * @Description: [ 进度表表头对应Value ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class TableHeadValueTO extends BaseTO {

    /**
     * 表头Id
     */
    @NotBlank(message = "表头Id不能为空",groups = {ADD.class, EDIT.class})
    private String tableHeadId;

    /**
     * 值
     */
    @NotBlank(message = "值不能为空",groups = {ADD.class, EDIT.class})
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