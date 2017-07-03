package com.bjike.goddess.stockholdermeeting.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import com.bjike.goddess.common.api.entity.ADD;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 导出excel条件
 *
 * @Author: [chenjunhao]
 * @Date: [2017-06-09 14:22]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ExcelDTO extends BaseDTO {
    /**
     * 会议纪要id
     */
    @NotBlank(groups = {ADD.class}, message = "会议纪要id不能为空")
    private String id;
    /**
     * 会议纪要子表id
     */
    private String summarySonId;

    public String getId() {
        return id;
    }

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
