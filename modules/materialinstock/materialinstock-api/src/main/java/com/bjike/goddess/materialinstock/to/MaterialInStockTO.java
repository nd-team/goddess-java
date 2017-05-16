package com.bjike.goddess.materialinstock.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.materialinstock.type.InstockType;
import com.bjike.goddess.materialinstock.type.MaterialState;
import com.bjike.goddess.materialinstock.type.UseState;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 物资入库
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-21 04:55 ]
 * @Description: [ 物资入库 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MaterialInStockTO extends BaseTO {

    /**
     * 项目组/部门
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "项目组/部门不能为空")
    private String projectGroup;

    /**
     * 入库类型
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "入库类型不能为空")
    private InstockType instockType;

    /**
     * 入库编码
     */
    private String stockEncoding;

    /**
     * 物资名称
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "物资名称不能为空")
    private String materialName;

    /**
     * 物品类型
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "物品类型不能为空")
    private String materialType;

    /**
     * 申购日期
     */
    private String buyDate;

    /**
     * 申购人
     */
    private String requisitioner;

    /**
     * 物资型号
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "物资型号不能为空")
    private String materialModel;

    /**
     * 数量
     */
    private Integer quantity;

    /**
     * 单位
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "单位不能为空")
    private String unit;

    /**
     * 单价(元)
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "单价不能为空")
    private Double unitPrice;

    /**
     * 总额(元)
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "总额不能为空")
    private Double totalSum;

    /**
     * 途径
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "途径不能为空")
    private String approach;

    /**
     * 购买网址
     */
    private String buyWebsites;

    /**
     * 入库日期
     */
    private String instockDate;

    /**
     * 存储地区
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "存储地区不能为空")
    private String storageArea;

    /**
     * 存储位置
     */
    private String storageSite;

    /**
     * 物资状态
     */
    private MaterialState materialState;

    /**
     * 使用状态
     */
    private UseState useState;

    /**
     * 外借人
     */
    private String lender;

    /**
     * 外借地区
     */
    private String lendArea;

    /**
     * 外借日期
     */
    private String lendDate;

    /**
     * 购买人
     */
    private String purchaser;

    /**
     * 购买日期
     */
    private String purchaseDate;

    /**
     * 是否有保修卡
     */
    private Boolean ifWarrantyCard;

    /**
     * 保修联系人
     */
    private String warrantyCardContact;

    /**
     * 保修联系电话
     */
    private String warrantyPhone;

    /**
     * 保修期限(月)
     */
    private Integer warrantyExpire;

    /**
     * 是否有发票
     */
    private Boolean ifInvoice;

    /**
     * 物资负责人
     */
    private String goodPrincipal;

    /**
     * 数据来源
     */
    private String dataSource;

    /**
     * 备注
     */
    private String comment;

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

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

    public String getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(String buyDate) {
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

    public String getInstockDate() {
        return instockDate;
    }

    public void setInstockDate(String instockDate) {
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

    public UseState getUseState() {
        return useState;
    }

    public void setUseState(UseState useState) {
        this.useState = useState;
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

    public String getLendDate() {
        return lendDate;
    }

    public void setLendDate(String lendDate) {
        this.lendDate = lendDate;
    }

    public String getPurchaser() {
        return purchaser;
    }

    public void setPurchaser(String purchaser) {
        this.purchaser = purchaser;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
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