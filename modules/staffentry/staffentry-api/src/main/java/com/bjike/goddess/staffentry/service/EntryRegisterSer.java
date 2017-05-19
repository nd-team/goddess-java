package com.bjike.goddess.staffentry.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.staffentry.bo.*;
import com.bjike.goddess.staffentry.dto.EntryRegisterDTO;
import com.bjike.goddess.staffentry.entity.EntryRegister;
import com.bjike.goddess.staffentry.to.*;

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
public interface EntryRegisterSer extends Ser<EntryRegister, EntryRegisterDTO> {

    /**
     * 入职登记列表总条数
     */
    default Long countEntryRegister(EntryRegisterDTO entryRegisterDTO) throws SerException {
        return null;
    }

    /**
     * 根据id获取入职登记
     *
     * @return class EntryRegisterBO
     */
    default EntryRegisterBO getOne(String id ) throws SerException {
        return null;
    }
    /**
     * 获取所有入职登记
     * @param entryRegisterDTO 入职登记dto
     * @return class entryRegisterBO
     * @throws SerException
     */
    default List<EntryRegister> listEntryRegister(EntryRegisterDTO entryRegisterDTO) throws SerException {
        return null;
    }

    /**
     * 添加员工入职
     *
     * @param entryRegisterTO   员工入职数据to
     * @param familyMemberTO    家庭成员数据to
     * @param studyExperienceTO 学习经历数据to
     * @param workExperienceTO  工作经历数据to
     * @param credentialTO      证书情况数据to
     * @return class entryRegisterBO
     * @throws SerException
     */
    default EntryRegisterBO insertEntryRegister(EntryRegisterTO entryRegisterTO, FamilyMemberTO familyMemberTO, StudyExperienceTO studyExperienceTO,
                                                WorkExperienceTO workExperienceTO, CredentialTO credentialTO) throws SerException {
        return null;
    }

    ;


    /**
     * 编辑员工入职
     *
     * @param entryRegisterTO   员工入职数据to
     * @param familyMemberTO    家庭成员数据to
     * @param studyExperienceTO 学习经历数据to
     * @param workExperienceTO  工作经历数据to
     * @param credentialTO      证书情况数据to
     * @return class entryRegisterBO
     * @throws SerException
     */
    default EntryRegisterBO editEntryRegister(EntryRegisterTO entryRegisterTO, FamilyMemberTO familyMemberTO, StudyExperienceTO studyExperienceTO,
                                              WorkExperienceTO workExperienceTO, CredentialTO credentialTO) throws SerException {
        return null;
    }

    ;


    /**
     * 根据id删除入职登记
     *
     * @param id
     * @throws SerException
     */
    default void removeEntryRegister(String id) throws SerException {
        return;
    }

    /**
     * 根据id查找某个员工入职登记
     *
     * @param id 员工入职登记id
     * @return class entryRegisterBO
     * @throws SerException
     */
    default EntryRegisterBO getEntryRegisterDetail(String id) throws SerException {
        return null;
    }

}
