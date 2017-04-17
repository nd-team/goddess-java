package com.bjike.goddess.assistance.action.assistance;

import com.bjike.goddess.assistance.api.AssistanceEmpAPI;
import com.bjike.goddess.assistance.bo.AssistanceEmpBO;
import com.bjike.goddess.assistance.dto.AssistanceEmpDTO;
import com.bjike.goddess.assistance.to.AssistanceEmpTO;
import com.bjike.goddess.assistance.vo.AssistanceEmpVO;
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
 * 补助员工名单
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-14 10:12 ]
 * @Description: [ 补助员工名单 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("assistanceemp")
public class AssistanceEmpAction {



    @Autowired
    private AssistanceEmpAPI assistanceEmpAPI;

    /**
     *  补助员工名单总条数
     *
     * @param assistanceEmpDTO  补助员工名单信息dto
     * @des 获取所有补助员工名单信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(AssistanceEmpDTO assistanceEmpDTO) throws ActException {
        try {
            Long count = assistanceEmpAPI.countAssistanceEmp(assistanceEmpDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 补助员工名单列表
     *
     * @param assistanceEmpDTO 补助员工名单信息dto
     * @des 获取所有补助员工名单信息
     * @return  class AssistanceEmpVO
     * @version v1
     */
    @GetMapping("v1/listAssistanceEmp")
    public Result findListAssistanceEmp(AssistanceEmpDTO assistanceEmpDTO, BindingResult bindingResult) throws ActException {
        try {
            List<AssistanceEmpVO> assistanceEmpVOList = BeanTransform.copyProperties(
                    assistanceEmpAPI.listAssistanceEmp(assistanceEmpDTO), AssistanceEmpVO.class, true);
            return ActResult.initialize(assistanceEmpVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加补助员工名单
     *
     * @param assistanceEmpTO 补助员工名单基本信息数据to
     * @des 添加补助员工名单
     * @return  class AssistanceEmpVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addAssistanceEmp(@Validated AssistanceEmpTO assistanceEmpTO, BindingResult bindingResult) throws ActException {
        try {
            AssistanceEmpBO assistanceEmpBO1 = assistanceEmpAPI.addAssistanceEmp(assistanceEmpTO);
            return ActResult.initialize(BeanTransform.copyProperties(assistanceEmpBO1,AssistanceEmpVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑补助员工名单
     *
     * @param assistanceEmpTO 补助员工名单基本信息数据bo
     * @des 添加补助员工名单
     * @return  class AssistanceEmpVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editAssistanceEmp(@Validated AssistanceEmpTO assistanceEmpTO) throws ActException {
        try {
            AssistanceEmpBO assistanceEmpBO1 = assistanceEmpAPI.editAssistanceEmp(assistanceEmpTO);
            return ActResult.initialize(BeanTransform.copyProperties(assistanceEmpBO1,AssistanceEmpVO.class,true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除补助员工名单信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteAssistanceEmp(@PathVariable String id) throws ActException {
        try {
            assistanceEmpAPI.deleteAssistanceEmp(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败："+e.getMessage());
        }
    }




}