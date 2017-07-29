package com.bjike.goddess.contractcommunicat.action.contractcommunicat;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contractcommunicat.api.ContractCollectEmailAPI;
import com.bjike.goddess.contractcommunicat.bo.CollectEmailBO;
import com.bjike.goddess.contractcommunicat.dto.CollectEmailDTO;
import com.bjike.goddess.contractcommunicat.entity.CollectEmail;
import com.bjike.goddess.contractcommunicat.to.CollectEmailTO;
import com.bjike.goddess.contractcommunicat.to.GuidePermissionTO;
import com.bjike.goddess.contractcommunicat.vo.CollectEmailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 商务项目合同邮件
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-16T19:08:18.885 ]
 * @Description: [ 商务项目合同邮件 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("colletEmail")
public class ContractCollectEmailAction {

    @Autowired
    private ContractCollectEmailAPI collectEmailAPI;


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
     * @param collectEmailDTO 项目合同邮件信息dto
     * @des 获取所有项目合同邮件信息总条数
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
     * 商务洽谈邮件汇总列表
     *
     * @param collectEmailDTO 商务邮件汇总信息dto
     * @return class CollectEmailVO
     * @des 获取所有商务邮件汇总信息
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
     * 添加商务洽谈邮件汇总
     *
     * @param collectEmailTO 商务邮件汇总基本信息数据to
     * @return class CollectEmailVO
     * @des 添加商务邮件汇总
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
     * 编辑商务洽谈邮件汇总
     *
     * @param collectEmailTO 商务邮件汇总基本信息数据bo
     * @return class CollectEmailVO
     * @des 添加商务邮件汇总
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result editCollectEmail(@Validated(CollectEmailTO.TestAdd.class) CollectEmailTO collectEmailTO,BindingResult bindingResult) throws ActException {
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
     * @des 根据id删除商务洽谈邮件汇总信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteCollectEmail(@PathVariable String id ) throws ActException {
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
     * @des 根据id冻结商务洽谈邮件汇总记录
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
     * @des 根据id解冻商务洽谈邮件汇总记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/thaw/{id}")
    public Result thaw(@PathVariable String id ) throws ActException {
        try {
            collectEmailAPI.thawCollectEmail(id);
            return new ActResult("thaw success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 汇总项目承包商务洽谈
     *
     * @param collectEmailTO 地区
     * @return class CollectEmailVO
     * @des 商务邮件汇总签订合同与预订
     * @version v1
     */

    @PostMapping("v1/collectContact")
    public Result collectContact(CollectEmailTO collectEmailTO, BindingResult bindingResult) throws ActException {
        List<CollectEmailVO> collectEmailVOList = new ArrayList<>();
        CollectEmail collectEmail = BeanTransform.copyProperties(collectEmailTO, CollectEmail.class);
        try {
            collectEmailVOList = BeanTransform.copyProperties(
                    collectEmailAPI.gatherPb(collectEmail), CollectEmailVO.class, true);
            return ActResult.initialize(collectEmailVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总项目外包商务洽谈
     *
     * @param collectEmailTO 甲方
     * @return class CollectEmailVO
     * @des 汇总合同基本信息
     * @version v1
     */
    @GetMapping("v1/collect/outsourcing")
    public Result collectOutsourcing(@Validated(CollectEmailDTO.TestFirstCompany.class) CollectEmailTO collectEmailTO, BindingResult bindingResult) throws ActException {
        List<CollectEmailVO> collectEmailVOList = new ArrayList<>();
        CollectEmail collectEmail = BeanTransform.copyProperties(collectEmailTO,CollectEmail.class);
        try {
            collectEmailVOList = BeanTransform.copyProperties(
                    collectEmailAPI.gatherPc(collectEmail), CollectEmailVO.class);
            return ActResult.initialize(collectEmailVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }



    /**
     * 检测
     *
     * @des 商务邮件汇总汇总派工合同
     * @version v1
     */
    @GetMapping("v1/checkSendEmail")
    public Result checkSendEmail(   ) throws ActException {
        try {
            collectEmailAPI.checkSendEmail( );
            return ActResult.initialize("发送成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}