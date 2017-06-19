package com.bjike.goddess.storage.service;

import com.alibaba.fastjson.JSON;
import com.bjike.goddess.common.api.dto.Condition;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.storage.api.StorageUserAPI;
import com.bjike.goddess.storage.bo.FileBO;
import com.bjike.goddess.storage.constant.PathCommon;
import com.bjike.goddess.storage.dto.FileDTO;
import com.bjike.goddess.storage.entity.File;
import com.bjike.goddess.storage.to.FileInfo;
import com.bjike.goddess.storage.utils.FileUtils;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
    private static final String[] IMAGE_SUFFIX = new String[]{"jpg", "bmp", "gif"};

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
    public List<FileBO> list(String path, String storageToken) throws SerException {
        String module = storageUserAPI.getCurrentModule(storageToken); //网盘登录用户
        String sysNO = storageUserAPI.getCurrentSysNO(storageToken); //网盘登录用户
        String realPath = getRealPath(path, storageToken);
        java.io.File dir = new java.io.File(realPath);
        java.io.File[] files = dir.listFiles();
        return getFileBo(files, module, sysNO, PathCommon.ROOT_PATH);
    }


    @Transactional
    @Override
    public List<FileBO> upload(List<InputStream> inputStreams) throws SerException {
        String module = null; //网盘登录用户
        String sysNO = null;
        try {
            int infoCount = 0;
            int fileCount = 1;
            int count = inputStreams.size();
            if (count >= 2) {
                count /= 2;
            }
            List<java.io.File> files = new ArrayList<>(count);
            for (int i = 0; i < count; i++) {
                FileInfo fileInfo = this.getFileInfo(inputStreams.get(infoCount));
                String storageToken = fileInfo.getStorageToken();
                module = storageUserAPI.getCurrentModule(storageToken);//网盘登录用户
                sysNO = storageUserAPI.getCurrentSysNO(storageToken); //网盘登录用户
                Object o_file = (Object) inputStreams.get(fileCount); //获取上传文件bytes
                String path = fileInfo.getPath(); //文件上传路径
                String fileName = fileInfo.getFileName(); //上传文件名
                String realPath = getRealPath(path, storageToken);
                String filePath = realPath + PathCommon.SEPARATOR + fileName; //文件保存目录
                java.io.File file = new java.io.File(filePath);
                if (!file.exists()) {
                    file = FileUtils.byteToFile((byte[]) o_file, realPath, fileName);

                    //保存到数据库
                    File myFile = new File();
                    myFile.setName(fileName);
                    myFile.setSize(FileUtils.getFileSize(file));
                    myFile.setModifyTime(DateUtil.parseTime(file.lastModified()));
                    myFile.setPath(getDbFilePath(file)); //保存路径为模块起始
                    myFile.setModule(module);
                    myFile.setFileType(FileUtils.getFileType(file));
                    super.save(myFile);
                } else { //更新
                    File myFile = getFile(module, path, fileName);
                    if (null != myFile) {
                        file = new java.io.File(filePath);
                        myFile.setSize(FileUtils.getFileSize(file));
                        myFile.setModifyTime(LocalDateTime.now());
                        super.update(myFile);
                    }
                }
                files.add(file);
                infoCount += 2;
                fileCount += 2;
            }
            return getFileBo(files.toArray(new java.io.File[count]), module, sysNO, PathCommon.ROOT_PATH);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SerException(e.getMessage());
        }
    }


    @Override
    public void mkDir(String path, String dir, String storageToken) throws SerException {
        path += (PathCommon.SEPARATOR + dir);
        String realPath = getRealPath(path, storageToken);
        java.io.File file = new java.io.File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        } else {
            throw new SerException("该文件目录已存在！");
        }
    }

    @Override
    public void delFile(String[] paths, String storageToken) throws SerException {
        for (String path : paths) {
            String module = storageUserAPI.getCurrentModule(storageToken); //网盘登录用户
            String savePath = getRealPath(path, storageToken);
            java.io.File file = new java.io.File(savePath);
            if (file.exists()) {
                if (file.isFile()) {
                    FileDTO dto = new FileDTO();
                    path = getDbFilePath(file);
                    dto.getConditions().add(Restrict.eq("path", path));
                    dto.getConditions().add(Restrict.eq("module", module));
                    dto.getConditions().add(Restrict.eq("fileType", FileUtils.getFileType(file).getCode()));
                    File db_file = super.findOne(dto);
                    if (null != db_file) {
                        file.delete();
                        super.remove(db_file);
                    }
                } else {
                    try {
                        FileDTO dto = new FileDTO();
                        path = getDbFilePath(file);
                        List<Condition> conditions = dto.getConditions();
                        conditions.add(Restrict.eq("module", module));
                        conditions.add(Restrict.like("path", path)); //以该路径开头的文件全部删除
                        List<File> fileList = super.findByCis(dto);
                        org.apache.commons.io.FileUtils.deleteDirectory(file); //删除目录及目录下的所有文件
                        super.remove(fileList); //删除所有文件
                    } catch (IOException e) {
                        throw new SerException(e.getMessage());
                    }
                }

            } else {
                throw new SerException("该文件目录不存在！");
            }
        }

    }


    @Override
    public void rename(String path, String newName, String storageToken) throws SerException {
        String module = storageUserAPI.getCurrentModule(storageToken); //网盘登录用户
        String realPath = getRealPath(path, storageToken);
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
                String oldPath = getDbFilePath(oldFile);
                String newPath = getDbFilePath(newFile);
                FileDTO dto = new FileDTO();
                dto.getConditions().add(Restrict.eq("path", oldPath));
                dto.getConditions().add(Restrict.eq("module", module));
                File file = super.findOne(dto);
                file.setPath(newPath);
                file.setName(newName);
                super.update(file);
            }
        } else {
            throw new SerException("新文件名和旧文件名相同!");
        }
    }

    @Override
    public byte[] download(String path, String storageToken) throws SerException {
        String realPath = getRealPath(path, storageToken);
        return FileUtils.FileToByte(realPath);
    }

    @Override
    public byte[] thumbnails(String path, String storageToken) throws SerException {
        String realPath = getRealPath(path, storageToken);
        String suffix = StringUtils.substringAfterLast(path, ".");
        boolean exist = false;
        for (String sx : IMAGE_SUFFIX) {
            if (sx.equalsIgnoreCase(suffix)) {
                exist = true;
            }
        }
        if (exist) {
            try {
                Thumbnails.Builder<java.io.File> fileBuilder = Thumbnails.of(realPath)
                        .forceSize(200, 180)
                        .outputQuality(0.35f)
                        .outputFormat(suffix);
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                fileBuilder.toOutputStream(os);
                return os.toByteArray();
            } catch (IOException e) {
                throw new SerException("缩略图获取错误");
            }
        } else {
            throw new SerException("不支持该文件类型缩略图");
        }

    }

    @Override
    public Boolean existsFile(String path, String storageToken) throws SerException {
        String realPath = getRealPath(path, storageToken);
        return new java.io.File(realPath).exists();
    }

    @Override
    public Boolean move(String fromPath, String toPath, String storageToken) throws SerException {
        String from = getRealPath(fromPath, storageToken);
        String to = getRealPath(toPath, storageToken);
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
    public void recycle(String path, String storageToken) throws SerException {
        String realPath = getRealPath(path, storageToken); //原目录
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
                //没有存在回收目录直接移动目录
                org.apache.commons.io.FileUtils.moveDirectoryToDirectory(file, dir, !file.exists());
            }
        } catch (Exception e) {
            throw new SerException(e.getMessage());
        }
    }

    @Override
    public void restore(String path, String storageToken) throws SerException {
        String module = storageUserAPI.getCurrentModule(storageToken); //网盘登录用户
        String sysNO = storageUserAPI.getCurrentSysNO(storageToken); //网盘登录用户
        String realPath = null;
        if (!"admin".equals(module)) {//回收站目录
            realPath = PathCommon.RECYCLE_PATH + PathCommon.SEPARATOR + sysNO + PathCommon.SEPARATOR + module + path;
        } else {
            realPath = PathCommon.RECYCLE_PATH + path;
        }
        java.io.File file = new java.io.File(realPath);
        try {
            if (file.isFile()) {
                String dirPath = getRealPath(path, storageToken);
                dirPath = StringUtils.substringBeforeLast(dirPath, PathCommon.SEPARATOR);//去文件名
                java.io.File dir = new java.io.File(dirPath);
                org.apache.commons.io.FileUtils.moveFileToDirectory(file, dir, true);
            } else {
                String dirPath = getRealPath(path, storageToken);
                dirPath = StringUtils.substringBeforeLast(dirPath, PathCommon.SEPARATOR); //去掉一层目录
                java.io.File dir = new java.io.File(dirPath);
                org.apache.commons.io.FileUtils.moveDirectoryToDirectory(file, dir, true);
            }
        } catch (IOException e) {
            throw new SerException(e.getMessage());
        }
    }


    @Override
    public List<FileBO> recycleList(String path, String storageToken) throws SerException {
        String module = storageUserAPI.getCurrentModule(storageToken); //网盘登录用户
        String sysNO = storageUserAPI.getCurrentSysNO(storageToken); //网盘登录用户
        String recycleRealPath = getRecycleRealPath(path, storageToken);
        java.io.File dir = new java.io.File(recycleRealPath);
        java.io.File[] files = dir.listFiles();
        return getFileBo(files, module, sysNO, PathCommon.RECYCLE_PATH);
    }


    /**
     * 通过文件信息查询数据库保存的相应数据
     *
     * @param module   文件所属
     * @param path     文件路径
     * @param fileName 文件名
     * @return
     * @throws SerException
     */
    private File getFile(String module, String path, String fileName) throws SerException {
        FileDTO dto = new FileDTO();
        dto.getConditions().add(Restrict.eq("module", module));
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
    private List<FileBO> getFileBo(java.io.File[] files, String module, String sysNO, String root) throws SerException {
        String rootPath = root;
        if (null != module) {
            rootPath += (PathCommon.SEPARATOR + sysNO + PathCommon.SEPARATOR + module);
        }
        if (null != files) {
            List<FileBO> fileBOS = new ArrayList<>(files.length);
            for (int i = 0; i < files.length; i++) {
                FileBO fileBO = new FileBO();
                java.io.File file = files[i];
                fileBO.setFileType(FileUtils.getFileType(file));
                if (file.isFile()) {
                    fileBO.setSize(FileUtils.getFileSize(file));
                    fileBO.setLength(file.length());
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
    @Override
    public String getRealPath(String path, String storageToken) throws SerException {
        String realPath = null;
        String module = storageUserAPI.getCurrentModule(storageToken); //网盘登录用户
        String sysNO = storageUserAPI.getCurrentSysNO(storageToken); //网盘登录用户
        if (StringUtils.isNotBlank(path)) {
            if (path.equals("/")) {
                path = "";
            }
            if (!"admin".equals(module)) {
                StringBuilder sb = new StringBuilder();
                sb.append(PathCommon.ROOT_PATH);
                sb.append(PathCommon.SEPARATOR);
                sb.append(sysNO);
                sb.append(PathCommon.SEPARATOR);
                sb.append(module);
                sb.append(path);
                realPath = sb.toString();
            } else {
                realPath = PathCommon.ROOT_PATH + path;
            }

        } else {
            throw new SerException("path 不能为空!");
        }

        return realPath;
    }


    /**
     * 获取回收站真实路径
     *
     * @param path
     * @return
     * @throws SerException
     */
    private String getRecycleRealPath(String path, String storageToken) throws SerException {
        String realPath = null;
        String module = storageUserAPI.getCurrentModule(storageToken); //网盘登录用户
        String sysNO = storageUserAPI.getCurrentSysNO(storageToken); //网盘登录用户
        if (!"admin".equals(module)) {
            StringBuilder sb = new StringBuilder();
            sb.append(PathCommon.RECYCLE_PATH);
            sb.append(PathCommon.SEPARATOR);
            sb.append(sysNO);
            sb.append(PathCommon.SEPARATOR);
            sb.append(module);
            sb.append(path);
            realPath = sb.toString();
        } else {
            realPath = PathCommon.RECYCLE_PATH + path;
        }
        return realPath;
    }

    /**
     * 通过文件获取保存数据库对应的数据
     *
     * @param file
     * @return
     */
    private String getDbFilePath(java.io.File file) {
        return StringUtils.substringAfterLast(file.getPath(), PathCommon.ROOT_PATH + PathCommon.SEPARATOR);
    }

    /**
     * 通过流获取文件信息
     *
     * @param obj
     * @return
     */
    private FileInfo getFileInfo(Object obj) {
        String json_info = new String((byte[]) obj);
        if (StringUtils.isNotBlank(json_info)) {
            json_info = json_info.replaceAll("\\\\", "");
            json_info = json_info.substring(1, json_info.length() - 1);
            FileInfo fileInfo = JSON.parseObject(json_info, FileInfo.class);
            return fileInfo;
        }
        return null;
    }


}