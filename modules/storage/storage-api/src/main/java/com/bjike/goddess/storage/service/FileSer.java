package com.bjike.goddess.storage.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.storage.entity.File;

import java.util.List;

/**
 * 文件存储业务接口
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-03-25 02:02 ]
 * @Description: [ 文件存储业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface FileSer {


    /**
     * 文件列表
     *
     * @param path
     */
    default List<File> list(String path) throws SerException {
        return null;
    }

    /**
     * 文件上传
     *
     * @param files 文件
     * @param path  上传路径
     */
    default void upload(List<java.io.File> files, String path) throws SerException {

    }

    /**
     * 文件夹创建
     *
     * @param path
     */
    default void mkDir(String path) throws SerException {

    }

    /**
     * 删除文件
     *
     * @param path
     */
    default void delFile(String path) throws SerException {

    }

    /**
     * 删除文件夹
     *
     * @param path
     */
    default void delFolder(String path) throws SerException {

    }

    /**
     * 重命名
     *
     * @param path
     * @param newName
     */
    default void rename(String path, String newName) throws SerException {

    }

    /**
     * 下载
     *
     * @param path
     */
    default void download(String path) throws SerException {

    }

}