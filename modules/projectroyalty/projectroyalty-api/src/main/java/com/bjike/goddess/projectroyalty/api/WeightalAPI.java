package com.bjike.goddess.projectroyalty.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectroyalty.bo.WeightalBO;
import com.bjike.goddess.projectroyalty.dto.WeightalDTO;
import com.bjike.goddess.projectroyalty.to.WeightalAdjustTO;
import com.bjike.goddess.projectroyalty.to.WeightalTO;

import java.util.List;

/**
 * 项目提成权重分配表业务接口
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-14 01:55 ]
 * @Description: [ 项目提成权重分配表业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface WeightalAPI {

    /**
     * 保存项目提成权重分配表
     *
     * @param to
     * @throws SerException
     */
    default void save(WeightalTO to) throws SerException {
        return;
    }

    /**
     * 修改
     *
     * @param to
     * @throws SerException
     */
    default void update(WeightalTO to) throws SerException {
        return;
    }

    /**
     * 删除
     *
     * @param id
     * @throws SerException
     */
    default void delete(String id) throws SerException {
        return;
    }

    /**
     * 根据id获取业务提成权重分配数据
     */
    default WeightalBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 项目提成权重分配表列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default List<WeightalBO> maps(WeightalDTO dto) throws SerException {
        return null;
    }

    /**
     * 项目提成权重分配表总条数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default Long getTotal(WeightalDTO dto) throws SerException {
        return null;
    }

    /**
     * 比例调整
     *
     * @param to
     * @throws SerException
     */
    default void adjust(WeightalAdjustTO to) throws SerException {
        return;
    }
}