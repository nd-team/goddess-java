package com.bjike.goddess.moneyside.to;

/**
 * Created by ike on 17-6-10.
 */
public class InvestDateTO {
    public interface Collect {
    }
    /**
     * 投资开始日期
     */
    private String startDate;
    /**
     * 投资结束日期
     */
    private String endDate;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
