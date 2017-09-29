package com.bjike.goddess.task.bo.custmize;

import java.util.List;

/**定制汇总项目
 * @Author: [liguiqin]
 * @Date: [2017-09-28 15:14]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CustomProject {
    private String name; //项目名
    private List<CustomTable> tables; //包含表

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CustomTable> getTables() {
        return tables;
    }

    public void setTables(List<CustomTable> tables) {
        this.tables = tables;
    }
}
