package com.bjike.goddess.common.consumer.interceptor;

import com.bjike.goddess.common.consumer.http.ResponseContext;
import com.bjike.goddess.common.consumer.restful.ActResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by huanghuanlai on 2017/1/14.
 */
@Aspect
@Component
public class ActionJSR303 {

    @Autowired
    private Validator validator;

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)" +
            " || @annotation(org.springframework.web.bind.annotation.GetMapping)" +
            " || @annotation(org.springframework.web.bind.annotation.PostMapping)" +
            " || @annotation(org.springframework.web.bind.annotation.PutMapping)" +
            " || @annotation(org.springframework.web.bind.annotation.DeleteMapping)" +
            " || @annotation(org.springframework.web.bind.annotation.PatchMapping)"
    )
    public void pointCut() {

    }

    @Around("pointCut()")
    public Object doAround(ProceedingJoinPoint joinPoint)
            throws Throwable {
        BindingResult result = null;
        MethodSignature methodSignature = (MethodSignature) joinPoint
                .getSignature();
        Method method = methodSignature.getMethod();
        Annotation[] annotationList = method.getAnnotations();
        Annotation[][] argAnnotations = method.getParameterAnnotations();
        String[] argNames = methodSignature.getParameterNames();
        Object[] args = joinPoint.getArgs();//获取参数值

        if(method.getName().equals("errorHtml")){//springmvc 错误请求
            return joinPoint.proceed(args);
        }


        methodSignature = null;//gc

        method = null;//gc
        for (int i = 0, len = args.length; i < len; i++) {
//            if (hasValidAnnotations(argAnnotations[i])) {
//                Object ret = validateArg(args[i], argNames[i]);
//                if (null != ret) {
//                    return ret;
//                }
//            }
            Object object = args[i];
            if (null != object) {
                if (null == result && object instanceof BindingResult) {
                    result = (BindingResult) object;
                }
                if (null != argAnnotations[i] && argAnnotations[i].length > 0) {//检测是否带注解
                    if (null != result) {
                        break;
                    }
                }
            }
        }

        if (null != result && writeResult(result)) {
            return null;
        }

        annotationList = null;//gc
        argAnnotations = null;//gc
        return joinPoint.proceed(args);
    }

    protected boolean writeResult(BindingResult result) {
        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            if (null != fieldErrors && fieldErrors.size() > 0) {
                ActResult actResult = new ActResult();
                actResult.setCode(1);
                actResult.setMsg(fieldErrors.get(0).getField() + ":" + fieldErrors.get(0).getDefaultMessage());
                ResponseContext.writeData(actResult.toString());
                return true;
            }
        }
        return false;
    }

    private boolean hasValidAnnotations(Annotation[] annotations) {
        boolean hasValid = false;
        for (Annotation annotation : annotations) {
            if (Valid.class.isInstance(annotation)) {
                hasValid = true;
                break;
            }
        }
        return hasValid;
    }

    private boolean hasRequestBodyAnnotations(Annotation[] annotations) {
        for (Annotation annotation : annotations) {
            if (ResponseBody.class.isInstance(annotation)) {
                return true;
            }
        }
        return false;
    }

    private ActResult validateArg(Object arg, String argName) {
        BindingResult result = getBindingResult(arg, argName);
        validator.validate(arg, result);
        if (result.hasErrors()) {
            ActResult actResult = new ActResult();
            actResult.setCode(1);
            List<ObjectError> objectErrors = result.getAllErrors();
            actResult.setMsg(objectErrors.get(0).getDefaultMessage());
            result = null;//gc
            return actResult;
        }
        result = null;//gc
        return null;
    }

    private BindingResult getBindingResult(Object target, String targetName) {
        return new BeanPropertyBindingResult(target, targetName);
    }
}
