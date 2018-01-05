package com.bjike.goddess.user.action.speech;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.user.utils.LatSpeechViewUtil;
import com.bjike.goddess.user.utils.ShareCodeUtil;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.util.List;

/**
 * 语音识别转文字
 * @Author:[tanghaixiang]
 * @Date: [2017-01-14 15:47]
 * @Description: [语音识别转文字]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@DefaultProperties
@RestController
public class LatSpeechAct extends BaseFileAction {


    /**
     * 语音识别
     *
     * @param request httpRequest 语音流
     * @version v1
     * @desc 只识别pcm格式且语音时间60秒内的语音
     */
    @PostMapping("v1/latSpeech/recogize")
    public Result latSpeechRecogize( javax.servlet.http.HttpServletRequest request) throws ActException {
        try {
            //跟前端约定好 ，文件路径是列表id
            // /id/....
            //上传图片-发票图片
            String result = "";
            String path = "/latspeech";
            List<InputStream> inputStreams = getInputStreams(request, path);
            if( inputStreams == null ){
                throw new ActException("请传一个音频流文件");
            }else{
                int fileCount = 1;
                int count = inputStreams.size();
                if (count >= 2) {
                    count /= 2;
                }
                for (int i = 0; i < count; i++) {
                    InputStream o_file =  inputStreams.get(fileCount); //获取上传文件bytes
                    result = LatSpeechViewUtil.recognize(o_file);
                    fileCount += 2;
                }
            }
            return ActResult.initialize(result);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    @GetMapping("v1/ShareCodeUtil/code")
    public Result code() throws ActException {
        String result = "";
        result = ShareCodeUtil.generateShortUuid();
        return ActResult.initialize(result);
    }


}