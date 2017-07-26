package com.bjike.goddess.allmeeting.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 会议层面
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-26 10:16 ]
 * @Description: [ 会议层面 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MeetingLayTO extends BaseTO {

    /**
     * 议题
     */
    @NotBlank(message = "议题不能为空", groups = {ADD.class, EDIT.class})
    private String topicId;

    /**
     * 层面名称
     */
    @NotBlank(message = "层面名称不能为空", groups = {ADD.class, EDIT.class})
    private String name;

    /**
     * 计划参会岗位
     */
    @NotNull(message = "计划参会岗位不能为空", groups = {ADD.class, EDIT.class})
    private String[] position;


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

    public String[] getPosition() {
        return position;
    }

    public void setPosition(String[] position) {
        this.position = position;
    }
}