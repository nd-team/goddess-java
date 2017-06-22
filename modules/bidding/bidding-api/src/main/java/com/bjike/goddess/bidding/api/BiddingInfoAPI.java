package com.bjike.goddess.bidding.api;

import com.bjike.goddess.bidding.bo.BiddingInfoBO;
import com.bjike.goddess.bidding.bo.BiddingInfoCollectBO;
import com.bjike.goddess.bidding.dto.BiddingInfoDTO;
import com.bjike.goddess.bidding.entity.BiddingInfo;
import com.bjike.goddess.bidding.excel.SonPermissionObject;
import com.bjike.goddess.bidding.to.BiddingInfoTO;
import com.bjike.goddess.bidding.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 招标信息业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T13:48:29.975 ]
 * @Description: [ 招标信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BiddingInfoAPI {

    /**
     * 下拉导航权限
     */
    default List<SonPermissionObject> sonPermission() throws SerException {
        return null;
    }

    /**
     * 功能导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }
    /**
     * 招标信息列表总条数
     */
    default Long countBiddingInfo(BiddingInfoDTO biddingInfoDTO) throws SerException {
        return null;
    }
    /**
     * 一个招标信息
     *
     * @return class BiddingInfoBO
     */
    default BiddingInfoBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 招标信息
     *
     * @param biddingInfoDTO 招标信息dto
     * @return class BiddingInfoBO
     * @throws SerException
     */
    default List<BiddingInfoBO> findListBiddingInfo(BiddingInfoDTO biddingInfoDTO) throws SerException {
        return null;
    }

    /**
     * 添加招标信息
     *
     * @param biddingInfoTO 招标信息数据to
     * @return class BiddingInfoBO
     * @throws SerException
     */
    default BiddingInfoBO insertBiddingInfo(BiddingInfoTO biddingInfoTO) throws SerException {
        return null;
    }

    /**
     * 编辑招标信息
     *
     * @param biddingInfoTO 招标信息数据to
     * @return class BiddingInfoBO
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
     * 搜索
     *
     * @throws SerException
     */
    default List<BiddingInfoBO> searchBiddingInfo(BiddingInfoDTO biddingInfoDTO) throws SerException {
        return null;
    }

    /**
     * 汇总
     *
     * @param cities cities
     * @return class BiddingInfoCollectBO
     * @throws SerException
     */
    default List<BiddingInfoCollectBO> collectBiddingInfo(String[] cities) throws SerException {
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
     * 获取项目名称
     *
     * @return class String
     */
    default List<String> getProjectName() throws SerException {
        return null;
    }
    /**
     * 获取招投编号
     *
     * @return class String
     */
    default List<String> getTenderNumber() throws SerException {
        return null;
    }
    /**
     * 导出Excel
     *
     * @param dto
     * @throws SerException
     */
    byte[] exportExcel(BiddingInfoDTO dto) throws SerException;




}