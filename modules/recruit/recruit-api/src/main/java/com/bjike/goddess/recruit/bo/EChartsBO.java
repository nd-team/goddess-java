package com.bjike.goddess.recruit.bo;
import java.io.Serializable;

public class EChartsBO implements Serializable{
    private String name;
    private Integer[] dates;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer[] getDates() {
        return dates;
    }

    public void setDates(Integer[] dates) {
        this.dates = dates;
    }
}
