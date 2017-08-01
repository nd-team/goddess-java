package com.bjike.goddess.announcement.vo;

/**
 * 文件对象
 *
 * @Author: [chenjunhao]
 * @Date: [2017-07-28 10:32]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class MyFileVO {
    /**
     * 下载路径
     */
    private String path;
    /**
     * 文件格式
     */
    private String type;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
