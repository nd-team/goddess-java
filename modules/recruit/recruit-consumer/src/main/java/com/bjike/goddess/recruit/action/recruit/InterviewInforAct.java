package com.bjike.goddess.recruit.action.recruit;

import com.alibaba.dubbo.rpc.RpcContext;
import com.bjike.goddess.accommodation.api.RentalAPI;
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
import com.bjike.goddess.recruit.api.FirstPhoneRecordAPI;
import com.bjike.goddess.recruit.api.InterviewInforAPI;
import com.bjike.goddess.recruit.bo.InterviewInforBO;
import com.bjike.goddess.recruit.dto.FirstPhoneRecordDTO;
import com.bjike.goddess.recruit.dto.InterviewInforDTO;
import com.bjike.goddess.recruit.entity.InterviewInfor;
import com.bjike.goddess.recruit.excel.FirstPhoneRecordExcel;
import com.bjike.goddess.recruit.excel.InterviewInforExcel;
import com.bjike.goddess.recruit.to.FirstPhoneRecordTO;
import com.bjike.goddess.recruit.to.GuidePermissionTO;
import com.bjike.goddess.recruit.to.IdeaTO;
import com.bjike.goddess.recruit.to.InterviewInforTO;
import com.bjike.goddess.recruit.vo.InterviewInforVO;
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

/**
 * 面试信息
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-15 16:30]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("interviewInfor")
public class InterviewInforAct extends BaseFileAction{

    @Autowired
    private InterviewInforAPI interviewInforAPI;
    @Autowired
    private FirstPhoneRecordAPI firstPhoneRecordAPI;
    @Autowired
    private ModuleAPI moduleAPI;
    @Autowired
    private RentalAPI rentalAPI;

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

            Boolean isHasPermission = interviewInforAPI.guidePermission(guidePermissionTO);
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
     * 根据id查询面试信息
     *
     * @param id 面试信息唯一标识
     * @return class InterviewInforVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/interviewInfor/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            InterviewInforBO bo = interviewInforAPI.findById(id);
            InterviewInforVO vo = BeanTransform.copyProperties(bo, InterviewInforVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 面试信息dto
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated InterviewInforDTO dto, BindingResult result) throws ActException {
        try {
            Long count = interviewInforAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取列表
     *
     * @param dto 面试信息dto
     * @return class InterviewInforVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(@Validated InterviewInforDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<InterviewInforBO> boList = interviewInforAPI.list(dto);
            List<InterviewInforVO> voList = BeanTransform.copyProperties(boList, InterviewInforVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加面试信息
     *
     * @param to 面试信息to信息
     * @return class InterviewInforVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(value = {ADD.class}) InterviewInforTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            InterviewInforBO bo = interviewInforAPI.save(to);
            InterviewInforVO vo = BeanTransform.copyProperties(bo, InterviewInforVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id删除面试信息
     *
     * @param id 面试信息唯一标识
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            interviewInforAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑面试信息
     *
     * @param to 面试信息to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated(value = {EDIT.class}) InterviewInforTO to, BindingResult result) throws ActException {
        try {
            interviewInforAPI.update(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 初试面试意见
     *
     * @param to 面试信息to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/firstIdea")
    public Result firstIdea(@Validated(value = {IdeaTO.FirstIdea.class}) IdeaTO to, BindingResult result) throws ActException {
        try {
            interviewInforAPI.firstIdea(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 复试面试意见
     *
     * @param to 面试信息to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/reexamineIdea")
    public Result reexamineIdea(@Validated(value = {IdeaTO.SecondIdea.class}) IdeaTO to, BindingResult result) throws ActException {
        try {
            interviewInforAPI.reexamineIdea(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 薪资面谈意见
     *
     * @param to 面试信息to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/wagesIdea")
    public Result wagesIdea(@Validated(value = {IdeaTO.WagesIdea.class}) IdeaTO to, BindingResult result) throws ActException {
        try {
            interviewInforAPI.wagesIdea(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 总经办审核录取
     *
     * @param to 面试信息to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/zjbAudit")
    public Result zjbAudit(@Validated(value = {IdeaTO.BossIdea.class}) IdeaTO to, BindingResult result) throws ActException {
        try {
            interviewInforAPI.zjbAudit(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 入职信息填写
     *
     * @param to 面试信息to信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/staffEntryInfo")
    public Result staffEntryInfo(@Validated(value = {IdeaTO.StaffEntry.class}) IdeaTO to, BindingResult result) throws ActException {
        try {
            interviewInforAPI.staffEntryInfo(to);
            return new ActResult("edit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }



    /**
     * 获取所有第一次电访记录的姓名名单
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/allFirstName")
    public Result allFirstName() throws ActException {
        try {
            return ActResult.initialize(firstPhoneRecordAPI.allFirstName());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有入职地址
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/allAddress")
    public Result allAddress(HttpServletRequest request) throws ActException {
        try {
            String token = request.getHeader(RpcCommon.USER_TOKEN).toString();
            if (moduleAPI.isCheck("accommodation")) {
                RpcContext.getContext().setAttachment(RpcCommon.USER_TOKEN, token);
                return ActResult.initialize(rentalAPI.allAddress());
            } else {
                return ActResult.initialize(new HashSet<>());
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 导入Excel
     *
     * @param request 注入HttpServletRequest对象
     * @version v1
     */
//    @LoginAuth
    @PostMapping("v1/importExcel")
    public Result importExcel(HttpServletRequest request) throws ActException {
        try {
            List<InputStream> inputStreams = super.getInputStreams(request);
            InputStream is = inputStreams.get(1);
            Excel excel = new Excel(0, 1);
            List<InterviewInforExcel> tos = ExcelUtil.excelToClazz(is, InterviewInforExcel.class, excel);
            List<InterviewInforTO> tocs = new ArrayList<>();
            for (InterviewInforExcel str : tos) {
                InterviewInforTO inforTO = BeanTransform.copyProperties(str, InterviewInforTO.class,
                        "whetherPass", "workingExperience", "whetherFirstQuestionCorrect",
                        "whetherFaceTest", "whetherFirstTestPass", "whetherNeedSecondTest", "whetherSecondTestPass",
                        "agreedEmployed", "whetherAcceptAdmit", "whetherAccommodation", "whetherUseFirmPC",
                        "whetherEntry");
                //简历筛选是否通过
                if (str.getWhetherPass().equals("是")) {
                    inforTO.setWhetherPass(true);
                }else {
                    inforTO.setWhetherPass(false);
                }
                //是否有相关工作经验
                if (str.getWorkingExperience().equals("是")) {
                    inforTO.setWorkingExperience(true);
                }else {
                    inforTO.setWorkingExperience(false);
                }
                //求职考试第一题是否正确
                if (str.getWhetherFirstQuestionCorrect().equals("是")) {
                    inforTO.setWhetherFirstQuestionCorrect(true);
                }else {
                    inforTO.setWhetherFirstQuestionCorrect(false);
                }
                //是否初试
                if (str.getWhetherFaceTest().equals("是")) {
                    inforTO.setWhetherFaceTest(true);
                }else {
                    inforTO.setWhetherFaceTest(false);
                }
                //初试是否通过
                if (str.getWhetherFirstTestPass().equals("是")) {
                    inforTO.setWhetherFirstTestPass(true);
                }else {
                    inforTO.setWhetherFirstTestPass(false);
                }
                //是否需要复试
                if (str.getWhetherNeedSecondTest().equals("是")) {
                    inforTO.setWhetherNeedSecondTest(true);
                }else {
                    inforTO.setWhetherNeedSecondTest(false);
                }
                //复试是否通过
                if (str.getWhetherSecondTestPass().equals("是")) {
                    inforTO.setWhetherSecondTestPass(true);
                }else {
                    inforTO.setWhetherSecondTestPass(false);
                }
                //是否同意录用
                if (str.getAgreedEmployed().equals("是")) {
                    inforTO.setAgreedEmployed(true);
                }else {
                    inforTO.setAgreedEmployed(false);
                }
                //是否接受录取
                if (str.getWhetherAcceptAdmit().equals("是")) {
                    inforTO.setWhetherAcceptAdmit(true);
                }else {
                    inforTO.setWhetherAcceptAdmit(false);
                }
                //是否住宿
                if (str.getWhetherAccommodation().equals("是")) {
                    inforTO.setWhetherAccommodation(true);
                }else {
                    inforTO.setWhetherAccommodation(false);
                }
                //是否使用公司电脑
                if (str.getWhetherUseFirmPC().equals("是")) {
                    inforTO.setWhetherUseFirmPC(true);
                }else {
                    inforTO.setWhetherUseFirmPC(false);
                }

                //是否入职
                if(str.getWhetherEntry().equals("是")){
                    inforTO.setWhetherEntry(true);
                }else {
                    inforTO.setWhetherEntry(false);
                }
                tocs.add(inforTO);
            }
            //注意序列化
            interviewInforAPI.importExcel(tocs);
            return new ActResult("导入成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 导出excel
     *
     * @param dto 面试信息
     * @des 导出面试信息
     * @version v1
     */
//    @LoginAuth
    @GetMapping("v1/export")
    public Result exportReport(InterviewInforDTO dto, HttpServletResponse response, BindingResult result) throws ActException {
        try {
            String fileName = "面试信息.xlsx";
            super.writeOutFile(response, interviewInforAPI.exportExcel(dto), fileName);
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
     * @des 下载模板第一次面试信息
     * @version v1
     */
    @GetMapping("v1/templateExport")
    public Result templateExport(HttpServletResponse response) throws ActException {
        try {
            String fileName = "面试信息.xlsx";
            super.writeOutFile(response, interviewInforAPI.templateExport(), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }
}
