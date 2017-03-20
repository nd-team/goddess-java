package com.bjike.goddess.supplier.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.supplier.bo.ContactSituationBO;
import com.bjike.goddess.supplier.dto.ContactSituationDTO;
import com.bjike.goddess.supplier.entity.ContactSituation;

import java.util.List;

/**
 * 联系情况业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-20T11:03:21.703 ]
 * @Description: [ 联系情况业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ContactSituationSer extends Ser<ContactSituation, ContactSituationDTO> {

    /**
     * 根据供应商基本信息ID查询联系情况
     *
     * @param info_id 供应商基本信息ID
     * @return
     * @throws SerException
     */
    default List<ContactSituationBO> findByInformation(String info_id) throws SerException {
        return null;
    }

}