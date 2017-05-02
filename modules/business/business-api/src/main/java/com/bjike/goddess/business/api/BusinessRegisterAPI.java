package com.bjike.goddess.business.api;

import com.bjike.goddess.business.bo.BusinessRegisterBO;
import com.bjike.goddess.business.dto.BusinessRegisterDTO;
import com.bjike.goddess.business.to.BusinessRegisterTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 工商注册业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-18 03:41 ]
 * @Description: [ 工商注册业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BusinessRegisterAPI {
    /**
     * 工商注册列表总条数
     */
    default Long countBusinessRegister(BusinessRegisterDTO businessRegisterDTO) throws SerException {
        return null;
    }
    /**
     * 一个工商注册
     *
     * @return class BusinessRegisterBO
     */
    default BusinessRegisterBO getOne(String id) throws SerException {
        return null;
    }
    /**
     * 工商注册
     *
     * @param businessRegisterDTO 工商注册dto
     * @return class BusinessRegisterBO
     * @throws SerException
     */
    default List<BusinessRegisterBO> findListBusinessRegister(BusinessRegisterDTO businessRegisterDTO) throws SerException {
        return null;
    }

    /**
     * 添加工商注册
     *
     * @param businessRegisterTO 工商注册数据to
     * @return class BusinessRegisterBO
     * @throws SerException
     */
    default BusinessRegisterBO insertBusinessRegister(BusinessRegisterTO businessRegisterTO) throws SerException {
        return null;
    }

    /**
     * 编辑工商注册
     *
     * @param businessRegisterTO 工商注册数据to
     * @return class BusinessRegisterBO
     * @throws SerException
     */
    default BusinessRegisterBO editBusinessRegister(BusinessRegisterTO businessRegisterTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除工商注册
     *
     * @param id
     * @throws SerException
     */
    default void removeBusinessRegister(String id) throws SerException {

    }
    /**
     * 上传
     */
    default void upload() throws SerException {
        return;

    }
    /**
     * 下载
     */
    default void download() throws SerException {
        return;

    }


}