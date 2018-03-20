package priv.thinkam.thinkmall.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import priv.thinkam.thinkmall.model.Administrator;
import priv.thinkam.thinkmall.service.AdministratorService;
import priv.thinkam.thinkmall.util.Md5Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 后台登录控制器
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

    @GetMapping("/login")
    public String login(HttpSession session) {
        // check login session
        if(session.getAttribute(ADMINISTRATOR_IN_SESSION) != null) {
            return "redirect:/manage/index";
        }
        return "/manageLogin.jsp";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request, HttpSession session, ModelMap modelMap) {
        logger.debug("login() get post request...");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // TODO: check field validation
        logger.debug("get params username: {}, password: {}", username, password);
        // check whether username matches password
        List<Administrator> administrators = administratorService.listByUsername(username);
        boolean loginSuccess = false;
        for (Administrator administrator : administrators) {
            String salt = administrator.getSalt();
            String realDigestPassword = administrator.getPassword();
            String digestPassword = Md5Util.digest(password + salt);
            logger.debug("realDigestPassword: {}, digestPassword: {}", realDigestPassword, digestPassword);
            if (digestPassword != null && digestPassword.equalsIgnoreCase(realDigestPassword)) {
                loginSuccess = true;
                session.setAttribute(ADMINISTRATOR_IN_SESSION, administrator);
            }
        }
        return loginSuccess ? "redirect:/manage/index" : "redirect:/manage/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        if(session.getAttribute(ADMINISTRATOR_IN_SESSION) != null) {
            session.removeAttribute(ADMINISTRATOR_IN_SESSION);
        }
        return "redirect:/manage/login";
    }
}
