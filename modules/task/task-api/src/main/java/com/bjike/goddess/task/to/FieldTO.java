package com.bjike.goddess.task.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.api.type.DataType;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 列
 *
 * @Author: [liguiqin]
 * @Date: [2017-09-15 16:48]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class FieldTO extends BaseTO {
    /**
     * 列名称
     */
    @NotNull(message = "列名称不能为空",groups = {ADD.class, EDIT.class})
    private String[] names;

    /**
     * 节点名称
     */
    @NotBlank(message = "节点名称不能为空",groups = {ADD.class, EDIT.class})
    private String node;

    /**
     * 所属表
     */
    @NotBlank(message = "所属表不能为空",groups = {ADD.class, EDIT.class})
    private String tableId;

    /**
     * 是否必填
     */
    private boolean need;
    /**
     * 数据类型
     */
    private DataType dataType;

    public String[] getNames() {
        return names;
    }

    public void setNames(String[] names) {
        this.names = names;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public boolean isNeed() {
        return need;
    }

    public void setNeed(boolean need) {
        this.need = need;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }
}
