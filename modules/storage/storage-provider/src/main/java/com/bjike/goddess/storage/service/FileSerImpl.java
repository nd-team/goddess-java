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
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public List<FileBO> list(String path) throws SerException {
        String rootPath = getRootPath(); // 根路径
        String savePath = getSavePath(path); //文件实际保存路径
        java.io.File dir = new java.io.File(savePath);
        java.io.File[] files = dir.listFiles();
        if (null != files) {
            List<FileBO> fileBOS = new ArrayList<>(files.length);
            for (int i = 0; i < files.length; i++) {
                FileBO fileBO = new FileBO();
                java.io.File file = files[i];
                if (file.isFile()) {
                    fileBO.setSize(FileUtils.getFileSize(file));
                    fileBO.setFileType(FileUtils.getFileType(file));
                }
                if(rootPath.equals(file.getParent())){
                    fileBO.setParentPath(null);
                }else {
                    fileBO.setParentPath(StringUtils.substringAfter(file.getParent(),rootPath));
                }
                fileBO.setPath(StringUtils.substringAfter(file.getPath(),rootPath));
                fileBO.setDir(file.isDirectory());
                fileBO.setName(file.getName());
                fileBO.setModifyTime(DateUtil.dateToString(DateUtil.parseTime(file.lastModified())));
                fileBOS.add(fileBO);
            }
            return fileBOS;
        }
        return null;
    }


    @Override
    public void upload(byte[] bytes, String fileName, String path) throws SerException {
        String userId = userAPI.currentUser().getId();
        String savePath = this.getSavePath(path);
        String filePath = savePath+PathCommon.SEPARATOR+fileName;
        java.io.File file = null;
        if (!new java.io.File(filePath).exists()) {
            file = FileUtils.byteToFile(bytes, savePath, fileName);
            File myFile = new File();
            myFile.setName(fileName);
            myFile.setSize(FileUtils.getFileSize(file));
            myFile.setModifyTime(DateUtil.parseTime(file.lastModified()));
            myFile.setPath(path); //只保存短路径
            myFile.setUserId(userId);
            myFile.setFileType(FileUtils.getFileType(file));
            super.save(myFile);
        } else { //更新
            FileDTO dto = new FileDTO();
            dto.getConditions().add(Restrict.eq("userId", userId));
            dto.getConditions().add(Restrict.eq("name", fileName));
            dto.getConditions().add(Restrict.eq("path", path));
            File myFile = super.findOne(dto);
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
        path += (PathCommon.SEPARATOR + dir);
        path = getSavePath(path);
        java.io.File file = new java.io.File(path);
        if (!file.exists()) {
            file.mkdirs();
        } else {
            throw new SerException("该文件目录已存在！");
        }
    }

    @Override
    public void delFile(String path) throws SerException {
        path = getSavePath(path);
        java.io.File file = new java.io.File(path);
        if (file.exists()) {
          file.delete();
        } else {
            throw new SerException("该文件目录不存在！");
        }
    }


    @Override
    public void rename(String path,String newName) throws SerException {
         path = getSavePath(path);
        String oldName = StringUtils.substringAfterLast(path, "/");
        String savePath = StringUtils.substringBeforeLast(path, "/");
        if (!oldName.equals(newName)) {//新的文件名和以前文件名不同时,才有必要进行重命名
            java.io.File oldFile = new java.io.File(path);
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
        path = getSavePath(path);
        return FileUtils.FileToByte(path);
    }

    @Override
    public Boolean existsFile(String path) throws SerException {
        path = getSavePath(path);
        return new java.io.File(path).exists();
    }

    /**
     * 获取真实保存地址前缀
     *
     * @param path
     * @return
     * @throws SerException
     */
    private String getSavePath(String path) throws SerException {
        UserBO userBO = userAPI.currentUser();
        StringBuilder sb_path = new StringBuilder();
        sb_path.append(PathCommon.ROOT_PATH);
        sb_path.append(PathCommon.SEPARATOR);
        sb_path.append(userBO.getId());
        if (!"/".equals(path)) {
            sb_path.append(path);
        }
        return sb_path.toString();
    }

    /**
     * 获取真实保存地址前缀
     *
     * @return
     * @throws SerException
     */
    private String getRootPath() throws SerException {
        UserBO userBO = userAPI.currentUser();
        StringBuilder sb_path = new StringBuilder();
        sb_path.append(PathCommon.ROOT_PATH);
        sb_path.append(PathCommon.SEPARATOR);
        sb_path.append(userBO.getId());
        return sb_path.toString();
    }


}