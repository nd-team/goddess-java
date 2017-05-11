package com.bjike.goddess.workjoin.action.workjoin;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.workjoin.api.WorkJoinTimeSpecificationAPI;
import com.bjike.goddess.workjoin.bo.WorkJoinBO;
import com.bjike.goddess.workjoin.bo.WorkJoinTimeSpecificationBO;
import com.bjike.goddess.workjoin.dto.WorkJoinDTO;
import com.bjike.goddess.workjoin.dto.WorkJoinTimeSpecificationDTO;
import com.bjike.goddess.workjoin.to.WorkJoinTO;
import com.bjike.goddess.workjoin.to.WorkJoinTimeSpecificationTO;
import com.bjike.goddess.workjoin.vo.WorkJoinTimeSpecificationVO;
import com.bjike.goddess.workjoin.vo.WorkJoinVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 工作交接时间规范
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-29 05:18 ]
 * @Description: [ 工作交接时间规范 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("workjointimespecification")
public class WorkJoinTimeSpecificationAction {
    @Autowired
    private WorkJoinTimeSpecificationAPI workJoinTimeSpecificationAPI;
    /**
     * 工作交接时间规范列表总条数
     *
     * @param workJoinTimeSpecificationDTO 工作交接时间规范dto
     * @des 获取所有工作交接时间规范总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(WorkJoinTimeSpecificationDTO workJoinTimeSpecificationDTO) throws ActException {
        try {
            Long count = workJoinTimeSpecificationAPI.countWorkJoinTimeSpecification(workJoinTimeSpecificationDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个工作交接时间规范
     *
     * @param id
     * @return class WorkJoinTimeSpecificationVO
     * @des 获取一个工作交接时间规范
     * @version v1
     */
    @GetMapping("v1/time/{id}")
    public Result time(@PathVariable String id) throws ActException {
        try {
            WorkJoinTimeSpecificationBO workJoinTimeSpecificationBO = workJoinTimeSpecificationAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(workJoinTimeSpecificationBO, WorkJoinTimeSpecificationVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 工作交接时间规范列表
     *
     * @param workJoinTimeSpecificationDTO 工作交接时间规范dto
     * @return class WorkJoinTimeSpecificationVO
     * @des 获取所有工作交接时间规范
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(WorkJoinTimeSpecificationDTO workJoinTimeSpecificationDTO, HttpServletRequest request) throws ActException {
        try {
            List<WorkJoinTimeSpecificationVO> workJoinTimeSpecificationVOS = BeanTransform.copyProperties
                    (workJoinTimeSpecificationAPI.findListWorkJoinTimeSpecification(workJoinTimeSpecificationDTO),WorkJoinTimeSpecificationVO.class,request);
            return ActResult.initialize(workJoinTimeSpecificationVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加工作交接时间规范
     *
     * @param workJoinTimeSpecificationTO 工作交接时间规范数据to
     * @return class WorkJoinTimeSpecificationVO
     * @des 添加工作交接时间规范
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) WorkJoinTimeSpecificationTO workJoinTimeSpecificationTO, BindingResult bindingResult) throws ActException {
        try {
            WorkJoinTimeSpecificationBO workJoinTimeSpecificationBO = workJoinTimeSpecificationAPI.insertWorkJoinTimeSpecification(workJoinTimeSpecificationTO);
            return ActResult.initialize(workJoinTimeSpecificationBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑工作交接时间规范
     *
     * @param workJoinTimeSpecificationTO 工作交接时间规范数据to
     * @return class WorkJoinTimeSpecificationVO
     * @des 编辑工作交接时间规范
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) WorkJoinTimeSpecificationTO workJoinTimeSpecificationTO, BindingResult bindingResult) throws ActException {
        try {
            WorkJoinTimeSpecificationBO workJoinTimeSpecificationBO = workJoinTimeSpecificationAPI.editWorkJoinTimeSpecification(workJoinTimeSpecificationTO);
            return ActResult.initialize(workJoinTimeSpecificationBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除工作交接时间规范
     *
     * @param id 用户id
     * @des 根据用户id删除工作交接时间规范记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            workJoinTimeSpecificationAPI.removeWorkJoinTimeSpecification(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}