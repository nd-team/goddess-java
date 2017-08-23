package com.bjike.goddess.intromanage.action.intromanage;

import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.intromanage.api.IndividualResumeAPI;
import com.bjike.goddess.intromanage.bo.IndividualResumeBO;
import com.bjike.goddess.intromanage.dto.IndividualResumeDTO;
import com.bjike.goddess.intromanage.excel.SonPermissionObject;
import com.bjike.goddess.intromanage.to.GuidePermissionTO;
import com.bjike.goddess.intromanage.to.IndividualDisplayFieldTO;
import com.bjike.goddess.intromanage.to.IndividualResumeTO;
import com.bjike.goddess.intromanage.vo.*;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import com.bjike.goddess.staffentry.api.EntryRegisterAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

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
public class IndividualResumeAct {

    @Autowired
    private IndividualResumeAPI individualResumeAPI;

    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;
    @Autowired
    private ModuleAPI moduleAPI;
    @Autowired
    private EntryRegisterAPI entryRegisterAPI;

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
}