package com.bjike.goddess.staffentry.action.staffentry;

import com.alibaba.dubbo.rpc.RpcContext;
import com.bjike.goddess.common.api.constant.RpcCommon;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.staffentry.api.EntryRegisterAPI;
import com.bjike.goddess.staffentry.api.StaffEntryRegisterAPI;
import com.bjike.goddess.staffentry.bo.*;
import com.bjike.goddess.staffentry.dto.EntryRegisterDTO;
import com.bjike.goddess.staffentry.excel.EntryRegisterExcel;
import com.bjike.goddess.staffentry.to.*;
import com.bjike.goddess.staffentry.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
public class EntryRegisterAction extends BaseFileAction{

    @Autowired
    private EntryRegisterAPI entryRegisterAPI;
    @Autowired
    private StaffEntryRegisterAPI staffEntryRegisterAPI;


    /**
     * 功能导航权限
     *
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = entryRegisterAPI.guidePermission(guidePermissionTO);
            if (!isHasPermission) {
                //int code, String msg
                return new ActResult(0, "没有权限", false);
            } else {
                return new ActResult(0, "有权限", true);
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
     * @return class EntryRegisterVO
     * @des 获取所有入职登记
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
     * @param entryRegisterUtilTO 员工入职数据to
     * @return class EntryRegisterVO
     * @des 添加员工入职
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addEntryRegister(@Validated(EntryRegisterUtilTO.TestBaseInfo.class) EntryRegisterUtilTO entryRegisterUtilTO) throws ActException {

        //组装数据
        EntryRegisterTO entryRegisterTO = new EntryRegisterTO();
        FamilyMemberTO familyMemberTO = new FamilyMemberTO();
        StudyExperienceTO studyExperienceTO = new StudyExperienceTO();
        WorkExperienceTO workExperienceTO = new WorkExperienceTO();
        CredentialTO credentialTO = new CredentialTO();

        //基本信息
        entryRegisterTO = BeanTransform.copyProperties(entryRegisterUtilTO, EntryRegisterTO.class);
        assembleData(entryRegisterUtilTO, familyMemberTO, studyExperienceTO,
                workExperienceTO, credentialTO);
        try {
            EntryRegisterBO entryRegisterBO1 = entryRegisterAPI.insertEntryRegister(entryRegisterTO,
                    familyMemberTO, studyExperienceTO, workExperienceTO, credentialTO);
            return ActResult.initialize(BeanTransform.copyProperties(entryRegisterBO1, EntryRegisterVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    private void assembleData(EntryRegisterUtilTO entryRegisterUtilTO, FamilyMemberTO familyMemberTO, StudyExperienceTO studyExperienceTO,
                              WorkExperienceTO workExperienceTO, CredentialTO credentialTO) {

        //家庭成员
        familyMemberTO.setTitles(entryRegisterUtilTO.getTitles());
        familyMemberTO.setNames(entryRegisterUtilTO.getNames());
        familyMemberTO.setAges(entryRegisterUtilTO.getAges());
        familyMemberTO.setUnits(entryRegisterUtilTO.getUnits());
        familyMemberTO.setPositions(entryRegisterUtilTO.getPositions());
        familyMemberTO.setPhones(entryRegisterUtilTO.getPhones());
        //学习经历
        studyExperienceTO.setStudyStartTimes(entryRegisterUtilTO.getStudyStartTimes());
        studyExperienceTO.setStudyEndTimes(entryRegisterUtilTO.getStudyEndTimes());
        studyExperienceTO.setSchools(entryRegisterUtilTO.getSchools());
        studyExperienceTO.setCertificates(entryRegisterUtilTO.getCertificates());
        //工作经历
        workExperienceTO.setWorkStartTimes(entryRegisterUtilTO.getWorkStartTimes());
        workExperienceTO.setWorkEndTimes(entryRegisterUtilTO.getWorkEndTimes());
        workExperienceTO.setFirms(entryRegisterUtilTO.getFirms());
        workExperienceTO.setJobDescriptions(entryRegisterUtilTO.getJobDescriptions());
        //证书情况
        credentialTO.setNameses(entryRegisterUtilTO.getNameses());
        credentialTO.setObtainTimes(entryRegisterUtilTO.getObtainTimes());
    }


    /**
     * 编辑员工入职
     *
     * @param entryRegisterUtilTO 员工入职数据to
     * @return class EntryRegisterVO
     * @des 编辑员工入职
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editEntryRegister(@Validated(EntryRegisterUtilTO.TestBaseInfo.class) EntryRegisterUtilTO entryRegisterUtilTO) throws ActException {
        //组装数据
        EntryRegisterTO entryRegisterTO = new EntryRegisterTO();
        FamilyMemberTO familyMemberTO = new FamilyMemberTO();
        StudyExperienceTO studyExperienceTO = new StudyExperienceTO();
        WorkExperienceTO workExperienceTO = new WorkExperienceTO();
        CredentialTO credentialTO = new CredentialTO();
        //基本信息
        entryRegisterTO = BeanTransform.copyProperties(entryRegisterUtilTO, EntryRegisterTO.class);
        assembleData(entryRegisterUtilTO, familyMemberTO, studyExperienceTO,
                workExperienceTO, credentialTO);
        try {
            EntryRegisterBO entryRegisterBO1 = entryRegisterAPI.editEntryRegister(entryRegisterTO,
                    familyMemberTO, studyExperienceTO, workExperienceTO, credentialTO);
            return ActResult.initialize(BeanTransform.copyProperties(entryRegisterBO1, EntryRegisterVO.class, true));
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
     * @return class EntryRegisterVO
     * @des 根据id查找某个员工入职登记
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
            entryRegisterVO.setFamilyMemberVOList(BeanTransform.copyProperties(fbo, FamilyMemberVO.class));
            entryRegisterVO.setStudyExperienceVOList(BeanTransform.copyProperties(sbo, StudyExperienceVO.class));
            entryRegisterVO.setWorkExperienceVOList(BeanTransform.copyProperties(wbo, WorkExperienceVO.class));
            entryRegisterVO.setCredentialVOList(BeanTransform.copyProperties(cbo, CredentialVO.class));
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
     * chenjunhao
     * 获取所有入职人员的姓名
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/names")
    public Result names() throws ActException {
        try {
            return ActResult.initialize(entryRegisterAPI.names());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * excel模板下载
     *
     * @des 下载模板入职登记
     * @version v1
     */
    @GetMapping("v1/templateExport")
    public Result templateExport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "入职登记模板.xlsx";
            super.writeOutFile(response, entryRegisterAPI.templateExport(), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }
    /**
     * 导入Excel
     *
     * @param request 注入HttpServletRequest对象
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/importExcel")
    public Result importExcel(HttpServletRequest request) throws ActException {
        try {
            String token=request.getHeader(RpcCommon.USER_TOKEN).toString();
            List<InputStream> inputStreams = super.getInputStreams(request);
            InputStream is = inputStreams.get(1);
            Excel excel = new Excel(0, 1);
            List<EntryRegisterExcel> tos = ExcelUtil.mergeExcelToClazz(is, EntryRegisterExcel.class, excel);
            List<EntryRegisterUtilTO> tocs = new ArrayList<>();
            Set<String> empNum = new HashSet<>();
            for (EntryRegisterExcel entryRegisterExcel:tos){
                empNum.add(entryRegisterExcel.getEmpNumber());
            }
            for (String num : empNum){
                List<EntryRegisterExcel> entryRegisterExcels = new ArrayList<>();
                List<String> titles = new ArrayList<>();
                List<String> names = new ArrayList<>();
                List<Integer> ages = new ArrayList<>();
                List<String> units = new ArrayList<>();
                List<String> positions = new ArrayList<>();
                List<String> phones = new ArrayList<>();
                List<String> studyStartTimes = new ArrayList<>();
                List<String> studyEndTimes = new ArrayList<>();
                List<String> schools = new ArrayList<>();
                List<String> certificates = new ArrayList<>();
                List<String> workStartTimes = new ArrayList<>();
                List<String> workEndTimes = new ArrayList<>();
                List<String> firms = new ArrayList<>();
                List<String> jobDescriptions = new ArrayList<>();
                List<String> nameses = new ArrayList<>();
                List<String> obtainTimes = new ArrayList<>();
                for(EntryRegisterExcel str : tos){
                    if(str.getEmpNumber().equals(num)){
                        entryRegisterExcels.add(str);
                        titles.add(str.getTitle());
                        names.add(str.getName());
                        ages.add(str.getAge());
                        units.add(str.getUnit());
                        positions.add(str.getPositionf());
                        phones.add(str.getPhonef());
                        studyStartTimes.add(str.getStartTime()==null?null:str.getStartTime().toString());
                        studyEndTimes.add(str.getEndTime()==null?null:str.getEndTime().toString());
                        schools.add(str.getSchool());
                        certificates.add(str.getCertificate());
                        workStartTimes.add(str.getWorkStartTime()==null?null:str.getWorkStartTime().toString());
                        workEndTimes.add(str.getWorkEndTime()==null?null:str.getWorkEndTime().toString());
                        firms.add(str.getFirm());
                        jobDescriptions.add(str.getJobDescription());
                        nameses.add(str.getName1());
                        obtainTimes.add(str.getObtainTime()==null?null:str.getObtainTime().toString());
                    }
                }
                EntryRegisterUtilTO entryRegisterUtilTO = BeanTransform.copyProperties(entryRegisterExcels.get(0),EntryRegisterUtilTO.class,"birthday","graduationDate","inductionDate");
                entryRegisterUtilTO.setBirthday(tos.get(0).getBirthday().toString());
                entryRegisterUtilTO.setGraduationDate(tos.get(0).getGraduationDate().toString());
                entryRegisterUtilTO.setInductionDate(tos.get(0).getInductionDate().toString());
                entryRegisterUtilTO.setTitles(titles);
                entryRegisterUtilTO.setNames(names);
                entryRegisterUtilTO.setAges(ages);
                entryRegisterUtilTO.setUnits(units);
                entryRegisterUtilTO.setPositions(positions);
                entryRegisterUtilTO.setPhones(phones);
                entryRegisterUtilTO.setStudyStartTimes(studyStartTimes);
                entryRegisterUtilTO.setStudyEndTimes(studyEndTimes);
                entryRegisterUtilTO.setSchools(schools);
                entryRegisterUtilTO.setCertificates(certificates);
                entryRegisterUtilTO.setWorkStartTimes(workStartTimes);
                entryRegisterUtilTO.setWorkEndTimes(workEndTimes);
                entryRegisterUtilTO.setFirms(firms);
                entryRegisterUtilTO.setJobDescriptions(jobDescriptions);
                entryRegisterUtilTO.setNameses(nameses);
                entryRegisterUtilTO.setObtainTimes(obtainTimes);
                RpcContext.getContext().setAttachment(RpcCommon.USER_TOKEN, token);
                addEntryRegister(entryRegisterUtilTO);
            }

            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}
