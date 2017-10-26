package com.bjike.goddess.organize.action.organize;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 老系统手机版本控制
 *
 * @Author: [tanghaixiang]
 * @Date: [2017-03-08 17:43]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("oldissp")
public class OldIsspAct {



    /**
     * 老系统手机版本控制
     *
     * @version v1
     */
    @GetMapping("v1/phone/version")
    public Result phoneVersionFromOldIssp( HttpServletRequest request) throws ActException {

        String uriAPI = "https://issp.bjike.com/admin/update/versionInfo/getNewVersion?name=ISSP";
        JSONObject result= new JSONObject();
        HttpGet httpRequst = new HttpGet(uriAPI);
        try {
            HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequst);//其中HttpGet是HttpUriRequst的子类
            if(httpResponse.getStatusLine().getStatusCode() == 200)
            {
                HttpEntity httpEntity = httpResponse.getEntity();
                result = JSON.parseObject(EntityUtils.toString(httpEntity));//取出应答字符串
                // 一般来说都要删除多余的字符
//                    result.replaceAll("\r", "");//去掉返回结果中的"\r"字符，否则会在结果字符串后面显示一个小方格
            }
            else
                httpRequst.abort();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ActResult.initialize(result);

    }



}