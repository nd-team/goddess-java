package com.bjike.goddess.taskallotment.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.taskallotment.enums.Status;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 项目列表
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-14 11:55 ]
 * @Description: [ 项目列表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProjectTO extends BaseTO {

    /**
     * 地区
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "地区不能为空")
    private String area;

    /**
     * 项目名称
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "项目名称不能为空")
    private String project;

    /**
     * 内部项目名称
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "内部项目名称不能为空")
    private String innerProject;

    /**
     * 所属项目组
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "所属项目组不能为空")
    private String depart;

    /**
     * 立项情况
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "立项情况不能为空")
    private String makeProject;

    /**
     * 派工单号
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "派工单号不能为空")
    private String dispatchNum;

    /**
     * 状态
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "状态不能为空")
    private Status status;

    /**
     * 项目表集合
     */
    private List<TableTO> tables;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getInnerProject() {
        return innerProject;
    }

    public void setInnerProject(String innerProject) {
        this.innerProject = innerProject;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getMakeProject() {
        return makeProject;
    }

    public void setMakeProject(String makeProject) {
        this.makeProject = makeProject;
    }

    public String getDispatchNum() {
        return dispatchNum;
    }

    public void setDispatchNum(String dispatchNum) {
        this.dispatchNum = dispatchNum;
    }

    public List<TableTO> getTables() {
        return tables;
    }

    public void setTables(List<TableTO> tables) {
        this.tables = tables;
    }
}