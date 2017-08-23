package com.bjike.goddess.progressmanage.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 节点表头对应值的行标记数据传输对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-26 10:01 ]
 * @Description: [ 节点表头对应值的行标记数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class NodeHeadRowSignDTO extends BaseDTO {

    public interface NodeValidate {
    }

    /**
     * 节点Id
     */
    @NotBlank(message = "节点Id不能为空", groups = {NodeHeadRowSignDTO.NodeValidate.class})
    private String nodeId;

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }
}