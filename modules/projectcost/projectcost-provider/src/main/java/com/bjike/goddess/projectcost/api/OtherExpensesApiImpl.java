package com.bjike.goddess.projectcost.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectcost.bo.OtherExpensesBO;
import com.bjike.goddess.projectcost.dto.OtherExpensesDTO;
import com.bjike.goddess.projectcost.service.OtherExpensesSer;
import com.bjike.goddess.projectcost.to.FindTO;
import com.bjike.goddess.projectcost.to.GuidePermissionTO;
import com.bjike.goddess.projectcost.to.OtherExpensesTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 其他费用业务接口实现
 *
 * @Author: [ 邓钧仁 ]
 * @Date: [ 2017-05-04 05:02 ]
 * @Description: [ 其他费用业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("projectOtherExpensesApiImpl")
public class OtherExpensesApiImpl implements OtherExpensesAPI {

    @Autowired
    private OtherExpensesSer otherExpensesSer;

    @Override
    public OtherExpensesBO save(OtherExpensesTO to) throws SerException {
        return otherExpensesSer.save(to);
    }

    @Override
    public OtherExpensesBO update(OtherExpensesTO to) throws SerException {
        return otherExpensesSer.update(to);
    }

    @Override
    public OtherExpensesBO delete(String id) throws SerException {
        return otherExpensesSer.delete(id);
    }

    @Override
    public List<OtherExpensesBO> maps(OtherExpensesDTO dto) throws SerException {
        return otherExpensesSer.maps(dto);
    }

    @Override
    public OtherExpensesBO getById(String id) throws SerException {
        return otherExpensesSer.getById(id);
    }

    @Override
    public Long getTotal() throws SerException {
        return otherExpensesSer.getTotal();
    }

    @Override
    public List<OtherExpensesBO> findByTO(FindTO to) throws SerException {
        return otherExpensesSer.findByTO(to);
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return otherExpensesSer.guidePermission( guidePermissionTO );
    }
}