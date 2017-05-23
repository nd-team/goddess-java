package com.bjike.goddess.regionalprogresscollect.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.regionalprogresscollect.bo.WeekTargetBO;
import com.bjike.goddess.regionalprogresscollect.dto.WeekTargetDTO;
import com.bjike.goddess.regionalprogresscollect.entity.WeekTarget;
import com.bjike.goddess.regionalprogresscollect.to.StandardTO;
import com.bjike.goddess.regionalprogresscollect.to.WeekTargetTO;

import java.util.List;

/**
 * 周指标业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-17 03:13 ]
 * @Description: [ 周指标业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface WeekTargetSer extends Ser<WeekTarget, WeekTargetDTO> {

    /**
     * 保存
     *
     * @param to 周指标传输对象
     * @return
     * @throws SerException
     */
    default WeekTargetBO save(WeekTargetTO to) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param to 周指标传输对象
     * @return
     * @throws SerException
     */
    default WeekTargetBO update(WeekTargetTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 周指标数据id
     * @return
     * @throws SerException
     */
    default WeekTargetBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 根据id查询周指标数据
     *
     * @param id 周指标数据id
     * @return
     * @throws SerException
     */
    default WeekTargetBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 编辑标准
     *
     * @param to 标准修改传输对象
     * @return
     * @throws SerException
     */
    default WeekTargetBO updateStandard(StandardTO to) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 周指标数据传输对象
     * @return
     * @throws SerException
     */
    default List<WeekTargetBO> maps(WeekTargetDTO dto) throws SerException {
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