package com.bjike.goddess.intromanage.action.intromanage;

import com.alibaba.dubbo.rpc.RpcContext;
import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.common.api.constant.RpcCommon;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.intromanage.api.IndividualResumeAPI;
import com.bjike.goddess.intromanage.bo.IndividualResumeBO;
import com.bjike.goddess.intromanage.bo.SummationBO;
import com.bjike.goddess.intromanage.dto.IndividualResumeDTO;
import com.bjike.goddess.intromanage.excel.IndividualResumeExcel;
import com.bjike.goddess.intromanage.excel.SonPermissionObject;
import com.bjike.goddess.intromanage.to.*;
import com.bjike.goddess.intromanage.vo.*;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import com.bjike.goddess.staffentry.api.EntryRegisterAPI;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.storage.vo.FileVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 个人简介
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-28 10:19 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("individualresume")
public class IndividualResumeAct extends BaseFileAction {

    @Autowired
    private IndividualResumeAPI individualResumeAPI;

    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;
    @Autowired
    private ModuleAPI moduleAPI;
    @Autowired
    private EntryRegisterAPI entryRegisterAPI;
    @Autowired
    private FileAPI fileAPI;

    /**
     * 模块设置导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/setButtonPermission")
    public Result setButtonPermission() throws ActException {
        List<SonPermissionObject> list = new ArrayList<>();
        try {
            SonPermissionObject obj = new SonPermissionObject();
            obj.setName("cuspermission");
            obj.setDescribesion("设置");
            Boolean isHasPermission = userSetPermissionAPI.checkSetPermission();
            if (!isHasPermission) {
                //int code, String msg
                obj.setFlag(false);
            } else {
                obj.setFlag(true);
            }
            list.add(obj);
            return new ActResult(0, "设置权限", list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 下拉导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/sonPermission")
    public Result sonPermission() throws ActException {
        try {

            List<SonPermissionObject> hasPermissionList = individualResumeAPI.sonPermission();
            return new ActResult(0, "有权限", hasPermissionList);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

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

            Boolean isHasPermission = individualResumeAPI.guidePermission(guidePermissionTO);
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
     * 根据id查询个人简介
     *
     * @param id 个人简介唯一标识
     * @return class IndividualResumeVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/individualresume/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            IndividualResumeBO bo = individualResumeAPI.findResumeById(id);
            IndividualResumeVO vo = BeanTransform.copyProperties(bo, IndividualResumeVO.class, request);

            //查询员工奖励
            List<StaffRewardVO> honorAndQualitieVOs = BeanTransform.copyProperties(bo.getStaffRewardBOS(), StaffRewardVO.class);
            //查询员工荣誉
            List<StaffHonorVO> mainBusinessIntroVOS = BeanTransform.copyProperties(bo.getStaffHonorBOS(), StaffHonorVO.class);
            //查询教育经历
            List<EducateExperienceVO> successStoriesVOS = BeanTransform.copyProperties(bo.getEducateExperienceBOS(), EducateExperienceVO.class);
            //查询工作经历
            List<WorkExperienceVO> customerAndPartnerVOS = BeanTransform.copyProperties(bo.getWorkExperienceBOS(), WorkExperienceVO.class);
            //查询证书情况
            List<CredentialSituationVO> communicationPathVOS = BeanTransform.copyProperties(bo.getCredentialSituationBOS(), CredentialSituationVO.class);

            vo.setStaffRewardVOS(honorAndQualitieVOs);
            vo.setStaffHonorVOS(mainBusinessIntroVOS);
            vo.setEducateExperienceVOS(successStoriesVOS);
            vo.setWorkExperienceVOS(customerAndPartnerVOS);
            vo.setCredentialSituationVOS(communicationPathVOS);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 个人简介dto
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/count")
    public Result count(@Validated IndividualResumeDTO dto, BindingResult result) throws ActException {
        try {
            Long count = individualResumeAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分页查询个人简介
     *
     * @param dto 个人简介dto
     * @return class IndividualResumeVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/list")
    public Result list(@Validated IndividualResumeDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<IndividualResumeBO> boList = individualResumeAPI.list(dto);
            List<IndividualResumeVO> voList = BeanTransform.copyProperties(boList, IndividualResumeVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加个人简介
     *
     * @param to 个人简介to
     * @return class IndividualResumeVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(value = {ADD.class}) IndividualResumeTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            IndividualResumeBO bo = individualResumeAPI.save(to);
            IndividualResumeVO vo = BeanTransform.copyProperties(bo, IndividualResumeVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除个人简介
     *
     * @param id 个人简介唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            individualResumeAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑个人简介
     *
     * @param to 个人简介to
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(value = {EDIT.class}) IndividualResumeTO to, BindingResult result) throws ActException {
        try {
            individualResumeAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 设置个人简介需要显示的字段
     *
     * @param username 用户姓名数组
     * @param to       个人简介显示字段to
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/setIndividualDisplayField")
    public Result setIndividualDisplayField(String[] username, @Validated(value = {ADD.class}) IndividualDisplayFieldTO to, BindingResult result) throws ActException {
        try {
            individualResumeAPI.setIndividualDisplayField(username, to);
            return new ActResult("setIndividualDisplayField success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * qq下拉值
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findStaffentry/qq")
    public Result findStaffentryQQ() throws ActException {
        try {
            List<String> qq = new ArrayList<>();
            if (moduleAPI.isCheck("staffentry")) {
                qq = entryRegisterAPI.findQQ();
            }
            return ActResult.initialize(qq);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 毕业学校下拉值
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findStaffentry/school")
    public Result findStaffentrySchool() throws ActException {
        try {
            List<String> school = new ArrayList<>();
            if (moduleAPI.isCheck("staffentry")) {
                school = entryRegisterAPI.findSchoolTag();
            }
            return ActResult.initialize(school);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 毕业时间下拉值
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findStaffentry/date")
    public Result findStaffentryDate() throws ActException {
        try {
            List<String> date = new ArrayList<>();
            if (moduleAPI.isCheck("staffentry")) {
                date = entryRegisterAPI.findGraduationDate();
            }
            return ActResult.initialize(date);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 上传附件
     *
     * @des 审核项目签订与立项
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/uploadFile/{id}")
    public Result uploadFile(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /id/....
            String paths = "/businessproject/siginmanage/" + id;
            List<InputStream> inputStreams = getInputStreams(request, paths);
            fileAPI.upload(inputStreams);
            return new ActResult("upload success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 文件附件列表
     *
     * @param id 签订与立项id
     * @return class FileVO
     * @version v1
     */
    @GetMapping("v1/listFile/{id}")
    public Result list(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /businessproject/id/....
            String path = "/businessproject/siginmanage/" + id;
            FileInfo fileInfo = new FileInfo();
            fileInfo.setPath(path);
            Object storageToken = request.getAttribute("storageToken");
            fileInfo.setStorageToken(storageToken.toString());
            List<FileVO> files = BeanTransform.copyProperties(fileAPI.list(fileInfo), FileVO.class);
            return ActResult.initialize(files);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 文件下载
     *
     * @param path 文件信息路径
     * @version v1
     */
    @GetMapping("v1/downloadFile")
    public Result download(@RequestParam String path, HttpServletRequest request, HttpServletResponse response) throws ActException {
        try {


            //该文件的路径
            Object storageToken = request.getAttribute("storageToken");
            FileInfo fileInfo = new FileInfo();
            fileInfo.setPath(path);
            fileInfo.setStorageToken(storageToken.toString());
            String filename = StringUtils.substringAfterLast(fileInfo.getPath(), "/");
            byte[] buffer = fileAPI.download(fileInfo);
            writeOutFile(response, buffer, filename);
            return new ActResult("download success");
        } catch (Exception e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 删除文件或文件夹
     *
     * @param siginManageDeleteFileTO 多文件信息路径
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/deleteFile")
    public Result delFile(@Validated(SiginManageDeleteFileTO.TestDEL.class) SiginManageDeleteFileTO siginManageDeleteFileTO, HttpServletRequest request) throws SerException {
        if (null != siginManageDeleteFileTO.getPaths() && siginManageDeleteFileTO.getPaths().length >= 0) {
            Object storageToken = request.getAttribute("storageToken");
            fileAPI.delFile(storageToken.toString(), siginManageDeleteFileTO.getPaths());
        }
        return new ActResult("delFile success");
    }

    /**
     * 冻结
     *
     * @param id id
     * @des 根据id冻结公司简介
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/congeal/{id}")
    public Result congeal(@PathVariable String id) throws ActException {
        try {
            individualResumeAPI.congealFirmin(id);
            return new ActResult("congeal success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 解冻
     *
     * @param id id
     * @des 根据id解冻公司简介
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/thaw/{id}")
    public Result thaw(@PathVariable String id) throws ActException {
        try {
            individualResumeAPI.thawFirmin(id);
            return new ActResult("thaw success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 简介管理周汇总
     *
     * @param year  年份
     * @param month 月份
     * @param week  周期
     * @return class SummationVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/summarize/week")
    public Result summarizeDay(Integer year, Integer month, Integer week, HttpServletRequest request) throws ActException {
        try {
            SummationBO bo = individualResumeAPI.summaWeek(year, month, week);
            SummationVO vo = BeanTransform.copyProperties(bo, SummationVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 简介管理月汇总
     *
     * @param year  年份
     * @param month 月份
     * @return class SummationVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/summarize/month")
    public Result summarizeMonth(Integer year, Integer month, HttpServletRequest request) throws ActException {
        try {
            SummationBO bo = individualResumeAPI.summaMonth(year, month);
            SummationVO vo = BeanTransform.copyProperties(bo, SummationVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 简介管理累计汇总
     *
     * @param date 截止日期
     * @return class SummationVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/summarize/total")
    public Result summarizeMonth(String date, HttpServletRequest request) throws ActException {
        try {
            SummationBO bo = individualResumeAPI.summaTotal(date);
            SummationVO vo = BeanTransform.copyProperties(bo, SummationVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 导出excel
     *
     * @des 导出个人简介
     * @version v1
     */
//    @LoginAuth
    @GetMapping("v1/export")
    public Result exportReport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "个人简介.xlsx";
            super.writeOutFile(response, individualResumeAPI.exportExcel(), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

    /**
     * excel模板下载
     *
     * @des 下载模板个人简介
     * @version v1
     */
    @GetMapping("v1/templateExport")
    public Result templateExport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "个人简介模板.xlsx";
            super.writeOutFile(response, individualResumeAPI.templateExport(), fileName);
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
            String token = request.getHeader(RpcCommon.USER_TOKEN).toString();
            List<InputStream> inputStreams = super.getInputStreams(request);
            InputStream is = inputStreams.get(1);
            Excel excel = new Excel(0, 1);
            List<IndividualResumeExcel> tos = ExcelUtil.mergeExcelToClazz(is, IndividualResumeExcel.class, excel);
            List<IndividualResumeTO> tocs = new ArrayList<>();
            Set<Integer> seqNum = new HashSet<>();
            for (IndividualResumeExcel individualResumeExcel : tos) {
                seqNum.add(individualResumeExcel.getSeqNum());
            }
            for (Integer seq : seqNum) {
                List<IndividualResumeExcel> individualResumeExcels = new ArrayList<>();
                List<StaffRewardTO> staffRewardTOS = new ArrayList<>();//员工奖励
                List<StaffHonorTO> staffHonorTOS = new ArrayList<>();//员工荣誉
                List<EducateExperienceTO> educateExperienceTOS = new ArrayList<>();//教育经历
                List<WorkExperienceTO> workExperienceTOS = new ArrayList<>();//工作经历
                List<CredentialSituationTO> credentialSituationTOS = new ArrayList<>(); //证书情况
                for (IndividualResumeExcel str : tos) {
                    if (str.getSeqNum().equals(seq)) {
                        individualResumeExcels.add(str);
                        StaffRewardTO staffRewardTO = BeanTransform.copyProperties(str, StaffRewardTO.class);
                        StaffHonorTO staffHonorTO = BeanTransform.copyProperties(str, StaffHonorTO.class);
                        EducateExperienceTO educateExperienceTO = BeanTransform.copyProperties(str, EducateExperienceTO.class);
                        WorkExperienceTO workExperienceTO = BeanTransform.copyProperties(str, WorkExperienceTO.class);
                        CredentialSituationTO credentialSituationTO = BeanTransform.copyProperties(str, CredentialSituationTO.class);
                        if (isCheckNull(staffRewardTO)) {
                            staffRewardTOS.add(staffRewardTO);
                        }
                        if (isCheckNull(staffHonorTO)) {
                            staffHonorTOS.add(staffHonorTO);
                        }
                        if (isCheckNull(educateExperienceTO)) {
                            educateExperienceTOS.add(educateExperienceTO);
                        }
                        if (isCheckNull(workExperienceTO)) {
                            workExperienceTOS.add(workExperienceTO);
                        }
                        if (isCheckNull(credentialSituationTO)) {
                            credentialSituationTOS.add(credentialSituationTO);
                        }
                    }
                }
                IndividualResumeTO individualResumeTO = BeanTransform.copyProperties(individualResumeExcels.get(0), IndividualResumeTO.class, "entryDate", "positiveTime", "buySocialSecurityTime","whetherSocialSecurity");
                individualResumeTO.setEntryDate(individualResumeExcels.get(0).getEntryDate().toString());
                individualResumeTO.setPositiveTime(individualResumeExcels.get(0).getPositiveTime().toString());
                individualResumeTO.setBuySocialSecurityTime(individualResumeExcels.get(0).getBuySocialSecurityTime().toString());
                individualResumeTO.setWhetherSocialSecurity(stringToBool(individualResumeExcels.get(0).getWhetherSocialSecurity()));
                individualResumeTO.setStaffRewardTOS(staffRewardTOS);
                individualResumeTO.setStaffHonorTOS(staffHonorTOS);
                individualResumeTO.setEducateExperienceTOS(educateExperienceTOS);
                individualResumeTO.setWorkExperienceTOS(workExperienceTOS);
                individualResumeTO.setCredentialSituationTOS(credentialSituationTOS);
                RpcContext.getContext().setAttachment(RpcCommon.USER_TOKEN, token);
                individualResumeAPI.save(individualResumeTO);
            }

            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    //判断某个类的所有属性是否都为空
    public Boolean isCheckNull(Object obj) throws ActException{
        Boolean bool = false;
        if (null == obj) {
            bool = false;
        }
        for (Field f : obj.getClass().getDeclaredFields()) {
            String name = f.getName(); // 获取属性的名字
            name = name.substring(0, 1).toUpperCase() + name.substring(1);// 将属性的首字符大写，方便构造get，set方法
            try {
                Method m = obj.getClass().getMethod("get" + name);
                Object value = m.invoke(obj);// 调用getter方法获取属性值
                if (null != value && !value.equals("")) {    //判断该属性值是否为空
                    bool = true;
                }
            } catch (Exception e) {

            }

        }
        return bool;
    }

    //string转bool
    public Boolean stringToBool(String type) throws ActException{
        Boolean bool = false;
        switch (type) {
            case "是":
                bool = true;
                break;
            case "否":
                bool = false;
                break;
            default:
                throw new ActException("是否购买社保输入不正确，正确的格式为(是/否)");
        }
        return bool;
    }
}