package com.bjike.goddess.receivable.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.receivable.bo.MainIncomeBO;
import com.bjike.goddess.receivable.dto.MainIncomeDTO;
import com.bjike.goddess.receivable.entity.MainIncome;
import com.bjike.goddess.receivable.service.MainIncomeSer;
import com.bjike.goddess.receivable.to.GuidePermissionTO;
import com.bjike.goddess.receivable.to.MainIncomeTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 主营业务收入业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-10 10:19 ]
 * @Description: [ 主营业务收入业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("mainIncomeApiImpl")
public class MainIncomeApiImpl implements MainIncomeAPI {
    @Autowired
    private MainIncomeSer mainIncomeSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return mainIncomeSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return mainIncomeSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long count(MainIncomeDTO dto) throws SerException {
        return mainIncomeSer.count(dto);
    }

    @Override
    public MainIncomeBO getOne(String id) throws SerException {
        return mainIncomeSer.getOne(id);
    }

    @Override
    public List<MainIncomeBO> list(MainIncomeDTO dto) throws SerException {
        return mainIncomeSer.list(dto);
    }

    @Override
    public MainIncomeBO add(MainIncomeTO to) throws SerException {
        return mainIncomeSer.add(to);
    }

    @Override
    public MainIncomeBO edit(MainIncomeTO to) throws SerException {
        return mainIncomeSer.edit(to);
    }

    @Override
    public void remove(String id) throws SerException {
        mainIncomeSer.remove(id);
    }
}