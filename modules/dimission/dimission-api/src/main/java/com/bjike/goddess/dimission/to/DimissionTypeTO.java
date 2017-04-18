package com.bjike.goddess.dimission.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.dimission.enums.DimissionType;

/**
 * 离职类型修改传输对象
 *
 * @Author: [dengjunren]
 * @Date: [2017-04-17 15:56]
 * @Description: [ 离职类型修改传输对象 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DimissionTypeTO extends BaseTO {

    /**
     * 离职类型
     */
    private DimissionType type;

    public DimissionType getType() {
        return type;
    }

    public void setType(DimissionType type) {
        this.type = type;
    }
}
