package com.bjike.goddess.taskallotment.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.taskallotment.bo.*;
import com.bjike.goddess.taskallotment.bo.DayReport.DayBO;
import com.bjike.goddess.taskallotment.bo.DayReport.DayReportCountBO;
import com.bjike.goddess.taskallotment.dto.ProjectDTO;
import com.bjike.goddess.taskallotment.dto.TaskNodeDTO;
import com.bjike.goddess.taskallotment.service.TaskNodeSer;
import com.bjike.goddess.taskallotment.to.GuidePermissionTO;
import com.bjike.goddess.taskallotment.to.TaskNodeTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 任务节点业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-14 02:10 ]
 * @Description: [ 任务节点业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("taskNodeApiImpl")
public class TaskNodeApiImpl implements TaskNodeAPI {
    @Autowired
    private TaskNodeSer taskNodeSer;

    @Override
    public List<ProjectBO> list(ProjectDTO dto) throws SerException {
        return taskNodeSer.list(dto);
    }

    @Override
    public void save(TaskNodeTO to) throws SerException {
        taskNodeSer.save(to);
    }

    @Override
    public void edit(TaskNodeTO to) throws SerException {
        taskNodeSer.edit(to);
    }

    @Override
    public void delete(String id) throws SerException {
        taskNodeSer.delete(id);
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return taskNodeSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return taskNodeSer.guidePermission(guidePermissionTO);
    }

    @Override
    public TaskNodeBO findByID(String id) throws SerException {
        return taskNodeSer.findByID(id);
    }

    @Override
    public Long count(TaskNodeDTO dto) throws SerException {
        return taskNodeSer.count(dto);
    }

    @Override
    public void initiateTask(TaskNodeTO to) throws SerException {
        taskNodeSer.initiateTask(to);
    }

    @Override
    public void addTask(TaskNodeTO to) throws SerException {
        taskNodeSer.addTask(to);
    }


    @Override
    public void reback(String id) throws SerException {
        taskNodeSer.reback(id);
    }

    @Override
    public void finish(TaskNodeTO to) throws SerException {
        taskNodeSer.finish(to);
    }

    @Override
    public void unFinish(TaskNodeTO to) throws SerException {
        taskNodeSer.unFinish(to);
    }

    @Override
    public void allotment(TaskNodeTO to) throws SerException {
        taskNodeSer.allotment(to);
    }

    @Override
    public void report(TaskNodeTO to) throws SerException {
        taskNodeSer.report(to);
    }

    @Override
    public void initiateAgain(TaskNodeTO to) throws SerException {
        taskNodeSer.initiateAgain(to);
    }

    @Override
    public void write(TaskNodeTO to) throws SerException {
        taskNodeSer.write(to);
    }

    @Override
    public List<PersonCountBO> personCount(TaskNodeDTO dto) throws SerException {
        return taskNodeSer.personCount(dto);
    }

    @Override
    public List<TimeCountBO> timeCount(TaskNodeDTO dto) throws SerException {
        return taskNodeSer.timeCount(dto);
    }

    @Override
    public List<ConfirmCountBO> confirmCount(TaskNodeDTO dto) throws SerException {
        return taskNodeSer.confirmCount(dto);
    }

    @Override
    public List<FinishCaseBO> finishCount(TaskNodeDTO dto) throws SerException {
        return taskNodeSer.finishCount(dto);
    }

    @Override
    public Boolean checkTime(TaskNodeTO to) throws SerException {
        return taskNodeSer.checkTime(to);
    }

    @Override
    public List<TaskNodeBO> myInitiate(TaskNodeDTO dto) throws SerException {
        return taskNodeSer.myInitiate(dto);
    }

    @Override
    public Long myInitiateNum(TaskNodeDTO dto) throws SerException {
        return taskNodeSer.myInitiateNum(dto);
    }

    @Override
    public void pass(TaskNodeTO to) throws SerException {
        taskNodeSer.pass(to);
    }

    @Override
    public void notPass(TaskNodeTO to) throws SerException {
        taskNodeSer.notPass(to);
    }

    @Override
    public List<TaskNodeBO> myCharge(TaskNodeDTO dto) throws SerException {
        return taskNodeSer.myCharge(dto);
    }

    @Override
    public Long myChargeNum(TaskNodeDTO dto) throws SerException {
        return taskNodeSer.myChargeNum(dto);
    }

    @Override
    public List<TaskNodeBO> myExecute(TaskNodeDTO dto) throws SerException {
        return taskNodeSer.myExecute(dto);
    }

    @Override
    public Long myExecuteNum(TaskNodeDTO dto) throws SerException {
        return taskNodeSer.myExecuteNum(dto);
    }

    @Override
    public void confirm(String id) throws SerException {
        taskNodeSer.confirm(id);
    }

    @Override
    public void unConfirm(TaskNodeTO to) throws SerException {
        taskNodeSer.unConfirm(to);
    }

    @Override
    public List<DayBO> dayReport(String time, String[] names) throws SerException {
        return taskNodeSer.dayReport(time, names);
    }

    @Override
    public DayReportCountBO dayCount(String startTime, String endTime, String[] departIds) throws SerException {
        return taskNodeSer.dayCount(startTime, endTime, departIds);
    }

    @Override
    public Double finishDay(String date,String name) throws SerException {
        return taskNodeSer.finishDay(date,name);
    }

    @Override
    public List<ObjectBO> taskSituation(String[] names, String date) throws SerException {
        return taskNodeSer.taskSituation(names, date);
    }
}