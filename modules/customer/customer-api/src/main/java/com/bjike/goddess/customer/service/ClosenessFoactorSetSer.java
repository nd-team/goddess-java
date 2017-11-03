package com.bjike.goddess.customer.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.customer.bo.ClosenessFoactorSetBO;
import com.bjike.goddess.customer.dto.ClosenessFoactorSetDTO;
import com.bjike.goddess.customer.entity.ClosenessFoactorSet;
import com.bjike.goddess.customer.to.ClosenessFoactorSetTO;

import java.util.List;

/**
 * 亲密度因素层设置业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 02:01 ]
 * @Description: [ 亲密度因素层设置业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ClosenessFoactorSetSer extends Ser<ClosenessFoactorSet, ClosenessFoactorSetDTO> {
    /**
     * 亲密度因素层设置总条数
     */
    default Long countCloseness(ClosenessFoactorSetDTO closenessFoactorSetDTO) throws SerException {
        return null;
    }

    /**
     * 一个亲密度因素层设置
     */
    default ClosenessFoactorSetBO getOneCloseness(String id) throws SerException {
        return null;
    }

    /**
     * 亲密度因素层设置列表
     *
     * @return class ClosenessFoactorSetBO
     */
    default List<ClosenessFoactorSetBO> listCloseness(ClosenessFoactorSetDTO closenessFoactorSetDTO) throws SerException {

        return null;
    }

    /**
     * 添加
     *
     * @param closenessFoactorSetTO 亲密度因素层设置
     * @return class ClosenessFoactorSetBO
     */
    default ClosenessFoactorSetBO addCloseness(ClosenessFoactorSetTO closenessFoactorSetTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param closenessFoactorSetTO 亲密度因素层设置
     * @return class ClosenessFoactorSetBO
     */
    default ClosenessFoactorSetBO editCloseness(ClosenessFoactorSetTO closenessFoactorSetTO) throws SerException {
        return null;
    }

    /**
     * 删除亲密度因素层设置
     *
     * @param id id
     */
    default void deleteCloseness(String id) throws SerException {
        return;
    }
}