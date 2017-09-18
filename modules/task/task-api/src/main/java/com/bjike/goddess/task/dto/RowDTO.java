package com.bjike.goddess.task.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.GET;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Author: [liguiqin]
 * @Date: [2017-09-15 17:17]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class RowDTO extends BaseDTO {
    /**
     * 执行原生sql是否分页
     */
    private boolean page;
    /**
     * 查询数据行表id
     */
    @NotBlank(message = "所属表id不能为空", groups = {GET.class, ADD.class})
    private String tableId;
    /**
     * 查询数据行节点
     */
    @NotBlank(message = "所属表节点不能为空", groups = {GET.class, ADD.class})
    private String node;

    public boolean isPage() {
        return page;
    }

    public void setPage(boolean page) {
        this.page = page;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }
}
