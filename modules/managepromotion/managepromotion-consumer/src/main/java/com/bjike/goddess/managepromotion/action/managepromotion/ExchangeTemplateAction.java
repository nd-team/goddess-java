package com.bjike.goddess.managepromotion.action.managepromotion;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.managepromotion.api.ExchangeTemplateAPI;
import com.bjike.goddess.managepromotion.bo.ExchangeTemplateBO;
import com.bjike.goddess.managepromotion.dto.ExchangeTemplateDTO;
import com.bjike.goddess.managepromotion.to.ExchangeTemplateTO;
import com.bjike.goddess.managepromotion.to.GuidePermissionTO;
import com.bjike.goddess.managepromotion.to.SkillStandardTO;
import com.bjike.goddess.managepromotion.vo.ExchangeTemplateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 各类交流沟通模板
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-11 11:49 ]
 * @Description: [ 各类交流沟通模板 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("exchangetemplate")
public class ExchangeTemplateAction {
    @Autowired
    private ExchangeTemplateAPI exchangeTemplateAPI;

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

            Boolean isHasPermission = exchangeTemplateAPI.guidePermission(guidePermissionTO);
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
     * 各类交流沟通模板总条数
     *
     * @param dto 各类交流沟通模板dto
     * @des 获取所有各类交流沟通模板
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(ExchangeTemplateDTO dto) throws ActException {
        try {
            Long count = exchangeTemplateAPI.count(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个各类交流沟通模板
     *
     * @param id
     * @return class ExchangeTemplateVO
     * @des 获取一个各类交流沟通模板
     * @version v1
     */
    @GetMapping("v1/template/{id}")
    public Result template(@PathVariable String id) throws ActException {
        try {
            ExchangeTemplateBO bo = exchangeTemplateAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, ExchangeTemplateVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 各类交流沟通模板列表
     *
     * @param dto 各类交流沟通模板记录dto
     * @return class ExchangeTemplateVO
     * @des 获取所有各类交流沟通模板
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(ExchangeTemplateDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<ExchangeTemplateVO> exchangeTemplateVOS = BeanTransform.copyProperties(
                    exchangeTemplateAPI.list(dto), ExchangeTemplateVO.class, request);
            return ActResult.initialize(exchangeTemplateVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加各类交流沟通模板
     *
     * @param to 各类交流沟通模板to
     * @return class ExchangeTemplateVO
     * @des 添加各类交流沟通模板
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(SkillStandardTO.TestAdd.class) ExchangeTemplateTO to, BindingResult bindingResult) throws ActException {
        try {
            ExchangeTemplateBO bo = exchangeTemplateAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, ExchangeTemplateVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑各类交流沟通模板
     *
     * @param to 各类交流沟通模板数据to
     * @return class ExchangeTemplateVO
     * @des 编辑各类交流沟通模板
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(SkillStandardTO.TestEdit.class) ExchangeTemplateTO to, BindingResult bindingResult) throws ActException {
        try {
            ExchangeTemplateBO bo = exchangeTemplateAPI.edit(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, ExchangeTemplateVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除各类交流沟通模板
     *
     * @param id 用户id
     * @des 根据用户id删除各类交流沟通模板
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            exchangeTemplateAPI.delete(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据标题获得发送邮件模板
     *
     * @param title 标题
     * @return class ExchangeTemplateVO
     * @des 根据标题获得发送邮件模板
     * @version v1
     */
    @GetMapping("v1/getContent")
    public Result getContent(String title) throws ActException {
        try {
            List<ExchangeTemplateVO> exchangeTemplateVOS = BeanTransform.copyProperties(exchangeTemplateAPI.getContent(title), ExchangeTemplateVO.class);
            return ActResult.initialize(exchangeTemplateVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有标题
     *
     * @des 获取所有标题
     * @version v1
     */
    @GetMapping("v1/getTitle")
    public Result getTitle() throws ActException {
        try {
            List<String> titleList = exchangeTemplateAPI.getTitle();
            return ActResult.initialize(titleList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}