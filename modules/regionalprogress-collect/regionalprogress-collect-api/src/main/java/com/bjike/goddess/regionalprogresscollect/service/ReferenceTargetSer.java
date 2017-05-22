package com.bjike.goddess.regionalprogresscollect.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.regionalprogresscollect.bo.ReferenceTargetBO;
import com.bjike.goddess.regionalprogresscollect.dto.ReferenceTargetDTO;
import com.bjike.goddess.regionalprogresscollect.entity.ReferenceTarget;
import com.bjike.goddess.regionalprogresscollect.to.FindTO;
import com.bjike.goddess.regionalprogresscollect.to.ReferenceTargetTO;

import java.util.List;

/**
 * 参考指标业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-17 02:56 ]
 * @Description: [ 参考指标业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ReferenceTargetSer extends Ser<ReferenceTarget, ReferenceTargetDTO> {

    /**
     * 保存
     *
     * @param to 参考指标传输对象
     * @return
     * @throws SerException
     */
    default ReferenceTargetBO save(ReferenceTargetTO to) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param to 参考指标传输对象
     * @return
     * @throws SerException
     */
    default ReferenceTargetBO update(ReferenceTargetTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 参考指标数据id
     * @return
     * @throws SerException
     */
    default ReferenceTargetBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 根据id获取参考指标数据
     *
     * @param id 参考指标数据id
     * @return
     * @throws SerException
     */
    default ReferenceTargetBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 参考指标数据传输对象
     * @return
     * @throws SerException
     */
    default List<ReferenceTargetBO> maps(ReferenceTargetDTO dto) throws SerException {
        return null;
    }

    /**
     * 获取总条数
     *
     * @return
     * @throws SerException
     */
    default Long getTotal() throws SerException {
        return null;
    }

    /**
     * 根据查询条件查询参考指标数据
     *
     * @param to 查询数据传输对象
     * @return
     * @throws SerException
     */
    default ReferenceTargetBO findByTO(FindTO to) throws SerException {
        return null;
    }

    /**
     * 根据查询条件查询参考指标数据
     *
     * @param to 查询数据传输对象
     * @return
     * @throws SerException
     */
    default List<ReferenceTargetBO> findListByTO(FindTO to) throws SerException {
        return null;
    }

}