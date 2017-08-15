package com.bjike.goddess.staffshares.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffshares.bo.BuyscheduleBO;
import com.bjike.goddess.staffshares.bo.BuyscheduleCollectBO;
import com.bjike.goddess.staffshares.dto.BuyscheduleDTO;
import com.bjike.goddess.staffshares.to.BuyscheduleTO;
import com.bjike.goddess.staffshares.to.GuidePermissionTO;

import java.util.List;

/**
 * 买入记录表业务接口
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-08-04 10:09 ]
 * @Description: [ 买入记录表业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BuyscheduleAPI {

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
     * 买入记录表列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default List<BuyscheduleBO> maps(BuyscheduleDTO dto) throws SerException {
        return null;
    }

    /**
     * 根据id获取买入记录表数据
     *
     * @param id
     * @return
     * @throws SerException
     */
    default BuyscheduleBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 获取总条数
     *
     * @param buyscheduleDTO
     * @return
     * @throws SerException
     */
    default Long getTotal(BuyscheduleDTO buyscheduleDTO) throws SerException {
        return null;
    }

    /**
     * 出售
     *
     * @param buyscheduleTO
     * @throws SerException
     */
    default void sell(BuyscheduleTO buyscheduleTO) throws SerException {
    }

    /**
     * 汇总
     *
     * @return
     * @throws SerException
     */
    default List<BuyscheduleCollectBO> collect() throws SerException {
        return null;
    }
}