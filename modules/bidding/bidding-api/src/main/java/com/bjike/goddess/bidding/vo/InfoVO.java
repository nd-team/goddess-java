package com.bjike.goddess.bidding.vo;

/**
 * @Author: [xiazhili]
 * @Date: [2017-10-11 08:40]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class InfoVO {
    /**
     * 标题
     */
    private String title;
    /**
     * 时间
     */
    private String date;
    /**
     * id
     */
    private String id;

    /**
     * 链接
     */
    private String link;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
