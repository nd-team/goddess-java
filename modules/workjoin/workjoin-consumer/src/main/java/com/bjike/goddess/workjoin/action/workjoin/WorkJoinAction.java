package com.bjike.goddess.workjoin.action.workjoin;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.workjoin.api.WorkJoinAPI;
import com.bjike.goddess.workjoin.bo.WorkJoinBO;
import com.bjike.goddess.workjoin.dto.WorkJoinDTO;
import com.bjike.goddess.workjoin.to.WorkJoinTO;
import com.bjike.goddess.workjoin.vo.WorkJoinVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 工作交接
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-29 05:14 ]
 * @Description: [ 工作交接 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("workjoin")
public class WorkJoinAction {
    @Autowired
    private WorkJoinAPI workJoinAPI;

    /**
     * 工作交接列表总条数
     *
     * @param workJoinDTO 工作交接dto
     * @des 获取所有工作交接总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(WorkJoinDTO workJoinDTO) throws ActException {
        try {
            Long count = workJoinAPI.countWorkJoin(workJoinDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个工作交接
     *
     * @param id
     * @return class WorkJoinVO
     * @des 获取一个工作交接
     * @version v1
     */
    @GetMapping("v1/work/{id}")
    public Result work(@PathVariable String id) throws ActException {
        try {
            WorkJoinBO workJoinBO = workJoinAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(workJoinBO, WorkJoinVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 工作交接列表
     *
     * @param workJoinDTO 工作交接dto
     * @return class WorkJoinVO
     * @des 获取所有工作交接
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(WorkJoinDTO workJoinDTO, HttpServletRequest request) throws ActException {
        try {
            List<WorkJoinVO> workJoinVOS = BeanTransform.copyProperties
                    (workJoinAPI.findListWorkJoin(workJoinDTO), WorkJoinVO.class, request);
            return ActResult.initialize(workJoinVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加工作交接
     *
     * @param workJoinTO 工作交接数据to
     * @return class WorkJoinVO
     * @des 添加工作交接
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) WorkJoinTO workJoinTO, BindingResult bindingResult) throws ActException {
        try {
            WorkJoinBO workJoinBO = workJoinAPI.insertWorkJoin(workJoinTO);
            return ActResult.initialize(workJoinBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑工作交接
     *
     * @param workJoinTO 工作交接数据to
     * @return class WorkJoinVO
     * @des 编辑工作交接
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) WorkJoinTO workJoinTO, BindingResult bindingResult) throws ActException {
        try {
            WorkJoinBO workJoinBO = workJoinAPI.editWorkJoin(workJoinTO);
            return ActResult.initialize(workJoinBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除工作交接
     *
     * @param id 用户id
     * @des 根据用户id删除工作交接记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            workJoinAPI.removeWorkJoin(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}