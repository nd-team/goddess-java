package com.bjike.goddess.bidding.api;

import com.bjike.goddess.bidding.bo.BiddingWebInfoBO;
import com.bjike.goddess.bidding.dto.BiddingWebInfoDTO;
import com.bjike.goddess.bidding.entity.BiddingWebInfo;
import com.bjike.goddess.bidding.to.BiddingWebInfoTO;
import com.bjike.goddess.bidding.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 招投标网站信息业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T10:15:43.321 ]
 * @Description: [ 招投标网站信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BiddingWebInfoAPI {
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
     * 招投标网站信息列表总条数
     */
    default Long countBiddingWebInfo(BiddingWebInfoDTO biddingWebInfoDTO) throws SerException {
        return null;
    }
    /**
     * 一个招投标网站信息
     *
     * @return class BiddingWebInfoBO
     */
    default BiddingWebInfoBO getOne(String id) throws SerException {
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
    /**
     * 获取网站名称
     *
     * @return class String
     */
    default List<String> getWebName() throws SerException {
        return null;
    }
    /**
     * 获取网址
     *
     * @return class String
     */
    default List<String> getUrl() throws SerException {
        return null;
    }
    /**
     * 根据网站名称获取网站信息
     *
     * @return class String
     */
    default BiddingWebInfoBO getWebInfo(String webName) throws SerException {
        return null;
    }


}