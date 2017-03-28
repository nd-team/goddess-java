package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.marketdevelopment.bo.BusinessTypeBO;
import com.bjike.goddess.marketdevelopment.dto.BusinessTypeDTO;
import com.bjike.goddess.marketdevelopment.entity.BusinessType;
import com.bjike.goddess.marketdevelopment.to.BusinessTypeTO;

import java.util.List;

/**
 * 业务类型业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 07:20 ]
 * @Description: [ 业务类型业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BusinessTypeSer extends Ser<BusinessType, BusinessTypeDTO> {

    /**
     * 保存业务类型数据
     *
     * @param to 业务类型传输对象
     * @return
     * @throws SerException
     */
    default BusinessTypeBO save(BusinessTypeTO to) throws SerException {
        return null;
    }

    /**
     * 修改业务类型数据
     *
     * @param to 业务类型传输对象
     * @return
     * @throws SerException
     */
    default BusinessTypeBO update(BusinessTypeTO to) throws SerException {
        return null;
    }

    /**
     * 冻结业务类型数据
     *
     * @param to 业务类型传输对象
     * @return
     * @throws SerException
     */
    default BusinessTypeBO congeal(BusinessTypeTO to) throws SerException {
        return null;
    }

    /**
     * 解冻业务类型数据
     *
     * @param to 业务类型传输对象
     * @return
     * @throws SerException
     */
    default BusinessTypeBO thaw(BusinessTypeTO to) throws SerException {
        return null;
    }

    /**
     * 删除业务类型数据
     *
     * @param to 业务类型传输对象
     * @return
     * @throws SerException
     */
    default BusinessTypeBO delete(BusinessTypeTO to) throws SerException {
        return null;
    }

    /**
     * 查询正常数据的业务类型数据
     *
     * @return
     * @throws SerException
     */
    default List<BusinessTypeBO> findThaw() throws SerException {
        return null;
    }

}