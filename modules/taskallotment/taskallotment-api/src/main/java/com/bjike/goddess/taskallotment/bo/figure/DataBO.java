package com.bjike.goddess.taskallotment.bo.figure;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * @Author: [chenjunhao]
 * @Date: [2017-11-04 11:35]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DataBO extends BaseBO{
    private String[] strings;
    private List<PersonLastBO1> data;

    public String[] getStrings() {
        return strings;
    }

    public void setStrings(String[] strings) {
        this.strings = strings;
    }

    public List<PersonLastBO1> getData() {
        return data;
    }

    public void setData(List<PersonLastBO1> data) {
        this.data = data;
    }
}
