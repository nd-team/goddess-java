package com.bjike.goddess.datastore.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.datastore.bo.FileSpecificationBO;
import com.bjike.goddess.datastore.bo.NumSpecificationBO;
import com.bjike.goddess.datastore.dto.FileSpecificationDTO;
import com.bjike.goddess.datastore.entity.NumSpecification;
import com.bjike.goddess.datastore.dto.NumSpecificationDTO;
import com.bjike.goddess.datastore.to.FileSpecificationTO;
import com.bjike.goddess.datastore.to.NumSpecificationTO;

import java.util.List;

/**
 * 数据存储编号规范业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-21 05:35 ]
 * @Description: [ 数据存储编号规范业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface NumSpecificationSer extends Ser<NumSpecification, NumSpecificationDTO> {

    /**
     * 数据存储编号规范列表总条数
     */
    default Long countNumSpecification(NumSpecificationDTO numSpecificationDTO) throws SerException {
        return null;
    }

    /**
     * 数据存储编号规范
     *
     * @param numSpecificationDTO 数据存储编号规范dto
     * @return class NumSpecificationBO
     * @throws SerException
     */
    default List<NumSpecificationBO> findListNumSpecification(NumSpecificationDTO numSpecificationDTO) throws SerException {
        return null;
    }

    /**
     * 添加数据存储编号规范
     *
     * @param numSpecificationTO 数据存储编号规范数据to
     * @return class NumSpecificationBO
     * @throws SerException
     */
    default NumSpecificationBO insertNumSpecification(NumSpecificationTO numSpecificationTO) throws SerException {
        return null;
    }

    /**
     * 编辑数据存储编号规范
     *
     * @param numSpecificationTO 数据存储编号规范数据to
     * @return class NumSpecificationBO
     * @throws SerException
     */
    default NumSpecificationBO editNumSpecification(NumSpecificationTO numSpecificationTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除数据存储编号规范
     *
     * @param id
     * @throws SerException
     */
    default void removeNumSpecification(String id) throws SerException {

    }

}