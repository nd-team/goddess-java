package com.bjike.goddess.businsurance.api;

import com.bjike.goddess.businsurance.bo.TowerInsureBO;
import com.bjike.goddess.businsurance.dto.TowerInsureDTO;
import com.bjike.goddess.businsurance.service.TowerInsureSer;
import com.bjike.goddess.businsurance.to.TowerInsureTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 塔工意外险信息管理业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-22 09:30 ]
 * @Description: [ 塔工意外险信息管理业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("towerInsureApiImpl")
public class TowerInsureApiImpl implements TowerInsureAPI {

    @Autowired
    private TowerInsureSer towerInsureSer;

    @Override
    public Long countTowerInsure(TowerInsureDTO towerInsureDTO) throws SerException {
        return towerInsureSer.count(towerInsureDTO);
    }

    @Override
    public List<TowerInsureBO> listTowerInsure(TowerInsureDTO towerInsureDTO) throws SerException {
        return towerInsureSer.listTowerInsure(towerInsureDTO);
    }

    @Override
    public TowerInsureBO addTowerInsure(TowerInsureTO towerInsureTO) throws SerException {
        return towerInsureSer.addTowerInsure(towerInsureTO);
    }

    @Override
    public TowerInsureBO editTowerInsure(TowerInsureTO towerInsureTO) throws SerException {
        return towerInsureSer.editTowerInsure(towerInsureTO);
    }

    @Override
    public void deleteTowerInsure(String id) throws SerException {
        towerInsureSer.deleteTowerInsure(id);
    }

    @Override
    public TowerInsureBO editAccount(TowerInsureTO towerInsureTO) throws SerException {
        return towerInsureSer.editAccount(towerInsureTO);
    }

    @Override
    public TowerInsureBO editContext(TowerInsureTO towerInsureTO) throws SerException {
        return towerInsureSer.editContext(towerInsureTO);
    }

    @Override
    public TowerInsureBO applicant(TowerInsureTO towerInsureTO) throws SerException {
        return towerInsureSer.applicant(towerInsureTO);
    }

    @Override
    public TowerInsureBO editInsurePerson(TowerInsureTO towerInsureTO) throws SerException {
        return towerInsureSer.editInsurePerson(towerInsureTO);
    }

    @Override
    public TowerInsureBO editBenefit(TowerInsureTO towerInsureTO) throws SerException {
        return towerInsureSer.editBenefit(towerInsureTO);
    }

    @Override
    public TowerInsureBO editSaleOrgan(TowerInsureTO towerInsureTO) throws SerException {
        return towerInsureSer.editSaleOrgan(towerInsureTO);
    }

    @Override
    public TowerInsureBO getTowerInsure(String id) throws SerException {
        return towerInsureSer.getTowerInsure(id);
    }
    
}