package com.bjike.goddess.secure.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.secure.bo.DrawRegisterBO;
import com.bjike.goddess.secure.bo.ReplaceRegisterBO;
import com.bjike.goddess.secure.dto.DrawRegisterDTO;
import com.bjike.goddess.secure.dto.ReplaceRegisterDTO;
import com.bjike.goddess.secure.entity.ReplaceRegister;
import com.bjike.goddess.secure.to.DrawRegisterTO;
import com.bjike.goddess.secure.to.GuidePermissionTO;
import com.bjike.goddess.secure.to.ReplaceRegisterTO;

import java.util.List;
import java.util.Set;

/**
 * 社保卡补办登记表业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-25 06:09 ]
 * @Description: [ 社保卡补办登记表业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ReplaceRegisterSer extends Ser<ReplaceRegister, ReplaceRegisterDTO> {
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
     * @param to 社保卡补办登记表
     * @return class ReplaceRegisterBO
     * @throws SerException
     */
    default ReplaceRegisterBO save(ReplaceRegisterTO to) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param to 社保卡补办登记表
     * @return class ReplaceRegisterBO
     * @throws SerException
     */
    default ReplaceRegisterBO edit(ReplaceRegisterTO to) throws SerException {
        return null;
    }

    /**
     * 查找
     *
     * @param dto 社保卡补办登记表
     * @return class ReplaceRegisterBO
     * @throws SerException
     */
    default List<ReplaceRegisterBO> list(ReplaceRegisterDTO dto) throws SerException {
        return null;
    }

    /**
     * 通过id查找
     *
     * @param id 社保卡补办登记表id
     * @return class ReplaceRegisterBO
     * @throws SerException
     */
    default ReplaceRegisterBO findByID(String id) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 社保卡补办登记表id
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
    Long count(ReplaceRegisterDTO dto) throws SerException;
    /**
     * 综合资源部意见
     *
     * @param to to
     * @return class ReplaceRegisterBO
     * @throws SerException
     */
    default ReplaceRegisterBO resourcesAudit(ReplaceRegisterTO to) throws SerException {
        return null;
    }
    /**
     * 运营财务部意见
     *
     * @param to to
     * @return class ReplaceRegisterBO
     * @throws SerException
     */
    default ReplaceRegisterBO financeAudit(ReplaceRegisterTO to) throws SerException {
        return null;
    }
    /**
     * 总经办意见
     *
     * @param to to
     * @return class ReplaceRegisterBO
     * @throws SerException
     */
    default ReplaceRegisterBO generalAudit(ReplaceRegisterTO to) throws SerException {
        return null;
    }
    /**
     * 获取所有姓名
     *
     * @throws SerException
     */
    Set<String> allName() throws SerException;
}