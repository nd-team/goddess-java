package com.bjike.goddess.businessprojectmanage.api;

import com.bjike.goddess.businessprojectmanage.bo.NotificationFormulaBO;
import com.bjike.goddess.businessprojectmanage.service.NotificationFormulaSer;
import com.bjike.goddess.businessprojectmanage.to.GuidePermissionTO;
import com.bjike.goddess.businessprojectmanage.to.NotificationFormulaTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 通报机制制定业务接口实现
 *
 * @Author: [ caiwenxian ]
 * @Date: [ 2017-12-14 05:05 ]
 * @Description: [ 通报机制制定业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("notificationFormulaApiImpl")
public class NotificationFormulaApiImpl implements NotificationFormulaAPI {

    @Autowired
    NotificationFormulaSer notificationFormulaSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return notificationFormulaSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return notificationFormulaSer.guidePermission(guidePermissionTO);
    }

    @Override
    public List<NotificationFormulaBO> list() throws SerException {
        return notificationFormulaSer.list();
    }

    @Override
    public void add(NotificationFormulaTO to) throws SerException {
        notificationFormulaSer.add(to);
    }

    @Override
    public void update(NotificationFormulaTO to) throws SerException {
        notificationFormulaSer.update(to);
    }

    @Override
    public void delete(String id) throws SerException {
        notificationFormulaSer.delete(id);
    }

    @Override
    public void checkSendEmail() throws SerException {
        notificationFormulaSer.checkSendEmail();
    }
}