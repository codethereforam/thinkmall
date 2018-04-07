package priv.thinkam.thinkmall.admin.interceptor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 日志记录切面
 *
 * @author thinkam
 * @date 2018/04/07
 */
@Aspect
@Component
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Before("execution(* *..controller..*.*(..))")
    public void doBefore(JoinPoint joinPoint) {
        // 获取切入方法的参数
        Object[] args = joinPoint.getArgs();
        // 获取切入方法
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        // 获取参数名
        ParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
        String[] parameterNames = parameterNameDiscoverer.getParameterNames(method);
        StringBuilder builder = new StringBuilder();
        builder.append("requested method -- ")
                .append(method.getDeclaringClass().getSimpleName())
                .append(".")
                .append(method.getName())
                .append(" | params -- ");
        for (int i = 0; i < parameterNames.length; i++) {
            builder.append(parameterNames[i])
                    .append(": ")
                    .append(args[i])
                    .append(" | ");
        }
        logger.debug(builder.toString());
    }

}
