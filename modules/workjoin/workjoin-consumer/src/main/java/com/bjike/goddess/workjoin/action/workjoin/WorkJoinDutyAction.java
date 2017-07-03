package com.bjike.goddess.workjoin.action.workjoin;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.workjoin.api.WorkJoinDutyAPI;
import com.bjike.goddess.workjoin.bo.WorkJoinBO;
import com.bjike.goddess.workjoin.bo.WorkJoinDutyBO;
import com.bjike.goddess.workjoin.dto.WorkJoinDTO;
import com.bjike.goddess.workjoin.dto.WorkJoinDutyDTO;
import com.bjike.goddess.workjoin.to.WorkJoinDutyTO;
import com.bjike.goddess.workjoin.to.WorkJoinTO;
import com.bjike.goddess.workjoin.vo.WorkJoinDutyVO;
import com.bjike.goddess.workjoin.vo.WorkJoinVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 工作交接责任义务
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-29 05:20 ]
 * @Description: [ 工作交接责任义务 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("workjoinduty")
public class WorkJoinDutyAction {
    @Autowired
    private WorkJoinDutyAPI workJoinDutyAPI;
    /**
     * 工作交接责任义务列表总条数
     *
     * @param workJoinDutyDTO 工作交接责任义务dto
     * @des 获取所有工作交接责任义务总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(WorkJoinDutyDTO workJoinDutyDTO) throws ActException {
        try {
            Long count = workJoinDutyAPI.countWorkJoinDuty(workJoinDutyDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个工作交接责任义务
     *
     * @param id
     * @return class WorkJoinDutyVO
     * @des 获取一个工作交接责任义务
     * @version v1
     */
    @GetMapping("v1/duty/{id}")
    public Result duty(@PathVariable String id) throws ActException {
        try {
            WorkJoinDutyBO workJoinDutyBO = workJoinDutyAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(workJoinDutyBO, WorkJoinDutyVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 工作交接责任义务列表
     *
     * @param workJoinDutyDTO 工作交接责任义务dto
     * @return class WorkJoinDutyVO
     * @des 获取所有工作交接责任义务
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(WorkJoinDutyDTO workJoinDutyDTO, HttpServletRequest request) throws ActException {
        try {
            List<WorkJoinDutyVO> workJoinDutyVOS = BeanTransform.copyProperties
                    (workJoinDutyAPI.findListWorkJoinDuty(workJoinDutyDTO),WorkJoinDutyVO.class,request);
            return ActResult.initialize(workJoinDutyVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加工作交接责任义务
     *
     * @param workJoinDutyTO 工作交接责任义务数据to
     * @return class WorkJoinDutyVO
     * @des 添加工作交接责任义务
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) WorkJoinDutyTO workJoinDutyTO, BindingResult bindingResult) throws ActException {
        try {
            WorkJoinDutyBO workJoinDutyBO = workJoinDutyAPI.insertWorkJoinDuty(workJoinDutyTO);
            return ActResult.initialize(workJoinDutyBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑工作交接责任义务
     *
     * @param workJoinDutyTO 工作交接责任义务数据to
     * @return class WorkJoinDutyVO
     * @des 编辑工作交接责任义务
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) WorkJoinDutyTO workJoinDutyTO, BindingResult bindingResult) throws ActException {
        try {
            WorkJoinDutyBO workJoinDutyBO = workJoinDutyAPI.editWorkJoinDuty(workJoinDutyTO);
            return ActResult.initialize(workJoinDutyBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除工作交接责任义务
     *
     * @param id 用户id
     * @des 根据用户id删除工作交接责任义务记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            workJoinDutyAPI.removeWorkJoinDuty(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}