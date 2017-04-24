package com.bjike.goddess.materialinstock.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.materialinstock.type.InstockType;
import com.bjike.goddess.materialinstock.type.MaterialState;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 物资入库
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-21 04:55 ]
 * @Description: [ 物资入库 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "materialinstock_materialinstock")
public class MaterialInStock extends BaseEntity {

    /**
     * 入库类型
     */
    @Column(name = "instockType", nullable = false, columnDefinition = "TINYINT(2) COMMENT '入库类型'")
    private InstockType instockType;

    /**
     * 入库编码
     */
    @Column(name = "stockEncoding", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '入库编码'")
    private String stockEncoding;

    /**
     * 物资名称
     */
    @Column(name = "materialName", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '物资名称'")
    private String materialName;

    /**
     * 物品类型
     */
    @Column(name = "materialType", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '物品类型'")
    private String materialType;

    /**
     * 申购日期
     */
    @Column(name = "buyDate", columnDefinition = "DATE COMMENT '申购日期'")
    private LocalDate buyDate;

    /**
     * 申购人
     */
    @Column(name = "requisitioner", columnDefinition = "VARCHAR(255) COMMENT '申购人'")
    private String requisitioner;

    /**
     * 物资型号
     */
    @Column(name = "materialModel", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '物资型号'")
    private String materialModel;

    /**
     * 数量
     */
    @Column(name = "quantity", nullable = false, columnDefinition = "INT(11) COMMENT '数量'")
    private Integer quantity;

    /**
     * 单位
     */
    @Column(name = "unit", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '单位'")
    private String unit;

    /**
     * 单价(元)
     */
    @Column(name = "unitPrice", nullable = false, columnDefinition = "DECIMAL(10,2) COMMENT '单价(元)'")
    private Double unitPrice;

    /**
     * 总额(元)
     */
    @Column(name = "totalSum", nullable = false, columnDefinition = "DECIMAL(10,2) COMMENT '总额(元)'")
    private Double totalSum;

    /**
     * 途径
     */
    @Column(name = "approach", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '途径'")
    private String approach;

    /**
     * 购买网址
     */
    @Column(name = "buyWebsites", columnDefinition = "VARCHAR(255) COMMENT '购买网址'")
    private String buyWebsites;

    /**
     * 入库日期
     */
    @Column(name = "instockDate", columnDefinition = "DATE COMMENT '入库日期'")
    private LocalDate instockDate;

    /**
     * 存储地区
     */
    @Column(name = "storageArea", columnDefinition = "VARCHAR(255) COMMENT '存储地区'")
    private String storageArea;

    /**
     * 存储位置
     */
    @Column(name = "storageSite", columnDefinition = "VARCHAR(255) COMMENT '存储位置'")
    private String storageSite;

    /**
     * 物资状态
     */
    @Column(name = "materialState", columnDefinition = "TINYINT(2) COMMENT '物资状态'")
    private MaterialState materialState;

    /**
     * 外借人
     */
    @Column(name = "lender", columnDefinition = "VARCHAR(255) COMMENT '外借人'")
    private String lender;

    /**
     * 外借地区
     */
    @Column(name = "lendArea", columnDefinition = "VARCHAR(255) COMMENT '外借地区'")
    private String lendArea;

    /**
     * 外借日期
     */
    @Column(name = "lendDate", columnDefinition = "DATE COMMENT '外借日期'")
    private LocalDate lendDate;

    /**
     * 购买人
     */
    @Column(name = "purchaser", columnDefinition = "VARCHAR(255) COMMENT '购买人'")
    private String purchaser;

    /**
     * 购买日期
     */
    @Column(name = "purchaseDate", columnDefinition = "DATE COMMENT '购买日期'")
    private LocalDate purchaseDate;

    /**
     * 是否有保修卡
     */
    @Column(name = "ifWarrantyCard", columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否有保修卡'", insertable = false)
    private Boolean ifWarrantyCard;

    /**
     * 保修联系人
     */
    @Column(name = "warrantyCardContact", columnDefinition = "VARCHAR(255) COMMENT '保修联系人'")
    private String warrantyCardContact;

    /**
     * 保修联系电话
     */
    @Column(name = "warrantyPhone", columnDefinition = "VARCHAR(255) COMMENT '保修联系电话'")
    private String warrantyPhone;

    /**
     * 保修期限(月)
     */
    @Column(name = "warrantyExpire", columnDefinition = "INT(11) COMMENT '保修期限(月)'")
    private Integer warrantyExpire;

    /**
     * 是否有发票
     */
    @Column(name = "ifInvoice", columnDefinition = "TINYINT(1) DEFAULT 0  COMMENT '是否有发票'")
    private Boolean ifInvoice;

    /**
     * 物资负责人
     */
    @Column(name = "goodPrincipal", columnDefinition = "VARCHAR(255) COMMENT '物资负责人'")
    private String goodPrincipal;

    /**
     * 数据来源
     */
    @Column(name = "dataSource", columnDefinition = "VARCHAR(255) COMMENT '数据来源'")
    private String dataSource;

    /**
     * 备注
     */
    @Column(name = "comment", columnDefinition = "VARCHAR(255) COMMENT '备注'")
    private String comment;


    public InstockType getInstockType() {
        return instockType;
    }

    public void setInstockType(InstockType instockType) {
        this.instockType = instockType;
    }

    public String getStockEncoding() {
        return stockEncoding;
    }

    public void setStockEncoding(String stockEncoding) {
        this.stockEncoding = stockEncoding;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public LocalDate getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(LocalDate buyDate) {
        this.buyDate = buyDate;
    }

    public String getRequisitioner() {
        return requisitioner;
    }

    public void setRequisitioner(String requisitioner) {
        this.requisitioner = requisitioner;
    }

    public String getMaterialModel() {
        return materialModel;
    }

    public void setMaterialModel(String materialModel) {
        this.materialModel = materialModel;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
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

    public String getBuyWebsites() {
        return buyWebsites;
    }

    public void setBuyWebsites(String buyWebsites) {
        this.buyWebsites = buyWebsites;
    }

    public LocalDate getInstockDate() {
        return instockDate;
    }

    public void setInstockDate(LocalDate instockDate) {
        this.instockDate = instockDate;
    }

    public String getStorageArea() {
        return storageArea;
    }

    public void setStorageArea(String storageArea) {
        this.storageArea = storageArea;
    }

    public String getStorageSite() {
        return storageSite;
    }

    public void setStorageSite(String storageSite) {
        this.storageSite = storageSite;
    }

    public MaterialState getMaterialState() {
        return materialState;
    }

    public void setMaterialState(MaterialState materialState) {
        this.materialState = materialState;
    }

    public String getLender() {
        return lender;
    }

    public void setLender(String lender) {
        this.lender = lender;
    }

    public String getLendArea() {
        return lendArea;
    }

    public void setLendArea(String lendArea) {
        this.lendArea = lendArea;
    }

    public LocalDate getLendDate() {
        return lendDate;
    }

    public void setLendDate(LocalDate lendDate) {
        this.lendDate = lendDate;
    }

    public String getPurchaser() {
        return purchaser;
    }

    public void setPurchaser(String purchaser) {
        this.purchaser = purchaser;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Boolean getIfWarrantyCard() {
        return ifWarrantyCard;
    }

    public void setIfWarrantyCard(Boolean ifWarrantyCard) {
        this.ifWarrantyCard = ifWarrantyCard;
    }

    public String getWarrantyCardContact() {
        return warrantyCardContact;
    }

    public void setWarrantyCardContact(String warrantyCardContact) {
        this.warrantyCardContact = warrantyCardContact;
    }

    public String getWarrantyPhone() {
        return warrantyPhone;
    }

    public void setWarrantyPhone(String warrantyPhone) {
        this.warrantyPhone = warrantyPhone;
    }

    public Integer getWarrantyExpire() {
        return warrantyExpire;
    }

    public void setWarrantyExpire(Integer warrantyExpire) {
        this.warrantyExpire = warrantyExpire;
    }

    public Boolean getIfInvoice() {
        return ifInvoice;
    }

    public void setIfInvoice(Boolean ifInvoice) {
        this.ifInvoice = ifInvoice;
    }

    public String getGoodPrincipal() {
        return goodPrincipal;
    }

    public void setGoodPrincipal(String goodPrincipal) {
        this.goodPrincipal = goodPrincipal;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}