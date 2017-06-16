package com.bjike.goddess.bidding.service;

import com.bjike.goddess.bidding.bo.BidOpeningCollectBO;
import com.bjike.goddess.bidding.bo.BidOpeningInfoBO;
import com.bjike.goddess.bidding.dto.BiddingInfoDTO;
import com.bjike.goddess.bidding.to.BidOpeningInfoTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.bidding.entity.BidOpeningInfo;
import com.bjike.goddess.bidding.dto.BidOpeningInfoDTO;

import java.util.List;

/**
 * 开标信息业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T14:17:14.807 ]
 * @Description: [ 开标信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BidOpeningInfoSer extends Ser<BidOpeningInfo, BidOpeningInfoDTO> {
    /**
     * 开标信息列表总条数
     */
    default Long countBidOpeningInfo(BidOpeningInfoDTO bidOpeningInfoDTO) throws SerException {
        return null;
    }
    /**
     * 一个开标信息
     *
     * @return class BidOpeningInfoBO
     */
    default BidOpeningInfoBO getOne(String id) throws SerException {
        return null;
    }
    /**
     * 开标信息
     *
     * @param bidOpeningInfoDTO 开标信息dto
     * @return class BidOpeningInfoBO
     * @throws SerException
     */
    default List<BidOpeningInfoBO> findListBidOpeningInfo(BidOpeningInfoDTO bidOpeningInfoDTO) throws SerException {
        return null;
    }

    /**
     * 添加开标信息
     *
     * @param bidOpeningInfoTO 开标信息数据to
     * @return class BidOpeningInfoBO
     * @throws SerException
     */
    default BidOpeningInfoBO insertBidOpeningInfo(BidOpeningInfoTO bidOpeningInfoTO) throws SerException {
        return null;
    }

    /**
     * 编辑开标信息
     *
     * @param bidOpeningInfoTO 开标信息数据to
     * @return class BidOpeningInfoBO
     * @throws SerException
     */
    default BidOpeningInfoBO editBidOpeningInfo(BidOpeningInfoTO bidOpeningInfoTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除开标信息
     *
     * @param id
     * @throws SerException
     */
    default void removeBidOpeningInfo(String id) throws SerException {

    }

    /**
     * 搜索
     *
     * @throws SerException
     */
    default List<BidOpeningInfoBO> searchBidOpeningInfo(BidOpeningInfoDTO bidOpeningInfoDTO) throws SerException {
        return null;
    }

    /**
     * 汇总
     *
     * @param cities 地市
     * @return class BidOpeningCollectBO
     * @throws SerException
     */
    default List<BidOpeningCollectBO> collectBidOpening(String [] cities) throws SerException {
        return null;
    }
    /**
     * 地市
     *
     * @return class String
     */
    default List<String> getBidOpeningInfoCities() throws SerException {
        return null;
    }
    /**
     * 导出Excel
     *
     * @param dto
     * @throws SerException
     */
    byte[] exportExcel(BidOpeningInfoDTO dto) throws SerException;
}