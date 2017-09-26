package com.bjike.goddess.organize.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * Created by ike on 17-9-7.
 */
public class ReDepartBO extends BaseBO {
    /**
     * 部门编号
     */
    private String serialNumber;
    /**
     * 项目组/部门
     */
    private String department;
    /**
     * 所属地区
     */
    private String area;
    /**
     * 层级详情
     */
    private List<ReArrangementBO> arrangementS;

    public List<ReArrangementBO> getArrangementS() {
        return arrangementS;
    }

    public void setArrangementS(List<ReArrangementBO> arrangementS) {
        this.arrangementS = arrangementS;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
