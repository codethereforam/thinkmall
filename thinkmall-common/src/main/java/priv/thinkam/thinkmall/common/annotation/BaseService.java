package priv.thinkam.thinkmall.common.annotation;

import java.lang.annotation.*;

/**
 * 初始化继承BaseService的service
 *
 * @author thinkam
 * @date 2018/03/22
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BaseService {
}
