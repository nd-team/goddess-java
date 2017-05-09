package com.bjike.goddess.common.consumer.handler;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.consumer.http.ResponseContext;
import com.bjike.goddess.common.consumer.restful.ActResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * action 异常处理
 *
 * @Author: [huanghuanlai]
 * @Date: [2017-01-15 09:59]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
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
        if (e instanceof ActException) {
            httpServletResponse.setStatus(SUCCESS_STATUS);
        } else {
            httpServletResponse.setStatus(EXCEPTION_STATUS);
            actResult.setCode(EXCEPTION_CODE);
            if ("notLogin".equals(e.getMessage())) {
                actResult.setCode(403);
            }
            LOGGER.error(e.getMessage());
        }
        actResult.setMsg(e.getMessage());

        String msg = handlerServer(e);
        if (null != msg) {
            actResult.setMsg(msg);
        } else {
            msg = handlerJpa(e);
            if (StringUtils.isNotBlank(msg)) {
                actResult.setMsg(msg);
            }
        }
        ResponseContext.writeData(actResult);
        return new ModelAndView();
    }

    /**
     * 处理服务未开启
     *
     * @param e
     */
    private String handlerServer(Exception e) {
        if (StringUtils.isNotBlank(e.getMessage()) && e.getMessage().startsWith("Forbid consumer")) {
            return "服务调用失败";
        }
        return null;
    }

    /**
     * 处理数据库异常(加入断路器则不再处理异常,由断路器处理)
     *
     * @param e
     */
    private String handlerJpa(Exception e) {
        String message = e.getMessage();
        String result = null;
        if ((result = constraint(message)) != null) {
            return result;
        } else if ((result = tooLong(message)) != null) {
            return result;
        }
        return result;
    }

    /**
     * 唯一约束异常
     *
     * @param message
     * @return
     */
    private String constraint(String message) {
        String constraint = StringUtils.substringAfterLast(message, "java.sql.SQLIntegrityConstraintViolationException: Duplicate entry");
        if (StringUtils.isNotBlank(constraint)) {
            constraint = StringUtils.substringBefore(constraint, "for key");
            return constraint + "已存在,请不要重复添加!";
        }
        return null;
    }

    /**
     * 唯一约束异常
     *
     * @param message
     * @return
     */
    private static String tooLong(String message) {
        String constraint = StringUtils.substringAfterLast(message, "MysqlDataTruncation: Data truncation: Data too long for column");
        if (StringUtils.isNotBlank(constraint)) {
            constraint = StringUtils.substringBefore(constraint, "at row");
            return constraint + "超出长度!";
        }
        return null;
    }

}
