package com.bjike.goddess.rotation.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.rotation.bo.CoverRotationOpinionBO;
import com.bjike.goddess.rotation.dto.CoverRotationOpinionDTO;

import java.util.List;

/**
 * 岗位轮换自荐意见业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-13 04:04 ]
 * @Description: [ 岗位轮换自荐意见业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CoverRotationOpinionAPI {

    /**
     * 根据岗位轮换自荐id查询意见
     *
     * @param id  岗位轮换自荐数据id
     * @param dto 岗位轮换自荐意见数据传输对象
     * @return
     * @throws SerException
     */
    default List<CoverRotationOpinionBO> findByCover(String id, CoverRotationOpinionDTO dto) throws SerException {
        return null;
    }

    /**
     * 获取总条数
     *
     * @param id 岗位轮换自荐数据id
     * @return
     * @throws SerException
     */
    default Long getTotal(String id) throws SerException {
        return null;
    }

}