package priv.thinkam.thinkmall.admin.controller.manage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import priv.thinkam.thinkmall.service.CategoryService;

/**
 * 类别控制器
 * @author thinkam
 * @date 2018/03/23
 */
@Controller
@RequestMapping("/manage/category")
public class CategoryController {
    private Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/index")
    public String index() {
        return "/manage/category/index.jsp";
    }
}
