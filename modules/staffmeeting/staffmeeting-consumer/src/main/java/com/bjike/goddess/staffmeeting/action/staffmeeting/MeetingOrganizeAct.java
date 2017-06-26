package com.bjike.goddess.staffmeeting.action.staffmeeting;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffmeeting.api.MeetingOrganizeAPI;
import com.bjike.goddess.staffmeeting.dto.MeetingOrganizeDTO;
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
     * 新增员工代表大会组织内容
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
     * 编辑员工代表大会组织内容
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
     * 冻结员工代表大会组织内容
     *
     * @param id 员工代表大会组织内容ID
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
     * 列表分页查询
     *
     * @param dto 分页条件
     * @return class MeetingOrganizeVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result pageList(MeetingOrganizeDTO dto) throws ActException {
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
    public Result count(MeetingOrganizeDTO dto) throws ActException {
        try {
            dto.getConditions().add(Restrict.eq("status", Status.THAW));
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


}