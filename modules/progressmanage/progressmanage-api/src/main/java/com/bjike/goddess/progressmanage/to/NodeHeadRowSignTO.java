package com.bjike.goddess.progressmanage.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 节点表头对应值的行标记
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-26 10:01 ]
 * @Description: [ 节点表头对应值的行标记 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class NodeHeadRowSignTO extends BaseTO {
    /**
     * 节点Id
     */
    @NotBlank(message = "节点Id不能为空", groups = {ADD.class, EDIT.class})
    private String nodeId;

    /**
     * 节点表头信息List
     */
    @NotNull(message = "进度表表头信息List不能为空", groups = {ADD.class, EDIT.class})
    private List<NodeHeadValueTO> toList;

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public List<NodeHeadValueTO> getToList() {
        return toList;
    }

    public void setToList(List<NodeHeadValueTO> toList) {
        this.toList = toList;
    }
}