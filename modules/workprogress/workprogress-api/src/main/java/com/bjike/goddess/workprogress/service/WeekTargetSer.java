package com.bjike.goddess.workprogress.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.workprogress.bo.WeekTargetBO;
import com.bjike.goddess.workprogress.dto.WeekTargetDTO;
import com.bjike.goddess.workprogress.entity.WeekTarget;
import com.bjike.goddess.workprogress.to.GuidePermissionTO;
import com.bjike.goddess.workprogress.to.StandardTO;
import com.bjike.goddess.workprogress.to.WeekTargetTO;

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

    /**
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }

    /**
     * 工能导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    default List<Integer> getStandard() throws SerException {
        return null;
    }
}