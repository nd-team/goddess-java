package com.bjike.goddess.reportmanagement.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.reportmanagement.bo.DebtStructureAdviceBO;
import com.bjike.goddess.reportmanagement.dto.DebtStructureAdviceDTO;
import com.bjike.goddess.reportmanagement.service.DebtStructureAdviceSer;
import com.bjike.goddess.reportmanagement.to.DebtStructureAdviceTO;
import com.bjike.goddess.reportmanagement.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 负债与权益结构管理建议设计业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-30 04:56 ]
 * @Description: [ 负债与权益结构管理建议设计业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("debtStructureAdviceApiImpl")
public class DebtStructureAdviceApiImpl implements DebtStructureAdviceAPI {
    @Autowired
    private DebtStructureAdviceSer debtStructureAdviceSer;

    @Override
    public List<DebtStructureAdviceBO> list(DebtStructureAdviceDTO dto) throws SerException {
        return debtStructureAdviceSer.list(dto);
    }

    @Override
    public DebtStructureAdviceBO save(DebtStructureAdviceTO to) throws SerException {
        return debtStructureAdviceSer.save(to);
    }

    @Override
    public void edit(DebtStructureAdviceTO to) throws SerException {
        debtStructureAdviceSer.edit(to);
    }

    @Override
    public void delete(String id) throws SerException {
        debtStructureAdviceSer.delete(id);
    }

    @Override
    public DebtStructureAdviceBO findByID(String id) throws SerException {
        return debtStructureAdviceSer.findByID(id);
    }

    @Override
    public Long count(DebtStructureAdviceDTO dto) throws SerException {
        return debtStructureAdviceSer.count(dto);
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return debtStructureAdviceSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return debtStructureAdviceSer.guidePermission(guidePermissionTO);
    }
}