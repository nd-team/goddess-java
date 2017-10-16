package com.bjike.goddess.financeinit.excel;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.financeinit.enums.BalanceDirection;
import com.bjike.goddess.financeinit.enums.CategoryName;


/**
 * 会计科目导出模板
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 02:40 ]
 * @Description: [ 会计科目导出模板 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AccountanCourseExportTemple extends BaseBO {

    /**
     * 代码
     */
    @ExcelHeader(name="代码",notNull = true)
    private String code;

    /**
     * 会计科目名称
     */
    @ExcelHeader(name="会计科目名称",notNull = true)
    private String accountanName;

    /**
     * 所属类别
     */
    @ExcelHeader(name="所属类别",notNull = true)
    private String belongCategory;

    /**
     * 余额方向
     */
    @ExcelHeader(name="余额方向",notNull = true)
    private String balanceDirection;

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

    public String getBelongCategory() {
        return belongCategory;
    }

    public void setBelongCategory(String belongCategory) {
        this.belongCategory = belongCategory;
    }

    public String getBalanceDirection() {
        return balanceDirection;
    }

    public void setBalanceDirection(String balanceDirection) {
        this.balanceDirection = balanceDirection;
    }
}