package com.bjike.goddess.feedback.action.feedback;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.feedback.api.SendEmailAPI;
import com.bjike.goddess.feedback.bo.SendEmailBO;
import com.bjike.goddess.feedback.dto.SendEmailDTO;
import com.bjike.goddess.feedback.entity.SendEmail;
import com.bjike.goddess.feedback.to.SendEmailTO;
import com.bjike.goddess.feedback.vo.SendEmailVO;
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
 * @Date: [ 2017-08-05 10:27 ]
 * @Description: [ 发送邮件 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("sendemail")
public class SendEmailAction {
//    @Autowired
//    private SendEmailAPI sendEmailAPI;
//    /**
//     * 列表总条数
//     *
//     * @param sendEmailDTO 邮件信息dto
//     * @des 获取所有邮件信息总条数
//     * @version v1
//     */
//    @GetMapping("v1/count")
//    public Result count(SendEmailDTO sendEmailDTO) throws ActException {
//        try {
//            Long count = sendEmailAPI.counts(sendEmailDTO);
//            return ActResult.initialize(count);
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//    /**
//     * 一个邮件
//     *
//     * @param id 邮件信息id
//     * @return class SendEmailVO
//     * @des 根据id获取邮件信息
//     * @version v1
//     */
//    @GetMapping("v1/getOneById/{id}")
//    public Result getOneById(@PathVariable String id) throws ActException {
//        try {
//            SendEmailVO projectCarryVO = BeanTransform.copyProperties(
//                    sendEmailAPI.getOne(id), SendEmailVO.class);
//            return ActResult.initialize(projectCarryVO);
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//    /**
//     * 邮件列表
//     *
//     * @param sendEmailDTO 邮件信息dto
//     * @return class SendEmailVO
//     * @des 获取所有邮件信息
//     * @version v1
//     */
//    @GetMapping("v1/list")
//    public Result lis(SendEmailDTO sendEmailDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
//        try {
//            List<SendEmailVO> sendEmailVOS = BeanTransform.copyProperties(
//                    sendEmailAPI.list(sendEmailDTO), SendEmailVO.class, request);
//            return ActResult.initialize(sendEmailVOS);
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//    /**
//     * 添加邮件
//     *
//     * @param sendEmailTO 邮件基本信息数据to
//     * @return class SendEmailVO
//     * @des 添加邮件
//     * @version v1
//     */
//    @LoginAuth
//    @PostMapping("v1/add")
//    public Result add(@Validated(SendEmailTO.TestAdd.class) SendEmailTO sendEmailTO, BindingResult bindingResult) throws ActException {
//        try {
//            SendEmailBO sendEmailBO = sendEmailAPI.add(sendEmailTO);
//            return ActResult.initialize(BeanTransform.copyProperties(sendEmailBO, SendEmailVO.class, true));
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//
//    /**
//     * 编辑邮件
//     *
//     * @param sendEmailTO 邮件基本信息数据bo
//     * @return class SendEmailVO
//     * @des 编辑邮件
//     * @version v1
//     */
//    @LoginAuth
//    @PostMapping("v1/edit")
//    public Result edit(@Validated(SendEmailTO.TestAdd.class) SendEmailTO sendEmailTO, BindingResult bindingResult) throws ActException {
//        try {
//            SendEmailBO sendEmailBO = sendEmailAPI.edit(sendEmailTO);
//            return ActResult.initialize(BeanTransform.copyProperties(sendEmailBO, SendEmailVO.class, true));
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//    /**
//     * 删除
//     *
//     * @param id id
//     * @des 根据id删除邮件记录
//     * @version v1
//     */
//    @LoginAuth
//    @DeleteMapping("v1/delete/{id}")
//    public Result delete(@PathVariable String id) throws ActException {
//        try {
//            sendEmailAPI.delete(id);
//            return new ActResult("delete success!");
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//    /**
//     * 冻结
//     *
//     * @param id id
//     * @des 根据id冻结邮件记录
//     * @version v1
//     */
//    @LoginAuth
//    @DeleteMapping("v1/congeal/{id}")
//    public Result congeal(@PathVariable String id) throws ActException {
//        try {
//            sendEmailAPI.congeal(id);
//            return new ActResult("congeal success!");
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//
//    }
//
//
//    /**
//     * 解冻
//     *
//     * @param id id
//     * @des 根据id解冻邮件记录
//     * @version v1
//     */
//    @LoginAuth
//    @DeleteMapping("v1/thaw/{id}")
//    public Result thaw(@PathVariable String id) throws ActException {
//        try {
//            sendEmailAPI.thaw(id);
//            return new ActResult("thaw success!");
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//    /**
//     * 检测
//     *
//     * @des 发送邮件
//     * @version v1
//     */
//    @GetMapping("v1/checkEmail")
//    public Result checkEmail() throws ActException {
//        try {
//            sendEmailAPI.checkSendEmail();
//            return ActResult.initialize("发送成功");
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }

}