package com.bjike.goddess.qualifications.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.qualifications.bo.QualificationsHandlePlanBO;
import com.bjike.goddess.qualifications.to.QualificationsHandlePlanTO;

import java.util.List;

/**
 * 资质办理计划业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 04:46 ]
 * @Description: [ 资质办理计划业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface QualificationsHandlePlanAPI {

    /**
     * 保存
     *
     * @param to 资质办理计划传输对象
     * @return
     * @throws SerException
     */
    default QualificationsHandlePlanBO save(QualificationsHandlePlanTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to 资质办理计划传输对象
     * @return
     * @throws SerException
     */
    default QualificationsHandlePlanBO update(QualificationsHandlePlanTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 资质办理计划id
     * @return
     * @throws SerException
     */
    default QualificationsHandlePlanBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 根据资质办理查询计划
     *
     * @param handle_id 资质办理ID
     * @return
     * @throws SerException
     */
    default List<QualificationsHandlePlanBO> findByHandle(String handle_id) throws SerException {
        return null;
    }

}