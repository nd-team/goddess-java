package com.bjike.goddess.organize.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.organize.bo.DimensionBO;
import com.bjike.goddess.organize.dto.DimensionDTO;
import com.bjike.goddess.organize.entity.Dimension;

import java.util.List;

/**
 * 维度业务接口
 *
 * @Author: [dengjunren]
 * @Date: [2017-03-08 17:13]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface DimensionAPI {

    /**
     * 查询未冻结维度
     *
     * @return
     * @throws SerException
     */
    default List<DimensionBO> findStatus() throws SerException {
        return null;
    }


}
