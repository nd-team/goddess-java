package com.bjike.goddess.contractcommunicat.util;

import com.bjike.goddess.common.api.exception.SerException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 导出Excel
 *
 * @Author: [Jason]
 * @Date: [17-5-12 下午2:50]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ExportExcelUtil {

    /**
     * 导出Excel
     *
     * @param path 导出路径
     */
    public static void export(String path, byte[] bytes) throws SerException {
        File out = new File(path);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(out);
            fos.write(bytes);
        } catch (FileNotFoundException e) {
            throw new SerException("找不到对应的文件");
        } catch (IOException e) {
            throw new SerException("字节流写出本地文件异常");
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                throw new SerException("输出流关闭异常");
            }
        }
    }
}
