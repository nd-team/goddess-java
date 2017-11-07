package com.bjike.goddess.customer.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.customer.bo.VisitRecommSetBO;
import com.bjike.goddess.customer.dto.VisitRecommSetDTO;
import com.bjike.goddess.customer.service.VisitRecommSetSer;
import com.bjike.goddess.customer.to.GuidePermissionTO;
import com.bjike.goddess.customer.to.VisitRecommSetTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 拜访推荐设置业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-03 04:12 ]
 * @Description: [ 拜访推荐设置业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("visitRecommSetApiImpl")
public class VisitRecommSetApiImpl implements VisitRecommSetAPI {
    @Autowired
    private VisitRecommSetSer visitRecommSetSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return visitRecommSetSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return visitRecommSetSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countVisitReco(VisitRecommSetDTO visitRecommSetDTO) throws SerException {
        return visitRecommSetSer.countVisitReco(visitRecommSetDTO);
    }

    @Override
    public List<VisitRecommSetBO> listVisitReco(VisitRecommSetDTO visitRecommSetDTO) throws SerException {
        return visitRecommSetSer.listVisitReco(visitRecommSetDTO);
    }

    @Override
    public VisitRecommSetBO addVisitReco(VisitRecommSetTO visitRecommSetTO) throws SerException {
        return visitRecommSetSer.addVisitReco(visitRecommSetTO);
    }

    @Override
    public VisitRecommSetBO editVisitReco(VisitRecommSetTO visitRecommSetTO) throws SerException {
        return visitRecommSetSer.editVisitReco(visitRecommSetTO);
    }

    @Override
    public void deleteVisitReco(String id) throws SerException {
        visitRecommSetSer.deleteVisitReco(id);
    }

    @Override
    public void congealVisitReco(String id) throws SerException {
        visitRecommSetSer.congealVisitReco(id);
    }

    @Override
    public void thawVisitReco(String id) throws SerException {
        visitRecommSetSer.thawVisitReco(id);
    }

    @Override
    public VisitRecommSetBO getVisitRecoById(String id) throws SerException {
        return visitRecommSetSer.getVisitRecoById(id);
    }

    @Override
    public void checkUpdateWeightHour() throws SerException {
        visitRecommSetSer.checkUpdateWeightHour();
    }

    @Override
    public void checkUpdateWeightDay() throws SerException {
        visitRecommSetSer.checkUpdateWeightDay();
    }

    @Override
    public void checkUpdateWeightWeek() throws SerException {
        visitRecommSetSer.checkUpdateWeightWeek();
    }

    @Override
    public void checkUpdateWeightMonth() throws SerException {
        visitRecommSetSer.checkUpdateWeightMonth();
    }

    @Override
    public void checkSendObjectHour() throws SerException {
        visitRecommSetSer.checkSendObjectHour();
    }

    @Override
    public void checkSendObjectDay() throws SerException {
        visitRecommSetSer.checkSendObjectDay();
    }

    @Override
    public void checkSendObjectWeek() throws SerException {
        visitRecommSetSer.checkSendObjectWeek();
    }

    @Override
    public void checkSendObjectMonth() throws SerException {
        visitRecommSetSer.checkSendObjectMonth();
    }

    @Override
    public void remindSend() throws SerException {
        visitRecommSetSer.remindSend();
    }
}