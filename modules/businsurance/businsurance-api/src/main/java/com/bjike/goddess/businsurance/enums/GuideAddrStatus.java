package com.bjike.goddess.businsurance.enums;

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
     * 查看明细
     */
    SEE(4),
    /**
     * 导出
     */
    EXPORT(5),
    /**
     * 总经办审核
     */
    MANAGEAUDIT(6),
    /**
     * 福利模块审核
     */
    MODULEAUDIT(7),
    /**
     * 运营商务部审核
     */
    BUSINESSAUDIT(8),
    /**
     * 查看附件
     */
    SEEFILE(9),
    /**
     * 上传附件
     */
    UPLOAD(10),
    /**
     * 下载附件
     */
    DOWNLOAD(11);

    private int code;

    GuideAddrStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
