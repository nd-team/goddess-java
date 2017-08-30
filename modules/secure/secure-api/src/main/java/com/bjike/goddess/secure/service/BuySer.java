package com.bjike.goddess.secure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.secure.bo.BuyBO;
import com.bjike.goddess.secure.dto.BuyDTO;
import com.bjike.goddess.secure.entity.Buy;
import com.bjike.goddess.secure.to.BuyTO;
import com.bjike.goddess.secure.to.GuidePermissionTO;

import java.util.List;

/**
 * 购买社保人员业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-21 02:45 ]
 * @Description: [ 购买社保人员业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BuySer extends Ser<Buy, BuyDTO> {
    /**
     * 下拉导航权限
     */
    Boolean sonPermission() throws SerException;

    /**
     * 导航权限
     */
    Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException;
    /**
     * 查找
     *
     * @param dto 购买社保人员分页信息
     * @return class BuyBO
     * @throws SerException
     */
    default List<BuyBO> find(BuyDTO dto) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param to 购买社保人员信息
     * @return class BuyBO
     * @throws SerException
     */
    default BuyBO edit(BuyTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 购买社保人员名单的id
     * @return class BuyBO
     * @throws SerException
     */
    default BuyBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 通过id查找
     *
     * @param id 购买社保人员的id
     * @return class BuyBO
     * @throws SerException
     */
    default BuyBO findByID(String id) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param to 购买社保人员的信息
     * @return class BuyBO
     * @throws SerException
     */
    default BuyBO save(BuyTO to) throws SerException {
        return null;
    }

    /**
     * 通过dto查找列表信息
     *
     * @param dto dto
     * @return
     * @throws SerException
     */
    List<BuyBO> findByDTO(BuyDTO dto) throws SerException;

    /**
     * 查找总记录数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(BuyDTO dto) throws SerException;
}