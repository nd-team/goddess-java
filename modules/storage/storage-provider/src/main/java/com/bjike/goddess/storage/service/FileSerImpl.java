package com.bjike.goddess.storage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.storage.api.StorageUserAPI;
import com.bjike.goddess.storage.bo.FileBO;
import com.bjike.goddess.storage.constant.PathCommon;
import com.bjike.goddess.storage.dto.FileDTO;
import com.bjike.goddess.storage.entity.File;
import com.bjike.goddess.storage.utils.FileUtils;
import com.bjike.goddess.user.api.UserAPI;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    private StorageUserAPI storageUserAPI;

    /**
     * 功能操作会通过storageToken确认登录后操作，每个storageToken对应一个模块
     *
     * @param path
     * @return
     * @throws SerException
     */

    @Override
    public List<FileBO> list(String path) throws SerException {
        String module = storageUserAPI.getCurrentModule(); //网盘登录用户
        String realPath = getRealPath(path);
        java.io.File dir = new java.io.File(realPath);
        java.io.File[] files = dir.listFiles();
        return getFileBo(files, module,PathCommon.ROOT_PATH);

    }


    @Transactional
    @Override
    public void upload(Map<String, byte[]> maps, String path) throws SerException {
        String module = storageUserAPI.getCurrentModule(); //网盘登录用户
        String realPath = getRealPath(path); //真实目录
        String userId = userAPI.currentUser().getId();
        for (Map.Entry<String, byte[]> entry : maps.entrySet()) {
            String fileName = entry.getKey();
            byte[] bytes = entry.getValue();
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


    }


    @Override
    public void mkDir(String path, String dir) throws SerException {
        path += (PathCommon.SEPARATOR + dir);
        String realPath = getRealPath(path);
        java.io.File file = new java.io.File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        } else {
            throw new SerException("该文件目录已存在！");
        }
    }

    @Override
    public void delFile(String path) throws SerException {
        String savePath = getRealPath(path);
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
        String realPath = getRealPath(path);
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
        String realPath = getRealPath(path);
        return FileUtils.FileToByte(realPath);
    }

    @Override
    public Boolean existsFile(String path) throws SerException {
        String realPath = getRealPath(path);
        return new java.io.File(realPath).exists();
    }

    @Override
    public Boolean move(String fromPath, String toPath) throws SerException {
        String from = getRealPath(fromPath);
        String to = getRealPath(toPath);
        java.io.File fromFile = new java.io.File(from);
        java.io.File toFile = new java.io.File(to);
        if (toFile.isFile()) {
            throw new SerException("不允许移动文件或文件夹到文件下！");
        } else {
            try {
                if (fromFile.isFile()) {
                    org.apache.commons.io.FileUtils.moveFileToDirectory(fromFile, toFile, true);
                } else {
                    org.apache.commons.io.FileUtils.moveDirectoryToDirectory(fromFile, toFile, true);
                }

            } catch (IOException e) {
                throw new SerException(e.getMessage());
            }
        }
        return true;
    }

    @Override
    public void recycle(String path) throws SerException {
        String realPath = getRealPath(path); //原目录
        java.io.File file = new java.io.File(realPath);
        try {
            if (file.isFile()) {
                realPath = StringUtils.substringAfter(realPath, PathCommon.ROOT_PATH);// 文件存储路径
                realPath = StringUtils.substringBeforeLast(realPath, PathCommon.SEPARATOR);//去文件名
                realPath = PathCommon.RECYCLE_PATH + PathCommon.SEPARATOR + realPath;
                java.io.File dir = new java.io.File(realPath);
                org.apache.commons.io.FileUtils.moveFileToDirectory(file, dir, true);
            } else {
                realPath = StringUtils.substringAfter(realPath, PathCommon.ROOT_PATH);// 文件存储路径
                realPath = PathCommon.RECYCLE_PATH + realPath;
                realPath = StringUtils.substringBeforeLast(realPath, PathCommon.SEPARATOR);//去掉一层目录
                java.io.File dir = new java.io.File(realPath);
                org.apache.commons.io.FileUtils.moveDirectoryToDirectory(file, dir, true);
            }
        } catch (Exception e) {
            throw new SerException(e.getMessage());
        }
    }

    @Override
    public void restore(String path) throws SerException {
        String module = storageUserAPI.getCurrentModule(); //网盘登录用户
        String realPath = null;
        if (!"admin".equals(module)) {//回收站目录
            realPath = PathCommon.RECYCLE_PATH + PathCommon.SEPARATOR + module + path;
        } else {
            realPath = PathCommon.RECYCLE_PATH + path;
        }
        java.io.File file = new java.io.File(realPath);
        try {
            if (file.isFile()) {
                String dirPath = getRealPath(path);
                dirPath = StringUtils.substringBeforeLast(dirPath, PathCommon.SEPARATOR);//去文件名
                java.io.File dir = new java.io.File(dirPath);
                org.apache.commons.io.FileUtils.moveFileToDirectory(file, dir, true);
            } else {
                String dirPath = getRealPath(path);
                dirPath = StringUtils.substringBeforeLast(dirPath, PathCommon.SEPARATOR); //去掉一层目录
                java.io.File dir = new java.io.File(dirPath);
                org.apache.commons.io.FileUtils.moveDirectoryToDirectory(file, dir, true);
            }
        } catch (IOException e) {
            throw new SerException(e.getMessage());
        }
    }


    @Override
    public List<FileBO> recycleList(String path) throws SerException {
        String module = storageUserAPI.getCurrentModule(); //网盘登录用户
        String recycleRealPath = getRecycleRealPath(path);
        java.io.File dir = new java.io.File(recycleRealPath);
        java.io.File[] files = dir.listFiles();
        return getFileBo(files, module,PathCommon.RECYCLE_PATH);
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
    private List<FileBO> getFileBo(java.io.File[] files, String module,String root) throws SerException {
        String rootPath = root;
        if (null != module) {
            rootPath += (PathCommon.SEPARATOR + module);
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

                if (root.equals(file.getParent())) {
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
     * @param path 模块用户path为文件短路径不带模块名前缀，系统用户则带模块前缀
     * @return
     */
    private String getRealPath(String path) throws SerException {
        String realPath = null;
        String module = storageUserAPI.getCurrentModule(); //网盘登录用户
        if (!"admin".equals(module)) {
            realPath = PathCommon.ROOT_PATH + PathCommon.SEPARATOR + module + path;
        } else {
            realPath = PathCommon.ROOT_PATH + path;
        }
        return realPath;
    }


    /**
     * 获取回收战真实路径
     *
     * @param path
     * @return
     * @throws SerException
     */
    private String getRecycleRealPath(String path) throws SerException {
        String realPath = null;
        String module = storageUserAPI.getCurrentModule(); //网盘登录用户
        if (!"admin".equals(module)) {
            realPath = PathCommon.RECYCLE_PATH + PathCommon.SEPARATOR + module + path;
        } else {
            realPath = PathCommon.RECYCLE_PATH + path;
        }
        return realPath;
    }

}