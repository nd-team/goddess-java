package com.bjike.goddess.progressmanage.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 进度节点表头数据传输对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-09 05:43 ]
 * @Description: [ 进度节点表头数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class NodeHeadDTO extends BaseDTO {

    public interface HeadValidate {
    }

    /**
     * 节点Id
     */
    @NotBlank(message = "节点Id不能为空", groups = {NodeHeadDTO.HeadValidate.class})
    private String nodeId;

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }
}