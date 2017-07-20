package com.bjike.goddess.assistance.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 补助标准
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-13 09:29 ]
 * @Description: [ 补助标准 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AssistanceStandardTO extends BaseTO {

    public interface TestAdd{}

    /**
     * 补助类型名称
     */
    @NotBlank(groups = {AssistanceStandardTO.TestAdd.class} , message = "补助类型名称不能为空,有：电脑补助/高温补助/工龄补助/住宿补助")
    private String name;

    /**
     * 标准
     */
    private String standardForm;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStandardForm() {
        return standardForm;
    }

    public void setStandardForm(String standardForm) {
        this.standardForm = standardForm;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
}