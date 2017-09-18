package com.bjike.goddess.managepromotion.action.managepromotion;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contacts.api.CommonalityAPI;
import com.bjike.goddess.contacts.vo.CommonalityVO;
import com.bjike.goddess.managepromotion.api.EmailAPI;
import com.bjike.goddess.managepromotion.bo.EmailBO;
import com.bjike.goddess.managepromotion.dto.EmailDTO;
import com.bjike.goddess.managepromotion.to.EmailTO;
import com.bjike.goddess.managepromotion.to.GuidePermissionTO;
import com.bjike.goddess.managepromotion.vo.EmailVO;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 发送邮件
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-14 02:16 ]
 * @Description: [ 发送邮件 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("email")
public class EmailAction {
    @Autowired
    private EmailAPI emailAPI;
    @Autowired
    private CommonalityAPI commonalityAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;

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

            Boolean isHasPermission = emailAPI.guidePermission(guidePermissionTO);
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
     * @param dto 邮件信息dto
     * @des 获取所有邮件信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(EmailDTO dto) throws ActException {
        try {
            Long count = emailAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个邮件
     *
     * @param id 邮件信息id
     * @return class EmailVO
     * @des 根据id获取邮件信息
     * @version v1
     */
    @GetMapping("v1/email/{id}")
    public Result email(@PathVariable String id) throws ActException {
        try {
            EmailVO emailVO = BeanTransform.copyProperties(
                    emailAPI.getOne(id), EmailVO.class);
            return ActResult.initialize(emailVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 邮件列表
     *
     * @param emailDTO 邮件信息dto
     * @return class EmailVO
     * @des 获取所有信息邮件信息
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(EmailDTO emailDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<EmailVO> collectEmailVOList = BeanTransform.copyProperties(
                    emailAPI.list(emailDTO), EmailVO.class, request);
            return ActResult.initialize(collectEmailVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加招投标信息邮件汇总
     *
     * @param to 招投标信息邮件汇总基本信息数据to
     * @return class EmailVO
     * @des 添加招投标信息邮件汇总
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated() EmailTO to, BindingResult bindingResult) throws ActException {
        try {
            EmailBO bo = emailAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, EmailVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑招投标信息邮件汇总
     *
     * @param to 招投标信息邮件汇总基本信息数据bo
     * @return class EmailVO
     * @des 添加招投标信息邮件汇总
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated() EmailTO to, BindingResult bindingResult) throws ActException {
        try {
            EmailBO bo = emailAPI.edit(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, EmailVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除邮件信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            emailAPI.remove(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 检测
     *
     * @des 邮件发送
     * @version v1
     */
    @GetMapping("v1/sendEmail")
    public Result sendEmail() throws ActException {
        try {
            emailAPI.sendEmail();
            return ActResult.initialize("发送成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 查询用户
     *
     * @return class UserVO
     * @version v1
     */
    @GetMapping("v1/sendObject")
    public Result sendObject(HttpServletRequest request) throws ActException {
        try {
            List<UserBO> userBOS = positionDetailUserAPI.findUserListInOrgan();
            return ActResult.initialize(BeanTransform.copyProperties(userBOS, UserVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询未冻结的公共邮箱
     *
     * @return class CommonalityVO
     * @version v1
     */
    @GetMapping("v1/email")
    public Result email(HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(commonalityAPI.findThaw(), CommonalityVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}