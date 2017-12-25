package com.bjike.goddess.progressmanage.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 进度表表头对应值的行标记
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-20 03:27 ]
 * @Description: [ 进度表表头对应值的行标记 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class TableHeadRowSignTO extends BaseTO {

    /**
     * 进度表Id
     */
    @NotBlank(message = "进度表Id不能为空", groups = {ADD.class, EDIT.class})
    private String tableId;

    /**
     * 进度表表头信息List
     */
    @NotNull(message = "进度表表头信息List不能为空", groups = {ADD.class, EDIT.class})
    private List<TableHeadValueTO> toList;

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public List<TableHeadValueTO> getToList() {
        return toList;
    }

    public void setToList(List<TableHeadValueTO> toList) {
        this.toList = toList;
    }
}