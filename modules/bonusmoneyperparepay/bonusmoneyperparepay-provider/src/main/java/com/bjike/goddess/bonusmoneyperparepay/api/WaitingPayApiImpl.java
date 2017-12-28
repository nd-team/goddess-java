package com.bjike.goddess.bonusmoneyperparepay.api;

import com.bjike.goddess.bonusmoneyperparepay.bo.PerpareActualDifferencesBO;
import com.bjike.goddess.bonusmoneyperparepay.bo.WaitingBO;
import com.bjike.goddess.bonusmoneyperparepay.bo.WaitingPayBO;
import com.bjike.goddess.bonusmoneyperparepay.dto.WaitingPayDTO;
import com.bjike.goddess.bonusmoneyperparepay.excel.SonPermissionObject;
import com.bjike.goddess.bonusmoneyperparepay.service.WaitingPaySer;
import com.bjike.goddess.bonusmoneyperparepay.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 等待付款业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-06-30 05:32 ]
 * @Description: [ 等待付款业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("waitingPayApiImpl")
public class WaitingPayApiImpl implements WaitingPayAPI {

    @Autowired
    private WaitingPaySer waitingPaySer;


    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return waitingPaySer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return waitingPaySer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countWaiting(WaitingPayDTO waitingPayDTO) throws SerException {
        return waitingPaySer.countWaiting(waitingPayDTO);
    }

    @Override
    public Long countAlready(WaitingPayDTO waitingPayDTO) throws SerException {
        return waitingPaySer.countAlready(waitingPayDTO);
    }

    @Override
    public WaitingPayBO getOneById(String id) throws SerException {
        return waitingPaySer.getOneById(id);
    }

    @Override
    public List<WaitingPayBO> listWaiting(WaitingPayDTO waitingPayDTO) throws SerException {
        return waitingPaySer.listWaiting(waitingPayDTO);
    }

    @Override
    public void deleteWaiting(String id) throws SerException {
        waitingPaySer.deleteWaiting(id);
    }

    @Override
    public List<WaitingPayBO> list(WaitingPayDTO waitingPayDTO) throws SerException {
        return waitingPaySer.list(waitingPayDTO);
    }

    @Override
    public void payMoney(String id, Double payMoney) throws SerException {
        waitingPaySer.payMoney(id, payMoney);
    }

    @Override
    public List<WaitingBO> yearsCompare(Integer years) throws SerException {
        return waitingPaySer.yearsCompare(years);
    }
    @Override
    public byte[] exportExcel(Integer years,Integer startMonth, Integer endMonth) throws SerException {
        return waitingPaySer.exportExcel(years,startMonth, endMonth);
    }

    @Override
    public byte[] exportArealdyExcel(Integer years,Integer startMonth, Integer endMonth) throws SerException {
        return waitingPaySer.exportArealdyExcel(years,startMonth, endMonth);
    }

    @Override
    public List<WaitingBO> projectCompare(Integer years, Integer month, String[] projectGroup) throws SerException {
        return waitingPaySer.projectCompare(years, month, projectGroup);
    }

    @Override
    public List<WaitingBO> monthCompare(Integer years, Integer month) throws SerException {
        return waitingPaySer.monthCompare(years, month);
    }

    @Override
    public List<PerpareActualDifferencesBO> differencesCompare(Integer years, Integer month) throws SerException {
        return waitingPaySer.differencesCompare(years, month);
    }

    @Override
    public List<String> findAllProject() throws SerException {
        return waitingPaySer.findAllProject();
    }
}