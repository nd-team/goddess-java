package com.bjike.goddess.bidding.service;

import com.bjike.goddess.bidding.bo.BiddingInfoBO;
import com.bjike.goddess.bidding.to.BiddingInfoTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.bidding.entity.BiddingInfo;
import com.bjike.goddess.bidding.dto.BiddingInfoDTO;

import java.util.List;

/**
 * 招标信息业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T13:48:29.977 ]
 * @Description: [ 招标信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BiddingInfoSer extends Ser<BiddingInfo, BiddingInfoDTO> {
    /**
     * 获取所有招标信息
     *
     * @param biddingInfoDTO 招标信息dto
     * @return class biddingInfoBO
     * @throws SerException
     */
    default List<BiddingInfoBO> findListBiddingInfo(BiddingInfoDTO biddingInfoDTO) throws SerException {
        return null;
    }

    /**
     * 添加招标信息
     *
     * @param biddingInfoTO 招标信息数据集合
     * @throws SerException
     */
    default BiddingInfoBO insertBiddingInfo(BiddingInfoTO biddingInfoTO) throws SerException {
        return null;
    }

    /**
     * 编辑招标信息
     *
     * @param biddingInfoTO 招标信息数据to
     * @return class biddingInfoBO
     * @throws SerException
     */
    default BiddingInfoBO editBiddingInfo(BiddingInfoTO biddingInfoTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除招标信息
     *
     * @param id
     * @throws SerException
     */
    default void removeBiddingInfo(String id) throws SerException {

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
     * 搜索
     *
     * @param
     * @throws SerException
     */
    default BiddingInfoBO search(String webName, String url, String provinces, String cities) throws SerException {
        return null;
    }

    /**
     * 上传
     */
    default void upload() throws SerException {
        return;

    }

    /**
     * 汇总
     *
     * @param cities cities
     * @return class biddingInfoBO
     * @throws SerException
     */
    default BiddingInfoBO collectBiddingInfo(String [] cities) throws SerException {
        return null;
    }
    /**
     * 获取地市
     *
     * @return class String
     */
    default List<String> getBiddingInfoCities() throws SerException {
        return null;
    }
    /**
     * 发送邮件
     *
     * @return class String
     */
    default BiddingInfoBO sendBiddingInfo(BiddingInfoTO biddingInfoTO) throws SerException {
        return null;
    }


}