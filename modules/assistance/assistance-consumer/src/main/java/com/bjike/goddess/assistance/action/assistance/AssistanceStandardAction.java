package com.bjike.goddess.assistance.action.assistance;

import com.bjike.goddess.assistance.api.AssistanceStandardAPI;
import com.bjike.goddess.assistance.bo.AssistanceStandardBO;
import com.bjike.goddess.assistance.dto.AssistanceStandardDTO;
import com.bjike.goddess.assistance.to.AssistanceStandardTO;
import com.bjike.goddess.assistance.vo.AssistanceStandardVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.auth.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 补助标准
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-13 09:29 ]
 * @Description: [ 补助标准 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("assistancestandard")
public class AssistanceStandardAction {


    @Autowired
    private AssistanceStandardAPI assistanceStandardAPI;

    /**
     *  补助标准列表总条数
     *
     * @param assistanceStandardDTO  补助标准信息dto
     * @des 获取所有补助标准信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(AssistanceStandardDTO assistanceStandardDTO) throws ActException {
        try {
            Long count = assistanceStandardAPI.countAssistanceStandard(assistanceStandardDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 补助标准列表
     *
     * @param assistanceStandardDTO 补助标准信息dto
     * @des 获取所有补助标准信息
     * @return  class AssistanceStandardVO
     * @version v1
     */
    @GetMapping("v1/listAssistanceStandard")
    public Result findListAssistanceStandard(AssistanceStandardDTO assistanceStandardDTO, BindingResult bindingResult) throws ActException {
        try {
            List<AssistanceStandardVO> assistanceStandardVOList = BeanTransform.copyProperties(
                    assistanceStandardAPI.listAssistanceStandard(assistanceStandardDTO), AssistanceStandardVO.class, true);
            return ActResult.initialize(assistanceStandardVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加补助标准
     *
     * @param assistanceStandardTO 补助标准基本信息数据to
     * @des 添加补助标准
     * @return  class AssistanceStandardVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addAssistanceStandard(@Validated AssistanceStandardTO assistanceStandardTO, BindingResult bindingResult) throws ActException {
        try {
            AssistanceStandardBO assistanceStandardBO1 = assistanceStandardAPI.addAssistanceStandard(assistanceStandardTO);
            return ActResult.initialize(BeanTransform.copyProperties(assistanceStandardBO1,AssistanceStandardVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑补助标准
     *
     * @param assistanceStandardTO 补助标准基本信息数据bo
     * @des 添加补助标准
     * @return  class AssistanceStandardVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editAssistanceStandard(@Validated AssistanceStandardTO assistanceStandardTO) throws ActException {
        try {
            AssistanceStandardBO assistanceStandardBO1 = assistanceStandardAPI.editAssistanceStandard(assistanceStandardTO);
            return ActResult.initialize(BeanTransform.copyProperties(assistanceStandardBO1,AssistanceStandardVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除补助标准信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteAssistanceStandard(@PathVariable String id) throws ActException {
        try {
            assistanceStandardAPI.deleteAssistanceStandard(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败："+e.getMessage());
        }
    }


    /**
     * 获取工龄补助标准
     *
     * @des 获取工龄补助标准
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/getAgeStands")
    public Result getAgeStands( ) throws ActException {
        try {
            List<AssistanceStandardVO> list = BeanTransform.copyProperties(
                    assistanceStandardAPI.getAgeStands( ) ,AssistanceStandardVO.class);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败："+e.getMessage());
        }
    }


    
}