package com.bjike.goddess.storage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.storage.bo.FileBO;

import java.util.List;
import java.util.Map;

/**
 * 文件存储业务接口
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-03-25 02:02 ]
 * @Description: [ 文件存储业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface FileAPI {

    /**
     * 文件列表
     *
     * @param path
     */
    default List<FileBO> list(String path) throws SerException {
        return null;
    }

    /**
     * 多文件上传
     *
     * @param maps 文件名，byte 文件字节
     * @param path 上传路径
     * @return 文件路径
     */
    default void upload(Map<String, byte[]> maps, String path) throws SerException {
    }

    /**
     * 单文件上传
     *
     * @param bytes    文件字节
     * @param fileName 文件名
     * @param path     上传路径
     * @return 文件路径
     */
    default void upload(byte[] bytes, String fileName, String path) throws SerException {
    }

    /**
     * 文件夹创建
     *
     * @param path 文件路径
     * @param dir  新的目录
     */
    default void mkDir(String path, String dir) throws SerException {

    }

    /**
     * 删除文件
     *
     * @param path 文件或者文件夹路径
     */
    default void delFile(String path) throws SerException {

    }


    /**
     * 重命名
     *
     * @param path    文件或者文件夹路径
     * @param newName 新文件名
     */
    default void rename(String path, String newName) throws SerException {

    }

    /**
     * 下载
     *
     * @param path 文件路径
     */
    default byte[] download(String path) throws SerException {
        return null;
    }

    /**
     * 文件是否存在
     *
     * @param path 文件路径
     */
    default Boolean existsFile(String path) throws SerException {
        return null;
    }

    /**
     * 文件是否存在
     *
     * @param fromPath 移动路径
     * @param toPath   目标路径
     */
    default Boolean move(String fromPath, String toPath) throws SerException {
        return null;
    }

    /**
     * 删除到回收站
     *
     * @param path
     * @throws SerException
     */
    default void recycle(String path) throws SerException {

    }

    /**
     * 恢复目录
     */
    default void restore(String path) throws SerException {

    }

    /**
     * 回收站目录
     *
     * @param path
     * @throws SerException
     */
    default  List<FileBO> recycleList(String path) throws SerException {
        return null;
    }

}