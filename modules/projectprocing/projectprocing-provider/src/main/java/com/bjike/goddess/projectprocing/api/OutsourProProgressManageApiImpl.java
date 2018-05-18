package com.bjike.goddess.projectprocing.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectprocing.bo.OutsourProProgressManageBO;
import com.bjike.goddess.projectprocing.dto.OutsourProProgressManageDTO;
import com.bjike.goddess.projectprocing.service.OutsourProProgressManageSer;
import com.bjike.goddess.projectprocing.to.GuidePermissionTO;
import com.bjike.goddess.projectprocing.to.OutsourProProgressManageTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 外包,半外包项目结算进度管理业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 03:03 ]
 * @Description: [ 外包,半外包项目结算进度管理业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("outsourProProgressManageApiImpl")
public class OutsourProProgressManageApiImpl implements OutsourProProgressManageAPI {
    @Autowired
    private OutsourProProgressManageSer outsourProProgressManageSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return outsourProProgressManageSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return outsourProProgressManageSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countOuts(OutsourProProgressManageDTO outsourProProgressManageDTO) throws SerException {
        return outsourProProgressManageSer.countOuts(outsourProProgressManageDTO);
    }

    @Override
    public OutsourProProgressManageBO getOneById(String id) throws SerException {
        return outsourProProgressManageSer.getOneById(id);
    }

    @Override
    public List<OutsourProProgressManageBO> listOuts(OutsourProProgressManageDTO outsourProProgressManageDTO) throws SerException {
        return outsourProProgressManageSer.listOuts(outsourProProgressManageDTO);
    }

    @Override
    public OutsourProProgressManageBO addOuts(OutsourProProgressManageTO outsourProProgressManageTO) throws SerException {
        return outsourProProgressManageSer.addOuts(outsourProProgressManageTO);
    }

    @Override
    public OutsourProProgressManageBO editOuts(OutsourProProgressManageTO outsourProProgressManageTO) throws SerException {
        return outsourProProgressManageSer.editOuts(outsourProProgressManageTO);
    }

    @Override
    public void deleteOuts(String id) throws SerException {
        outsourProProgressManageSer.deleteOuts(id);
    }

    @Override
    public void manageOpinion(OutsourProProgressManageTO outsourProProgressManageTO) throws SerException {
        outsourProProgressManageSer.manageOpinion(outsourProProgressManageTO);
    }

    @Override
    public void remindingEmail() throws SerException {
        outsourProProgressManageSer.remindingEmail();
    }

    @Override
    public void receivaConfir(OutsourProProgressManageTO outsourProProgressManageTO) throws SerException {
        outsourProProgressManageSer.receivaConfir(outsourProProgressManageTO);
    }

    @Override
    public void noticeInvoice(OutsourProProgressManageTO outsourProProgressManageTO) throws SerException {
        outsourProProgressManageSer.noticeInvoice(outsourProProgressManageTO);
    }

    @Override
    public void payMoney(OutsourProProgressManageTO outsourProProgressManageTO) throws SerException {
        outsourProProgressManageSer.payMoney(outsourProProgressManageTO);
    }

    @Override
    public void scheduleConfirm(OutsourProProgressManageTO outsourProProgressManageTO) throws SerException {
        outsourProProgressManageSer.scheduleConfirm(outsourProProgressManageTO);
    }
}