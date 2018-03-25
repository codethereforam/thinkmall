package priv.thinkam.thinkmall.dao.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import priv.thinkam.thinkmall.common.base.BaseEnum;
import priv.thinkam.thinkmall.common.util.EnumUtil;
import priv.thinkam.thinkmall.dao.enums.CategoryLevelEnum;

/**
 * 字符串转枚举基类转化器
 * @author thinkam
 * @date 2018/03/25
 */
public class StringToBaseEnumConverter implements ConverterFactory<String, BaseEnum> {

    @Override
    public <T extends BaseEnum> Converter<String, T> getConverter(Class<T> targetType) {
        if(!targetType.isEnum()) {
            throw new UnsupportedOperationException("只支持转换到枚举类型");
        }
        return new StringToEnum(targetType);
    }

    private class StringToEnum<T extends BaseEnum> implements Converter<String, T> {

        private final Class<T> enumType;

        public StringToEnum(Class<T> enumType) {
            this.enumType = enumType;
        }

        @Override
        public T convert(String source) {
            return EnumUtil.codeOf(enumType, Integer.parseInt(source));
        }
    }
}
