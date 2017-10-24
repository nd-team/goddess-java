package com.bjike.goddess.organize.excel;

import com.bjike.goddess.common.utils.excel.ExcelHeader;

import java.io.Serializable;

/**
 * 岗位工作明细表
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-03-29 03:57 ]
 * @Description: [ 岗位工作明细表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PositionWorkDetailsImport implements Serializable {

    /**
     * 排序
     */
    @ExcelHeader(name = "排序", notNull = true)
    private Integer seqNum; 

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
     * -------------33---------
     */
    /**
     * （对接）规划模块
     */
    @ExcelHeader(name = "（对接）规划模块")
    private Boolean hasConnet1;

    /**
     * 通报时间节点
     */
    @ExcelHeader(name = "规划通报时间节点")
    private String informTimeNode1;

    /**
     * 通报形式
     */
    @ExcelHeader(name = "规划通报形式")
    private String notificationForm1;

    /**
     * 通报内容模板
     */
    @ExcelHeader(name = "规划通报内容模板")
    private String notificationContent1;

    /**
     * 协助时间节点
     */
    @ExcelHeader(name = "规划协助时间节点")
    private String timeNode1;

    /**
     * 协助函发送形式
     */
    @ExcelHeader(name = "规划协助函发送形式")
    private String letterForm1;

    /**
     * 协助内容模板
     */
    @ExcelHeader(name = "规划协助内容模板")
    private String contentTemplate1;

    /**
     * 功能
     */
    @ExcelHeader(name = "规划功能")
    private String functions1;

    /**
     * 指标序号
     */
    @ExcelHeader(name = "规划指标序号")
    private String number1;

    /**
     * 指标类型
     */
    @ExcelHeader(name = "规划指标类型")
    private String type1;

    /**
     * 考核指标
     */
    @ExcelHeader(name = "规划考核指标")
    private String assessment1;

    /**
     * 目标值
     */
    @ExcelHeader(name = "规划目标值")
    private String targetValue1;

    /**
     * 体现
     */
    @ExcelHeader(name = "规划体现")
    private String reflect1;

    /**
     * 达到指标对赌分
     */
    @ExcelHeader(name = "规划达到指标对赌分")
    private Long gambling1;

    /**
     * 未达到指标对赌分
     */
    @ExcelHeader(name = "规划未达到指标对赌分")
    private Long unGambling1;

    /**
     * 是否达到指标
     */
    @ExcelHeader(name = "规划是否达到指标")
    private Boolean isReach1;

    /**
     * 要求方
     */
    @ExcelHeader(name = "规划要求方")
    private String requestSide1;

    /**
     * 对赌方
     */
    @ExcelHeader(name = "规划对赌方")
    private String gamblingSide1;

    /**
     * 规则
     */
    @ExcelHeader(name = "规划规则")
    private String rule1;

    //-----------------------（对接）福利模块---------19---52-----
    /**
     * （对接）福利模块
     */
    @ExcelHeader(name = "（对接）福利模块")
    private Boolean hasConnet2;

    /**
     * 通报时间节点
     */
    @ExcelHeader(name = "福利通报时间节点")
    private String informTimeNode2;

    /**
     * 通报形式
     */
    @ExcelHeader(name = "福利通报形式")
    private String notificationForm2;

    /**
     * 通报内容模板
     */
    @ExcelHeader(name = "福利通报内容模板")
    private String notificationContent2;

    /**
     * 协助时间节点
     */
    @ExcelHeader(name = "福利协助时间节点")
    private String timeNode2;

    /**
     * 协助函发送形式
     */
    @ExcelHeader(name = "福利协助函发送形式")
    private String letterForm2;

    /**
     * 协助内容模板
     */
    @ExcelHeader(name = "福利协助内容模板")
    private String contentTemplate2;

    /**
     * 功能
     */
    @ExcelHeader(name = "福利功能")
    private String functions2;

    /**
     * 指标序号
     */
    @ExcelHeader(name = "福利指标序号")
    private String number2;

    /**
     * 指标类型
     */
    @ExcelHeader(name = "福利指标类型")
    private String type2;

    /**
     * 考核指标
     */
    @ExcelHeader(name = "福利考核指标")
    private String assessment2;

    /**
     * 目标值
     */
    @ExcelHeader(name = "福利目标值")
    private String targetValue2;

    /**
     * 体现
     */
    @ExcelHeader(name = "福利体现")
    private String reflect2;

    /**
     * 达到指标对赌分
     */
    @ExcelHeader(name = "福利达到指标对赌分")
    private Long gambling2;

    /**
     * 未达到指标对赌分
     */
    @ExcelHeader(name = "福利未达到指标对赌分")
    private Long unGambling2;

    /**
     * 是否达到指标
     */
    @ExcelHeader(name = "福利是否达到指标")
    private Boolean isReach2;

    /**
     * 要求方
     */
    @ExcelHeader(name = "福利要求方")
    private String requestSide2;

    /**
     * 对赌方
     */
    @ExcelHeader(name = "福利对赌方")
    private String gamblingSide2;

    /**
     * 规则
     */
    @ExcelHeader(name = "福利规则")
    private String rule2;

    //-------------------------------（对接）素养模块模块3-----------------------------
    /**
     * （对接）素养模块模块
     */
    @ExcelHeader(name = "（对接）素养模块模块")
    private Boolean hasConnet3;

    /**
     * 通报时间节点
     */
    @ExcelHeader(name = "素养通报时间节点")
    private String informTimeNode3;

    /**
     * 通报形式
     */
    @ExcelHeader(name = "素养通报形式")
    private String notificationForm3;

    /**
     * 通报内容模板
     */
    @ExcelHeader(name = "素养通报内容模板")
    private String notificationContent3;

    /**
     * 协助时间节点
     */
    @ExcelHeader(name = "素养协助时间节点")
    private String timeNode3;

    /**
     * 协助函发送形式
     */
    @ExcelHeader(name = "素养协助函发送形式")
    private String letterForm3;

    /**
     * 协助内容模板
     */
    @ExcelHeader(name = "素养协助内容模板")
    private String contentTemplate3;

    /**
     * 功能
     */
    @ExcelHeader(name = "素养功能")
    private String functions3;

    /**
     * 指标序号
     */
    @ExcelHeader(name = "素养指标序号")
    private String number3;

    /**
     * 指标类型
     */
    @ExcelHeader(name = "素养指标类型")
    private String type3;

    /**
     * 考核指标
     */
    @ExcelHeader(name = "素养考核指标")
    private String assessment3;

    /**
     * 目标值
     */
    @ExcelHeader(name = "素养目标值")
    private String targetValue3;

    /**
     * 体现
     */
    @ExcelHeader(name = "素养体现")
    private String reflect3;

    /**
     * 达到指标对赌分
     */
    @ExcelHeader(name = "素养达到指标对赌分")
    private Long gambling3;

    /**
     * 未达到指标对赌分
     */
    @ExcelHeader(name = "素养未达到指标对赌分")
    private Long unGambling3;

    /**
     * 是否达到指标
     */
    @ExcelHeader(name = "素养是否达到指标")
    private Boolean isReach3;

    /**
     * 要求方
     */
    @ExcelHeader(name = "素养要求方")
    private String requestSide3;

    /**
     * 对赌方
     */
    @ExcelHeader(name = "素养对赌方")
    private String gamblingSide3;

    /**
     * 规则
     */
    @ExcelHeader(name = "素养规则")
    private String rule3;

    //-------------------------------（对接）预算模块4-----------------------------
    /**
     * （对接）预算模块
     */
    @ExcelHeader(name = "（对接）预算模块")
    private Boolean hasConnet4;

    /**
     * 通报时间节点
     */
    @ExcelHeader(name = "预算通报时间节点")
    private String informTimeNode4;

    /**
     * 通报形式
     */
    @ExcelHeader(name = "预算通报形式")
    private String notificationForm4;

    /**
     * 通报内容模板
     */
    @ExcelHeader(name = "预算通报内容模板")
    private String notificationContent4;

    /**
     * 协助时间节点
     */
    @ExcelHeader(name = "预算协助时间节点")
    private String timeNode4;

    /**
     * 协助函发送形式
     */
    @ExcelHeader(name = "预算协助函发送形式")
    private String letterForm4;

    /**
     * 协助内容模板
     */
    @ExcelHeader(name = "预算协助内容模板")
    private String contentTemplate4;

    /**
     * 功能
     */
    @ExcelHeader(name = "预算功能")
    private String functions4;

    /**
     * 指标序号
     */
    @ExcelHeader(name = "预算指标序号")
    private String number4;

    /**
     * 指标类型
     */
    @ExcelHeader(name = "预算指标类型")
    private String type4;

    /**
     * 考核指标
     */
    @ExcelHeader(name = "预算考核指标")
    private String assessment4;

    /**
     * 目标值
     */
    @ExcelHeader(name = "预算目标值")
    private String targetValue4;

    /**
     * 体现
     */
    @ExcelHeader(name = "预算体现")
    private String reflect4;

    /**
     * 达到指标对赌分
     */
    @ExcelHeader(name = "预算达到指标对赌分")
    private Long gambling4;

    /**
     * 未达到指标对赌分
     */
    @ExcelHeader(name = "预算未达到指标对赌分")
    private Long unGambling4;

    /**
     * 是否达到指标
     */
    @ExcelHeader(name = "预算是否达到指标")
    private Boolean isReach4;

    /**
     * 要求方
     */
    @ExcelHeader(name = "预算要求方")
    private String requestSide4;

    /**
     * 对赌方
     */
    @ExcelHeader(name = "预算对赌方")
    private String gamblingSide4;

    /**
     * 规则
     */
    @ExcelHeader(name = "预算规则")
    private String rule4;
    //-------------------------------（对接）资金模块5-----------------------------
    /**
     * （对接）资金模块
     */
    @ExcelHeader(name = "（对接）资金模块")
    private Boolean hasConnet5;

    /**
     * 通报时间节点
     */
    @ExcelHeader(name = "资金通报时间节点")
    private String informTimeNode5;

    /**
     * 通报形式
     */
    @ExcelHeader(name = "资金通报形式")
    private String notificationForm5;

    /**
     * 通报内容模板
     */
    @ExcelHeader(name = "资金通报内容模板")
    private String notificationContent5;

    /**
     * 协助时间节点
     */
    @ExcelHeader(name = "资金协助时间节点")
    private String timeNode5;

    /**
     * 协助函发送形式
     */
    @ExcelHeader(name = "资金协助函发送形式")
    private String letterForm5;

    /**
     * 协助内容模板
     */
    @ExcelHeader(name = "资金协助内容模板")
    private String contentTemplate5;

    /**
     * 功能
     */
    @ExcelHeader(name = "资金功能")
    private String functions5;

    /**
     * 指标序号
     */
    @ExcelHeader(name = "资金指标序号")
    private String number5;

    /**
     * 指标类型
     */
    @ExcelHeader(name = "资金指标类型")
    private String type5;

    /**
     * 考核指标
     */
    @ExcelHeader(name = "资金考核指标")
    private String assessment5;

    /**
     * 目标值
     */
    @ExcelHeader(name = "资金目标值")
    private String targetValue5;

    /**
     * 体现
     */
    @ExcelHeader(name = "资金体现")
    private String reflect5;

    /**
     * 达到指标对赌分
     */
    @ExcelHeader(name = "资金达到指标对赌分")
    private Long gambling5;

    /**
     * 未达到指标对赌分
     */
    @ExcelHeader(name = "资金未达到指标对赌分")
    private Long unGambling5;

    /**
     * 是否达到指标
     */
    @ExcelHeader(name = "资金是否达到指标")
    private Boolean isReach5;

    /**
     * 要求方
     */
    @ExcelHeader(name = "要求方")
    private String requestSide5;

    /**
     * 对赌方
     */
    @ExcelHeader(name = "资金对赌方")
    private String gamblingSide5;

    /**
     * 规则
     */
    @ExcelHeader(name = "资金规则")
    private String rule5;
    //-------------------------------（对接）账务模块6-----------------------------
    /**
     * （对接）账务模块
     */
    @ExcelHeader(name = "（对接）账务模块")
    private Boolean hasConnet6;

    /**
     * 通报时间节点
     */
    @ExcelHeader(name = "账务通报时间节点")
    private String informTimeNode6;

    /**
     * 通报形式
     */
    @ExcelHeader(name = "账务通报形式")
    private String notificationForm6;

    /**
     * 通报内容模板
     */
    @ExcelHeader(name = "账务通报内容模板")
    private String notificationContent6;

    /**
     * 协助时间节点
     */
    @ExcelHeader(name = "账务协助时间节点")
    private String timeNode6;

    /**
     * 协助函发送形式
     */
    @ExcelHeader(name = "账务协助函发送形式")
    private String letterForm6;

    /**
     * 协助内容模板
     */
    @ExcelHeader(name = "账务协助内容模板")
    private String contentTemplate6;

    /**
     * 功能
     */
    @ExcelHeader(name = "账务功能")
    private String functions6;

    /**
     * 指标序号
     */
    @ExcelHeader(name = "账务指标序号")
    private String number6;

    /**
     * 指标类型
     */
    @ExcelHeader(name = "账务指标类型")
    private String type6;

    /**
     * 考核指标
     */
    @ExcelHeader(name = "账务考核指标")
    private String assessment6;

    /**
     * 目标值
     */
    @ExcelHeader(name = "账务目标值")
    private String targetValue6;

    /**
     * 体现
     */
    @ExcelHeader(name = "账务体现")
    private String reflect6;

    /**
     * 达到指标对赌分
     */
    @ExcelHeader(name = "账务达到指标对赌分")
    private Long gambling6;

    /**
     * 未达到指标对赌分
     */
    @ExcelHeader(name = "账务未达到指标对赌分")
    private Long unGambling6;

    /**
     * 是否达到指标
     */
    @ExcelHeader(name = "账务是否达到指标")
    private Boolean isReach6;

    /**
     * 要求方
     */
    @ExcelHeader(name = "账务要求方")
    private String requestSide6;

    /**
     * 对赌方
     */
    @ExcelHeader(name = "账务对赌方")
    private String gamblingSide6;

    /**
     * 规则
     */
    @ExcelHeader(name = "账务规则")
    private String rule6;
    //-------------------------------（对接）客户管理模块7-----------------------------
    /**
     * （对接）客户管理模块
     */
    @ExcelHeader(name = "（对接）客户管理模块")
    private Boolean hasConnet7;

    /**
     * 通报时间节点
     */
    @ExcelHeader(name = "客户管理通报时间节点")
    private String informTimeNode7;

    /**
     * 通报形式
     */
    @ExcelHeader(name = "客户管理通报形式")
    private String notificationForm7;

    /**
     * 通报内容模板
     */
    @ExcelHeader(name = "客户管理通报内容模板")
    private String notificationContent7;

    /**
     * 协助时间节点
     */
    @ExcelHeader(name = "客户管理协助时间节点")
    private String timeNode7;

    /**
     * 协助函发送形式
     */
    @ExcelHeader(name = "客户管理协助函发送形式")
    private String letterForm7;

    /**
     * 协助内容模板
     */
    @ExcelHeader(name = "客户管理协助内容模板")
    private String contentTemplate7;

    /**
     * 功能
     */
    @ExcelHeader(name = "客户管理功能")
    private String functions7;

    /**
     * 指标序号
     */
    @ExcelHeader(name = "客户管理指标序号")
    private String number7;

    /**
     * 指标类型
     */
    @ExcelHeader(name = "客户管理指标类型")
    private String type7;

    /**
     * 考核指标
     */
    @ExcelHeader(name = "客户管理考核指标")
    private String assessment7;

    /**
     * 目标值
     */
    @ExcelHeader(name = "客户管理目标值")
    private String targetValue7;

    /**
     * 体现
     */
    @ExcelHeader(name = "客户管理体现")
    private String reflect7;

    /**
     * 达到指标对赌分
     */
    @ExcelHeader(name = "客户管理达到指标对赌分")
    private Long gambling7;

    /**
     * 未达到指标对赌分
     */
    @ExcelHeader(name = "客户管理未达到指标对赌分")
    private Long unGambling7;

    /**
     * 是否达到指标
     */
    @ExcelHeader(name = "客户管理是否达到指标")
    private Boolean isReach7;

    /**
     * 要求方
     */
    @ExcelHeader(name = "客户管理要求方")
    private String requestSide7;

    /**
     * 对赌方
     */
    @ExcelHeader(name = "客户管理对赌方")
    private String gamblingSide7;

    /**
     * 规则
     */
    @ExcelHeader(name = "客户管理规则")
    private String rule7;
    //-------------------------------（对接）进度管理模块8-----------------------------
    /**
     * （对接）进度管理模块
     */
    @ExcelHeader(name = "（对接）进度管理模块")
    private Boolean hasConnet8;

    /**
     * 通报时间节点
     */
    @ExcelHeader(name = "进度管理通报时间节点")
    private String informTimeNode8;

    /**
     * 通报形式
     */
    @ExcelHeader(name = "进度管理通报形式")
    private String notificationForm8;

    /**
     * 通报内容模板
     */
    @ExcelHeader(name = "进度管理通报内容模板")
    private String notificationContent8;

    /**
     * 协助时间节点
     */
    @ExcelHeader(name = "进度管理协助时间节点")
    private String timeNode8;

    /**
     * 协助函发送形式
     */
    @ExcelHeader(name = "进度管理协助函发送形式")
    private String letterForm8;

    /**
     * 协助内容模板
     */
    @ExcelHeader(name = "进度管理协助内容模板")
    private String contentTemplate8;

    /**
     * 功能
     */
    @ExcelHeader(name = "进度管理功能")
    private String functions8;

    /**
     * 指标序号
     */
    @ExcelHeader(name = "进度管理指标序号")
    private String number8;

    /**
     * 指标类型
     */
    @ExcelHeader(name = "进度管理指标类型")
    private String type8;

    /**
     * 考核指标
     */
    @ExcelHeader(name = "进度管理考核指标")
    private String assessment8;

    /**
     * 目标值
     */
    @ExcelHeader(name = "进度管理目标值")
    private String targetValue8;

    /**
     * 体现
     */
    @ExcelHeader(name = "进度管理体现")
    private String reflect8;

    /**
     * 达到指标对赌分
     */
    @ExcelHeader(name = "进度管理达到指标对赌分")
    private Long gambling8;

    /**
     * 未达到指标对赌分
     */
    @ExcelHeader(name = "进度管理未达到指标对赌分")
    private Long unGambling8;

    /**
     * 是否达到指标
     */
    @ExcelHeader(name = "进度管理是否达到指标")
    private Boolean isReach8;

    /**
     * 要求方
     */
    @ExcelHeader(name = "进度管理要求方")
    private String requestSide8;

    /**
     * 对赌方
     */
    @ExcelHeader(name = "进度管理对赌方")
    private String gamblingSide8;

    /**
     * 规则
     */
    @ExcelHeader(name = "进度管理规则")
    private String rule8;
    //-------------------------------（对接）业务管理模块9-----------------------------
    /**
     * （对接）业务管理模块
     */
    @ExcelHeader(name = "（对接）业务管理模块")
    private Boolean hasConnet9;

    /**
     * 通报时间节点
     */
    @ExcelHeader(name = "业务管理通报时间节点")
    private String informTimeNode9;

    /**
     * 通报形式
     */
    @ExcelHeader(name = "业务管理通报形式")
    private String notificationForm9;

    /**
     * 通报内容模板
     */
    @ExcelHeader(name = "业务管理通报内容模板")
    private String notificationContent9;

    /**
     * 协助时间节点
     */
    @ExcelHeader(name = "业务管理协助时间节点")
    private String timeNode9;

    /**
     * 协助函发送形式
     */
    @ExcelHeader(name = "业务管理协助函发送形式")
    private String letterForm9;

    /**
     * 协助内容模板
     */
    @ExcelHeader(name = "业务管理协助内容模板")
    private String contentTemplate9;

    /**
     * 功能
     */
    @ExcelHeader(name = "业务管理功能")
    private String functions9;

    /**
     * 指标序号
     */
    @ExcelHeader(name = "业务管理指标序号")
    private String number9;

    /**
     * 指标类型
     */
    @ExcelHeader(name = "业务管理指标类型")
    private String type9;

    /**
     * 考核指标
     */
    @ExcelHeader(name = "业务管理考核指标")
    private String assessment9;

    /**
     * 目标值
     */
    @ExcelHeader(name = "业务管理目标值")
    private String targetValue9;

    /**
     * 体现
     */
    @ExcelHeader(name = "业务管理体现")
    private String reflect9;

    /**
     * 达到指标对赌分
     */
    @ExcelHeader(name = "业务管理达到指标对赌分")
    private Long gambling9;

    /**
     * 未达到指标对赌分
     */
    @ExcelHeader(name = "业务管理未达到指标对赌分")
    private Long unGambling9;

    /**
     * 是否达到指标
     */
    @ExcelHeader(name = "业务管理是否达到指标")
    private Boolean isReach9;

    /**
     * 要求方
     */
    @ExcelHeader(name = "业务管理要求方")
    private String requestSide9;

    /**
     * 对赌方
     */
    @ExcelHeader(name = "业务管理对赌方")
    private String gamblingSide9;

    /**
     * 规则
     */
    @ExcelHeader(name = "业务管理规则")
    private String rule9;
    //-------------------------------（对接）项目组10-----------------------------
    /**
     * （对接）项目组
     */
    @ExcelHeader(name = "（对接）项目组")
    private Boolean hasConnet0;

    /**
     * 通报时间节点
     */
    @ExcelHeader(name = "项目组通报时间节点")
    private String informTimeNode0;

    /**
     * 通报形式
     */
    @ExcelHeader(name = "项目组通报形式")
    private String notificationForm0;

    /**
     * 通报内容模板
     */
    @ExcelHeader(name = "项目组通报内容模板")
    private String notificationContent0;

    /**
     * 协助时间节点
     */
    @ExcelHeader(name = "项目组协助时间节点")
    private String timeNode0;

    /**
     * 协助函发送形式
     */
    @ExcelHeader(name = "项目组协助函发送形式")
    private String letterForm0;

    /**
     * 协助内容模板
     */
    @ExcelHeader(name = "项目组协助内容模板")
    private String contentTemplate0;

    /**
     * 功能
     */
    @ExcelHeader(name = "项目组功能")
    private String functions0;

    /**
     * 指标序号
     */
    @ExcelHeader(name = "项目组指标序号")
    private String number0;

    /**
     * 指标类型
     */
    @ExcelHeader(name = "项目组指标类型")
    private String type0;

    /**
     * 考核指标
     */
    @ExcelHeader(name = "项目组考核指标")
    private String assessment0;

    /**
     * 目标值
     */
    @ExcelHeader(name = "项目组目标值")
    private String targetValue0;

    /**
     * 体现
     */
    @ExcelHeader(name = "项目组体现")
    private String reflect0;

    /**
     * 达到指标对赌分
     */
    @ExcelHeader(name = "项目组达到指标对赌分")
    private Long gambling0;

    /**
     * 未达到指标对赌分
     */
    @ExcelHeader(name = "项目组未达到指标对赌分")
    private Long unGambling0;

    /**
     * 是否达到指标
     */
    @ExcelHeader(name = "项目组是否达到指标")
    private Boolean isReach0;

    /**
     * 要求方
     */
    @ExcelHeader(name = "项目组要求方")
    private String requestSide0;

    /**
     * 对赌方
     */
    @ExcelHeader(name = "项目组对赌方")
    private String gamblingSide0;

    /**
     * 规则
     */
    @ExcelHeader(name = "项目组规则")
    private String rule0;

    public Integer getSeqNum() {
        return seqNum;
    }

    public void setSeqNum(Integer seqNum) {
        this.seqNum = seqNum;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDepartmentalGoals() {
        return departmentalGoals;
    }

    public void setDepartmentalGoals(String departmentalGoals) {
        this.departmentalGoals = departmentalGoals;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPositionGoals() {
        return positionGoals;
    }

    public void setPositionGoals(String positionGoals) {
        this.positionGoals = positionGoals;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Long serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getAngle() {
        return angle;
    }

    public void setAngle(String angle) {
        this.angle = angle;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getProjectStage() {
        return projectStage;
    }

    public void setProjectStage(String projectStage) {
        this.projectStage = projectStage;
    }

    public String getProjectStageNum() {
        return projectStageNum;
    }

    public void setProjectStageNum(String projectStageNum) {
        this.projectStageNum = projectStageNum;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getTimeNode() {
        return timeNode;
    }

    public void setTimeNode(String timeNode) {
        this.timeNode = timeNode;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getWorkContent() {
        return workContent;
    }

    public void setWorkContent(String workContent) {
        this.workContent = workContent;
    }

    public String getAccessories() {
        return accessories;
    }

    public void setAccessories(String accessories) {
        this.accessories = accessories;
    }

    public Boolean getHasMould() {
        return hasMould;
    }

    public void setHasMould(Boolean hasMould) {
        this.hasMould = hasMould;
    }

    public String getMouldStorage() {
        return mouldStorage;
    }

    public void setMouldStorage(String mouldStorage) {
        this.mouldStorage = mouldStorage;
    }

    public String getPaperStorage() {
        return paperStorage;
    }

    public void setPaperStorage(String paperStorage) {
        this.paperStorage = paperStorage;
    }

    public String getSummarize() {
        return summarize;
    }

    public void setSummarize(String summarize) {
        this.summarize = summarize;
    }

    public String getInformTimeNode() {
        return informTimeNode;
    }

    public void setInformTimeNode(String informTimeNode) {
        this.informTimeNode = informTimeNode;
    }

    public String getNotificationForm() {
        return notificationForm;
    }

    public void setNotificationForm(String notificationForm) {
        this.notificationForm = notificationForm;
    }

    public String getNotificationContent() {
        return notificationContent;
    }

    public void setNotificationContent(String notificationContent) {
        this.notificationContent = notificationContent;
    }

    public String getNotificationObj() {
        return notificationObj;
    }

    public void setNotificationObj(String notificationObj) {
        this.notificationObj = notificationObj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public Boolean getComplete() {
        return isComplete;
    }

    public void setComplete(Boolean complete) {
        isComplete = complete;
    }

    public String getCompleteOpition() {
        return completeOpition;
    }

    public void setCompleteOpition(String completeOpition) {
        this.completeOpition = completeOpition;
    }

    public Boolean getHasConnet1() {
        return hasConnet1;
    }

    public void setHasConnet1(Boolean hasConnet1) {
        this.hasConnet1 = hasConnet1;
    }

    public String getInformTimeNode1() {
        return informTimeNode1;
    }

    public void setInformTimeNode1(String informTimeNode1) {
        this.informTimeNode1 = informTimeNode1;
    }

    public String getNotificationForm1() {
        return notificationForm1;
    }

    public void setNotificationForm1(String notificationForm1) {
        this.notificationForm1 = notificationForm1;
    }

    public String getNotificationContent1() {
        return notificationContent1;
    }

    public void setNotificationContent1(String notificationContent1) {
        this.notificationContent1 = notificationContent1;
    }

    public String getTimeNode1() {
        return timeNode1;
    }

    public void setTimeNode1(String timeNode1) {
        this.timeNode1 = timeNode1;
    }

    public String getLetterForm1() {
        return letterForm1;
    }

    public void setLetterForm1(String letterForm1) {
        this.letterForm1 = letterForm1;
    }

    public String getContentTemplate1() {
        return contentTemplate1;
    }

    public void setContentTemplate1(String contentTemplate1) {
        this.contentTemplate1 = contentTemplate1;
    }

    public String getFunctions1() {
        return functions1;
    }

    public void setFunctions1(String functions1) {
        this.functions1 = functions1;
    }

    public String getNumber1() {
        return number1;
    }

    public void setNumber1(String number1) {
        this.number1 = number1;
    }

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getAssessment1() {
        return assessment1;
    }

    public void setAssessment1(String assessment1) {
        this.assessment1 = assessment1;
    }

    public String getTargetValue1() {
        return targetValue1;
    }

    public void setTargetValue1(String targetValue1) {
        this.targetValue1 = targetValue1;
    }

    public String getReflect1() {
        return reflect1;
    }

    public void setReflect1(String reflect1) {
        this.reflect1 = reflect1;
    }

    public Long getGambling1() {
        return gambling1;
    }

    public void setGambling1(Long gambling1) {
        this.gambling1 = gambling1;
    }

    public Long getUnGambling1() {
        return unGambling1;
    }

    public void setUnGambling1(Long unGambling1) {
        this.unGambling1 = unGambling1;
    }

    public Boolean getReach1() {
        return isReach1;
    }

    public void setReach1(Boolean reach1) {
        isReach1 = reach1;
    }

    public String getRequestSide1() {
        return requestSide1;
    }

    public void setRequestSide1(String requestSide1) {
        this.requestSide1 = requestSide1;
    }

    public String getGamblingSide1() {
        return gamblingSide1;
    }

    public void setGamblingSide1(String gamblingSide1) {
        this.gamblingSide1 = gamblingSide1;
    }

    public String getRule1() {
        return rule1;
    }

    public void setRule1(String rule1) {
        this.rule1 = rule1;
    }

    public Boolean getHasConnet2() {
        return hasConnet2;
    }

    public void setHasConnet2(Boolean hasConnet2) {
        this.hasConnet2 = hasConnet2;
    }

    public String getInformTimeNode2() {
        return informTimeNode2;
    }

    public void setInformTimeNode2(String informTimeNode2) {
        this.informTimeNode2 = informTimeNode2;
    }

    public String getNotificationForm2() {
        return notificationForm2;
    }

    public void setNotificationForm2(String notificationForm2) {
        this.notificationForm2 = notificationForm2;
    }

    public String getNotificationContent2() {
        return notificationContent2;
    }

    public void setNotificationContent2(String notificationContent2) {
        this.notificationContent2 = notificationContent2;
    }

    public String getTimeNode2() {
        return timeNode2;
    }

    public void setTimeNode2(String timeNode2) {
        this.timeNode2 = timeNode2;
    }

    public String getLetterForm2() {
        return letterForm2;
    }

    public void setLetterForm2(String letterForm2) {
        this.letterForm2 = letterForm2;
    }

    public String getContentTemplate2() {
        return contentTemplate2;
    }

    public void setContentTemplate2(String contentTemplate2) {
        this.contentTemplate2 = contentTemplate2;
    }

    public String getFunctions2() {
        return functions2;
    }

    public void setFunctions2(String functions2) {
        this.functions2 = functions2;
    }

    public String getNumber2() {
        return number2;
    }

    public void setNumber2(String number2) {
        this.number2 = number2;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public String getAssessment2() {
        return assessment2;
    }

    public void setAssessment2(String assessment2) {
        this.assessment2 = assessment2;
    }

    public String getTargetValue2() {
        return targetValue2;
    }

    public void setTargetValue2(String targetValue2) {
        this.targetValue2 = targetValue2;
    }

    public String getReflect2() {
        return reflect2;
    }

    public void setReflect2(String reflect2) {
        this.reflect2 = reflect2;
    }

    public Long getGambling2() {
        return gambling2;
    }

    public void setGambling2(Long gambling2) {
        this.gambling2 = gambling2;
    }

    public Long getUnGambling2() {
        return unGambling2;
    }

    public void setUnGambling2(Long unGambling2) {
        this.unGambling2 = unGambling2;
    }

    public Boolean getReach2() {
        return isReach2;
    }

    public void setReach2(Boolean reach2) {
        isReach2 = reach2;
    }

    public String getRequestSide2() {
        return requestSide2;
    }

    public void setRequestSide2(String requestSide2) {
        this.requestSide2 = requestSide2;
    }

    public String getGamblingSide2() {
        return gamblingSide2;
    }

    public void setGamblingSide2(String gamblingSide2) {
        this.gamblingSide2 = gamblingSide2;
    }

    public String getRule2() {
        return rule2;
    }

    public void setRule2(String rule2) {
        this.rule2 = rule2;
    }

    public Boolean getHasConnet3() {
        return hasConnet3;
    }

    public void setHasConnet3(Boolean hasConnet3) {
        this.hasConnet3 = hasConnet3;
    }

    public String getInformTimeNode3() {
        return informTimeNode3;
    }

    public void setInformTimeNode3(String informTimeNode3) {
        this.informTimeNode3 = informTimeNode3;
    }

    public String getNotificationForm3() {
        return notificationForm3;
    }

    public void setNotificationForm3(String notificationForm3) {
        this.notificationForm3 = notificationForm3;
    }

    public String getNotificationContent3() {
        return notificationContent3;
    }

    public void setNotificationContent3(String notificationContent3) {
        this.notificationContent3 = notificationContent3;
    }

    public String getTimeNode3() {
        return timeNode3;
    }

    public void setTimeNode3(String timeNode3) {
        this.timeNode3 = timeNode3;
    }

    public String getLetterForm3() {
        return letterForm3;
    }

    public void setLetterForm3(String letterForm3) {
        this.letterForm3 = letterForm3;
    }

    public String getContentTemplate3() {
        return contentTemplate3;
    }

    public void setContentTemplate3(String contentTemplate3) {
        this.contentTemplate3 = contentTemplate3;
    }

    public String getFunctions3() {
        return functions3;
    }

    public void setFunctions3(String functions3) {
        this.functions3 = functions3;
    }

    public String getNumber3() {
        return number3;
    }

    public void setNumber3(String number3) {
        this.number3 = number3;
    }

    public String getType3() {
        return type3;
    }

    public void setType3(String type3) {
        this.type3 = type3;
    }

    public String getAssessment3() {
        return assessment3;
    }

    public void setAssessment3(String assessment3) {
        this.assessment3 = assessment3;
    }

    public String getTargetValue3() {
        return targetValue3;
    }

    public void setTargetValue3(String targetValue3) {
        this.targetValue3 = targetValue3;
    }

    public String getReflect3() {
        return reflect3;
    }

    public void setReflect3(String reflect3) {
        this.reflect3 = reflect3;
    }

    public Long getGambling3() {
        return gambling3;
    }

    public void setGambling3(Long gambling3) {
        this.gambling3 = gambling3;
    }

    public Long getUnGambling3() {
        return unGambling3;
    }

    public void setUnGambling3(Long unGambling3) {
        this.unGambling3 = unGambling3;
    }

    public Boolean getReach3() {
        return isReach3;
    }

    public void setReach3(Boolean reach3) {
        isReach3 = reach3;
    }

    public String getRequestSide3() {
        return requestSide3;
    }

    public void setRequestSide3(String requestSide3) {
        this.requestSide3 = requestSide3;
    }

    public String getGamblingSide3() {
        return gamblingSide3;
    }

    public void setGamblingSide3(String gamblingSide3) {
        this.gamblingSide3 = gamblingSide3;
    }

    public String getRule3() {
        return rule3;
    }

    public void setRule3(String rule3) {
        this.rule3 = rule3;
    }

    public Boolean getHasConnet4() {
        return hasConnet4;
    }

    public void setHasConnet4(Boolean hasConnet4) {
        this.hasConnet4 = hasConnet4;
    }

    public String getInformTimeNode4() {
        return informTimeNode4;
    }

    public void setInformTimeNode4(String informTimeNode4) {
        this.informTimeNode4 = informTimeNode4;
    }

    public String getNotificationForm4() {
        return notificationForm4;
    }

    public void setNotificationForm4(String notificationForm4) {
        this.notificationForm4 = notificationForm4;
    }

    public String getNotificationContent4() {
        return notificationContent4;
    }

    public void setNotificationContent4(String notificationContent4) {
        this.notificationContent4 = notificationContent4;
    }

    public String getTimeNode4() {
        return timeNode4;
    }

    public void setTimeNode4(String timeNode4) {
        this.timeNode4 = timeNode4;
    }

    public String getLetterForm4() {
        return letterForm4;
    }

    public void setLetterForm4(String letterForm4) {
        this.letterForm4 = letterForm4;
    }

    public String getContentTemplate4() {
        return contentTemplate4;
    }

    public void setContentTemplate4(String contentTemplate4) {
        this.contentTemplate4 = contentTemplate4;
    }

    public String getFunctions4() {
        return functions4;
    }

    public void setFunctions4(String functions4) {
        this.functions4 = functions4;
    }

    public String getNumber4() {
        return number4;
    }

    public void setNumber4(String number4) {
        this.number4 = number4;
    }

    public String getType4() {
        return type4;
    }

    public void setType4(String type4) {
        this.type4 = type4;
    }

    public String getAssessment4() {
        return assessment4;
    }

    public void setAssessment4(String assessment4) {
        this.assessment4 = assessment4;
    }

    public String getTargetValue4() {
        return targetValue4;
    }

    public void setTargetValue4(String targetValue4) {
        this.targetValue4 = targetValue4;
    }

    public String getReflect4() {
        return reflect4;
    }

    public void setReflect4(String reflect4) {
        this.reflect4 = reflect4;
    }

    public Long getGambling4() {
        return gambling4;
    }

    public void setGambling4(Long gambling4) {
        this.gambling4 = gambling4;
    }

    public Long getUnGambling4() {
        return unGambling4;
    }

    public void setUnGambling4(Long unGambling4) {
        this.unGambling4 = unGambling4;
    }

    public Boolean getReach4() {
        return isReach4;
    }

    public void setReach4(Boolean reach4) {
        isReach4 = reach4;
    }

    public String getRequestSide4() {
        return requestSide4;
    }

    public void setRequestSide4(String requestSide4) {
        this.requestSide4 = requestSide4;
    }

    public String getGamblingSide4() {
        return gamblingSide4;
    }

    public void setGamblingSide4(String gamblingSide4) {
        this.gamblingSide4 = gamblingSide4;
    }

    public String getRule4() {
        return rule4;
    }

    public void setRule4(String rule4) {
        this.rule4 = rule4;
    }

    public Boolean getHasConnet5() {
        return hasConnet5;
    }

    public void setHasConnet5(Boolean hasConnet5) {
        this.hasConnet5 = hasConnet5;
    }

    public String getInformTimeNode5() {
        return informTimeNode5;
    }

    public void setInformTimeNode5(String informTimeNode5) {
        this.informTimeNode5 = informTimeNode5;
    }

    public String getNotificationForm5() {
        return notificationForm5;
    }

    public void setNotificationForm5(String notificationForm5) {
        this.notificationForm5 = notificationForm5;
    }

    public String getNotificationContent5() {
        return notificationContent5;
    }

    public void setNotificationContent5(String notificationContent5) {
        this.notificationContent5 = notificationContent5;
    }

    public String getTimeNode5() {
        return timeNode5;
    }

    public void setTimeNode5(String timeNode5) {
        this.timeNode5 = timeNode5;
    }

    public String getLetterForm5() {
        return letterForm5;
    }

    public void setLetterForm5(String letterForm5) {
        this.letterForm5 = letterForm5;
    }

    public String getContentTemplate5() {
        return contentTemplate5;
    }

    public void setContentTemplate5(String contentTemplate5) {
        this.contentTemplate5 = contentTemplate5;
    }

    public String getFunctions5() {
        return functions5;
    }

    public void setFunctions5(String functions5) {
        this.functions5 = functions5;
    }

    public String getNumber5() {
        return number5;
    }

    public void setNumber5(String number5) {
        this.number5 = number5;
    }

    public String getType5() {
        return type5;
    }

    public void setType5(String type5) {
        this.type5 = type5;
    }

    public String getAssessment5() {
        return assessment5;
    }

    public void setAssessment5(String assessment5) {
        this.assessment5 = assessment5;
    }

    public String getTargetValue5() {
        return targetValue5;
    }

    public void setTargetValue5(String targetValue5) {
        this.targetValue5 = targetValue5;
    }

    public String getReflect5() {
        return reflect5;
    }

    public void setReflect5(String reflect5) {
        this.reflect5 = reflect5;
    }

    public Long getGambling5() {
        return gambling5;
    }

    public void setGambling5(Long gambling5) {
        this.gambling5 = gambling5;
    }

    public Long getUnGambling5() {
        return unGambling5;
    }

    public void setUnGambling5(Long unGambling5) {
        this.unGambling5 = unGambling5;
    }

    public Boolean getReach5() {
        return isReach5;
    }

    public void setReach5(Boolean reach5) {
        isReach5 = reach5;
    }

    public String getRequestSide5() {
        return requestSide5;
    }

    public void setRequestSide5(String requestSide5) {
        this.requestSide5 = requestSide5;
    }

    public String getGamblingSide5() {
        return gamblingSide5;
    }

    public void setGamblingSide5(String gamblingSide5) {
        this.gamblingSide5 = gamblingSide5;
    }

    public String getRule5() {
        return rule5;
    }

    public void setRule5(String rule5) {
        this.rule5 = rule5;
    }

    public Boolean getHasConnet6() {
        return hasConnet6;
    }

    public void setHasConnet6(Boolean hasConnet6) {
        this.hasConnet6 = hasConnet6;
    }

    public String getInformTimeNode6() {
        return informTimeNode6;
    }

    public void setInformTimeNode6(String informTimeNode6) {
        this.informTimeNode6 = informTimeNode6;
    }

    public String getNotificationForm6() {
        return notificationForm6;
    }

    public void setNotificationForm6(String notificationForm6) {
        this.notificationForm6 = notificationForm6;
    }

    public String getNotificationContent6() {
        return notificationContent6;
    }

    public void setNotificationContent6(String notificationContent6) {
        this.notificationContent6 = notificationContent6;
    }

    public String getTimeNode6() {
        return timeNode6;
    }

    public void setTimeNode6(String timeNode6) {
        this.timeNode6 = timeNode6;
    }

    public String getLetterForm6() {
        return letterForm6;
    }

    public void setLetterForm6(String letterForm6) {
        this.letterForm6 = letterForm6;
    }

    public String getContentTemplate6() {
        return contentTemplate6;
    }

    public void setContentTemplate6(String contentTemplate6) {
        this.contentTemplate6 = contentTemplate6;
    }

    public String getFunctions6() {
        return functions6;
    }

    public void setFunctions6(String functions6) {
        this.functions6 = functions6;
    }

    public String getNumber6() {
        return number6;
    }

    public void setNumber6(String number6) {
        this.number6 = number6;
    }

    public String getType6() {
        return type6;
    }

    public void setType6(String type6) {
        this.type6 = type6;
    }

    public String getAssessment6() {
        return assessment6;
    }

    public void setAssessment6(String assessment6) {
        this.assessment6 = assessment6;
    }

    public String getTargetValue6() {
        return targetValue6;
    }

    public void setTargetValue6(String targetValue6) {
        this.targetValue6 = targetValue6;
    }

    public String getReflect6() {
        return reflect6;
    }

    public void setReflect6(String reflect6) {
        this.reflect6 = reflect6;
    }

    public Long getGambling6() {
        return gambling6;
    }

    public void setGambling6(Long gambling6) {
        this.gambling6 = gambling6;
    }

    public Long getUnGambling6() {
        return unGambling6;
    }

    public void setUnGambling6(Long unGambling6) {
        this.unGambling6 = unGambling6;
    }

    public Boolean getReach6() {
        return isReach6;
    }

    public void setReach6(Boolean reach6) {
        isReach6 = reach6;
    }

    public String getRequestSide6() {
        return requestSide6;
    }

    public void setRequestSide6(String requestSide6) {
        this.requestSide6 = requestSide6;
    }

    public String getGamblingSide6() {
        return gamblingSide6;
    }

    public void setGamblingSide6(String gamblingSide6) {
        this.gamblingSide6 = gamblingSide6;
    }

    public String getRule6() {
        return rule6;
    }

    public void setRule6(String rule6) {
        this.rule6 = rule6;
    }

    public Boolean getHasConnet7() {
        return hasConnet7;
    }

    public void setHasConnet7(Boolean hasConnet7) {
        this.hasConnet7 = hasConnet7;
    }

    public String getInformTimeNode7() {
        return informTimeNode7;
    }

    public void setInformTimeNode7(String informTimeNode7) {
        this.informTimeNode7 = informTimeNode7;
    }

    public String getNotificationForm7() {
        return notificationForm7;
    }

    public void setNotificationForm7(String notificationForm7) {
        this.notificationForm7 = notificationForm7;
    }

    public String getNotificationContent7() {
        return notificationContent7;
    }

    public void setNotificationContent7(String notificationContent7) {
        this.notificationContent7 = notificationContent7;
    }

    public String getTimeNode7() {
        return timeNode7;
    }

    public void setTimeNode7(String timeNode7) {
        this.timeNode7 = timeNode7;
    }

    public String getLetterForm7() {
        return letterForm7;
    }

    public void setLetterForm7(String letterForm7) {
        this.letterForm7 = letterForm7;
    }

    public String getContentTemplate7() {
        return contentTemplate7;
    }

    public void setContentTemplate7(String contentTemplate7) {
        this.contentTemplate7 = contentTemplate7;
    }

    public String getFunctions7() {
        return functions7;
    }

    public void setFunctions7(String functions7) {
        this.functions7 = functions7;
    }

    public String getNumber7() {
        return number7;
    }

    public void setNumber7(String number7) {
        this.number7 = number7;
    }

    public String getType7() {
        return type7;
    }

    public void setType7(String type7) {
        this.type7 = type7;
    }

    public String getAssessment7() {
        return assessment7;
    }

    public void setAssessment7(String assessment7) {
        this.assessment7 = assessment7;
    }

    public String getTargetValue7() {
        return targetValue7;
    }

    public void setTargetValue7(String targetValue7) {
        this.targetValue7 = targetValue7;
    }

    public String getReflect7() {
        return reflect7;
    }

    public void setReflect7(String reflect7) {
        this.reflect7 = reflect7;
    }

    public Long getGambling7() {
        return gambling7;
    }

    public void setGambling7(Long gambling7) {
        this.gambling7 = gambling7;
    }

    public Long getUnGambling7() {
        return unGambling7;
    }

    public void setUnGambling7(Long unGambling7) {
        this.unGambling7 = unGambling7;
    }

    public Boolean getReach7() {
        return isReach7;
    }

    public void setReach7(Boolean reach7) {
        isReach7 = reach7;
    }

    public String getRequestSide7() {
        return requestSide7;
    }

    public void setRequestSide7(String requestSide7) {
        this.requestSide7 = requestSide7;
    }

    public String getGamblingSide7() {
        return gamblingSide7;
    }

    public void setGamblingSide7(String gamblingSide7) {
        this.gamblingSide7 = gamblingSide7;
    }

    public String getRule7() {
        return rule7;
    }

    public void setRule7(String rule7) {
        this.rule7 = rule7;
    }

    public Boolean getHasConnet8() {
        return hasConnet8;
    }

    public void setHasConnet8(Boolean hasConnet8) {
        this.hasConnet8 = hasConnet8;
    }

    public String getInformTimeNode8() {
        return informTimeNode8;
    }

    public void setInformTimeNode8(String informTimeNode8) {
        this.informTimeNode8 = informTimeNode8;
    }

    public String getNotificationForm8() {
        return notificationForm8;
    }

    public void setNotificationForm8(String notificationForm8) {
        this.notificationForm8 = notificationForm8;
    }

    public String getNotificationContent8() {
        return notificationContent8;
    }

    public void setNotificationContent8(String notificationContent8) {
        this.notificationContent8 = notificationContent8;
    }

    public String getTimeNode8() {
        return timeNode8;
    }

    public void setTimeNode8(String timeNode8) {
        this.timeNode8 = timeNode8;
    }

    public String getLetterForm8() {
        return letterForm8;
    }

    public void setLetterForm8(String letterForm8) {
        this.letterForm8 = letterForm8;
    }

    public String getContentTemplate8() {
        return contentTemplate8;
    }

    public void setContentTemplate8(String contentTemplate8) {
        this.contentTemplate8 = contentTemplate8;
    }

    public String getFunctions8() {
        return functions8;
    }

    public void setFunctions8(String functions8) {
        this.functions8 = functions8;
    }

    public String getNumber8() {
        return number8;
    }

    public void setNumber8(String number8) {
        this.number8 = number8;
    }

    public String getType8() {
        return type8;
    }

    public void setType8(String type8) {
        this.type8 = type8;
    }

    public String getAssessment8() {
        return assessment8;
    }

    public void setAssessment8(String assessment8) {
        this.assessment8 = assessment8;
    }

    public String getTargetValue8() {
        return targetValue8;
    }

    public void setTargetValue8(String targetValue8) {
        this.targetValue8 = targetValue8;
    }

    public String getReflect8() {
        return reflect8;
    }

    public void setReflect8(String reflect8) {
        this.reflect8 = reflect8;
    }

    public Long getGambling8() {
        return gambling8;
    }

    public void setGambling8(Long gambling8) {
        this.gambling8 = gambling8;
    }

    public Long getUnGambling8() {
        return unGambling8;
    }

    public void setUnGambling8(Long unGambling8) {
        this.unGambling8 = unGambling8;
    }

    public Boolean getReach8() {
        return isReach8;
    }

    public void setReach8(Boolean reach8) {
        isReach8 = reach8;
    }

    public String getRequestSide8() {
        return requestSide8;
    }

    public void setRequestSide8(String requestSide8) {
        this.requestSide8 = requestSide8;
    }

    public String getGamblingSide8() {
        return gamblingSide8;
    }

    public void setGamblingSide8(String gamblingSide8) {
        this.gamblingSide8 = gamblingSide8;
    }

    public String getRule8() {
        return rule8;
    }

    public void setRule8(String rule8) {
        this.rule8 = rule8;
    }

    public Boolean getHasConnet9() {
        return hasConnet9;
    }

    public void setHasConnet9(Boolean hasConnet9) {
        this.hasConnet9 = hasConnet9;
    }

    public String getInformTimeNode9() {
        return informTimeNode9;
    }

    public void setInformTimeNode9(String informTimeNode9) {
        this.informTimeNode9 = informTimeNode9;
    }

    public String getNotificationForm9() {
        return notificationForm9;
    }

    public void setNotificationForm9(String notificationForm9) {
        this.notificationForm9 = notificationForm9;
    }

    public String getNotificationContent9() {
        return notificationContent9;
    }

    public void setNotificationContent9(String notificationContent9) {
        this.notificationContent9 = notificationContent9;
    }

    public String getTimeNode9() {
        return timeNode9;
    }

    public void setTimeNode9(String timeNode9) {
        this.timeNode9 = timeNode9;
    }

    public String getLetterForm9() {
        return letterForm9;
    }

    public void setLetterForm9(String letterForm9) {
        this.letterForm9 = letterForm9;
    }

    public String getContentTemplate9() {
        return contentTemplate9;
    }

    public void setContentTemplate9(String contentTemplate9) {
        this.contentTemplate9 = contentTemplate9;
    }

    public String getFunctions9() {
        return functions9;
    }

    public void setFunctions9(String functions9) {
        this.functions9 = functions9;
    }

    public String getNumber9() {
        return number9;
    }

    public void setNumber9(String number9) {
        this.number9 = number9;
    }

    public String getType9() {
        return type9;
    }

    public void setType9(String type9) {
        this.type9 = type9;
    }

    public String getAssessment9() {
        return assessment9;
    }

    public void setAssessment9(String assessment9) {
        this.assessment9 = assessment9;
    }

    public String getTargetValue9() {
        return targetValue9;
    }

    public void setTargetValue9(String targetValue9) {
        this.targetValue9 = targetValue9;
    }

    public String getReflect9() {
        return reflect9;
    }

    public void setReflect9(String reflect9) {
        this.reflect9 = reflect9;
    }

    public Long getGambling9() {
        return gambling9;
    }

    public void setGambling9(Long gambling9) {
        this.gambling9 = gambling9;
    }

    public Long getUnGambling9() {
        return unGambling9;
    }

    public void setUnGambling9(Long unGambling9) {
        this.unGambling9 = unGambling9;
    }

    public Boolean getReach9() {
        return isReach9;
    }

    public void setReach9(Boolean reach9) {
        isReach9 = reach9;
    }

    public String getRequestSide9() {
        return requestSide9;
    }

    public void setRequestSide9(String requestSide9) {
        this.requestSide9 = requestSide9;
    }

    public String getGamblingSide9() {
        return gamblingSide9;
    }

    public void setGamblingSide9(String gamblingSide9) {
        this.gamblingSide9 = gamblingSide9;
    }

    public String getRule9() {
        return rule9;
    }

    public void setRule9(String rule9) {
        this.rule9 = rule9;
    }

    public Boolean getHasConnet0() {
        return hasConnet0;
    }

    public void setHasConnet0(Boolean hasConnet0) {
        this.hasConnet0 = hasConnet0;
    }

    public String getInformTimeNode0() {
        return informTimeNode0;
    }

    public void setInformTimeNode0(String informTimeNode0) {
        this.informTimeNode0 = informTimeNode0;
    }

    public String getNotificationForm0() {
        return notificationForm0;
    }

    public void setNotificationForm0(String notificationForm0) {
        this.notificationForm0 = notificationForm0;
    }

    public String getNotificationContent0() {
        return notificationContent0;
    }

    public void setNotificationContent0(String notificationContent0) {
        this.notificationContent0 = notificationContent0;
    }

    public String getTimeNode0() {
        return timeNode0;
    }

    public void setTimeNode0(String timeNode0) {
        this.timeNode0 = timeNode0;
    }

    public String getLetterForm0() {
        return letterForm0;
    }

    public void setLetterForm0(String letterForm0) {
        this.letterForm0 = letterForm0;
    }

    public String getContentTemplate0() {
        return contentTemplate0;
    }

    public void setContentTemplate0(String contentTemplate0) {
        this.contentTemplate0 = contentTemplate0;
    }

    public String getFunctions0() {
        return functions0;
    }

    public void setFunctions0(String functions0) {
        this.functions0 = functions0;
    }

    public String getNumber0() {
        return number0;
    }

    public void setNumber0(String number0) {
        this.number0 = number0;
    }

    public String getType0() {
        return type0;
    }

    public void setType0(String type0) {
        this.type0 = type0;
    }

    public String getAssessment0() {
        return assessment0;
    }

    public void setAssessment0(String assessment0) {
        this.assessment0 = assessment0;
    }

    public String getTargetValue0() {
        return targetValue0;
    }

    public void setTargetValue0(String targetValue0) {
        this.targetValue0 = targetValue0;
    }

    public String getReflect0() {
        return reflect0;
    }

    public void setReflect0(String reflect0) {
        this.reflect0 = reflect0;
    }

    public Long getGambling0() {
        return gambling0;
    }

    public void setGambling0(Long gambling0) {
        this.gambling0 = gambling0;
    }

    public Long getUnGambling0() {
        return unGambling0;
    }

    public void setUnGambling0(Long unGambling0) {
        this.unGambling0 = unGambling0;
    }

    public Boolean getReach0() {
        return isReach0;
    }

    public void setReach0(Boolean reach0) {
        isReach0 = reach0;
    }

    public String getRequestSide0() {
        return requestSide0;
    }

    public void setRequestSide0(String requestSide0) {
        this.requestSide0 = requestSide0;
    }

    public String getGamblingSide0() {
        return gamblingSide0;
    }

    public void setGamblingSide0(String gamblingSide0) {
        this.gamblingSide0 = gamblingSide0;
    }

    public String getRule0() {
        return rule0;
    }

    public void setRule0(String rule0) {
        this.rule0 = rule0;
    }
}