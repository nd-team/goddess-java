package com.bjike.goddess.bankrecords.util;

import com.bjike.goddess.common.api.exception.SerException;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author: [Jason]
 * @Date: [17-4-24 上午11:29]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class FileUtil {

    /**
     * 把字节数组保存为一个文件  
     *
     * @param byteArray
     * @param outputFile
     * @fileurn
     */
    public static File getFileFromBytes(byte[] byteArray, String outputFile) throws SerException{
        File file = null;
        BufferedOutputStream stream = null;
        try {
            file = new File(outputFile);
            FileOutputStream fstream = new FileOutputStream(file);
            stream = new BufferedOutputStream(fstream);
            stream.write(byteArray);
        } catch (Exception e) {
            throw new SerException(e.getMessage());
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new SerException(e.getMessage());
                }
            }
        }
        return file;
    }
}
