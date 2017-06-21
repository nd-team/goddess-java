package com.bjike.goddess.projectmarketfee.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.projectmarketfee.bo.WarnBO;
import com.bjike.goddess.projectmarketfee.dto.WarnDTO;
import com.bjike.goddess.projectmarketfee.entity.Warn;
import com.bjike.goddess.projectmarketfee.to.GuidePermissionTO;
import com.bjike.goddess.projectmarketfee.to.WarnTO;

import java.util.List;

/**
 * 预警设计业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-09 04:53 ]
 * @Description: [ 预警设计业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface WarnSer extends Ser<Warn, WarnDTO> {
    /**
     * 下拉导航权限
     */
    Boolean sonPermission() throws SerException;

    /**
     * 导航权限
     */
    Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException;

    /**
     * 添加
     *
     * @param to 预警设计信息
     * @return class WarnBO
     * @throws SerException
     */
    default WarnBO save(WarnTO to) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param to 预警设计信息
     * @throws SerException
     */
    default void edit(WarnTO to) throws SerException {
    }

    /**
     * 分页查找
     *
     * @param dto 预警设计分页信息
     * @return class WarnBO
     * @throws SerException
     */
    default List<WarnBO> list(WarnDTO dto) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 预警设计id
     * @throws SerException
     */
    default void delete(String id) throws SerException {
    }

    /**
     * 通过id查找
     *
     * @param id 预警设计id
     * @return class WarnBO
     * @throws SerException
     */
    default WarnBO findByID(String id) throws SerException {
        return null;
    }

    /**
     * 查找总记录数
     *
     * @param dto 预警信息
     * @return class WarnBO
     * @throows SerException
     */
    default WarnBO countNum(WarnDTO dto) throws SerException {
        return null;
    }
}