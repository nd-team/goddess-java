package com.bjike.goddess.organize.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 工作范围信息设置展示对象
 *
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午10:50]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class WorkRangeTO extends BaseTO {

    /**
     * 方向
     */
    @NotNull(message = "方向不能为空", groups = {ADD.class, EDIT.class})
    private String direction;

    /**
     * 科目
     */
    @NotNull(message = "科目不能为空", groups = {ADD.class, EDIT.class})
    private String project;

    /**
     * 专业分类
     */
    @NotNull(message = "专业分类不能为空", groups = {ADD.class, EDIT.class})
    private String classify;

    /**
     * 工作范围
     */
    @NotNull(message = "工作范围不能为空", groups = {ADD.class, EDIT.class})
    private String workRange;

    /**
     * 工作界面(节点)
     */
    @NotNull(message = "工作界面不能为空", groups = {ADD.class, EDIT.class})
    private String node;

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getWorkRange() {
        return workRange;
    }

    public void setWorkRange(String workRange) {
        this.workRange = workRange;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }
}
