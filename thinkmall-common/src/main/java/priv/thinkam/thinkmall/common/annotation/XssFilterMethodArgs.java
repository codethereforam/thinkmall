package priv.thinkam.thinkmall.common.annotation;

import java.lang.annotation.*;

/**
 * 标记方法参数需要进行XSS防御
 *
 * @author thinkam
 * @date 2018/04/06
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface XssFilterMethodArgs {
}
