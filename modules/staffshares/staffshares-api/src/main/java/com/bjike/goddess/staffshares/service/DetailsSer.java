package com.bjike.goddess.staffshares.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.staffshares.bo.DetailsBO;
import com.bjike.goddess.staffshares.dto.DetailsDTO;
import com.bjike.goddess.staffshares.entity.Details;
import com.bjike.goddess.staffshares.to.GuidePermissionTO;
import com.bjike.goddess.staffshares.to.PurchaseTO;
import com.bjike.goddess.staffshares.to.SellscheduleTO;

import java.util.List;

/**
 * 交易详情业务接口
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-08-05 08:54 ]
 * @Description: [ 交易详情业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface DetailsSer extends Ser<Details, DetailsDTO> {
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
     * 交易详情列表
     *
     * @param dto
     * @return
     */
    List<DetailsBO> listDetail(DetailsDTO dto) throws SerException;

    /**
     * 获得一条交易详情数据
     *
     * @param id
     * @return
     * @throws SerException
     */
    DetailsBO getDetailById(String id) throws SerException;

    /**
     * 获取交易详情总条数
     *
     * @param detailsDTO
     * @return
     * @throws SerException
     */
    Long getTotal(DetailsDTO detailsDTO) throws SerException;

    /**
     * 交易详情中的购买
     *
     * @param to
     */
    void buy(PurchaseTO to) throws SerException;

    /**
     * 回收
     *
     * @param id
     * @throws SerException
     */
    void recovery(String id) throws SerException;

    /**
     * 出售
     *
     * @param to
     * @throws SerException
     */
    void sell(SellscheduleTO to) throws SerException;
}