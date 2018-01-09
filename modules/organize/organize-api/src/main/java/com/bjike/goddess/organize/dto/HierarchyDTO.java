package com.bjike.goddess.organize.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 体系数据传输
 *
 * @Author: [dengjunren]
 * @Date: [2017-03-08 16:58]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class HierarchyDTO extends BaseDTO {
    @Override
    public String toString() {
        return "HierarchyDTO{" +
                "limit=" + limit +
                ", page=" + page +
                ", sorts=" + sorts +
                ", conditions=" + conditions +
                ", conditionsJson='" + conditionsJson + '\'' +
                '}';
    }
}
