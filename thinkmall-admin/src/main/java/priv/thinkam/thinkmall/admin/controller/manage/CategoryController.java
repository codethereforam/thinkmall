package priv.thinkam.thinkmall.admin.controller.manage;

import com.baidu.unbiz.fluentvalidator.*;
import com.baidu.unbiz.fluentvalidator.jsr303.HibernateSupportedValidator;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import priv.thinkam.thinkmall.common.annotation.XssFilterMethod;
import priv.thinkam.thinkmall.common.annotation.XssFilterMethodArgs;
import priv.thinkam.thinkmall.common.base.Result;
import priv.thinkam.thinkmall.dao.entity.Category;
import priv.thinkam.thinkmall.dao.entity.CategoryExample;
import priv.thinkam.thinkmall.dao.enums.CategoryLevelEnum;
import priv.thinkam.thinkmall.service.CategoryService;
import priv.thinkam.thinkmall.validator.FieldExistValidator;

import javax.validation.Validation;
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
        return Result.create(true, categories);
    }

    @XssFilterMethod
    @PostMapping("/update")
    @ResponseBody
    public Result update(@XssFilterMethodArgs Category category) {
        logger.debug("update() get category{}: ", category);
        logger.debug("update() get category create time: {}", DATE_FORMAT.format(category.getCreateTime()));
        //TODO:check parameter
        // update modifiedTime
        category.setModifiedTime(new Date());
        categoryService.updateByPrimaryKey(category);
        return Result.createWithoutData(true);
    }

    @XssFilterMethod
    @PostMapping("/add")
    @ResponseBody
    public Result add(@XssFilterMethodArgs Category category) {
        logger.debug("add() get category: {}", category);
        // trim relative params
        category.setName(StringUtils.trim(category.getName()));
        category.setDescription(StringUtils.trim(category.getDescription()));
        // validate parameter
        javax.validation.Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        ComplexResult result = FluentValidator.checkAll()
                .on(category, new HibernateSupportedValidator<Category>().setHiberanteValidator(validator))
                .on(category.getName(), new FieldExistValidator<>(categoryService, "name", "已存在该类别"))
                .on(category.getParentId(), new ValidatorHandler<Long>() {
                    @Override
                    public boolean validate(ValidatorContext context, Long parentId) {
                        if (parentId == 0) {
                            return true;
                        }
                        ValidationError error = ValidationError.create("父类别非法操作，否则请联系管理员")
                                .setErrorCode(0)
                                .setField("parentId")
                                .setInvalidValue(parentId);
                        Category parent = categoryService.selectByPrimaryKey(parentId);
                        // 父类别必须存在且不能为三级类别
                        if (parent == null || parent.getLevel() == CategoryLevelEnum.THREE) {
                            context.addError(error);
                            return false;
                        }
                        return true;
                    }
                })
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return Result.create(false, result.getErrors());
        }
        Date now = new Date();
        category.setCreateTime(now);
        category.setModifiedTime(now);
        logger.debug("add() category to be persisted: {}", category);
        // persist category
        categoryService.insert(category);
        return Result.createWithoutData(true);
    }
}
