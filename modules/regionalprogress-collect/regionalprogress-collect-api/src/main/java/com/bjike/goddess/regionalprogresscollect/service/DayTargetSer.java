package com.bjike.goddess.regionalprogresscollect.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.regionalprogresscollect.bo.DayTargetBO;
import com.bjike.goddess.regionalprogresscollect.dto.DayTargetDTO;
import com.bjike.goddess.regionalprogresscollect.entity.DayTarget;
import com.bjike.goddess.regionalprogresscollect.to.DayTargetTO;
import com.bjike.goddess.regionalprogresscollect.to.StandardTO;

import java.util.List;

/**
 * 日指标业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-17 03:15 ]
 * @Description: [ 日指标业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface DayTargetSer extends Ser<DayTarget, DayTargetDTO> {

    /**
     * 保存
     *
     * @param to 日指标传输对象
     * @return
     * @throws SerException
     */
    default DayTargetBO save(DayTargetTO to) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param to 日指标传输对象
     * @return
     * @throws SerException
     */
    default DayTargetBO update(DayTargetTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 日指标数据id
     * @return
     * @throws SerException
     */
    default DayTargetBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 根据id查询日指标数据
     *
     * @param id 日指标数据id
     * @return
     * @throws SerException
     */
    default DayTargetBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 编辑标准
     *
     * @param to 标准修改传输对象
     * @return
     * @throws SerException
     */
    default DayTargetBO updateStandard(StandardTO to) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 日指标数据传输对象
     * @return
     * @throws SerException
     */
    default List<DayTargetBO> maps(DayTargetDTO dto) throws SerException {
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

}