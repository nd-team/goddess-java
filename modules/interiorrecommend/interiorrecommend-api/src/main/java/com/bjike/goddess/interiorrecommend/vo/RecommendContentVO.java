package com.bjike.goddess.interiorrecommend.vo;

/**
 * 推荐内容表现层对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-10 02:51 ]
 * @Description: [ 推荐内容表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class RecommendContentVO {

    /**
     * id
     */
    private String id;

    /**
     * 推荐信息id
     */
    private String infoId;

    /**
     * 推荐内容
     */
    private String content;

    /**
     * 内容明细
     */
    private String detail;


    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

}