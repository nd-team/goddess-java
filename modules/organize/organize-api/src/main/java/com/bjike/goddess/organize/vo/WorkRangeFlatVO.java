package com.bjike.goddess.organize.vo;

import java.util.List;

/**
 * 工作范围信息设置展示对象
 *
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午10:50]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class WorkRangeFlatVO {

    /**
     * 业务方向分类
     */
    private String direction;

    /**
     * 业务方向－科目集合
     */
    private List<ProjectFlatVO> projectFlatVOs;

    /**
     * id
     */
    private String id;

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public List<ProjectFlatVO> getProjectFlatVOs() {
        return projectFlatVOs;
    }

    public void setProjectFlatVOs(List<ProjectFlatVO> projectFlatVOs) {
        this.projectFlatVOs = projectFlatVOs;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
