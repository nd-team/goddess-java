package user_register_code;

import com.dounine.corgi.jta.impl.JTAApiFilterImpl;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by huanghuanlai on 2016/12/19.
 */
@Aspect
@Component
public class MyJTAFilterImpl extends JTAApiFilterImpl {

    @Around("execution(* com.bjike.goddess.user.register.service..*.*(..))")
    public Object aroundMethod(ProceedingJoinPoint pjd) throws Throwable {
        return super.aroundMethod(pjd);
    }
}
