package priv.thinkam.thinkmall.admin.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.util.HtmlUtils;
import priv.thinkam.thinkmall.common.annotation.HtmlEscape;
import priv.thinkam.thinkmall.common.annotation.XssFilterMethodArgs;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * XSS防御切面
 *
 * @author thinkam
 * @date 2018/04/05
 */
@Aspect
@Component
public class XssAspect {
    private static final Logger logger = LoggerFactory.getLogger(XssAspect.class);

    @Around("@annotation(priv.thinkam.thinkmall.common.annotation.XssFilterMethod)")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取切入方法的参数
        Object[] args = joinPoint.getArgs();
        // 获取切入方法
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        // 获取参数的类型
        Class<?>[] paramTypes = method.getParameterTypes();
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < paramTypes.length; i++) {
            if(parameters[i].getAnnotation(XssFilterMethodArgs.class) == null) {
                continue;
            }
            if (paramTypes[i].equals(String.class)) {
                if (args[i] != null && args[i] != "") {
                    args[i] = HtmlUtils.htmlEscape((String) args[i]);
                }
            } else {
                if (args[i] != null) {
                    Class<?> clazz = args[i].getClass();
                    Field[] fields = clazz.getDeclaredFields();
                    for (Field field : fields) {
                        if (field.getType().equals(String.class) && field.getAnnotation(HtmlEscape.class) != null) {
                            logger.debug(field.getName());
                            String capitalizedFieldName = field.getName().substring(0, 1).toUpperCase() + field
                                    .getName().substring(1);
                            String getMethodName = "get" + capitalizedFieldName;
                            Method getMethod = clazz.getDeclaredMethod(getMethodName);
                            String result = HtmlUtils.htmlEscape((String) getMethod.invoke(args[i]));

                            String setMethodName = "set" + capitalizedFieldName;
                            Method setMethod = clazz.getDeclaredMethod(setMethodName, String.class);
                            setMethod.invoke(args[i], result);
                        }
                    }
                }
            }
        }
        return joinPoint.proceed();
    }

}
