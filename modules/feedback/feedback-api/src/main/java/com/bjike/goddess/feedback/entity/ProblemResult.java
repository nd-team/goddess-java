package com.bjike.goddess.feedback.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;


/**
 * 问题处理结果
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-02 07:02 ]
 * @Description: [ 问题处理结果 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "feedback_problemresult")
public class ProblemResult extends BaseEntity {

    /**
     * 最终解决方案
     */
    @Column(name = "finalSolution",  columnDefinition = "VARCHAR(255)   COMMENT '最终解决方案'")
    private String finalSolution;

    /**
     * 最终解决方案确定时间
     */
    @Column(name = "finalSolutionTime", columnDefinition = "DATETIME   COMMENT '最终解决方案确定时间'")
    private LocalDateTime finalSolutionTime;

    /**
     * 问题解决时间
     */
    @Column(name = "problemSolveTime",  columnDefinition = "DATETIME   COMMENT '问题解决时间'")
    private LocalDateTime problemSolveTime;

    /**
     * 当事人是否确认处理完成
     */
    @Column(name = "partyFinish", columnDefinition = "VARCHAR(255)   COMMENT '当事人是否确认处理完成'")
    private String partyFinish;

    /**
     * 当事人确认意见
     */
    @Column(name = "partyIdea", columnDefinition = "VARCHAR(255)   COMMENT '当事人确认意见'")
    private String partyIdea;

    /**
     * 其他模块意见数
     */
    @Column(  columnDefinition = "VARCHAR(255)   COMMENT '其他模块意见数'")
    private Integer otherIdeaNum;

    /**
     * 是否需要协调
     */
    @Column(name = "is_coordinate", columnDefinition = "TINYINT(1)  COMMENT '是否需要协调'")
    private Boolean coordinate;

    /**
     * 协调结果
     */
    @Column(name = "coordinateResult",  columnDefinition = "VARCHAR(255)   COMMENT '协调结果'")
    private String coordinateResult;

    /**
     * 问题处理结果
     */
    @Column(name = "problemResult",  columnDefinition = "VARCHAR(255)   COMMENT '问题处理结果'")
    private String problemResult;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "receivedFeedback_id", nullable = false,unique = true,columnDefinition = "VARCHAR(36)   COMMENT '已受理的反馈'")
    private ReceivedFeedback receivedFeedback;

    public ReceivedFeedback getReceivedFeedback() {
        return receivedFeedback;
    }

    public void setReceivedFeedback(ReceivedFeedback receivedFeedback) {
        this.receivedFeedback = receivedFeedback;
    }

    public String getFinalSolution() {
        return finalSolution;
    }

    public void setFinalSolution(String finalSolution) {
        this.finalSolution = finalSolution;
    }

    public LocalDateTime getFinalSolutionTime() {
        return finalSolutionTime;
    }

    public void setFinalSolutionTime(LocalDateTime finalSolutionTime) {
        this.finalSolutionTime = finalSolutionTime;
    }

    public LocalDateTime getProblemSolveTime() {
        return problemSolveTime;
    }

    public void setProblemSolveTime(LocalDateTime problemSolveTime) {
        this.problemSolveTime = problemSolveTime;
    }

    public String getPartyFinish() {
        return partyFinish;
    }

    public void setPartyFinish(String partyFinish) {
        this.partyFinish = partyFinish;
    }

    public String getPartyIdea() {
        return partyIdea;
    }

    public void setPartyIdea(String partyIdea) {
        this.partyIdea = partyIdea;
    }

    public Integer getOtherIdeaNum() {
        return otherIdeaNum;
    }

    public void setOtherIdeaNum(Integer otherIdeaNum) {
        this.otherIdeaNum = otherIdeaNum;
    }

    public Boolean getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Boolean coordinate) {
        this.coordinate = coordinate;
    }

    public String getCoordinateResult() {
        return coordinateResult;
    }

    public void setCoordinateResult(String coordinateResult) {
        this.coordinateResult = coordinateResult;
    }

    public String getProblemResult() {
        return problemResult;
    }

    public void setProblemResult(String problemResult) {
        this.problemResult = problemResult;
    }
}