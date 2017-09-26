package com.bjike.goddess.task.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.task.dto.TaskDTO;
import com.bjike.goddess.task.entity.Project;
import com.bjike.goddess.task.entity.Row;
import com.bjike.goddess.task.entity.Table;
import com.bjike.goddess.task.entity.Task;
import com.bjike.goddess.task.enums.ExecStatus;
import com.bjike.goddess.task.to.TaskTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.dto.UserDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 任务业务实现
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class TaskSerImpl extends ServiceImpl<Task, TaskDTO> implements TaskSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private RowSer rowSer;
    @Autowired
    private TableSer tableSer;
    @Autowired
    private ProjectSer projectSer;

    @Override
    public void issued(TaskTO to) throws SerException {
        UserBO user = userAPI.currentUser();
        Task tmp_task = BeanTransform.copyProperties(to, Task.class, true);
        Integer spiltDay = to.getSpiltDay();
        Row row = rowSer.findById(to.getRowId());
        String content = rowSer.findContent(row.getId());
        if (null != row && StringUtils.isNotBlank(content)) {
            Map<String, String> msgMap = new HashMap<>();
            List<Task> tasks = new ArrayList<>();
            List<UserBO> userBOS = initExecUser(to.getExecUsers());
            Integer minute = initMinute(to, userBOS);
            LocalDateTime start = tmp_task.getStartTime();
            for (UserBO u : userBOS) {
                if (null != spiltDay) {
                    for (int i = 0; i < spiltDay; i++) {
                        Task task = new Task();
                        BeanUtils.copyProperties(tmp_task, task);
                        task.setExecUser(u.getId());
                        task.setIssuedUser(user.getId());
                        task.setRow(row);
                        task.setMinute(minute);
                        task.setStartTime(start.toLocalDate().plusDays(i).atTime(8, 30, 00));
                        task.setEndTime(start.toLocalDate().plusDays(i).atTime(18, 00, 00));
                        tasks.add(task);
                        if (null != u.getEmail()) {
                            msgMap.put(u.getEmail(), content);
                        }
                    }
                } else {
                    Task task = new Task();
                    BeanUtils.copyProperties(tmp_task, task);
                    task.setExecUser(u.getId());
                    task.setIssuedUser(user.getId());
                    task.setRow(row);
                    task.setMinute(minute);
                    tasks.add(task);
                }
            }
//            this.sendMail(msgMap);
            updateExecStatus(row);       //更新项目,表,任务行执行状态
            super.save(tasks);
        } else {
            throw new SerException("系统找不到任务行,或者任务内容未填写");
        }
    }

    /**
     * 获取任务执行人,且确保任务执行人有效
     *
     * @param execUsers
     * @return
     * @throws SerException
     */
    private List<UserBO> initExecUser(String[] execUsers) throws SerException {
        UserDTO dto = new UserDTO();
        dto.getConditions().add(Restrict.in("id", execUsers));
        List<UserBO> userBOS = userAPI.findByCis(dto);
        if (null != userBOS && userBOS.size() > 0) {
            return userBOS;
        }
        throw new SerException("无效执行人,请检查执行人是否正确");
    }

    /**
     * 获取任务执行时间
     *
     * @param to
     * @param userBOS
     * @return
     * @throws SerException
     */
    private Integer initMinute(TaskTO to, List<UserBO> userBOS) throws SerException {
        Integer spiltDay = to.getSpiltDay();
        Integer minute = to.getMinute();
        minute = null != spiltDay ? minute / spiltDay : minute;
        minute /= userBOS.size();
        return minute;
    }

    private void updateExecStatus(Row row) throws SerException {
        ExecStatus status = ExecStatus.EXECUTING;
        if (!row.getExecStatus().equals(status)) {
            row.setExecStatus(status);
            rowSer.update(row);
        }
        Table table = tableSer.findByRowId(row.getId());
        if (!table.getExecStatus().equals(status)) {
            table.setExecStatus(status);
            tableSer.update(table);
        }
        Project project = projectSer.findByTableId(table.getId());
        if (!project.getExecStatus().equals(status)) {
            project.setExecStatus(ExecStatus.EXECUTING);
            projectSer.update(project);
        }
    }

}
