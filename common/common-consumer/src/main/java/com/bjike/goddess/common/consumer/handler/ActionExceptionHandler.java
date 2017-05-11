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
        if (StringUtils.isNotBlank(e.getMessage()) && e.getMessage().startsWith("Forbid consumer")) {
            actResult.setMsg("服务调用失败");
        } else {
            actResult.setMsg(e.getMessage());
        }
        ResponseContext.writeData(actResult);

        return new ModelAndView();
    }
}
