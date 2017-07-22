package com.bjike.goddess.oilcardmanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.oilcardmanage.bo.AnalyzeBO;
import com.bjike.goddess.oilcardmanage.bo.OilCardRechargeBO;
import com.bjike.goddess.oilcardmanage.dto.OilCardRechargeDTO;
import com.bjike.goddess.oilcardmanage.service.OilCardRechargeSer;
import com.bjike.goddess.oilcardmanage.to.GuidePermissionTO;
import com.bjike.goddess.oilcardmanage.to.OilCardRechargeTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 油卡充值对外发布接口实现类
 *
 * @Author: [Jason]
 * @Date: [17-3-15 上午11:06]
 * @Package:[ com.bjike.goddess.oilcardmanage.api ]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("oilCardRechargeApiImpl")
public class OilCardRechargeApiImpl implements OilCardRechargeAPI {

    @Autowired
    private OilCardRechargeSer oilCardRechargeSer;

    @Override
    public OilCardRechargeBO saveOilCardRecharge(OilCardRechargeTO to) throws SerException {
        return oilCardRechargeSer.saveOilCardRecharge(to);
    }

    @Override
    public OilCardRechargeBO updateOilCardRecharge(OilCardRechargeTO to) throws SerException {
        return oilCardRechargeSer.updateOilCardRecharge(to);
    }

    @Override
    public List<OilCardRechargeBO> collect(String oilCardBasicId, String startTime, String endTime) throws SerException {
        return oilCardRechargeSer.collect(oilCardBasicId, startTime, endTime);
    }

    @Override
    public List<OilCardRechargeBO> pageList(OilCardRechargeDTO dto) throws SerException {
        return oilCardRechargeSer.pageList(dto);
    }

    @Override
    public List<OilCardRechargeBO> findByBasicId(String id) throws SerException {
        return oilCardRechargeSer.findByBasicId(id);
    }

    @Override
    public OilCardRechargeBO findById(String id) throws SerException {
        return BeanTransform.copyProperties(oilCardRechargeSer.findById(id), OilCardRechargeBO.class);
    }

    @Override
    public Long count(OilCardRechargeDTO dto) throws SerException {
        return oilCardRechargeSer.count(dto);
    }

    @Override
    public AnalyzeBO analyze(String oilCardCode, Integer year, Integer month) throws SerException {
        return oilCardRechargeSer.analyze(oilCardCode, year, month);
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return oilCardRechargeSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return oilCardRechargeSer.guidePermission(guidePermissionTO);
    }
}
