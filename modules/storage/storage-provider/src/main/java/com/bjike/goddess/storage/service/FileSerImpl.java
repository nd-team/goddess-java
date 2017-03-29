package com.bjike.goddess.storage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.storage.bo.FileBO;
import com.bjike.goddess.storage.constant.PathCommon;
import com.bjike.goddess.storage.dto.FileDTO;
import com.bjike.goddess.storage.entity.File;
import com.bjike.goddess.storage.utils.FileUtils;
import com.bjike.goddess.user.api.UserAPI;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
public class FileSerImpl extends ServiceImpl<File, FileDTO> implements FileSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private Environment env;

    /**
     * 功能操作会通过storageToken确认登录后操作，每个storageToken对应一个模块
     *
     * @param path
     * @return
     * @throws SerException
     */

    //module/xxx/xxx
    @Override
    public List<FileBO> list(String path) throws SerException {
        String module = env.getProperty("module"); //网盘登录用户
        String realPath = getRealPath(module, path);
        java.io.File dir = new java.io.File(realPath);
        java.io.File[] files = dir.listFiles();
        return getFileBo(files, module);

    }


    @Transactional
    @Override
    public void upload(byte[] bytes, String fileName, String path) throws SerException {
        String userId = userAPI.currentUser().getId();
        String module = env.getProperty("module"); //网盘登录用户
        String realPath = getRealPath(module, path); //真实目录
        String filePath = realPath + PathCommon.SEPARATOR + fileName; //文件保存目录
        java.io.File file = null;
        if (!new java.io.File(filePath).exists()) {
            file = FileUtils.byteToFile(bytes, realPath, fileName);
            File myFile = new File();
            myFile.setName(fileName);
            myFile.setSize(FileUtils.getFileSize(file));
            myFile.setModifyTime(DateUtil.parseTime(file.lastModified()));
            myFile.setPath(module + path + PathCommon.SEPARATOR + fileName); //保存路径为模块起始
            myFile.setModule(module);
            myFile.setUserId(userId);
            myFile.setFileType(FileUtils.getFileType(file));
            super.save(myFile);
        } else { //更新
            File myFile = getFile(userId, path, fileName);
            if (null != myFile) {
                file = new java.io.File(filePath);
                myFile.setSize(FileUtils.getFileSize(file));
                myFile.setModifyTime(LocalDateTime.now());
                super.update(myFile);
            }
        }

    }


    @Override
    public void mkDir(String path, String dir) throws SerException {
        String module = env.getProperty("module"); //网盘登录用户
        path += (PathCommon.SEPARATOR + dir);
        String realPath = getRealPath(module, path);
        java.io.File file = new java.io.File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        } else {
            throw new SerException("该文件目录已存在！");
        }
    }

    @Override
    public void delFile(String path) throws SerException {
        String module = env.getProperty("module"); //网盘登录用户
        String savePath = getRealPath(module, path);
        java.io.File file = new java.io.File(savePath);
        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
            } else {
                try {
                    org.apache.commons.io.FileUtils.deleteDirectory(file);
                } catch (IOException e) {
                    throw new SerException(e.getMessage());
                }
            }

        } else {
            throw new SerException("该文件目录不存在！");
        }
    }


    @Override
    public void rename(String path, String newName) throws SerException {
        String module = env.getProperty("module"); //网盘登录用户
        String realPath = getRealPath(module, path);
        String oldName = StringUtils.substringAfterLast(realPath, "/");
        String savePath = StringUtils.substringBeforeLast(realPath, "/");
        if (!oldName.equals(newName)) {//新的文件名和以前文件名不同时,才有必要进行重命名
            java.io.File oldFile = new java.io.File(realPath);
            java.io.File newFile = new java.io.File(savePath + "/" + newName);
            if (!oldFile.exists()) {//重命名文件不存在
                throw new SerException("重命名文件不存在！");
            }
            if (newFile.exists())
                throw new SerException(newName + "已经存在！");
            else {
                oldFile.renameTo(newFile);
            }
        } else {
            throw new SerException("新文件名和旧文件名相同...");
        }
    }

    @Override
    public byte[] download(String path) throws SerException {
        String module = env.getProperty("module"); //网盘登录用户
        String realPath = getRealPath(module, path);
        return FileUtils.FileToByte(realPath);
    }

    @Override
    public Boolean existsFile(String path) throws SerException {
        String module = env.getProperty("module"); //网盘登录用户
        String realPath = getRealPath(module, path);
        return new java.io.File(realPath).exists();
    }


    /**
     * 通过文件信息查询数据库保存的相应数据
     *
     * @param userId   文件所属
     * @param path     文件路径
     * @param fileName 文件名
     * @return
     * @throws SerException
     */
    private File getFile(String userId, String path, String fileName) throws SerException {
        FileDTO dto = new FileDTO();
        dto.getConditions().add(Restrict.eq("userId", userId));
        dto.getConditions().add(Restrict.eq("name", fileName));
        dto.getConditions().add(Restrict.eq("path", path));
        File myFile = super.findOne(dto);
        return myFile;
    }


    /**
     * 通过文件列表获取文件的详细信息
     *
     * @param files
     * @return
     */
    private List<FileBO> getFileBo(java.io.File[] files, String module) throws SerException {
        String rootPath = PathCommon.ROOT_PATH;
        if (null != module) {
            rootPath = rootPath + PathCommon.SEPARATOR + module;
        }
        if (null != files) {
            List<FileBO> fileBOS = new ArrayList<>(files.length);
            for (int i = 0; i < files.length; i++) {
                FileBO fileBO = new FileBO();
                java.io.File file = files[i];
                fileBO.setFileType(FileUtils.getFileType(file));
                if (file.isFile()) {
                    fileBO.setSize(FileUtils.getFileSize(file));
                }

                if (PathCommon.ROOT_PATH.equals(file.getParent())) {
                    fileBO.setParentPath(null);
                } else {
                    fileBO.setParentPath(StringUtils.substringAfter(file.getParent(), rootPath));
                }
                fileBO.setPath(StringUtils.substringAfter(file.getPath(), rootPath));
                fileBO.setDir(file.isDirectory());
                fileBO.setName(file.getName());
                fileBO.setModifyTime(DateUtil.dateToString(DateUtil.parseTime(file.lastModified())));
                fileBOS.add(fileBO);
            }
            return fileBOS;
        }
        return null;
    }

    /**
     * @param module 登录的模块名
     * @param path   模块用户path为文件短路径不带模块名前缀，系统用户则带模块前缀
     * @return
     */
    private String getRealPath(String module, String path) {
        String realPath = null;
        if (null != module) {
            realPath = PathCommon.ROOT_PATH + PathCommon.SEPARATOR + module + path;
        } else {
            realPath = PathCommon.ROOT_PATH + path;
        }
        return realPath;
    }


}