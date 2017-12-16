package com.bjike.goddess.contacts.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.contacts.bo.ExternalContactsBO;
import com.bjike.goddess.contacts.bo.MobileExternalContactsBO;
import com.bjike.goddess.contacts.dto.ExternalContactsDTO;
import com.bjike.goddess.contacts.to.ExternalContactsTO;
import com.bjike.goddess.contacts.to.GuidePermissionTO;

import java.util.List;

/**
 * 外部通讯录业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 05:26 ]
 * @Description: [ 外部通讯录业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ExternalContactsAPI {

    /**
     * 保存
     *
     * @param to 外部通讯录传输对象
     * @return
     * @throws SerException
     */
    default ExternalContactsBO save(ExternalContactsTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to 外部通讯录传输对象
     * @return
     * @throws SerException
     */
    default ExternalContactsBO update(ExternalContactsTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param to 外部通讯录传输对象
     * @return
     * @throws SerException
     */
    default ExternalContactsBO delete(ExternalContactsTO to) throws SerException {
        return null;
    }

    /**
     * 根据地区查询
     *
     * @param area 地区
     * @return
     * @throws SerException
     */
    default List<ExternalContactsBO> findByArea(String area) throws SerException {
        return null;
    }

    /**
     * 列表查询
     *
     * @param dto 外部通讯录数据传输对象
     * @return
     * @throws SerException
     */
    default List<ExternalContactsBO> maps(ExternalContactsDTO dto) throws SerException {
        return null;
    }


    /**
     * 根据id获取外部通讯录数据
     *
     * @param id 外部通讯录数据id
     * @return
     * @throws SerException
     */
    default ExternalContactsBO getById(String id) throws SerException {
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
     * 导入
     */
    ExternalContactsBO importExcel(List<ExternalContactsTO> tocs) throws SerException;

    default byte[] templateExport() throws SerException {
        return null;
    }

    /**
     * 移动端获取列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default List<MobileExternalContactsBO> mobileList(ExternalContactsDTO dto) throws SerException {
        return null;
    }

    /**
     * 移动端总条数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default Long getMobileTotal(ExternalContactsDTO dto) throws SerException {
        return null;
    }

    /**
     * 根据id 获取移动端数据
     *
     * @param id
     * @return
     * @throws SerException
     */
    default MobileExternalContactsBO findByMobileID(String id) throws SerException {
        return null;
    }

}