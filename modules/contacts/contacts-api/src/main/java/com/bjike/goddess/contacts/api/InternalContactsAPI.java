package com.bjike.goddess.contacts.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.contacts.bo.InternalContactsBO;
import com.bjike.goddess.contacts.bo.MobileInternalContactsBO;
import com.bjike.goddess.contacts.bo.NameAndIdBO;
import com.bjike.goddess.contacts.dto.InternalContactsDTO;
import com.bjike.goddess.contacts.to.GuidePermissionTO;
import com.bjike.goddess.contacts.to.InternalContactsTO;

import java.util.List;

/**
 * 内部通讯录业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-29 05:08 ]
 * @Description: [ 内部通讯录业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface InternalContactsAPI {


    /**
     * 保存
     *
     * @param to 内部通讯录传输对象
     * @return
     * @throws SerException
     */
    default InternalContactsBO save(InternalContactsTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to 内部通讯录传输对象
     * @return
     * @throws SerException
     */
    default InternalContactsBO update(InternalContactsTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param to 内部通讯录传输对象
     * @return
     * @throws SerException
     */
    default InternalContactsBO delete(InternalContactsTO to) throws SerException {
        return null;
    }

    /**
     * 查询邮箱不为空数据
     *
     * @return
     * @throws SerException
     */
    default List<InternalContactsBO> findEmailNotNull() throws SerException {
        return null;
    }

    /**
     * 根据用户ID查询通讯录
     *
     * @param user_id 用户ID
     * @return
     * @throws SerException
     */
    default InternalContactsBO findByUser(String user_id) throws SerException {
        return null;
    }

    /**
     * 列表数据
     *
     * @param dto 内部通讯录数据传输对象
     * @return
     * @throws SerException
     */
    default List<InternalContactsBO> maps(InternalContactsDTO dto) throws SerException {
        return null;
    }

    /**
     * 通讯录信息确认邮件
     *
     * @throws SerException
     */
    void sendEmail() throws SerException;

    /**
     * 根据id获取内部通讯录数据
     *
     * @param id 内部通讯录数据id
     * @return
     * @throws SerException
     */
    default InternalContactsBO getById(String id) throws SerException {
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
    InternalContactsBO importExcel(List<InternalContactsTO> tocs) throws SerException;

    /**
     * 定时器检测并删除离职员工通讯录
     */
    void checkDimissionInfo() throws SerException;

    /**
     * 获得入职人员的姓名
     */
    List<NameAndIdBO> getUserName() throws SerException;

    default byte[] templateExport() throws SerException {
        return null;
    }

    /**
     * 根据用户名字获取邮箱
     */
    default List<String> getEmails(String[] names) throws SerException {
        return null;
    }

    /**
     * 根据单个名字获取邮箱
     */
    default String getEmail(String name) throws SerException {
        return null;
    }

    /**
     * 移动端获取列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default List<MobileInternalContactsBO> mobileList(InternalContactsDTO dto) throws SerException {
        return null;
    }

    /**
     * 移动端总条数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default Long getMobileTotal(InternalContactsDTO dto) throws SerException {
        return null;
    }

    /**
     * 根据id 获取移动端数据
     *
     * @param id
     * @return
     */
    default MobileInternalContactsBO findByMobileID(String id) throws SerException {
        return null;
    }

    default void test(List<InternalContactsTO> tocs) throws SerException {
        return;
    }
}