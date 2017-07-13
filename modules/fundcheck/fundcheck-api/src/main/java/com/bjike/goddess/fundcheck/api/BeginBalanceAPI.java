package com.bjike.goddess.fundcheck.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.fundcheck.bo.BeginBalanceBO;
import com.bjike.goddess.fundcheck.dto.BeginBalanceDTO;
import com.bjike.goddess.fundcheck.to.BeginBalanceTO;
import com.bjike.goddess.fundcheck.to.GuidePermissionTO;

import java.util.List;

/**
 * 期初余额业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-05 03:34 ]
 * @Description: [ 期初余额业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BeginBalanceAPI {
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
     * 期初余额列表总条数
     */
    default Long count(BeginBalanceDTO beginBalanceDTO) throws SerException {
        return null;
    }

    /**
     * 一个期初余额
     *
     * @return class BeginBalanceBO
     */
    default BeginBalanceBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 期初余额
     *
     * @param beginBalanceDTO 期初余额dto
     * @return class BeginBalanceBO
     * @throws SerException
     */
    default List<BeginBalanceBO> findList(BeginBalanceDTO beginBalanceDTO) throws SerException {
        return null;
    }

    /**
     * 添加期初余额
     *
     * @param beginBalanceTO 期初余额数据to
     * @return class BeginBalanceBO
     * @throws SerException
     */
    default BeginBalanceBO insert(BeginBalanceTO beginBalanceTO) throws SerException {
        return null;
    }

    /**
     * 编辑期初余额
     *
     * @param beginBalanceTO 期初余额数据to
     * @return class BeginBalanceBO
     * @throws SerException
     */
    default BeginBalanceBO edit(BeginBalanceTO beginBalanceTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除期初余额
     *
     * @param id
     * @throws SerException
     */
    default void remove(String id) throws SerException {

    }
    /**
     * 根据日期查询期初余额
     *
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @throws SerException
     */
    default List<BeginBalanceBO> getBeginBalace(String startTime,String endTime) throws SerException {
        return null;
    }
}