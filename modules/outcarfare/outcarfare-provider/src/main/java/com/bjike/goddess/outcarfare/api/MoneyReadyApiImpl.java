package com.bjike.goddess.outcarfare.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.outcarfare.bo.MoneyReadyBO;
import com.bjike.goddess.outcarfare.bo.MoneyReadyCountBO;
import com.bjike.goddess.outcarfare.dto.MoneyReadyDTO;
import com.bjike.goddess.outcarfare.service.MoneyReadySer;
import com.bjike.goddess.outcarfare.to.GuidePermissionTO;
import com.bjike.goddess.outcarfare.to.MoneyReadyTO;
import com.bjike.goddess.outcarfare.vo.SonPermissionObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 资金准备审核业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-05 02:41 ]
 * @Description: [ 资金准备审核业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("moneyReadyApiImpl")
public class MoneyReadyApiImpl implements MoneyReadyAPI {
    @Autowired
    private MoneyReadySer moneyReadySer;

    @Override
    public MoneyReadyBO save(MoneyReadyTO to) throws SerException {
        return moneyReadySer.save(to);
    }

    @Override
    public void edit(MoneyReadyTO to) throws SerException {
        moneyReadySer.edit(to);
    }

    @Override
    public void delete(String id) throws SerException {
        moneyReadySer.delete(id);
    }

    @Override
    public List<MoneyReadyBO> list(MoneyReadyDTO dto) throws SerException {
        return moneyReadySer.list(dto);
    }

    @Override
    public MoneyReadyBO findByID(String id) throws SerException {
        return moneyReadySer.findByID(id);
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return moneyReadySer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return moneyReadySer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countSum(MoneyReadyDTO dto) throws SerException {
        return moneyReadySer.countSum(dto);
    }

    @Override
    public List<MoneyReadyBO> delList(MoneyReadyDTO dto) throws SerException {
        return moneyReadySer.delList(dto);
    }

    @Override
    public Long delCount(MoneyReadyDTO dto) throws SerException {
        return moneyReadySer.delCount(dto);
    }

    @Override
    public void quartz() throws SerException {
        moneyReadySer.quartz();
    }

    @Override
    public List<MoneyReadyCountBO> departCount(MoneyReadyDTO dto) throws SerException {
        return moneyReadySer.departCount(dto);
    }

    @Override
    public List<MoneyReadyCountBO> areaCount(MoneyReadyDTO dto) throws SerException {
        return moneyReadySer.areaCount(dto);
    }

    @Override
    public void reback(String id) throws SerException {
        moneyReadySer.reback(id);
    }

    @Override
    public List<MoneyReadyBO> details(MoneyReadyDTO dto) throws SerException {
        return moneyReadySer.details(dto);
    }

    @Override
    public Set<String> findAllGroupTeams() throws SerException {
        return moneyReadySer.findAllGroupTeams();
    }

    @Override
    public Set<String> areas() throws SerException {
        return moneyReadySer.areas();
    }
}