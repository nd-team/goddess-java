package com.bjike.goddess.taskallotment.excel;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.taskallotment.enums.Status;

import javax.persistence.Column;

/**
 * 项目表excel
 *
 * @Author: [chenjunhao]
 * @Date: [2017-10-28 10:12]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class TableExcel extends BaseTO {
    /**
     * 表名称
     */
    @ExcelHeader(name = "表名称", notNull = true)
    private String name;

    /**
     * 状态
     */
    @ExcelHeader(name = "状态", notNull = true)
    private Status status;
    /**
     * 创建人
     */
    @ExcelHeader(name = "创建人", notNull = true)
    private String creater;

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
