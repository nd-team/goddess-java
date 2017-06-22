package com.bjike.goddess.allmeeting.action.allmeeting;

import com.bjike.goddess.allmeeting.api.AllMeetingOrganizeAPI;
import com.bjike.goddess.allmeeting.dto.AllMeetingOrganizeDTO;
import com.bjike.goddess.allmeeting.to.AllMeetingOrganizeTO;
import com.bjike.goddess.allmeeting.vo.AllMeetingOrganizeVO;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("allmeetingorganize")
public class AllMeetingOrganizeAct {

    @Autowired
    private AllMeetingOrganizeAPI allMeetingOrganizeAPI;

    /**
     * 新增会议组织
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
     * 编辑会议组织
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
     * 冻结会议组织
     *
     * @param id 会议组织ID
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
     * 列表分页查询
     *
     * @param dto 分页条件
     * @return class AllMeetingOrganizeVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result pageList(AllMeetingOrganizeDTO dto) throws ActException {
        try {
            List<AllMeetingOrganizeVO> voList = BeanTransform.copyProperties(allMeetingOrganizeAPI.pageList(dto), AllMeetingOrganizeVO.class);
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
    public Result count(AllMeetingOrganizeDTO dto) throws ActException {
        try {
            dto.getConditions().add(Restrict.eq("status", Status.THAW));
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


}