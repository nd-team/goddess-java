package com.bjike.goddess.secure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.secure.bo.DrawRegisterBO;
import com.bjike.goddess.secure.bo.PayDetailBO;
import com.bjike.goddess.secure.dto.DrawRegisterDTO;
import com.bjike.goddess.secure.dto.PayDetailDTO;
import com.bjike.goddess.secure.entity.DrawRegister;
import com.bjike.goddess.secure.to.DrawRegisterTO;
import com.bjike.goddess.secure.to.GuidePermissionTO;
import com.bjike.goddess.secure.to.PayDetailTO;

import java.util.List;
import java.util.Set;

/**
 * 社保卡领取登记表业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-25 05:55 ]
 * @Description: [ 社保卡领取登记表业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface DrawRegisterSer extends Ser<DrawRegister, DrawRegisterDTO> {
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
     * @param to 社保卡领取登记表
     * @return class DrawRegisterBO
     * @throws SerException
     */
    default DrawRegisterBO save(DrawRegisterTO to) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param to 社保卡领取登记表
     * @return class DrawRegisterBO
     * @throws SerException
     */
    default DrawRegisterBO edit(DrawRegisterTO to) throws SerException {
        return null;
    }

    /**
     * 查找
     *
     * @param dto 社保卡领取登记表
     * @return class DrawRegisterBO
     * @throws SerException
     */
    default List<DrawRegisterBO> list(DrawRegisterDTO dto) throws SerException {
        return null;
    }

    /**
     * 通过id查找
     *
     * @param id 社保卡领取登记表id
     * @return class DrawRegisterBO
     * @throws SerException
     */
    default DrawRegisterBO findByID(String id) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 社保卡领取登记表id
     * @throws SerException
     */
    default void delete(String id) throws SerException {

    }

    /**
     * 查找总记录数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(DrawRegisterDTO dto) throws SerException;
    /**
     * 负责人审核
     *
     * @param to 社保卡领取登记表
     * @return class DrawRegisterBO
     * @throws SerException
     */
    default DrawRegisterBO chargeAudit(DrawRegisterTO to) throws SerException {
        return null;
    }
    /**
     * 获取所有姓名
     *
     * @throws SerException
     */
    Set<String> allName() throws SerException;
}