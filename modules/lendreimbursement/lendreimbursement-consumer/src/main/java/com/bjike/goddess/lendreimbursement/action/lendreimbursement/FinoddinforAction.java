package com.bjike.goddess.lendreimbursement.action.lendreimbursement;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.lendreimbursement.api.FinoddinforAPI;
import com.bjike.goddess.lendreimbursement.bo.FinoddinforBO;
import com.bjike.goddess.lendreimbursement.dto.FinoddinforDTO;
import com.bjike.goddess.lendreimbursement.to.FinoddinforTO;
import com.bjike.goddess.lendreimbursement.vo.FinoddinforVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 报销单号管理
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-12 09:19 ]
 * @Description: [ 报销单号管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("finoddinfor")
public class FinoddinforAction {


    @Autowired
    private FinoddinforAPI finoddinforAPI;

    /**
     *  报销单号列表总条数
     *
     * @param finoddinforDTO  报销单号信息dto
     * @des 获取所有报销单号信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(FinoddinforDTO finoddinforDTO) throws ActException {
        try {
            Long count = finoddinforAPI.countFinoddinfor(finoddinforDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 报销单号列表
     *
     * @param finoddinforDTO 报销单号信息dto
     * @des 获取所有报销单号信息
     * @return  class FinoddinforVO
     * @version v1
     */
    @GetMapping("v1/listFinoddinfor")
    public Result findListFinoddinfor(FinoddinforDTO finoddinforDTO, BindingResult bindingResult) throws ActException {
        try {
            List<FinoddinforVO> finoddinforVOList = BeanTransform.copyProperties(
                    finoddinforAPI.listFinoddinfor(finoddinforDTO), FinoddinforVO.class, true);
            return ActResult.initialize(finoddinforVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加报销单号
     *
     * @param finoddinforTO 报销单号基本信息数据to
     * @des 添加报销单号
     * @return  class FinoddinforVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addFinoddinfor(@Validated FinoddinforTO finoddinforTO, BindingResult bindingResult) throws ActException {
        try {
            FinoddinforBO finoddinforBO1 = finoddinforAPI.addFinoddinfor(finoddinforTO);
            return ActResult.initialize(BeanTransform.copyProperties(finoddinforBO1,FinoddinforVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }



    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除报销单号信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteFinoddinfor(@PathVariable String id) throws ActException {
        try {
            finoddinforAPI.deleteFinoddinfor(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败："+e.getMessage());
        }
    }




    /**
     * 冻结
     *
     * @param id id
     * @des 根据id冻结报销单号记录
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/congeal/{id}")
    public Result congeal(@PathVariable String id) throws ActException {
        try {
            finoddinforAPI.congealFinoddinfor(id);
            return new ActResult("congeal success!");
        } catch (SerException e) {
            throw new ActException("冻结失败："+e.getMessage());
        }
    }


    /**
     * 解冻
     *
     * @param id id
     * @des 根据id解冻报销单号记录
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/thaw/{id}")
    public Result thaw (@PathVariable String id) throws ActException {
        try {
            finoddinforAPI.thawFinoddinfor(id);
            return new ActResult("thaw success!");
        } catch (SerException e) {
            throw new ActException("冻结失败："+e.getMessage());
        }
    }

    
}