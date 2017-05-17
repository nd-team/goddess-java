package com.bjike.goddess.staffentry.action.staffentry;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffentry.api.EntryRegisterAPI;
import com.bjike.goddess.staffentry.bo.*;
import com.bjike.goddess.staffentry.dto.EntryRegisterDTO;
import com.bjike.goddess.staffentry.entity.EntryRegister;
import com.bjike.goddess.staffentry.to.*;
import com.bjike.goddess.staffentry.vo.EntryRegisterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
@RequestMapping("entryregister")
public class EntryRegisterAction {

    @Autowired
    private EntryRegisterAPI entryRegisterAPI;

    /**
     * 入职登记列表总条数
     *
     * @param entryRegisterDTO 入职登记信息dto
     * @des 获取所有入职登记信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(EntryRegisterDTO entryRegisterDTO) throws ActException {
        try {
            Long count = entryRegisterAPI.countEntryRegister(entryRegisterDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个入职登记
     *
     * @param id 入职登记信息id
     * @return class EntryRegisterVO
     * @des 根据id查询入职登记
     * @version v1
     */
    @GetMapping("v1/getOne/{id}")
    public Result getOne(@PathVariable String id) throws ActException {
        try {
            EntryRegisterVO entryRegisterVOList = BeanTransform.copyProperties(
                    entryRegisterAPI.getOne(id), EntryRegisterVO.class);
            return ActResult.initialize(entryRegisterVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 入职登记列表
     *
     * @param entryRegisterDTO 入职登记dto
     * @des 获取所有入职登记
     * @return class EntryRegisterVO
     * @version v1
     */
    @GetMapping("v1/list")
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
     * @param entryRegisterUtilTO   员工入职数据to
     * @des 添加员工入职
     * @return class EntryRegisterVO
     * @version v1
     */
    @PostMapping("v1/add")
    public Result addEntryRegister(@Validated(EntryRegisterUtilTO.TestBaseInfo.class) EntryRegisterUtilTO entryRegisterUtilTO ) throws ActException {

        //组装数据
        EntryRegisterTO entryRegisterTO = new EntryRegisterTO();
        FamilyMemberTO familyMemberTO = new FamilyMemberTO ();
        StudyExperienceTO studyExperienceTO = new StudyExperienceTO ();
        WorkExperienceTO workExperienceTO = new WorkExperienceTO();
        CredentialTO credentialTO = new CredentialTO ();

        assembleData(entryRegisterUtilTO, entryRegisterTO,  familyMemberTO,  studyExperienceTO,
                 workExperienceTO,  credentialTO);
        try {
            EntryRegisterBO entryRegisterBO1 = entryRegisterAPI.insertEntryRegister(entryRegisterTO,
                    familyMemberTO, studyExperienceTO, workExperienceTO, credentialTO);
            return ActResult.initialize(BeanTransform.copyProperties(entryRegisterBO1,EntryRegisterVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    private void assembleData(EntryRegisterUtilTO entryRegisterUtilTO,EntryRegisterTO entryRegisterTO, FamilyMemberTO familyMemberTO, StudyExperienceTO studyExperienceTO,
        WorkExperienceTO workExperienceTO, CredentialTO credentialTO){
        //基本信息
        entryRegisterTO = BeanTransform.copyProperties( entryRegisterUtilTO, EntryRegisterTO.class);
        //家庭成员
        familyMemberTO.setTitles( entryRegisterUtilTO.getTitles());
        familyMemberTO.setNames( entryRegisterUtilTO.getNames());
        familyMemberTO.setAges( entryRegisterUtilTO.getAges());
        familyMemberTO.setUnits( entryRegisterUtilTO.getUnits());
        familyMemberTO.setPositions( entryRegisterUtilTO.getPositions());
        familyMemberTO.setPhones( entryRegisterUtilTO.getPhones());
        //学习经历
        studyExperienceTO.setStudyStartTimes( entryRegisterUtilTO.getStudyStartTimes());
        studyExperienceTO.setStudyEndTimes( entryRegisterUtilTO.getStudyEndTimes());
        studyExperienceTO.setSchools( entryRegisterUtilTO.getSchools());
        studyExperienceTO.setCertificates( entryRegisterUtilTO.getCertificates());
        //工作经历
        workExperienceTO.setWorkStartTimes( entryRegisterUtilTO.getWorkStartTimes());
        workExperienceTO.setWorkEndTimes( entryRegisterUtilTO.getWorkEndTimes());
        workExperienceTO.setFirms( entryRegisterUtilTO.getFirms());
        workExperienceTO.setJobDescriptions( entryRegisterUtilTO.getJobDescriptions());
        //证书情况
        credentialTO.setNameses( entryRegisterUtilTO.getNameses());
        credentialTO.setObtainTimes( entryRegisterUtilTO.getObtainTimes());
    }


    /**
     * 编辑员工入职
     *
     * @param entryRegisterUtilTO   员工入职数据to
     * @des 编辑员工入职
     * @return class EntryRegisterVO
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result editEntryRegister(@Validated(EntryRegisterUtilTO.TestBaseInfo.class) EntryRegisterUtilTO entryRegisterUtilTO) throws ActException {
        //组装数据
        EntryRegisterTO entryRegisterTO = new EntryRegisterTO();
        FamilyMemberTO familyMemberTO = new FamilyMemberTO ();
        StudyExperienceTO studyExperienceTO = new StudyExperienceTO ();
        WorkExperienceTO workExperienceTO = new WorkExperienceTO();
        CredentialTO credentialTO = new CredentialTO ();

        assembleData(entryRegisterUtilTO, entryRegisterTO,  familyMemberTO,  studyExperienceTO,
                workExperienceTO,  credentialTO);
        try {
            EntryRegisterBO entryRegisterBO1 = entryRegisterAPI.editEntryRegister(entryRegisterTO,
                    familyMemberTO, studyExperienceTO, workExperienceTO, credentialTO);
            return ActResult.initialize(BeanTransform.copyProperties(entryRegisterBO1,EntryRegisterVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 删除
     *
     * @param id 用户id
     * @des 根据用户id删除员工入职登记记录
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
     * 查找入职登记详细信息
     *
     * @param id 员工入职登记id
     * @des 根据id查找某个员工入职登记
     * @return class EntryRegisterVO
     * @version v1
     */
    @GetMapping("v1/getEntryRegister/{id}")
    public Result findOneEntryRegister(@PathVariable String id) throws ActException {
        try {
            EntryRegisterVO entryRegisterVO = BeanTransform.copyProperties(
                    entryRegisterAPI.getEntryRegister(id), EntryRegisterVO.class,true);
            return ActResult.initialize(entryRegisterVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}
