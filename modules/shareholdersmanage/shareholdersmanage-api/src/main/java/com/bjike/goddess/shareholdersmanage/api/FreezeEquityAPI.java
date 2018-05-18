package com.bjike.goddess.shareholdersmanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.shareholdersmanage.bo.FreezeEquityBO;
import com.bjike.goddess.shareholdersmanage.bo.FreezeEquityLinkDateBO;
import com.bjike.goddess.shareholdersmanage.dto.FreezeEquityDTO;
import com.bjike.goddess.shareholdersmanage.to.FreezeEquityTO;
import com.bjike.goddess.shareholdersmanage.to.GuidePermissionTO;

import java.util.List;

/**
 * 冻结股权业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 05:39 ]
 * @Description: [ 冻结股权业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface FreezeEquityAPI {
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
     * 冻结股权列表总条数
     */
    default Long countFreeze(FreezeEquityDTO freezeEquityDTO) throws SerException {
        return null;
    }

    /**
     * 一个冻结股权
     *
     * @return class FreezeEquityBO
     */
    default FreezeEquityBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 冻结股权列表
     *
     * @param freezeEquityDTO 冻结股权dto
     * @return class FreezeEquityBO
     * @throws SerException
     */
    default List<FreezeEquityBO> findList(FreezeEquityDTO freezeEquityDTO) throws SerException {
        return null;
    }

    /**
     * 冻结股权添加
     *
     * @param freezeEquityTO 冻结股权to
     * @throws SerException
     */
    default FreezeEquityBO save(FreezeEquityTO freezeEquityTO) throws SerException {
        return null;
    }

    /**
     * 冻结股权编辑
     *
     * @param freezeEquityTO 冻结股权to
     * @throws SerException
     */
    default FreezeEquityBO edit(FreezeEquityTO freezeEquityTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除冻结股权
     *
     * @param id
     * @throws SerException
     */
    default void delete(String id) throws SerException {
        return;
    }

    /**
     * 根据类型获取持股数量总和金额总和
     *
     * @param equityType
     * @throws SerException
     */
    default FreezeEquityLinkDateBO totalNum(String equityType) throws SerException {
        return null;
    }
}