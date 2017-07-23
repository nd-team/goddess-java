package com.bjike.goddess.allmeeting.action.allmeeting;

import com.bjike.goddess.allmeeting.api.AllMeetingOrganizeAPI;
import com.bjike.goddess.allmeeting.api.MeetingLayAPI;
import com.bjike.goddess.allmeeting.dto.AllMeetingOrganizeDTO;
import com.bjike.goddess.allmeeting.excel.SonPermissionObject;
import com.bjike.goddess.allmeeting.to.AllMeetingOrganizeTO;
import com.bjike.goddess.allmeeting.to.GuidePermissionTO;
import com.bjike.goddess.allmeeting.vo.AllMeetingOrganizeVO;
import com.bjike.goddess.allmeeting.vo.MeetingLayVO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 所有工作内容汇总会议组织内容
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-31 11:06 ]
 * @Description: [ 所有工作内容汇总会议组织内容 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("organize")
public class AllMeetingOrganizeAct {

    @Autowired
    private AllMeetingOrganizeAPI allMeetingOrganizeAPI;
    @Autowired
    private MeetingLayAPI meetingLayAPI;
    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;


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

            List<SonPermissionObject> hasPermissionList = allMeetingOrganizeAPI.sonPermission();
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

            Boolean isHasPermission = allMeetingOrganizeAPI.guidePermission(guidePermissionTO);
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
     * @param to 会议组织
     * @return class AllMeetingOrganizeVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) AllMeetingOrganizeTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            AllMeetingOrganizeVO voList = BeanTransform.copyProperties(allMeetingOrganizeAPI.add(to), AllMeetingOrganizeVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 会议组织
     * @return class AllMeetingOrganizeVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) AllMeetingOrganizeTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            AllMeetingOrganizeVO vo = BeanTransform.copyProperties(allMeetingOrganizeAPI.edit(to), AllMeetingOrganizeVO.class, request);
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
            allMeetingOrganizeAPI.freeze(id);
            return new ActResult("冻结成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 解冻
     *
     * @param id 会议组织ID
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/unfreeze/{id}")
    public Result unfreeze(@PathVariable String id) throws ActException {
        try {
            allMeetingOrganizeAPI.unfreeze(id);
            return new ActResult("解冻成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 列表
     *
     * @param dto 分页条件
     * @return class AllMeetingOrganizeVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result pageList(@Validated(AllMeetingOrganizeDTO.SelectStatus.class) AllMeetingOrganizeDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<AllMeetingOrganizeVO> voList = BeanTransform.copyProperties(allMeetingOrganizeAPI.pageList(dto), AllMeetingOrganizeVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 会议层面列表
     *
     * @return class AllMeetingOrganizeVO
     * @version v1
     */
    @GetMapping("v1/lays")
    public Result lays(HttpServletRequest request) throws ActException {
        try {
            List<MeetingLayVO> voList = BeanTransform.copyProperties(meetingLayAPI.lays(), MeetingLayVO.class, request);
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
    public Result count(@Validated(AllMeetingOrganizeDTO.SelectStatus.class) AllMeetingOrganizeDTO dto) throws ActException {
        try {
            dto.getConditions().add(Restrict.eq("status", dto.getStatus()));
            Long count = allMeetingOrganizeAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id查询会议组织
     *
     * @param id 会议组织id
     * @return class AllMeetingOrganizeVO
     * @version v1
     */
    @GetMapping("v1/find/{id}")
    public Result find(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            AllMeetingOrganizeVO vo = BeanTransform.copyProperties(allMeetingOrganizeAPI.findById(id), AllMeetingOrganizeVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询参会人员
     * @version v1
     */
    @GetMapping("v1/getPlanPeople")
    public Result getPlanPeople() throws ActException{
        try {
            String[] planPeople = allMeetingOrganizeAPI.getPlanPeople();
            return ActResult.initialize(planPeople);
        }catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }



}