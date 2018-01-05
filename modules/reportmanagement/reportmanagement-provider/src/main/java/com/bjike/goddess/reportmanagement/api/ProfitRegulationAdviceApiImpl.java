package com.bjike.goddess.reportmanagement.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.reportmanagement.bo.ProfitRegulationAdviceBO;
import com.bjike.goddess.reportmanagement.dto.ProfitRegulationAdviceDTO;
import com.bjike.goddess.reportmanagement.service.ProfitRegulationAdviceSer;
import com.bjike.goddess.reportmanagement.to.GuidePermissionTO;
import com.bjike.goddess.reportmanagement.to.ProfitRegulationAdviceTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 利润增减率分析管理建议设计业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-30 06:53 ]
 * @Description: [ 利润增减率分析管理建议设计业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("profitRegulationAdviceApiImpl")
public class ProfitRegulationAdviceApiImpl implements ProfitRegulationAdviceAPI {
    @Autowired
    private ProfitRegulationAdviceSer profitRegulationAdviceSer;

    @Override
    public List<ProfitRegulationAdviceBO> list(ProfitRegulationAdviceDTO dto) throws SerException {
        return profitRegulationAdviceSer.list(dto);
    }

    @Override
    public ProfitRegulationAdviceBO save(ProfitRegulationAdviceTO to) throws SerException {
        return profitRegulationAdviceSer.save(to);
    }

    @Override
    public void edit(ProfitRegulationAdviceTO to) throws SerException {
        profitRegulationAdviceSer.edit(to);
    }

    @Override
    public void delete(String id) throws SerException {
        profitRegulationAdviceSer.delete(id);
    }

    @Override
    public ProfitRegulationAdviceBO findByID(String id) throws SerException {
        return profitRegulationAdviceSer.findByID(id);
    }

    @Override
    public Long count(ProfitRegulationAdviceDTO dto) throws SerException {
        return profitRegulationAdviceSer.count(dto);
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return profitRegulationAdviceSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return profitRegulationAdviceSer.guidePermission(guidePermissionTO);
    }
}