package com.bjike.goddess.financeinit.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.financeinit.enums.BalanceDirection;
import com.bjike.goddess.financeinit.enums.CategoryName;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 会计科目
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 02:40 ]
 * @Description: [ 会计科目 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AccountanCourseTO extends BaseTO {

    /**
     * 代码
     */
    @NotBlank(message = "代码不能为空",groups = {ADD.class, EDIT.class})
    private String code;

    /**
     * 会计科目名称
     */
    @NotBlank(message = "会计科目名称不能为空",groups = {ADD.class, EDIT.class})
    private String accountanName;

    /**
     * 所属类别
     */
    @NotNull(message = "所属类别不能为空",groups = {ADD.class, EDIT.class})
    private CategoryName belongCategory;

    /**
     * 余额方向
     */
    @NotNull(message = "余额方向不能为空",groups = {ADD.class, EDIT.class})
    private BalanceDirection balanceDirection;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAccountanName() {
        return accountanName;
    }

    public void setAccountanName(String accountanName) {
        this.accountanName = accountanName;
    }

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