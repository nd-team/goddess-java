package com.bjike.goddess.bidding.api;

import com.bjike.goddess.bidding.bo.BidOpeningInfoBO;
import com.bjike.goddess.bidding.dto.BidOpeningInfoDTO;
import com.bjike.goddess.bidding.entity.BidOpeningInfo;
import com.bjike.goddess.bidding.to.BidOpeningInfoTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 开标信息业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T14:17:14.806 ]
 * @Description: [ 开标信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BidOpeningInfoAPI {
    /**
     * 获取所有开标信息
     *
     * @param bidOpeningInfoDTO 开标信息dto
     * @return class bidOpeningInfoBO
     * @throws SerException
     */
    default List<BidOpeningInfoBO> findListBidOpeningInfo(BidOpeningInfoDTO bidOpeningInfoDTO) throws SerException {
        return null;
    }

    /**
     * 添加开标信息
     *
     * @param bidOpeningInfoTO 开标信息数据集合to
     * @throws SerException
     */
    default BidOpeningInfoBO insertBidOpeningInfo(BidOpeningInfoTO bidOpeningInfoTO) throws SerException {
        return null;
    }

    /**
     * 编辑开标信息
     *
     * @param bidOpeningInfoTO 开标信息数据to
     * @return class bidOpeningInfoBO
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
     * @param competitive
     * @throws SerException
     */
    default BidOpeningInfoBO search(String competitive) throws SerException {
        return null;
    }
    /**
     * 发送邮件
     *
     * @return class String
     */
    default BidOpeningInfoBO sendBidOpeningInfo(BidOpeningInfoTO bidOpeningInfoTO) throws SerException {
        return null;
    }

    /**
     * 导出
     *
     * @param projectName
     * @throws SerException
     */
    default String exportExcel(String projectName) throws SerException {
        return null;
    }
    /**
     * 汇总
     *
     * @param cities cities
     * @return class bidOpeningInfoBO
     * @throws SerException
     */
    default BidOpeningInfoBO collectBidOpeningInfo(String cities) throws SerException {
        return null;
    }

}