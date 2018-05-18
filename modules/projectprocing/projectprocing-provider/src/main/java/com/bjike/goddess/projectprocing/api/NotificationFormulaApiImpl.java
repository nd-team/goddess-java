package com.bjike.goddess.projectprocing.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectprocing.bo.NotificationFormulaBO;
import com.bjike.goddess.projectprocing.dto.NotificationFormulaDTO;
import com.bjike.goddess.projectprocing.service.NotificationFormulaSer;
import com.bjike.goddess.projectprocing.to.GuidePermissionTO;
import com.bjike.goddess.projectprocing.to.NotificationFormulaTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 通报机制制定业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 03:24 ]
 * @Description: [ 通报机制制定业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("notificationFormulaApiImpl")
public class NotificationFormulaApiImpl implements NotificationFormulaAPI {

    @Autowired
    private NotificationFormulaSer notificationFormulaSer;


    @Override
    public Boolean sonPermission() throws SerException {
        return notificationFormulaSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return notificationFormulaSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countNotifi(NotificationFormulaDTO notificationFormulaDTO) throws SerException {
        return notificationFormulaSer.countNotifi(notificationFormulaDTO);
    }

    @Override
    public NotificationFormulaBO getOne(String id) throws SerException {
        return notificationFormulaSer.getOne(id);
    }

    @Override
    public List<NotificationFormulaBO> listCollectEmail(NotificationFormulaDTO notificationFormulaDTO) throws SerException {
        return notificationFormulaSer.listCollectEmail(notificationFormulaDTO);
    }

    @Override
    public NotificationFormulaBO addCollectEmail(NotificationFormulaTO notificationFormulaTO) throws SerException {
        return notificationFormulaSer.addCollectEmail(notificationFormulaTO);
    }

    @Override
    public NotificationFormulaBO editCollectEmail(NotificationFormulaTO notificationFormulaTO) throws SerException {
        return notificationFormulaSer.editCollectEmail(notificationFormulaTO);
    }

    @Override
    public void deleteCollectEmail(String id) throws SerException {
        notificationFormulaSer.deleteCollectEmail(id);
    }

    @Override
    public void congealCollectEmail(String id) throws SerException {
        notificationFormulaSer.congealCollectEmail(id);
    }

    @Override
    public void thawCollectEmail(String id) throws SerException {
        notificationFormulaSer.thawCollectEmail(id);
    }

    @Override
    public void checkEmail() throws SerException {
        notificationFormulaSer.checkEmail();
    }
}