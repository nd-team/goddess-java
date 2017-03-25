package com.bjike.goddess.organize.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.organize.bo.DesignNumberInfoBO;
import com.bjike.goddess.organize.to.DesignNumberInfoTO;

/**
 * 对外编号设计信息业务接口
 *
 * @Author: [dengjunren]
 * @Date: [2017-03-08 17:11]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface DesignNumberInfoAPI {

    /**
     * 保存编号设计信息
     *
     * @param to
     * @return
     * @throws SerException
     */
    default DesignNumberInfoBO save(DesignNumberInfoTO to) throws SerException {
        return null;
    }

    /**
     * 修改编号设计信息
     *
     * @param to
     * @return
     * @throws SerException
     */
    default DesignNumberInfoBO update(DesignNumberInfoTO to) throws SerException {
        return null;
    }

}
