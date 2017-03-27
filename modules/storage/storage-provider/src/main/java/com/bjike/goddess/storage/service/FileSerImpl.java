package com.bjike.goddess.storage.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.storage.entity.File;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文件存储业务实现
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-03-25 02:02 ]
 * @Description: [ 文件存储业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service
public class FileSerImpl implements FileSer {
    @Autowired
    private UserAPI userAPI;

    @Override
    public List<File> list(String path) throws SerException {
        String username = userAPI.currentUser().getUsername();
        return null;
    }

    @Override
    public void upload(List<java.io.File> files, String path) throws SerException {
        System.out.println(files);
    }

    @Override
    public void mkDir(String path) throws SerException {

    }

    @Override
    public void delFile(String path) throws SerException {

    }

    @Override
    public void delFolder(String path) throws SerException {

    }

    @Override
    public void rename(String path, String newName) throws SerException {

    }

    @Override
    public void download(String path) throws SerException {

    }
}