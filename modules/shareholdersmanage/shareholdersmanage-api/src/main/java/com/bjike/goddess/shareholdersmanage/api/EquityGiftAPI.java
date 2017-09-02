package com.bjike.goddess.shareholdersmanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.shareholdersmanage.bo.EquityGiftBO;
import com.bjike.goddess.shareholdersmanage.dto.EquityGiftDTO;
import com.bjike.goddess.shareholdersmanage.to.EquityGiftTO;
import com.bjike.goddess.shareholdersmanage.to.GuidePermissionTO;

import java.util.List;

/**
 * 股权赠与业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 05:08 ]
 * @Description: [ 股权赠与业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface EquityGiftAPI {
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
     * 股权赠与列表总条数
     */
    default Long countGift(EquityGiftDTO equityGiftDTO) throws SerException {
        return null;
    }

    /**
     * 一个股权赠与
     *
     * @return class EquityGiftBO
     */
    default EquityGiftBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 股权赠与列表
     *
     * @param equityGiftDTO 股权转让dto
     * @return class EquityGiftBO
     * @throws SerException
     */
    default List<EquityGiftBO> findList(EquityGiftDTO equityGiftDTO) throws SerException {
        return null;
    }

    /**
     * 股权赠与添加
     *
     * @param equityGiftTO 股权转让数据to
     * @throws SerException
     */
    default EquityGiftBO save(EquityGiftTO equityGiftTO) throws SerException {
        return null;
    }

    /**
     * 股权赠与编辑
     *
     * @param equityGiftTO 股权继承数据to
     * @throws SerException
     */
    default EquityGiftBO edit(EquityGiftTO equityGiftTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除股权赠与
     *
     * @param id
     * @throws SerException
     */
    default void delete(String id) throws SerException {
        return;
    }
}