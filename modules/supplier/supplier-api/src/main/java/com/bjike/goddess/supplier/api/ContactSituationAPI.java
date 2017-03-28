package com.bjike.goddess.supplier.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.supplier.bo.ContactSituationBO;
import com.bjike.goddess.supplier.to.ContactSituationTO;

import java.util.List;

/**
 * 联系情况业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-20T11:03:21.702 ]
 * @Description: [ 联系情况业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ContactSituationAPI {

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

    /**
     * 保存供应商联系情况数据
     *
     * @param to 供应商联系情况传输对象
     * @return
     * @throws SerException
     */
    default ContactSituationBO save(ContactSituationTO to) throws SerException {
        return null;
    }

    /**
     * 修改供应商联系情况数据
     *
     * @param to 供应商联系情况传输对象
     * @return
     * @throws SerException
     */
    default ContactSituationBO update(ContactSituationTO to) throws SerException {
        return null;
    }

    /**
     * 删除供应商联系情况数据
     *
     * @param id 供应商联系情况id
     * @return
     * @throws SerException
     */
    default ContactSituationBO delete(String id) throws SerException {
        return null;
    }

}