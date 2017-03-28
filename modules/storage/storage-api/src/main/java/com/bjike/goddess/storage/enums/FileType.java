package com.bjike.goddess.storage.enums;

/**
 * 文件类型
 *
 * @Author: [liguiqin]
 * @Date: [2017-03-25 14:04]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum FileType {
    TXT(0),
    DOCM(1),
    DOC(2),
    XLSX(3),
    XLS(4),
    ZIP(5),
    RAR(6),
    BMP(7),
    PNG(8),
    GIF(9),
    JPG(10),
    JPEG(11),
    UNKNOW(12);

    private int code;

    FileType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}
