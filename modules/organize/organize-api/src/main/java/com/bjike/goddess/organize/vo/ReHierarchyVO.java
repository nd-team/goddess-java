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
