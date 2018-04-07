package priv.thinkam.thinkmall.common.annotation;

import java.lang.annotation.*;

/**
 * 标记需要进行html转义的字段
 * @author thinkam
 * @date 2018/04/06
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HtmlEscape {
}
