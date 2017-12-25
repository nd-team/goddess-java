package com.bjike.goddess.royalty.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 指标库
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-10 11:23 ]
 * @Description: [ 指标库 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class IndexLibraryTO extends BaseTO {
    public interface TestAdd {
    }

    public interface TestEdit {
    }

    /**
     * 指标编号
     */
    @NotBlank(message = "指标编号不能为空", groups = {IndexLibraryTO.TestAdd.class, IndexLibraryTO.TestEdit.class})
    private String indexNum;

    /**
     * 指标维度
     */
    private String indexDimension;

    /**
     * 指标名称
     */
    @NotBlank(message = "指标名称不能为空", groups = {IndexLibraryTO.TestAdd.class, IndexLibraryTO.TestEdit.class})
    private String indexName;

    /**
     * 指标描述
     */
    private String indexDescription;

    /**
     * 意义
     */
    private String sense;

    /**
     * 考核部门
     */
    private String inspectionDepartment;

    /**
     * 适用岗位
     */
    private String forPost;

    /**
     * 数据来源
     */
    private String dataSource;
    /**
     * 是否被选用
     */
    private Boolean choose;
    /**
     * 对赌承诺-确认目标值
     */
    private String confirmTargetValue;
    /**
     * 达标状态
     */
    private String standard;

    public String getIndexNum() {
        return indexNum;
    }

    public void setIndexNum(String indexNum) {
        this.indexNum = indexNum;
    }

    public String getIndexDimension() {
        return indexDimension;
    }

    public void setIndexDimension(String indexDimension) {
        this.indexDimension = indexDimension;
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public String getIndexDescription() {
        return indexDescription;
    }

    public void setIndexDescription(String indexDescription) {
        this.indexDescription = indexDescription;
    }

    public String getSense() {
        return sense;
    }

    public void setSense(String sense) {
        this.sense = sense;
    }

    public String getInspectionDepartment() {
        return inspectionDepartment;
    }

    public void setInspectionDepartment(String inspectionDepartment) {
        this.inspectionDepartment = inspectionDepartment;
    }

    public String getForPost() {
        return forPost;
    }

    public void setForPost(String forPost) {
        this.forPost = forPost;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public Boolean getChoose() {
        return choose;
    }

    public void setChoose(Boolean choose) {
        this.choose = choose;
    }

    public String getConfirmTargetValue() {
        return confirmTargetValue;
    }

    public void setConfirmTargetValue(String confirmTargetValue) {
        this.confirmTargetValue = confirmTargetValue;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }
}