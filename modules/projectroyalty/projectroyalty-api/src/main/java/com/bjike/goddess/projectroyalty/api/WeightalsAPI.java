package com.bjike.goddess.projectroyalty.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectroyalty.bo.CollectBO;
import com.bjike.goddess.projectroyalty.bo.WeightalsBO;
import com.bjike.goddess.projectroyalty.dto.WeightalsDTO;
import com.bjike.goddess.projectroyalty.enums.Type;
import com.bjike.goddess.projectroyalty.to.GuidePermissionTO;
import com.bjike.goddess.projectroyalty.to.WeightalAdjustTO;
import com.bjike.goddess.projectroyalty.to.WeightalsTO;

import java.util.List;

/**
 * 项目提成权重分配表业务接口
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-20 03:34 ]
 * @Description: [ 项目提成权重分配表业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface WeightalsAPI {

    /**
     * 保存项目提成权重分配表
     *
     * @param to
     * @throws SerException
     */
    default void save(WeightalsTO to) throws SerException {
        return;
    }

    /**
     * 修改
     *
     * @param to
     * @throws SerException
     */
    default void update(WeightalsTO to) throws SerException {
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
     *
     * @param id
     * @return
     * @throws SerException
     */
    default WeightalsBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 项目提成权重分配表列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default List<WeightalsBO> maps(WeightalsDTO dto) throws SerException {
        return null;
    }

    /**
     * 项目提成权重分配表总条数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default Long getTotal(WeightalsDTO dto) throws SerException {
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

    /**
     * 获取方案
     *
     * @return
     * @throws SerException
     */
    default List<String> findProgram() throws SerException {
        return null;
    }

    /**
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }

    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    /**
     * 项目提成管理日汇总
     *
     * @return
     * @throws SerException
     */
    default List<CollectBO> dayCollect(String time) throws SerException {
        return null;
    }

    /**
     * 项目提成管理周汇总
     *
     * @param year
     * @param month
     * @param week
     * @return
     * @throws SerException
     */
    default List<CollectBO> weekCollect(Integer year, Integer month, Integer week) throws SerException {
        return null;
    }

    /**
     * 项目提成管理月汇总
     *
     * @param month
     * @return
     * @throws SerException
     */
    default List<CollectBO> monthCollect(String month) throws SerException {
        return null;
    }

    /**
     * 项目提成管理累计汇总
     *
     * @return
     * @throws SerException
     */
    default List<CollectBO> totalCollect() throws SerException {
        return null;
    }

    /**
     * 根据内部项目名称和类型获得业务提成定额
     */
    default Double findAimAmount(String projectName, Type type) throws SerException {
        return null;
    }
}