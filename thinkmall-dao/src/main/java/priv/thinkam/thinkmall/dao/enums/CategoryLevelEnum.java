package priv.thinkam.thinkmall.dao.enums;

import priv.thinkam.thinkmall.common.base.BaseEnum;

/**
 * 类别层次枚举
 *
 * @author thinkam
 * @date 2018/03/19
 */
public enum CategoryLevelEnum implements BaseEnum {
    /**
     * 一级类别
     */
    ONE(1, "一级类别"),
    /**
     * 二级类别
     */
    TWO(2, "二级类别"),
    /**
     * 三级类别
     */
    THREE(3, "三级类别");

    private int code;
    private String name;

    CategoryLevelEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }
}
