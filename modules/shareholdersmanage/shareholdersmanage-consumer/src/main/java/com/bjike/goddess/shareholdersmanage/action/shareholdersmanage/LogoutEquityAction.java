package com.bjike.goddess.shareholdersmanage.action.shareholdersmanage;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.shareholdersmanage.api.LogoutEquityAPI;
import com.bjike.goddess.shareholdersmanage.api.LogoutShareAPI;
import com.bjike.goddess.shareholdersmanage.bo.LogoutEquityBO;
import com.bjike.goddess.shareholdersmanage.dto.LogoutEquityDTO;
import com.bjike.goddess.shareholdersmanage.entity.LogoutEquity;
import com.bjike.goddess.shareholdersmanage.service.LogoutEquitySer;
import com.bjike.goddess.shareholdersmanage.service.LogoutShareSer;
import com.bjike.goddess.shareholdersmanage.to.LogoutEquityTO;
import com.bjike.goddess.shareholdersmanage.vo.LogoutEquityVO;
import com.bjike.goddess.shareholdersmanage.vo.LogoutShareLinkDateVO;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 注销股权
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 04:51 ]
 * @Description: [ 注销股权 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("logoutequity")
public class LogoutEquityAction {
    @Autowired
    private LogoutEquityAPI logoutEquityAPI;
    @Autowired
    private LogoutShareAPI logoutShareAPI;

    /**
     * 列表总条数
     *
     * @param logoutEquityDTO 注销股权dto
     * @des 获取所有注销股权总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(LogoutEquityDTO logoutEquityDTO) throws ActException {
        try {
            Long count = logoutEquityAPI.countLogEquity(logoutEquityDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个注销股权
     *
     * @param id 注销股权id
     * @return class LogoutShareVO
     * @des 根据id获取注销股权
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            LogoutEquityVO logoutEquityVO = BeanTransform.copyProperties(
                    logoutEquityAPI.getOne(id), LogoutEquityVO.class);
            return ActResult.initialize(logoutEquityVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 注销股权列表
     *
     * @param logoutEquityDTO 注销股权dto
     * @return class ShareChangeVO
     * @des 获取所有注销股权
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findLogout(LogoutEquityDTO logoutEquityDTO, HttpServletRequest request) throws ActException {
        try {
            List<LogoutEquityVO> logoutEquityVOList = BeanTransform.copyProperties(
                    logoutEquityAPI.findList(logoutEquityDTO), LogoutEquityVO.class, request);
            return ActResult.initialize(logoutEquityVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 添加注销股权
     *
     * @param logoutEquityTO 注销股权to
     * @return class LogoutShareVO
     * @des 添加注销股权
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addLogoutEquity(@Validated({ADD.class}) LogoutEquityTO logoutEquityTO, BindingResult result) throws ActException {
        try {
            LogoutEquityBO logoutEquityBO = logoutEquityAPI.save(logoutEquityTO);
            return ActResult.initialize(BeanTransform.copyProperties(logoutEquityBO, LogoutEquityVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑注销股权
     *
     * @param logoutEquityTO 注销股权数据bo
     * @return class logoutEquityBO
     * @des 编辑注销股权
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result editLogoutEquity(@Validated({EDIT.class}) LogoutEquityTO logoutEquityTO, BindingResult result) throws ActException {
        try {
            LogoutEquityBO logoutEquityBO = logoutEquityAPI.edit(logoutEquityTO);
            return ActResult.initialize(BeanTransform.copyProperties(logoutEquityBO, LogoutEquityVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除注销股权
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteLogoutEquity(@PathVariable String id) throws ActException {
        try {
            logoutEquityAPI.delete(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 根据股东名链接数据
     *
     * @param logoutShareName 注销股东名称
     * @return class LogoutShareLinkDateVO
     * @des 根据股东名链接数据
     * @version v1
     */
    @GetMapping("v1/getLinkDate/logoutShareName")
    public Result getLinkDate(@RequestParam String logoutShareName) throws ActException {
        try {
            LogoutShareLinkDateVO logoutShareLinkDateVO = BeanTransform.copyProperties(
                    logoutShareAPI.linkDateByName(logoutShareName), LogoutShareLinkDateVO.class);
            return ActResult.initialize(logoutShareLinkDateVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}