package com.bjike.goddess.dispatchcar.api;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.dispatchcar.bo.*;
import com.bjike.goddess.dispatchcar.dto.DispatchCarInfoDTO;
import com.bjike.goddess.dispatchcar.enums.CollectIntervalType;
import com.bjike.goddess.dispatchcar.enums.CollectType;
import com.bjike.goddess.dispatchcar.enums.FindType;
import com.bjike.goddess.dispatchcar.service.DispatchCarInfoSer;
import com.bjike.goddess.dispatchcar.to.ConditionTO;
import com.bjike.goddess.dispatchcar.to.DispatchCarInfoTO;
import com.bjike.goddess.dispatchcar.to.FinanceCollectTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;

/**
 * 出车记录业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-12 05:26 ]
 * @Description: [ 出车记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("dispatchCarInfoApiImpl")
public class DispatchCarInfoApiImpl implements DispatchCarInfoAPI {

    @Autowired
    private DispatchCarInfoSer dispatchCarInfoSer;

    @Override
    public DispatchCarInfoBO addModel(DispatchCarInfoTO to) throws SerException {
        return dispatchCarInfoSer.insertModel(to);
    }

    @Override
    public DispatchCarInfoBO editModel(DispatchCarInfoTO to) throws SerException {
        return dispatchCarInfoSer.updateModel(to);
    }

    @Override
    public void freeze(String id) throws SerException {
        dispatchCarInfoSer.freeze(id);
    }

    @Override
    public void breakFreeze(String id) throws SerException {
        dispatchCarInfoSer.breakFreeze(id);
    }

    @Override
    public List<DispatchCarInfoBO> pageList(DispatchCarInfoDTO dto) throws SerException {
        return dispatchCarInfoSer.pageList(dto);
    }

    @Override
    public void fileUpload(DispatchCarInfoTO to) throws SerException {
        dispatchCarInfoSer.fileUpload(to);
    }

    @Override
    public DispatchCarInfoBO findDetail(String id) throws SerException {
        return BeanTransform.copyProperties(dispatchCarInfoSer.findById(id), DispatchCarInfoBO.class);
    }

    @Override
    public AuditDetailBO findAudit(String id) throws SerException {
        return dispatchCarInfoSer.findAudit(id);
    }

    @Override
    public void fundSugg(String id, String fundModuleSugg) throws SerException {
        dispatchCarInfoSer.fundSugg(id, fundModuleSugg);
    }

    @Override
    public void budgetSugg(String id, String budgetModuleSugg) throws SerException {
        dispatchCarInfoSer.budgetSugg(id, budgetModuleSugg);
    }

    @Override
    public void principalSugg(String id, String principalSugg, Boolean auditResult) throws SerException {
        dispatchCarInfoSer.principalSugg(id, principalSugg, auditResult);
    }

    @Override
    public void receiptAudit(String id, String auditReceiptSugg, String receiveReceiptDate, Boolean auditReceiptResult) throws SerException {
        dispatchCarInfoSer.receiptAudit(id, auditReceiptSugg, receiveReceiptDate, auditReceiptResult);
    }

    @Override
    public void pay(String id) throws SerException {
        dispatchCarInfoSer.pay(id);
    }

    @Override
    public List<DispatchCollectBO> dispatchCollect(CollectIntervalType collectIntervalType, CollectType collectType) throws SerException {
        return dispatchCarInfoSer.dispatchCollect(collectIntervalType, collectType);
    }

    @Override
    public List<FinanceCollectBO> weekCollect(String startDate, String endDate) throws SerException {
        return dispatchCarInfoSer.weekCollect(startDate, endDate);
    }

    @Override
    public List<FinanceCollectBO> monthCollect(Integer year, Integer month) throws SerException {
        return dispatchCarInfoSer.monthCollect(year, month);
    }

    @Override
    public List<FinanceCollectBO> selectCollect(FinanceCollectTO to) throws SerException {
        return dispatchCarInfoSer.selectCollect(to);
    }

    @Override
    public FinanceCollectBO findCollectDetail(String id) throws SerException {
        return dispatchCarInfoSer.findCollectDetail(id);
    }

    @Override
    public List<FinanceAnalyzeBO> selectAnalyze(FinanceCollectTO to) throws SerException {
        return dispatchCarInfoSer.selectAnalyze(to);
    }

    @Override
    public Long count(DispatchCarInfoDTO dto) throws SerException {
        return dispatchCarInfoSer.count(dto);
    }

    @Override
    public DispatchCarInfoBO findById(String id) throws SerException {
        return BeanTransform.copyProperties(dispatchCarInfoSer.findById(id), DispatchCarInfoBO.class);
    }

    @Override
    public List<DispatchCarInfoBO> allWaitPay() throws SerException {
        DispatchCarInfoDTO dto = new DispatchCarInfoDTO();
        dto.getConditions().add(Restrict.eq("findType", FindType.WAITPAY));
        return BeanTransform.copyProperties(dispatchCarInfoSer.findByCis(dto), DispatchCarInfoBO.class);
    }

    @Override
    public List<AuditResultBO> findAuditResult(String id) throws SerException {
        return dispatchCarInfoSer.findAuditResults(id);
    }

    @Override
    public void predict(String id, String budgetPayDate, String payPlan) throws SerException {
        dispatchCarInfoSer.predict(id, budgetPayDate, payPlan);
    }

    @Override
    public List<DispatchCarInfoBO> getByConfition(ConditionTO to) throws SerException {
        DispatchCarInfoDTO dto = new DispatchCarInfoDTO();
        if (!StringUtils.isEmpty(to.getArea())) {
            dto.getConditions().add(Restrict.eq("area",to.getArea()));
        }
        if (!StringUtils.isEmpty(to.getGroup())) {
            dto.getConditions().add(Restrict.eq("group",to.getGroup()));
        }
        if (!StringUtils.isEmpty(to.getProject())) {
            dto.getConditions().add(Restrict.eq("project",to.getProject()));
        }
        if (!StringUtils.isEmpty(to.getDispatchDate())) {
            dto.getConditions().add(Restrict.between("dispatchDate",to.getDispatchDate()));
        }
        return BeanTransform.copyProperties(dispatchCarInfoSer.findByCis(dto), DispatchCarInfoBO.class);
    }
}