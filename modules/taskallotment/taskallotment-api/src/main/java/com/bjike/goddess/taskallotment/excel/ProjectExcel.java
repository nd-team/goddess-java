package com.bjike.goddess.taskallotment.excel;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.taskallotment.enums.Status;

/**
 * 项目excel
 *
 * @Author: [chenjunhao]
 * @Date: [2017-10-28 10:01]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ProjectExcel extends BaseTO {
    /**
     * 地区
     */
    @ExcelHeader(name = "地区", notNull = true)
    private String area;

    /**
     * 项目名称
     */
    @ExcelHeader(name = "项目名称", notNull = true)
    private String project;

    /**
     * 内部项目名称
     */
    @ExcelHeader(name = "内部项目名称", notNull = true)
    private String innerProject;

    /**
     * 所属项目组
     */
    @ExcelHeader(name = "所属项目组", notNull = true)
    private String depart;

    /**
     * 立项情况
     */
    @ExcelHeader(name = "立项情况", notNull = true)
    private String makeProject;

    /**
     * 派工单号
     */
    @ExcelHeader(name = "派工单号", notNull = true)
    private String dispatchNum;

    /**
     * 创建人
     */
    @ExcelHeader(name = "创建人", notNull = true)
    private String name;

    /**
     * 状态
     */
    @ExcelHeader(name = "状态", notNull = true)
    private Status status;

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
