package priv.thinkam.thinkmall.dao.converter;

import org.springframework.core.convert.converter.Converter;

import java.util.Date;

/**
 * 时间戳字符串转日期类型转化器
 * @author thinkam
 * @date 2018/03/25
 */
public class StringToDateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String source) {
        return new Date(Long.parseLong(source));
    }
}
