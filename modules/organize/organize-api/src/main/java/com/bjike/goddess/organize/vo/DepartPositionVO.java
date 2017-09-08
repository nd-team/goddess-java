package com.bjike.goddess.organize.vo;

import java.util.List;

/**
 * Created by ike on 17-9-6.
 */
public class DepartPositionVO {
    /**
     * 项目组/部门
     */
    private String department;
    /**
     * 部门下的职位
     */
    private List<PositionDetailVO> positions;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<PositionDetailVO> getPositions() {
        return positions;
    }

    public void setPositions(List<PositionDetailVO> positions) {
        this.positions = positions;
    }
}
