package priv.thinkam.thinkmall.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局控制器辅助类，用来处理全局的异常等
 *
 * @author thinkam
 * @date 2018/03/21
 */
@ControllerAdvice
public class GlobalControllerAdvice {
    private static final Logger logger = LoggerFactory.getLogger(GlobalControllerAdvice.class);

    /**
     * 统一异常处理
     *
     * @param request   请求对象
     * @param exception 异常
     */
    @ExceptionHandler
    public String exceptionHandler(HttpServletRequest request, Exception exception) {
        logger.error("统一异常处理：", exception);
        request.setAttribute("ex", exception);
        return "/error.jsp";
    }
}
