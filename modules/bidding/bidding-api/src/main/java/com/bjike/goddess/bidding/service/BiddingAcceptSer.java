package com.bjike.goddess.bidding.service;

import com.bjike.goddess.bidding.bo.BiddingAcceptBO;
import com.bjike.goddess.bidding.dto.BiddingAcceptDTO;
import com.bjike.goddess.bidding.entity.BiddingAccept;
import com.bjike.goddess.bidding.excel.SonPermissionObject;
import com.bjike.goddess.bidding.to.BiddingAcceptTO;
import com.bjike.goddess.bidding.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 招标问题受理和处理业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-14 04:41 ]
 * @Description: [ 招标问题受理和处理业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BiddingAcceptSer extends Ser<BiddingAccept, BiddingAcceptDTO> {
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
     * 招标问题受理和处理列表总条数
     */
    default Long count(BiddingAcceptDTO dto) throws SerException {
        return null;
    }

    /**
     * 一个招标问题受理和处理
     *
     * @return class BiddingAcceptBO
     */
    default BiddingAcceptBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 招标问题受理和处理
     *
     * @param dto 招标问题受理和处理dto
     * @return class BiddingAcceptBO
     * @throws SerException
     */
    default List<BiddingAcceptBO> list(BiddingAcceptDTO dto) throws SerException {
        return null;
    }

    /**
     * 添加招标问题受理和处理
     *
     * @param to 招标问题受理和处理数据to
     * @return class BiddingAcceptBO
     * @throws SerException
     */
    default BiddingAcceptBO save(BiddingAcceptTO to) throws SerException {
        return null;
    }

    /**
     * 编辑招标问题受理和处理
     *
     * @param to 招标问题受理和处理数据to
     * @return class BiddingAcceptBO
     * @throws SerException
     */
    default BiddingAcceptBO edit(BiddingAcceptTO to) throws SerException {
        return null;
    }

    /**
     * 根据id删除招标问题受理和处理
     *
     * @param id
     * @throws SerException
     */
    default void remove(String id) throws SerException {

    }

    /**
     * 是否通报
     *
     * @param to 招标问题受理和处理数据to
     * @return class BiddingAcceptBO
     * @throws SerException
     */
    default BiddingAcceptBO notification(BiddingAcceptTO to) throws SerException {
        return null;
    }
}