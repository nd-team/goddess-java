package com.bjike.goddess.staffentry.action.staffentry;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffentry.api.EntryRegisterAPI;
import com.bjike.goddess.staffentry.api.StaffEntryRegisterAPI;
import com.bjike.goddess.staffentry.bo.*;
import com.bjike.goddess.staffentry.dto.EntryRegisterDTO;
import com.bjike.goddess.staffentry.entity.Credential;
import com.bjike.goddess.staffentry.entity.EntryRegister;
import com.bjike.goddess.staffentry.entity.StaffEntryRegister;
import com.bjike.goddess.staffentry.to.*;
import com.bjike.goddess.staffentry.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    private StaffEntryRegisterAPI staffEntryRegisterAPI;



    /**
     * 功能导航权限
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = entryRegisterAPI.guidePermission(guidePermissionTO);
            if(! isHasPermission ){
                //int code, String msg
                return new ActResult(0,"没有权限",false );
            }else{
                return new ActResult(0,"有权限",true );
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

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
    @LoginAuth
    @PostMapping("v1/add")
    public Result addEntryRegister(@Validated(EntryRegisterUtilTO.TestBaseInfo.class) EntryRegisterUtilTO entryRegisterUtilTO ) throws ActException {

        //组装数据
        EntryRegisterTO entryRegisterTO = new EntryRegisterTO();
        FamilyMemberTO familyMemberTO = new FamilyMemberTO ();
        StudyExperienceTO studyExperienceTO = new StudyExperienceTO ();
        WorkExperienceTO workExperienceTO = new WorkExperienceTO();
        CredentialTO credentialTO = new CredentialTO ();

        //基本信息
        entryRegisterTO = BeanTransform.copyProperties( entryRegisterUtilTO, EntryRegisterTO.class);
        assembleData(entryRegisterUtilTO,  familyMemberTO,  studyExperienceTO,
                 workExperienceTO,  credentialTO);
        try {
            EntryRegisterBO entryRegisterBO1 = entryRegisterAPI.insertEntryRegister(entryRegisterTO,
                    familyMemberTO, studyExperienceTO, workExperienceTO, credentialTO);
            return ActResult.initialize(BeanTransform.copyProperties(entryRegisterBO1,EntryRegisterVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    private void assembleData(EntryRegisterUtilTO entryRegisterUtilTO, FamilyMemberTO familyMemberTO, StudyExperienceTO studyExperienceTO,
        WorkExperienceTO workExperienceTO, CredentialTO credentialTO){

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
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editEntryRegister(@Validated(EntryRegisterUtilTO.TestBaseInfo.class) EntryRegisterUtilTO entryRegisterUtilTO) throws ActException {
        //组装数据
        EntryRegisterTO entryRegisterTO = new EntryRegisterTO();
        FamilyMemberTO familyMemberTO = new FamilyMemberTO ();
        StudyExperienceTO studyExperienceTO = new StudyExperienceTO ();
        WorkExperienceTO workExperienceTO = new WorkExperienceTO();
        CredentialTO credentialTO = new CredentialTO ();
        //基本信息
        entryRegisterTO = BeanTransform.copyProperties( entryRegisterUtilTO, EntryRegisterTO.class);
        assembleData(entryRegisterUtilTO,  familyMemberTO,  studyExperienceTO,
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
     * @param id 列表id
     * @des 根据列表id删除员工入职登记记录
     * @version v1
     */
    @LoginAuth
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
            EntryRegisterBO bo = entryRegisterAPI.getEntryRegisterDetail(id);
            List<FamilyMemberBO> fbo = bo.getFamilyMemberBOList();
            List<StudyExperienceBO> sbo = bo.getStudyExperienceBOList();
            List<WorkExperienceBO> wbo = bo.getWorkExperienceBOList();
            List<CredentialBO> cbo = bo.getCredentialBOList();

            EntryRegisterVO entryRegisterVO = BeanTransform.copyProperties(
                    bo, EntryRegisterVO.class);
            entryRegisterVO.setFamilyMemberVOList( BeanTransform.copyProperties(fbo , FamilyMemberVO.class));
            entryRegisterVO.setStudyExperienceVOList( BeanTransform.copyProperties(sbo , StudyExperienceVO.class));
            entryRegisterVO.setWorkExperienceVOList( BeanTransform.copyProperties(wbo , WorkExperienceVO.class));
            entryRegisterVO.setCredentialVOList( BeanTransform.copyProperties(cbo , CredentialVO.class));
            return ActResult.initialize(entryRegisterVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 获取所有的员工编号
     *
     * @des 获取所有的员工编号
     * @version v1
     */
    @GetMapping("v1/findEmpNum")
    public Result findEmpNum() throws ActException {
        try {
            List<String> empNums = staffEntryRegisterAPI.findEmpNum();
            return ActResult.initialize(empNums);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 根据员工编号获取数据
     *
     * @des 根据员工编号获取数据
     * @version v1
     */
    @GetMapping("v1/findByEmpNum")
    public Result findByEmpNum(@RequestParam String empNums) throws ActException {
        try {
            LinkDateStaffEntryBO linkDateStaffEntryBO = staffEntryRegisterAPI.findByEmpNum(empNums);
            return ActResult.initialize(linkDateStaffEntryBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 入职管理日汇总
     *
     * @param date 日期
     * @return class EntrySummaryVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/summarize/day")
    public Result summarizeDay(String date, HttpServletRequest request) throws ActException {
        try {
            List<EntrySummaryBO> boList = staffEntryRegisterAPI.summaDay(date);
            List<EntrySummaryVO> voList = BeanTransform.copyProperties(boList, EntrySummaryVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 入职管理周汇总
     *
     * @param year 年份
     * @param month 月份
     * @param week 周期
     * @return class EntrySummaryVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/summarize/week")
    public Result summarizeDay(Integer year,Integer month,Integer week, HttpServletRequest request) throws ActException {
        try {
            List<EntrySummaryBO> boList = staffEntryRegisterAPI.summaWeek(year,month,week);
            List<EntrySummaryVO> voList = BeanTransform.copyProperties(boList, EntrySummaryVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 入职管理月汇总
     *
     * @param year 年份
     * @param month 月份
     * @return class EntrySummaryVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/summarize/month")
    public Result summarizeMonth(Integer year,Integer month, HttpServletRequest request) throws ActException {
        try {
            List<EntrySummaryBO> boList = staffEntryRegisterAPI.summaMonth(year,month);
            List<EntrySummaryVO> voList = BeanTransform.copyProperties(boList, EntrySummaryVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 入职管理累计汇总
     *
     * @param date 截止日期
     * @return class EntrySummaryVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/summarize/total")
    public Result summarizeMonth(String date, HttpServletRequest request) throws ActException {
        try {
            List<EntrySummaryBO> boList = staffEntryRegisterAPI.summaTotal(date);
            List<EntrySummaryVO> voList = BeanTransform.copyProperties(boList, EntrySummaryVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}
