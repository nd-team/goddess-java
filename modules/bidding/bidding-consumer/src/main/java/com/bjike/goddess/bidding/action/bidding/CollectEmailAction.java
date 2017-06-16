package com.bjike.goddess.bidding.action.bidding;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.bidding.api.CollectEmailAPI;
import com.bjike.goddess.bidding.bo.CollectEmailBO;
import com.bjike.goddess.bidding.dto.CollectEmailDTO;
import com.bjike.goddess.bidding.to.CollectEmailTO;
import com.bjike.goddess.bidding.to.GuidePermissionTO;
import com.bjike.goddess.bidding.vo.CollectEmailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 招投标信息邮件发送定制
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T19:08:18.885 ]
 * @Description: [ 招投标信息邮件发送定制 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("collectemail")
public class CollectEmailAction {

    @Autowired
    private CollectEmailAPI collectEmailAPI;


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

            Boolean isHasPermission = collectEmailAPI.guidePermission(guidePermissionTO);
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
     * @param collectEmailDTO 招投标信息邮件信息dto
     * @des 获取所有招投标信息邮件信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(CollectEmailDTO collectEmailDTO) throws ActException {
        try {
            Long count = collectEmailAPI.counts(collectEmailDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个招投标信息邮件
     *
     * @param id 招投标信息邮件信息id
     * @return class CollectEmailVO
     * @des 根据id获取招投标信息邮件信息
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            CollectEmailVO projectCarryVO = BeanTransform.copyProperties(
                    collectEmailAPI.getOne(id), CollectEmailVO.class);
            return ActResult.initialize(projectCarryVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 招投标信息邮件汇总列表
     *
     * @param collectEmailDTO 招投标信息邮件汇总信息dto
     * @return class CollectEmailVO
     * @des 获取所有招投标信息邮件汇总信息
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListCollectEmail(CollectEmailDTO collectEmailDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<CollectEmailVO> collectEmailVOList = BeanTransform.copyProperties(
                    collectEmailAPI.listCollectEmail(collectEmailDTO), CollectEmailVO.class, request);
            return ActResult.initialize(collectEmailVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加招投标信息邮件汇总
     *
     * @param collectEmailTO 招投标信息邮件汇总基本信息数据to
     * @return class CollectEmailVO
     * @des 添加招投标信息邮件汇总
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addCollectEmail(@Validated(CollectEmailTO.TestAdd.class) CollectEmailTO collectEmailTO, BindingResult bindingResult) throws ActException {
        try {
            CollectEmailBO collectEmailBO1 = collectEmailAPI.addCollectEmail(collectEmailTO);
            return ActResult.initialize(BeanTransform.copyProperties(collectEmailBO1, CollectEmailVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑招投标信息邮件汇总
     *
     * @param collectEmailTO 招投标信息邮件汇总基本信息数据bo
     * @return class CollectEmailVO
     * @des 添加招投标信息邮件汇总
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result editCollectEmail(@Validated(CollectEmailTO.TestAdd.class) CollectEmailTO collectEmailTO, BindingResult bindingResult) throws ActException {
        try {
            CollectEmailBO collectEmailBO1 = collectEmailAPI.editCollectEmail(collectEmailTO);
            return ActResult.initialize(BeanTransform.copyProperties(collectEmailBO1, CollectEmailVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除招投标信息邮件汇总信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteCollectEmail(@PathVariable String id, BindingResult bindingResult) throws ActException {
        try {
            collectEmailAPI.deleteCollectEmail(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 冻结
     *
     * @param id id
     * @des 根据id冻结招投标信息邮件汇总记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/congeal/{id}")
    public Result congeal(@PathVariable String id) throws ActException {
        try {
            collectEmailAPI.congealCollectEmail(id);
            return new ActResult("congeal success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 解冻
     *
     * @param id id
     * @des 根据id解冻招投标信息邮件汇总记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/thaw/{id}")
    public Result thaw(@PathVariable String id) throws ActException {
        try {
            collectEmailAPI.thawCollectEmail(id);
            return new ActResult("thaw success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 检测
     *
     * @des 招投标信息邮件汇总
     * @version v1
     */
    @GetMapping("v1/checkEmail")
    public Result checkEmail() throws ActException {
        try {
            collectEmailAPI.checkSendEmail();
            return ActResult.initialize("发送成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}