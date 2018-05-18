package com.bjike.goddess.marketdevelopment.vo;

import java.util.List;

/**
 * 阶段表头数据表现层对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-30 04:30 ]
 * @Description: [ 阶段表头数据表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FilesDataVO {

    /**
     * id
     */
    private String id;
    /**
     * 日期id
     */
    private String dateDataId;

    /**
     * 表头下标
     */
    private Integer tableIndex;

    /**
     * 表头
     */
    private String tableName;

    /**
     * 内容
     */
    private String context;

    /**
     * 父级id
     */
    private String fatherId;

    private List<FilesDataVO> filesDataVO1s;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDateDataId() {
        return dateDataId;
    }

    public void setDateDataId(String dateDataId) {
        this.dateDataId = dateDataId;
    }

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

    public String getFatherId() {
        return fatherId;
    }

    public void setFatherId(String fatherId) {
        this.fatherId = fatherId;
    }

    public List<FilesDataVO> getFilesDataVO1s() {
        return filesDataVO1s;
    }

    public void setFilesDataVO1s(List<FilesDataVO> filesDataVO1s) {
        this.filesDataVO1s = filesDataVO1s;
    }
}