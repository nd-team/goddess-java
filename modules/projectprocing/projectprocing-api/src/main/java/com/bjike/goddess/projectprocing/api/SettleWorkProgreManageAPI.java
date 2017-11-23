package com.bjike.goddess.projectprocing.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectprocing.bo.SettleWorkProgreManageBO;
import com.bjike.goddess.projectprocing.dto.SettleWorkProgreManageDTO;
import com.bjike.goddess.projectprocing.to.SettleWorkProgreManageTO;

import java.util.List;

/**
 * 结算工作进度管理业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 03:09 ]
 * @Description: [ 结算工作进度管理业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SettleWorkProgreManageAPI {
    /**
     * 结算工作进度管理总条数
     */
    default Long countSettleWork(SettleWorkProgreManageDTO settleWorkProgreManageDTO) throws SerException {
        return null;
    }

    /**
     * 根据id获取结算工作进度管理
     *
     * @return class SettleWorkProgreManageBO
     */
    default SettleWorkProgreManageBO getOneById(String id) throws SerException {
        return null;
    }

    /**
     * 结算工作进度管理列表
     *
     * @return class SettleWorkProgreManageBO
     */
    default List<SettleWorkProgreManageBO> listSettleWork(SettleWorkProgreManageDTO settleWorkProgreManageDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param settleWorkProgreManageTO 结算工作进度管理
     * @return class SettleWorkProgreManageBO
     */
    default SettleWorkProgreManageBO addSettleWork(SettleWorkProgreManageTO settleWorkProgreManageTO) throws SerException {
        return null;
    }

    /**
     * 重新分配负责人
     *
     * @param responsible 责任人
     * @param id          id
     */
    default void redistribution(String id, String responsible) throws SerException {
        return;
    }

    /**
     * 删除
     *
     * @param id id
     */
    default void deleteSettleWork(String id) throws SerException {
        return;
    }
}