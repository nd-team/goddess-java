package com.bjike.goddess.businessinteraction.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 留言
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-28 03:11 ]
 * @Description: [ 留言 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class LeavingMessageTO extends BaseTO {
    public interface TESTLeavingMessage{}

    /**
     * 姓名
     */
    @NotBlank(groups = {LeavingMessageTO.TESTLeavingMessage.class}, message = "姓名不能为空")
    private String name;

    /**
     * 联系方式
     */
    @NotBlank(groups = {LeavingMessageTO.TESTLeavingMessage.class}, message = "联系方式不能为空")
    private String contactWay;

    /**
     * 留言内容
     */
    private String contactContent;

    /**
     * 互动联系信息id
     */
    @NotBlank(groups = {LeavingMessageTO.TESTLeavingMessage.class},message = "互动联系信息id不能为空")
    private String interactionId;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactWay() {
        return contactWay;
    }

    public void setContactWay(String contactWay) {
        this.contactWay = contactWay;
    }

    public String getContactContent() {
        return contactContent;
    }

    public void setContactContent(String contactContent) {
        this.contactContent = contactContent;
    }

    public String getInteractionId() {
        return interactionId;
    }

    public void setInteractionId(String interactionId) {
        this.interactionId = interactionId;
    }
}