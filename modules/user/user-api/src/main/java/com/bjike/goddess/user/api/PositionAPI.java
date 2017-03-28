package com.bjike.goddess.user.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.user.bo.PositionBO;

import java.util.List;

/**
 * 对外提供岗位业务接口
 *
 * @Author: [dengjunren]
 * @Date: [2017-03-16 11:36]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface PositionAPI {

    /**
     * 根据ID查询岗位
     *
     * @param id 岗位ID
     * @return
     */
    default PositionBO findById(String id) throws SerException {
        return null;
    }

    /**
     * 查询父级岗位
     *
     * @param id 岗位ID;
     * @return
     */
    default PositionBO findParent(String id) throws SerException {
        return null;
    }

    /**
     * 查询子级岗位
     *
     * @param id 岗位ID
     * @return
     */
    default List<PositionBO> findChild(String id) throws SerException {
        return null;
    }

}
