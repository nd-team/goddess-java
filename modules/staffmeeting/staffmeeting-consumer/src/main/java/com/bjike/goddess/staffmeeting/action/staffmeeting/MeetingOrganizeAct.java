package com.bjike.goddess.staffmeeting.action.staffmeeting;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffmeeting.api.MeetingOrganizeAPI;
import com.bjike.goddess.staffmeeting.dto.MeetingOrganizeDTO;
import com.bjike.goddess.staffmeeting.to.GuidePermissionTO;
import com.bjike.goddess.staffmeeting.to.MeetingOrganizeTO;
import com.bjike.goddess.staffmeeting.vo.MeetingOrganizeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 员工代表大会组织内容
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-06 10:45 ]
 * @Description: [ 员工代表大会组织内容 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("organize")
public class MeetingOrganizeAct {

    @Autowired
    private MeetingOrganizeAPI meetingOrganizeAPI;

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

            Boolean isHasPermission = meetingOrganizeAPI.guidePermission(guidePermissionTO);
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
     * 新增
     *
     * @param to 员工代表大会组织内容
     * @return class MeetingOrganizeVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) MeetingOrganizeTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            MeetingOrganizeVO voList = BeanTransform.copyProperties(meetingOrganizeAPI.add(to), MeetingOrganizeVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 员工代表大会组织内容
     * @return class MeetingOrganizeVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) MeetingOrganizeTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            MeetingOrganizeVO vo = BeanTransform.copyProperties(meetingOrganizeAPI.edit(to), MeetingOrganizeVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结
     *
     * @param id id
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/freeze/{id}")
    public Result freeze(@PathVariable String id) throws ActException {
        try {
            meetingOrganizeAPI.freeze(id);
            return new ActResult("冻结成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 解冻
     *
     * @param id id
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/unfreeze/{id}")
    public Result unfreeze(@PathVariable String id) throws ActException {
        try {
            meetingOrganizeAPI.unfreeze(id);
            return new ActResult("解冻成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 列表
     *
     * @param dto 分页条件
     * @return class MeetingOrganizeVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/list")
    public Result pageList(@Validated(MeetingOrganizeDTO.Select.class) MeetingOrganizeDTO dto) throws ActException {
        try {
            List<MeetingOrganizeVO> voList = BeanTransform.copyProperties(meetingOrganizeAPI.pageList(dto), MeetingOrganizeVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询总记录数
     *
     * @param dto 分页条件
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(@Validated(MeetingOrganizeDTO.Select.class) MeetingOrganizeDTO dto) throws ActException {
        try {
            dto.getConditions().add(Restrict.eq("status", dto.getStatus()));
            Long count = meetingOrganizeAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询员工代表大会组织内容
     *
     * @param id 员工代表大会组织内容id
     * @return class MeetingOrganizeVO
     * @version v1
     */
    @GetMapping("v1/find/{id}")
    public Result find(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            MeetingOrganizeVO vo = BeanTransform.copyProperties(meetingOrganizeAPI.findById(id), MeetingOrganizeVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询所有人员
     */
    @GetMapping("v1/findPlanUser")
    public Result findPlanUser() throws ActException{
        try {
            String[] planUser = meetingOrganizeAPI.findPlanUser();
            return ActResult.initialize(planUser);
        }catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}