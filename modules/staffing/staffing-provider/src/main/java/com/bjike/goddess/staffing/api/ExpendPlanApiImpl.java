package com.bjike.goddess.staffing.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffing.bo.ExpendPlanBO;
import com.bjike.goddess.staffing.dto.ExpendPlanDTO;
import com.bjike.goddess.staffing.service.ExpendPlanSer;
import com.bjike.goddess.staffing.to.ExpendPlanTO;
import com.bjike.goddess.staffing.to.GuidePermissionTO;
import com.bjike.goddess.staffing.vo.SonPermissionObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 人工成本计划业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-31 11:47 ]
 * @Description: [ 人工成本计划业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("expendPlanApiImpl")
public class ExpendPlanApiImpl implements ExpendPlanAPI {
    @Autowired
    private ExpendPlanSer expendPlanSer;

    @Override
    public List<ExpendPlanBO> list(ExpendPlanDTO dto) throws SerException {
        return expendPlanSer.list(dto);
    }

    @Override
    public ExpendPlanBO save(ExpendPlanTO to) throws SerException {
        return expendPlanSer.save(to);
    }

    @Override
    public void edit(ExpendPlanTO to) throws SerException {
        expendPlanSer.edit(to);
    }

    @Override
    public void delete(String id) throws SerException {
        expendPlanSer.delete(id);
    }

    @Override
    public ExpendPlanBO findByID(String id) throws SerException {
        return expendPlanSer.findByID(id);
    }

    @Override
    public Long count(ExpendPlanDTO dto) throws SerException {
        return expendPlanSer.count(dto);
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return expendPlanSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return expendPlanSer.guidePermission(guidePermissionTO);
    }
}