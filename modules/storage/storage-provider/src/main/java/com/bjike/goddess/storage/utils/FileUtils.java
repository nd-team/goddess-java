package com.bjike.goddess.storage.utils;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.storage.constant.PathCommon;

import java.io.*;

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


    public static void byteToFile(byte[] buffer, String path, String fileName) throws SerException {
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
    }

}
