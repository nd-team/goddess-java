package com.bjike.goddess.common.consumer.interceptor;

import com.alibaba.fastjson.JSON;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.consumer.http.ResponseContext;
import com.bjike.goddess.common.consumer.interceptor.idem.Info;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.dounine.japi.act.ResultImpl;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by huanghuanlai on 2017/2/27.
 */
@Component
public class IdempotencyRequestInterceptor implements HandlerInterceptor {

    private static final String[] RID_METHODS = {"POST","PUT","PATCH","DELETE"};
    private static final LoadingCache<String,Info> loadingCache = CacheBuilder.newBuilder()
            .expireAfterWrite(1, TimeUnit.HOURS)
         .build(new CacheLoader<String, Info>() {
        @Override
        public Info load(String key) throws Exception {
            if(true){
                throw new RuntimeException("请求已失效,请重新操作");
            }
            return null;
        }
    });

    private boolean checkMethod(HttpServletRequest httpServletRequest){
        for(String type : RID_METHODS){
            if(type.equals(httpServletRequest.getMethod())){
                return true;
            }
        }
        return false;
    }

    private Info getInfo(HttpServletRequest httpServletRequest) throws Exception{
        String rid = httpServletRequest.getHeader("rtoken");
        if(StringUtils.isBlank(rid)){
            throw new RuntimeException("请求头rtoken不能为空");
        }
        try {
            return loadingCache.get(rid);
        }catch (Exception e){
            throw new RuntimeException(e.getCause().getMessage());
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        if(checkMethod(httpServletRequest)){
            ResponseContext.get().setContentType("application/json;charset=UTF-8");
            ActResult actResult = new ActResult();
            actResult.setCode(5);
            Info info = null;
            try {
                info = getInfo(httpServletRequest);
            }catch (Exception e){
                actResult.setMsg(e.getMessage());
                ResponseContext.get().getWriter().print(actResult.toString());
                return false;
            }
            if(null==info.getStatus()){
                info.setStatus(Info.Status.PRE);
            }else if(Info.Status.PRE.equals(info.getStatus())){
                actResult.setMsg("请求处理中,请稍后");
                ResponseContext.get().getWriter().print(actResult.toString());
                return false;
            }else if(Info.Status.AFTER.equals(info.getStatus())){
                actResult.setMsg("请求已处理结束");
                ResponseContext.get().getWriter().print(actResult.toString());
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        if(checkMethod(httpServletRequest)){
            Info info = getInfo(httpServletRequest);
            info.setStatus(Info.Status.AFTER);
        }
    }

    public static LoadingCache<String,Info> getLoadingCache(){
        return loadingCache;
    }
}
