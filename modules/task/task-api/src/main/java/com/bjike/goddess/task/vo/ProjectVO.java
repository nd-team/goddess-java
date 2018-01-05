package com.bjike.goddess.task.vo;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.task.enums.ExecStatus;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * 项目
 *
 * @Author: [liguiqin]
 * @Date: [2016-12-24 10:16]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ProjectVO implements Serializable {
    /**
     * id
     */
    private String id;

    /**
     * 项目名
     */
    private String name;
    /**
     * 地区
     */
    private String area;

    /**
     * 描述
     */
    private String description;

    /**
     * 状态
     */
    private Status status;
    /**
     * 执行状态
     */
    private ExecStatus execStatus;

    /**
     * 创建时间
     */
    private String createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ExecStatus getExecStatus() {
        return execStatus;
    }

    public void setExecStatus(ExecStatus execStatus) {
        this.execStatus = execStatus;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
