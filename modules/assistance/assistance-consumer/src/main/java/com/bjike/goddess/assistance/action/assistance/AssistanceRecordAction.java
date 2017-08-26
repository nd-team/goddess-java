package com.bjike.goddess.assistance.action.assistance;

import com.alibaba.dubbo.rpc.RpcContext;
import com.bjike.goddess.assemble.api.ModuleAPI;
import com.bjike.goddess.assistance.api.AssistanceRecordAPI;
import com.bjike.goddess.assistance.bo.AssistanceRecordBO;
import com.bjike.goddess.assistance.dto.AssistanceRecordDTO;
import com.bjike.goddess.assistance.to.AssistanceRecordTO;
import com.bjike.goddess.assistance.to.GuidePermissionTO;
import com.bjike.goddess.assistance.vo.AssistanceRecordVO;
import com.bjike.goddess.common.api.constant.RpcCommon;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 公司员工补助信息记录
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-14 10:07 ]
 * @Description: [ 公司员工补助信息记录 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("assistancerecord")
public class AssistanceRecordAction {


    @Autowired
    private AssistanceRecordAPI assistanceRecordAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;
    @Autowired
    private ModuleAPI moduleAPI;

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

            Boolean isHasPermission = assistanceRecordAPI.guidePermission(guidePermissionTO);
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
     * 总条数
     *
     * @param assistanceRecordDTO 公司员工补助信息记录信息dto
     * @des 获取所有公司员工补助信息记录信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(AssistanceRecordDTO assistanceRecordDTO) throws ActException {
        try {
            Long count = assistanceRecordAPI.countAssistanceRecord(assistanceRecordDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个
     *
     * @param id 公司员工补助信息id
     * @return class AssistanceRecordVO
     * @des 一个公司员工补助信息
     * @version v1
     */
    @GetMapping("v1/one/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            AssistanceRecordVO ageAssistVO = BeanTransform.copyProperties(
                    assistanceRecordAPI.getOneById(id), AssistanceRecordVO.class);
            return ActResult.initialize(ageAssistVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 列表
     *
     * @param assistanceRecordDTO 公司员工补助信息记录信息dto
     * @return class AssistanceRecordVO
     * @des 获取所有公司员工补助信息记录信息
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListAssistanceRecord(AssistanceRecordDTO assistanceRecordDTO, BindingResult bindingResult) throws ActException {
        try {
            List<AssistanceRecordVO> assistanceRecordVOList = BeanTransform.copyProperties(
                    assistanceRecordAPI.listAssistanceRecord(assistanceRecordDTO), AssistanceRecordVO.class, true);
            return ActResult.initialize(assistanceRecordVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param assistanceRecordTO 公司员工补助信息记录基本信息数据to
     * @return class AssistanceRecordVO
     * @des 添加公司员工补助信息记录
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addAssistanceRecord(@Validated AssistanceRecordTO assistanceRecordTO, BindingResult bindingResult) throws ActException {
        try {
            AssistanceRecordBO assistanceRecordBO1 = assistanceRecordAPI.addAssistanceRecord(assistanceRecordTO);
            return ActResult.initialize(BeanTransform.copyProperties(assistanceRecordBO1, AssistanceRecordVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑
     *
     * @param assistanceRecordTO 公司员工补助信息记录基本信息数据bo
     * @return class AssistanceRecordVO
     * @des 添加公司员工补助信息记录
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editAssistanceRecord(@Validated AssistanceRecordTO assistanceRecordTO) throws ActException {
        try {
            AssistanceRecordBO assistanceRecordBO1 = assistanceRecordAPI.editAssistanceRecord(assistanceRecordTO);
            return ActResult.initialize(BeanTransform.copyProperties(assistanceRecordBO1, AssistanceRecordVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除公司员工补助信息记录信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteAssistanceRecord(@PathVariable String id) throws ActException {
        try {
            assistanceRecordAPI.deleteAssistanceRecord(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败：" + e.getMessage());
        }
    }

    /**
     * 获取所有用户
     *
     * @return class UserVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/users")
    public Result users(HttpServletRequest request) throws ActException {
        try {
            List<UserBO> list = new ArrayList<>();
            String token = request.getHeader(RpcCommon.USER_TOKEN).toString();
            if (moduleAPI.isCheck("organize")) {
                RpcContext.getContext().setAttachment(RpcCommon.USER_TOKEN, token);
                list = positionDetailUserAPI.findUserListInOrgan();
            }
            return ActResult.initialize(BeanTransform.copyProperties(list, UserVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}