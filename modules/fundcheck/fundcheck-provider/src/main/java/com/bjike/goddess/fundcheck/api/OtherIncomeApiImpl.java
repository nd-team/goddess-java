package com.bjike.goddess.fundcheck.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.fundcheck.bo.OtherIncomeBO;
import com.bjike.goddess.fundcheck.dto.OtherIncomeDTO;
import com.bjike.goddess.fundcheck.service.OtherIncomeSer;
import com.bjike.goddess.fundcheck.to.GuidePermissionTO;
import com.bjike.goddess.fundcheck.to.OtherIncomeCollectTO;
import com.bjike.goddess.fundcheck.to.OtherIncomeTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 其他收入业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-30 04:53 ]
 * @Description: [ 其他收入业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("otherIncomeApiImpl")
public class OtherIncomeApiImpl implements OtherIncomeAPI {
    @Autowired
    private OtherIncomeSer otherIncomeSer;
    @Override
    public Boolean sonPermission() throws SerException {
        return otherIncomeSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return otherIncomeSer.guidePermission( guidePermissionTO );
    }
    @Override
    public Long count(OtherIncomeDTO otherIncomeDTO) throws SerException {
        return otherIncomeSer.count(otherIncomeDTO);
    }

    @Override
    public OtherIncomeBO getOne(String id) throws SerException {
        return otherIncomeSer.getOne(id);
    }

    @Override
    public List<OtherIncomeBO> findList(OtherIncomeDTO otherIncomeDTO) throws SerException {
        return otherIncomeSer.findList(otherIncomeDTO);
    }

    @Override
    public OtherIncomeBO insert(OtherIncomeTO otherIncomeTO) throws SerException {
        return otherIncomeSer.insert(otherIncomeTO);
    }

    @Override
    public OtherIncomeBO edit(OtherIncomeTO otherIncomeTO) throws SerException {
        return otherIncomeSer.edit(otherIncomeTO);
    }

    @Override
    public void remove(String id) throws SerException {
        otherIncomeSer.remove(id);
    }
    @Override
    public List<OtherIncomeBO> collect(OtherIncomeCollectTO to) throws SerException {
        return otherIncomeSer.collect(to);
    }
}