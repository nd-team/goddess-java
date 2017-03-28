package com.bjike.goddess.storage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.storage.bo.FileBO;
import com.bjike.goddess.storage.entity.File;
import com.bjike.goddess.storage.service.FileSer;
import com.bjike.goddess.storage.to.FileTO;
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
    public List<FileBO> list(String path) throws SerException {
        return fileSer.list(path);
    }

    @Override
    public void upload( byte[] bytes,String fileName ,String path) throws SerException {
        fileSer.upload(bytes, fileName,path);
    }

    @Override
    public void mkDir(String path) throws SerException {
        fileSer.mkDir(path);
    }

    @Override
    public void delFile(String path) throws SerException {
        fileSer.delFile(path);
    }


    @Override
    public void rename(String path, String oldName, String newName) throws SerException {
        fileSer.rename(path, oldName,newName);
    }

    @Override
    public byte[] download(String path) throws SerException {
       return fileSer.download(path);
    }

    @Override
    public String getSavePath(String path) throws SerException {
        return  fileSer.getSavePath(path);
    }
}