package com.bjike.goddess.reportmanagement.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.reportmanagement.bo.ProfitIndicatorAdviceBO;
import com.bjike.goddess.reportmanagement.dto.ProfitIndicatorAdviceDTO;
import com.bjike.goddess.reportmanagement.service.ProfitIndicatorAdviceSer;
import com.bjike.goddess.reportmanagement.to.GuidePermissionTO;
import com.bjike.goddess.reportmanagement.to.ProfitIndicatorAdviceTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 利润分析指标管理建议设计业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-30 05:24 ]
 * @Description: [ 利润分析指标管理建议设计业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("profitIndicatorAdviceApiImpl")
public class ProfitIndicatorAdviceApiImpl implements ProfitIndicatorAdviceAPI {
    @Autowired
    private ProfitIndicatorAdviceSer profitIndicatorAdviceSer;

    @Override
    public List<ProfitIndicatorAdviceBO> list(ProfitIndicatorAdviceDTO dto) throws SerException {
        return profitIndicatorAdviceSer.list(dto);
    }

    @Override
    public ProfitIndicatorAdviceBO save(ProfitIndicatorAdviceTO to) throws SerException {
        return profitIndicatorAdviceSer.save(to);
    }

    @Override
    public void edit(ProfitIndicatorAdviceTO to) throws SerException {
        profitIndicatorAdviceSer.edit(to);
    }

    @Override
    public void delete(String id) throws SerException {
        profitIndicatorAdviceSer.delete(id);
    }

    @Override
    public ProfitIndicatorAdviceBO findByID(String id) throws SerException {
        return profitIndicatorAdviceSer.findByID(id);
    }

    @Override
    public Long count(ProfitIndicatorAdviceDTO dto) throws SerException {
        return profitIndicatorAdviceSer.count(dto);
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return profitIndicatorAdviceSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return profitIndicatorAdviceSer.guidePermission(guidePermissionTO);
    }
}