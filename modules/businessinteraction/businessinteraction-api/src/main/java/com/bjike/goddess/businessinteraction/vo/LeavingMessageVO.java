package com.bjike.goddess.businessinteraction.vo;

/**
 * 留言表现层对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-28 03:11 ]
 * @Description: [ 留言表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class LeavingMessageVO {

    /**
     * id
     */
    private String id;
    /**
     * 姓名
     */
    private String name;

    /**
     * 联系方式
     */
    private String contactWay;

    /**
     * 留言内容
     */
    private String contactContent;

    /**
     * 互动联系信息id
     */
    private String interactionId;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;

    /**
     * 互动联系信息
     */
    private InteractionRelationVO interactionRelationVO;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactWay() {
        return contactWay;
    }

    public void setContactWay(String contactWay) {
        this.contactWay = contactWay;
    }

    public String getContactContent() {
        return contactContent;
    }

    public void setContactContent(String contactContent) {
        this.contactContent = contactContent;
    }

    public String getInteractionId() {
        return interactionId;
    }

    public void setInteractionId(String interactionId) {
        this.interactionId = interactionId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public InteractionRelationVO getInteractionRelationVO() {
        return interactionRelationVO;
    }

    public void setInteractionRelationVO(InteractionRelationVO interactionRelationVO) {
        this.interactionRelationVO = interactionRelationVO;
    }


}