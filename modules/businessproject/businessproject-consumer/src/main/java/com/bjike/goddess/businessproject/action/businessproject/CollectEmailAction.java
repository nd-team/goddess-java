package com.bjike.goddess.businessproject.action.businessproject;

import com.bjike.goddess.businessproject.api.CollectEmailAPI;
import com.bjike.goddess.businessproject.bo.CollectEmailBO;
import com.bjike.goddess.businessproject.dto.CollectEmailDTO;
import com.bjike.goddess.businessproject.entity.CollectEmail;
import com.bjike.goddess.businessproject.service.CollectEmailSer;
import com.bjike.goddess.businessproject.to.CollectEmailTO;
import com.bjike.goddess.businessproject.vo.CollectEmailVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("collectemail")
public class CollectEmailAction {

    @Autowired
    private CollectEmailAPI collectEmailAPI;

    /**
     * 商务邮件汇总列表
     *
     * @param collectEmailDTO 商务邮件汇总信息dto
     * @des 获取所有商务邮件汇总信息
     * @return  class CollectEmailVO
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
     * 添加商务邮件汇总
     *
     * @param collectEmailTO 商务邮件汇总基本信息数据to
     * @des 添加商务邮件汇总
     * @return  class CollectEmailVO
     * @version v1
     */
    @PostMapping("v1/add")
    public Result addCollectEmail(@Validated CollectEmailTO collectEmailTO) throws ActException {
        try {
            CollectEmailBO collectEmailBO1 = collectEmailAPI.addCollectEmail(collectEmailTO);
            return ActResult.initialize(BeanTransform.copyProperties(collectEmailBO1,CollectEmailVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑商务邮件汇总
     *
     * @param collectEmailTO 商务邮件汇总基本信息数据bo
     * @des 添加商务邮件汇总
     * @return  class CollectEmailVO
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result editCollectEmail(@Validated CollectEmailTO collectEmailTO) throws ActException {
        try {
            CollectEmailBO collectEmailBO1 = collectEmailAPI.editCollectEmail(collectEmailTO);
            return ActResult.initialize(BeanTransform.copyProperties(collectEmailBO1,CollectEmailVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除商务邮件汇总信息记录
     * @version v1
     */
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
     * @des 根据id冻结商务邮件汇总记录
     * @version v1
     */
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
     * @des 根据id解冻商务邮件汇总记录
     * @version v1
     */
    @DeleteMapping("v1/thaw/{id}")
    public Result thaw (@PathVariable String id) throws ActException {
        try {
            collectEmailAPI.thawCollectEmail(id);
            return new ActResult("thaw success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 汇总签订合同与预订
     *
     * @param areas 地区
     * @des 商务邮件汇总签订合同与预订
     * @return  class CollectEmailVO
     * @version v1
     */
    @GetMapping("v1/CollectSign")
    public Result CollectSign ( @NotBlank String[] areas ) throws ActException {
        try {
            List<CollectEmailVO> collectEmailVOList = BeanTransform.copyProperties(
                    collectEmailAPI.collectCollectEmail(areas), CollectEmailVO.class, true);
            return ActResult.initialize(collectEmailVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总合同基本信息
     *
     * @param firstCompany 甲方
     * @des 汇总合同基本信息
     * @return  class CollectEmailVO
     * @version v1
     */
    @GetMapping("v1/CollectBaseInfo")
    public Result CollectBaseInfo ( @NotBlank String[] firstCompany ) throws ActException {
        try {
            List<CollectEmailVO> collectEmailVOList = BeanTransform.copyProperties(
                    collectEmailAPI.collectBaseInfoEmail(firstCompany), CollectEmailVO.class, true);
            return ActResult.initialize(collectEmailVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 汇总派工合同
     *
     * @param areas 地区
     * @des 商务邮件汇总汇总派工合同
     * @return  class CollectEmailVO
     * @version v1
     */
    @GetMapping("v1/CollectDispatch")
    public Result CollectDispatch ( @NotBlank String[] areas ) throws ActException {
        try {
            List<CollectEmailVO> collectEmailVOList = BeanTransform.copyProperties(
                    collectEmailAPI.collectDispatchEmail(areas), CollectEmailVO.class, true);
            return ActResult.initialize(collectEmailVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }



}