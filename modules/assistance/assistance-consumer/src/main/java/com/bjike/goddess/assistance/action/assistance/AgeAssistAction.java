package com.bjike.goddess.assistance.action.assistance;

import com.bjike.goddess.assistance.api.AgeAssistAPI;
import com.bjike.goddess.assistance.bo.AgeAssistBO;
import com.bjike.goddess.assistance.dto.AgeAssistDTO;
import com.bjike.goddess.assistance.to.AgeAssistTO;
import com.bjike.goddess.assistance.vo.AgeAssistVO;
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

import javax.validation.Valid;
import java.util.List;

/**
 * 工龄补助
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-14 10:59 ]
 * @Description: [ 工龄补助 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("ageassist")
public class AgeAssistAction {

    @Autowired
    private AgeAssistAPI ageAssistAPI;

    /**
     *  工龄补助列表总条数
     *
     * @param ageAssistDTO  工龄补助信息dto
     * @des 获取所有工龄补助信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(AgeAssistDTO ageAssistDTO) throws ActException {
        try {
            Long count = ageAssistAPI.countAgeAssist(ageAssistDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     *  一个工龄补助
     *
     * @param id  工龄补助id
     * @des 一个工龄补助
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id ) throws ActException {
        try {
            AgeAssistVO ageAssistVO = BeanTransform.copyProperties(
                    ageAssistAPI.getOneById(id), AgeAssistVO.class);
            return ActResult.initialize(ageAssistVO );
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 工龄补助列表
     *
     * @param ageAssistDTO 工龄补助信息dto
     * @des 获取所有工龄补助信息
     * @return  class AgeAssistVO
     * @version v1
     */
    @GetMapping("v1/listAgeAssist")
    public Result findListAgeAssist(AgeAssistDTO ageAssistDTO, BindingResult bindingResult) throws ActException {
        try {
            List<AgeAssistVO> ageAssistVOList = BeanTransform.copyProperties(
                    ageAssistAPI.listAgeAssist(ageAssistDTO), AgeAssistVO.class, true);
            return ActResult.initialize(ageAssistVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加工龄补助
     *
     * @param ageAssistTO 工龄补助基本信息数据to
     * @des 添加工龄补助
     * @return  class AgeAssistVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addAgeAssist(@Validated AgeAssistTO ageAssistTO, BindingResult bindingResult) throws ActException {
        try {
            AgeAssistBO ageAssistBO1 = ageAssistAPI.addAgeAssist(ageAssistTO);
            return ActResult.initialize(BeanTransform.copyProperties(ageAssistBO1,AgeAssistVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑工龄补助
     *
     * @param ageAssistTO 工龄补助基本信息数据bo
     * @des 添加工龄补助
     * @return  class AgeAssistVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editAgeAssist(@Validated AgeAssistTO ageAssistTO) throws ActException {
        try {
            AgeAssistBO ageAssistBO1 = ageAssistAPI.editAgeAssist(ageAssistTO);
            return ActResult.initialize(BeanTransform.copyProperties(ageAssistBO1,AgeAssistVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除工龄补助信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteAgeAssist(@PathVariable String id) throws ActException {
        try {
            ageAssistAPI.deleteAgeAssist(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败："+e.getMessage());
        }
    }



    
}