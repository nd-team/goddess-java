package com.bjike.goddess.businessinteraction.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 留言数据传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-28 03:11 ]
 * @Description: [ 留言数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class LeavingMessageDTO extends BaseDTO {

    public interface TestList{}
    /**
     * 互动id
     */
    @NotBlank(groups = {LeavingMessageDTO.TestList.class} , message = "互动id不能为空")
    private String interactionId;

    public String getInteractionId() {
        return interactionId;
    }

    public void setInteractionId(String interactionId) {
        this.interactionId = interactionId;
    }
}