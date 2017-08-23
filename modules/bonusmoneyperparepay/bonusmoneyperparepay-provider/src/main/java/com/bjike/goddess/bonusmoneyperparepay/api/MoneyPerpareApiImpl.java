package com.bjike.goddess.bonusmoneyperparepay.api;

import com.bjike.goddess.bonusmoneyperparepay.bo.MoneyPerpareBO;
import com.bjike.goddess.bonusmoneyperparepay.bo.MoneyPerpareContrastBO;
import com.bjike.goddess.bonusmoneyperparepay.bo.PerpareBO;
import com.bjike.goddess.bonusmoneyperparepay.dto.MoneyPerpareDTO;
import com.bjike.goddess.bonusmoneyperparepay.service.MoneyPerpareSer;
import com.bjike.goddess.bonusmoneyperparepay.to.GuidePermissionTO;
import com.bjike.goddess.bonusmoneyperparepay.to.MoneyPerpareTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 奖金资金准备与支付业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-06-30 04:44 ]
 * @Description: [ 奖金资金准备与支付业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("moneyPerpareApiImpl")
public class MoneyPerpareApiImpl implements MoneyPerpareAPI {

    @Autowired
    private MoneyPerpareSer moneyPerpareSer;


    @Override
    public Boolean sonPermission() throws SerException {
        return moneyPerpareSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return moneyPerpareSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countMoney(MoneyPerpareDTO moneyPerpareDTO) throws SerException {
        return moneyPerpareSer.countMoney(moneyPerpareDTO);
    }

    @Override
    public MoneyPerpareBO getOneById(String id) throws SerException {
        return moneyPerpareSer.getOneById(id);
    }

    @Override
    public List<MoneyPerpareBO> listMoneyPerpare(MoneyPerpareDTO moneyPerpareDTO) throws SerException {
        return moneyPerpareSer.listMoneyPerpare(moneyPerpareDTO);
    }

    @Override
    public MoneyPerpareBO addMoneyPerpare(MoneyPerpareTO moneyPerpareTO) throws SerException {
        return moneyPerpareSer.addMoneyPerpare(moneyPerpareTO);
    }

    @Override
    public MoneyPerpareBO editMoneyPerpare(MoneyPerpareTO moneyPerpareTO) throws SerException {
        return moneyPerpareSer.editMoneyPerpare(moneyPerpareTO);
    }

    @Override
    public void deleteMoneyPerpare(String id) throws SerException {
        moneyPerpareSer.deleteMoneyPerpare(id);
    }

    @Override
    public List<PerpareBO> projectCompare(Integer years, Integer month, String[] projectGroup) throws SerException {
        return moneyPerpareSer.projectCompare(years, month, projectGroup);
    }

    @Override
    public List<PerpareBO> monthCompare(Integer years, Integer month) throws SerException {
        return moneyPerpareSer.monthCompare(years, month);
    }

    @Override
    public List<PerpareBO> yearsCompare(Integer years) throws SerException {
        return moneyPerpareSer.yearsCompare(years);
    }

    @Override
    public List<MoneyPerpareContrastBO> contrastCompare(Integer years, Integer month) throws SerException {
        return moneyPerpareSer.contrastCompare(years, month);
    }

    @Override
    public List<String> findAllProject() throws SerException {
        return moneyPerpareSer.findAllProject();
    }
}