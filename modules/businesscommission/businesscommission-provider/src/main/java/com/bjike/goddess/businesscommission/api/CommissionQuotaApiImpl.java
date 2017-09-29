//package com.bjike.goddess.businesscommission.api;
//
//import com.bjike.goddess.businesscommission.bo.CommissionQuotaBO;
//import com.bjike.goddess.businesscommission.dto.CommissionQuotaDTO;
//import com.bjike.goddess.businesscommission.service.CommissionQuotaSer;
//import com.bjike.goddess.businesscommission.to.CommissionQuotaTO;
//import com.bjike.goddess.businesscommission.to.GuidePermissionTO;
//import com.bjike.goddess.common.api.exception.SerException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.util.CollectionUtils;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
///**
// * 业务提成定额表业务接口实现
// *
// * @Author: [ zhuangkaiqin ]
// * @Date: [ 2017-06-29 04:49 ]
// * @Description: [ 业务提成定额表业务接口实现 ]
// * @Version: [ v1.0.0 ]
// * @Copy: [ com.bjike ]
// */
//@Service("commissionQuotaApiImpl")
//public class CommissionQuotaApiImpl implements CommissionQuotaAPI {
//
//    @Autowired
//    private CommissionQuotaSer commissionQuotaSer;
//
//
//    @Override
//    public Long countCommissionQuota(CommissionQuotaDTO commissionQuotaDTO) throws SerException {
//        return commissionQuotaSer.countCommissionQuota(commissionQuotaDTO);
//    }
//
//    @Override
//    public CommissionQuotaBO getOneById(String id) throws SerException {
//        return commissionQuotaSer.getOneById(id);
//    }
//
//    @Override
//    public List<CommissionQuotaBO> listCommissionQuota(CommissionQuotaDTO commissionQuotaDTO) throws SerException {
//        return commissionQuotaSer.listCommissionQuota(commissionQuotaDTO);
//    }
//
//    @Override
//    public CommissionQuotaBO addCommissionQuota(CommissionQuotaTO commissionQuotaTO) throws SerException {
//        return commissionQuotaSer.addCommissionQuota(commissionQuotaTO);
//    }
//
//    @Override
//    public CommissionQuotaBO editCommissionQuota(CommissionQuotaTO commissionQuotaTO) throws SerException {
//        return commissionQuotaSer.editCommissionQuota(commissionQuotaTO);
//    }
//
//    @Override
//    public void deleteCommissionQuota(String id) throws SerException {
//        commissionQuotaSer.deleteCommissionQuota(id);
//    }
//
//    @Override
//    public CommissionQuotaBO importExcel(List<CommissionQuotaTO> commissionQuotaTO) throws SerException {
//        return commissionQuotaSer.importExcel(commissionQuotaTO);
//    }
//
//    @Override
//    public byte[] exportExcel(CommissionQuotaDTO dto) throws SerException {
//        return commissionQuotaSer.exportExcel(dto);
//    }
//
//    @Override
//    public byte[] templateExport() throws SerException {
//        return commissionQuotaSer.templateExport();
//    }
//
//    @Override
//    public Boolean sonPermission() throws SerException {
//        return commissionQuotaSer.sonPermission();
//    }
//
//    @Override
//    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
//        return commissionQuotaSer.guidePermission(guidePermissionTO);
//    }
//
//    @Override
//    public List<String> listInformationProvide() throws SerException {
//        return commissionQuotaSer.listInformationProvide();
//    }
//
//    @Override
//    public List<String> listBusinessContracting() throws SerException {
//        return commissionQuotaSer.listBusinessContracting();
//    }
//
//    @Override
//    public List<String> listBusinessNegotiation() throws SerException {
//        return commissionQuotaSer.listBusinessNegotiation();
//    }
//
//    @Override
//    public List<String> listMaintenance() throws SerException {
//        return commissionQuotaSer.listMaintenance();
//    }
//
//    @Override
//    public List<CommissionQuotaBO> listAreas() throws SerException {
//        return commissionQuotaSer.listAreas();
//    }
//
//    @Override
//    public List<String> getArea() throws SerException {
//        List<CommissionQuotaBO> commissionQuotaBOList = commissionQuotaSer.listAreas();
//        List<String> list = new ArrayList<>(0);
//        if (!CollectionUtils.isEmpty(commissionQuotaBOList)) {
//            for (CommissionQuotaBO bo : commissionQuotaBOList) {
//                list.add(bo.getArea());
//            }
//        }
//        return list;
//    }
//
//    @Override
//    public List<String> getName() throws SerException {
//        List<CommissionQuotaBO> commissionQuotaBOList = commissionQuotaSer.listAreas();
//        List<String> list = new ArrayList<>(0);
//        if (!CollectionUtils.isEmpty(commissionQuotaBOList)) {
//            for (CommissionQuotaBO bo : commissionQuotaBOList) {
//                list.add(bo.getProjectName());
//            }
//        }
//        return list;
//    }
//
//    @Override
//    public List<Double> getActualAmount() throws SerException {
//        List<CommissionQuotaBO> commissionQuotaBOList = commissionQuotaSer.listAreas();
//        List<Double> list = new ArrayList<>(0);
//        if (!CollectionUtils.isEmpty(commissionQuotaBOList)) {
//            list=commissionQuotaBOList.stream().map(CommissionQuotaBO::getActualAmount).distinct().collect(Collectors.toList());
//        }
//        return list;
//    }
//
//}