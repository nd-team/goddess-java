package com.bjike.goddess.stockholdermeeting.action.stockholdermeeting;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.stockholdermeeting.api.OrganizationAPI;
import com.bjike.goddess.stockholdermeeting.bo.OrganizationBO;
import com.bjike.goddess.stockholdermeeting.dto.OrganizationDTO;
import com.bjike.goddess.stockholdermeeting.to.OrganizationTO;
import com.bjike.goddess.stockholdermeeting.vo.OrganizationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

/**
 * 股东大会组织内容
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-06 05:46 ]
 * @Description: [ 股东大会组织内容 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("organization")
public class OrganizationAct {
    @Autowired
    private OrganizationAPI organizationAPI;

    /**
     * 股东大会组织内容列表总条数
     *
     * @param dto 股东大会组织内容dto
     * @des 获取所有股东大会组织内容总条数
     * @version v1
     */
    @GetMapping("v1/countNum")
    public Result countNum(OrganizationDTO dto) throws ActException {
        try {
            Long count = organizationAPI.countNum(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个股东大会组织内容
     *
     * @param id id
     * @return class OrganizationVO
     * @des 获取一个股东大会组织内容
     * @version v1
     */
    @GetMapping("v1/organization/{id}")
    public Result organization(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            OrganizationBO bo = organizationAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, OrganizationVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 股东大会组织内容列表
     *
     * @param dto 股东大会组织内容信息dto
     * @return class OrganizationVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(OrganizationDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<OrganizationVO> VOS = BeanTransform.copyProperties
                    (organizationAPI.list(dto), OrganizationVO.class, request);
            return ActResult.initialize(VOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加股东大会组织内容信息
     *
     * @param to 股东大会组织内容信息数据to
     * @return class OrganizationVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/save")
    public Result save(@Validated({ADD.class}) OrganizationTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            OrganizationBO bo = organizationAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, OrganizationVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑股东大会组织内容信息
     *
     * @param to 股东大会组织内容信息数据to
     * @des 编辑股东大会组织内容信息
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) OrganizationTO to, BindingResult bindingResult) throws ActException {
        try {
            organizationAPI.edit(to);
            return new ActResult("编辑成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过岗位查找员工
     *
     * @param positionIds 岗位id数组
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findPeople/{positionIds}")
    public Result findPeople(@PathVariable String[] positionIds) throws ActException {
        try {
            List<String> list = organizationAPI.findPeople(positionIds);
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找所有会议编号
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/allMeetingsNumbers")
    public Result allMeetingsNumbers() throws ActException {
        try {
            Set<String> set = organizationAPI.allMeetingsNumbers();
            return ActResult.initialize(set);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找所有会议形式
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/allMeetingFormats")
    public Result allMeetingFormats() throws ActException {
        try {
            Set<String> set = organizationAPI.allMeetingFormats();
            return ActResult.initialize(set);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结
     *
     * @param id 组织内容id
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/freeze/{id}")
    public Result freeze(@PathVariable String id) throws ActException {
        try {
            organizationAPI.freeze(id);
            return new ActResult("冻结成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 解冻
     *
     * @param id 组织内容id
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/thaw/{id}")
    public Result thaw(@PathVariable String id) throws ActException {
        try {
            organizationAPI.thaw(id);
            return new ActResult("解冻成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}