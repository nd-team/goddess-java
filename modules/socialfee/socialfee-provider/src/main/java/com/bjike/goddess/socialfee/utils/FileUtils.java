package com.bjike.goddess.socialfee.utils;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author: [tanghaixiang]
 * @Date: [2017-04-20 18:35]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public final class FileUtils {

    /**
     * 将Workbook保存到本地
     *
     * @param wb
     *            Excel工作
     * @param dirPath
     *            保存路径
     * @param fileName
     *            文件名
     * @return 保存成功后的本地文件
     */
    public static File saveWBFile(Workbook wb, String dirPath, String fileName) {
        FileOutputStream fos = null;
        if (null != wb && StringUtils.isNoneBlank(dirPath) && StringUtils.isNoneBlank(fileName)) {
            File dir = new File(dirPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String _filePath = new StringBuffer(dirPath).append("/")
                    .append(FilenameUtils.getBaseName(fileName)).append("-").append(System.nanoTime())
                    .append((fileName.substring( fileName.lastIndexOf( "." ) ).toLowerCase())).toString();
            try {
                fos = new FileOutputStream(_filePath);
                wb.write(fos);
                wb.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (null != fos) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return new File(_filePath);
        }
        return null;
    }

    /**
     * 获取文件下载地址
     *
     * @param file
     *            本地文件
     * @return 文件下载地址
     */
    public static String getDownloadPath(File file) {
        if (null == file || (null != file && !file.exists())) {
            return null;
        }
        String filePath = file.getAbsolutePath()
                .substring((new File("").getAbsolutePath()).length());
        return filePath.replace( "\\", "/" );
    }

    /**
     * 获取相对files文件地址
     * @param res 文件相对地址
     * @return 完整地址
     */
    public static String getFilesRePath(String res){
        String path = new StringBuilder("").append("files/").append(res).toString();
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
        return path;
    }
}
