package com.bjike.goddess.projectprocing.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectprocing.bo.AllotmentNodeDataBO;
import com.bjike.goddess.projectprocing.bo.ScreeningSettleProgressManageBO;
import com.bjike.goddess.projectprocing.bo.SettleProgressManageBO;
import com.bjike.goddess.projectprocing.dto.SettleProgressManageDTO;
import com.bjike.goddess.projectprocing.service.SettleProgressManageSer;
import com.bjike.goddess.projectprocing.to.ScheduleDelayDataTO;
import com.bjike.goddess.projectprocing.to.SettleProgressManageTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 结算进度管理业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 02:22 ]
 * @Description: [ 结算进度管理业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("settleProgressManageApiImpl")
public class SettleProgressManageApiImpl implements SettleProgressManageAPI {
    @Autowired
    private SettleProgressManageSer settleProgressManageSer;

    @Override
    public byte[] exportExcel(String outUnit) throws SerException {
        return settleProgressManageSer.exportExcel(outUnit);
    }

    @Override
    public Long countManage(SettleProgressManageDTO settleProgressManageDTO) throws SerException {
        return settleProgressManageSer.countManage(settleProgressManageDTO);
    }

    @Override
    public SettleProgressManageBO getOneById(String id) throws SerException {
        return settleProgressManageSer.getOneById(id);
    }

    @Override
    public List<SettleProgressManageBO> listManage(SettleProgressManageDTO settleProgressManageDTO) throws SerException {
        return settleProgressManageSer.listManage(settleProgressManageDTO);
    }

    @Override
    public SettleProgressManageBO addManage(SettleProgressManageTO settleProgressManageTO) throws SerException {
        return settleProgressManageSer.addManage(settleProgressManageTO);
    }

    @Override
    public SettleProgressManageBO editManage(SettleProgressManageTO settleProgressManageTO) throws SerException {
        return settleProgressManageSer.editManage(settleProgressManageTO);
    }

    @Override
    public void deleteManage(String id) throws SerException {
        settleProgressManageSer.deleteManage(id);
    }

    @Override
    public List<String> findDispatName() throws SerException {
        return settleProgressManageSer.findDispatName();
    }

    @Override
    public List<String> findOperatorName() throws SerException {
        return settleProgressManageSer.findOperatorName();
    }

    @Override
    public List<String> findArea() throws SerException {
        return settleProgressManageSer.findArea();
    }

    @Override
    public List<String> findOutUnit() throws SerException {
        return settleProgressManageSer.findOutUnit();
    }

    @Override
    public List<String> findSaleContractNo() throws SerException {
        return settleProgressManageSer.findSaleContractNo();
    }

    @Override
    public List<String> findContractNo() throws SerException {
        return settleProgressManageSer.findContractNo();
    }

    @Override
    public List<String> findProgress() throws SerException {
        return settleProgressManageSer.findProgress();
    }

    @Override
    public List<ScreeningSettleProgressManageBO> listByOutUnit(String outUnit) throws SerException {
        return settleProgressManageSer.listByOutUnit(outUnit);
    }

    @Override
    public List<AllotmentNodeDataBO> findAllNodeById(String id) throws SerException {
        return settleProgressManageSer.findAllNodeById(id);
    }

    @Override
    public SettleProgressManageBO findByContractNo(String contractNo) throws SerException {
        return settleProgressManageSer.findByContractNo(contractNo);
    }

    @Override
    public void importExcel(List<SettleProgressManageTO> settleProgressManageTOS) throws SerException {
        settleProgressManageSer.importExcel(settleProgressManageTOS);
    }

    @Override
    public void scheduleDelay(ScheduleDelayDataTO scheduleDelayDataTO) throws SerException {
        settleProgressManageSer.scheduleDelay(scheduleDelayDataTO);
    }
}