package com.bjike.goddess.housepay.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.housepay.bo.AreaCollectBO;
import com.bjike.goddess.housepay.bo.CollectDetailBO;
import com.bjike.goddess.housepay.bo.ProjectCollectBO;
import com.bjike.goddess.housepay.bo.WaitPayBO;
import com.bjike.goddess.housepay.dto.WaitPayDTO;
import com.bjike.goddess.housepay.excel.SonPermissionObject;
import com.bjike.goddess.housepay.to.CollectAreaTO;
import com.bjike.goddess.housepay.to.CollectProjectTO;
import com.bjike.goddess.housepay.to.GuidePermissionTO;
import com.bjike.goddess.housepay.to.WaitPayTO;

import java.util.List;

/**
 * 等待付款业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-13 09:11 ]
 * @Description: [ 等待付款业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface WaitPayAPI {
    /**
     * 下拉导航权限
     */
    default List<SonPermissionObject> sonPermission() throws SerException {
        return null;
    }

    /**
     * 功能导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    /**
     * 等待付款列表总条数
     */
    default Long countWaitPay(WaitPayDTO waitPayDTO) throws SerException {
        return null;
    }

    /**
     * 一个等待付款
     *
     * @return class WaitPayBO
     */
    default WaitPayBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 等待付款
     *
     * @param waitPayDTO 等待付款dto
     * @return class WaitPayBO
     * @throws SerException
     */
    default List<WaitPayBO> findListWaitPay(WaitPayDTO waitPayDTO) throws SerException {
        return null;
    }

    /**
     * 添加等待付款
     *
     * @param waitPayTO 等待付款数据to
     * @return class WaitPayBO
     * @throws SerException
     */
    default WaitPayBO insertWaitPay(WaitPayTO waitPayTO) throws SerException {
        return null;
    }

    /**
     * 编辑等待付款
     *
     * @param waitPayTO 等待付款数据to
     * @return class WaitPayBO
     * @throws SerException
     */
    default WaitPayBO editWaitPay(WaitPayTO waitPayTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除等待付款
     *
     * @param id
     * @throws SerException
     */
    default void removeWaitPay(String id) throws SerException {

    }

    /**
     * 付款
     *
     * @param id
     * @throws SerException
     */
    default void payment(String id) throws SerException {
    }

    /**
     * 地区汇总
     *
     * @param to to
     * @return class AreaCollectBO
     * @throws SerException
     */
    default List<AreaCollectBO> collectArea(CollectAreaTO to) throws SerException {
        return null;
    }

    /**
     * 地区汇总详情
     *
     * @param to to
     * @return class CollectDetailBO
     * @throws SerException
     */
    default List<CollectDetailBO> collectAreaDetail(CollectAreaTO to) throws SerException {
        return null;
    }


    /**
     * 获取地区
     *
     * @return class String
     */
    default List<String> getAreas() throws SerException {
        return null;
    }

    /**
     * 项目汇总
     *
     * @param to to
     * @return class ProjectCollectBO
     * @throws SerException
     */
    default List<ProjectCollectBO> collectProject(CollectProjectTO to) throws SerException {
        return null;
    }

    /**
     * 项目汇总详情
     *
     * @param to to
     * @return class CollectDetailBO
     * @throws SerException
     */
    default List<CollectDetailBO> collectProjectDatail(CollectProjectTO to) throws SerException {
        return null;
    }

    /**
     * 获取项目
     *
     * @return class String
     */
    default List<String> getProject() throws SerException {
        return null;
    }
    /**
     * 计算合计
     *
     * @return class Double
     */
    default Double calculate(WaitPayTO to) throws SerException {
        return null;
    }
    /**
     * 获取所有年份
     *
     */
    default List<String> yearList( ) throws SerException {

        return null;
    }

}