package com.bjike.goddess.financeinit.vo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.financeinit.enums.BalanceDirection;
import com.bjike.goddess.financeinit.enums.CategoryName;

/**
 * 会计科目业务传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 02:40 ]
 * @Description: [ 会计科目业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CourseDateVO {

    /**
     * 所属类别
     */
    private CategoryName belongCategory;

    /**
     * 余额方向
     */
    private BalanceDirection balanceDirection;

    public CategoryName getBelongCategory() {
        return belongCategory;
    }

    public void setBelongCategory(CategoryName belongCategory) {
        this.belongCategory = belongCategory;
    }

    public BalanceDirection getBalanceDirection() {
        return balanceDirection;
    }

    public void setBalanceDirection(BalanceDirection balanceDirection) {
        this.balanceDirection = balanceDirection;
    }
}