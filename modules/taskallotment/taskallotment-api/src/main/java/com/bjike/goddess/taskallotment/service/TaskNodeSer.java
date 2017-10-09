package com.bjike.goddess.taskallotment.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.taskallotment.bo.*;
import com.bjike.goddess.taskallotment.dto.ProjectDTO;
import com.bjike.goddess.taskallotment.dto.TaskNodeDTO;
import com.bjike.goddess.taskallotment.entity.TaskNode;
import com.bjike.goddess.taskallotment.to.TaskNodeTO;

import java.util.List;

/**
 * 任务节点业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-14 02:10 ]
 * @Description: [ 任务节点业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface TaskNodeSer extends Ser<TaskNode, TaskNodeDTO> {
    /**
     * 列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<ProjectBO> list(ProjectDTO dto) throws SerException;

    /**
     * 添加
     *
     * @param to
     * @return
     * @throws SerException
     */
    void save(TaskNodeTO to) throws SerException;

    /**
     * 编辑
     *
     * @param to
     * @throws SerException
     */
    void edit(TaskNodeTO to) throws SerException;

    /**
     * 删除
     *
     * @param id
     * @throws SerException
     */
    void delete(String id) throws SerException;

    /**
     * 通过id查找
     *
     * @param id
     * @return
     * @throws SerException
     */
    TaskNodeBO findByID(String id) throws SerException;

    /**
     * 查找总记录数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(TaskNodeDTO dto) throws SerException;

    /**
     * 检测某执行人某天的任务时长是否超过8小时
     *
     * @param to
     * @return
     * @throws SerException
     */
    Boolean checkTime(TaskNodeTO to) throws SerException;

    /**
     * 发起任务
     *
     * @param to
     * @throws SerException
     */
    void initiateTask(TaskNodeTO to) throws SerException;

    /**
     * 添加小任务
     *
     * @param to
     * @throws SerException
     */
    void addTask(TaskNodeTO to) throws SerException;

    /**
     * 我分发的任务
     *
     * @return
     * @throws SerException
     */
    List<TaskNodeBO> myInitiate(TaskNodeDTO dto) throws SerException;

    /**
     * 我分发的任务总条数
     *
     * @return
     * @throws SerException
     */
    Long myInitiateNum() throws SerException;

    /**
     * 撤回任务
     *
     * @param id
     * @throws SerException
     */
    void reback(String id) throws SerException;

    /**
     * 确认接收任务
     *
     * @param id
     * @throws SerException
     */
    void confirm(String id) throws SerException;

    /**
     * 不确认接收任务
     *
     * @param to
     * @throws SerException
     */
    void unConfirm(TaskNodeTO to) throws SerException;

    /**
     * 确认完成
     *
     * @param id
     * @throws SerException
     */
    void finish(String id) throws SerException;

    /**
     * 确认未完成
     *
     * @param id
     * @throws SerException
     */
    void unFinish(String id) throws SerException;

    /**
     * 上报审核通过
     *
     * @param to
     * @throws SerException
     */
    void pass(TaskNodeTO to) throws SerException;

    /**
     * 上报审核不通过
     *
     * @param to
     * @throws SerException
     */
    void notPass(TaskNodeTO to) throws SerException;

    /**
     * 我负责的任务
     *
     * @return
     * @throws SerException
     */
    List<TaskNodeBO> myCharge(TaskNodeDTO dto) throws SerException;

    /**
     * 我负责的任务总条数
     *
     * @return
     * @throws SerException
     */
    Long myChargeNum() throws SerException;

    /**
     * 分配我负责的任务
     *
     * @param to
     * @throws SerException
     */
    void allotment(TaskNodeTO to) throws SerException;

    /**
     * 我执行的任务
     *
     * @return
     * @throws SerException
     */
    List<TaskNodeBO> myExecute(TaskNodeDTO dto) throws SerException;

    /**
     * 我执行的任务总条数
     *
     * @return
     * @throws SerException
     */
    Long myExecuteNum() throws SerException;

    /**
     * 上报任务
     *
     * @param to
     * @throws SerException
     */
    void report(TaskNodeTO to) throws SerException;

    /**
     * 再次分发
     *
     * @param to
     * @throws SerException
     */
    void initiateAgain(TaskNodeTO to) throws SerException;

    /**
     * 填写任务完成情况
     *
     * @param to
     * @throws SerException
     */
    void write(TaskNodeTO to) throws SerException;

    /**
     * 个人汇总
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<PersonCountBO> personCount(TaskNodeDTO dto) throws SerException;

    /**
     * 人员标准工时汇总
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<TimeCountBO> timeCount(TaskNodeDTO dto) throws SerException;

    /**
     * 分配及确认汇总
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<ConfirmCountBO> confirmCount(TaskNodeDTO dto) throws SerException;

    /**
     * 完成情况汇总
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<FinishCaseBO> finishCount(TaskNodeDTO dto) throws SerException;

    /**
     * 获取所有地区
     *
     * @return
     * @throws SerException
     */
    List<String> areas() throws SerException;

    /**
     * 获取所有部门
     *
     * @return
     * @throws SerException
     */
    List<String> departs() throws SerException;

    /**
     * 日报
     * @param time 时间
     * @param names 姓名数组
     * @return
     * @throws SerException
     */
    List<DayBO> dayReport(String time, String[] names) throws SerException;
}