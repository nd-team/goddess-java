package com.bjike.goddess.reimbursementprepare.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.reimbursementprepare.bo.DifferencesBO;
import com.bjike.goddess.reimbursementprepare.bo.WaitPayBO;
import com.bjike.goddess.reimbursementprepare.dto.WaitPayDTO;
import com.bjike.goddess.reimbursementprepare.entity.WaitPay;
import com.bjike.goddess.reimbursementprepare.to.GuidePermissionTO;
import com.bjike.goddess.reimbursementprepare.to.WaitPayTO;

import java.util.List;
import java.util.Set;

/**
 * 等待付款业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-03 03:01 ]
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
     * 等待付款列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<WaitPayBO> waitPays(WaitPayDTO dto) throws SerException;

    /**
     * 付款
     *
     * @param to
     * @throws SerException
     */
    void pay(WaitPayTO to) throws SerException;

    /**
     * 已付款列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<WaitPayBO> pays(WaitPayDTO dto) throws SerException;

    /**
     * 导出等待付款
     *
     * @param dto
     * @return
     * @throws SerException
     */
    byte[] waitPayExport(WaitPayDTO dto) throws SerException;

    /**
     * 导出已付款
     *
     * @param dto
     * @return
     * @throws SerException
     */
    byte[] payExport(WaitPayDTO dto) throws SerException;

    /**
     * 分条件汇总
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<WaitPayBO> conditionsCount(WaitPayDTO dto) throws SerException;

    /**
     * 资金准备与实际支付差异
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<DifferencesBO> difference(WaitPayDTO dto) throws SerException;

    /**
     * 获取所有项目组
     *
     * @return
     * @throws SerException
     */
    Set<String> allProjectGroups() throws SerException;

    /**
     * 获取所有地区
     *
     * @return
     * @throws SerException
     */
    Set<String> allAreas() throws SerException;

    /**
     * 已付款记录数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long payCount(WaitPayDTO dto) throws SerException;

    /**
     * 等待付款记录数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long waitPayCount(WaitPayDTO dto) throws SerException;

    /**
     * 通过id查找等待付款
     *
     * @param id
     * @return
     * @throws SerException
     */
    WaitPayBO findWait(String id) throws SerException;

}