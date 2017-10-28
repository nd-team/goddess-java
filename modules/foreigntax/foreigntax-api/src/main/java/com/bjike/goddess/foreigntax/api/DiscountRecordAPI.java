package com.bjike.goddess.foreigntax.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.foreigntax.bo.DiscountRecordBO;
import com.bjike.goddess.foreigntax.dto.DiscountRecordDTO;
import com.bjike.goddess.foreigntax.to.DiscountRecordTO;
import com.bjike.goddess.foreigntax.to.GuidePermissionTO;

import java.util.List;

/**
 * 优惠备案业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-17 09:21 ]
 * @Description: [ 优惠备案业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface DiscountRecordAPI {
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
     * 优惠备案列表总条数
     */
    default Long count(DiscountRecordDTO dto) throws SerException {
        return null;
    }

    /**
     * 一个优惠备案
     *
     * @return class DiscountRecordBO
     */
    default DiscountRecordBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 优惠备案
     *
     * @param dto 优惠备案dto
     * @return class DiscountRecordBO
     * @throws SerException
     */
    default List<DiscountRecordBO> list(DiscountRecordDTO dto) throws SerException {
        return null;
    }

    /**
     * 添加优惠备案
     *
     * @param to 优惠备案数据to
     * @return class DiscountRecordBO
     * @throws SerException
     */
    default DiscountRecordBO insert(DiscountRecordTO to) throws SerException {
        return null;
    }

    /**
     * 编辑优惠备案
     *
     * @param to 优惠备案数据to
     * @return class DiscountRecordBO
     * @throws SerException
     */
    default DiscountRecordBO edit(DiscountRecordTO to) throws SerException {
        return null;
    }

    /**
     * 根据id删除优惠备案
     *
     * @param id
     * @throws SerException
     */
    default void remove(String id) throws SerException {

    }
}