package com.bjike.goddess.system.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.system.bo.AuswerBO;
import com.bjike.goddess.system.dto.AuswerDTO;
import com.bjike.goddess.system.to.AuswerTO;

import java.util.List;

/**
 * 答案业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-11 04:49 ]
 * @Description: [ 答案业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AuswerAPI {
    /**
     * 答案业务列表总条数
     */
    default Long count(AuswerDTO dto) throws SerException {
        return null;
    }

    /**
     * 一个答案业务
     *
     * @return class AuswerBO
     */
    default AuswerBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 答案业务
     *
     * @param dto 答案业务dto
     * @return class AuswerBO
     * @throws SerException
     */
    default List<AuswerBO> list(AuswerDTO dto) throws SerException {
        return null;
    }

    /**
     * 添加答案业务
     *
     * @param to 答案业务数据to
     * @return class AuswerBO
     * @throws SerException
     */
    default AuswerBO insert(AuswerTO to) throws SerException {
        return null;
    }

    /**
     * 编辑答案业务
     *
     * @param to 答案业务数据to
     * @return class AuswerBO
     * @throws SerException
     */
    default AuswerBO edit(AuswerTO to) throws SerException {
        return null;
    }

    /**
     * 根据id删除答案业务
     *
     * @param id
     * @throws SerException
     */
    default void remove(String id) throws SerException {

    }
}