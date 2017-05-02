package com.bjike.goddess.business.service;

import com.bjike.goddess.business.bo.BusinessAnnualInfoBO;
import com.bjike.goddess.business.bo.BusinessTaxChangeBO;
import com.bjike.goddess.business.dto.BusinessAnnualInfoDTO;
import com.bjike.goddess.business.to.BusinessAnnualInfoTO;
import com.bjike.goddess.business.to.BusinessTaxChangeTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.business.entity.BusinessTaxChange;
import com.bjike.goddess.business.dto.BusinessTaxChangeDTO;

import java.util.List;

/**
 * 工商税务变更业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-18 03:56 ]
 * @Description: [ 工商税务变更业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BusinessTaxChangeSer extends Ser<BusinessTaxChange, BusinessTaxChangeDTO> {
    /**
     * 工商税务变更列表总条数
     */
    default Long countBusinessTaxChange(BusinessTaxChangeDTO businessTaxChangeDTO) throws SerException {
        return null;
    }
    /**
     * 一个工商税务变更
     *
     * @return class BusinessTaxChangeBO
     */
    default BusinessTaxChangeBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 工商税务变更
     *
     * @param businessTaxChangeDTO 工商税务变更dto
     * @return class BusinessTaxChangeBO
     * @throws SerException
     */
    default List<BusinessTaxChangeBO> findListBusinessTaxChange(BusinessTaxChangeDTO businessTaxChangeDTO) throws SerException {
        return null;
    }

    /**
     * 添加工商税务变更
     *
     * @param businessTaxChangeTO 工商税务变更数据to
     * @return class BusinessTaxChangeBO
     * @throws SerException
     */
    default BusinessTaxChangeBO insertBusinessTaxChange(BusinessTaxChangeTO businessTaxChangeTO) throws SerException {
        return null;
    }

    /**
     * 编辑工商税务变更
     *
     * @param businessTaxChangeTO 工商税务变更数据to
     * @return class BusinessTaxChangeBO
     * @throws SerException
     */
    default BusinessTaxChangeBO editBusinessTaxChange(BusinessTaxChangeTO businessTaxChangeTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除工商税务变更
     *
     * @param id
     * @throws SerException
     */
    default void removeBusinessTaxChange(String id) throws SerException {

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