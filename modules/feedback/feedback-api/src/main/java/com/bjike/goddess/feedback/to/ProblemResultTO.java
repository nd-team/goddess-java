package com.bjike.goddess.feedback.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.feedback.entity.ProblemResult;
import com.bjike.goddess.feedback.service.ProblemResultSer;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 问题处理结果
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-02 07:02 ]
 * @Description: [ 问题处理结果 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProblemResultTO extends BaseTO {
    public interface TestParty{}
    public interface TestCoordinate{}
    public interface TestResult{}

    /**
     * 最终解决方案
     */
    private String finalSolution;

    /**
     * 最终解决方案确定时间
     */
    private String finalSolutionTime;

    /**
     * 问题解决时间
     */
    private String problemSolveTime;

    /**
     * 当事人是否确认处理完成
     */
    @NotBlank(message = "当事人是否确认处理完成不能为空",groups = {ProblemResultTO.TestParty.class})
    private String partyFinish;

    /**
     * 当事人确认意见
     */
    @NotBlank(message = "当事人确认意见不能为空",groups = {ProblemResultTO.TestParty.class})
    private String partyIdea;

    /**
     * 其他模块意见数
     */
    private Integer otherIdeaNum;

    /**
     * 是否需要协调
     */
    @NotNull(message = "是否需要协调不能为空",groups = {ProblemResultTO.TestCoordinate.class})
    private Boolean coordinate;

    /**
     * 协调结果
     */
    @NotBlank(message = "协调结果不能为空",groups = {ProblemResultTO.TestResult.class})
    private String coordinateResult;

    /**
     * 问题处理结果
     */
    private String problemResult;


    public String getFinalSolution() {
        return finalSolution;
    }

    public void setFinalSolution(String finalSolution) {
        this.finalSolution = finalSolution;
    }

    public String getFinalSolutionTime() {
        return finalSolutionTime;
    }

    public void setFinalSolutionTime(String finalSolutionTime) {
        this.finalSolutionTime = finalSolutionTime;
    }

    public String getProblemSolveTime() {
        return problemSolveTime;
    }

    public void setProblemSolveTime(String problemSolveTime) {
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