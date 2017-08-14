package com.bjike.goddess.reportmanagement.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.reportmanagement.bo.RepayAnalyzeAdviceBO;
import com.bjike.goddess.reportmanagement.dto.RepayAnalyzeAdviceDTO;
import com.bjike.goddess.reportmanagement.service.RepayAnalyzeAdviceSer;
import com.bjike.goddess.reportmanagement.to.GuidePermissionTO;
import com.bjike.goddess.reportmanagement.to.RepayAnalyzeAdviceTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 偿还能力分析管理建议设计业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-30 04:04 ]
 * @Description: [ 偿还能力分析管理建议设计业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("repayAnalyzeAdviceApiImpl")
public class RepayAnalyzeAdviceApiImpl implements RepayAnalyzeAdviceAPI {
    @Autowired
    private RepayAnalyzeAdviceSer repayAnalyzeAdviceSer;

    @Override
    public List<RepayAnalyzeAdviceBO> list(RepayAnalyzeAdviceDTO dto) throws SerException {
        return repayAnalyzeAdviceSer.list(dto);
    }

    @Override
    public RepayAnalyzeAdviceBO save(RepayAnalyzeAdviceTO to) throws SerException {
        return repayAnalyzeAdviceSer.save(to);
    }

    @Override
    public void edit(RepayAnalyzeAdviceTO to) throws SerException {
        repayAnalyzeAdviceSer.edit(to);
    }

    @Override
    public void delete(String id) throws SerException {
        repayAnalyzeAdviceSer.delete(id);
    }

    @Override
    public RepayAnalyzeAdviceBO findByID(String id) throws SerException {
        return repayAnalyzeAdviceSer.findByID(id);
    }

    @Override
    public Long count(RepayAnalyzeAdviceDTO dto) throws SerException {
        return repayAnalyzeAdviceSer.count(dto);
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return repayAnalyzeAdviceSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return repayAnalyzeAdviceSer.guidePermission(guidePermissionTO);
    }
}