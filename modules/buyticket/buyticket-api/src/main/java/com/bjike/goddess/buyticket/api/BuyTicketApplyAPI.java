package com.bjike.goddess.buyticket.api;

import com.bjike.goddess.buyticket.bo.BuyTicketApplyBO;
import com.bjike.goddess.buyticket.dto.BuyTicketApplyDTO;
import com.bjike.goddess.buyticket.enums.AuditType;
import com.bjike.goddess.buyticket.excel.SonPermissionObject;
import com.bjike.goddess.buyticket.to.BuyTicketApplyTO;
import com.bjike.goddess.buyticket.to.BuyGuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 车票购买申请业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-25 04:32 ]
 * @Description: [ 车票购买申请业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BuyTicketApplyAPI {

    /**
     * 下拉导航权限
     */
    default List<SonPermissionObject> sonPermission() throws SerException {

        return null;
    }

    /**
     * 功能导航权限
     */
    default Boolean guidePermission(BuyGuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }
    /**
     * 车票购买申请列表总条数
     */
    default Long countBuyTicketApply(BuyTicketApplyDTO buyTicketApplyDTO) throws SerException {
        return null;
    }
    /**
     * 一个车票购买申请
     * @return class BuyTicketApplyBO
     */
    default BuyTicketApplyBO getOne(String id) throws SerException {return null;}

    /**
     * 基本信息设置
     *
     * @param buyTicketApplyDTO 车票购买申请dto
     * @return class BuyTicketApplyBO
     * @throws SerException
     */
    default List<BuyTicketApplyBO> findListBuyTicketApply(BuyTicketApplyDTO buyTicketApplyDTO) throws SerException {
        return null;
    }

    /**
     * 添加车票购买申请
     *
     * @param buyTicketApplyTO 车票购买申请数据to
     * @return class BuyTicketApplyBO
     * @throws SerException
     */
    default BuyTicketApplyBO insertBuyTicketApply(BuyTicketApplyTO buyTicketApplyTO) throws SerException {
        return null;
    }

    /**
     * 编辑车票购买申请
     *
     * @param buyTicketApplyTO 车票购买申请数据to
     * @return class BuyTicketApplyBO
     * @throws SerException
     */
    default BuyTicketApplyBO editBuyTicketApply(BuyTicketApplyTO buyTicketApplyTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除车票购买申请
     *
     * @param id
     * @throws SerException
     */
    default void removeBuyTicketApply(String id) throws SerException {

    }


    /**
     * 获取所有组织结构中的部门
     *
     * @return
     * @throws SerException
     */
    default List<String> findAddAllDetails() throws SerException {
        return null;
    }

    /**
     * 获取所有用户
     *
     * @return
     * @throws SerException
     */
    default List<String> findallMonUser() throws SerException {
        return null;
    }

    /**
     * 规划模块审核
     *
     * @return class BuyTicketApplyBO
     */
    default void planAuditBuyTicketApply(String id,AuditType planAuditOpinion) throws SerException {
        return;
    }

    /**
     * 福利模块审核
     *
     * @return class BuyTicketApplyBO
     */
    default void welfAuditBuyTicketApply(String id,AuditType welfAuditOpinion) throws SerException {
        return;
    }

}