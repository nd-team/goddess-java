package com.bjike.goddess.buyticket.service;

import com.bjike.goddess.buyticket.bo.BuyTicketStandardBO;
import com.bjike.goddess.buyticket.dto.BuyTicketStandardDTO;
import com.bjike.goddess.buyticket.entity.BuyTicketStandard;
import com.bjike.goddess.buyticket.to.BuyTicketStandardTO;
import com.bjike.goddess.buyticket.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 购票标准业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-25 06:40 ]
 * @Description: [ 购票标准业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BuyTicketStandardSer extends Ser<BuyTicketStandard, BuyTicketStandardDTO> {

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
     * 购票标准列表总条数
     */
    default Long countBuyTicketStandard(BuyTicketStandardDTO buyTicketStandardDTO) throws SerException {
        return null;
    }

    /**
     * 一个购票标准
     *
     * @return class BuyTicketStandardBO
     */
    default BuyTicketStandardBO getOne(String id) throws SerException {
        return null;
    }


    /**
     * 购票标准
     *
     * @param buyTicketStandardDTO 购票标准dto
     * @return class BuyTicketStandardBO
     * @throws SerException
     */
    default List<BuyTicketStandardBO> findListBuyTicketStandard(BuyTicketStandardDTO buyTicketStandardDTO) throws SerException {
        return null;
    }

    /**
     * 添加购票标准
     *
     * @param buyTicketStandardTO 购票标准数据to
     * @return class BuyTicketStandardBO
     * @throws SerException
     */
    default BuyTicketStandardBO insertBuyTicketStandard(BuyTicketStandardTO buyTicketStandardTO) throws SerException {
        return null;
    }

    /**
     * 编辑购票标准
     *
     * @param buyTicketStandardTO 购票标准数据to
     * @return class BuyTicketStandardBO
     * @throws SerException
     */
    default BuyTicketStandardBO editBuyTicketStandard(BuyTicketStandardTO buyTicketStandardTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除购票标准
     *
     * @param id
     * @throws SerException
     */
    default void removeBuyTicketStandard(String id) throws SerException {

    }
}