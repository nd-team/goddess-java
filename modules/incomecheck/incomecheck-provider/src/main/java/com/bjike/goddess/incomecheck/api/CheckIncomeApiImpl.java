package com.bjike.goddess.incomecheck.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.incomecheck.bo.CheckIncomeBO;
import com.bjike.goddess.incomecheck.dto.CheckIncomeDTO;
import com.bjike.goddess.incomecheck.service.CheckIncomeSer;
import com.bjike.goddess.incomecheck.to.CheckIncomeTO;
import com.bjike.goddess.incomecheck.to.GuidePermissionTO;
import com.bjike.goddess.incomecheck.vo.SonPermissionObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 收入核算资金回笼业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-29 09:54 ]
 * @Description: [ 收入核算资金回笼业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("checkIncomeApiImpl")
public class CheckIncomeApiImpl implements CheckIncomeAPI {


    @Autowired
    private CheckIncomeSer checkIncomeSer;

    @Override
    public Long countCheckIncome(CheckIncomeDTO checkIncomeDTO) throws SerException {
        return checkIncomeSer.countCheckIncome(checkIncomeDTO);
    }

    @Override
    public CheckIncomeBO getOneById(String id) throws SerException {
        return checkIncomeSer.getOneById(id);
    }

    @Override
    public List<CheckIncomeBO> listCheckIncome(CheckIncomeDTO checkIncomeDTO) throws SerException {
        return checkIncomeSer.listCheckIncome(checkIncomeDTO);
    }

    @Override
    public CheckIncomeBO addCheckIncome(CheckIncomeTO checkIncomeTO) throws SerException {
        return checkIncomeSer.addCheckIncome(checkIncomeTO);
    }

    @Override
    public CheckIncomeBO editCheckIncome(CheckIncomeTO checkIncomeTO) throws SerException {
        return checkIncomeSer.editCheckIncome(checkIncomeTO);
    }

    @Override
    public void deleteCheckIncome(String id) throws SerException {
        checkIncomeSer.deleteCheckIncome(id);
    }

    @Override
    public List<CheckIncomeBO> collectArea(CheckIncomeDTO checkIncomeDTO) throws SerException {
        return checkIncomeSer.collectArea(checkIncomeDTO);
    }

    @Override
    public List<CheckIncomeBO> collectGroup(CheckIncomeDTO checkIncomeDTO) throws SerException {
        return checkIncomeSer.collectGroup(checkIncomeDTO);
    }

    @Override
    public List<CheckIncomeBO> collectProject(CheckIncomeDTO checkIncomeDTO) throws SerException {
        return checkIncomeSer.collectProject(checkIncomeDTO);
    }

    @Override
    public List<CheckIncomeBO> areaDiff(CheckIncomeDTO checkIncomeDTO) throws SerException {
        return checkIncomeSer.areaDiff(checkIncomeDTO);
    }

    @Override
    public List<CheckIncomeBO> groupDiff(CheckIncomeDTO checkIncomeDTO) throws SerException {
        return checkIncomeSer.groupDiff(checkIncomeDTO);
    }

    @Override
    public List<CheckIncomeBO> projectDiff(CheckIncomeDTO checkIncomeDTO) throws SerException {
        return checkIncomeSer.projectDiff(checkIncomeDTO);
    }

    @Override
    public List<String> areaList() throws SerException {
        return checkIncomeSer.areaList();
    }

    @Override
    public List<String> groupList() throws SerException {
        return checkIncomeSer.groupList();
    }

    @Override
    public List<String> projectList() throws SerException {
        return checkIncomeSer.projectList();
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return checkIncomeSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return checkIncomeSer.guidePermission(guidePermissionTO);
    }
}