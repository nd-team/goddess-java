package com.bjike.goddess.communicatemeeting.action.communicatemeeting;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.communicatemeeting.api.OrganizeContentAPI;
import com.bjike.goddess.communicatemeeting.bo.OrganizeContentBO;
import com.bjike.goddess.communicatemeeting.dto.OrganizeContentDTO;
import com.bjike.goddess.communicatemeeting.to.OrganizeContentTO;
import com.bjike.goddess.communicatemeeting.vo.OrganizeContentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

/**
 * 交流会组织内容
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-26 02:16 ]
 * @Description: [ 交流会组织内容 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("organizecontent")
public class OrganizeContentAct {
    @Autowired
    private OrganizeContentAPI organizeContentAPI;

    /**
     * 交流会组织内容列表总条数
     *
     * @param dto 交流会组织内容dto
     * @des 获取所有交流会组织内容总条数
     * @version v1
     */
    @GetMapping("v1/countNum")
    public Result countNum(OrganizeContentDTO dto) throws ActException {
        try {
            Long count = organizeContentAPI.countNum(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个交流会组织内容
     *
     * @param id id
     * @return class OrganizeContentVO
     * @des 获取一个交流会组织内容
     * @version v1
     */
    @GetMapping("v1/organizecontent/{id}")
    public Result organizecontent(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            OrganizeContentBO bo = organizeContentAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, OrganizeContentVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 交流会组织内容列表
     *
     * @param dto 交流会组织内容信息dto
     * @return class OrganizeContentVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(OrganizeContentDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<OrganizeContentVO> VOS = BeanTransform.copyProperties
                    (organizeContentAPI.list(dto), OrganizeContentVO.class, request);
            return ActResult.initialize(VOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加交流会组织内容信息
     *
     * @param to 交流会组织内容信息数据to
     * @return class OrganizeContentVO
     * @version v1
     */
    //  @LoginAuth
    @PostMapping("v1/save")
    public Result save(@Validated({ADD.class}) OrganizeContentTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            OrganizeContentBO bo = organizeContentAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, OrganizeContentVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑交流会组织内容信息
     *
     * @param to 交流会组织内容信息数据to
     * @des 编辑交流会组织内容信息
     * @version v1
     */
    // @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) OrganizeContentTO to, BindingResult bindingResult) throws ActException {
        try {
            organizeContentAPI.edit(to);
            return new ActResult("编辑成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 更新主持人
     *
     * @param id   id
     * @param host 主持人
     * @throws ActException
     * @version v1
     */
    @PatchMapping("v1/updateHost/{id}/{host}")
    public Result updateHost(@PathVariable String id, @PathVariable String host) throws ActException {
        try {
            organizeContentAPI.updateHost(id, host);
            return new ActResult("更新成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找对应岗位的所有员工
     *
     * @param jobs 岗位数组
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findPeoples/{jobs}")
    public Result findPeoples(@PathVariable String... jobs) throws ActException {
        try {
            Set<String> set = organizeContentAPI.findPeoples(jobs);
            return ActResult.initialize(set);
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
    @GetMapping("v1/allMeetingsNumber")
    public Result allMeetingsNumber() throws ActException {
        try {
            Set<String> set = organizeContentAPI.allMeetingsNumber();
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
            Set<String> set = organizeContentAPI.allMeetingFormats();
            return ActResult.initialize(set);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}