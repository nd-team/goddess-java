package com.bjike.goddess.dispatchcar.action.dispatchcar;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.dispatchcar.api.CheckChangeCarAPI;
import com.bjike.goddess.dispatchcar.bo.CheckChangeCarBO;
import com.bjike.goddess.dispatchcar.dto.CheckChangeCarDTO;
import com.bjike.goddess.dispatchcar.to.CheckChangeCarTO;
import com.bjike.goddess.dispatchcar.to.CorrectMistakeTO;
import com.bjike.goddess.dispatchcar.to.GuidePermissionTO;
import com.bjike.goddess.dispatchcar.vo.CheckChangeCarVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
* 出车核对修改记录
* @Author: [ Jason ]
* @Date: [  2017-09-25 11:24 ]
* @Description:	[ 出车核对修改记录 ]
* @Version: [ v1.0.0 ]
* @Copy: [ com.bjike ]
*/
@RestController
@RequestMapping("checkchangecar")
public class CheckChangeCarAction {
    @Autowired
    private CheckChangeCarAPI checkChangeCarAPI;



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

            Boolean isHasPermission = checkChangeCarAPI.guidePermission(guidePermissionTO);
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
     * 出车核对修改记录列表
     * @param dto 查询条件
     * @return class CheckChangeCarVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findList(CheckChangeCarDTO dto) throws ActException{
        try {
            List<CheckChangeCarBO> boList = checkChangeCarAPI.list(dto);
            List<CheckChangeCarVO> voList = BeanTransform.copyProperties(boList,CheckChangeCarVO.class);
            return ActResult.initialize(voList);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 问题解决
     * @param to 出车
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/modify")
    public Result modify(@Validated(ADD.class) CorrectMistakeTO to) throws ActException{
        try {
            checkChangeCarAPI.modify(to);
            return new ActResult("问题解决成功");
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 根据id查询单个核对修改记录
     * @param id
     * @return class CheckChangeCarVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/find/one")
    public Result findOne(@RequestParam String id) throws ActException{
        try {
            CheckChangeCarBO checkChangeCarBO = checkChangeCarAPI.findOne(id);
            CheckChangeCarVO checkChangeCarVO = BeanTransform.copyProperties(checkChangeCarBO,CheckChangeCarVO.class);
            return ActResult.initialize(checkChangeCarVO);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

 }