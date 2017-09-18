package com.bjike.goddess.managepromotion.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.managepromotion.bo.EmailBO;
import com.bjike.goddess.managepromotion.dto.EmailDTO;
import com.bjike.goddess.managepromotion.to.EmailTO;
import com.bjike.goddess.managepromotion.to.GuidePermissionTO;

import java.util.List;

/**
 * 发送邮件业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-14 02:16 ]
 * @Description: [ 发送邮件业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface EmailAPI {
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
     * 发送邮件列表总条数
     */
    default Long count(EmailDTO emailDTO) throws SerException {
        return null;
    }

    /**
     * 一个发送邮件
     *
     * @return class EmailBO
     */
    default EmailBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 发送邮件
     *
     * @param dto 发送邮件dto
     * @return class EmailBO
     * @throws SerException
     */
    default List<EmailBO> list(EmailDTO dto) throws SerException {
        return null;
    }

    /**
     * 添加发送邮件
     *
     * @param to 发送邮件数据to
     * @return class EmailBO
     * @throws SerException
     */
    default EmailBO save(EmailTO to) throws SerException {
        return null;
    }

    /**
     * 编辑发送邮件
     *
     * @param to 发送邮件数据to
     * @return class EmailBO
     * @throws SerException
     */
    default EmailBO edit(EmailTO to) throws SerException {
        return null;
    }

    /**
     * 根据id删除发送邮件
     *
     * @param id
     * @throws SerException
     */
    default void remove(String id) throws SerException {

    }
    /**
     * 发送邮件
     *
     * @throws SerException
     */
    default void sendEmail() throws SerException {

    }
}