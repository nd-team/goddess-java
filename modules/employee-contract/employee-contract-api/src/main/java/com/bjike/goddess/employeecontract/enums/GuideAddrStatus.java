package com.bjike.goddess.employeecontract.enums;

/**
 * 导航权限
 *
 * @Author: [lijuntao]
 * @Date: [2017-06-20 12:53]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum GuideAddrStatus {
    /**
     * 列表
     */
    LIST(0),
    /**
     * 添加
     */
    ADD(1),
    /**
     * 编辑
     */
    EDIT(2),
    /**
     * 删除
     */
    DELETE(3),
    /**
     * 劳动合同管理列表
     */
    LDLIST(4),
    /**
     * 劳动合同管理编辑
     */
    LDEDIT(5),
    /**
     * 劳动合同变更列表,编辑
     */
    LDCHANG(6),
    /**
     * 劳动合同解除
     */
    LDREMOVE(7),
    /**
     * 上传附件
     */
    UPLOAD(8),
    /**
     * 下载附件
     */
    DOWNLOAD(9),
    /**
     * 查看
     */
    SEE(10),
    /**
     * 查看附件
     */
    SEEFILE(11),
    /**
     * 冻结
     */
    CONGEL(12),
    /**
     * 解冻
     */
    THAW(13);
    private int code;

    GuideAddrStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
