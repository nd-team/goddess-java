package com.bjike.goddess.organize.excel;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;

/**
 * 岗位工作明细表
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-03-29 03:57 ]
 * @Description: [ 岗位工作明细表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ModuleAndIndicatorExport extends BaseTO {

    /**
     * （对接）福利模块名
     */
    @ExcelHeader(name = "（对接）福利模块id", notNull = true)
    private String id;

    /**
     * （对接）规划模块名
     */
    @ExcelHeader(name = "（对接）规划模块名", notNull = true)
    private String name;
    /**
     * （对接）规划模块
     */
    @ExcelHeader(name = "（对接）规划模块", notNull = true)
    private Boolean hasConnet;

    /**
     * 指标序号
     */
    @ExcelHeader(name = "规划指标序号", notNull = true)
    private String number;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getHasConnet() {
        return hasConnet;
    }

    public void setHasConnet(Boolean hasConnet) {
        this.hasConnet = hasConnet;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }
}