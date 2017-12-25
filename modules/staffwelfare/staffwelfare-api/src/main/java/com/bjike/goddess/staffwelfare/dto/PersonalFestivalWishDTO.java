package com.bjike.goddess.staffwelfare.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
/**
* 节日祝福语数据传输对象
* @Author:			[ Jason ]
* @Date:			[  2017-04-07 03:08 ]
* @Description:	[ 节日祝福语数据传输对象 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public class PersonalFestivalWishDTO extends BaseDTO {

    /**
     * 节日名称
     */
    private String festivalName;

    public String getFestivalName() {
        return festivalName;
    }

    public void setFestivalName(String festivalName) {
        this.festivalName = festivalName;
    }
}