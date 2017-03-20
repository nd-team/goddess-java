package com.bjike.goddess.staffentry.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffentry.bo.*;
import com.bjike.goddess.staffentry.dto.EntryRegisterDTO;
import com.bjike.goddess.staffentry.entity.EntryRegister;
import com.bjike.goddess.staffentry.service.EntryRegisterSer;
import com.bjike.goddess.staffentry.to.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 入职登记业务接口
 *
 * @Author: [tanghaixiang]
 * @Date: [2017-03-09 13:48]
 * @Description: [入职登记业务接口]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("entryRegisterApiImpl")
public class EntryRegisterApiImpl implements EntryRegisterAPI {

    @Autowired
    private EntryRegisterSer entryRegisterSer;

    @Override
    public List<EntryRegisterBO> listEntryRegister(EntryRegisterDTO entryRegisterDTO) throws SerException {
        List<EntryRegister> entryRegisters = entryRegisterSer.listEntryRegister(entryRegisterDTO);
        List<EntryRegisterBO> entryRegisterBOS = BeanTransform.copyProperties( entryRegisters , EntryRegisterBO.class  );

        return entryRegisterBOS;
    }

    @Override
    public EntryRegisterBO insertEntryRegister(EntryRegisterTO entryRegisterTO, FamilyMemberTO familyMemberTO, StudyExperienceTO studyExperienceTO,
                                               WorkExperienceTO workExperienceTO, CredentialTO credentialTO) throws SerException {
        return entryRegisterSer.insertEntryRegister(entryRegisterTO,familyMemberTO,studyExperienceTO,workExperienceTO,credentialTO);
    }

    @Override
    public EntryRegisterBO editEntryRegister(EntryRegisterTO entryRegisterTO, FamilyMemberTO familyMemberTO, StudyExperienceTO studyExperienceTO,
                                             WorkExperienceTO workExperienceTO, CredentialTO credentialTO) throws SerException {
        return entryRegisterSer.editEntryRegister(entryRegisterTO,familyMemberTO,studyExperienceTO,workExperienceTO,credentialTO);
    }

    @Override
    public void removeEntryRegister(String id) throws SerException {
        entryRegisterSer.removeEntryRegister(id);
    }

    @Override
    public EntryRegisterBO getEntryRegister(String id) throws SerException {
        return entryRegisterSer.getEntryRegister(id);
    }
}
