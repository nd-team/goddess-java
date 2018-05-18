package com.bjike.goddess.moneyprepare.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.moneyprepare.bo.*;
import com.bjike.goddess.moneyprepare.dto.FundPrepareDTO;
import com.bjike.goddess.moneyprepare.excel.SonPermissionObject;
import com.bjike.goddess.moneyprepare.service.FundPrepareSer;
import com.bjike.goddess.moneyprepare.to.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 资金准备业务接口实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-07-08 02:23 ]
 * @Description: [ 资金准备业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("fundPrepareApiImpl")
public class FundPrepareApiImpl implements FundPrepareAPI {

    @Autowired
    private FundPrepareSer fundPrepareSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return fundPrepareSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return fundPrepareSer.guidePermission(guidePermissionTO);
    }


    @Override
    public Long countFundPrepare(FundPrepareDTO fundPrepareDTO) throws SerException {
        return fundPrepareSer.countFundPrepare(fundPrepareDTO);
    }

    @Override
    public List<ProportionBO> listDetail(String id) throws SerException {
        return fundPrepareSer.listDetail(id);
    }

    @Override
    public List<FundPrepareBO> listFundPrepare(FundPrepareDTO fundPrepareDTO) throws SerException {
        return fundPrepareSer.listFundPrepare(fundPrepareDTO);
    }

    @Override
    public List<FundPrepareBO> addFundPrepare(FundPrepareObjectTO fundPrepareObjectTO) throws SerException {
        return fundPrepareSer.addFundPrepare(fundPrepareObjectTO);
    }

    @Override
    public FundPrepareBO editFundPrepare(FundPrepareTO fundPrepareTO) throws SerException {
        return fundPrepareSer.editFundPrepare(fundPrepareTO);
    }

    @Override
    public void deleteFundPrepare(String id) throws SerException {
        fundPrepareSer.deleteFundPrepare(id);
    }

    @Override
    public List<String> findFirstSubject() throws SerException {
        return fundPrepareSer.findFirstSubject();
    }

    @Override
    public List<String> findSecondSubject(String firstSubject) throws SerException {
        return fundPrepareSer.findSecondSubject(firstSubject);
    }

    @Override
    public FundPrepareBO getOneById(String id) throws SerException {
        return fundPrepareSer.getOneById(id);
    }

    @Override
    public List<FundPrepareWeekBO> weekCollect() throws SerException {
        return fundPrepareSer.weekCollect();
    }

    @Override
    public List<FundPrepareMonthBO> monthCollect() throws SerException {
        return fundPrepareSer.monthCollect();
    }

    @Override
    public List<FundPrepareYearBO> yearCollect() throws SerException {
        return fundPrepareSer.yearCollect();
    }

    @Override
    public List<FundPrepareanAlysisBO> analysis() throws SerException {
        return fundPrepareSer.analysis();
    }

    @Override
    public List<ProjectCollectBO> projectCollect() throws SerException {
        return fundPrepareSer.projectCollect();
    }

    @Override
    public List<ProportionBO> editProportion(ProportionObjectTO proportionObjectTO) throws SerException {
        return fundPrepareSer.editProportion(proportionObjectTO);
    }
}