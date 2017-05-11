package com.bjike.goddess.workjoin.action.workjoin;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.workjoin.api.TaskJoinAPI;
import com.bjike.goddess.workjoin.bo.JoinInfoBO;
import com.bjike.goddess.workjoin.bo.TaskJoinBO;
import com.bjike.goddess.workjoin.dto.JoinInfoDTO;
import com.bjike.goddess.workjoin.dto.TaskJoinDTO;
import com.bjike.goddess.workjoin.to.JoinInfoTO;
import com.bjike.goddess.workjoin.to.TaskJoinTO;
import com.bjike.goddess.workjoin.vo.JoinInfoVO;
import com.bjike.goddess.workjoin.vo.TaskJoinVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 任务交接
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-29 04:55 ]
 * @Description: [ 任务交接 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("taskjoin")
public class TaskJoinAction {
    @Autowired
    private TaskJoinAPI taskJoinAPI;
    /**
     * 任务交接列表总条数
     *
     * @param taskJoinDTO 任务交接dto
     * @des 获取所有任务交接总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(TaskJoinDTO taskJoinDTO) throws ActException {
        try {
            Long count = taskJoinAPI.countTaskJoin(taskJoinDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个任务交接
     *
     * @param id
     * @return class TaskJoinVO
     * @des 获取一个任务交接
     * @version v1
     */
    @GetMapping("v1/task/{id}")
    public Result task(@PathVariable String id) throws ActException {
        try {
            TaskJoinBO taskJoinBO = taskJoinAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(taskJoinBO, TaskJoinVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 任务交接列表
     *
     * @param taskJoinDTO 任务交接dto
     * @return class TaskJoinVO
     * @des 获取所有任务交接
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(TaskJoinDTO taskJoinDTO, HttpServletRequest request) throws ActException {
        try {
            List<TaskJoinVO> taskJoinVOS = BeanTransform.copyProperties
                    (taskJoinAPI.findListTaskJoin(taskJoinDTO),TaskJoinVO.class, request);
            return ActResult.initialize(taskJoinVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加任务交接
     *
     * @param taskJoinTO 任务交接数据to
     * @return class TaskJoinVO
     * @des 添加任务交接
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) TaskJoinTO taskJoinTO, BindingResult bindingResult) throws ActException {
        try {
            TaskJoinBO joinInfoBO = taskJoinAPI.insertTaskJoin(taskJoinTO);
            return ActResult.initialize(joinInfoBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑任务交接
     *
     * @param taskJoinTO 任务交接数据to
     * @return class TaskJoinVO
     * @des 编辑任务交接
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) TaskJoinTO taskJoinTO, BindingResult bindingResult) throws ActException {
        try {
            TaskJoinBO taskJoinBO = taskJoinAPI.editTaskJoin(taskJoinTO);
            return ActResult.initialize(taskJoinBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除任务交接
     *
     * @param id 用户id
     * @des 根据用户id删除任务交接记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            taskJoinAPI.removeTaskJoin(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 上传
     *
     * @version v1
     */
    @PostMapping("v1/upload")
    public Result upload() throws ActException {
        try {
            taskJoinAPI.upload();
            return new ActResult("upload success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }

    /**
     * 下载
     *
     * @version v1
     */
    @PostMapping("v1/download")
    public Result download() throws ActException {
        try {
            taskJoinAPI.download();
            return new ActResult("download success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }

    }


}