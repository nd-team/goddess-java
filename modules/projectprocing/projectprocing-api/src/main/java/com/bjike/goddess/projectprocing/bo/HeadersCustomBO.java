package com.bjike.goddess.projectprocing.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.projectprocing.enums.Types;

/**
 * 表头定制业务传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 10:26 ]
 * @Description: [ 表头定制业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class HeadersCustomBO extends BaseBO {

    /**
     * 外包单位
     */
    private String outUnit;

    /**
     * 进度管理id
     */
    private String prossManageId;
    /**
     * 父级id
     */
    private String fatherId;
    /**
     * 表头
     */
    private String header;


    /**
     * 类型
     */
    private Types types;

    /**
     * 是否为必填
     */
    private Boolean requiredFill;

    /**
     * 字段内容
     */
    private String content;

    /**
     * 备注
     */
    private String remark;


    public String getOutUnit() {
        return outUnit;
    }

    public void setOutUnit(String outUnit) {
        this.outUnit = outUnit;
    }

    public String getProssManageId() {
        return prossManageId;
    }

    public void setProssManageId(String prossManageId) {
        this.prossManageId = prossManageId;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getFatherId() {
        return fatherId;
    }

    public void setFatherId(String fatherId) {
        this.fatherId = fatherId;
    }

    public Types getTypes() {
        return types;
    }

    public void setTypes(Types types) {
        this.types = types;
    }

    public Boolean getRequiredFill() {
        return requiredFill;
    }

    public void setRequiredFill(Boolean requiredFill) {
        this.requiredFill = requiredFill;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}