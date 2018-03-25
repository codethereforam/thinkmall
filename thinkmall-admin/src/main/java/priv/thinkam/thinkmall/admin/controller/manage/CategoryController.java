package priv.thinkam.thinkmall.admin.controller.manage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import priv.thinkam.thinkmall.common.base.Result;
import priv.thinkam.thinkmall.dao.entity.Category;
import priv.thinkam.thinkmall.dao.entity.CategoryExample;
import priv.thinkam.thinkmall.service.CategoryService;

import java.util.List;

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

    @GetMapping("/list")
    @ResponseBody
    public Result list() {
        List<Category> categories = categoryService.selectByExample(new CategoryExample());
        categories.forEach(e -> logger.debug(e.toString()));
        return Result.create(true, categories);
    }


}
