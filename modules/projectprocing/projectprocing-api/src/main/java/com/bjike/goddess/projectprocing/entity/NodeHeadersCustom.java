package com.bjike.goddess.projectprocing.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 节点表头定制
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 10:47 ]
 * @Description: [ 节点表头定制 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "projectprocing_nodeheaderscustom")
public class NodeHeadersCustom extends BaseEntity {

    /**
     * 外包单位
     */
    @Column(name = "outUnit" , columnDefinition = "VARCHAR(255)   COMMENT '外包单位'")
    private String outUnit;

    /**
     * 进度管理id
     */
    @Column(name = "prossManageId", columnDefinition = "VARCHAR(255)   COMMENT '进度管理id'")
    private String prossManageId;

    /**
     * 父级id
     */
    @Column(name = "fatherId", columnDefinition = "VARCHAR(255)   COMMENT '父级id'")
    private String fatherId;

    /**
     * 节点1名称
     */
    @Column(name = "nodeOneName" , columnDefinition = "VARCHAR(255)   COMMENT '节点1名称'")
    private String nodeOneName;

    /**
     * 节点1名称内容
     */
    @Column(name = "nodeOneNameContent", columnDefinition = "TINYINT(2)   COMMENT '节点1名称内容'")
    private Integer nodeOneNameContent;

    /**
     * 节点1表头
     */
    @Column(name = "nodeOneHeader" , columnDefinition = "VARCHAR(255)   COMMENT '节点1表头'")
    private String nodeOneHeader;

    /**
     * 节点1内容
     */
    @Column(name = "nodeOneContent", columnDefinition = "DATE   COMMENT '节点1内容'")
    private LocalDate nodeOneContent;

    /**
     * 对节点2间隔时间（单位：天）
     */
    @Column(name = "nodeTwoInterDate"  , columnDefinition = "INT(11)   COMMENT '对节点2间隔时间（单位：天）'")
    private Integer nodeTwoInterDate;

    /**
     * 节点2表头
     */
    @Column(name = "nodeTwoHeader" , columnDefinition = "VARCHAR(255)   COMMENT '节点2表头'")
    private String nodeTwoHeader;

    /**
     * 节点2内容
     */
    @Column(name = "nodeTwoContent", columnDefinition = "DATE   COMMENT '节点2内容'")
    private LocalDate nodeTwoContent;

    /**
     * 对节点3间隔时间（单位：天）
     */
    @Column(name = "nodeThreeInterDate"   , columnDefinition = "INT(11)   COMMENT '对节点3间隔时间（单位：天）'")
    private Integer nodeThreeInterDate;

    /**
     * 节点3表头
     */
    @Column(name = "nodeThreeHeader" , columnDefinition = "VARCHAR(255)   COMMENT '节点3表头'")
    private String nodeThreeHeader;

    /**
     * 节点3内容
     */
    @Column(name = "nodeThreeContent", columnDefinition = "DATE   COMMENT '节点3内容'")
    private LocalDate nodeThreeContent;

    /**
     * 对节点4间隔时间（单位：天）
     */
    @Column(name = "nodeFourInterDate" , columnDefinition = "INT(11)   COMMENT '对节点4间隔时间（单位：天）'")
    private Integer nodeFourInterDate;

    /**
     * 节点4表头
     */
    @Column(name = "nodeFourHeader" , columnDefinition = "VARCHAR(255)   COMMENT '节点4表头'")
    private String nodeFourHeader;

    /**
     * 节点4内容
     */
    @Column(name = "nodeFourContent", columnDefinition = "DATE   COMMENT '节点4内容'")
    private LocalDate nodeFourContent;


    public String getOutUnit() {
        return outUnit;
    }

    public void setOutUnit(String outUnit) {
        this.outUnit = outUnit;
    }

    public String getProssManageId() {
        return prossManageId;
    }

    public void setProssManageId(String prossManageId) {
        this.prossManageId = prossManageId;
    }

    public String getFatherId() {
        return fatherId;
    }

    public void setFatherId(String fatherId) {
        this.fatherId = fatherId;
    }

    public String getNodeOneName() {
        return nodeOneName;
    }

    public void setNodeOneName(String nodeOneName) {
        this.nodeOneName = nodeOneName;
    }

    public Integer getNodeOneNameContent() {
        return nodeOneNameContent;
    }

    public void setNodeOneNameContent(Integer nodeOneNameContent) {
        this.nodeOneNameContent = nodeOneNameContent;
    }

    public String getNodeOneHeader() {
        return nodeOneHeader;
    }

    public void setNodeOneHeader(String nodeOneHeader) {
        this.nodeOneHeader = nodeOneHeader;
    }

    public LocalDate getNodeOneContent() {
        return nodeOneContent;
    }

    public void setNodeOneContent(LocalDate nodeOneContent) {
        this.nodeOneContent = nodeOneContent;
    }

    public Integer getNodeTwoInterDate() {
        return nodeTwoInterDate;
    }

    public void setNodeTwoInterDate(Integer nodeTwoInterDate) {
        this.nodeTwoInterDate = nodeTwoInterDate;
    }

    public String getNodeTwoHeader() {
        return nodeTwoHeader;
    }

    public void setNodeTwoHeader(String nodeTwoHeader) {
        this.nodeTwoHeader = nodeTwoHeader;
    }

    public LocalDate getNodeTwoContent() {
        return nodeTwoContent;
    }

    public void setNodeTwoContent(LocalDate nodeTwoContent) {
        this.nodeTwoContent = nodeTwoContent;
    }

    public Integer getNodeThreeInterDate() {
        return nodeThreeInterDate;
    }

    public void setNodeThreeInterDate(Integer nodeThreeInterDate) {
        this.nodeThreeInterDate = nodeThreeInterDate;
    }

    public String getNodeThreeHeader() {
        return nodeThreeHeader;
    }

    public void setNodeThreeHeader(String nodeThreeHeader) {
        this.nodeThreeHeader = nodeThreeHeader;
    }

    public LocalDate getNodeThreeContent() {
        return nodeThreeContent;
    }

    public void setNodeThreeContent(LocalDate nodeThreeContent) {
        this.nodeThreeContent = nodeThreeContent;
    }

    public Integer getNodeFourInterDate() {
        return nodeFourInterDate;
    }

    public void setNodeFourInterDate(Integer nodeFourInterDate) {
        this.nodeFourInterDate = nodeFourInterDate;
    }

    public String getNodeFourHeader() {
        return nodeFourHeader;
    }

    public void setNodeFourHeader(String nodeFourHeader) {
        this.nodeFourHeader = nodeFourHeader;
    }

    public LocalDate getNodeFourContent() {
        return nodeFourContent;
    }

    public void setNodeFourContent(LocalDate nodeFourContent) {
        this.nodeFourContent = nodeFourContent;
    }
}