package com.bjike.goddess.intromanage.action.intromanage;

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
import com.bjike.goddess.intromanage.to.IndividualDisplayFieldTO;
import com.bjike.goddess.intromanage.to.IndividualResumeTO;
import com.bjike.goddess.intromanage.vo.IndividualResumeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
            IndividualResumeBO bo = individualResumeAPI.findById(id);
            IndividualResumeVO vo = BeanTransform.copyProperties(bo, IndividualResumeVO.class, request);
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
}