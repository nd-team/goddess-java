package com.bjike.goddess.staffentry.action.staffentry;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffentry.api.EntryRegisterAPI;
import com.bjike.goddess.staffentry.bo.*;
import com.bjike.goddess.staffentry.dto.EntryRegisterDTO;
import com.bjike.goddess.staffentry.to.*;
import com.bjike.goddess.staffentry.vo.EntryRegisterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 入职登记
 *
 * @Author: [tanghaixiang]
 * @Date: [2017-03-09 13:58]
 * @Description: [入职登记]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("staffentry/entryregister")
public class EntryRegisterAction {

    @Autowired
    private EntryRegisterAPI entryRegisterAPI;

    /**
     * 获取所有入职登记
     *
     * @param entryRegisterDTO 入职登记dto
     * @return class entryRegisterBO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/listEntryRegister")
    public Result findListEntryRegister(EntryRegisterDTO entryRegisterDTO) throws ActException {
        try {
            List<EntryRegisterVO> entryRegisterVOList = BeanTransform.copyProperties(
                    entryRegisterAPI.listEntryRegister(entryRegisterDTO), EntryRegisterVO.class);
            return ActResult.initialize(entryRegisterVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
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
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/add")
    public Result addEntryRegister(@Valid EntryRegisterTO entryRegisterTO, FamilyMemberTO familyMemberTO, StudyExperienceTO studyExperienceTO,
                                   WorkExperienceTO workExperienceTO, CredentialTO credentialTO) throws ActException {
        try {
            EntryRegisterBO entryRegisterBO1 = entryRegisterAPI.insertEntryRegister(entryRegisterTO,
                    familyMemberTO, studyExperienceTO, workExperienceTO, credentialTO);
            return ActResult.initialize(entryRegisterBO1);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑员工入职
     *
     * @param entryRegisterTO   员工入职数据to
     * @param familyMemberTO    家庭成员数据to
     * @param studyExperienceTO 学习经历数据to
     * @param workExperienceTO  工作经历数据to
     * @param credentialTO      证书情况数据to
     * @return class entryRegisterBO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result editEntryRegister(@Valid EntryRegisterTO entryRegisterTO, FamilyMemberTO familyMemberTO, StudyExperienceTO studyExperienceTO,
                                    WorkExperienceTO workExperienceTO, CredentialTO credentialTO) throws ActException {
        try {
            EntryRegisterBO entryRegisterBO1 = entryRegisterAPI.editEntryRegister(entryRegisterTO,
                    familyMemberTO, studyExperienceTO, workExperienceTO, credentialTO);
            return ActResult.initialize(entryRegisterBO1);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 根据用户id删除员工入职登记记录
     *
     * @param id 用户id
     * @return
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result deleteEntryRegister(@PathVariable String id) throws ActException {
        try {
            entryRegisterAPI.removeEntryRegister(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查找某个员工入职登记
     *
     * @param id 员工入职登记id
     * @return class entryRegisterBO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/getEntryRegister")
    public Result findOneEntryRegister(@RequestParam String id) throws ActException {
        try {
            EntryRegisterVO entryRegisterVO = BeanTransform.copyProperties(
                    entryRegisterAPI.getEntryRegister(id), EntryRegisterVO.class);
            return ActResult.initialize(entryRegisterVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}
