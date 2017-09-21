package com.bjike.goddess.task.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import com.bjike.goddess.task.entity.ProblemType;
import com.bjike.goddess.task.enums.ProblemStatus;

/**
 * 问题
 * @des 更多条件请构建条件json
 *
 * @Author: [liguiqin]
 * @Date: [2017-09-20 09:36]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ProblemDTO extends BaseDTO {
    /**
     * 问题类型
     */
    private ProblemType type;
    /**
     * 问题状态
     */
    private ProblemStatus status;

    public ProblemType getType() {
        return type;
    }

    public void setType(ProblemType type) {
        this.type = type;
    }

    public ProblemStatus getStatus() {
        return status;
    }

    public void setStatus(ProblemStatus status) {
        this.status = status;
    }
}
