package com.bjike.goddess.organize.vo;

import java.util.List;

/**
 * Created by ike on 17-9-7.
 */
public class ReHierarchyVO {
    /**
     * id
     */
    private String id;
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
    private List<ReDepartVO> departs;
    /**
     * 层级详情
     */
    private List<ReArrangementVO> arrangementS;
    /**
     * 模块详情
     */
    private List<ReModuleVO> moduleS;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ReDepartVO> getDeparts() {
        return departs;
    }

    public void setDeparts(List<ReDepartVO> departs) {
        this.departs = departs;
    }

    public List<ReArrangementVO> getArrangementS() {
        return arrangementS;
    }

    public void setArrangementS(List<ReArrangementVO> arrangementS) {
        this.arrangementS = arrangementS;
    }

    public List<ReModuleVO> getModuleS() {
        return moduleS;
    }

    public void setModuleS(List<ReModuleVO> moduleS) {
        this.moduleS = moduleS;
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
