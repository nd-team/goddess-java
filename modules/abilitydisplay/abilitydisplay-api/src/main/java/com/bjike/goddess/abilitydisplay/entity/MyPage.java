package com.bjike.goddess.abilitydisplay.entity;

import com.bjike.goddess.abilitydisplay.bo.CompanyBO;

import java.io.Serializable;
import java.util.List;

public class MyPage implements Serializable {
    private Long totalElements;
    private Integer number;
    private List<CompanyBO> content;

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public List<CompanyBO> getContent() {
        return content;
    }

    public void setContent(List<CompanyBO> content) {
        this.content = content;
    }

}
