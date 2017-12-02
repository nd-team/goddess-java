package com.bjike.goddess.marketdevelopment.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 阶段表头数据业务传输对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-30 04:30 ]
 * @Description: [ 阶段表头数据业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FilesDataBO extends BaseBO {

    /**
     * 日期id
     */
    private String dateDataId;

    /**
     * 表头下标
     */
    private Integer index;

    /**
     * 表头
     */
    private String table;

    /**
     * 内容
     */
    private String context;

    /**
     * 父级id
     */
    private String fatherId;

    private List<FilesDataBO> filesDataVO1s;


    public String getDateDataId() {
        return dateDataId;
    }

    public void setDateDataId(String dateDataId) {
        this.dateDataId = dateDataId;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
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

    public List<FilesDataBO> getFilesDataVO1s() {
        return filesDataVO1s;
    }

    public void setFilesDataVO1s(List<FilesDataBO> filesDataVO1s) {
        this.filesDataVO1s = filesDataVO1s;
    }
}