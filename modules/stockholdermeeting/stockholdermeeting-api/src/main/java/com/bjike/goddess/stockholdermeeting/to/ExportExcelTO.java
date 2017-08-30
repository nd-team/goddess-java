package com.bjike.goddess.stockholdermeeting.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 导出条件
 *
 * @Author: [chenjunhao]
 * @Date: [2017-06-09 09:04]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ExportExcelTO extends BaseTO {
    /**
     * 会议纪要id
     */
    @NotBlank(groups = {ADD.class}, message = "会议纪要id不能为空")
    private String id;
    /**
     * 会议纪要子表id
     */
    private String summarySonId;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getSummarySonId() {
        return summarySonId;
    }

    public void setSummarySonId(String summarySonId) {
        this.summarySonId = summarySonId;
    }
}
