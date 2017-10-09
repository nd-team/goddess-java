package com.bjike.goddess.taskallotment.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.contacts.api.InternalContactsAPI;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.enums.MsgType;
import com.bjike.goddess.message.enums.RangeType;
import com.bjike.goddess.message.enums.SendType;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.taskallotment.bo.TaskRemindBO;
import com.bjike.goddess.taskallotment.dto.ProjectDTO;
import com.bjike.goddess.taskallotment.dto.TableDTO;
import com.bjike.goddess.taskallotment.dto.TaskNodeDTO;
import com.bjike.goddess.taskallotment.dto.TaskRemindDTO;
import com.bjike.goddess.taskallotment.entity.Project;
import com.bjike.goddess.taskallotment.entity.Table;
import com.bjike.goddess.taskallotment.entity.TaskNode;
import com.bjike.goddess.taskallotment.entity.TaskRemind;
import com.bjike.goddess.taskallotment.to.TaskRemindTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 任务提醒业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-15 10:14 ]
 * @Description: [ 任务提醒业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "taskallotmentSerCache")
@Service
public class TaskRemindSerImpl extends ServiceImpl<TaskRemind, TaskRemindDTO> implements TaskRemindSer {
    @Autowired
    private TaskNodeSer taskNodeSer;
    @Autowired
    private ProjectSer projectSer;
    @Autowired
    private TableSer tableSer;
    @Autowired
    private MessageAPI messageAPI;
    @Autowired
    private InternalContactsAPI internalContactsAPI;

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public TaskRemindBO save(TaskRemindTO to) throws SerException {
        TaskRemind entity = BeanTransform.copyProperties(to, TaskRemind.class, true);
        if (null != entity.getSecondTime()) {
            Long mis = entity.getSecondTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
                    - entity.getFirstTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            if (mis <= 0) {
                throw new SerException("第二次提醒时间不能小于第一次提醒时间");
            }
        }
        if (null != entity.getThridTime()) {
            if (null == entity.getSecondTime()) {
                throw new SerException("必须先填写第二次提醒时间");
            }
            Long mis = entity.getThridTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
                    - entity.getSecondTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            if (mis <= 0) {
                throw new SerException("第三次提醒时间不能小于第二次提醒时间");
            }
        }
        super.save(entity);
        return BeanTransform.copyProperties(entity, TaskRemindBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(TaskRemindTO to) throws SerException {
        TaskRemind entity = super.findById(to.getId());
        LocalDateTime a = entity.getCreateTime();
        entity = BeanTransform.copyProperties(to, TaskRemind.class, true);
        entity.setCreateTime(a);
        if (null != entity.getSecondTime()) {
            Long mis = entity.getSecondTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
                    - entity.getFirstTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            if (mis <= 0) {
                throw new SerException("第二次提醒时间不能小于第一次提醒时间");
            }
        }
        if (null != entity.getThridTime()) {
            if (null == entity.getSecondTime()) {
                throw new SerException("必须先填写第二次提醒时间");
            }
            Long mis = entity.getThridTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
                    - entity.getSecondTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            if (mis <= 0) {
                throw new SerException("第三次提醒时间不能小于第二次提醒时间");
            }
        }
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public List<TaskRemindBO> list(TaskRemindDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<TaskRemind> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, TaskRemindBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void delete(String id) throws SerException {
        TaskRemind entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        super.remove(id);
    }


    @Override
    public Long count(TaskRemindDTO dto) throws SerException {
        return super.count(dto);
    }

    @Override
    public TaskRemindBO findByID(String id) throws SerException {
        TaskRemind entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        return BeanTransform.copyProperties(entity, TaskRemindBO.class);
    }

    @Override
    public void mail() throws SerException {
        List<TaskRemind> list = super.findAll();
        for (TaskRemind t : list) {
            String project = t.getProject();
            String table = t.getProjectTable();
            String taskName = t.getTaskName();
            ProjectDTO projectDTO = new ProjectDTO();
            projectDTO.getConditions().add(Restrict.eq("project", project));
            Project p = projectSer.findOne(projectDTO);
            if (null != p) {
                TableDTO tableDTO = new TableDTO();
                tableDTO.getConditions().add(Restrict.eq("project.id", p.getId()));
                tableDTO.getConditions().add(Restrict.eq("name", table));
                Table tb = tableSer.findOne(tableDTO);
                if (null != tb) {
                    TaskNodeDTO dto = new TaskNodeDTO();
                    dto.getConditions().add(Restrict.eq("taskName", taskName));
                    dto.getConditions().add(Restrict.eq("table.id", tb.getId()));
                    List<TaskNode> taskNodes = taskNodeSer.findByCis(dto);
                    List<String> executes = taskNodes.stream().filter(taskNode -> null == taskNode.getConfirm() && (null != taskNode.getExecute())).map(taskNode -> taskNode.getExecute()).collect(Collectors.toList());
                    if (!executes.isEmpty()) {
                        String[] strings = new String[executes.size()];
                        strings = executes.toArray(strings);
                        List<String> emails = internalContactsAPI.getEmails(strings);
                        if (null != emails && !emails.isEmpty()) {
                            String[] receivers = new String[emails.size()];
                            receivers = emails.toArray(receivers);
                            long mis = DateUtil.mis(LocalDateTime.now(), t.getFirstTime());
                            if (mis >= 0) {
                                send(receivers, t);
                            }
                            LocalDateTime secondTime = t.getSecondTime();
                            if (null != secondTime) {
                                long mis1 = DateUtil.mis(LocalDateTime.now(), secondTime);
                                if (mis1 >= 0) {
                                    send(receivers, t);
                                }
                            }
                            LocalDateTime thridTime = t.getThridTime();
                            if (null != thridTime) {
                                long mis2 = DateUtil.mis(LocalDateTime.now(), thridTime);
                                if (mis2 >= 0) {
                                    send(receivers, t);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void send(String[] receivers, TaskRemind t) throws SerException {
        MessageTO messageTO = new MessageTO();
        messageTO.setTitle("有待确认任务");
        messageTO.setContent("请及时上系统确认" + t.getTaskName() + "任务");
        messageTO.setMsgType(MsgType.SYS);
        messageTO.setSendType(SendType.EMAIL);
        messageTO.setRangeType(RangeType.SPECIFIED);
        //定时发送必须写
        messageTO.setSenderId("SYSTEM");
        messageTO.setSenderName("SYSTEM");
        messageTO.setReceivers(receivers);
        messageAPI.send(messageTO);
    }
}