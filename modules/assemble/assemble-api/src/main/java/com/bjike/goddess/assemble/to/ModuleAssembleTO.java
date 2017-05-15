package com.bjike.goddess.assemble.to;

import com.bjike.goddess.assemble.type.CheckType;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 模块关联
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-05-12 04:42 ]
 * @Description: [ 模块关联 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ModuleAssembleTO extends BaseTO {

    /**
     * 模块id
     */
    @NotBlank(message = "模块名不能为空", groups = {ADD.class, EDIT.class})
    private String moduleName;

    /**
     * 关联模块id
     */
    @NotBlank(message = "关联模块名不能为空", groups = {ADD.class, EDIT.class})
    private String relationName;

    private CheckType checkType;

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getRelationName() {
        return relationName;
    }

    public void setRelationName(String relationName) {
        this.relationName = relationName;
    }

    public CheckType getCheckType() {
        return checkType;
    }

    public void setCheckType(CheckType checkType) {
        this.checkType = checkType;
    }
}