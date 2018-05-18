package com.bjike.goddess.balancecard.to;

import com.bjike.goddess.balancecard.enums.SeparateStatus;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 年度指标设置
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-19 09:11 ]
 * @Description: [ 年度指标设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class YearIndexSetTO extends BaseTO {
    public interface TestAdd {
    }

    public interface TestSer {
    }

    /**
     * 指标名称
     */
    @NotBlank(groups = {YearIndexSetTO.TestAdd.class}, message = "指标名称不能为空")
    private String indexName;

    /**
     * 年度指标编号
     */
    private Integer indexNumber;


    /**
     * 年份
     */
    @NotNull(groups = {YearIndexSetTO.TestAdd.class}, message = "年份名称不能为空")
    private Integer year;

    /**
     * 指标类型
     */
    private String indexType;

    /**
     * 维度
     */
    private String dimension;

    /**
     * 指标权重
     */
    @NotNull(groups = {YearIndexSetTO.TestAdd.class}, message = "指标权重不能为空")
    private Double describtion;

    /**
     * 年度目标值
     */
    @NotNull(groups = {YearIndexSetTO.TestAdd.class}, message = "年度目标值不能为空")
    private Double yearTarget;

    /**
     * 完成值
     */
    private Double complete;

    /**
     * 数据来源
     */
    private String dataOrigin;

    /**
     * 考核周期
     */
    private String examDuring;

    /**
     * 年度指标添加人
     */
    private String yearPersion;


    /**
     * 年度指标添加时间
     */
    private String yearIndexTime;

    /**
     * 被分解状态
     */
    private SeparateStatus separateStatus;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;

    /**
     * 部门项目组分解数据
     */
    @NotNull(groups = {YearIndexSetTO.TestSer.class}, message = "部门项目组分解数据不能为空")
    private List<DepartSerperateTO> departSerperateTOS;

    /**
     * 是否重新分解
     */
    private Boolean ifAgain;


    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getIndexType() {
        return indexType;
    }

    public void setIndexType(String indexType) {
        this.indexType = indexType;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public Double getDescribtion() {
        return describtion;
    }

    public void setDescribtion(Double describtion) {
        this.describtion = describtion;
    }

    public Double getYearTarget() {
        return yearTarget;
    }

    public void setYearTarget(Double yearTarget) {
        this.yearTarget = yearTarget;
    }

    public Double getComplete() {
        return complete;
    }

    public void setComplete(Double complete) {
        this.complete = complete;
    }

    public String getDataOrigin() {
        return dataOrigin;
    }

    public void setDataOrigin(String dataOrigin) {
        this.dataOrigin = dataOrigin;
    }

    public String getExamDuring() {
        return examDuring;
    }

    public void setExamDuring(String examDuring) {
        this.examDuring = examDuring;
    }

    public String getYearPersion() {
        return yearPersion;
    }

    public void setYearPersion(String yearPersion) {
        this.yearPersion = yearPersion;
    }


    public String getYearIndexTime() {
        return yearIndexTime;
    }

    public void setYearIndexTime(String yearIndexTime) {
        this.yearIndexTime = yearIndexTime;
    }

    public SeparateStatus getSeparateStatus() {
        return separateStatus;
    }

    public void setSeparateStatus(SeparateStatus separateStatus) {
        this.separateStatus = separateStatus;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public List<DepartSerperateTO> getDepartSerperateTOS() {
        return departSerperateTOS;
    }

    public void setDepartSerperateTOS(List<DepartSerperateTO> departSerperateTOS) {
        this.departSerperateTOS = departSerperateTOS;
    }

    public Integer getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(Integer indexNumber) {
        this.indexNumber = indexNumber;
    }

    public Boolean getIfAgain() {
        return ifAgain;
    }

    public void setIfAgain(Boolean ifAgain) {
        this.ifAgain = ifAgain;
    }
}