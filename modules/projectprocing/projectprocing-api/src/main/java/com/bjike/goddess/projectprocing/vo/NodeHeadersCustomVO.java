package com.bjike.goddess.projectprocing.vo;

/**
 * 节点表头定制表现层对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 10:47 ]
 * @Description: [ 节点表头定制表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class NodeHeadersCustomVO {

    /**
     * id
     */
    private String id;
    /**
     * 外包单位
     */
    private String outUnit;
    /**
     * 进度管理id
     */
    private String prossManageId;
    /**
     * 父级id
     */
    private String fatherId;
    /**
     * 节点1名称
     */
    private String nodeOneName;

    /**
     * 节点1名称内容
     */
    private Integer nodeOneNameContent;

    /**
     * 节点1表头
     */
    private String nodeOneHeader;

    /**
     * 节点1内容
     */
    private String nodeOneContent;

    /**
     * 对节点2间隔时间（单位：天）
     */
    private Integer nodeTwoInterDate;

    /**
     * 节点2表头
     */
    private String nodeTwoHeader;

    /**
     * 节点2内容
     */
    private String nodeTwoContent;

    /**
     * 对节点3间隔时间（单位：天）
     */
    private Integer nodeThreeInterDate;

    /**
     * 节点3表头
     */
    private String nodeThreeHeader;

    /**
     * 节点3内容
     */
    private String nodeThreeContent;

    /**
     * 对节点4间隔时间（单位：天）
     */
    private Integer nodeFourInterDate;

    /**
     * 节点4表头
     */
    private String nodeFourHeader;

    /**
     * 节点4内容
     */
    private String nodeFourContent;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getNodeOneContent() {
        return nodeOneContent;
    }

    public void setNodeOneContent(String nodeOneContent) {
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

    public String getNodeTwoContent() {
        return nodeTwoContent;
    }

    public void setNodeTwoContent(String nodeTwoContent) {
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

    public String getNodeThreeContent() {
        return nodeThreeContent;
    }

    public void setNodeThreeContent(String nodeThreeContent) {
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

    public String getNodeFourContent() {
        return nodeFourContent;
    }

    public void setNodeFourContent(String nodeFourContent) {
        this.nodeFourContent = nodeFourContent;
    }
}