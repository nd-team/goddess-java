package com.bjike.goddess.attendance.action.attendance;

import com.alibaba.dubbo.rpc.RpcContext;
import com.bjike.goddess.attendance.api.overtime.OverWorkAPI;
import com.bjike.goddess.attendance.dto.overtime.PhoneMyEntryOverWorkDTO;
import com.bjike.goddess.attendance.dto.overtime.PhoneMyOverWorkDTO;
import com.bjike.goddess.attendance.enums.OverWorkIndentity;
import com.bjike.goddess.attendance.vo.OverWorkVO;
import com.bjike.goddess.attendance.vo.overtime.PhoneOWIdentityVO;
import com.bjike.goddess.attendance.vo.overtime.PhoneOverWorkVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.PositionUserDetailAPI;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.storage.api.FileAPI;
import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.storage.vo.FileVO;
import com.bjike.goddess.user.api.UserAPI;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 手机版加班
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-10-10 10:32 ]
 * @Description: [ 加班 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("phoneoverwork")
public class PhoneOverWorkAction extends BaseFileAction {

    @Autowired
    private OverWorkAPI overWorkAPI;
    @Autowired
    private PositionUserDetailAPI positionUserDetailAPI;
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private FileAPI fileAPI;

    /**
     * 当前用户身份
     *
     * @return class PhoneOWIdentityVO
     * @des 根据id获取加班
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/identity")
    public Result identity() throws ActException {
        try {
            PhoneOWIdentityVO phoneOWIdentityVO = new PhoneOWIdentityVO();
            OverWorkIndentity overWorkIndentity = OverWorkIndentity.NOMAL;
            RpcContext.getContext().getAttachment("userToken");
            String userName = userAPI.currentUser().getUsername();
            RpcContext.getContext().setAttachment("userToken", userName);
            PositionDetailBO positionDetailBO = positionUserDetailAPI.getPosition(userName);
            RpcContext.getContext().setAttachment("userToken", userName);
            if ("admin".equalsIgnoreCase(userName)){
                overWorkIndentity = OverWorkIndentity.MANAGE;
            }
            else if (positionDetailBO != null) {
                if (StringUtils.isBlank(positionDetailBO.getPosition())) {
                    return null;
                } else {
                    if ( positionDetailBO.getPosition().contains("项目经理")) {
                        overWorkIndentity = OverWorkIndentity.MANAGE;
                    } else if (userName.toLowerCase().equals("ike") || positionDetailBO.getPosition().contains("负责人")) {
                        overWorkIndentity = OverWorkIndentity.CHARGE;
                    } else {
                        overWorkIndentity = OverWorkIndentity.NOMAL;
                    }
                }
            }
            phoneOWIdentityVO.setName(userName);
            phoneOWIdentityVO.setOverWorkIndentity(overWorkIndentity);
            return ActResult.initialize(phoneOWIdentityVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个加班
     *
     * @param id 加班id
     * @return class PhoneOverWorkVO
     * @des 根据id获取加班
     * @version v1
     */
    @GetMapping("v1/getPhoneOneById/{id}")
    public Result getPhoneOneById(@PathVariable String id) throws ActException {
        try {
            PhoneOverWorkVO overWorkVO = BeanTransform.copyProperties(
                    overWorkAPI.getPhoneOneById(id), PhoneOverWorkVO.class);
            return ActResult.initialize(overWorkVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 我的加班列表
     *
     * @param phoneMyOverWorkDTO 加班信息dto
     * @return class PhoneOverWorkVO
     * @des 我的加班列表
     * @version v1
     */
    @GetMapping("v1/my/list")
    public Result findListOverWork(@Validated(PhoneMyOverWorkDTO.TESTLIST.class) PhoneMyOverWorkDTO phoneMyOverWorkDTO, BindingResult bindingResult) throws ActException {
        try {
            List<PhoneOverWorkVO> overWorkVOList = BeanTransform.copyProperties(
                    overWorkAPI.myListOverWork(phoneMyOverWorkDTO), PhoneOverWorkVO.class, true);
            return ActResult.initialize(overWorkVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 我录入的加班列表
     *
     * @param phoneMyEntryOverWorkDTO 加班信息dto
     * @return class PhoneOverWorkVO
     * @des 仅项目经理和负责人和本人录入的才可以看
     * @version v1
     */
    @GetMapping("v1/my/entry/list")
    public Result myEntryList(@Validated(PhoneMyEntryOverWorkDTO.TESTLIST.class) PhoneMyEntryOverWorkDTO phoneMyEntryOverWorkDTO, BindingResult bindingResult) throws ActException {
        try {
            List<PhoneOverWorkVO> overWorkVOList = BeanTransform.copyProperties(
                    overWorkAPI.myEntryList(phoneMyEntryOverWorkDTO), PhoneOverWorkVO.class, true);
            return ActResult.initialize(overWorkVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 待我审核的加班列表
     *
     * @param phoneMyEntryOverWorkDTO 加班信息dto
     * @return class PhoneOverWorkVO
     * @des 仅项目经理和审核人才可以看
     * @version v1
     */
    @GetMapping("v1/my/audit/list")
    public Result myAuditList(@Validated(PhoneMyEntryOverWorkDTO.TESTLIST.class) PhoneMyEntryOverWorkDTO phoneMyEntryOverWorkDTO, BindingResult bindingResult) throws ActException {
        try {
            List<PhoneOverWorkVO> overWorkVOList = BeanTransform.copyProperties(
                    overWorkAPI.myAuditList(phoneMyEntryOverWorkDTO), PhoneOverWorkVO.class, true);
            return ActResult.initialize(overWorkVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}