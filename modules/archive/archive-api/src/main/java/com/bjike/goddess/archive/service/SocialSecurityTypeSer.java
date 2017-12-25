package com.bjike.goddess.archive.service;

import com.bjike.goddess.archive.bo.SocialSecurityTypeBO;
import com.bjike.goddess.archive.dto.SocialSecurityTypeDTO;
import com.bjike.goddess.archive.entity.SocialSecurityType;
import com.bjike.goddess.archive.to.GuidePermissionTO;
import com.bjike.goddess.archive.to.SocialSecurityTypeTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.common.api.type.Status;

import java.util.List;

/**
 * 公司社保购买类型业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 11:08 ]
 * @Description: [ 公司社保购买类型业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SocialSecurityTypeSer extends Ser<SocialSecurityType, SocialSecurityTypeDTO> {


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
     * 保存
     *
     * @param to 公司社保购买类型传输对象
     * @return
     * @throws SerException
     */
    default SocialSecurityTypeBO save(SocialSecurityTypeTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to 公司社保购买类型传输对象
     * @return
     * @throws SerException
     */
    default SocialSecurityTypeBO update(SocialSecurityTypeTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 公司社保购买类型数据id
     * @return
     * @throws SerException
     */
    default SocialSecurityTypeBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 冻结
     *
     * @param id 公司社保购买类型数据id
     * @return
     * @throws SerException
     */
    default SocialSecurityTypeBO congeal(String id) throws SerException {
        return null;
    }

    /**
     * 解冻
     *
     * @param id 公司社保购买类型数据id
     * @return
     * @throws SerException
     */
    default SocialSecurityTypeBO thaw(String id) throws SerException {
        return null;
    }

    /**
     * 根据状态查询公司社保购买类型数据
     *
     * @param status 状态
     * @return
     * @throws SerException
     */
    default List<SocialSecurityTypeBO> findByStatus(Status status) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 公司社保购买类型数据传输对象
     * @return
     * @throws SerException
     */
    default List<SocialSecurityTypeBO> maps(SocialSecurityTypeDTO dto) throws SerException {
        return null;
    }


    /**
     * 根据id获取公司社保购买类型数据
     *
     * @param id 公司社保购买类型数据id
     * @return
     * @throws SerException
     */
    default SocialSecurityTypeBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 获取总条数
     *
     * @return
     * @throws SerException
     */
    default Long getTotal() throws SerException {
        return null;
    }
}