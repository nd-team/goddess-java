package com.bjike.goddess.feedback.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
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
    @Column(name = "finalSolution", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '最终解决方案'")
    private String finalSolution;

    /**
     * 最终解决方案确定时间
     */
    @Column(name = "finalSolutionTime", nullable = false, columnDefinition = "DATETIME   COMMENT '最终解决方案确定时间'")
    private LocalDateTime finalSolutionTime;

    /**
     * 问题解决时间
     */
    @Column(name = "problemSolveTime", nullable = false, columnDefinition = "DATETIME   COMMENT '问题解决时间'")
    private LocalDateTime problemSolveTime;

    /**
     * 当事人是否确认处理完成
     */
    @Column(name = "partyFinish", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '当事人是否确认处理完成'")
    private String partyFinish;

    /**
     * 当事人确认意见
     */
    @Column(name = "partyIdea", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '当事人确认意见'")
    private String partyIdea;

    /**
     * 其他模块意见数
     */
    @Column( nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '其他模块意见数'")
    private Integer otherIdeaNum;

    /**
     * 是否需要协调
     */
    @Column(name = "is_coordinate", nullable = false, columnDefinition = "TINYINT(1)  COMMENT '是否需要协调'")
    private Boolean coordinate;

    /**
     * 协调结果
     */
    @Column(name = "coordinateResult", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '协调结果'")
    private String coordinateResult;

    /**
     * 问题处理结果
     */
    @Column(name = "problemResult", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '问题处理结果'")
    private String problemResult;


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