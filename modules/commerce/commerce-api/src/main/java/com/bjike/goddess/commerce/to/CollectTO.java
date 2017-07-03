package com.bjike.goddess.commerce.to;

import com.bjike.goddess.common.api.entity.EDIT;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * @Author: [dengjunren]
 * @Date: [2017-06-02 09:51]
 * @Description: [ 导出查询条件传输对象 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CollectTO implements Serializable {

    /**
     * 开始时间
     */
    @NotBlank(message = "开始时间不能为空", groups = EDIT.class)
    private String start;

    /**
     * 结束时间
     */
    @NotBlank(message = "结束时间不能为空", groups = EDIT.class)
    private String end;

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
