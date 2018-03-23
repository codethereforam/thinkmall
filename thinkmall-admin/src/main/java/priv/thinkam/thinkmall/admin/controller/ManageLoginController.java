package priv.thinkam.thinkmall.admin.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import priv.thinkam.thinkmall.common.base.Result;
import priv.thinkam.thinkmall.common.exception.UsernamePasswordNotMatchException;
import priv.thinkam.thinkmall.dao.entity.Administrator;
import priv.thinkam.thinkmall.service.AdministratorService;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 后台登录控制器
 *
 * @author thinkam
 * @date 2018/03/19
 */

@Controller
@RequestMapping("/manage")
public class ManageLoginController {
    private static final Logger logger = LoggerFactory.getLogger(ManageLoginController.class);

    /**
     * 会话的键
     */
    private static final String ADMINISTRATOR_IN_SESSION = "administrator-in-session";

    @Autowired
    private AdministratorService administratorService;
    @Autowired
    private Producer captchaProducer;

    @GetMapping("/login")
    public String login(HttpSession session, ModelMap modelMap, HttpServletRequest request) {
        // check login session
        if (session.getAttribute(ADMINISTRATOR_IN_SESSION) != null) {
            // redirect to original url
            String redirectUrl = request.getHeader("Referer");
            if (redirectUrl == null) {
                redirectUrl = "/manage/index";
            }
            modelMap.addAttribute("errorMsg", "您已登录");
            modelMap.addAttribute("redirectUrl", redirectUrl);
            return "forward:/manage/redirect";
        }
        return "/login.jsp";
    }

    @PostMapping("/login")
    @ResponseBody
    public Result login(HttpServletRequest request, HttpSession session) {
        logger.debug("login() get post request...");
        // check login
        if (session.getAttribute(ADMINISTRATOR_IN_SESSION) != null) {
            return Result.create(false, "您已登录");
        }
        // get field
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String captcha = request.getParameter("captcha");
        logger.debug("get params username: {}, password: {}, captcha: {}", username, password, captcha);
        // check captcha and trim
        if (StringUtils.isBlank(captcha)) {
            return Result.create(false, "请输入验证码");
        }
        captcha = StringUtils.trim(captcha);
        Object kaptchaSessionKey = session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (kaptchaSessionKey == null || !captcha.equalsIgnoreCase((String) kaptchaSessionKey)) {
            return Result.create(false, "验证码错误");
        }
        // check other field
        if (StringUtils.isBlank(username)) {
            return Result.create(false, "请填写用户名");
        }
        if (StringUtils.isBlank(password)) {
            return Result.create(false, "请输入密码");
        }
        // trim other field
        username = StringUtils.trim(username);
        password = StringUtils.trim(password);
        // check whether username matches password
        try {
            Administrator administrator = administratorService.validate(username, password);
            session.setAttribute(ADMINISTRATOR_IN_SESSION, administrator);
        } catch (UsernamePasswordNotMatchException e) {
            return Result.create(false, "您输入的密码和账户名不匹配");
        }
        return Result.createWithoutData(true);
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, ModelMap modelMap) {
        // if not login
        if (session.getAttribute(ADMINISTRATOR_IN_SESSION) == null) {
            modelMap.addAttribute("errorMsg", "您还未登录");
            modelMap.addAttribute("redirectUrl", "/manage/login");
            return "forward:/manage/redirect";
        }
        session.removeAttribute(ADMINISTRATOR_IN_SESSION);
        return "redirect:/manage/login";
    }

    @GetMapping("/captcha")
    public void getCaptcha(HttpServletResponse response, HttpSession session) throws IOException {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        String captchaText = captchaProducer.createText();
        logger.debug("captcha text: {}", captchaText);
        // restore in session
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, captchaText);
        BufferedImage bufferedImage = captchaProducer.createImage(captchaText);
        try (ServletOutputStream out = response.getOutputStream()) {
            ImageIO.write(bufferedImage, "jpg", out);
            out.flush();
        } catch (IOException e) {
            throw new IOException("验证码输出异常", e);
        }
    }
}

