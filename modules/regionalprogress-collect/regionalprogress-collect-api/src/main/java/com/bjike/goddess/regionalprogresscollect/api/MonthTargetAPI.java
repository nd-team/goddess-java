package com.bjike.goddess.regionalprogresscollect.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.regionalprogresscollect.bo.MonthTargetBO;
import com.bjike.goddess.regionalprogresscollect.dto.MonthTargetDTO;
import com.bjike.goddess.regionalprogresscollect.to.MonthTargetTO;
import com.bjike.goddess.regionalprogresscollect.to.StandardTO;

import java.util.List;

/**
 * 月指标业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-17 03:13 ]
 * @Description: [ 月指标业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MonthTargetAPI {

    /**
     * 保存
     *
     * @param to 月指标传输对象
     * @return
     * @throws SerException
     */
    default MonthTargetBO save(MonthTargetTO to) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param to 月指标传输对象
     * @return
     * @throws SerException
     */
    default MonthTargetBO update(MonthTargetTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 月指标数据id
     * @return
     * @throws SerException
     */
    default MonthTargetBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 根据id查询月指标数据
     *
     * @param id 月指标数据id
     * @return
     * @throws SerException
     */
    default MonthTargetBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 编辑标准
     *
     * @param to 标准修改传输对象
     * @return
     * @throws SerException
     */
    default MonthTargetBO updateStandard(StandardTO to) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 月指标数据传输对象
     * @return
     * @throws SerException
     */
    default List<MonthTargetBO> maps(MonthTargetDTO dto) throws SerException {
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