package com.bjike.goddess.staffmeeting.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 会议层面
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-03 03:34 ]
 * @Description: [ 会议层面 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MeetingLayTO extends BaseTO {

    /**
     * 议题Id
     */
    @NotBlank(message = "议题Id不能为空",groups = {ADD.class, EDIT.class})
    private String topicId;

    /**
     * 层面名称
     */
    @NotBlank(message = "层面名称不能为空",groups = {ADD.class, EDIT.class})
    private String name;

    /**
     * 计划参会岗位
     */
    @NotBlank(message = "计划参会岗位不能为空",groups = {ADD.class, EDIT.class})
    private String position;

    /**
     * 关联功能
     */
    @NotBlank(message = "关联功能不能为空",groups = {ADD.class, EDIT.class})
    private String relation;


    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }
}