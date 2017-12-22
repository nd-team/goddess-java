package com.bjike.goddess.businessprojectmanage.api;

import com.bjike.goddess.businessprojectmanage.bo.NotificationFormulaBO;
import com.bjike.goddess.businessprojectmanage.to.GuidePermissionTO;
import com.bjike.goddess.businessprojectmanage.to.NotificationFormulaTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 通报机制制定业务接口
 *
 * @Author: [ caiwenxian ]
 * @Date: [ 2017-12-14 05:05 ]
 * @Description: [ 通报机制制定业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface NotificationFormulaAPI {

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
     * 获取通报列表
     *
     * @param
     * @return class
     * @version v1
     */
    List<NotificationFormulaBO> list() throws SerException;

    /**
     * 添加通报
     *
     * @param
     * @return class
     * @version v1
     */
    void add(NotificationFormulaTO to) throws SerException;

    /**
     * 更新通报
     *
     * @param
     * @return class
     * @version v1
     */
    void update(NotificationFormulaTO to) throws  SerException;

    /**
     * 删除通报
     *
     * @param
     * @return class
     * @version v1
     */
    void delete(String id) throws SerException;

    /**
     * 邮箱发送检测
     *
     * @param
     * @return class
     * @version v1
     */
    void checkSendEmail() throws SerException;
}