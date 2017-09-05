package com.bjike.goddess.organize.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

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
public class WorkRangeFlatBO extends BaseBO {

    /**
     * 业务方向分类
     */
    private String direction;

    /**
     * 业务方向－科目集合
     */
    private List<ProjectFlatBO> projectFlatBOs;

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

    public List<ProjectFlatBO> getProjectFlatBOs() {
        return projectFlatBOs;
    }

    public void setProjectFlatBOs(List<ProjectFlatBO> projectFlatBOs) {
        this.projectFlatBOs = projectFlatBOs;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }
}
