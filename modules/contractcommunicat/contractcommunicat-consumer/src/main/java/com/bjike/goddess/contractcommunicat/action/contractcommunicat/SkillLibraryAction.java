package com.bjike.goddess.contractcommunicat.action.contractcommunicat;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contractcommunicat.api.SkillLibraryAPI;
import com.bjike.goddess.contractcommunicat.bo.HistoryAppraiseBO;
import com.bjike.goddess.contractcommunicat.dto.SkillLibraryDTO;
import com.bjike.goddess.contractcommunicat.to.GuidePermissionTO;
import com.bjike.goddess.contractcommunicat.to.SkillLibraryTO;
import com.bjike.goddess.contractcommunicat.vo.InProjectsVO;
import com.bjike.goddess.contractcommunicat.vo.ProjectOutsourcingVO;
import com.bjike.goddess.contractcommunicat.vo.SkillLibraryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 谈判技巧库
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-11-25 04:55 ]
 * @Description: [ 谈判技巧库 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("skilllibrary")
public class SkillLibraryAction {
    @Autowired
    private SkillLibraryAPI skillLibraryAPI;


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

            Boolean isHasPermission = skillLibraryAPI.guidePermission(guidePermissionTO);
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
     * 查询总记录数
     *
     * @param dto 查询条件
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(SkillLibraryDTO dto) throws ActException {
        try {
            Long count = skillLibraryAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询谈判技巧库
     *
     * @param id 谈判技巧库id
     * @return class SkillLibraryVO
     * @version v1
     */
    @GetMapping("v1/skill/{id}")
    public Result skill(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            SkillLibraryVO vo = BeanTransform.copyProperties(skillLibraryAPI.getOne(id), SkillLibraryVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 谈判技巧库列表
     *
     * @return class SkillLibraryVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(SkillLibraryDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<SkillLibraryVO> vo = BeanTransform.copyProperties(skillLibraryAPI.list(dto), InProjectsVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 新增项目承包洽谈
     *
     * @param to 项目承包洽谈信息
     * @return class SkillLibraryVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) SkillLibraryTO to, BindingResult bindingResult) throws ActException {
        try {
            SkillLibraryVO voList = BeanTransform.copyProperties(skillLibraryAPI.insert(to), ProjectOutsourcingVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑项目承包洽谈
     *
     * @param to 项目承包洽谈信息
     * @return class ProjectOutsourcingVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) SkillLibraryTO to, BindingResult bindingResult) throws ActException {
        try {
            ProjectOutsourcingVO vo = BeanTransform.copyProperties(skillLibraryAPI.edit(to), ProjectOutsourcingVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除项目承包洽谈
     *
     * @param id id
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/remove")
    public Result remove(@PathVariable String id) throws ActException {
        try {
            skillLibraryAPI.remove(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 评价
     *
     * @param to 评价
     * @return class SkillLibraryVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/appraise")
    public Result appraise(@Validated({SkillLibraryTO.APPRAISE.class}) SkillLibraryTO to, BindingResult bindingResult) throws ActException {
        try {
            SkillLibraryVO vo = BeanTransform.copyProperties(skillLibraryAPI.appraise(to), SkillLibraryVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查看历史评价
     *
     * @param id id
     * @return class HistoryAppraiseBO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/historyAppraise")
    public Result historyAppraise(@PathVariable String id) throws ActException {
        try {
            List<HistoryAppraiseBO> boList = BeanTransform.copyProperties(skillLibraryAPI.historyAppraise(id), HistoryAppraiseBO.class);
            return ActResult.initialize(boList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}