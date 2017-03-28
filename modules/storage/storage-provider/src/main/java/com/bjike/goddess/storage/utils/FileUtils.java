package com.bjike.goddess.storage.utils;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.storage.constant.PathCommon;
import com.bjike.goddess.storage.enums.FileType;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @Author: [liguiqin]
 * @Date: [2017-03-28 10:15]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class FileUtils {

    public static byte[] FileToByte(String filePath) throws SerException {
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] b = new byte[1024];
                int n;
                while ((n = fis.read(b)) != -1) {
                    bos.write(b, 0, n);
                }
                fis.close();
                bos.close();
                buffer = bos.toByteArray();
            } else {
                throw new SerException("文件不存在！");
            }

        } catch (FileNotFoundException e) {
            throw new SerException(e.getMessage());
        } catch (IOException e) {
            throw new SerException(e.getMessage());
        }
        return buffer;
    }


    public static File byteToFile(byte[] buffer, String path, String fileName) throws SerException {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(path);
            if (!dir.exists()) { //&& dir.isDirectory() 不自动创建文件夹
                dir.mkdirs();
            }
            file = new File(path + PathCommon.SEPARATOR + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(buffer);
        } catch (Exception e) {
            throw new SerException(e.getMessage());
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    throw new SerException(e.getMessage());
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    throw new SerException(e.getMessage());
                }
            }
        }
        return file;
    }



    /**
     * 获取文件类型
     *
     * @param file
     * @return
     */
    public static FileType getFileType(File file) {
        String suffix = file.getName().split("\\.")[1];
        suffix = suffix.toUpperCase();
        FileType type = null;
        try {
            type = FileType.valueOf(suffix);
        } catch (Exception e) {
            type = FileType.UNKNOW;
        }
        return type;
    }

    /**
     * 获取文件大小
     *
     * @param file
     * @return
     */
    public static String getFileSize(File file) {
        long size = file.length();
        if (size > 1000) {
            double kb = new BigDecimal(size).divide(new BigDecimal(1000)).doubleValue();
            if (kb > 1000) {
                double mb = new BigDecimal(kb).divide(new BigDecimal(1000)).doubleValue();
                if (mb > 1000) {
                    return getBySeconds(mb / 1000) + "GB";
                } else {
                    return getBySeconds(mb) + "MB";
                }
            } else {
                return getBySeconds(kb) + "KB";
            }

        } else {
            return size + "B";
        }
    }


    /**
     * 保留两位小数
     *
     * @return
     */
    private  static  double getBySeconds(double val) {
        return new BigDecimal(val).setScale(2, RoundingMode.UP).doubleValue();
    }


}
