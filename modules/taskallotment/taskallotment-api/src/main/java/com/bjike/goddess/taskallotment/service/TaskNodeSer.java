package com.bjike.goddess.taskallotment.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.taskallotment.bo.*;
import com.bjike.goddess.taskallotment.bo.DayReport.DayBO;
import com.bjike.goddess.taskallotment.bo.DayReport.DayReportCountBO;
import com.bjike.goddess.taskallotment.bo.DayReport.DayReportMailBO;
import com.bjike.goddess.taskallotment.bo.figure.DataBO;
import com.bjike.goddess.taskallotment.bo.figure.OptionBO;
import com.bjike.goddess.taskallotment.dto.ProjectDTO;
import com.bjike.goddess.taskallotment.dto.TableDTO;
import com.bjike.goddess.taskallotment.dto.TaskNodeDTO;
import com.bjike.goddess.taskallotment.entity.TaskNode;
import com.bjike.goddess.taskallotment.excel.TaskNodeExcel;
import com.bjike.goddess.taskallotment.excel.TaskNodeLeadTO;
import com.bjike.goddess.taskallotment.to.CollectDataTO;
import com.bjike.goddess.taskallotment.to.GuidePermissionTO;
import com.bjike.goddess.taskallotment.to.TaskNodeTO;
import com.bjike.goddess.taskallotment.vo.CollectDataVO;

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
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }

    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<TableBO> list(TableDTO dto) throws SerException;

    /**
     * 总条数
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(TableDTO dto) throws SerException;

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
    Long myInitiateNum(TaskNodeDTO dto) throws SerException;

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
     * @param to
     * @throws SerException
     */
    void finish(TaskNodeTO to) throws SerException;

    /**
     * 确认未完成
     *
     * @param to
     * @throws SerException
     */
    void unFinish(TaskNodeTO to) throws SerException;

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
    Long myChargeNum(TaskNodeDTO dto) throws SerException;

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
    Long myExecuteNum(TaskNodeDTO dto) throws SerException;

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
     * 个人汇总图形化
     * @param dto
     * @return
     * @throws SerException
     */
    OptionBO personCountFigure(TaskNodeDTO dto) throws SerException;

    /**
     * 人员标准工时汇总
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<TimeCountBO> timeCount(TaskNodeDTO dto) throws SerException;

    /**
     * 人员标准工时汇总图形化
     * @param dto
     * @return
     * @throws SerException
     */
    OptionBO timeCountFigure(TaskNodeDTO dto) throws SerException;

    /**
     * 分配及确认汇总
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<ConfirmCountBO> confirmCount(TaskNodeDTO dto) throws SerException;

    /**
     * 分配及确认汇总图形化
     * @param dto
     * @return
     * @throws SerException
     */
    OptionBO confirmCountFigure(TaskNodeDTO dto) throws SerException;

    /**
     * 分配及确认汇总饼状图
     * @param dto
     * @return
     * @throws SerException
     */
    DataBO personBing(TaskNodeDTO dto) throws SerException;

    /**
     * 完成情况汇总
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<FinishCaseBO> finishCount(TaskNodeDTO dto) throws SerException;

    /**
     * 完成情况汇总图形化
     * @param dto
     * @return
     * @throws SerException
     */
    OptionBO finishCountFigure(TaskNodeDTO dto) throws SerException;

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
     *
     * @param time  时间
     * @param names 姓名数组
     * @return
     * @throws SerException
     */
    List<DayBO> dayReport(String time, String[] names) throws SerException;

    /**
     * 日报汇总
     *
     * @param startTime
     * @param endTime
     * @param departIds
     * @return
     * @throws SerException
     */
    DayReportCountBO dayCount(String startTime, String endTime, String[] departIds) throws SerException;

    /**
     * 日报汇总邮件
     * @param startTime
     * @param endTime
     * @param departIds
     * @return
     * @throws SerException
     */
    List<DayReportMailBO> dayCountMail(String startTime, String endTime, String[] departIds) throws SerException;

    /**
     * 获取某人当天的任务完成天数
     *
     * @param date
     * @return
     * @throws SerException
     */
    Double finishDay(String date, String name) throws SerException;

    /**
     * 获取某些人某天的任务完成情况
     * @param names
     * @param date
     * @return
     * @throws SerException
     */
    List<ObjectBO> taskSituation(String[] names, String date) throws SerException;

    /**
     * 导出excel
     * @param dto
     * @return
     * @throws SerException
     */
    byte[] exportExcel(TaskNodeDTO dto) throws SerException;

    /**
     * 导入excel
     * @param toList
     * @param tableId
     * @throws SerException
     */
    void leadExcel(List<TaskNodeLeadTO> toList, String tableId) throws SerException;

    /**
     * 根据项目表id获取任务名称
     * @param tableID
     * @return
     * @throws SerException
     */
    List<String> taskNames(String tableID) throws SerException;

    /**
     * 个人项目汇总
     * @param collectDataTO
     * @return
     * @throws SerException
     * @desc 根据某个用户名汇总返回用户和参与的项目名称
     * @author tanghaixiang
     */
    default CollectDataVO personProjectCollect (CollectDataTO collectDataTO) throws SerException{return null;};
}