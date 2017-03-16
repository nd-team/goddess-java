package com.bjike.goddess.common.api.to;

import com.bjike.goddess.common.api.entity.DEL;
import com.bjike.goddess.common.api.entity.EDIT;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * 数据传输基础对象
 *
 * @Author: [liguiqin]
 * @Date: [2017-03-13 09:41]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public abstract class BaseTO implements Serializable {
    /**
     * 数据行id
     */
    @NotBlank(message = "id不能为空", groups = {EDIT.class, DEL.class})
    protected String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
