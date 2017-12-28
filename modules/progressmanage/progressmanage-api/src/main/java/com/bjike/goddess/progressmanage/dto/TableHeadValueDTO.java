package com.bjike.goddess.progressmanage.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 进度表表头对应Value数据传输对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-19 04:48 ]
 * @Description: [ 进度表表头对应Value数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class TableHeadValueDTO extends BaseDTO {

    public interface TableValidate {
    }

    /**
     * 进度表Id
     */
    @NotBlank(message = "进度表Id不能为空", groups = {TableHeadValueDTO.TableValidate.class})
    private String tableId;

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }
}