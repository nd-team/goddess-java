package com.bjike.goddess.bidding.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.to.BaseTO;

/**
 * @Author: [xiazhili]
 * @Date: [2017-10-11 08:40]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class InfoBO extends BaseBO {
    public InfoBO(String title, String date, String id) {
        this.title = title;
        this.date = date;
        this.id = id;
    }

    /**
     * 标题
     */
    private String title;
    /**
     * 时间
     */
    private String date;
    /*
     * 链接
     */
    private String link;

    /**
     * 总条数
     */
    private Integer total;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
