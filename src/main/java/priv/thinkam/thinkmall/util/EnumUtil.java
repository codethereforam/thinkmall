package priv.thinkam.thinkmall.util;

import priv.thinkam.thinkmall.enums.BaseEnum;

/**
 * 枚举工具类
 *
 * @author thinkam
 * @date 2018/03/22
 */
public class EnumUtil {
    /**
     * 根据枚举类和枚举标识码获取枚举类型
     * @param enumClass 枚举类
     * @param code 枚举标识码
     * @param <E> 枚举类型
     * @return 枚举类型
     */
    public static <E extends Enum<?> & BaseEnum> E codeOf(Class<E> enumClass, int code) {
        E[] enumConstants = enumClass.getEnumConstants();
        for (E e : enumConstants) {
            if (e.getCode() == code) {
                return e;
            }
        }
        return null;
    }
}
