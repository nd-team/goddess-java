package com.bjike.goddess.organize.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * Created by ike on 17-9-7.
 */
public class ReHierarchyBO extends BaseBO{
    /**
     * 体系编号
     */
    private String serialNumber;
    /**
     * 体系
     */
    private String hierarchy;
    /**
     * 部门详情
     */
    private List<ReDepartBO> departs;
    /**
     * 层级详情
     */
    private List<ReArrangementBO> arrangementS;
    /**
     * 模块详情
     */
    private List<ReModuleBO> moduleS;

    public List<ReModuleBO> getModuleS() {
        return moduleS;
    }

    public void setModuleS(List<ReModuleBO> moduleS) {
        this.moduleS = moduleS;
    }

    public List<ReArrangementBO> getArrangementS() {
        return arrangementS;
    }

    public void setArrangementS(List<ReArrangementBO> arrangementS) {
        this.arrangementS = arrangementS;
    }

    public List<ReDepartBO> getDeparts() {
        return departs;
    }

    public void setDeparts(List<ReDepartBO> departs) {
        this.departs = departs;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(String hierarchy) {
        this.hierarchy = hierarchy;
    }
}
