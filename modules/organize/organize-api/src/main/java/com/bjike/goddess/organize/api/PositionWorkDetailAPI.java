package com.bjike.goddess.organize.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.organize.bo.PositionWorkDetailBO;
import com.bjike.goddess.organize.to.PositionWorkDetailTO;

import java.util.List;

/**
 * 对外岗位工作详细业务接口
 *
 * @Author: [dengjunren]
 * @Date: [2017-03-08 17:30]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface PositionWorkDetailAPI {

    /**
     * 根据说明书ID查询工作详细
     *
     * @param id 岗位说明书ID
     * @return
     */
    default List<PositionWorkDetailBO> findByInstruction(String id) throws SerException {
        return null;
    }

    /**
     * 添加岗位工作明细
     *
     * @param to
     * @return
     * @throws SerException
     */
    default PositionWorkDetailBO save(PositionWorkDetailTO to) throws SerException {
        return null;
    }

    /**
     * 修改岗位工作明细
     *
     * @param to
     * @return
     * @throws SerException
     */
    default PositionWorkDetailBO update(PositionWorkDetailTO to) throws SerException {
        return null;
    }

}
