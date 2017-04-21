package com.bjike.goddess.socialfee.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 社会缴费
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-19 03:25 ]
 * @Description: [ 社会缴费 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class VoucherDataTO implements Serializable {

    /**
     * 日期
     */
    @NotBlank(message = "日期不能为空")
    private String vDate;

    /**
     * 摘要
     */
    @NotBlank(message = "摘要不能为空")
    private String sumary;

    /**
     * 会计科目数组
     */
    @NotNull(message = "会计科目数组不能为空")
    private List<String> subjects;

    /**
     * 借方金额数组
     */
    @NotNull(message = "借方金额数组不能为空")
    private List<Double> borrowMoneys;

    /**
     * 贷方金额数组
     */
    @NotNull(message = "贷方金额数组不能为空")
    private List<Double> loanMoneys;

    /**
     * 地区
     */
    @NotBlank(message = "地区不能为空")
    private String areas;
    /**
     * 项目数组
     */
    @NotBlank(message = "项目不能为空")
    private String projects;
    /**
     * 项目组
     */
    @NotBlank(message = "项目组不能为空")
    private String groups;

    public String getvDate() {
        return vDate;
    }

    public void setvDate(String vDate) {
        this.vDate = vDate;
    }

    public String getSumary() {
        return sumary;
    }

    public void setSumary(String sumary) {
        this.sumary = sumary;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public List<Double> getBorrowMoneys() {
        return borrowMoneys;
    }

    public void setBorrowMoneys(List<Double> borrowMoneys) {
        this.borrowMoneys = borrowMoneys;
    }

    public List<Double> getLoanMoneys() {
        return loanMoneys;
    }

    public void setLoanMoneys(List<Double> loanMoneys) {
        this.loanMoneys = loanMoneys;
    }

    public String getAreas() {
        return areas;
    }

    public void setAreas(String areas) {
        this.areas = areas;
    }

    public String getProjects() {
        return projects;
    }

    public void setProjects(String projects) {
        this.projects = projects;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }
}