package com.bjike.goddess.capability.action.capability;

import com.bjike.goddess.capability.api.CollectEmailAPI;
import com.bjike.goddess.capability.bo.CollectEmailBO;
import com.bjike.goddess.capability.dto.CollectEmailDTO;
import com.bjike.goddess.capability.to.CollectEmailTO;
import com.bjike.goddess.capability.to.GuidePermissionTO;
import com.bjike.goddess.capability.vo.CollectEmailVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 商业能力邮件发送定制
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-16T19:08:18.885 ]
 * @Description: [ 商业能力邮件发送定制 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("cusemail")
public class CollectEmailAction {

    @Autowired
    private CollectEmailAPI collectEmailAPI;

    /**
     * 总条数
     *
     * @param collectEmailDTO dto
     * @des 获取总条数
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
     * 一个汇总
     *
     * @param id
     * @return class CollectEmailVO
     * @des 获取一个汇总
     * @version v1
     */
    @GetMapping("v1/getOne/{id}")
    public Result getOne(@PathVariable String id) throws ActException {
        try {
            CollectEmailBO collectEmailBO = collectEmailAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(collectEmailBO, CollectEmailVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 商业能力邮件汇总列表
     *
     * @param collectEmailDTO 商业能力邮件汇总信息dto
     * @return class CollectEmailVO
     * @des 获取所有商业能力邮件汇总信息
     * @version v1
     */
    @GetMapping("v1/listCollectEmail")
    public Result findListCollectEmail(CollectEmailDTO collectEmailDTO) throws ActException {
        try {
            List<CollectEmailVO> collectEmailVOList = BeanTransform.copyProperties(
                    collectEmailAPI.listCollectEmail(collectEmailDTO), CollectEmailVO.class, true);
            return ActResult.initialize(collectEmailVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加商业能力邮件汇总
     *
     * @param collectEmailTO 商业能力邮件汇总基本信息数据to
     * @return class CollectEmailVO
     * @des 添加商业能力邮件汇总, 行业不能为空发送间隔汇总间隔等都不能为空
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addCollectEmail(@Validated CollectEmailTO collectEmailTO) throws ActException {
        try {
            CollectEmailBO collectEmailBO1 = collectEmailAPI.addCollectEmail(collectEmailTO);
            return ActResult.initialize(BeanTransform.copyProperties(collectEmailBO1, CollectEmailVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑商业能力邮件汇总
     *
     * @param collectEmailTO 商业能力邮件汇总基本信息数据bo
     * @return class CollectEmailVO
     * @des 添加商业能力邮件汇总, 行业不能为空发送间隔汇总间隔等都不能为空
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editCollectEmail(@Validated CollectEmailTO collectEmailTO) throws ActException {
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
     * @des 根据id删除商业能力邮件汇总信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteCollectEmail(@PathVariable String id) throws ActException {
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
     * @des 根据id冻结商业能力邮件汇总记录
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/congeal/{id}")
    public Result congeal(@PathVariable String id) throws ActException {
        try {
            collectEmailAPI.congealCollectEmail(id);
            return new ActResult("congeal success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**地方
     * 解冻
     *
     * @param id id
     * @des 根据id解冻商业能力邮件汇总记录
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/thaw/{id}")
    public Result thaw(@PathVariable String id) throws ActException {
        try {
            collectEmailAPI.thawCollectEmail(id);
            return new ActResult("thaw success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 汇总商业能力
     *
     * @param collectEmailDTO collectEmailDTO
     * @return class CollectEmailVO
     * @des 根据公司名称汇总
     * @version v1
     */
    @GetMapping("v1/collectCompanyCapability")
    public Result collectCompanyCapability(@Validated({CollectEmailDTO.TestCompany.class}) CollectEmailDTO collectEmailDTO) throws ActException {
        String[] companys = collectEmailDTO.getCompanys();
        try {
            List<CollectEmailVO> collectEmailVOList = BeanTransform.copyProperties(
                    collectEmailAPI.collectCompanyEmail(companys), CollectEmailVO.class, true);
            return ActResult.initialize(collectEmailVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 汇总个人能力
     *
     * @param collectEmailDTO collectEmailDTO
     * @return class CollectEmailVO
     * @des 根据姓名汇总
     * @version v1
     */
    @GetMapping("v1/collectSelfCapability")
    public Result collectSelfCapability(@Validated({CollectEmailDTO.TestPerson.class}) CollectEmailDTO collectEmailDTO) throws ActException {
        try {
            String[] names = collectEmailDTO.getNames();
            List<CollectEmailVO> collectEmailVOList = BeanTransform.copyProperties(
                    collectEmailAPI.collectSelfEmail(names), CollectEmailVO.class, true);
            return ActResult.initialize(collectEmailVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总合作能力
     *
     * @param collectEmailDTO collectEmailDTO
     * @return class CollectEmailVO
     * @des 根据公司名称汇总
     * @version v1
     */
    @GetMapping("v1/collectCooperCapability")
    public Result collectCooperCapability(@Validated(CollectEmailDTO.TestCompany.class) CollectEmailDTO collectEmailDTO) throws ActException {
        String[] companys = collectEmailDTO.getCompanys();
        try {
            List<CollectEmailVO> collectEmailVOList = BeanTransform.copyProperties(
                    collectEmailAPI.collectCooperEmail(companys), CollectEmailVO.class, true);
            return ActResult.initialize(collectEmailVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 公司名或姓名
     *
     * @param type 汇总类型
     * @des 根据汇总类型获取公司名或姓名
     * @version v1
     */
    @GetMapping("v1/listName")
    public Result listName(String type) throws ActException {
        try {
            List<String> list = collectEmailAPI.listName(type);
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 检测
     *
     * @des 汇总商业能力汇总个人能力汇总合作能力
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
}