package com.bjike.goddess.customer.action.customer;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.customer.api.CusEmailAPI;
import com.bjike.goddess.customer.bo.CusEmailBO;
import com.bjike.goddess.customer.dto.CusEmailDTO;
import com.bjike.goddess.customer.to.CusEmailTO;
import com.bjike.goddess.customer.vo.CusEmailVO;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 客户邮件发送定制
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-16T19:08:18.885 ]
 * @Description: [ 客户邮件发送定制 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("customer/cusemail")
public class CusEmailAction {

    @Autowired
    private CusEmailAPI cusEmailAPI;

    /**
     * 客户邮件汇总列表总条数
     *
     * @param cusEmailDTO 客户邮件汇总dto
     * @des 获取所有客户邮件汇总总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(CusEmailDTO cusEmailDTO) throws ActException {
        try {
            Long count = cusEmailAPI.countCusEmail(cusEmailDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 客户邮件汇总列表
     *
     * @param cusEmailDTO 客户邮件汇总信息dto
     * @des 获取所有客户邮件汇总信息
     * @return  class CusEmailVO
     * @version v1
     */
    @GetMapping("v1/listCusEmail")
    public Result findListCusEmail(CusEmailDTO cusEmailDTO) throws ActException {
        try {
            List<CusEmailVO> cusEmailVOList = BeanTransform.copyProperties(
                    cusEmailAPI.listCusEmail(cusEmailDTO), CusEmailVO.class, true);
            return ActResult.initialize(cusEmailVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加客户邮件汇总
     *
     * @param cusEmailTO 客户邮件汇总基本信息数据to
     * @des 添加客户邮件汇总,行业不能为空发送间隔汇总间隔等都不能为空
     * @return  class CusEmailVO
     * @version v1
     */
    @PostMapping("v1/add")
    public Result addCusEmail( @Validated CusEmailTO cusEmailTO , BindingResult bindingResult) throws ActException {
        try {
            CusEmailBO cusEmailBO1 = cusEmailAPI.addCusEmail(cusEmailTO);
            return ActResult.initialize(BeanTransform.copyProperties(cusEmailBO1,CusEmailVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑客户邮件汇总
     *
     * @param cusEmailTO 客户邮件汇总基本信息数据bo
     * @des 添加客户邮件汇总,行业不能为空发送间隔汇总间隔等都不能为空
     * @return  class CusEmailVO
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result editCusEmail(@Validated CusEmailTO cusEmailTO , BindingResult bindingResult) throws ActException {
        try {
            CusEmailBO cusEmailBO1 = cusEmailAPI.editCusEmail(cusEmailTO);
            return ActResult.initialize(BeanTransform.copyProperties(cusEmailBO1,CusEmailVO.class,true));
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
    @DeleteMapping("v1/delete/{id}")
    public Result deleteCusEmail(@PathVariable String id) throws ActException {
        try {
            cusEmailAPI.deleteCusEmail(id);
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
    @PutMapping("v1/congeal/{id}")
    public Result congeal(@PathVariable String id) throws ActException {
        try {
            cusEmailAPI.congealCusEmail(id);
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
    @PutMapping("v1/thaw/{id}")
    public Result thaw (@PathVariable String id) throws ActException {
        try {
            cusEmailAPI.thawCusEmail(id);
            return new ActResult("thaw success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 汇总
     *
     * @param works 行业
     * @des 根据行业汇总
     * @return  class CusEmailVO
     * @version v1
     */
    @GetMapping("v1/Collect")
    public Result CollectSign (@RequestParam String[] works ) throws ActException {
        try {
            List<CusEmailVO> collectEmailVOList = BeanTransform.copyProperties(
                    cusEmailAPI.collectCusEmail(works), CusEmailVO.class, true);
            return ActResult.initialize(collectEmailVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}