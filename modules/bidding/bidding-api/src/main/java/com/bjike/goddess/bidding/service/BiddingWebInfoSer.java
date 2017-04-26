package com.bjike.goddess.bidding.service;

import com.bjike.goddess.bidding.bo.BiddingWebInfoBO;
import com.bjike.goddess.bidding.to.BiddingWebInfoTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.bidding.entity.BiddingWebInfo;
import com.bjike.goddess.bidding.dto.BiddingWebInfoDTO;

import java.util.List;

/**
 * 招投标网站信息业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T10:15:43.322 ]
 * @Description: [ 招投标网站信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BiddingWebInfoSer extends Ser<BiddingWebInfo, BiddingWebInfoDTO> {
    /**
     * 招投标网站信息列表总条数
     */
    default Long countBiddingWebInfo(BiddingWebInfoDTO biddingWebInfoDTO) throws SerException {
        return null;
    }
    /**
     * 招投标网站信息
     *
     * @param biddingWebInfoDTO 招投标网站信息dto
     * @return class BiddingWebInfoBO
     * @throws SerException
     */
    default List<BiddingWebInfoBO> findListBiddingWebInfo(BiddingWebInfoDTO biddingWebInfoDTO) throws SerException {
        return null;
    }

    /**
     * 添加招投标网站信息
     *
     * @param biddingWebInfoTO 招投标网站信息数据to
     * @return class BiddingWebInfoBO
     * @throws SerException
     */
    default BiddingWebInfoBO insertBiddingWebInfo(BiddingWebInfoTO biddingWebInfoTO) throws SerException {
        return null;
    }

    /**
     * 编辑招投标网站信息
     *
     * @param biddingWebInfoTO 招投标网站信息数据to
     * @return class BiddingWebInfoBO
     * @throws SerException
     */
    default BiddingWebInfoBO editBiddingWebInfo(BiddingWebInfoTO biddingWebInfoTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除招投标网站信息
     *
     * @param id
     * @throws SerException
     */
    default void removeBiddingWebInfo(String id) throws SerException {

    }
}