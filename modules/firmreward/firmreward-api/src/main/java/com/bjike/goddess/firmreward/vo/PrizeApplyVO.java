package com.bjike.goddess.firmreward.vo;

/**
 * 奖品申请表现层对象
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-13 09:04 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PrizeApplyVO {

    /**
     * id
     */
    private String id;
    /**
     * 奖励项目名称
     */
    private String awardItemName;

    /**
     * 员工姓名
     */
    private String staffName;

    /**
     * 项目组
     */
    private String projectTeam;

    /**
     * 获奖等级
     */
    private String awardGrade;

    /**
     * 奖品注意事项
     */
    private String awardNotes;

    /**
     * 运营财务部审批
     */
    private String yYFinanceApproval;

    /**
     * 总经办审批
     */
    private String zjbApproval;

    /**
     * 特殊情况备注
     */
    private String comment;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAwardItemName() {
        return awardItemName;
    }

    public void setAwardItemName(String awardItemName) {
        this.awardItemName = awardItemName;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getProjectTeam() {
        return projectTeam;
    }

    public void setProjectTeam(String projectTeam) {
        this.projectTeam = projectTeam;
    }

    public String getAwardGrade() {
        return awardGrade;
    }

    public void setAwardGrade(String awardGrade) {
        this.awardGrade = awardGrade;
    }

    public String getAwardNotes() {
        return awardNotes;
    }

    public void setAwardNotes(String awardNotes) {
        this.awardNotes = awardNotes;
    }

    public String getYYFinanceApproval() {
        return yYFinanceApproval;
    }

    public void setYYFinanceApproval(String yYFinanceApproval) {
        this.yYFinanceApproval = yYFinanceApproval;
    }

    public String getZjbApproval() {
        return zjbApproval;
    }

    public void setZjbApproval(String zjbApproval) {
        this.zjbApproval = zjbApproval;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}