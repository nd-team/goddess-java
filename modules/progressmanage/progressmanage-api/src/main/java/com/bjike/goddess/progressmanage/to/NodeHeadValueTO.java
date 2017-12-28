package com.bjike.goddess.progressmanage.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 节点表头值
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-26 09:54 ]
 * @Description: [ 节点表头值 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class NodeHeadValueTO extends BaseTO {


    /**
     * 节点表头Id
     */
    @NotBlank(message = "节点表头Id不能为空", groups = {ADD.class, EDIT.class})
    private String nodeHeadId;

    /**
     * 值
     */
    private String value;


    public String getNodeHeadId() {
        return nodeHeadId;
    }

    public void setNodeHeadId(String nodeHeadId) {
        this.nodeHeadId = nodeHeadId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}