package com.bjike.goddess.storage.to;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * 文件信息
 *
 * @Author: [liguiqin]
 * @Date: [2017-04-27 08:57]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class FileInfo implements Serializable {
    /**
     * 登录令牌
     */
    public interface COMMON {
    }

    /**
     * 移动目录
     */
    public interface MOVE {
    }

    /**
     * 创建目录
     */
    public interface MKDIR {
    }

    /**
     * 重命名
     */
    public interface RENAME {
    }

    /**
     * 登录令牌
     */
    private String storageToken;

    /**
     * 文件，文件夹路径
     */
    @NotBlank(message = "文件，文件夹路径不能为空", groups = {FileInfo.COMMON.class})
    private String path;

    /**
     * 新文件名
     */
    @NotBlank(message = "文件，文件夹名不能为空", groups = {FileInfo.RENAME.class})
    private String newName;


    /**
     * 创建目录
     */
    @NotBlank(message = "创建目录不能为空", groups = {FileInfo.MKDIR.class})
    private String dir;


    /**
     * 移动到的文件夹路径
     */
    @NotBlank(message = "移动到的文件夹不能为空", groups = {FileInfo.MOVE.class})
    private String toPath;

    /**
     * 文件名
     */
    private String fileName;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getStorageToken() {
        return storageToken;
    }

    public void setStorageToken(String storageToken) {
        this.storageToken = storageToken;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getToPath() {
        return toPath;
    }

    public void setToPath(String toPath) {
        this.toPath = toPath;
    }

}
