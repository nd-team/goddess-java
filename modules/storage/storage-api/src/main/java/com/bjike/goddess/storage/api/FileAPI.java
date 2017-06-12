package com.bjike.goddess.storage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.storage.bo.FileBO;
import com.bjike.goddess.storage.to.FileInfo;

import java.io.InputStream;
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
public interface FileAPI {
    /**
     * 文件列表
     *
     * @param fileInfo 文件信息
     */
    default List<FileBO> list(FileInfo fileInfo) throws SerException {
        return null;
    }

    /**
     * 文件上传
     *
     * @param inputStreams 上传文件及信息
     */
    default List<FileBO> upload(List<InputStream> inputStreams) throws SerException {
        return null;
    }


    /**
     * 文件夹创建
     *
     * @param fileInfo 文件信息
     */
    default void mkDir(FileInfo fileInfo) throws SerException {

    }

    /**
     * 删除文件
     *
     * @param paths 文件路径
     * @param storageToken 登录令牌
     */
    default void delFile(String storageToken,String[] paths) throws SerException {

    }


    /**
     * 重命名
     *
     * @param fileInfo 文件信息
     */
    default void rename(FileInfo fileInfo) throws SerException {

    }

    /**
     * 下载
     *
     * @param fileInfo 文件信息
     */
    default byte[] download(FileInfo fileInfo) throws SerException {
        return null;
    }

    /**
     * 文件是否存在
     *
     * @param fileInfo 文件信息
     */
    default Boolean existsFile(FileInfo fileInfo) throws SerException {
        return null;
    }

    /**
     * 文件是否存在
     *
     * @param fileInfo 文件信息
     */
    default Boolean move(FileInfo fileInfo) throws SerException {
        return null;
    }

    /**
     * 删除到回收站
     *
     * @param fileInfo 文件信息
     * @throws SerException
     */
    default void recycle(FileInfo fileInfo) throws SerException {

    }

    /**
     * 恢复目录
     *
     * @param fileInfo 文件信息
     */
    default void restore(FileInfo fileInfo) throws SerException {

    }

    /**
     * 回收站目录
     *
     * @param fileInfo 文件信息
     * @throws SerException
     */
    default List<FileBO> recycleList(FileInfo fileInfo) throws SerException {
        return null;
    }


    /**
     * 获取真实存储路径
     *
     * @param fileInfo 文件信息
     * @return
     * @throws SerException
     */
    default String getRealPath(FileInfo fileInfo) throws SerException {
        return null;
    }
}