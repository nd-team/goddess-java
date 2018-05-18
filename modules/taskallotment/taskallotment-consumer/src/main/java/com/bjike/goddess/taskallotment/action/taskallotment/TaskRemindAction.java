package com.bjike.goddess.taskallotment.action.taskallotment;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.taskallotment.api.ProjectAPI;
import com.bjike.goddess.taskallotment.api.TaskRemindAPI;
import com.bjike.goddess.taskallotment.bo.ProjectBO;
import com.bjike.goddess.taskallotment.bo.TableBO;
import com.bjike.goddess.taskallotment.bo.TaskRemindBO;
import com.bjike.goddess.taskallotment.dto.TaskRemindDTO;
import com.bjike.goddess.taskallotment.to.TaskRemindTO;
import com.bjike.goddess.taskallotment.vo.ProjectVO;
import com.bjike.goddess.taskallotment.vo.TableVO;
import com.bjike.goddess.taskallotment.vo.TaskRemindVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 任务提醒
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-15 10:14 ]
 * @Description: [ 任务提醒 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("taskremind")
public class TaskRemindAction {
    @Autowired
    private TaskRemindAPI taskRemindAPI;
    @Autowired
    private ProjectAPI projectAPI;

    /**
     * 列表
     *
     * @param dto 任务提醒数据传输
     * @return class TaskRemindVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(TaskRemindDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<TaskRemindBO> list = taskRemindAPI.list(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, TaskRemindVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param to 任务提醒传输对象
     * @return class TaskRemindVO
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) TaskRemindTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            TaskRemindBO bo = taskRemindAPI.save(to);
            return ActResult.initialize(BeanTransform.copyProperties(bo, TaskRemindVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id查找
     *
     * @param id 任务提醒id
     * @return class TaskRemindVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/taskRemind/{id}")
    public Result TaskRemind(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            TaskRemindBO bo = taskRemindAPI.findByID(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, TaskRemindVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @param to 任务提醒传输对象
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) TaskRemindTO to, BindingResult result) throws ActException {
        try {
            taskRemindAPI.edit(to);
            return new ActResult("编辑成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id 任务提醒id
     * @throws ActException
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            taskRemindAPI.delete(id);
            return new ActResult("删除成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找总记录数
     *
     * @param dto 任务提醒数据传输
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(TaskRemindDTO dto) throws ActException {
        try {
            return ActResult.initialize(taskRemindAPI.count(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 定时发送邮件
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/send")
    public Result send() throws ActException {
        try {
            taskRemindAPI.mail();
            return new ActResult("发送成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有项目名称
     *
     * @return class ProjectVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/projects")
    public Result projects(HttpServletRequest request) throws ActException {
        try {
            List<ProjectBO> list = projectAPI.projects();
            return ActResult.initialize(BeanTransform.copyProperties(list, ProjectVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据项目获取项目表
     *
     * @param projectId 项目id
     * @return class TableVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/tables/{projectId}")
    public Result tables(@PathVariable String projectId, HttpServletRequest request) throws ActException {
        try {
            List<TableBO> list = projectAPI.tables(projectId);
            return ActResult.initialize(BeanTransform.copyProperties(list, TableVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据项目表获取任务名
     *
     * @param tableId 项目表id
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/task/names/{tableId}")
    public Result taskNames(@PathVariable String tableId) throws ActException {
        try {
            return ActResult.initialize(projectAPI.taskNames(tableId));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}