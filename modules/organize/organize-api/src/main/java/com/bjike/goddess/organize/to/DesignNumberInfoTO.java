package com.bjike.goddess.organize.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 编号设计信息
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-31 09:49 ]
 * @Description: [ 编号设计信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DesignNumberInfoTO extends BaseTO {

    /**
     * 操作对象
     */
    @NotBlank(message = "操作对象不能为空", groups = {ADD.class, EDIT.class})
    private String serialNumber;

    /**
     * 编号信息类型
     */
    @NotBlank(message = "编号信息类型不能为空", groups = {ADD.class, EDIT.class})
    private String type;

    /**
     * 举例名称
     */
    @NotBlank(message = "举例名称不能为空", groups = {ADD.class, EDIT.class})
    private String illustrate;

    /**
     * 举例编号
     */
    @NotBlank(message = "举例编号不能为空", groups = {ADD.class, EDIT.class})
    private String illustrateNumber;

    /**
     * 编号信息生成说明
     */
    @NotBlank(message = "编号信息生成说明不能为空", groups = {ADD.class, EDIT.class})
    private String description;


    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIllustrate() {
        return illustrate;
    }

    public void setIllustrate(String illustrate) {
        this.illustrate = illustrate;
    }

    public String getIllustrateNumber() {
        return illustrateNumber;
    }

    public void setIllustrateNumber(String illustrateNumber) {
        this.illustrateNumber = illustrateNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}