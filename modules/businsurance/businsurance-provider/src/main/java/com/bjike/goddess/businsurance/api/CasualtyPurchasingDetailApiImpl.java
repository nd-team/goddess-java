package com.bjike.goddess.businsurance.api;

import com.bjike.goddess.businsurance.bo.CasualtyPurchasingDetailBO;
import com.bjike.goddess.businsurance.bo.SummaryBO;
import com.bjike.goddess.businsurance.dto.CasualtyPurchasingDetailDTO;
import com.bjike.goddess.businsurance.entity.CasualtyPurchasingDetail;
import com.bjike.goddess.businsurance.service.CasualtyPurchasingDetailSer;
import com.bjike.goddess.businsurance.to.CasualtyPurchasingDetailTO;
import com.bjike.goddess.businsurance.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 团体意外险购买详情业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-26 10:44 ]
 * @Description: [ 团体意外险购买详情业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("casualtyPurchasingDetailApiImpl")
public class CasualtyPurchasingDetailApiImpl implements CasualtyPurchasingDetailAPI {
   @Autowired
   private CasualtyPurchasingDetailSer casualtyPurchasingDetailSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return casualtyPurchasingDetailSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return casualtyPurchasingDetailSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countDetail(CasualtyPurchasingDetailDTO casualtyPurchasingDetailDTO) throws SerException {
        return casualtyPurchasingDetailSer.countDetail(casualtyPurchasingDetailDTO);
    }

    @Override
    public CasualtyPurchasingDetailBO getOneDetail(String id) throws SerException {
        return casualtyPurchasingDetailSer.getOneDetail(id);
    }

    @Override
    public List<CasualtyPurchasingDetailBO> listDetail(CasualtyPurchasingDetailDTO casualtyPurchasingDetailDTO) throws SerException {
        return casualtyPurchasingDetailSer.listDetail(casualtyPurchasingDetailDTO);
    }

    @Override
    public CasualtyPurchasingDetailBO addDetail(CasualtyPurchasingDetailTO casualtyPurchasingDetailTO) throws SerException {
        return casualtyPurchasingDetailSer.addDetail(casualtyPurchasingDetailTO);
    }

    @Override
    public CasualtyPurchasingDetailBO editDetail(CasualtyPurchasingDetailTO casualtyPurchasingDetailTO) throws SerException {
        return casualtyPurchasingDetailSer.editDetail(casualtyPurchasingDetailTO);
    }

    @Override
    public byte[] exportExcel() throws SerException {
        return casualtyPurchasingDetailSer.exportExcel();
    }

    @Override
    public byte[] templateExport() throws SerException {
        return casualtyPurchasingDetailSer.templateExport();
    }

    @Override
    public void importExcel(List<CasualtyPurchasingDetailTO> casualtyPurchasingDetailTOS) throws SerException {
        casualtyPurchasingDetailSer.importExcel(casualtyPurchasingDetailTOS);
    }

    @Override
    public List<String> findEmpNo() throws SerException {
        return casualtyPurchasingDetailSer.findEmpNo();
    }

    @Override
    public CasualtyPurchasingDetailBO findByEmpNo(String empNum) throws SerException {
        return casualtyPurchasingDetailSer.findByEmpNo(empNum);
    }

    @Override
    public void checkStatus() throws SerException {
        casualtyPurchasingDetailSer.checkStatus();
    }

    @Override
    public List<SummaryBO> summaDay(String summationDate) throws SerException {
        return casualtyPurchasingDetailSer.summaDay(summationDate);
    }

    @Override
    public List<SummaryBO> summaWeek(Integer year, Integer month, Integer week) throws SerException {
        return casualtyPurchasingDetailSer.summaWeek(year,month,week);
    }

    @Override
    public List<SummaryBO> summaMonth(Integer year, Integer month) throws SerException {
        return casualtyPurchasingDetailSer.summaMonth(year,month);
    }

    @Override
    public List<SummaryBO> summaTotal(String endDate) throws SerException {
        return casualtyPurchasingDetailSer.summaTotal(endDate);
    }
}