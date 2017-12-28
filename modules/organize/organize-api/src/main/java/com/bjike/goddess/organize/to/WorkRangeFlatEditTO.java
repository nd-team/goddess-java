package com.bjike.goddess.organize.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import org.hibernate.validator.constraints.NotBlank;

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
public class WorkRangeFlatEditTO {

    /**
     * 修改前的业务方向分类
     */
    private String directionEdit;

    /**
     * 业务方向分类
     */
    @NotBlank(message = "业务方向分类不能为空", groups = {ADD.class, EDIT.class})
    private String direction;

    /**
     * 业务方向－科目集合
     */
    private List<ProjectFlatTO> projectFlatTOs;


    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public List<ProjectFlatTO> getProjectFlatTOs() {
        return projectFlatTOs;
    }

    public void setProjectFlatTOs(List<ProjectFlatTO> projectFlatTOs) {
        this.projectFlatTOs = projectFlatTOs;
    }

    public String getDirectionEdit() {
        return directionEdit;
    }

    public void setDirectionEdit(String directionEdit) {
        this.directionEdit = directionEdit;
    }
}
