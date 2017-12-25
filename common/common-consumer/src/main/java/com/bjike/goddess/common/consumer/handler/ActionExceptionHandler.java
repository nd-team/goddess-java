package com.bjike.goddess.common.consumer.handler;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.consumer.http.ResponseContext;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by huanghuanlai on 2017/1/14.
 */
@Component
public class ActionExceptionHandler extends AbstractHandlerExceptionResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActionExceptionHandler.class);
    private static final String JSON_CONTEXT = "text/html;charset=utf-8";
    private static final int SUCCESS_STATUS = 200;
    private static final int EXCEPTION_STATUS = 500;
    private static final int EXCEPTION_CODE = 1;

    @Override
    protected ModelAndView doResolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        ActResult actResult = new ActResult();
        httpServletResponse.setContentType(JSON_CONTEXT);
        if (!isChinese(e.getMessage())) {
            actResult.setMsg("服务器错误:"+e.getMessage());
        } else {
            actResult.setMsg(e.getMessage());
        }
        e.printStackTrace();
        if (e instanceof ActException || e instanceof HystrixBadRequestException) {
            actResult.setCode(1);
            httpServletResponse.setStatus(SUCCESS_STATUS);
        } else {
            httpServletResponse.setStatus(EXCEPTION_STATUS);
            actResult.setCode(EXCEPTION_CODE);
        }
        if ("expire".equals(e.getMessage())) {
            actResult.setCode(401);
            actResult.setMsg("登录已过期!");
        } else if ("notLogin".equals(e.getMessage())) {
            actResult.setCode(403);
            actResult.setMsg("用户未登录!");
        }
        if (StringUtils.isNotBlank(e.getMessage()) && e.getMessage().startsWith("Forbid consumer")) {
            LOGGER.error(e.getMessage());
            actResult.setMsg("服务调用失败:"+e.getMessage());
        }
        /**
         * 处理数据库异常
         */
        String msg = handleJapException(e);
        if (StringUtils.isNotBlank(msg)) {
            actResult.setMsg(msg);
        }
        ResponseContext.writeData(actResult);
        return new ModelAndView();
    }

    private String handleJapException(Throwable throwable) {
        String msg = throwable.getMessage();
        String result = null;
        result = StringUtils.substringAfter(msg, "Caused by: java.sql.SQLIntegrityConstraintViolationException:");

        if (StringUtils.isNotBlank(result)) {
            /**
             * 处理唯一约束
             */
            result = StringUtils.substringBefore(result, "' for key");
            result = StringUtils.substringAfter(result, "Duplicate entry '");
            if (StringUtils.isNotBlank(result)) {
                return "[" + result + "]该名称已被占用!";
            }
            /**
             * 处理非空约束
             */

            result = StringUtils.substringBefore(result, "' cannot be null");
            result = StringUtils.substringAfter(result, "Column '");
            if (StringUtils.isNotBlank(result)) {
                return "[" + result + "]不能为空!";
            }
        }
        result = StringUtils.substringAfter(msg, "com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column '");
        if (StringUtils.isNotBlank(result)) {
            /**
             * 数据长度
             */
            result = StringUtils.substringBefore(result, "' at row");
            return "[" + result + "]超出长度范围!";
        }
        return result;
    }

    private static final String REG = "[\u4e00-\u9fa5]";

    public boolean isChinese(String str) {
        if (StringUtils.isNotBlank(str)) {
            Pattern p = Pattern.compile(REG);
            Matcher m = p.matcher(str);
            if (m.find()) {
                return true;
            }
        }
        return false;
    }

}
