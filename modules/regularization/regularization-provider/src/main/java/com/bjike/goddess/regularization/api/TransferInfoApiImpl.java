package com.bjike.goddess.regularization.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.regularization.bo.SummationBO;
import com.bjike.goddess.regularization.bo.TransferInfoBO;
import com.bjike.goddess.regularization.dto.TransferInfoDTO;
import com.bjike.goddess.regularization.entity.TransferInfo;
import com.bjike.goddess.regularization.service.TransferInfoSer;
import com.bjike.goddess.regularization.to.GuidePermissionTO;
import com.bjike.goddess.regularization.to.TransferInfoTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 转正人员信息业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-12 02:20 ]
 * @Description: [ 转正人员信息业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("transferInfoApiImpl")
public class TransferInfoApiImpl implements TransferInfoAPI {
    @Autowired
    private TransferInfoSer transferInfoSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return transferInfoSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return transferInfoSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countTrans(TransferInfoDTO transferInfoDTO) throws SerException {
        return transferInfoSer.countTrans(transferInfoDTO);
    }

    @Override
    public TransferInfoBO getOne(String id) throws SerException {
        return transferInfoSer.getOne(id);
    }

    @Override
    public List<TransferInfoBO> list(TransferInfoDTO dto) throws SerException {
        return transferInfoSer.list(dto);
    }

    @Override
    public TransferInfoBO findByEmpNo(String empNo) throws SerException {
        return transferInfoSer.findByEmpNo(empNo);
    }

    @Override
    public void saveTransferInfo(TransferInfoTO transferInfoTO) throws SerException {
        transferInfoSer.saveTransferInfo(transferInfoTO);
    }

    @Override
    public void followUp(TransferInfoTO transferInfoTO) throws SerException {
        transferInfoSer.followUp(transferInfoTO);
    }

    @Override
    public void welfareAssess(TransferInfoTO transferInfoTO) throws SerException {
        transferInfoSer.welfareAssess(transferInfoTO);
    }

    @Override
    public void planAssess(TransferInfoTO transferInfoTO) throws SerException {
        transferInfoSer.planAssess(transferInfoTO);
    }

    @Override
    public void budgetAssess(TransferInfoTO transferInfoTO) throws SerException {
        transferInfoSer.budgetAssess(transferInfoTO);
    }

    @Override
    public void moduleRespon(TransferInfoTO transferInfoTO) throws SerException {
        transferInfoSer.moduleRespon(transferInfoTO);
    }

    @Override
    public void projectManage(TransferInfoTO transferInfoTO) throws SerException {
        transferInfoSer.projectManage(transferInfoTO);
    }

    @Override
    public void genManage(TransferInfoTO transferInfoTO) throws SerException {
        transferInfoSer.genManage(transferInfoTO);
    }

    @Override
    public void interview(TransferInfoTO transferInfoTO) throws SerException {
        transferInfoSer.interview(transferInfoTO);
    }

    @Override
    public void check() throws SerException {
        transferInfoSer.check();
    }

    @Override
    public List<SummationBO> summaDay(String summDate) throws SerException {
        return transferInfoSer.summaDay(summDate);
    }

    @Override
    public List<SummationBO> summaWeek(Integer year, Integer month, Integer week) throws SerException {
        return transferInfoSer.summaWeek(year,month,week);
    }

    @Override
    public List<SummationBO> summaMonth(Integer year, Integer month) throws SerException {
        return transferInfoSer.summaMonth(year,month);
    }

    @Override
    public List<SummationBO> summaTotal(String endDate) throws SerException {
        return transferInfoSer.summaTotal(endDate);
    }
}