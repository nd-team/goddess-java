package com.bjike.goddess.equipmentprepared.vo;

/**
 * 已付款汇总信息
 *
 * @Author: [chenjunhao]
 * @Date: [2017-05-13 14:50]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class PayCountVO {
    /**
     * 申购时间
     */
    private String subscribeDate;

    /**
     * 申购人
     */
    private String requisitioner;

    /**
     * 地区
     */
    private String area;

    /**
     * 部门
     */
    private String projectTeam;

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 型号
     */
    private String model;

    /**
     * 购买单价
     */
    private Double unitPrice;

    /**
     * 数量
     */
    private Integer quantity;

    /**
     * 合计金额
     */
    private Double totalSum;

    /**
     * 购买途径
     */
    private String approach;

    /**
     * 备注
     */
    private String comment;

    /**
     * 总数量
     */
    private Integer quantitySum;

    /**
     * 总金额
     */
    private Double sum;

    public Integer getQuantitySum() {
        return quantitySum;
    }

    public void setQuantitySum(Integer quantitySum) {
        this.quantitySum = quantitySum;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public String getProjectTeam() {
        return projectTeam;
    }

    public void setProjectTeam(String projectTeam) {
        this.projectTeam = projectTeam;
    }

    public String getSubscribeDate() {
        return subscribeDate;
    }

    public void setSubscribeDate(String subscribeDate) {
        this.subscribeDate = subscribeDate;
    }

    public String getRequisitioner() {
        return requisitioner;
    }

    public void setRequisitioner(String requisitioner) {
        this.requisitioner = requisitioner;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(Double totalSum) {
        this.totalSum = totalSum;
    }

    public String getApproach() {
        return approach;
    }

    public void setApproach(String approach) {
        this.approach = approach;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
