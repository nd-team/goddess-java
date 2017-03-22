package com.bjike.goddess.supplier.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.supplier.bo.CooperationSituationBO;
import com.bjike.goddess.supplier.dto.CooperationSituationDTO;
import com.bjike.goddess.supplier.entity.CooperationSituation;
import com.bjike.goddess.supplier.to.ContactSituationTO;

import java.util.List;

/**
 * 合作情况业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-20T11:05:37.657 ]
 * @Description: [ 合作情况业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CooperationSituationSer extends Ser<CooperationSituation, CooperationSituationDTO> {

    /**
     * 根据供应商基本信息ID查询合作情况
     *
     * @param info_id 供应商基本信息ID
     * @return
     * @throws SerException
     */
    default List<CooperationSituationBO> findByInformation(String info_id) throws SerException {
        return null;
    }

    /**
     * 保存供应商合作情况数据
     *
     * @param to 供应商合作情况传输对象
     * @return
     * @throws SerException
     */
    default CooperationSituationBO save(ContactSituationTO to) throws SerException {
        return null;
    }

    /**
     * 修改供应商合作情况数据
     *
     * @param to 供应商合作情况传输对象
     * @return
     * @throws SerException
     */
    default CooperationSituationBO update(ContactSituationTO to) throws SerException {
        return null;
    }

    /**
     * 删除供应商合作情况数据
     *
     * @param id 供应商合作情况id
     * @return
     * @throws SerException
     */
    default CooperationSituationBO delete(String id) throws SerException {
        return null;
    }

}