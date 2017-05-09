package com.bjike.goddess.businessinteraction.action.businessinteraction;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.businessinteraction.api.CollectEmailAPI;
import com.bjike.goddess.businessinteraction.bo.CollectEmailBO;
import com.bjike.goddess.businessinteraction.dto.CollectEmailDTO;
import com.bjike.goddess.businessinteraction.to.CollectEmailTO;
import com.bjike.goddess.businessinteraction.vo.CollectEmailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 邮件发送定制
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-16T19:08:18.885 ]
 * @Description: [ 邮件发送定制 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("collectemail")
public class CollectEmailAction {

    @Autowired
    private CollectEmailAPI collectEmailAPI;

    /**
     *  列表总条数
     *
     * @param collectEmailDTO  邮件信息dto
     * @des 获取所有邮件信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(CollectEmailDTO collectEmailDTO) throws ActException {
        try {
            Long count = collectEmailAPI.countInter(collectEmailDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个邮件
     *
     * @param id 邮件信息id
     * @des 根据id获取所有邮件信息
     * @return  class CollectEmailVO
     * @version v1
     */
    @GetMapping("v1/getOne/{id}")
    public Result getOne( @PathVariable String id ) throws ActException {
        try {
            CollectEmailVO collectEmailVOList = BeanTransform.copyProperties(
                    collectEmailAPI.getOneById(id), CollectEmailVO.class);
            return ActResult.initialize(collectEmailVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    
    /**
     * 邮件汇总列表
     *
     * @param collectEmailDTO 邮件汇总信息dto
     * @des 获取所有邮件汇总信息
     * @return  class CollectEmailVO
     * @version v1
     */
    @GetMapping("v1/listCollectEmail")
    public Result findListCollectEmail(CollectEmailDTO collectEmailDTO, HttpServletRequest request) throws ActException {
        try {
            List<CollectEmailVO> collectEmailVOList = BeanTransform.copyProperties(
                    collectEmailAPI.listCollectEmail(collectEmailDTO), CollectEmailVO.class, request);
            return ActResult.initialize(collectEmailVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加邮件汇总
     *
     * @param collectEmailTO 邮件汇总基本信息数据to
     * @des 添加邮件汇总,行业不能为空发送间隔汇总间隔等都不能为空
     * @return  class CollectEmailVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addCollectEmail( @Validated CollectEmailTO collectEmailTO , BindingResult bindingResult) throws ActException {
        try {
            CollectEmailBO collectEmailBO1 = collectEmailAPI.addCollectEmail(collectEmailTO);
            return ActResult.initialize(BeanTransform.copyProperties(collectEmailBO1,CollectEmailVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑邮件汇总
     *
     * @param collectEmailTO 邮件汇总基本信息数据bo
     * @des 添加邮件汇总,行业不能为空发送间隔汇总间隔等都不能为空
     * @return  class CollectEmailVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editCollectEmail(@Validated CollectEmailTO collectEmailTO , BindingResult bindingResult) throws ActException {
        try {
            CollectEmailBO collectEmailBO1 = collectEmailAPI.editCollectEmail(collectEmailTO);
            return ActResult.initialize(BeanTransform.copyProperties(collectEmailBO1,CollectEmailVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除邮件汇总信息记录
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
     * @des 根据id冻结邮件汇总记录
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


    /**
     * 解冻
     *
     * @param id id
     * @des 根据id解冻邮件汇总记录
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/thaw/{id}")
    public Result thaw (@PathVariable String id) throws ActException {
        try {
            collectEmailAPI.thawCollectEmail(id);
            return new ActResult("thaw success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 汇总
     *
     * @param collectEmailDTO 数据
     * @des 根据地区汇总
     * @return  class CollectEmailVO
     * @version v1
     */
    @GetMapping("v1/collect")
    public Result collect ( @Validated(CollectEmailDTO.TestCollect.class) CollectEmailDTO collectEmailDTO ) throws ActException {
        String[] areas = collectEmailDTO.getAreas();
        try {
            List<CollectEmailVO> collectEmailVOList = BeanTransform.copyProperties(
                    collectEmailAPI.collectCollectEmail(areas), CollectEmailVO.class);
            return ActResult.initialize(collectEmailVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 所有地区
     *
     * @version v1
     */
    @GetMapping("v1/listArea")
    public Result listArea (  ) throws ActException {
        try {
            List<String> areas = collectEmailAPI.areaList( );
            return ActResult.initialize(areas);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}