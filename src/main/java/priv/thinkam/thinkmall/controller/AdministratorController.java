package priv.thinkam.thinkmall.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import priv.thinkam.thinkmall.model.Administrator;
import priv.thinkam.thinkmall.service.AdministratorService;
import priv.thinkam.thinkmall.util.Md5Util;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author thinkam
 * @date 2018/03/19
 */
@Controller
public class AdministratorController {
    private static final Logger logger = LoggerFactory.getLogger(AdministratorController.class);

    @Autowired
    private AdministratorService administratorService;

    @GetMapping("/adminLogin")
    public String login() {
        // TODO: check login session
        return "/adminLogin.jsp";
    }

    @PostMapping("/adminLogin")
    public String login(HttpServletRequest request) {
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
                // TODO: add login session
            }
        }
        return loginSuccess ? "/admin/index.jsp" : "/adminLogin.jsp";
    }
}
