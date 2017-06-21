package com.bjike.goddess.staffmeeting.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 议题管理
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-03 03:31 ]
 * @Description: [ 议题管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MeetingTopicTO extends BaseTO {

    /**
     * 议题
     */
    @NotBlank(message = "议题不能为空",groups = {ADD.class, EDIT.class})
    private String topic;

    /**
     * 议题包含内容
     */
    @NotBlank(message = "议题包含内容不能为空",groups = {ADD.class, EDIT.class})
    private String topicContent;


    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTopicContent() {
        return topicContent;
    }

    public void setTopicContent(String topicContent) {
        this.topicContent = topicContent;
    }
}