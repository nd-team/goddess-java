package com.bjike.goddess.outcarfare.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.outcarfare.bo.ArrivalCountBO;
import com.bjike.goddess.outcarfare.bo.CarUserCountBO;
import com.bjike.goddess.outcarfare.bo.DriverCountBO;
import com.bjike.goddess.outcarfare.bo.WaitPayBO;
import com.bjike.goddess.outcarfare.dto.WaitPayDTO;
import com.bjike.goddess.outcarfare.entity.WaitPay;
import com.bjike.goddess.outcarfare.to.GuidePermissionTO;
import com.bjike.goddess.outcarfare.to.WaitPayTO;

import java.util.List;
import java.util.Set;

/**
 * 等待付款业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-05 03:11 ]
 * @Description: [ 等待付款业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface WaitPaySer extends Ser<WaitPay, WaitPayDTO> {
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
     * 添加
     *
     * @param to 等待付款信息
     * @return class WaitPayBO
     * @throws SerException
     */
    default WaitPayBO save(WaitPayTO to) throws SerException {
        return null;
    }
    /**
     * 编辑
     *
     * @param to 等待付款信息
     * @return class WaitPayBO
     * @throws SerException
     */
    default WaitPayBO edit(WaitPayTO to) throws SerException {
        return null;
    }

    /**
     * 确认付款
     *
     * @param to 等待付款信息
     * @throws SerException
     */
    default void pay(WaitPayTO to) throws SerException {
    }

    /**
     * 删除
     *
     * @param id 等待付款信息id
     * @throws SerException
     */
    default void delete(String id) throws SerException {

    }

    /**
     * 等待付款列表
     *
     * @param dto 等待付款分页信息
     * @return class WaitPayBO
     * @throws SerException
     */
    default List<WaitPayBO> list(WaitPayDTO dto) throws SerException {
        return null;

    }

    /**
     * 通过id查找
     *
     * @param id 等待付款信息id
     * @return class WaitPayBO
     * @throws SerException
     */
    default WaitPayBO findByID(String id) throws SerException {
        return null;
    }

    /**
     * 查找已付款列表
     *
     * @param dto dto
     * @return class WaitPayBO
     * @throws SerException
     */
    default List<WaitPayBO> pays(WaitPayDTO dto) throws SerException {
        return null;
    }

    /**
     * 查找等待付款总记录数
     *
     * @param dto dto
     * @throws SerException
     */
    default Long waitCountSum(WaitPayDTO dto) throws SerException {
        return null;
    }

    /**
     * 查找已付款总记录数
     *
     * @param dto dto
     * @throws SerException
     */
    default Long payCountSum(WaitPayDTO dto) throws SerException {
        return null;
    }

    /**
     * 删除列表
     * @param dto
     * @return
     * @throws SerException
     */
    List<WaitPayBO> delList(WaitPayDTO dto) throws SerException;

    /**
     * 撤销删除
     * @param id
     * @throws SerException
     */
    void reback(String id) throws SerException;

    /**
     * 删除列表总条数
     * @param dto
     * @return
     * @throws SerException
     */
    Long delCount(WaitPayDTO dto) throws SerException;

    /**
     * 定时检测删除
     * @throws SerException
     */
    void quartz() throws SerException;

    /**
     * 已付款的司机汇总
     * @param dto
     * @return
     * @throws SerException
     */
    List<DriverCountBO> driverCount(WaitPayDTO dto) throws SerException;

    /**
     * 已付款的地区汇总
     * @param dto
     * @return
     * @throws SerException
     */
    List<ArrivalCountBO> arrivalCount(WaitPayDTO dto) throws SerException;

    /**
     * 已付款的用车人汇总
     * @param dto
     * @return
     * @throws SerException
     */
    List<CarUserCountBO> carUserCount(WaitPayDTO dto) throws SerException;

    /**
     * 等待付款的司机汇总
     * @param dto
     * @return
     * @throws SerException
     */
    List<DriverCountBO> waitDriverCount(WaitPayDTO dto) throws SerException;

    /**
     * 等待付款的地区汇总
     * @param dto
     * @return
     * @throws SerException
     */
    List<ArrivalCountBO> waitArrivalCount(WaitPayDTO dto) throws SerException;

    /**
     * 等待付款的用车人汇总
     * @param dto
     * @return
     * @throws SerException
     */
    List<CarUserCountBO> waitCarUserCount(WaitPayDTO dto) throws SerException;

    /**
     * 等待付款汇总明细
     * @param dto
     * @return
     * @throws SerException
     */
    List<WaitPayBO> waitDetails(WaitPayDTO dto) throws SerException;

    /**
     * 已付款汇总明细
     * @param dto
     * @return
     * @throws SerException
     */
    List<WaitPayBO> details(WaitPayDTO dto) throws SerException;

    /**
     * 所有司机
     * @return
     * @throws SerException
     */
    Set<String> findAllDrivers() throws SerException;

    /**
     * 所有地区
     * @return
     * @throws SerException
     */
    Set<String> findAllArrivals() throws SerException;

    /**
     * 所有用车人
     * @return
     * @throws SerException
     */
    Set<String> findAllCarUsers() throws SerException;
}