package com.bjike.goddess.assistance.api;

import com.bjike.goddess.assistance.bo.AssistanceStandardBO;
import com.bjike.goddess.assistance.dto.AssistanceStandardDTO;
import com.bjike.goddess.assistance.service.AssistanceStandardSer;
import com.bjike.goddess.assistance.to.AssistanceStandardTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 补助标准业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-13 09:29 ]
 * @Description: [ 补助标准业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("assistanceStandardApiImpl")
public class AssistanceStandardApiImpl implements AssistanceStandardAPI {

    @Autowired
    private AssistanceStandardSer assistanceStandardSer;

    @Override
    public Long countAssistanceStandard(AssistanceStandardDTO assistanceStandardDTO) throws SerException {
        return assistanceStandardSer.countAssistanceStandard( assistanceStandardDTO);
    }

    @Override
    public AssistanceStandardBO getOneById(String id) throws SerException {
        return assistanceStandardSer.getOneById(id);
    }

    @Override
    public List<AssistanceStandardBO> listAssistanceStandard(AssistanceStandardDTO assistanceStandardDTO) throws SerException {
        return assistanceStandardSer.listAssistanceStandard(assistanceStandardDTO);
    }

    @Override
    public AssistanceStandardBO addAssistanceStandard(AssistanceStandardTO assistanceStandardTO) throws SerException {
        return assistanceStandardSer.addAssistanceStandard(assistanceStandardTO);
    }

    @Override
    public AssistanceStandardBO editAssistanceStandard(AssistanceStandardTO assistanceStandardTO) throws SerException {
        return assistanceStandardSer.editAssistanceStandard(assistanceStandardTO);
    }

    @Override
    public void deleteAssistanceStandard(String id) throws SerException {
        assistanceStandardSer.deleteAssistanceStandard(id);
    }

    @Override
    public List<AssistanceStandardBO> getAgeStands() throws SerException {
        return assistanceStandardSer.getAgeStands();
    }
}