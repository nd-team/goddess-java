package com.bjike.goddess.storage.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.storage.bo.FileBO;
import com.bjike.goddess.storage.dto.FileDTO;
import com.bjike.goddess.storage.entity.File;

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
public interface FileSer extends Ser<File, FileDTO> {


    /**
     * 文件列表
     *
     * @param path         路径
     * @param storageToken 登录令牌
     */
    default List<FileBO> list(String path, String storageToken) throws SerException {
        return null;
    }

    /**
     * 文件上传
     *
     * @param inputStreams 上传文件，文件信息流
     */
    default List<FileBO> upload(List<InputStream> inputStreams) throws SerException {
        return null;
    }


    /**
     * 文件夹创建
     *
     * @param path         文件路径
     * @param dir          新的目录
     * @param storageToken 登录令牌
     */
    default void mkDir(String path, String dir, String storageToken) throws SerException {

    }

    /**
     * 删除文件
     *
     * @param paths         文件或者文件夹路径
     * @param storageToken 登录令牌
     */
    default void delFile(String[] paths, String storageToken) throws SerException {

    }


    /**
     * 重命名
     *
     * @param path         文件或者文件夹路径
     * @param newName      新文件名
     * @param storageToken 登录令牌
     */
    default void rename(String path, String newName, String storageToken) throws SerException {

    }

    /**
     * 下载
     *
     * @param storageToken 登录令牌
     * @param path         文件路径
     */
    default byte[] download(String path, String storageToken) throws SerException {
        return null;
    }

    /**
     * 文件是否存在
     *
     * @param path         文件路径
     * @param storageToken 登录令牌
     */
    default Boolean existsFile(String path, String storageToken) throws SerException {
        return null;
    }

    /**
     * 文件是否存在
     *
     * @param fromPath     移动路径
     * @param toPath       目标路径
     * @param storageToken 登录令牌
     */
    default Boolean move(String fromPath, String toPath, String storageToken) throws SerException {
        return null;
    }

    /**
     * 删除到回收站
     *
     * @param path         文件路径
     * @param storageToken 登录令牌
     * @throws SerException
     */
    default void recycle(String path, String storageToken) throws SerException {

    }

    /**
     * 恢复目录
     *
     * @param path         文件路径
     * @param storageToken 登录令牌
     */
    default void restore(String path, String storageToken) throws SerException {

    }

    /**
     * 回收站目录
     *
     * @param path         文件路径
     * @param storageToken 登录令牌
     * @throws SerException
     */
    default List<FileBO> recycleList(String path, String storageToken) throws SerException {
        return null;
    }


    /**
     * 获取真实存储路径
     *
     * @param path         文件路径
     * @param storageToken 登录令牌
     * @return
     * @throws SerException
     */
    default String getRealPath(String path, String storageToken) throws SerException {
        return null;
    }
}