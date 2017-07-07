package com.bjike.goddess.reportmanagement.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.reportmanagement.enums.Form;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 对应的公式
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-20 09:56 ]
 * @Description: [ 对应的公式 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FormulaTO extends BaseTO {
    public interface A {
    }

    /**
     * 科目
     */
    @NotBlank(groups = {ADD.class, EDIT.class, FormulaTO.A.class}, message = "科目不能为空")
    private String project;

    /**
     * 公式方向
     */
    @NotNull(groups = {ADD.class, EDIT.class, FormulaTO.A.class}, message = "公式方向不能为空")
    private Form form;

    /**
     * 对应科目的id
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "对应科目的id不能为空")
    private String foreignId;

    /**
     * 运算方式
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "类型")
    private String type1;

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getForeignId() {
        return foreignId;
    }

    public void setForeignId(String foreignId) {
        this.foreignId = foreignId;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }
}