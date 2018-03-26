package priv.thinkam.thinkmall.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author thinkam
 * @date 2018/03/20
 */
@Controller
@RequestMapping("/manage")
public class ManageController {

    @GetMapping("/index")
    public String index() {
        return "/manage/index.jsp";
    }

    @GetMapping("/redirect")
    public String redirect() {
        return "/redirect.jsp";
    }

    @GetMapping("/errorPage")
    public String errorPage() {
        return "/500.jsp";
    }
}
