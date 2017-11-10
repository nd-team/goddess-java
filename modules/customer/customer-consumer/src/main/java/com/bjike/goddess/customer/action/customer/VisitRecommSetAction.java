package com.bjike.goddess.customer.action.customer;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.customer.api.VisitRecommSetAPI;
import com.bjike.goddess.customer.bo.VisitRecommSetBO;
import com.bjike.goddess.customer.dto.VisitRecommSetDTO;
import com.bjike.goddess.customer.entity.VisitRecommSet;
import com.bjike.goddess.customer.to.GuidePermissionTO;
import com.bjike.goddess.customer.to.VisitRecommSetTO;
import com.bjike.goddess.customer.vo.VisitRecommSetVO;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 拜访推荐设置
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-03 04:12 ]
 * @Description: [ 拜访推荐设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("visitrecommset")
public class VisitRecommSetAction {
    @Autowired
    private VisitRecommSetAPI visitRecommSetAPI;


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

            Boolean isHasPermission = visitRecommSetAPI.guidePermission(guidePermissionTO);
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
     * 拜访设置列表总条数
     *
     * @param visitRecommSetDTO 客户邮件汇总dto
     * @des 获取所有拜访设置总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(VisitRecommSetDTO visitRecommSetDTO) throws ActException {
        try {
            Long count = visitRecommSetAPI.countVisitReco(visitRecommSetDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 拜访设置列表
     *
     * @param visitRecommSetDTO 拜访设置dto
     * @return class VisitRecommSetVO
     * @des 获取所有拜访设置信息
     * @version v1
     */
    @GetMapping("v1/listCusEmail")
    public Result findListCusEmail(VisitRecommSetDTO visitRecommSetDTO) throws ActException {
        try {

            List<VisitRecommSetVO> visitRecommSetVOS = BeanTransform.copyProperties(
                    visitRecommSetAPI.listVisitReco(visitRecommSetDTO), VisitRecommSetVO.class, true);
            return ActResult.initialize(visitRecommSetVOS);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加拜访设置
     *
     * @param visitRecommSetTO 拜访设置数据to
     * @return class VisitRecommSetVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addCusEmail(@Validated(ADD.class) VisitRecommSetTO visitRecommSetTO, BindingResult bindingResult) throws ActException {
        try {

            VisitRecommSetBO visitRecommSetBO = visitRecommSetAPI.addVisitReco(visitRecommSetTO);
            return ActResult.initialize(BeanTransform.copyProperties(visitRecommSetBO, VisitRecommSetVO.class, true));

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑拜访设置
     *
     * @param visitRecommSetTO 客户邮件汇总基本信息数据bo
     * @return class VisitRecommSetVO
     * @des 添加客户邮件汇总, 行业不能为空发送间隔汇总间隔等都不能为空
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editCusEmail(@Validated(EDIT.class) VisitRecommSetTO visitRecommSetTO, BindingResult bindingResult) throws ActException {
        try {

            VisitRecommSetBO visitRecommSetBO = visitRecommSetAPI.editVisitReco(visitRecommSetTO);
            return ActResult.initialize(BeanTransform.copyProperties(visitRecommSetBO, VisitRecommSetVO.class, true));

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除客户邮件汇总信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteCusEmail(@PathVariable String id) throws ActException {
        try {

            visitRecommSetAPI.deleteVisitReco(id);
            return new ActResult("delete success!");

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 冻结
     *
     * @param id id
     * @des 根据id冻结客户邮件汇总记录
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/congeal/{id}")
    public Result congeal(@PathVariable String id) throws ActException {
        try {
            visitRecommSetAPI.congealVisitReco(id);
            return new ActResult("congeal success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 解冻
     *
     * @param id id
     * @des 根据id解冻客户邮件汇总记录
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/thaw/{id}")
    public Result thaw(@PathVariable String id) throws ActException {
        try {
            visitRecommSetAPI.thawVisitReco(id);
            return new ActResult("thaw success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 检测
     *
     * @des 调用更新算法
     * @version v1
     */
    @GetMapping("v1/checkEmail")
    public Result checkEmail(   ) throws ActException {
        try {
            visitRecommSetAPI.remindSend( );
            return ActResult.initialize("发送成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}