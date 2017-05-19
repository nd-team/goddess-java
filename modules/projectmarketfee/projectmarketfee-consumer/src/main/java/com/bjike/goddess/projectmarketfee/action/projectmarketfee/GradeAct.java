package com.bjike.goddess.projectmarketfee.action.projectmarketfee;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectmarketfee.api.GradeAPI;
import com.bjike.goddess.projectmarketfee.bo.GradeBO;
import com.bjike.goddess.projectmarketfee.dto.GradeDTO;
import com.bjike.goddess.projectmarketfee.to.GradeTO;
import com.bjike.goddess.projectmarketfee.vo.GradeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 等级设计
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-09 04:55 ]
 * @Description: [ 等级设计 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("grade")
public class GradeAct {
    @Autowired
    private GradeAPI gradeAPI;

    /**
     * 添加
     *
     * @param to      等级设计信息
     * @param request 请求对象
     * @return class GradeVO
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/save")
    public Result save(@Validated({ADD.class}) GradeTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            GradeBO bo = gradeAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, GradeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 等级设计信息
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) GradeTO to, BindingResult result) throws ActException {
        try {
            gradeAPI.edit(to);
            return new ActResult("编辑成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 等级设计id
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            gradeAPI.delete(id);
            return new ActResult("删除成功!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找
     *
     * @param dto     等级设计分页信息
     * @param request 请求对象
     * @return class GradeVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(GradeDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<GradeBO> list = gradeAPI.list(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, GradeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找
     *
     * @param id      等级设计id
     * @param request 请求对象
     * @return class GradeVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/moneyready/{id}")
    public Result moneyready(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            GradeBO bo = gradeAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, GradeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询总记录数
     *
     * @param dto     dto
     * @param request 请求对象
     * @return class GradeVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/countNum")
    public Result countNum(GradeDTO dto, HttpServletRequest request) throws ActException {
        try {
            GradeBO bo = gradeAPI.countNum(dto);
            return ActResult.initialize(BeanTransform.copyProperties(bo, GradeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}