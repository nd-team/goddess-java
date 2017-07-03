package com.bjike.goddess.user.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.user.bo.PositionBO;
import com.bjike.goddess.user.dto.PositionDTO;
import com.bjike.goddess.user.entity.Position;

import java.util.List;

/**
 * 职位业务接口
 *
 * @Author: [liguiqin]
 * @Date: [2016-12-28 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface PositionSer extends Ser<Position, PositionDTO> {

    /**
     * 根据ID查询岗位
     *
     * @param id 岗位ID
     * @return
     */
    default PositionBO findBOById(String id) throws SerException {
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
