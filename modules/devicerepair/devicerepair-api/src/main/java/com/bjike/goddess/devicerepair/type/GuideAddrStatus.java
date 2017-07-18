package com.bjike.goddess.devicerepair.type;

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
     * 上传附件
     */
    UPLOAD(4),
    /**
     * 下载附件
     */
    DOWNLOAD(5),
    /**
     * 设备报废
     */
    SCRAP(6),
    /**
     * 取回设备
     */
    RETRAEVE(7),
    /**
     * 福利模块审核
     */
    AUDIT(8),
    /**
     * 查看附件
     */
    SEEFILE(9);


    private int code;

    GuideAddrStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
