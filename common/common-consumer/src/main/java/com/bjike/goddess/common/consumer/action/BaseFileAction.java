package com.bjike.goddess.common.consumer.action;

import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.fastjson.JSON;
import com.bjike.goddess.common.api.constant.RpcCommon;
import com.bjike.goddess.common.api.exception.SerException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 上传文件的action继承该类
 *
 * @Author: [liguiqin]
 * @Date: [2017-04-18 15:53]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public abstract class BaseFileAction {
    /**
     * 支持预览的文件类型
     */
    private static final String[] SUFFIX = new String[]{"xls", "xlsx", "docx", "dotx", "pptx", "xlsm", "xlt"};

    /**
     * 生成预览url
     *
     * @param bytes    文件流
     * @param fileName 包含后缀
     * @return url
     */
    public String previewUrl(byte[] bytes, String fileName) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        String[] fileInfo = fileName.split("\\.");
        if (fileInfo.length == 1) {
            throw new RuntimeException("请添加文件后缀.");
        } else {
            validatedSuffix(fileInfo[1]);
        }
        if (isContainChinese(fileName) || fileName.length() <= 3) {
            fileName += LocalDate.now().toString();
        }
        String url = null;
        try {
            File file = File.createTempFile(fileName, "." + fileInfo[1]);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
            httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost("http://192.168.0.170/WopiHost/api/upload");
            FileBody fileBody = new FileBody(file);
//            StringBody username = new StringBody("Scott", ContentType.create(
//                    "text/plain", Consts.UTF_8));
            HttpEntity reqEntity = MultipartEntityBuilder.create()
                    .addPart("file", fileBody)
//                    .addPart("username", username)
                    .build();
            httpPost.setEntity(reqEntity);
            response = httpClient.execute(httpPost);
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                url = EntityUtils.toString(resEntity, Charset.forName("UTF-8"));
            }
            EntityUtils.consume(resEntity);  // 销毁
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
                if (bos != null) {
                    bos.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return url;
    }


    /**
     * 输出文件
     *
     * @param response
     * @param bytes
     * @param fileName
     * @throws IOException
     */
    public void writeOutFile(HttpServletResponse response, byte[] bytes, String fileName) throws IOException {
        fileName = fileName.replaceAll(" ", "");
        response.reset();
        response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
        response.addHeader("Content-Length", "" + bytes.length);
        OutputStream os = new BufferedOutputStream(response.getOutputStream());
        response.setContentType("application/octet-stream");
        os.write(bytes);// 输出文件
        os.flush();
        os.close();
    }

    /**
     * 上传文件调用该方法获得文件流
     * 需要单独处理path
     *
     * @param request
     * @param path
     * @return
     * @throws SerException
     */
    public List<InputStream> getInputStreams(HttpServletRequest request, String path) throws SerException {
        return initInputStreams(request, path);
    }

    /**
     * 上传文件调用该方法获得文件流
     * InputStreams 顺序为文件信息,文件
     * @param request
     * @return
     * @throws SerException
     */
    public List<InputStream> getInputStreams(HttpServletRequest request) throws SerException {
        String path = request.getParameter("path");
        return initInputStreams(request, path);
    }

    /**
     * 上传文件转bytes
     *
     * @param request
     * @return 文件列表[字节]
     * @throws SerException
     */
    public List<byte[]> getBytes(HttpServletRequest request) throws SerException {
        try {
            List<MultipartFile> multipartFiles = getMultipartFile(request);
            List<byte[]> bytesList = new ArrayList<>(multipartFiles.size());
            for (MultipartFile mf : multipartFiles) {
                byte[] bytes = IOUtils.toByteArray(mf.getInputStream());
                bytesList.add(bytes);
            }
            return bytesList;
        } catch (IOException e) {
            throw new SerException(e.getMessage());
        }
    }


    private List<InputStream> initInputStreams(HttpServletRequest request, String path) throws SerException {
        List<MultipartFile> multipartFiles = getMultipartFile(request);
        List<InputStream> inputStreams = null;
        String token = request.getParameter(RpcCommon.STORAGE_TOKEN);
        if (StringUtils.isBlank(token)) {
            Object obj = request.getAttribute(RpcCommon.STORAGE_TOKEN);
            if (null != obj) {
                token = obj.toString();
            }else {
                token =  RpcContext.getContext().getAttachment(RpcCommon.STORAGE_TOKEN);
            }

        }
        if (null != multipartFiles) {
            inputStreams = new ArrayList<>(multipartFiles.size() * 2);
            try {
                for (MultipartFile mf : multipartFiles) {
                    String fileInfo = "{\"fileName\":\"%s\",\"path\":\"%s\",\"storageToken\":\"%s\"}";
                    fileInfo = String.format(fileInfo, mf.getOriginalFilename(), path, token);
                    InputStream is_fileInfo = new ByteArrayInputStream(JSON.toJSONBytes(fileInfo));
                    InputStream file = mf.getInputStream();
                    inputStreams.add(is_fileInfo);
                    inputStreams.add(file);
                }
            } catch (IOException e) {
                throw new SerException(e.getMessage());
            }

        }
        return inputStreams;
    }

    /**
     * 通过request获取上传文件
     *
     * @param request
     * @return
     * @throws SerException
     */
    private List<MultipartFile> getMultipartFile(HttpServletRequest request) throws SerException {

        if (null != request && !isMultipartContent(request)) {
            throw new SerException("上传表单不是multipart/form-data类型");
        }
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request; // 转换成多部分request
        return multiRequest.getFiles("files");

    }

    /**
     * 上传是否合理
     *
     * @param request
     * @return
     */
    private boolean isMultipartContent(HttpServletRequest request) {
        if (!"post".equals(request.getMethod().toLowerCase())) {
            return false;
        }

        String contentType = request.getContentType();  //获取Content-Type
        if ((contentType != null) && (contentType.toLowerCase().startsWith("multipart/"))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 格式验证
     *
     * @param suffix
     */
    private void validatedSuffix(String suffix) {
        boolean exists = false;
        for (String str : SUFFIX) {
            if (str.equalsIgnoreCase(suffix)) {
                exists = true;
            }
        }
        if (!exists) {
            throw new RuntimeException("不支持该格式文件:" + suffix);
        }
    }

    /**
     * 是否包含中文
     *
     * @param str
     * @return
     */
    private boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }


}
