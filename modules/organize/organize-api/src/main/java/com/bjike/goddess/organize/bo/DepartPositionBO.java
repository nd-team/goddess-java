package com.bjike.goddess.organize.bo;

import java.util.List;

/**
 * Created by ike on 17-9-6.
 */
public class DepartPositionBO {
    /**
     * 项目组/部门
     */
    private String department;
    /**
     * 部门下的职位
     */
    private List<PositionDetailBO> positions;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<PositionDetailBO> getPositions() {
        return positions;
    }

    public void setPositions(List<PositionDetailBO> positions) {
        this.positions = positions;
    }
}
