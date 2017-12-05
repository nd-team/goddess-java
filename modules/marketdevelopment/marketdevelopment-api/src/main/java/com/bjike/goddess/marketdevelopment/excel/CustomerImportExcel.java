package com.bjike.goddess.marketdevelopment.excel;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;

/**
 * 客户接触阶段业务传输对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-29 09:37 ]
 * @Description: [ 客户接触阶段业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CustomerImportExcel extends BaseBO {

//    /**
//     * 阶段编码
//     */
//    private String code;

    /**
     * 阶段
     */
    @ExcelHeader(name = "阶段", notNull = true)
    private String stage;

    /**
     * 定义
     */
    @ExcelHeader(name = "定义")
    private String define;

    /**
     * 输出结果
     */
    @ExcelHeader(name = "输出结果")
    private String results;

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getDefine() {
        return define;
    }

    public void setDefine(String define) {
        this.define = define;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }
}