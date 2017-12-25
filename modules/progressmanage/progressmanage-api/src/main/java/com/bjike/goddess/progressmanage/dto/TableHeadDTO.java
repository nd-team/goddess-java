package com.bjike.goddess.progressmanage.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 进度表表头数据传输对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-09 05:08 ]
 * @Description: [ 进度表表头数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class TableHeadDTO extends BaseDTO {

    public interface Headvalidate {
    }

    /**
     * 进度表Id
     */
    @NotBlank(message = "进度表Id不能为空!", groups = {TableHeadDTO.Headvalidate.class})
    private String tableId;

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }
}