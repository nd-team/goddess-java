package com.bjike.goddess.assemble.to;

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
    @NotBlank(message = "模块id不能为空", groups = {ADD.class, EDIT.class})
    private String moduleId;

    /**
     * 关联模块id
     */
    @NotBlank(message = "关联模块id不能为空", groups = {ADD.class, EDIT.class})
    private String relationId;

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }
}