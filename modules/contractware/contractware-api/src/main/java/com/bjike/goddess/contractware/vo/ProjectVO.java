package com.bjike.goddess.contractware.vo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * Created by ike on 17-5-31.
 */
public class ProjectVO{
    /**
     * id
     */
    private String id;

    /**
     * 内部名称
     */
    private String internalName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInternalName() {
        return internalName;
    }

    public void setInternalName(String internalName) {
        this.internalName = internalName;
    }
}
