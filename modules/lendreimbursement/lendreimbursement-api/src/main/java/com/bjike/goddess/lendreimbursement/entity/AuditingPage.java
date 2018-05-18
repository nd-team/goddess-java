package com.bjike.goddess.lendreimbursement.entity;

import java.io.Serializable;
import java.util.List;

public class AuditingPage implements Serializable {
    /**
     * 总条数
     */
    private Long totalElements;
    /**
     * 当前页数
     */
    private Integer number;
    /**
     * 公司
     */
    private List<Auditing> content;

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

    public List getContent() {
        return content;
    }

    public void setContent(List content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "MyPage{" +
                "totalElements=" + totalElements +
                ", number=" + number +
                ", content=" + content +
                '}';
    }
}
