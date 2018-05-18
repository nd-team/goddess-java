package com.bjike.goddess.staffshares.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.staffshares.bo.PurchaseBO;
import com.bjike.goddess.staffshares.dto.PurchaseDTO;
import com.bjike.goddess.staffshares.entity.Purchase;
import com.bjike.goddess.staffshares.to.GuidePermissionTO;
import com.bjike.goddess.staffshares.to.PurchaseTO;

import java.util.List;

/**
 * 干股申购表业务接口
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-08-05 09:51 ]
 * @Description: [ 干股申购表业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface PurchaseSer extends Ser<Purchase, PurchaseDTO> {

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
     * 申请购买
     *
     * @param to
     */
    void buy(PurchaseTO to) throws SerException;

    /**
     * 编辑
     *
     * @param to
     * @throws SerException
     */
    void update(PurchaseTO to) throws SerException;

    /**
     * 删除
     *
     * @param id
     * @throws SerException
     */
    void delete(String id) throws SerException;

    /**
     * 审核
     *
     * @param to
     */
    void examine(PurchaseTO to) throws SerException;

    /**
     * 干股申购表列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<PurchaseBO> list(PurchaseDTO dto) throws SerException;

    /**
     * 获得一条干股申购表
     *
     * @param id
     * @return
     * @throws SerException
     */
    PurchaseBO getById(String id) throws SerException;

    /**
     * 获取干股申购表总条数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long getTotal(PurchaseDTO dto) throws SerException;
}