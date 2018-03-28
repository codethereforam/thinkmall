package priv.thinkam.thinkmall.validator;

import com.baidu.unbiz.fluentvalidator.ValidationError;
import com.baidu.unbiz.fluentvalidator.Validator;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import priv.thinkam.thinkmall.dao.entity.CategoryExample;
import priv.thinkam.thinkmall.service.CategoryService;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 验证字段在数据库是否存在
 * TODO: 使用泛型修改为通用的类
 * @author thinkam
 * @date 2018/03/28
 */
public class FieldExistValidator extends ValidatorHandler<String> implements Validator<String> {
    private CategoryService categoryService;
    private String field;
    private String errorMsg;

    private static Logger logger = LoggerFactory.getLogger(FieldExistValidator.class);

    public FieldExistValidator(CategoryService categoryService, String field, String errorMsg) {
        this.categoryService = categoryService;
        this.field = field;
        this.errorMsg = errorMsg;
    }

    @Override
    public boolean validate(ValidatorContext context, String s) {
        if (s == null) {
            return false;
        }
        CategoryExample categoryExample = new CategoryExample();
        CategoryExample.Criteria criteria = categoryExample.createCriteria();
        //.andNameEqualTo(s)
        String methodName = "and" + field.substring(0, 1).toUpperCase() + field.substring(1, field.length()) + "EqualTo";
        try {
            Method method = CategoryExample.Criteria.class.getDeclaredMethod(methodName, String.class);
            method.invoke(criteria, s);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            logger.error("方法反射调用错误", e);
        }
        if (categoryService.countByExample(categoryExample) > 0) {
            context.addError(
                    ValidationError.create(errorMsg)
                            .setErrorCode(0)
                            .setField(field)
                            .setInvalidValue(s));
            return false;
        }
        return true;
    }
}
