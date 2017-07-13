package com.bjike.goddess.reimbursementprepare.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.reimbursementprepare.bo.MoneyReadyBO;
import com.bjike.goddess.reimbursementprepare.bo.MoneyReadyCountBO;
import com.bjike.goddess.reimbursementprepare.dto.MoneyReadyDTO;
import com.bjike.goddess.reimbursementprepare.service.MoneyReadySer;
import com.bjike.goddess.reimbursementprepare.to.GuidePermissionTO;
import com.bjike.goddess.reimbursementprepare.to.MoneyReadyTO;
import com.bjike.goddess.reimbursementprepare.vo.SonPermissionObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 资金准备业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-03 03:07 ]
 * @Description: [ 资金准备业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("moneyReadyApiImpl")
public class MoneyReadyApiImpl implements MoneyReadyAPI {
    @Autowired
    private MoneyReadySer moneyReadySer;

    @Override
    public List<MoneyReadyCountBO> countContrast(MoneyReadyDTO dto) throws SerException {
        return moneyReadySer.countContrast(dto);
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
    public Long countSum(MoneyReadyDTO dto) throws SerException {
        return moneyReadySer.countSum(dto);
    }
}