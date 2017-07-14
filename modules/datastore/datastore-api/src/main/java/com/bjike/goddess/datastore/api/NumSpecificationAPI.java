package com.bjike.goddess.datastore.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.datastore.bo.NumSpecificationBO;
import com.bjike.goddess.datastore.dto.NumSpecificationDTO;
import com.bjike.goddess.datastore.to.GuidePermissionTO;
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
public interface NumSpecificationAPI {
    /**
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }
    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }
    /**
     * 数据存储编号规范列表总条数
     */
    default Long countNumSpecification(NumSpecificationDTO numSpecificationDTO) throws SerException {
        return null;
    }
    /**
     * 一个数据存储编号规范
     * @return class NumSpecificationBO
     */
    default NumSpecificationBO getOne(String id) throws SerException {return null;}


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