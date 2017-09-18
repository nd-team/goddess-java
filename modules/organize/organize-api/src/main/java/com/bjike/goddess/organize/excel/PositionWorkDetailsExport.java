package com.bjike.goddess.organize.excel;

import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.organize.vo.IndicatorVO;
import com.bjike.goddess.organize.vo.ModulesVO;

import java.io.Serializable;
import java.util.List;

/**
 * 岗位工作明细表
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-03-29 03:57 ]
 * @Description: [ 岗位工作明细表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PositionWorkDetailsExport implements Serializable {


    /**
     * 公司目标
     */
    @ExcelHeader(name = "公司目标", notNull = true)
    private String goals;

    /**
     * 公司
     */
    @ExcelHeader(name = "公司", notNull = true)
    private String company;

    /**
     * 部门目标
     */
    @ExcelHeader(name = "部门目标", notNull = true)
    private String departmentalGoals;

    /**
     * 项目组/部门
     */
    @ExcelHeader(name = "项目组/部门", notNull = true)
    private String department;

    /**
     * 岗位目标
     */
    @ExcelHeader(name = "岗位目标", notNull = true)
    private String positionGoals;

    /**
     * 岗位
     */
    @ExcelHeader(name = "岗位", notNull = true)
    private String position;

    /**
     * 序号
     */
    @ExcelHeader(name = "序号", notNull = true)
    private Long serialNumber;

    /**
     * 角度
     */
    @ExcelHeader(name = "角度", notNull = true)
    private String angle;

    /**
     * 维度
     */
    @ExcelHeader(name = "维度", notNull = true)
    private String dimension;

    /**
     * 分类
     */
    @ExcelHeader(name = "分类", notNull = true)
    private String classification;

    /**
     * 项目阶段
     */
    @ExcelHeader(name = "项目阶段", notNull = true)
    private String projectStage;

    /**
     * 项目阶段编号
     */
    @ExcelHeader(name = "项目阶段编号", notNull = true)
    private String projectStageNum;

    /**
     * 功能（流程）
     */
    @ExcelHeader(name = "功能（流程）", notNull = true)
    private String function;

    /**
     * 功能（流程）目的
     */
    @ExcelHeader(name = "功能（流程）目的", notNull = true)
    private String purpose;

    /**
     * 功能版本
     */
    @ExcelHeader(name = "功能版本", notNull = true)
    private String version;

    /**
     * 工作频率
     */
    @ExcelHeader(name = "工作频率", notNull = true)
    private String frequency;

    /**
     * 工作时间节点
     */
    @ExcelHeader(name = "工作时间节点", notNull = true)
    private String timeNode;

    /**
     * 操作类型
     */
    @ExcelHeader(name = "操作类型", notNull = true)
    private String operationType;

    /**
     * 工作内容
     */
    @ExcelHeader(name = "工作内容", notNull = true)
    private String workContent;

    /**
     * 包含的附件（名称）
     */
    @ExcelHeader(name = "包含的附件（名称）", notNull = true)
    private String accessories;

    /**
     * 是否有模板
     */
    @ExcelHeader(name = "是否有模板", notNull = true)
    private Boolean hasMould;

    /**
     * 模板储存位置
     */
    @ExcelHeader(name = "模板储存位置", notNull = true)
    private String mouldStorage;

    /**
     * 工作数据存储位置
     */
    @ExcelHeader(name = "工作数据存储位置", notNull = true)
    private String paperStorage;

    /**
     * 经验总结
     */
    @ExcelHeader(name = "经验总结", notNull = true)
    private String summarize;

    /**
     * 通报时间节点
     */
    @ExcelHeader(name = "通报时间节点", notNull = true)
    private String informTimeNode;

    /**
     * 通报形式
     */
    @ExcelHeader(name = "通报形式", notNull = true)
    private String notificationForm;

    /**
     * 通报内容
     */
    @ExcelHeader(name = "通报内容", notNull = true)
    private String notificationContent;

    /**
     * 通报对象
     */
    @ExcelHeader(name = "通报对象", notNull = true)
    private String notificationObj;

    /**
     * 姓名（在岗人员）
     */
    @ExcelHeader(name = "姓名（在岗人员）", notNull = true)
    private String name;

    /**
     * 代理人
     */
    @ExcelHeader(name = "代理人", notNull = true)
    private String agent;

    /**
     * 是否完成
     */
    @ExcelHeader(name = "是否完成", notNull = true)
    private Boolean isComplete;

    /**
     * 完成岗位
     */
    @ExcelHeader(name = "完成岗位", notNull = true)
    private String completeOpition;

    /**
     * 模块
     */
    private List<ModulesVO> modulesVOList;

}