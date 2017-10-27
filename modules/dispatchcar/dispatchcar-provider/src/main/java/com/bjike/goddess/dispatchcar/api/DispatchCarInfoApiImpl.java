package com.bjike.goddess.dispatchcar.api;


import com.bjike.goddess.carinfo.bo.DriverInfoBO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.dispatchcar.bo.*;
import com.bjike.goddess.dispatchcar.dto.CollectDispatchcarDTO;
import com.bjike.goddess.dispatchcar.dto.DispatchCarInfoDTO;
import com.bjike.goddess.dispatchcar.enums.CollectIntervalType;
import com.bjike.goddess.dispatchcar.enums.CollectType;
import com.bjike.goddess.dispatchcar.enums.FindType;
import com.bjike.goddess.dispatchcar.excel.DispatchCarInfoSetExcel;
import com.bjike.goddess.dispatchcar.excel.SonPermissionObject;
import com.bjike.goddess.dispatchcar.service.DispatchCarInfoSer;
import com.bjike.goddess.dispatchcar.to.*;
import com.bjike.goddess.organize.bo.AreaBO;
import com.bjike.goddess.user.bo.UserBO;
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
    public List<SonPermissionObject> sonPermission() throws SerException {
        return dispatchCarInfoSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return dispatchCarInfoSer.guidePermission(guidePermissionTO);
    }

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
    public void principalSugg(String id, String principalSugg, Boolean auditResult) throws SerException {
        dispatchCarInfoSer.principalSugg(id, principalSugg, auditResult);
    }

//    @Override
//    public void receiptAudit(String id, String auditReceiptSugg, String receiveReceiptDate, Boolean auditReceiptResult) throws SerException {
//        dispatchCarInfoSer.receiptAudit(id, auditReceiptSugg, receiveReceiptDate, auditReceiptResult);
//    }

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
    public void predict(PredictPayTO to) throws SerException {
        dispatchCarInfoSer.predict(to);
    }

    @Override
    public List<DispatchCarInfoBO> getByConfition(ConditionTO to) throws SerException {
        DispatchCarInfoDTO dto = new DispatchCarInfoDTO();
        if (!StringUtils.isEmpty(to.getArea())) {
            dto.getConditions().add(Restrict.eq("area", to.getArea()));
        }
        if (!StringUtils.isEmpty(to.getGroup())) {
            dto.getConditions().add(Restrict.eq("group", to.getGroup()));
        }
        if (!StringUtils.isEmpty(to.getProject())) {
            dto.getConditions().add(Restrict.eq("project", to.getProject()));
        }
        if (!StringUtils.isEmpty(to.getStartDate()) && !StringUtils.isEmpty(to.getEndDate())) {
            LocalDate startDate = DateUtil.parseDate(to.getStartDate());
            LocalDate endDate = DateUtil.parseDate(to.getEndDate());
            LocalDate[] dates = new LocalDate[]{startDate, endDate};
            dto.getConditions().add(Restrict.between("dispatchDate", dates));
        }
        return BeanTransform.copyProperties(dispatchCarInfoSer.findByCis(dto), DispatchCarInfoBO.class);
    }

    @Override
    public List<SonPermissionObject> financeSonPermission() throws SerException {
        return dispatchCarInfoSer.financeSonPermission();
    }

    @Override
    public Boolean financeGuidePermission(GuidePermissionTO to) throws SerException {
        return dispatchCarInfoSer.financeGuidePermission(to);
    }
    @Override
    public List<DriverDispatchFeeBO> findDispatchFree(String area, String projectGroup, Integer year, Integer month) throws SerException{
        return dispatchCarInfoSer.findDispatchFree(area,projectGroup,year,month);
    }

    @Override
    public List<DriverDispatchsBO> findDispatchs(String area, String projectGroup, Integer year, Integer month) throws SerException{
        return dispatchCarInfoSer.findDispatchs(area,projectGroup,year,month);
    }


    @Override
    public Double findOilAmount(String oilCardCode, Integer year, Integer month) throws SerException {
        return dispatchCarInfoSer.findOilAmount(oilCardCode, year, month);
    }

    @Override
    public List<DriverInfoBO> findDriver() throws SerException {
        return dispatchCarInfoSer.findDriver();
    }

    @Override
    public List<UserBO> findAllEntry() throws SerException {
        return dispatchCarInfoSer.findAllEntry();
    }

    @Override
    public List<OilCardBasicCarBO> findAllOil() throws SerException {
        return dispatchCarInfoSer.findAllOil();
    }

//    @Override
//    public void copyServer() throws SerException {
//        dispatchCarInfoSer.copyServer();
//    }

    @Override
    public void copyDriver() throws SerException {
        dispatchCarInfoSer.copyDriver();
    }

    @Override
    public List<String> findAllProject() throws SerException {
        return dispatchCarInfoSer.findAllProject();
    }


    @Override
    public void budgetSugg(DispatchCarInfoTO dispatchCarInfoTO, CheckChangeCarTO to) throws SerException {
        dispatchCarInfoSer.budgetSugg(dispatchCarInfoTO,to);
    }

    @Override
    public void fundSugg(DispatchCarInfoTO dispatchCarInfoTO, PredictPayTO to) throws SerException {
        dispatchCarInfoSer.fundSugg(dispatchCarInfoTO,to);
    }

    @Override
    public void clientSugg(CheckChangeCarTO to) throws SerException {
        dispatchCarInfoSer.clientSugg(to);
    }

    @Override
    public void headSugg(CheckChangeCarTO to) throws SerException {
        dispatchCarInfoSer.headSugg(to);
    }

    @Override
    public void financialSugg(DispatchCarInfoTO dispatchCarInfoTO, CheckChangeCarTO to) throws SerException {
        dispatchCarInfoSer.financialSugg(dispatchCarInfoTO,to);
    }

    @Override
    public List<DispatchCarInfoBO> findWrongRecord(DispatchCarInfoDTO dto) throws SerException {
        return dispatchCarInfoSer.findWrongRecord(dto);
    }

    @Override
    public void correctMistake(DispatchCarInfoTO to) throws SerException {
        dispatchCarInfoSer.correctMistake(to);
    }

    @Override
    public void receivePaper(String id, Boolean isCorrect) throws SerException {
        dispatchCarInfoSer.receivePaper(id,isCorrect);
    }

    @Override
    public void mail(MailTO to) throws SerException {
        dispatchCarInfoSer.mail(to);
    }

    @Override
    public void leadExcel(List<DispatchCarInfoSetExcel> toList) throws SerException {
        dispatchCarInfoSer.leadExcel(toList);
    }

    @Override
    public byte[] exportExcel(ExportDispatchCarInfoTO to) throws SerException {
        return dispatchCarInfoSer.exportExcel(to);
    }

    @Override
    public byte[] templateExport() throws SerException {
        return dispatchCarInfoSer.templateExport();
    }

    @Override
    public List<PayDriverMoneyCollectBO> driverCollect(String startTime, String endTime) throws SerException {
        return dispatchCarInfoSer.driverCollect(startTime,endTime);
    }

    @Override
    public List<PayedCollectBO> collectPayed(String startTime, String endTime) throws SerException {
        return dispatchCarInfoSer.collectPayed(startTime,endTime);
    }

    @Override
    public byte[] exportExcel(CollectIntervalType collectIntervalType, CollectType collectType, ExportCollectPayedTO to) throws SerException {
        return dispatchCarInfoSer.exportExcel(collectIntervalType,collectType,to);
    }

    @Override
    public List<CollectDispatchcarBO> countCar(CollectDispatchcarDTO dispatchcarDTO) throws SerException {
        return dispatchCarInfoSer.countCar(dispatchcarDTO);
    }

    @Override
    public void delete(String id) throws SerException {
        dispatchCarInfoSer.delete(id);
    }

    @Override
    public List<DispatchCarInfoBO> findInformation( String area, String department, String day) throws SerException {
        return dispatchCarInfoSer.findInformation(area,department,day);
    }

    @Override
    public List<DispatchCarInfoBO> findInformation(String area, String department, LocalDate[] day) throws SerException {
        return dispatchCarInfoSer.findInformation(area,department,day);
    }

    @Override
    public List<DispatchCarInfoBO> findInformation(String department, LocalDate[] day) throws SerException {
        return dispatchCarInfoSer.findInformation(department,day);
    }

    @Override
    public Double findOilWear(String driver) throws SerException {
        return dispatchCarInfoSer.findOilWear(driver);
    }

    @Override
    public Double findBalance(String oilCardNumber) throws SerException {
        return dispatchCarInfoSer.findBalance(oilCardNumber);
    }

    @Override
    public Boolean findProjectAproval(String project) throws SerException {
        return dispatchCarInfoSer.findProjectAproval(project);
    }

    @Override
    public List<String> getAllDepartment() throws SerException {
        return dispatchCarInfoSer.getAllDepartment();
    }

    @Override
    public List<AreaBO> findArea() throws SerException {
        return dispatchCarInfoSer.findArea();
    }
}