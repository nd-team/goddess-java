package com.bjike.goddess.progressmanage.action.progressmanage;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.progressmanage.api.ProgressTableAPI;
import com.bjike.goddess.progressmanage.dto.ProgressTableDTO;
import com.bjike.goddess.progressmanage.to.ProgressTableTO;
import com.bjike.goddess.progressmanage.vo.ProgressTableVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 进度表
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-09 04:46 ]
 * @Description: [ 进度表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("progresstable")
public class ProgressTableAct {
/*
    @Autowired
    private ProgressTableAPI progressTableAPI;


    *//**
     * 新增进度表
     *
     * @param to 进度表
     * @return class ProgressTableVO
     * @version v1
     *//*
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) ProgressTableTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            ProgressTableVO vo = BeanTransform.copyProperties(progressTableAPI.add(to), ProgressTableVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    *//**
     * 编辑进度表
     *
     * @param to 进度表
     * @return class ProgressTableVO
     * @version v1
     *//*
    @LoginAuth
    @PutMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) ProgressTableTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            ProgressTableVO vo = BeanTransform.copyProperties(progressTableAPI.edit(to), ProgressTableVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    *//**
     * 删除进度表
     *
     * @param id 项目承包洽谈ID
     * @version v1
     *//*
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            progressTableAPI.delete(id);
            return new ActResult("删除成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    *//**
     * 列表
     *
     * @param dto 分页条件
     * @return class ProgressTableVO
     * @version v1
     *//*
    @ge("v1/list")
    public Result pageList(ProgressTableDTO dto, HttpServletRequest request) throws ActException {

        try {
            List<ProgressTableVO> voList = BeanTransform.copyProperties(progressTableAPI.pageList(dto), ProgressTableVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
}*/
}