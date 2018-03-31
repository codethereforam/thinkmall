package priv.thinkam.thinkmall.validator;

import com.baidu.unbiz.fluentvalidator.ValidationError;
import com.baidu.unbiz.fluentvalidator.Validator;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import priv.thinkam.thinkmall.common.util.AopTargetUtils;

import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 验证字段在数据库是否存在
 * TODO: 使用泛型修改为field类型无关
 *
 * @author thinkam
 * @date 2018/03/28
 */
public class FieldExistValidator<S> extends ValidatorHandler<String> implements Validator<String> {
    private static Logger logger = LoggerFactory.getLogger(FieldExistValidator.class);
    private final Pattern pattern = Pattern.compile("<(.+)>");

    private S service;
    private String field;
    private String errorMsg;

    public FieldExistValidator(S service, String field, String errorMsg) {
        this.service = service;
        this.field = field;
        this.errorMsg = errorMsg;
    }

    @Override
    public boolean validate(ValidatorContext context, String s) {
        if (s == null) {
            return false;
        }
        try {
            Class serviceClass = AopTargetUtils.getTarget(service).getClass();
            String getGenericSuperclassName = serviceClass.getGenericSuperclass().getTypeName();
            Matcher matcher = pattern.matcher(getGenericSuperclassName);
            String exampleClassName = null;
            if (matcher.find()) {
                exampleClassName = matcher.group(1).split(",")[2].trim();
            }
            if(exampleClassName == null) {
                return false;
            }
            Object example = Class.forName(exampleClassName).newInstance();

            Method createCriteriaMethod = example.getClass().getDeclaredMethod("createCriteria");
            Object criteria = createCriteriaMethod.invoke(example);

            //.andXxxEqualTo(s)
            String methodName = "and" + field.substring(0, 1).toUpperCase() + field.substring(1, field.length()) + "EqualTo";
            Class[] exampleInnerClasses = example.getClass().getDeclaredClasses();
            Class criteriaClass = null;
            for (Class c : exampleInnerClasses) {
                if ("Criteria".equals(c.getSimpleName())) {
                    criteriaClass = c;
                }
            }
            logger.debug("criteriaClass: {}", criteriaClass);
            if (criteriaClass == null) {
                return false;
            }
            Method method = criteriaClass.getDeclaredMethod(methodName, String.class);
            method.invoke(criteria, s);

            Method countByExample = AopTargetUtils.getTarget(service).getClass().getSuperclass().getDeclaredMethod
                    ("countByExample", Object
                            .class);
            int count = (int) countByExample.invoke(AopTargetUtils.getTarget(service), example);
            if (count > 0) {
                context.addError(
                        ValidationError.create(errorMsg)
                                .setErrorCode(0)
                                .setField(field)
                                .setInvalidValue(s));
                return false;
            }
        } catch (Exception e) {
            logger.error("检查字段是否存在验证器出错", e);
        }
        return true;
    }
}
