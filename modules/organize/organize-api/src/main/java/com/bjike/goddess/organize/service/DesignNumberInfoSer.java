package com.bjike.goddess.organize.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.organize.bo.DesignNumberInfoBO;
import com.bjike.goddess.organize.dto.DesignNumberInfoDTO;
import com.bjike.goddess.organize.entity.DesignNumberInfo;
import com.bjike.goddess.organize.to.DesignNumberInfoTO;

/**
 * 编号设计信息业务接口
 *
 * @Author: [dengjunren]
 * @Date: [2017-03-08 17:11]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface DesignNumberInfoSer extends Ser<DesignNumberInfo, DesignNumberInfoDTO> {

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
