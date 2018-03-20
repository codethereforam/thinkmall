package priv.thinkam.thinkmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author thinkam
 * @date 2018/03/20
 */
@Controller
public class WebController {

    @GetMapping("/index")
    public String index() {
        return "/web/index.jsp";
    }
}
