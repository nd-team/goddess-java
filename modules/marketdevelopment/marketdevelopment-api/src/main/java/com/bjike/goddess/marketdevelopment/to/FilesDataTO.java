package com.bjike.goddess.marketdevelopment.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 阶段表头数据
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-30 04:30 ]
 * @Description: [ 阶段表头数据 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FilesDataTO extends BaseTO {

//    /**
//     * 日期id
//     */
//    private String dateDataId;

    /**
     * 表头下标
     */
    @NotNull(message = "表头下标不能为空", groups = {ADD.class, EDIT.class})
    private Integer tableIndex;

    /**
     * 表头
     */
    @NotNull(message = "表头不能为空", groups = {ADD.class, EDIT.class})
    private String tableName;

    /**
     * 内容
     */
    @NotNull(message = "内容不能为空", groups = {ADD.class, EDIT.class})
    private String context;

//    /**
//     * 父级id
//     */
//    private String fatherId;


    public Integer getTableIndex() {
        return tableIndex;
    }

    public void setTableIndex(Integer tableIndex) {
        this.tableIndex = tableIndex;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }


}