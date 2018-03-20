package priv.thinkam.thinkmall.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import priv.thinkam.thinkmall.dto.Result;
import priv.thinkam.thinkmall.entity.Administrator;
import priv.thinkam.thinkmall.service.AdministratorService;
import priv.thinkam.thinkmall.util.Md5Util;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

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
    public String login(HttpSession session) {
        // check login session
        if (session.getAttribute(ADMINISTRATOR_IN_SESSION) != null) {
            return "redirect:/manage/index";
        }
        return "/manageLogin.jsp";
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
        List<Administrator> administrators = administratorService.listByUsername(username);
        boolean matching = false;
        for (Administrator administrator : administrators) {
            String salt = administrator.getSalt();
            String realDigestPassword = administrator.getPassword();
            String digestPassword = Md5Util.digest(password + salt);
            logger.debug("realDigestPassword: {}, digestPassword: {}", realDigestPassword, digestPassword);
            if (digestPassword != null && digestPassword.equalsIgnoreCase(realDigestPassword)) {
                matching = true;
                session.setAttribute(ADMINISTRATOR_IN_SESSION, administrator);
            }
        }
        if (!matching) {
            return Result.create(false, "您输入的密码和账户名不匹配");
        }
        return Result.createWithoutData(true);
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        if (session.getAttribute(ADMINISTRATOR_IN_SESSION) != null) {
            session.removeAttribute(ADMINISTRATOR_IN_SESSION);
        }
        return "redirect:/manage/login";
    }

    @GetMapping("/captcha")
    public void getCaptcha(HttpServletResponse response, HttpSession session) {
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
            //TODO:处理系统错误，记录日志
        }
    }
}