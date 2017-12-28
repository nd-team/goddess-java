package com.bjike.goddess.moneyside.action.moneyside;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.moneyside.api.CallInfoAPI;
import com.bjike.goddess.moneyside.bo.CallInfoBO;
import com.bjike.goddess.moneyside.dto.CallInfoDTO;
import com.bjike.goddess.moneyside.to.CallInfoTO;
import com.bjike.goddess.moneyside.to.GuidePermissionTO;
import com.bjike.goddess.moneyside.vo.CallInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

/**
 * 招投信息列表
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 02:13 ]
 * @Description: [ 招投信息列表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("callinfo")
public class CallInfoAction {
    @Autowired
    private CallInfoAPI callInfoAPI;
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

            Boolean isHasPermission = callInfoAPI.guidePermission(guidePermissionTO);
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
     * 招投信息列表总条数
     *
     * @param callInfoDTO 招投信息列表dto
     * @des 获取所有招投信息列表总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(CallInfoDTO callInfoDTO) throws ActException {
        try {
            Long count = callInfoAPI.countCallInfo(callInfoDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个招投信息列表
     *
     * @param id
     * @return class CallInfoVO
     * @des 获取一个招投信息列表
     * @version v1
     */
    @GetMapping("v1/call/{id}")
    public Result call(@PathVariable String id) throws ActException {
        try {
            CallInfoBO callInfoBO = callInfoAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(callInfoBO, CallInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 招投信息列表
     *
     * @param callInfoDTO 招投信息列表dto
     * @return class CallInfoVO
     * @des 获取所有招投信息列表
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(CallInfoDTO callInfoDTO, HttpServletRequest request) throws ActException {
        try {
            List<CallInfoVO> callInfoVOS = BeanTransform.copyProperties
                    (callInfoAPI.findListCallInfo(callInfoDTO), CallInfoVO.class, request);
            return ActResult.initialize(callInfoVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加招投信息列表
     *
     * @param callInfoTO 招投信息列表数据to
     * @return class CallInfoVO
     * @des 添加招投信息列表
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(CallInfoTO.TestAdd.class) CallInfoTO callInfoTO, BindingResult bindingResult) throws ActException {
        try {
            CallInfoBO callInfoBO = callInfoAPI.insertCallInfo(callInfoTO);
            return ActResult.initialize(callInfoBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑招投信息列表
     *
     * @param callInfoTO 招投信息列表数据to
     * @return class CallInfoVO
     * @des 编辑招投信息列表
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(CallInfoTO.TestEdit.class) CallInfoTO callInfoTO, BindingResult bindingResult) throws ActException {
        try {
            CallInfoBO callInfoBO = callInfoAPI.editCallInfo(callInfoTO);
            return ActResult.initialize(callInfoBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除招投信息列表
     *
     * @param id 用户id
     * @des 根据用户id删除招投信息列表记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            callInfoAPI.removeCallInfo(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 申请投资
     *
     * @param callInfoTO 申请投资数据to
     * @return class CallInfoVO
     * @des 申请投资
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/apply")
    public Result apply(@Validated(CallInfoTO.TestApply.class) CallInfoTO callInfoTO, BindingResult bindingResult) throws ActException {
        try {
            CallInfoBO callInfoBO = callInfoAPI.applyInvest(callInfoTO);
            return ActResult.initialize(BeanTransform.copyProperties(callInfoBO,CallInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取招投信息列表
     *
     * @param innerProject 内部项目名称
     * @return class CallInfoVO
     * @des 根据内部项目名称获取招投信息列表
     * @version v1
     */
    @GetMapping("v1/getProject")
    public Result getProject(String innerProject) throws ActException {
        try {
            CallInfoBO callInfoBO = callInfoAPI.getInnerProject(innerProject);
            return ActResult.initialize(BeanTransform.copyProperties(callInfoBO, CallInfoVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取内部项目名称
     *
     * @des 获取内部项目名称
     * @version v1
     */
    @GetMapping("v1/getInnerProject")
    public Result getInnerProject() throws ActException {
        try {
            Set<String> set = callInfoAPI.getInnerProject();
            return ActResult.initialize(set);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}