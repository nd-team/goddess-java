package com.bjike.goddess.storage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.storage.bo.FileBO;
import com.bjike.goddess.storage.service.FileSer;
import com.bjike.goddess.storage.to.FileInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

/**
 * 文件存储业务接口实现
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-03-25 02:02 ]
 * @Description: [ 文件存储业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("fileApiImpl")
public class FileApiImpl implements FileAPI {

    @Autowired
    private FileSer fileSer;

    @Override
    public List<FileBO> list(FileInfo fileInfo) throws SerException {
        String path = fileInfo.getPath();
        String token = fileInfo.getStorageToken();
        return fileSer.list(path, token);
    }

    @Override
    public List<FileBO> upload(List<InputStream> inputStreams) throws SerException {
        return fileSer.upload(inputStreams);
    }

    @Override
    public void mkDir(FileInfo fileInfo) throws SerException {
        String path = fileInfo.getPath();
        String token = fileInfo.getStorageToken();
        String dir = fileInfo.getDir();
        if (StringUtils.isNotBlank(dir)) {
            fileSer.mkDir(path, dir, token);
        } else {
            throw new SerException("dir 不能为空！");
        }
    }

    @Override
    public void delFile(FileInfo fileInfo) throws SerException {
        String path = fileInfo.getPath();
        String token = fileInfo.getStorageToken();
        fileSer.delFile(path, token);
    }


    @Override
    public void rename(FileInfo fileInfo) throws SerException {
        String path = fileInfo.getPath();
        String newName = fileInfo.getNewName();
        String token = fileInfo.getStorageToken();
        if (StringUtils.isNotBlank(newName)) {
            fileSer.rename(path, newName, token);
        } else {
            throw new SerException("newName 不能为空！");
        }
    }

    @Override
    public byte[] download(FileInfo fileInfo) throws SerException {
        String path = fileInfo.getPath();
        String token = fileInfo.getStorageToken();
        return fileSer.download(path, token);
    }

    @Override
    public Boolean existsFile(FileInfo fileInfo) throws SerException {
        String path = fileInfo.getPath();
        String token = fileInfo.getStorageToken();
        return fileSer.existsFile(path, token);
    }

    @Override
    public Boolean move(FileInfo fileInfo) throws SerException {
        String from = fileInfo.getPath();
        String to = fileInfo.getToPath();
        if (StringUtils.isNotBlank(to)) {
            String token = fileInfo.getStorageToken();
            return fileSer.move(from, to, token);
        } else {
            throw new SerException("toPath 不能为空！");
        }

    }

    @Override
    public void recycle(FileInfo fileInfo) throws SerException {
        String path = fileInfo.getPath();
        String token = fileInfo.getStorageToken();
        fileSer.recycle(path, token);
    }

    @Override
    public void restore(FileInfo fileInfo) throws SerException {
        String path = fileInfo.getPath();
        String token = fileInfo.getStorageToken();
        fileSer.restore(path, token);
    }

    @Override
    public List<FileBO> recycleList(FileInfo fileInfo) throws SerException {
        String path = fileInfo.getPath();
        String token = fileInfo.getStorageToken();
        return fileSer.recycleList(path, token);
    }


    @Override
    public String getRealPath(FileInfo fileInfo) throws SerException {
        String path = fileInfo.getPath();
        String token = fileInfo.getStorageToken();
        return fileSer.getRealPath(path, token);
    }


}