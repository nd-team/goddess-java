package com.bjike.goddess.storage.utils;

import com.bjike.goddess.storage.enums.FileType;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @Author: [liguiqin]
 * @Date: [2017-03-27 16:25]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class FileInfoUtils {


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
