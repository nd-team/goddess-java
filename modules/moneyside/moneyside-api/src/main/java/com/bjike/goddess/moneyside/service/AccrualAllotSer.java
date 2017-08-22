package com.bjike.goddess.moneyside.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.moneyside.bo.AccrualAllotBO;
import com.bjike.goddess.moneyside.dto.AccrualAllotDTO;
import com.bjike.goddess.moneyside.entity.AccrualAllot;
import com.bjike.goddess.moneyside.to.AccrualAllotTO;
import com.bjike.goddess.moneyside.to.GuidePermissionTO;

import java.util.List;

/**
 * 权责分配业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 11:13 ]
 * @Description: [ 权责分配业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AccrualAllotSer extends Ser<AccrualAllot, AccrualAllotDTO> {
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
     * 权责分配列表总条数
     */
    default Long countAccrualAllot(AccrualAllotDTO accrualAllotDTO) throws SerException {
        return null;
    }

    /**
     * 一个权责分配
     *
     * @return class AccrualAllotBO
     */
    default AccrualAllotBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 权责分配
     *
     * @param accrualAllotDTO 权责分配dto
     * @return class AccrualAllotBO
     * @throws SerException
     */
    default List<AccrualAllotBO> findListAccrualAllot(AccrualAllotDTO accrualAllotDTO) throws SerException {
        return null;
    }

    /**
     * 添加权责分配
     *
     * @param accrualAllotTO 权责分配数据to
     * @return class AccrualAllotBO
     * @throws SerException
     */
    default AccrualAllotBO insertAccrualAllot(AccrualAllotTO accrualAllotTO) throws SerException {
        return null;
    }

    /**
     * 编辑权责分配
     *
     * @param accrualAllotTO 权责分配数据to
     * @return class AccrualAllotBO
     * @throws SerException
     */
    default AccrualAllotBO editAccrualAllot(AccrualAllotTO accrualAllotTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除权责分配
     *
     * @param id
     * @throws SerException
     */
    default void removeAccrualAllot(String id) throws SerException {

    }

}