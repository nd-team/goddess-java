package com.bjike.goddess.organize.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.organize.bo.OperateBO;
import com.bjike.goddess.organize.to.OperateTO;

import java.util.List;

/**
 * 对外操作类型业务接口
 *
 * @Author: [dengjunren]
 * @Date: [2017-03-08 17:27]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface OperateAPI {

    /**
     * 查询未冻结操作类型
     *
     * @return
     * @throws SerException
     */
    default List<OperateBO> findStatus() throws SerException {
        return null;
    }

    /**
     * 保存操作类型
     *
     * @param to
     * @return
     * @throws SerException
     */
    default OperateBO save(OperateTO to) throws SerException {
        return null;
    }

    /**
     * 修改操作类型
     *
     * @param to
     * @return
     * @throws SerException
     */
    default OperateBO update(OperateTO to) throws SerException {
        return null;
    }

}
