package com.bjike.goddess.dataprogress.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 汇总
 *
 * @Author: [xiazhili]
 * @Date: [2017-09-29 10:13]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CollectBO extends BaseBO {
    /**
     * 专业方向
     */
    private String majorDirection;

    /**
     * 商务需求
     */
    private Integer business;

    /**
     * 项目需求
     */
    private Integer project;

    /**
     * 公司管理需求
     */
    private Integer administer;

    /**
     * 公司发展需求
     */
    private Integer develop;

    /**
     * 个人发展需求
     */
    private Integer personDevelop;

    /**
     * 资料总目标数量认证
     */
    private Integer targetApprove;

    /**
     * 资料总目标数量实际
     */
    private Integer targetReality;
    /**
     * 未收集资料总数量认证
     */
    private Integer notCollectApprove;

    /**
     * 未收集资料总数量实际
     */
    private Integer notCollectReality;

    /**
     * 已收集资料总数量认证
     */
    private Integer haveCollectNumApprove;

    /**
     * 已收集资料总数量实际
     */
    private Integer haveCollectNumReality;

    /**
     * 已收集资料总课时认证
     */
    private Integer haveCollectHourApprove;

    /**
     * 已收集资料总课时实际
     */
    private Integer haveCollectHourReality;

    /**
     * 已收集认证资料已收到合格资料
     */
    private Integer qualifiedApprove;
    /**
     * 已收集认证资料已收到不合格资料
     */
    private Integer notQualifiedApprove;

    /**
     * 已收集认证资料教案
     */
    private Integer grammarApprove;

    /**
     * 已收集认证资料考题
     */
    private Integer questionApprove;

    /**
     * 已收集认证资料答案
     */
    private Integer answerApprove;

    /**
     * 已收集实际资料已收到合格资料
     */
    private Integer qualifiedReality;

    /**
     * 已收集实际资料已收到不合格资料
     */
    private Integer notQualifiedReality;

    /**
     * 已收集实际资料教案
     */
    private Integer grammarReality;
    /**
     * 已收集实际资料考题
     */
    private Integer questionReality;
    /**
     * 已收集实际资料答案
     */
    private Integer answerReality;
    /**
     * 仍需收集的资料总数量认证
     */
    private Integer needApprove;
    /**
     * 仍需收集的资料总数量实际
     */
    private Integer needReality;
}
