package com.bjike.goddess.contractcommunicat.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.contractcommunicat.bo.BusinessNegotiationBO;
import com.bjike.goddess.contractcommunicat.dto.BusinessNegotiationDTO;
import com.bjike.goddess.contractcommunicat.entity.BusinessNegotiation;
import com.bjike.goddess.contractcommunicat.to.BusinessNegotiationTO;
import com.bjike.goddess.contractcommunicat.to.GuidePermissionTO;

import java.util.List;

/**
 * 商务洽谈业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-11-28 11:24 ]
 * @Description: [ 商务洽谈业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BusinessNegotiationSer extends Ser<BusinessNegotiation, BusinessNegotiationDTO> {
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
     * 商务洽谈列表总条数
     */
    default Long count(BusinessNegotiationDTO dto) throws SerException {
        return null;
    }

    /**
     * 一个商务洽谈
     *
     * @return class BusinessNegotiationBO
     */
    default BusinessNegotiationBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 获取商务洽谈
     *
     * @param dto 商务洽谈dto
     * @return class BusinessNegotiationBO
     * @throws SerException
     */
    default List<BusinessNegotiationBO> list(BusinessNegotiationDTO dto) throws SerException {
        return null;
    }

    /**
     * 添加商务洽谈
     *
     * @param to 商务洽谈数据to
     * @return class BusinessNegotiationBO
     * @throws SerException
     */
    default BusinessNegotiationBO insert(BusinessNegotiationTO to) throws SerException {
        return null;
    }

    /**
     * 编辑商务洽谈
     *
     * @param to 商务洽谈数据to
     * @return class BusinessNegotiationBO
     * @throws SerException
     */
    default BusinessNegotiationBO edit(BusinessNegotiationTO to) throws SerException {
        return null;
    }

    /**
     * 根据id删除商务洽谈
     *
     * @param id
     * @throws SerException
     */
    default void remove(String id) throws SerException {

    }

    /**
     * 导入商务洽谈excel
     *
     * @param businessNegotiationTOS
     * @return
     * @throws SerException
     */
    default BusinessNegotiationBO importExcel(List<BusinessNegotiationTO> businessNegotiationTOS) throws SerException {
        return null;
    }

    /**
     * 导出excel
     *
     * @param dto
     * @return
     * @throws SerException
     */
    byte[] exportExcel(BusinessNegotiationDTO dto) throws SerException;

    /**
     * excel模板
     *
     * @return
     * @throws SerException
     */
    byte[] templateExcel() throws SerException;
}