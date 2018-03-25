package priv.thinkam.thinkmall.admin.controller.manage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import priv.thinkam.thinkmall.common.base.Result;
import priv.thinkam.thinkmall.dao.entity.Category;
import priv.thinkam.thinkmall.dao.entity.CategoryExample;
import priv.thinkam.thinkmall.service.CategoryService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 类别控制器
 *
 * @author thinkam
 * @date 2018/03/23
 */
@Controller
@RequestMapping("/manage/category")
public class CategoryController {
    private final Logger logger = LoggerFactory.getLogger(CategoryController.class);
    private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

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

    @PostMapping("/update")
    @ResponseBody
    public Result update(Category category) {
        logger.debug("get category: " + category);
        logger.debug("get category create time: {}", DATE_FORMAT.format(category.getCreateTime()));
        //TODO:check parameter
        // update modifiedTime
        category.setModifiedTime(new Date());
        // check and set parent isLeaf
        Long parentId = category.getParentId();
        Category parentCategory = categoryService.selectByPrimaryKey(parentId);
        if(parentCategory.getLeaf()) {
            parentCategory.setLeaf(false);
            categoryService.updateByPrimaryKey(parentCategory);
        }
        return Result.createWithoutData(true);
    }
}
