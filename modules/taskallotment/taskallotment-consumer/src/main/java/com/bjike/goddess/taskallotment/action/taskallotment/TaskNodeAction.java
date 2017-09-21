package com.bjike.goddess.taskallotment.action.taskallotment;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.taskallotment.api.TaskNodeAPI;
import com.bjike.goddess.taskallotment.bo.*;
import com.bjike.goddess.taskallotment.dto.TaskNodeDTO;
import com.bjike.goddess.taskallotment.to.TaskNodeTO;
import com.bjike.goddess.taskallotment.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 任务节点
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-14 02:10 ]
 * @Description: [ 任务节点 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("tasknode")
public class TaskNodeAction {
    @Autowired
    private TaskNodeAPI taskNodeAPI;

    /**
     * 列表
     *
     * @param dto 任务节点数据传输
     * @return class TaskNodeVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(TaskNodeDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<TaskNodeBO> list = taskNodeAPI.list(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, TaskNodeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param to 任务节点传输对象
     * @return class TaskNodeVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) TaskNodeTO to, BindingResult result) throws ActException {
        try {
            taskNodeAPI.save(to);
            return new ActResult("添加成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找
     *
     * @param id 任务节点id
     * @return class TaskNodeVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/taskNode/{id}")
    public Result TaskNode(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            TaskNodeBO bo = taskNodeAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, TaskNodeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 任务节点传输对象
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) TaskNodeTO to, BindingResult result) throws ActException {
        try {
            taskNodeAPI.edit(to);
            return new ActResult("编辑成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 发起任务
     *
     * @param to to
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/initiateTask")
    public Result initiateTask(@Validated(EDIT.class) TaskNodeTO to, BindingResult result) throws ActException {
        try {
            taskNodeAPI.initiateTask(to);
            return new ActResult("发起任务成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加小任务
     *
     * @param to to
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/addTask")
    public Result addTask(@Validated(EDIT.class) TaskNodeTO to, BindingResult result) throws ActException {
        try {
            taskNodeAPI.addTask(to);
            return new ActResult("添加小任务成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 任务节点id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            taskNodeAPI.delete(id);
            return new ActResult("删除成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找总记录数
     *
     * @param dto 任务节点数据传输
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(TaskNodeDTO dto) throws ActException {
        try {
            return ActResult.initialize(taskNodeAPI.count(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 检测某执行人某天的任务时长是否超过8小时
     *
     * @param to to
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/checkTime")
    public Result checkTime(TaskNodeTO to) throws ActException {
        try {
            return ActResult.initialize(taskNodeAPI.checkTime(to));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 我分发的任务
     *
     * @param dto dto
     * @return class TaskNodeVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/my/initiate")
    public Result myInitiate(TaskNodeDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<TaskNodeBO> list = taskNodeAPI.myInitiate(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, TaskNodeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 我分发的任务总条数
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/initiate/num")
    public Result myInitiateNum() throws ActException {
        try {
            return ActResult.initialize(taskNodeAPI.myInitiateNum());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 撤回任务
     *
     * @param id id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/reback/{id}")
    public Result reback(@PathVariable String id) throws ActException {
        try {
            taskNodeAPI.reback(id);
            return new ActResult("撤回任务成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 确认完成
     *
     * @param id id
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/finish/{id}")
    public Result finish(@PathVariable String id) throws ActException {
        try {
            taskNodeAPI.finish(id);
            return new ActResult("确认完成成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 确认未完成
     *
     * @param id id
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/unFinish/{id}")
    public Result unFinish(@PathVariable String id) throws ActException {
        try {
            taskNodeAPI.unFinish(id);
            return new ActResult("确认未完成成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 上报审核通过
     *
     * @param to to
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/pass")
    public Result pass(TaskNodeTO to) throws ActException {
        try {
            taskNodeAPI.pass(to);
            return new ActResult("上报审核通过成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 上报审核不通过
     *
     * @param to to
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/notPass")
    public Result notPass(TaskNodeTO to) throws ActException {
        try {
            taskNodeAPI.notPass(to);
            return new ActResult("上报审核不通过成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 我负责的任务
     *
     * @param dto dto
     * @return class TaskNodeVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/myCharge")
    public Result myCharge(TaskNodeDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<TaskNodeBO> list = taskNodeAPI.myCharge(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, TaskNodeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 我负责的任务总条数
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/charge/num")
    public Result myChargeNum() throws ActException {
        try {
            return ActResult.initialize(taskNodeAPI.myChargeNum());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分配我负责的任务
     *
     * @param to to
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/allotment")
    public Result allotment(TaskNodeTO to) throws ActException {
        try {
            taskNodeAPI.allotment(to);
            return new ActResult("分配我负责的任务成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 我执行的任务
     *
     * @param dto dto
     * @return class TaskNodeVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/myExecute")
    public Result myExecute(TaskNodeDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<TaskNodeBO> list = taskNodeAPI.myExecute(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, TaskNodeVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 我执行的任务总条数
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/execute/num")
    public Result myExecuteNum() throws ActException {
        try {
            return ActResult.initialize(taskNodeAPI.myExecuteNum());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 上报任务
     *
     * @param to to
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/report")
    public Result report(TaskNodeTO to) throws ActException {
        try {
            taskNodeAPI.report(to);
            return new ActResult("上报任务成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 再次分发我执行的任务
     *
     * @param to to
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/initiateAgain")
    public Result initiateAgain(TaskNodeTO to) throws ActException {
        try {
            taskNodeAPI.initiateAgain(to);
            return new ActResult("再次分发成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 填写任务完成情况
     *
     * @param to to
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/write")
    public Result write(TaskNodeTO to) throws ActException {
        try {
            taskNodeAPI.write(to);
            return new ActResult("填写成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 个人汇总
     *
     * @param dto dto
     * @return class PersonCountVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/person/count")
    public Result personCount(TaskNodeDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<PersonCountBO> list = taskNodeAPI.personCount(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, PersonCountVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 人员标准工时汇总
     *
     * @param dto dto
     * @return class TimeCountVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/time/count")
    public Result timeCount(TaskNodeDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<TimeCountBO> list = taskNodeAPI.timeCount(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, TimeCountVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分配及确认汇总
     *
     * @param dto dto
     * @return class ConfirmCountVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/confirm/count")
    public Result confirmCount(TaskNodeDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<ConfirmCountBO> list = taskNodeAPI.confirmCount(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, ConfirmCountVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 完成情况汇总
     *
     * @param dto dto
     * @return class FinishCaseVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/finish/count")
    public Result finishCount(TaskNodeDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<FinishCaseBO> list = taskNodeAPI.finishCount(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, FinishCaseVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}