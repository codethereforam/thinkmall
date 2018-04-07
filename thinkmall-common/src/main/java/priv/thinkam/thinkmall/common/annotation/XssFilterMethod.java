package priv.thinkam.thinkmall.common.annotation;

import java.lang.annotation.*;

/**
 * 标记方法需要进行XSS防御
 *
 * @author thinkam
 * @date 2018/04/06
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface XssFilterMethod {
}
