package com.bjike.goddess.festival.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 节假日礼品标准
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-01 09:02 ]
 * @Description: [ 节假日礼品标准 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class GiftStandardTO extends BaseTO {

    public interface TESTAddAndEdit{}
    /**
     * 礼品名
     */
    @NotBlank(groups = GiftStandardTO.TESTAddAndEdit.class ,message = "礼品名不能为空")
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