package com.bjike.goddess.rotation.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import com.bjike.goddess.rotation.enums.CollectDetailsType;

/**
 *
 * @Author: [caiwenxian]
 * @Date: [2018-01-09 15:53]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class RotationCollectEchartDTO extends BaseDTO{


    /**
     * 岗位轮换详细类型
     */
    private CollectDetailsType collectDetailsType;

    /**
     * 地区
     */
    private String area;

    public RotationCollectEchartDTO() {

    }

    public RotationCollectEchartDTO(CollectDetailsType collectDetailsType, String area) {
        this.collectDetailsType = collectDetailsType;
        this.area = area;
    }

    @Override
    public String toString() {
        return "RotationCollectEchartDTO{" +
                "collectDetailsType=" + collectDetailsType +
                ", area='" + area + '\'' +
                '}';
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public CollectDetailsType getCollectDetailsType() {
        return collectDetailsType;
    }

    public void setCollectDetailsType(CollectDetailsType collectDetailsType) {
        this.collectDetailsType = collectDetailsType;
    }
}
