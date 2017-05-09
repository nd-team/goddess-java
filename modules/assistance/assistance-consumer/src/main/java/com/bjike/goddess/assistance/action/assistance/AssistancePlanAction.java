package com.bjike.goddess.assistance.action.assistance;

import com.bjike.goddess.assistance.api.AssistancePlanAPI;
import com.bjike.goddess.assistance.bo.AssistancePlanBO;
import com.bjike.goddess.assistance.dto.AssistancePlanDTO;
import com.bjike.goddess.assistance.to.AssistancePlanTO;
import com.bjike.goddess.assistance.vo.AssistancePlanVO;
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
 * 补助方案
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-13 09:27 ]
 * @Description: [ 补助方案 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("assistanceplan")
public class AssistancePlanAction {

    @Autowired
    private AssistancePlanAPI assistancePlanAPI;

    /**
     *  补助方案列表总条数
     *
     * @param assistancePlanDTO  补助方案信息dto
     * @des 获取所有补助方案信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(AssistancePlanDTO assistancePlanDTO) throws ActException {
        try {
            Long count = assistancePlanAPI.countAssistancePlan(assistancePlanDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 补助方案列表
     *
     * @param assistancePlanDTO 补助方案信息dto
     * @des 获取所有补助方案信息
     * @return  class AssistancePlanVO
     * @version v1
     */
    @GetMapping("v1/listAssistancePlan")
    public Result findListAssistancePlan(AssistancePlanDTO assistancePlanDTO, BindingResult bindingResult) throws ActException {
        try {
            List<AssistancePlanVO> assistancePlanVOList = BeanTransform.copyProperties(
                    assistancePlanAPI.listAssistancePlan(assistancePlanDTO), AssistancePlanVO.class, true);
            return ActResult.initialize(assistancePlanVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加补助方案
     *
     * @param assistancePlanTO 补助方案基本信息数据to
     * @des 添加补助方案
     * @return  class AssistancePlanVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addAssistancePlan(@Validated AssistancePlanTO assistancePlanTO, BindingResult bindingResult) throws ActException {
        try {
            AssistancePlanBO assistancePlanBO1 = assistancePlanAPI.addAssistancePlan(assistancePlanTO);
            return ActResult.initialize(BeanTransform.copyProperties(assistancePlanBO1,AssistancePlanVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑补助方案
     *
     * @param assistancePlanTO 补助方案基本信息数据bo
     * @des 添加补助方案
     * @return  class AssistancePlanVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editAssistancePlan(@Validated AssistancePlanTO assistancePlanTO) throws ActException {
        try {
            AssistancePlanBO assistancePlanBO1 = assistancePlanAPI.editAssistancePlan(assistancePlanTO);
            return ActResult.initialize(BeanTransform.copyProperties(assistancePlanBO1,AssistancePlanVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除补助方案信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteAssistancePlan(@PathVariable String id) throws ActException {
        try {
            assistancePlanAPI.deleteAssistancePlan(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败："+e.getMessage());
        }
    }


    /**
     * 审核补助方案
     *
     * @param assistancePlanTO 补助方案基本信息数据bo
     * @des 添加补助方案
     * @return  class AssistancePlanVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/audit")
    public Result auditAssistancePlan(@Validated AssistancePlanTO assistancePlanTO) throws ActException {
        try {
            AssistancePlanBO assistancePlanBO1 = assistancePlanAPI.auditAssistancePlan(assistancePlanTO);
            return ActResult.initialize(BeanTransform.copyProperties(assistancePlanBO1,AssistancePlanVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有补助方案序号
     *
     * @des 获取所有补助方案序号
     * @return  class AssistancePlanVO
     * @version v1
     */
    @GetMapping("v1/listPlanNum")
    public Result listPlanNum( ) throws ActException {
        try {
            List<AssistancePlanVO> assistancePlanVOList = BeanTransform.copyProperties(
                    assistancePlanAPI.listPlanNum( ), AssistancePlanVO.class, true);
            return ActResult.initialize(assistancePlanVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 获取补助方案根据
     *
     * @param assistancePlanDTO 补助方案信息dto
     * @des 获取所有补助方案序号
     * @return  class AssistancePlanVO
     * @version v1
     */
    @GetMapping("v1/getPlanByNum")
    public Result getPlanByNum(@Validated(AssistancePlanDTO.TestQueryByNum.class) AssistancePlanDTO assistancePlanDTO, BindingResult bindingResult) throws ActException {
        try {
            List<AssistancePlanVO> assistancePlanVOList = BeanTransform.copyProperties(
                    assistancePlanAPI.getPlanByNum(assistancePlanDTO), AssistancePlanVO.class, true);
            return ActResult.initialize(assistancePlanVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}