package com.bjike.goddess.qualifications.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.qualifications.bo.AuditMaterialBO;
import com.bjike.goddess.qualifications.to.AuditMaterialTO;

import java.util.List;

/**
 * 审核资料业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 06:44 ]
 * @Description: [ 审核资料业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AuditMaterialAPI {

    /**
     * 保存
     *
     * @param to 审核资料传输对象
     * @return
     * @throws SerException
     */
    default AuditMaterialBO save(AuditMaterialTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to 审核资料传输对象
     * @return
     * @throws SerException
     */
    default AuditMaterialBO update(AuditMaterialTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 审核资料ID
     * @return
     * @throws SerException
     */
    default AuditMaterialBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 获取全部
     *
     * @return
     * @throws SerException
     */
    default List<AuditMaterialBO> all() throws SerException {
        return null;
    }

}