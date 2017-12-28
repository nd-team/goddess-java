package com.bjike.goddess.event.vo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.event.bo.FatherBO;

/**
 * app列表所需字段
 *
 * @Author: [lijuntao]
 * @Date: [2017-08-11 15:58]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class AppListDataVO {
    /**
     * id
     */
    private String id;
    /**
     * 头像
     */
    private String headPortrait;
    /**
     * 姓名
     */
    private String userName;

    /**
     * 所属部门
     */
    private String deparment;

    /**
     * 事件获取时间
     */
    private String getTime;
    /**
     * 事件内容
     */
    private String content;
    /**
     * 事件内容List
     */
    private String[] contents;
    /**
     * 父表所属模块
     */
    private FatherBO fatherBO;
    /**
     * 状态
     */
    private String status;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDeparment() {
        return deparment;
    }

    public void setDeparment(String deparment) {
        this.deparment = deparment;
    }

    public String getGetTime() {
        return getTime;
    }

    public void setGetTime(String getTime) {
        this.getTime = getTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String[] getContents() {
        return contents;
    }

    public void setContents(String[] contents) {
        this.contents = contents;
    }

    public FatherBO getFatherBO() {
        return fatherBO;
    }

    public void setFatherBO(FatherBO fatherBO) {
        this.fatherBO = fatherBO;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
