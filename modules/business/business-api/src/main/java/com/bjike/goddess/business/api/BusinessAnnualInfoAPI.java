package com.bjike.goddess.business.api;

import com.bjike.goddess.business.bo.BusinessAnnualInfoBO;
import com.bjike.goddess.business.dto.BusinessAnnualInfoDTO;
import com.bjike.goddess.business.to.BusinessAnnualInfoTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 工商年检信息业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-18 03:48 ]
 * @Description: [ 工商年检信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BusinessAnnualInfoAPI {
    /**
     * 工商年检信息列表总条数
     */
    default Long countBusinessAnnualInfo(BusinessAnnualInfoDTO businessAnnualInfoDTO) throws SerException {
        return null;
    }
    /**
     * 一个工商年检信息
     *
     * @return class BusinessAnnualInfoBO
     */
    default BusinessAnnualInfoBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 工商年检信息
     *
     * @param businessAnnualInfoDTO 工商年检信息dto
     * @return class BusinessAnnualInfoBO
     * @throws SerException
     */
    default List<BusinessAnnualInfoBO> findListBusinessAnnualInfo(BusinessAnnualInfoDTO businessAnnualInfoDTO) throws SerException {
        return null;
    }

    /**
     * 添加工商年检信息
     *
     * @param businessAnnualInfoTO 工商年检信息数据to
     * @return class BusinessAnnualInfoBO
     * @throws SerException
     */
    default BusinessAnnualInfoBO insertBusinessAnnualInfo(BusinessAnnualInfoTO businessAnnualInfoTO) throws SerException {
        return null;
    }

    /**
     * 编辑工商年检信息
     *
     * @param businessAnnualInfoTO 工商年检信息数据to
     * @return class BusinessAnnualInfoBO
     * @throws SerException
     */
    default BusinessAnnualInfoBO editBusinessAnnualInfo(BusinessAnnualInfoTO businessAnnualInfoTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除工商年检信息
     *
     * @param id
     * @throws SerException
     */
    default void removeBusinessAnnualInfo(String id) throws SerException {

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