package com.bjike.goddess.festival.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;

/**
 * 注意事项业务传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-01 09:19 ]
 * @Description: [ 注意事项业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class NoticeThingBO extends BaseBO {

    /**
     * 注意事项名
     */
    private String name;

    /**
     * 描述
     */
    private String describeDetail;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribeDetail() {
        return describeDetail;
    }

    public void setDescribeDetail(String describeDetail) {
        this.describeDetail = describeDetail;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }


}