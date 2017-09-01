package com.bjike.goddess.shareholdersmanage.action.shareholdersmanage;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.shareholdersmanage.api.NewEquityAPI;
import com.bjike.goddess.shareholdersmanage.bo.NewEquityBO;
import com.bjike.goddess.shareholdersmanage.dto.NewEquityDTO;
import com.bjike.goddess.shareholdersmanage.to.NewEquityTO;
import com.bjike.goddess.shareholdersmanage.vo.NewEquityLinkDateVO;
import com.bjike.goddess.shareholdersmanage.vo.NewEquityVO;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 新增股权
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 04:17 ]
 * @Description: [ 新增股权 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("newequity")
public class NewEquityAction {
    @Autowired
    private NewEquityAPI newEquityAPI;

    /**
     * 列表总条数
     *
     * @param newEquityDTO 新增股权dto
     * @des 获取所有新增股权总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(NewEquityDTO newEquityDTO) throws ActException {
        try {
            Long count = newEquityAPI.countNewEquity(newEquityDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个新增股权
     *
     * @param id 新增股权id
     * @return class NewEquityVO
     * @des 根据id获取新增股权
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            NewEquityVO newEquityVO = BeanTransform.copyProperties(
                    newEquityAPI.getOne(id), NewEquityVO.class);
            return ActResult.initialize(newEquityVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 新增股权列表
     *
     * @param newEquityDTO 新增股权dto
     * @return class NewEquityVO
     * @des 获取所有新增股权
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findNewEqu(NewEquityDTO newEquityDTO, HttpServletRequest request) throws ActException {
        try {
            List<NewEquityVO> newEquityVOS = BeanTransform.copyProperties(
                    newEquityAPI.findList(newEquityDTO), NewEquityVO.class, request);
            return ActResult.initialize(newEquityVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 添加新增股权
     *
     * @param newEquityTO 新增股权to
     * @return class NewEquityBO
     * @des 添加新增股权
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addNewEqu(@Validated({ADD.class}) NewEquityTO newEquityTO, BindingResult result) throws ActException {
        try {
            NewEquityBO newEquityBO = newEquityAPI.save(newEquityTO);
            return ActResult.initialize(BeanTransform.copyProperties(newEquityBO, NewEquityVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑新增股权
     *
     * @param newEquityTO 新增股权数据bo
     * @return class NewEquityVO
     * @des 编辑新增股权
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result editShareOpen(@Validated({EDIT.class}) NewEquityTO newEquityTO, BindingResult result) throws ActException {
        try {
            NewEquityBO newEquityBO = newEquityAPI.edit(newEquityTO);
            return ActResult.initialize(BeanTransform.copyProperties(newEquityBO, NewEquityVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除新增股权
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteShareChange(@PathVariable String id) throws ActException {
        try {
            newEquityAPI.delete(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 根据股东名链接数据
     *
     * @param logoutShareName 注销股东名称
     * @param newHoldNum 新增股数
     * @return class NewEquityLinkDateVO
     * @des 根据股东名链接数据
     * @version v1
     */
    @GetMapping("v1/getLinkDate/logoutShareName")
    public Result getLinkDate(@RequestParam Integer newHoldNum, @RequestParam String logoutShareName) throws ActException {
        try {
            NewEquityLinkDateVO newEquityLinkDateVO = BeanTransform.copyProperties(
                    newEquityAPI.newEqLinkDate(newHoldNum,logoutShareName), NewEquityLinkDateVO.class);
            return ActResult.initialize(newEquityLinkDateVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}