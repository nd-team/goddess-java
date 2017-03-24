package com.bjike.goddess.organize.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.organize.bo.DimensionBO;
import com.bjike.goddess.organize.to.DimensionTO;

import java.util.List;

/**
 * 对外维度业务接口
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


    /**
     * 保存维度
     *
     * @param to
     * @return
     * @throws SerException
     */
    default DimensionBO save(DimensionTO to) throws SerException {
        return null;
    }

    /**
     * 修改维度
     *
     * @param to
     * @return
     * @throws SerException
     */
    default DimensionBO update(DimensionTO to) throws SerException {
        return null;
    }
}
