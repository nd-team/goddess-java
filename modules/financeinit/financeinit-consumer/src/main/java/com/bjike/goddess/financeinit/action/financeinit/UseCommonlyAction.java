package com.bjike.goddess.financeinit.action.financeinit;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.financeinit.api.UseCommonlyAPI;
import com.bjike.goddess.financeinit.bo.UseCommonlyBO;
import com.bjike.goddess.financeinit.dto.UseCommonlyDTO;
import com.bjike.goddess.financeinit.entity.UseCommonly;
import com.bjike.goddess.financeinit.to.GuidePermissionTO;
import com.bjike.goddess.financeinit.to.UseCommonlyTO;
import com.bjike.goddess.financeinit.vo.UseCommonlyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 常用摘要
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-13 12:56 ]
 * @Description: [ 常用摘要 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("usecommonly")
public class UseCommonlyAction {
    @Autowired
    private UseCommonlyAPI useCommonlyAPI;
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

            Boolean isHasPermission = useCommonlyAPI.guidePermission(guidePermissionTO);
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
     * 列表总条数
     *
     * @param useCommonlyDTO 常用摘要dto
     * @des 获取所有常用摘要总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(UseCommonlyDTO useCommonlyDTO) throws ActException {
        try {
            Long count = useCommonlyAPI.countUse(useCommonlyDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个常用摘要
     *
     * @param id 常用摘要id
     * @return class UseCommonlyVO
     * @des 根据id获取常用摘要
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            UseCommonlyVO useCommonlyVO = BeanTransform.copyProperties(
                    useCommonlyAPI.getOneById(id), UseCommonlyVO.class);
            return ActResult.initialize(useCommonlyVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 常用摘要列表
     *
     * @param useCommonlyDTO 常用摘要dto
     * @return class UseCommonlyVO
     * @des 获取所有常用摘要
     * @version v1
     */
    @GetMapping("v1/listAccount")
    public Result findListAccount(UseCommonlyDTO useCommonlyDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<UseCommonlyVO> accountVOList = BeanTransform.copyProperties(
                    useCommonlyAPI.listUse(useCommonlyDTO), UseCommonlyVO.class, request);
            return ActResult.initialize(accountVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加凭证字
     *
     * @param useCommonlyTO 常用摘要数据to
     * @return class UseCommonlyVO
     * @des 添加常用摘要
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addAccount(@Validated(value = ADD.class) UseCommonlyTO useCommonlyTO, BindingResult bindingResult) throws ActException {
        try {
            UseCommonlyBO useCommonlyBO = useCommonlyAPI.addUse(useCommonlyTO);
            return ActResult.initialize(BeanTransform.copyProperties(useCommonlyBO, UseCommonlyVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除常用摘要
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteAccount(@PathVariable String id) throws ActException {
        try {
            useCommonlyAPI.delete(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败：" + e.getMessage());
        }
    }
}