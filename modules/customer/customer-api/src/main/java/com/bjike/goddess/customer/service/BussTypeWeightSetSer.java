package com.bjike.goddess.customer.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.customer.bo.BussTypeWeightSetBO;
import com.bjike.goddess.customer.dto.BussTypeWeightSetDTO;
import com.bjike.goddess.customer.entity.BussTypeWeightSet;
import com.bjike.goddess.customer.to.BussTypeWeightSetTO;

import java.util.List;

/**
 * 业务类型权重设置业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 10:24 ]
 * @Description: [ 业务类型权重设置业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BussTypeWeightSetSer extends Ser<BussTypeWeightSet, BussTypeWeightSetDTO> {
    /**
     * 业务类型权重设置列表总条数
     */
    default Long countBussType(BussTypeWeightSetDTO bussTypeWeightSetDTO) throws SerException {
        return null;
    }

    /**
     * 一个业务类型权重设置
     */
    default BussTypeWeightSetBO getOneBussType(String id) throws SerException {
        return null;
    }

    /**
     * 业务类型权重设置列表
     *
     * @return class BussTypeWeightSetBO
     */
    default List<BussTypeWeightSetBO> listBussType(BussTypeWeightSetDTO bussTypeWeightSetDTO) throws SerException {

        return null;
    }

    /**
     * 添加
     *
     * @param bussTypeWeightSetTO 业务类型权重设置
     * @return class BussTypeWeightSetBO
     */
    default BussTypeWeightSetBO addBussType(BussTypeWeightSetTO bussTypeWeightSetTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param bussTypeWeightSetTO 业务类型权重设置
     * @return class BussTypeWeightSetBO
     */
    default BussTypeWeightSetBO editBussType(BussTypeWeightSetTO bussTypeWeightSetTO) throws SerException {
        return null;
    }

    /**
     * 删除业务类型权重设置
     *
     * @param id id
     */
    default void deleteBussType(String id) throws SerException {
        return;
    }
}