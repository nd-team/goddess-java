package com.bjike.goddess.projectprocing.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.projectprocing.enums.Types;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 表头定制
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 10:26 ]
 * @Description: [ 表头定制 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class HeadersCustomTO extends BaseTO {
    /**
     * 外包单位
     */
    @NotBlank(message = "外包单位不能为空",groups = {ADD.class,EDIT.class})
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
    @NotBlank(message = "表头不能为空",groups = {ADD.class,EDIT.class})
    private String header;

    /**
     * 类型
     */
    @NotNull(message = "类型不能为空",groups = {ADD.class,EDIT.class})
    private Types types;

    /**
     * 是否为必填
     */
    @NotNull(message = "是否为必填不能为空",groups = {ADD.class,EDIT.class})
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

    public String getFatherId() {
        return fatherId;
    }

    public void setFatherId(String fatherId) {
        this.fatherId = fatherId;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
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