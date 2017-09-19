package com.bjike.goddess.bidding.service;

import com.bjike.goddess.bidding.bo.BiddingTypeBO;
import com.bjike.goddess.bidding.dto.BiddingTypeDTO;
import com.bjike.goddess.bidding.entity.BiddingType;
import com.bjike.goddess.bidding.to.BiddingTypeTO;
import com.bjike.goddess.bidding.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 招投标类型业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-14 04:24 ]
 * @Description: [ 招投标类型业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BiddingTypeSer extends Ser<BiddingType, BiddingTypeDTO> {

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
     * 招投标类型列表总条数
     */
    default Long count(BiddingTypeDTO dto) throws SerException {
        return null;
    }

    /**
     * 一个招投标类型
     *
     * @return class BiddingTypeBO
     */
    default BiddingTypeBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 招投标类型
     *
     * @param dto 招投标类型dto
     * @return class BiddingAcceptBO
     * @throws SerException
     */
    default List<BiddingTypeBO> list(BiddingTypeDTO dto) throws SerException {
        return null;
    }

    /**
     * 添加招投标类型
     *
     * @param to 招投标类型数据to
     * @return class BiddingTypeBO
     * @throws SerException
     */
    default BiddingTypeBO save(BiddingTypeTO to) throws SerException {
        return null;
    }

    /**
     * 编辑招投标类型
     *
     * @param to 招投标类型数据to
     * @return class BiddingTypeBO
     * @throws SerException
     */
    default BiddingTypeBO edit(BiddingTypeTO to) throws SerException {
        return null;
    }

    /**
     * 根据id删除招投标类型
     *
     * @param id
     * @throws SerException
     */
    default void remove(String id) throws SerException {

    }
    /**
     * 获取所有招投标类型
     *
     * @throws SerException
     */
    default List<String> getType() throws SerException {
        return null;
    }
}